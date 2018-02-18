package com.lge.sm.cr_data_store.anemics.arepository;

/**
  * <<<<<<<< Automatically Generated Code >>>>>>>>
  * If you want to modify this file, use Inherited class in ".repository" package.
  */

import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.lge.framework.ceasar.repository.PermanenceRepository;
import com.lge.framework.ceasar.repository.CacheableRepository;
import com.lge.framework.ceasar.repository.MapSet;
import com.lge.framework.ceasar.event.EventBroker;
import com.lge.framework.ceasar.event.event_kind.LoadEvent;
import com.lge.framework.ceasar.event.event_kind.CreateEvent;
import com.lge.framework.ceasar.event.event_kind.DeleteEvent;
import com.lge.framework.ceasar.event.event_kind.UpdateEvent;
import com.lge.framework.ceasar.event.publisher.KindredEventPublisher;
import com.lge.framework.ceasar.repository.Repos;
import com.lge.framework.ceasar.util.ToString;
import com.lge.framework.ceasar.util.CriteriaUtil;
import com.lge.framework.ceasar.util.JsonUtil;
import com.lge.framework.ceasar.util.DateStringUtil;
import com.lge.framework.ceasar.service.view.Skin;

import com.lge.framework.ceasar.logger.Logger;
import com.lge.sm.cr_data_store.repository.DriverRepository;
import com.lge.sm.cr_data_store.dao.DriverDao;
import com.lge.sm.cr_data_store.entity.DriverTypeEntity;
import com.lge.sm.cr_data_store.repository.DriverTypeRepository;
import com.lge.sm.cr_data_store.entity.TcpEntity;
import com.lge.sm.cr_data_store.repository.TcpRepository;
import com.lge.sm.cr_data_store.entity.UrlEntity;
import com.lge.sm.cr_data_store.repository.UrlRepository;
import com.lge.sm.cr_data_store.entity.PointEntity;
import com.lge.sm.cr_data_store.repository.PointRepository;

import com.lge.sm.cr_data_store.entity.DriverEntity;
import com.lge.sm.cr_data_store.dto.DriverDto;
import com.lge.sm.cr_data_store.dto.DriverDtoExample;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.lge.sm.cr_data_store.repository.SkinRepository;
import com.lge.sm.cr_data_store.entity.SkinEntity;

abstract public class ADriverRepository extends CacheableRepository<DriverEntity, DriverDao, DriverDto, DriverDtoExample>{
    @SuppressWarnings("unchecked")  
    private KindredEventPublisher<LoadEvent<DriverEntity>> loadPublisher = (KindredEventPublisher<LoadEvent<DriverEntity>>)EventBroker.getPublisher(DriverEntity.class, LoadEvent.class);
    @SuppressWarnings("unchecked")  
    private KindredEventPublisher<CreateEvent<DriverEntity>> createPublisher = (KindredEventPublisher<CreateEvent<DriverEntity>>)EventBroker.getPublisher(DriverEntity.class, CreateEvent.class);
    @SuppressWarnings("unchecked")
    private KindredEventPublisher<UpdateEvent<DriverEntity>> updatePublisher = (KindredEventPublisher<UpdateEvent<DriverEntity>>)EventBroker.getPublisher(DriverEntity.class, UpdateEvent.class);
    @SuppressWarnings("unchecked")
    private KindredEventPublisher<DeleteEvent<DriverEntity>> deletePublisher = (KindredEventPublisher<DeleteEvent<DriverEntity>>)EventBroker.getPublisher(DriverEntity.class, DeleteEvent.class);

    @Autowired
    public ADriverRepository(DriverDao dao) {
        super(dao);
    }
    

	protected volatile long lastId = -1;
	public long getNextId() {
		return ++lastId;
	}

    @Override
    public DriverEntity create(DriverDto dto) throws IllegalArgumentException {
    	dto.setCdate(DateStringUtil.getCurrentDateString(DateStringUtil.gmtTimeZoneId));
		dto.setDriverId(getNextId());
        if(checkCreated(dto) == true) throw new IllegalArgumentException("Already created : " + ToString.toLine(dto));    
        if(checkForeignKeyEntityExist(dto) == false) throw new IllegalArgumentException("No record of foreign key when create : " + ToString.toLine(dto));
        if(dao.insert(dto) == false) throw new IllegalArgumentException();
            
        DriverEntity ret = load(dto);
        createPublisher.publish(new CreateEvent<DriverEntity>(cloneOf(ret)));
        
        return ret;
    }
  
    protected boolean checkForeignKeyEntityExist(DriverDto dto) {
		if(dto.getDriverTypeId() != null && Repos.repo(DriverTypeRepository.class).getByMapKey(DriverTypeEntity.newMapKey(dto.getDriverTypeId())) == null) return false;

        return true;
    }
        
    public DriverEntity cloneOf(DriverEntity entity) {
        DriverEntity ret = newEntity(entity.getDto());

        return ret;
    }
  
    protected DriverEntity newEntity(DriverDto dto){
        return new DriverEntity(dto);
    }
    
    @Transactional
    @Override
    protected boolean deleteDao(List<DriverEntity> entities) {
        super.deleteDao(entities);
		List<TcpEntity> tcpList = new ArrayList<>();
		for(DriverEntity each : entities) tcpList.addAll(Repos.repo(TcpRepository.class).getByDriverId(each.getDriverId()));
		if(tcpList.size() != 0) {
			if(Repos.repo(TcpRepository.class).delete(tcpList) == false) return false;
		}
		List<UrlEntity> urlList = new ArrayList<>();
		for(DriverEntity each : entities) urlList.addAll(Repos.repo(UrlRepository.class).getByDriverId(each.getDriverId()));
		if(urlList.size() != 0) {
			if(Repos.repo(UrlRepository.class).delete(urlList) == false) return false;
		}
		List<PointEntity> pointList = new ArrayList<>();
		for(DriverEntity each : entities) pointList.addAll(Repos.repo(PointRepository.class).getByDriverId(each.getDriverId()));
		if(pointList.size() != 0) {
			if(Repos.repo(PointRepository.class).delete(pointList) == false) return false;
		}
 
        return dao.delete(Repos.repo(DriverRepository.class).getDtoList(entities));
    }
   
    @Override
    protected boolean initDescendent() {

		lastId = dao.getLastId();

		parentSkinTypes.add(DriverTypeEntity.skinType());

		kidSkinTypes.add(TcpEntity.skinType());
		kidSkinTypes.add(UrlEntity.skinType());
		kidSkinTypes.add(PointEntity.skinType());
        return true;
    }
    
    public DriverDtoExample example(){
        return new DriverDtoExample();
    }
    
    private DriverDtoExample criteriaFactory = new DriverDtoExample();
    public DriverDtoExample.Criteria criteria(){
        return criteriaFactory.createCriteria();
    }
    
    public List<DriverEntity> getByCriteria(DriverDtoExample.Criteria c){
        DriverDtoExample newExample = new DriverDtoExample();
        newExample.or(c);
        List<DriverDto> dtos = dao.selectByExample(newExample);
        List<DriverEntity> entities = new ArrayList<>();
        if(dtos == null) return entities;
        for(DriverDto each : dtos) entities.add(load(each));
      
        return entities;
    }
    
// As a Skinner ----------------------------------------------------------------------------------------------------------------------------------------  
    private Set<String> parentSkinTypes = ConcurrentHashMap.newKeySet();
    private Set<String> kidSkinTypes = ConcurrentHashMap.newKeySet();
    
    public List<String> getParentSkinTypes(){ return new ArrayList<>(parentSkinTypes); }
    public List<String> getKidSkinTypes(){ return new ArrayList<>(kidSkinTypes); }
    
    public String skinized(JsonNode inputNode) {
        ObjectMapper om = JsonUtil.objectMapper();
        try {
            DriverDto dto = om.treeToValue(inputNode, DriverDto.class);
            DriverEntity entity = get(dto);
            if(entity == null) {
                Logger.error(TAG, "Failed to find entity");
                return "";
            }
            return skinize(entity);
        } catch (JsonProcessingException e) {
            Logger.error(TAG, "Failed to make Object by json");
            return "";
        }
    }
  
    public String skinized(DriverEntity entity) {
        return skinize(entity);
    }
    
    protected String skinize(DriverEntity entity) {
        return skinize(entity.getDto());
    }
  
    protected String skinize(DriverDto dto) {
        ObjectMapper om = JsonUtil.objectMapper();
        JsonNode node = om.valueToTree(dto);
    
        SkinEntity skinEntity = Repos.repo(SkinRepository.class).getByMapKey(SkinEntity.newMapKey(skinType()));
        ((ObjectNode) node).put("skinType", skinType());
        skinEntity.skinize(node);
    
        try {
            return om.writeValueAsString(node);
        } catch (JsonProcessingException e) {
            // TODO : error handling
        }
        
        return "";
    }
    
    @Override
    public String skinType() {
        return DriverEntity.skinType();
    }

    @Override
    public Skin skin() {
        SkinEntity entity = Repos.repo(SkinRepository.class).getByMapKey(SkinEntity.newMapKey(skinType()));
        return entity.skin(getParentSkinTypes(), getKidSkinTypes());
    }
    
    protected DriverDto jsonNodeToDto(JsonNode node) {
        ObjectMapper om = JsonUtil.objectMapper();
        try {
            DriverDto dto = om.treeToValue(node, DriverDto.class);
            return dto;
        } catch (Exception e) {
System.out.println("Exception = " + e.getMessage());
            try {
              String json = om.writeValueAsString(node);
        Logger.error(TAG, "Failed to create " + skinType() + " with jsonNode " + json);
      } catch (JsonProcessingException e1) {
System.out.println("JsonProcessingException = " + e1.getMessage());
        Logger.error(TAG, "Failed to create " + skinType());
      }
            return null;
      }
    }
    
    public String create(JsonNode nodeList) {
    	List<DriverDto> dtoList = new ArrayList<>();
		for(JsonNode each : nodeList) {
	        DriverDto dto = jsonNodeToDto(each);
	        if(dto == null) return "";
	        dtoList.add(dto);
		}
		
    	List<DriverEntity> entityList = new ArrayList<>();
		for(DriverDto dto : dtoList) {
	        DriverEntity entity = create(dto);
	        if(entity == null) Logger.error(TAG, "Failed to create : " + ToString.toLine(dto));
	        else entityList.add(entity);
		}
		
        StringBuffer ret = new StringBuffer();
        ret.append("[");
        for(int i = 0; i < entityList.size(); i++) {
        	DriverEntity entity = entityList.get(i);
            ret.append(skinize(entity));
            if(i != entityList.size() - 1) ret.append(",");
        }
        ret.append("]");
        
        return ret.toString();
    }
    
    public String update(JsonNode nodeList) {
    	List<DriverDto> dtoList = new ArrayList<>();
		for(JsonNode each : nodeList) {
	        DriverDto dto = jsonNodeToDto(each);
	        System.out.println(ToString.toLine(dto));
	        if(dto == null) return "";
	        dtoList.add(dto);
		}
		
    	List<DriverEntity> entityList = new ArrayList<>();
		for(DriverDto dto : dtoList) {
	        DriverEntity entity = newEntity(dto);
	        entityList.add(entity);
		}
		
		boolean result = update(entityList);
		if(result == false) {
			Logger.error(TAG, "Failed to update");
			return "";
		}
		
        StringBuffer ret = new StringBuffer();
        ret.append("[");
        for(int i = 0; i < entityList.size(); i++) {
        	DriverEntity entity = entityList.get(i);
            ret.append(skinize(entity));
            if(i != entityList.size() - 1) ret.append(",");
        }
        ret.append("]");
        
        return ret.toString();
    }
    
    public String delete(JsonNode nodeList) {
    	List<DriverDto> dtoList = new ArrayList<>();
		for(JsonNode each : nodeList) {
	        DriverDto dto = jsonNodeToDto(each);
	        System.out.println(ToString.toLine(dto));
	        if(dto == null) return "";
	        dtoList.add(dto);
		}
		
    	List<DriverEntity> entityList = new ArrayList<>();
		for(DriverDto dto : dtoList) {
	        DriverEntity entity = get(dto);
	        if(entity == null) Logger.error(TAG, "Failed to delete : " + ToString.toLine(dto));
	        else entityList.add(entity);
		}
		
		boolean result = delete(entityList);
		if(result == false) {
			Logger.error(TAG, "Failed to delete");
			return "";
		}
		
        StringBuffer ret = new StringBuffer();
        ret.append("[");
        for(int i = 0; i < entityList.size(); i++) {
        	DriverEntity entity = entityList.get(i);
            ret.append(skinize(entity));
            if(i != entityList.size() - 1) ret.append(",");
        }
        ret.append("]");
        
        return ret.toString();
    }
    
    public String getSkinizedKids(JsonNode node, String kidSkinType) {
        DriverDto dto = jsonNodeToDto(node);
        if(dto == null) return "";
        DriverEntity entity = get(dto);
        if(entity == null) return "";

		if(kidSkinType.equals("Tcp")) {
			List<TcpEntity> list = entity.getTcpEntityList();
			TcpRepository repo = Repos.repo(TcpRepository.class);
			StringBuffer ret = new StringBuffer();
			ret.append("[");
			for(int i = 0; i < list.size(); i++) {
				TcpEntity each = list.get(i);
				ret.append(repo.skinized(each));
				if(i != list.size()-1) ret.append(",");
			}
			ret.append("]");
			return ret.toString();
		}
		if(kidSkinType.equals("Url")) {
			List<UrlEntity> list = entity.getUrlEntityList();
			UrlRepository repo = Repos.repo(UrlRepository.class);
			StringBuffer ret = new StringBuffer();
			ret.append("[");
			for(int i = 0; i < list.size(); i++) {
				UrlEntity each = list.get(i);
				ret.append(repo.skinized(each));
				if(i != list.size()-1) ret.append(",");
			}
			ret.append("]");
			return ret.toString();
		}
		if(kidSkinType.equals("Point")) {
			List<PointEntity> list = entity.getPointEntityList();
			PointRepository repo = Repos.repo(PointRepository.class);
			StringBuffer ret = new StringBuffer();
			ret.append("[");
			for(int i = 0; i < list.size(); i++) {
				PointEntity each = list.get(i);
				ret.append(repo.skinized(each));
				if(i != list.size()-1) ret.append(",");
			}
			ret.append("]");
			return ret.toString();
		}
        
        return "";
    }
    
    @Override
    public String read(Map<String, String> from, Map<String, String> to, Map<String, List<String>> maps, int limit) {
        List<DriverDto> dtos = new ArrayList<>();
        Object[] keySet = maps.keySet().toArray();
        int idx[] = new int[keySet.length];
            
        while(true){
            DriverDtoExample example = example();
            example.setOrderByClause("cdate desc limit " + limit);
            DriverDtoExample.Criteria criteria = example.or();
      
            int row = 0;
            while(row < keySet.length) {
                List<String> columns = maps.get(keySet[row]);
                if(columns.size() == 0) {
                    row++;
                    continue;
                }

                if(CriteriaUtil.setEqualCriteria(criteria, (String)keySet[row], columns.get(idx[row])) == false) {
                    Logger.error(TAG, "Failed to make database read criteria " + (String)keySet[row] + " equal to " + columns.get(idx[row]));
                    return "";
                }
          
                row++;
            }
        
            for(String key : from.keySet()) {
                String fieldFrom = from.get(key);
                if(fieldFrom == "") continue;
                String fieldTo = to.get(key);
                if(CriteriaUtil.setBetweenCriteria(criteria, (String)key, fieldFrom, fieldTo) == false) {
                    Logger.error(TAG, "Failed to make database read criteria " + (String)keySet[row] + " bwtween " + fieldFrom + " to " + fieldTo);
                    return "";
                }
            }
        
            List<DriverDto> selected = dao.selectByExample(example);
            dtos.addAll(selected);
        
            int r = CriteriaUtil.calculateCriteriaCombination(idx, maps, keySet);
            if(r == -1) break;
        }
      
        StringBuffer ret = new StringBuffer();
        ret.append("[");
        for(int i = dtos.size() - 1; i >= 0; i--) {
            ret.append(skinize(dtos.get(i)));
            if(i != 0) ret.append(",");
        }
        ret.append("]");

        return ret.toString();
    }
     


    public String skinizedAll() {
        List<String> all = new ArrayList<String>();
        for(DriverEntity entity : getAll()) {
            all.add(skinized(entity));
        }
      
        StringBuffer ret = new StringBuffer();
        ret.append("[");
        for(int i = 0; i < all.size(); i++) {
            ret.append(all.get(i));
            if(i != all.size()-1) ret.append(",");
        }
        ret.append("]");
        return ret.toString();
    }

// ----------------------------------------------------------------------------------------------------------------------------------------


	protected MapSet<DriverEntity> driverTypeMapSet = new MapSet<>();

	public List<DriverEntity> getByDriverTypeId(String driverTypeId) {
		return driverTypeMapSet.get(DriverTypeEntity.newMapKey(driverTypeId));
	}

    @Override
    public DriverEntity get(DriverDto dto) {
        return map.get(DriverEntity.newMapKey(dto));
    }
       
    protected boolean checkCreated(DriverDto dto){
        if(map.containsKey(DriverEntity.newMapKey(dto))) return true;
        return false;
    }
    
    protected DriverEntity load(DriverDto dto) {
        DriverEntity entity = newEntity(dto);
        map.put(entity.mapKey(), entity);    

		driverTypeMapSet.put(DriverTypeEntity.newMapKey(entity.getDriverTypeId()), entity);
        
        return entity;
    }
    
    protected void loaded(DriverEntity entity){
        loadPublisher.publish(new LoadEvent<DriverEntity>(cloneOf(entity)));
    }
        
    @Override
    protected void updated(List<DriverEntity> entities) {
        for(DriverEntity entity : entities) updatePublisher.publish(new UpdateEvent<DriverEntity>(cloneOf(entity)));
    }
  
    @Override
    protected void daoDeleted(List<DriverEntity> entities) {
        super.daoDeleted(entities);
        for(DriverEntity entity : entities) deletePublisher.publish(new DeleteEvent<DriverEntity>(cloneOf(entity)));
		for(DriverEntity each : entities) driverTypeMapSet.remove(DriverTypeEntity.newMapKey(each.getDriverTypeId()), each);
    }
    
    @Override
    protected List<DriverDto> dtoListToStart() {
        return dao.selectAll();
    }
}
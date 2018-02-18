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
import com.lge.sm.cr_data_store.repository.PointRepository;
import com.lge.sm.cr_data_store.dao.PointDao;
import com.lge.sm.cr_data_store.entity.DriverEntity;
import com.lge.sm.cr_data_store.repository.DriverRepository;
import com.lge.sm.cr_data_store.entity.PointEntity;
import com.lge.sm.cr_data_store.repository.PointRepository;
import com.lge.sm.cr_data_store.entity.SpotEntity;
import com.lge.sm.cr_data_store.repository.SpotRepository;
import com.lge.sm.cr_data_store.entity.PointAttributeEntity;
import com.lge.sm.cr_data_store.repository.PointAttributeRepository;
import com.lge.sm.cr_data_store.entity.PointEntity;
import com.lge.sm.cr_data_store.repository.PointRepository;

import com.lge.sm.cr_data_store.entity.PointEntity;
import com.lge.sm.cr_data_store.dto.PointDto;
import com.lge.sm.cr_data_store.dto.PointDtoExample;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.lge.sm.cr_data_store.repository.SkinRepository;
import com.lge.sm.cr_data_store.entity.SkinEntity;

abstract public class APointRepository extends CacheableRepository<PointEntity, PointDao, PointDto, PointDtoExample>{
    @SuppressWarnings("unchecked")  
    private KindredEventPublisher<LoadEvent<PointEntity>> loadPublisher = (KindredEventPublisher<LoadEvent<PointEntity>>)EventBroker.getPublisher(PointEntity.class, LoadEvent.class);
    @SuppressWarnings("unchecked")  
    private KindredEventPublisher<CreateEvent<PointEntity>> createPublisher = (KindredEventPublisher<CreateEvent<PointEntity>>)EventBroker.getPublisher(PointEntity.class, CreateEvent.class);
    @SuppressWarnings("unchecked")
    private KindredEventPublisher<UpdateEvent<PointEntity>> updatePublisher = (KindredEventPublisher<UpdateEvent<PointEntity>>)EventBroker.getPublisher(PointEntity.class, UpdateEvent.class);
    @SuppressWarnings("unchecked")
    private KindredEventPublisher<DeleteEvent<PointEntity>> deletePublisher = (KindredEventPublisher<DeleteEvent<PointEntity>>)EventBroker.getPublisher(PointEntity.class, DeleteEvent.class);

    @Autowired
    public APointRepository(PointDao dao) {
        super(dao);
    }
    

	protected volatile long lastId = -1;
	public long getNextId() {
		return ++lastId;
	}

    @Override
    public PointEntity create(PointDto dto) throws IllegalArgumentException {
    	dto.setCdate(DateStringUtil.getCurrentDateString(DateStringUtil.gmtTimeZoneId));
		dto.setPointId(getNextId());
        if(checkCreated(dto) == true) throw new IllegalArgumentException("Already created : " + ToString.toLine(dto));    
        if(checkForeignKeyEntityExist(dto) == false) throw new IllegalArgumentException("No record of foreign key when create : " + ToString.toLine(dto));
        if(dao.insert(dto) == false) throw new IllegalArgumentException();
            
        PointEntity ret = load(dto);
        createPublisher.publish(new CreateEvent<PointEntity>(cloneOf(ret)));
        
        return ret;
    }
  
    protected boolean checkForeignKeyEntityExist(PointDto dto) {
		if(dto.getDriverId() != null && Repos.repo(DriverRepository.class).getByMapKey(DriverEntity.newMapKey(dto.getDriverId())) == null) return false;
		if(dto.getParentPointId() != null && Repos.repo(PointRepository.class).getByMapKey(PointEntity.newMapKey(dto.getParentPointId())) == null) return false;

        return true;
    }
        
    public PointEntity cloneOf(PointEntity entity) {
        PointEntity ret = newEntity(entity.getDto());

        return ret;
    }
  
    protected PointEntity newEntity(PointDto dto){
        return new PointEntity(dto);
    }
    
    @Transactional
    @Override
    protected boolean deleteDao(List<PointEntity> entities) {
        super.deleteDao(entities);
		List<SpotEntity> spotList = new ArrayList<>();
		for(PointEntity each : entities) spotList.addAll(Repos.repo(SpotRepository.class).getByPointId(each.getPointId()));
		if(spotList.size() != 0) {
			if(Repos.repo(SpotRepository.class).delete(spotList) == false) return false;
		}
		List<PointAttributeEntity> pointAttributeList = new ArrayList<>();
		for(PointEntity each : entities) pointAttributeList.addAll(Repos.repo(PointAttributeRepository.class).getByPointId(each.getPointId()));
		if(pointAttributeList.size() != 0) {
			if(Repos.repo(PointAttributeRepository.class).delete(pointAttributeList) == false) return false;
		}
		List<PointEntity> pointList = new ArrayList<>();
		for(PointEntity each : entities) pointList.addAll(Repos.repo(PointRepository.class).getByPointId(each.getPointId()));
		if(pointList.size() != 0) {
			if(Repos.repo(PointRepository.class).delete(pointList) == false) return false;
		}
 
        return dao.delete(Repos.repo(PointRepository.class).getDtoList(entities));
    }
   
    @Override
    protected boolean initDescendent() {

		lastId = dao.getLastId();

		parentSkinTypes.add(DriverEntity.skinType());
		parentSkinTypes.add(PointEntity.skinType());

		kidSkinTypes.add(SpotEntity.skinType());
		kidSkinTypes.add(PointAttributeEntity.skinType());
		kidSkinTypes.add(PointEntity.skinType());
        return true;
    }
    
    public PointDtoExample example(){
        return new PointDtoExample();
    }
    
    private PointDtoExample criteriaFactory = new PointDtoExample();
    public PointDtoExample.Criteria criteria(){
        return criteriaFactory.createCriteria();
    }
    
    public List<PointEntity> getByCriteria(PointDtoExample.Criteria c){
        PointDtoExample newExample = new PointDtoExample();
        newExample.or(c);
        List<PointDto> dtos = dao.selectByExample(newExample);
        List<PointEntity> entities = new ArrayList<>();
        if(dtos == null) return entities;
        for(PointDto each : dtos) entities.add(load(each));
      
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
            PointDto dto = om.treeToValue(inputNode, PointDto.class);
            PointEntity entity = get(dto);
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
  
    public String skinized(PointEntity entity) {
        return skinize(entity);
    }
    
    protected String skinize(PointEntity entity) {
        return skinize(entity.getDto());
    }
  
    protected String skinize(PointDto dto) {
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
        return PointEntity.skinType();
    }

    @Override
    public Skin skin() {
        SkinEntity entity = Repos.repo(SkinRepository.class).getByMapKey(SkinEntity.newMapKey(skinType()));
        return entity.skin(getParentSkinTypes(), getKidSkinTypes());
    }
    
    protected PointDto jsonNodeToDto(JsonNode node) {
        ObjectMapper om = JsonUtil.objectMapper();
        try {
            PointDto dto = om.treeToValue(node, PointDto.class);
            return dto;
        } catch (Exception e) {
            try {
              String json = om.writeValueAsString(node);
        Logger.error(TAG, "Failed to create " + skinType() + " with jsonNode " + json);
      } catch (JsonProcessingException e1) {
        Logger.error(TAG, "Failed to create " + skinType());
      }
            return null;
      }
    }
    
    public String create(JsonNode nodeList) {
    	List<PointDto> dtoList = new ArrayList<>();
		for(JsonNode each : nodeList) {
	        PointDto dto = jsonNodeToDto(each);
	        if(dto == null) return "";
	        dtoList.add(dto);
		}
		
    	List<PointEntity> entityList = new ArrayList<>();
		for(PointDto dto : dtoList) {
	        PointEntity entity = create(dto);
	        if(entity == null) Logger.error(TAG, "Failed to create : " + ToString.toLine(dto));
	        else entityList.add(entity);
		}
		
        StringBuffer ret = new StringBuffer();
        ret.append("[");
        for(int i = 0; i < entityList.size(); i++) {
        	PointEntity entity = entityList.get(i);
            ret.append(skinize(entity));
            if(i != entityList.size() - 1) ret.append(",");
        }
        ret.append("]");
        
        return ret.toString();
    }
    
    public String update(JsonNode nodeList) {
    	List<PointDto> dtoList = new ArrayList<>();
		for(JsonNode each : nodeList) {
	        PointDto dto = jsonNodeToDto(each);
	        System.out.println(ToString.toLine(dto));
	        if(dto == null) return "";
	        dtoList.add(dto);
		}
		
    	List<PointEntity> entityList = new ArrayList<>();
		for(PointDto dto : dtoList) {
	        PointEntity entity = newEntity(dto);
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
        	PointEntity entity = entityList.get(i);
            ret.append(skinize(entity));
            if(i != entityList.size() - 1) ret.append(",");
        }
        ret.append("]");
        
        return ret.toString();
    }
    
    public String delete(JsonNode nodeList) {
    	List<PointDto> dtoList = new ArrayList<>();
		for(JsonNode each : nodeList) {
	        PointDto dto = jsonNodeToDto(each);
	        System.out.println(ToString.toLine(dto));
	        if(dto == null) return "";
	        dtoList.add(dto);
		}
		
    	List<PointEntity> entityList = new ArrayList<>();
		for(PointDto dto : dtoList) {
	        PointEntity entity = get(dto);
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
        	PointEntity entity = entityList.get(i);
            ret.append(skinize(entity));
            if(i != entityList.size() - 1) ret.append(",");
        }
        ret.append("]");
        
        return ret.toString();
    }
    
    public String getSkinizedKids(JsonNode node, String kidSkinType) {
        PointDto dto = jsonNodeToDto(node);
        if(dto == null) return "";
        PointEntity entity = get(dto);
        if(entity == null) return "";

		if(kidSkinType.equals("Spot")) {
			List<SpotEntity> list = entity.getSpotEntityList();
			SpotRepository repo = Repos.repo(SpotRepository.class);
			StringBuffer ret = new StringBuffer();
			ret.append("[");
			for(int i = 0; i < list.size(); i++) {
				SpotEntity each = list.get(i);
				ret.append(repo.skinized(each));
				if(i != list.size()-1) ret.append(",");
			}
			ret.append("]");
			return ret.toString();
		}
		if(kidSkinType.equals("PointAttribute")) {
			List<PointAttributeEntity> list = entity.getPointAttributeEntityList();
			PointAttributeRepository repo = Repos.repo(PointAttributeRepository.class);
			StringBuffer ret = new StringBuffer();
			ret.append("[");
			for(int i = 0; i < list.size(); i++) {
				PointAttributeEntity each = list.get(i);
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
        List<PointDto> dtos = new ArrayList<>();
        Object[] keySet = maps.keySet().toArray();
        int idx[] = new int[keySet.length];
            
        while(true){
            PointDtoExample example = example();
            example.setOrderByClause("cdate desc limit " + limit);
            PointDtoExample.Criteria criteria = example.or();
      
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
        
            List<PointDto> selected = dao.selectByExample(example);
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
        for(PointEntity entity : getAll()) {
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


	protected MapSet<PointEntity> driverMapSet = new MapSet<>();
	protected MapSet<PointEntity> pointMapSet = new MapSet<>();

	public List<PointEntity> getByDriverId(Long driverId) {
		return driverMapSet.get(DriverEntity.newMapKey(driverId));
	}
	public List<PointEntity> getByPointId(Long parentPointId) {
		return pointMapSet.get(PointEntity.newMapKey(parentPointId));
	}

    @Override
    public PointEntity get(PointDto dto) {
        return map.get(PointEntity.newMapKey(dto));
    }
       
    protected boolean checkCreated(PointDto dto){
        if(map.containsKey(PointEntity.newMapKey(dto))) return true;
        return false;
    }
    
    protected PointEntity load(PointDto dto) {
        PointEntity entity = newEntity(dto);
        map.put(entity.mapKey(), entity);    

		driverMapSet.put(DriverEntity.newMapKey(entity.getDriverId()), entity);
		pointMapSet.put(PointEntity.newMapKey(entity.getParentPointId()), entity);
        
        return entity;
    }
    
    protected void loaded(PointEntity entity){
        loadPublisher.publish(new LoadEvent<PointEntity>(cloneOf(entity)));
    }
        
    @Override
    protected void updated(List<PointEntity> entities) {
        for(PointEntity entity : entities) updatePublisher.publish(new UpdateEvent<PointEntity>(cloneOf(entity)));
    }
  
    @Override
    protected void daoDeleted(List<PointEntity> entities) {
        super.daoDeleted(entities);
        for(PointEntity entity : entities) deletePublisher.publish(new DeleteEvent<PointEntity>(cloneOf(entity)));
		for(PointEntity each : entities) driverMapSet.remove(DriverEntity.newMapKey(each.getDriverId()), each);		for(PointEntity each : entities) pointMapSet.remove(PointEntity.newMapKey(each.getPointId()), each);
    }
    
    @Override
    protected List<PointDto> dtoListToStart() {
        return dao.selectAll();
    }
}
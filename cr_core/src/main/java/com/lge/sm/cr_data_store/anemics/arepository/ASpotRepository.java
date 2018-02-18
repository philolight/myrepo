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
import com.lge.sm.cr_data_store.repository.SpotRepository;
import com.lge.sm.cr_data_store.dao.SpotDao;
import com.lge.sm.cr_data_store.entity.PointEntity;
import com.lge.sm.cr_data_store.repository.PointRepository;

import com.lge.sm.cr_data_store.entity.SpotEntity;
import com.lge.sm.cr_data_store.dto.SpotDto;
import com.lge.sm.cr_data_store.dto.SpotDtoExample;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.lge.sm.cr_data_store.repository.SkinRepository;
import com.lge.sm.cr_data_store.entity.SkinEntity;

abstract public class ASpotRepository extends CacheableRepository<SpotEntity, SpotDao, SpotDto, SpotDtoExample>{
    @SuppressWarnings("unchecked")  
    private KindredEventPublisher<LoadEvent<SpotEntity>> loadPublisher = (KindredEventPublisher<LoadEvent<SpotEntity>>)EventBroker.getPublisher(SpotEntity.class, LoadEvent.class);
    @SuppressWarnings("unchecked")  
    private KindredEventPublisher<CreateEvent<SpotEntity>> createPublisher = (KindredEventPublisher<CreateEvent<SpotEntity>>)EventBroker.getPublisher(SpotEntity.class, CreateEvent.class);
    @SuppressWarnings("unchecked")
    private KindredEventPublisher<UpdateEvent<SpotEntity>> updatePublisher = (KindredEventPublisher<UpdateEvent<SpotEntity>>)EventBroker.getPublisher(SpotEntity.class, UpdateEvent.class);
    @SuppressWarnings("unchecked")
    private KindredEventPublisher<DeleteEvent<SpotEntity>> deletePublisher = (KindredEventPublisher<DeleteEvent<SpotEntity>>)EventBroker.getPublisher(SpotEntity.class, DeleteEvent.class);

    @Autowired
    public ASpotRepository(SpotDao dao) {
        super(dao);
    }
    

	protected volatile long lastId = -1;
	public long getNextId() {
		return ++lastId;
	}

    @Override
    public SpotEntity create(SpotDto dto) throws IllegalArgumentException {
    	dto.setCdate(DateStringUtil.getCurrentDateString(DateStringUtil.gmtTimeZoneId));
		dto.setSpotId(getNextId());
        if(checkCreated(dto) == true) throw new IllegalArgumentException("Already created : " + ToString.toLine(dto));    
        if(checkForeignKeyEntityExist(dto) == false) throw new IllegalArgumentException("No record of foreign key when create : " + ToString.toLine(dto));
        if(dao.insert(dto) == false) throw new IllegalArgumentException();
            
        SpotEntity ret = load(dto);
        createPublisher.publish(new CreateEvent<SpotEntity>(cloneOf(ret)));
        
        return ret;
    }
  
    protected boolean checkForeignKeyEntityExist(SpotDto dto) {
		if(dto.getPointId() != null && Repos.repo(PointRepository.class).getByMapKey(PointEntity.newMapKey(dto.getPointId())) == null) return false;

        return true;
    }
        
    public SpotEntity cloneOf(SpotEntity entity) {
        SpotEntity ret = newEntity(entity.getDto());

        return ret;
    }
  
    protected SpotEntity newEntity(SpotDto dto){
        return new SpotEntity(dto);
    }
    
    @Transactional
    @Override
    protected boolean deleteDao(List<SpotEntity> entities) {
        super.deleteDao(entities);
 
        return dao.delete(Repos.repo(SpotRepository.class).getDtoList(entities));
    }
   
    @Override
    protected boolean initDescendent() {

		lastId = dao.getLastId();

		parentSkinTypes.add(PointEntity.skinType());

        return true;
    }
    
    public SpotDtoExample example(){
        return new SpotDtoExample();
    }
    
    private SpotDtoExample criteriaFactory = new SpotDtoExample();
    public SpotDtoExample.Criteria criteria(){
        return criteriaFactory.createCriteria();
    }
    
    public List<SpotEntity> getByCriteria(SpotDtoExample.Criteria c){
        SpotDtoExample newExample = new SpotDtoExample();
        newExample.or(c);
        List<SpotDto> dtos = dao.selectByExample(newExample);
        List<SpotEntity> entities = new ArrayList<>();
        if(dtos == null) return entities;
        for(SpotDto each : dtos) entities.add(load(each));
      
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
            SpotDto dto = om.treeToValue(inputNode, SpotDto.class);
            SpotEntity entity = get(dto);
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
  
    public String skinized(SpotEntity entity) {
        return skinize(entity);
    }
    
    protected String skinize(SpotEntity entity) {
        return skinize(entity.getDto());
    }
  
    protected String skinize(SpotDto dto) {
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
        return SpotEntity.skinType();
    }

    @Override
    public Skin skin() {
        SkinEntity entity = Repos.repo(SkinRepository.class).getByMapKey(SkinEntity.newMapKey(skinType()));
        return entity.skin(getParentSkinTypes(), getKidSkinTypes());
    }
    
    protected SpotDto jsonNodeToDto(JsonNode node) {
        ObjectMapper om = JsonUtil.objectMapper();
        try {
            SpotDto dto = om.treeToValue(node, SpotDto.class);
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
    	List<SpotDto> dtoList = new ArrayList<>();
		for(JsonNode each : nodeList) {
	        SpotDto dto = jsonNodeToDto(each);
	        if(dto == null) return "";
	        dtoList.add(dto);
		}
		
    	List<SpotEntity> entityList = new ArrayList<>();
		for(SpotDto dto : dtoList) {
	        SpotEntity entity = create(dto);
	        if(entity == null) Logger.error(TAG, "Failed to create : " + ToString.toLine(dto));
	        else entityList.add(entity);
		}
		
        StringBuffer ret = new StringBuffer();
        ret.append("[");
        for(int i = 0; i < entityList.size(); i++) {
        	SpotEntity entity = entityList.get(i);
            ret.append(skinize(entity));
            if(i != entityList.size() - 1) ret.append(",");
        }
        ret.append("]");
        
        return ret.toString();
    }
    
    public String update(JsonNode nodeList) {
    	List<SpotDto> dtoList = new ArrayList<>();
		for(JsonNode each : nodeList) {
	        SpotDto dto = jsonNodeToDto(each);
	        System.out.println(ToString.toLine(dto));
	        if(dto == null) return "";
	        dtoList.add(dto);
		}
		
    	List<SpotEntity> entityList = new ArrayList<>();
		for(SpotDto dto : dtoList) {
	        SpotEntity entity = newEntity(dto);
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
        	SpotEntity entity = entityList.get(i);
            ret.append(skinize(entity));
            if(i != entityList.size() - 1) ret.append(",");
        }
        ret.append("]");
        
        return ret.toString();
    }
    
    public String delete(JsonNode nodeList) {
    	List<SpotDto> dtoList = new ArrayList<>();
		for(JsonNode each : nodeList) {
	        SpotDto dto = jsonNodeToDto(each);
	        System.out.println(ToString.toLine(dto));
	        if(dto == null) return "";
	        dtoList.add(dto);
		}
		
    	List<SpotEntity> entityList = new ArrayList<>();
		for(SpotDto dto : dtoList) {
	        SpotEntity entity = get(dto);
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
        	SpotEntity entity = entityList.get(i);
            ret.append(skinize(entity));
            if(i != entityList.size() - 1) ret.append(",");
        }
        ret.append("]");
        
        return ret.toString();
    }
    
    public String getSkinizedKids(JsonNode node, String kidSkinType) {
        SpotDto dto = jsonNodeToDto(node);
        if(dto == null) return "";
        SpotEntity entity = get(dto);
        if(entity == null) return "";

        
        return "";
    }
    
    @Override
    public String read(Map<String, String> from, Map<String, String> to, Map<String, List<String>> maps, int limit) {
        List<SpotDto> dtos = new ArrayList<>();
        Object[] keySet = maps.keySet().toArray();
        int idx[] = new int[keySet.length];
            
        while(true){
            SpotDtoExample example = example();
            example.setOrderByClause("cdate desc limit " + limit);
            SpotDtoExample.Criteria criteria = example.or();
      
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
        
            List<SpotDto> selected = dao.selectByExample(example);
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
        for(SpotEntity entity : getAll()) {
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


	protected MapSet<SpotEntity> pointMapSet = new MapSet<>();

	public List<SpotEntity> getByPointId(Long pointId) {
		return pointMapSet.get(PointEntity.newMapKey(pointId));
	}

    @Override
    public SpotEntity get(SpotDto dto) {
        return map.get(SpotEntity.newMapKey(dto));
    }
       
    protected boolean checkCreated(SpotDto dto){
        if(map.containsKey(SpotEntity.newMapKey(dto))) return true;
        return false;
    }
    
    protected SpotEntity load(SpotDto dto) {
        SpotEntity entity = newEntity(dto);
        map.put(entity.mapKey(), entity);    

		pointMapSet.put(PointEntity.newMapKey(entity.getPointId()), entity);
        
        return entity;
    }
    
    protected void loaded(SpotEntity entity){
        loadPublisher.publish(new LoadEvent<SpotEntity>(cloneOf(entity)));
    }
        
    @Override
    protected void updated(List<SpotEntity> entities) {
        for(SpotEntity entity : entities) updatePublisher.publish(new UpdateEvent<SpotEntity>(cloneOf(entity)));
    }
  
    @Override
    protected void daoDeleted(List<SpotEntity> entities) {
        super.daoDeleted(entities);
        for(SpotEntity entity : entities) deletePublisher.publish(new DeleteEvent<SpotEntity>(cloneOf(entity)));
		for(SpotEntity each : entities) pointMapSet.remove(PointEntity.newMapKey(each.getPointId()), each);
    }
    
    @Override
    protected List<SpotDto> dtoListToStart() {
        return dao.selectAll();
    }
}
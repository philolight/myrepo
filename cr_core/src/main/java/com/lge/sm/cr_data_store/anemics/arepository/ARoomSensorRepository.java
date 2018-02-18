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
import com.lge.sm.cr_data_store.repository.RoomSensorRepository;
import com.lge.sm.cr_data_store.dao.RoomSensorDao;
import com.lge.sm.cr_data_store.entity.RoomEntity;
import com.lge.sm.cr_data_store.repository.RoomRepository;
import com.lge.sm.cr_data_store.entity.SensorEntity;
import com.lge.sm.cr_data_store.repository.SensorRepository;
import com.lge.sm.cr_data_store.entity.LocationEntity;
import com.lge.sm.cr_data_store.repository.LocationRepository;

import com.lge.sm.cr_data_store.entity.RoomSensorEntity;
import com.lge.sm.cr_data_store.dto.RoomSensorDto;
import com.lge.sm.cr_data_store.dto.RoomSensorDtoExample;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.lge.sm.cr_data_store.repository.SkinRepository;
import com.lge.sm.cr_data_store.entity.SkinEntity;

abstract public class ARoomSensorRepository extends CacheableRepository<RoomSensorEntity, RoomSensorDao, RoomSensorDto, RoomSensorDtoExample>{
    @SuppressWarnings("unchecked")  
    private KindredEventPublisher<LoadEvent<RoomSensorEntity>> loadPublisher = (KindredEventPublisher<LoadEvent<RoomSensorEntity>>)EventBroker.getPublisher(RoomSensorEntity.class, LoadEvent.class);
    @SuppressWarnings("unchecked")  
    private KindredEventPublisher<CreateEvent<RoomSensorEntity>> createPublisher = (KindredEventPublisher<CreateEvent<RoomSensorEntity>>)EventBroker.getPublisher(RoomSensorEntity.class, CreateEvent.class);
    @SuppressWarnings("unchecked")
    private KindredEventPublisher<UpdateEvent<RoomSensorEntity>> updatePublisher = (KindredEventPublisher<UpdateEvent<RoomSensorEntity>>)EventBroker.getPublisher(RoomSensorEntity.class, UpdateEvent.class);
    @SuppressWarnings("unchecked")
    private KindredEventPublisher<DeleteEvent<RoomSensorEntity>> deletePublisher = (KindredEventPublisher<DeleteEvent<RoomSensorEntity>>)EventBroker.getPublisher(RoomSensorEntity.class, DeleteEvent.class);

    @Autowired
    public ARoomSensorRepository(RoomSensorDao dao) {
        super(dao);
    }
    

	protected volatile long lastId = -1;
	public long getNextId() {
		return ++lastId;
	}

    @Override
    public RoomSensorEntity create(RoomSensorDto dto) throws IllegalArgumentException {
    	dto.setCdate(DateStringUtil.getCurrentDateString(DateStringUtil.gmtTimeZoneId));
		dto.setRoomSensorId(getNextId());
        if(checkCreated(dto) == true) throw new IllegalArgumentException("Already created : " + ToString.toLine(dto));    
        if(checkForeignKeyEntityExist(dto) == false) throw new IllegalArgumentException("No record of foreign key when create : " + ToString.toLine(dto));
        if(dao.insert(dto) == false) throw new IllegalArgumentException();
            
        RoomSensorEntity ret = load(dto);
        createPublisher.publish(new CreateEvent<RoomSensorEntity>(cloneOf(ret)));
        
        return ret;
    }
  
    protected boolean checkForeignKeyEntityExist(RoomSensorDto dto) {
		if(dto.getRoomId() != null && Repos.repo(RoomRepository.class).getByMapKey(RoomEntity.newMapKey(dto.getRoomId())) == null) return false;
		if(dto.getSensorId() != null && Repos.repo(SensorRepository.class).getByMapKey(SensorEntity.newMapKey(dto.getSensorId())) == null) return false;
		if(dto.getLocationId() != null && Repos.repo(LocationRepository.class).getByMapKey(LocationEntity.newMapKey(dto.getLocationId())) == null) return false;

        return true;
    }
        
    public RoomSensorEntity cloneOf(RoomSensorEntity entity) {
        RoomSensorEntity ret = newEntity(entity.getDto());

        return ret;
    }
  
    protected RoomSensorEntity newEntity(RoomSensorDto dto){
        return new RoomSensorEntity(dto);
    }
    
    @Transactional
    @Override
    protected boolean deleteDao(List<RoomSensorEntity> entities) {
        super.deleteDao(entities);
 
        return dao.delete(Repos.repo(RoomSensorRepository.class).getDtoList(entities));
    }
   
    @Override
    protected boolean initDescendent() {

		lastId = dao.getLastId();

		parentSkinTypes.add(RoomEntity.skinType());
		parentSkinTypes.add(SensorEntity.skinType());
		parentSkinTypes.add(LocationEntity.skinType());

        return true;
    }
    
    public RoomSensorDtoExample example(){
        return new RoomSensorDtoExample();
    }
    
    private RoomSensorDtoExample criteriaFactory = new RoomSensorDtoExample();
    public RoomSensorDtoExample.Criteria criteria(){
        return criteriaFactory.createCriteria();
    }
    
    public List<RoomSensorEntity> getByCriteria(RoomSensorDtoExample.Criteria c){
        RoomSensorDtoExample newExample = new RoomSensorDtoExample();
        newExample.or(c);
        List<RoomSensorDto> dtos = dao.selectByExample(newExample);
        List<RoomSensorEntity> entities = new ArrayList<>();
        if(dtos == null) return entities;
        for(RoomSensorDto each : dtos) entities.add(load(each));
      
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
            RoomSensorDto dto = om.treeToValue(inputNode, RoomSensorDto.class);
            RoomSensorEntity entity = get(dto);
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
  
    public String skinized(RoomSensorEntity entity) {
        return skinize(entity);
    }
    
    protected String skinize(RoomSensorEntity entity) {
        return skinize(entity.getDto());
    }
  
    protected String skinize(RoomSensorDto dto) {
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
        return RoomSensorEntity.skinType();
    }

    @Override
    public Skin skin() {
        SkinEntity entity = Repos.repo(SkinRepository.class).getByMapKey(SkinEntity.newMapKey(skinType()));
        return entity.skin(getParentSkinTypes(), getKidSkinTypes());
    }
    
    protected RoomSensorDto jsonNodeToDto(JsonNode node) {
        ObjectMapper om = JsonUtil.objectMapper();
        try {
            RoomSensorDto dto = om.treeToValue(node, RoomSensorDto.class);
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
    	List<RoomSensorDto> dtoList = new ArrayList<>();
		for(JsonNode each : nodeList) {
	        RoomSensorDto dto = jsonNodeToDto(each);
	        if(dto == null) return "";
	        dtoList.add(dto);
		}
		
    	List<RoomSensorEntity> entityList = new ArrayList<>();
		for(RoomSensorDto dto : dtoList) {
	        RoomSensorEntity entity = create(dto);
	        if(entity == null) Logger.error(TAG, "Failed to create : " + ToString.toLine(dto));
	        else entityList.add(entity);
		}
		
        StringBuffer ret = new StringBuffer();
        ret.append("[");
        for(int i = 0; i < entityList.size(); i++) {
        	RoomSensorEntity entity = entityList.get(i);
            ret.append(skinize(entity));
            if(i != entityList.size() - 1) ret.append(",");
        }
        ret.append("]");
        
        return ret.toString();
    }
    
    public String update(JsonNode nodeList) {
    	List<RoomSensorDto> dtoList = new ArrayList<>();
		for(JsonNode each : nodeList) {
	        RoomSensorDto dto = jsonNodeToDto(each);
	        System.out.println(ToString.toLine(dto));
	        if(dto == null) return "";
	        dtoList.add(dto);
		}
		
    	List<RoomSensorEntity> entityList = new ArrayList<>();
		for(RoomSensorDto dto : dtoList) {
	        RoomSensorEntity entity = newEntity(dto);
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
        	RoomSensorEntity entity = entityList.get(i);
            ret.append(skinize(entity));
            if(i != entityList.size() - 1) ret.append(",");
        }
        ret.append("]");
        
        return ret.toString();
    }
    
    public String delete(JsonNode nodeList) {
    	List<RoomSensorDto> dtoList = new ArrayList<>();
		for(JsonNode each : nodeList) {
	        RoomSensorDto dto = jsonNodeToDto(each);
	        System.out.println(ToString.toLine(dto));
	        if(dto == null) return "";
	        dtoList.add(dto);
		}
		
    	List<RoomSensorEntity> entityList = new ArrayList<>();
		for(RoomSensorDto dto : dtoList) {
	        RoomSensorEntity entity = get(dto);
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
        	RoomSensorEntity entity = entityList.get(i);
            ret.append(skinize(entity));
            if(i != entityList.size() - 1) ret.append(",");
        }
        ret.append("]");
        
        return ret.toString();
    }
    
    public String getSkinizedKids(JsonNode node, String kidSkinType) {
        RoomSensorDto dto = jsonNodeToDto(node);
        if(dto == null) return "";
        RoomSensorEntity entity = get(dto);
        if(entity == null) return "";

        
        return "";
    }
    
    @Override
    public String read(Map<String, String> from, Map<String, String> to, Map<String, List<String>> maps, int limit) {
        List<RoomSensorDto> dtos = new ArrayList<>();
        Object[] keySet = maps.keySet().toArray();
        int idx[] = new int[keySet.length];
            
        while(true){
            RoomSensorDtoExample example = example();
            example.setOrderByClause("cdate desc limit " + limit);
            RoomSensorDtoExample.Criteria criteria = example.or();
      
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
        
            List<RoomSensorDto> selected = dao.selectByExample(example);
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
        for(RoomSensorEntity entity : getAll()) {
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


	protected MapSet<RoomSensorEntity> roomMapSet = new MapSet<>();
	protected MapSet<RoomSensorEntity> sensorMapSet = new MapSet<>();
	protected MapSet<RoomSensorEntity> locationMapSet = new MapSet<>();

	public List<RoomSensorEntity> getByRoomId(String roomId) {
		return roomMapSet.get(RoomEntity.newMapKey(roomId));
	}
	public List<RoomSensorEntity> getBySensorId(String sensorId) {
		return sensorMapSet.get(SensorEntity.newMapKey(sensorId));
	}
	public List<RoomSensorEntity> getByLocationId(String locationId) {
		return locationMapSet.get(LocationEntity.newMapKey(locationId));
	}

    @Override
    public RoomSensorEntity get(RoomSensorDto dto) {
        return map.get(RoomSensorEntity.newMapKey(dto));
    }
       
    protected boolean checkCreated(RoomSensorDto dto){
        if(map.containsKey(RoomSensorEntity.newMapKey(dto))) return true;
        return false;
    }
    
    protected RoomSensorEntity load(RoomSensorDto dto) {
        RoomSensorEntity entity = newEntity(dto);
        map.put(entity.mapKey(), entity);    

		roomMapSet.put(RoomEntity.newMapKey(entity.getRoomId()), entity);
		sensorMapSet.put(SensorEntity.newMapKey(entity.getSensorId()), entity);
		locationMapSet.put(LocationEntity.newMapKey(entity.getLocationId()), entity);
        
        return entity;
    }
    
    protected void loaded(RoomSensorEntity entity){
        loadPublisher.publish(new LoadEvent<RoomSensorEntity>(cloneOf(entity)));
    }
        
    @Override
    protected void updated(List<RoomSensorEntity> entities) {
        for(RoomSensorEntity entity : entities) updatePublisher.publish(new UpdateEvent<RoomSensorEntity>(cloneOf(entity)));
    }
  
    @Override
    protected void daoDeleted(List<RoomSensorEntity> entities) {
        super.daoDeleted(entities);
        for(RoomSensorEntity entity : entities) deletePublisher.publish(new DeleteEvent<RoomSensorEntity>(cloneOf(entity)));
		for(RoomSensorEntity each : entities) roomMapSet.remove(RoomEntity.newMapKey(each.getRoomId()), each);		for(RoomSensorEntity each : entities) sensorMapSet.remove(SensorEntity.newMapKey(each.getSensorId()), each);		for(RoomSensorEntity each : entities) locationMapSet.remove(LocationEntity.newMapKey(each.getLocationId()), each);
    }
    
    @Override
    protected List<RoomSensorDto> dtoListToStart() {
        return dao.selectAll();
    }
}
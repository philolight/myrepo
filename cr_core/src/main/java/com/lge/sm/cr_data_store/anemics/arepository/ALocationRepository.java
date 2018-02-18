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
import com.lge.sm.cr_data_store.repository.LocationRepository;
import com.lge.sm.cr_data_store.dao.LocationDao;
import com.lge.sm.cr_data_store.entity.RoomSensorEntity;
import com.lge.sm.cr_data_store.repository.RoomSensorRepository;
import com.lge.sm.cr_data_store.entity.RoomEntity;
import com.lge.sm.cr_data_store.repository.RoomRepository;
import com.lge.sm.cr_data_store.entity.AuthorityLocationEntity;
import com.lge.sm.cr_data_store.repository.AuthorityLocationRepository;

import com.lge.sm.cr_data_store.entity.LocationEntity;
import com.lge.sm.cr_data_store.dto.LocationDto;
import com.lge.sm.cr_data_store.dto.LocationDtoExample;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.lge.sm.cr_data_store.repository.SkinRepository;
import com.lge.sm.cr_data_store.entity.SkinEntity;

abstract public class ALocationRepository extends CacheableRepository<LocationEntity, LocationDao, LocationDto, LocationDtoExample>{
    @SuppressWarnings("unchecked")  
    private KindredEventPublisher<LoadEvent<LocationEntity>> loadPublisher = (KindredEventPublisher<LoadEvent<LocationEntity>>)EventBroker.getPublisher(LocationEntity.class, LoadEvent.class);
    @SuppressWarnings("unchecked")  
    private KindredEventPublisher<CreateEvent<LocationEntity>> createPublisher = (KindredEventPublisher<CreateEvent<LocationEntity>>)EventBroker.getPublisher(LocationEntity.class, CreateEvent.class);
    @SuppressWarnings("unchecked")
    private KindredEventPublisher<UpdateEvent<LocationEntity>> updatePublisher = (KindredEventPublisher<UpdateEvent<LocationEntity>>)EventBroker.getPublisher(LocationEntity.class, UpdateEvent.class);
    @SuppressWarnings("unchecked")
    private KindredEventPublisher<DeleteEvent<LocationEntity>> deletePublisher = (KindredEventPublisher<DeleteEvent<LocationEntity>>)EventBroker.getPublisher(LocationEntity.class, DeleteEvent.class);

    @Autowired
    public ALocationRepository(LocationDao dao) {
        super(dao);
    }
    


    @Override
    public LocationEntity create(LocationDto dto) throws IllegalArgumentException {
    	dto.setCdate(DateStringUtil.getCurrentDateString(DateStringUtil.gmtTimeZoneId));

        if(checkCreated(dto) == true) throw new IllegalArgumentException("Already created : " + ToString.toLine(dto));    
        if(checkForeignKeyEntityExist(dto) == false) throw new IllegalArgumentException("No record of foreign key when create : " + ToString.toLine(dto));
        if(dao.insert(dto) == false) throw new IllegalArgumentException();
            
        LocationEntity ret = load(dto);
        createPublisher.publish(new CreateEvent<LocationEntity>(cloneOf(ret)));
        
        return ret;
    }
  
    protected boolean checkForeignKeyEntityExist(LocationDto dto) {

        return true;
    }
        
    public LocationEntity cloneOf(LocationEntity entity) {
        LocationEntity ret = newEntity(entity.getDto());

        return ret;
    }
  
    protected LocationEntity newEntity(LocationDto dto){
        return new LocationEntity(dto);
    }
    
    @Transactional
    @Override
    protected boolean deleteDao(List<LocationEntity> entities) {
        super.deleteDao(entities);
		List<RoomSensorEntity> roomSensorList = new ArrayList<>();
		for(LocationEntity each : entities) roomSensorList.addAll(Repos.repo(RoomSensorRepository.class).getByLocationId(each.getLocationId()));
		if(roomSensorList.size() != 0) {
			if(Repos.repo(RoomSensorRepository.class).delete(roomSensorList) == false) return false;
		}
		List<RoomEntity> roomList = new ArrayList<>();
		for(LocationEntity each : entities) roomList.addAll(Repos.repo(RoomRepository.class).getByLocationId(each.getLocationId()));
		if(roomList.size() != 0) {
			if(Repos.repo(RoomRepository.class).delete(roomList) == false) return false;
		}
		List<AuthorityLocationEntity> authorityLocationList = new ArrayList<>();
		for(LocationEntity each : entities) authorityLocationList.addAll(Repos.repo(AuthorityLocationRepository.class).getByLocationId(each.getLocationId()));
		if(authorityLocationList.size() != 0) {
			if(Repos.repo(AuthorityLocationRepository.class).delete(authorityLocationList) == false) return false;
		}
 
        return dao.delete(Repos.repo(LocationRepository.class).getDtoList(entities));
    }
   
    @Override
    protected boolean initDescendent() {



		kidSkinTypes.add(RoomSensorEntity.skinType());
		kidSkinTypes.add(RoomEntity.skinType());
		kidSkinTypes.add(AuthorityLocationEntity.skinType());
        return true;
    }
    
    public LocationDtoExample example(){
        return new LocationDtoExample();
    }
    
    private LocationDtoExample criteriaFactory = new LocationDtoExample();
    public LocationDtoExample.Criteria criteria(){
        return criteriaFactory.createCriteria();
    }
    
    public List<LocationEntity> getByCriteria(LocationDtoExample.Criteria c){
        LocationDtoExample newExample = new LocationDtoExample();
        newExample.or(c);
        List<LocationDto> dtos = dao.selectByExample(newExample);
        List<LocationEntity> entities = new ArrayList<>();
        if(dtos == null) return entities;
        for(LocationDto each : dtos) entities.add(load(each));
      
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
            LocationDto dto = om.treeToValue(inputNode, LocationDto.class);
            LocationEntity entity = get(dto);
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
  
    public String skinized(LocationEntity entity) {
        return skinize(entity);
    }
    
    protected String skinize(LocationEntity entity) {
        return skinize(entity.getDto());
    }
  
    protected String skinize(LocationDto dto) {
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
        return LocationEntity.skinType();
    }

    @Override
    public Skin skin() {
        SkinEntity entity = Repos.repo(SkinRepository.class).getByMapKey(SkinEntity.newMapKey(skinType()));
        return entity.skin(getParentSkinTypes(), getKidSkinTypes());
    }
    
    protected LocationDto jsonNodeToDto(JsonNode node) {
        ObjectMapper om = JsonUtil.objectMapper();
        try {
            LocationDto dto = om.treeToValue(node, LocationDto.class);
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
    	List<LocationDto> dtoList = new ArrayList<>();
		for(JsonNode each : nodeList) {
	        LocationDto dto = jsonNodeToDto(each);
	        if(dto == null) return "";
	        dtoList.add(dto);
		}
		
    	List<LocationEntity> entityList = new ArrayList<>();
		for(LocationDto dto : dtoList) {
	        LocationEntity entity = create(dto);
	        if(entity == null) Logger.error(TAG, "Failed to create : " + ToString.toLine(dto));
	        else entityList.add(entity);
		}
		
        StringBuffer ret = new StringBuffer();
        ret.append("[");
        for(int i = 0; i < entityList.size(); i++) {
        	LocationEntity entity = entityList.get(i);
            ret.append(skinize(entity));
            if(i != entityList.size() - 1) ret.append(",");
        }
        ret.append("]");
        
        return ret.toString();
    }
    
    public String update(JsonNode nodeList) {
    	List<LocationDto> dtoList = new ArrayList<>();
		for(JsonNode each : nodeList) {
	        LocationDto dto = jsonNodeToDto(each);
	        System.out.println(ToString.toLine(dto));
	        if(dto == null) return "";
	        dtoList.add(dto);
		}
		
    	List<LocationEntity> entityList = new ArrayList<>();
		for(LocationDto dto : dtoList) {
	        LocationEntity entity = newEntity(dto);
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
        	LocationEntity entity = entityList.get(i);
            ret.append(skinize(entity));
            if(i != entityList.size() - 1) ret.append(",");
        }
        ret.append("]");
        
        return ret.toString();
    }
    
    public String delete(JsonNode nodeList) {
    	List<LocationDto> dtoList = new ArrayList<>();
		for(JsonNode each : nodeList) {
	        LocationDto dto = jsonNodeToDto(each);
	        System.out.println(ToString.toLine(dto));
	        if(dto == null) return "";
	        dtoList.add(dto);
		}
		
    	List<LocationEntity> entityList = new ArrayList<>();
		for(LocationDto dto : dtoList) {
	        LocationEntity entity = get(dto);
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
        	LocationEntity entity = entityList.get(i);
            ret.append(skinize(entity));
            if(i != entityList.size() - 1) ret.append(",");
        }
        ret.append("]");
        
        return ret.toString();
    }
    
    public String getSkinizedKids(JsonNode node, String kidSkinType) {
        LocationDto dto = jsonNodeToDto(node);
        if(dto == null) return "";
        LocationEntity entity = get(dto);
        if(entity == null) return "";

		if(kidSkinType.equals("RoomSensor")) {
			List<RoomSensorEntity> list = entity.getRoomSensorEntityList();
			RoomSensorRepository repo = Repos.repo(RoomSensorRepository.class);
			StringBuffer ret = new StringBuffer();
			ret.append("[");
			for(int i = 0; i < list.size(); i++) {
				RoomSensorEntity each = list.get(i);
				ret.append(repo.skinized(each));
				if(i != list.size()-1) ret.append(",");
			}
			ret.append("]");
			return ret.toString();
		}
		if(kidSkinType.equals("Room")) {
			List<RoomEntity> list = entity.getRoomEntityList();
			RoomRepository repo = Repos.repo(RoomRepository.class);
			StringBuffer ret = new StringBuffer();
			ret.append("[");
			for(int i = 0; i < list.size(); i++) {
				RoomEntity each = list.get(i);
				ret.append(repo.skinized(each));
				if(i != list.size()-1) ret.append(",");
			}
			ret.append("]");
			return ret.toString();
		}
		if(kidSkinType.equals("AuthorityLocation")) {
			List<AuthorityLocationEntity> list = entity.getAuthorityLocationEntityList();
			AuthorityLocationRepository repo = Repos.repo(AuthorityLocationRepository.class);
			StringBuffer ret = new StringBuffer();
			ret.append("[");
			for(int i = 0; i < list.size(); i++) {
				AuthorityLocationEntity each = list.get(i);
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
        List<LocationDto> dtos = new ArrayList<>();
        Object[] keySet = maps.keySet().toArray();
        int idx[] = new int[keySet.length];
            
        while(true){
            LocationDtoExample example = example();
            example.setOrderByClause("cdate desc limit " + limit);
            LocationDtoExample.Criteria criteria = example.or();
      
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
        
            List<LocationDto> selected = dao.selectByExample(example);
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
        for(LocationEntity entity : getAll()) {
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




    @Override
    public LocationEntity get(LocationDto dto) {
        return map.get(LocationEntity.newMapKey(dto));
    }
       
    protected boolean checkCreated(LocationDto dto){
        if(map.containsKey(LocationEntity.newMapKey(dto))) return true;
        return false;
    }
    
    protected LocationEntity load(LocationDto dto) {
        LocationEntity entity = newEntity(dto);
        map.put(entity.mapKey(), entity);    

        
        return entity;
    }
    
    protected void loaded(LocationEntity entity){
        loadPublisher.publish(new LoadEvent<LocationEntity>(cloneOf(entity)));
    }
        
    @Override
    protected void updated(List<LocationEntity> entities) {
        for(LocationEntity entity : entities) updatePublisher.publish(new UpdateEvent<LocationEntity>(cloneOf(entity)));
    }
  
    @Override
    protected void daoDeleted(List<LocationEntity> entities) {
        super.daoDeleted(entities);
        for(LocationEntity entity : entities) deletePublisher.publish(new DeleteEvent<LocationEntity>(cloneOf(entity)));

    }
    
    @Override
    protected List<LocationDto> dtoListToStart() {
        return dao.selectAll();
    }
}
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
import com.lge.framework.ceasar.service.view.Skin;

import com.lge.framework.pacific.logger.Logger;
import com.lge.sm.cr_data_store.repository.RoomRepository;
import com.lge.sm.cr_data_store.dao.RoomDao;
import com.lge.sm.cr_data_store.entity.LocationEntity;
import com.lge.sm.cr_data_store.repository.LocationRepository;
import com.lge.sm.cr_data_store.entity.RoomSensorEntity;
import com.lge.sm.cr_data_store.repository.RoomSensorRepository;

import com.lge.sm.cr_data_store.entity.RoomEntity;
import com.lge.sm.cr_data_store.dto.RoomDto;
import com.lge.sm.cr_data_store.dto.RoomDtoExample;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.lge.sm.cr_data_store.repository.SkinRepository;
import com.lge.sm.cr_data_store.entity.SkinEntity;

abstract public class ARoomRepository extends CacheableRepository<RoomEntity, RoomDao, RoomDto, RoomDtoExample>{
    @SuppressWarnings("unchecked")  
    private KindredEventPublisher<LoadEvent<RoomEntity>> loadPublisher = (KindredEventPublisher<LoadEvent<RoomEntity>>)EventBroker.getPublisher(RoomEntity.class, LoadEvent.class);
    @SuppressWarnings("unchecked")  
    private KindredEventPublisher<CreateEvent<RoomEntity>> createPublisher = (KindredEventPublisher<CreateEvent<RoomEntity>>)EventBroker.getPublisher(RoomEntity.class, CreateEvent.class);
    @SuppressWarnings("unchecked")
    private KindredEventPublisher<UpdateEvent<RoomEntity>> updatePublisher = (KindredEventPublisher<UpdateEvent<RoomEntity>>)EventBroker.getPublisher(RoomEntity.class, UpdateEvent.class);
    @SuppressWarnings("unchecked")
    private KindredEventPublisher<DeleteEvent<RoomEntity>> deletePublisher = (KindredEventPublisher<DeleteEvent<RoomEntity>>)EventBroker.getPublisher(RoomEntity.class, DeleteEvent.class);

    @Autowired
    public ARoomRepository(RoomDao dao) {
        super(dao);
    }
    


    @Override
    public RoomEntity create(RoomDto dto) throws IllegalArgumentException {
        if(checkCreated(dto) == true) throw new IllegalArgumentException("Already created : " + ToString.toLine(dto));    
        if(checkForeignKeyEntityExist(dto) == false) throw new IllegalArgumentException("No record of foreign key when create : " + ToString.toLine(dto));
        if(dao.insert(dto) == false) throw new IllegalArgumentException();
            
        RoomEntity ret = load(dto);
        createPublisher.publish(new CreateEvent<RoomEntity>(cloneOf(ret)));
        
        return ret;
    }
  
    protected boolean checkForeignKeyEntityExist(RoomDto dto) {
		if(Repos.repo(LocationRepository.class).getByMapKey(LocationEntity.newMapKey(dto.getLocationId())) == null) return false;

        return true;
    }
        
    public RoomEntity cloneOf(RoomEntity entity) {
        RoomEntity ret = newEntity(entity.getDto());

        return ret;
    }
  
    protected RoomEntity newEntity(RoomDto dto){
        return new RoomEntity(dto);
    }
    
    @Transactional
    @Override
    protected boolean deleteDao(List<RoomEntity> entities) {
        super.deleteDao(entities);
		List<RoomSensorEntity> roomSensorList = new ArrayList<>();
		for(RoomEntity each : entities) roomSensorList.addAll(Repos.repo(RoomSensorRepository.class).getByRoomId(each.getRoomId()));
		if(Repos.repo(RoomSensorRepository.class).delete(roomSensorList) == false) return false;
 
        return dao.delete(Repos.repo(RoomRepository.class).getDtoList(entities));
    }
   
    @Override
    protected boolean initDescendent() {


		parentSkinTypes.add(LocationEntity.skinType());

		kidSkinTypes.add(RoomSensorEntity.skinType());
        return true;
    }
    
    public RoomDtoExample example(){
        return new RoomDtoExample();
    }
    
    private RoomDtoExample criteriaFactory = new RoomDtoExample();
    public RoomDtoExample.Criteria criteria(){
        return criteriaFactory.createCriteria();
    }
    
    public List<RoomEntity> getByCriteria(RoomDtoExample.Criteria c){
        RoomDtoExample newExample = new RoomDtoExample();
        newExample.or(c);
        List<RoomDto> dtos = dao.selectByExample(newExample);
        List<RoomEntity> entities = new ArrayList<>();
        if(dtos == null) return entities;
        for(RoomDto each : dtos) entities.add(load(each));
      
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
            RoomDto dto = om.treeToValue(inputNode, RoomDto.class);
            RoomEntity entity = get(dto);
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
  
    public String skinized(RoomEntity entity) {
        return skinize(entity);
    }
    
    protected String skinize(RoomEntity entity) {
        return skinize(entity.getDto());
    }
  
    protected String skinize(RoomDto dto) {
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
        return RoomEntity.skinType();
    }

    @Override
    public Skin skin() {
        SkinEntity entity = Repos.repo(SkinRepository.class).getByMapKey(SkinEntity.newMapKey(skinType()));
        return entity.skin(getParentSkinTypes(), getKidSkinTypes());
    }
    
    protected RoomDto jsonNodeToDto(JsonNode node) {
        ObjectMapper om = JsonUtil.objectMapper();
        try {
            RoomDto dto = om.treeToValue(node, RoomDto.class);
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
    
    public String create(JsonNode inputNode) {
        RoomDto dto = jsonNodeToDto(inputNode);
        if(dto == null) return "";
        RoomEntity entity = create(dto);
        if(entity != null) return skinized(entity);
        return "";
    }
    
    public String update(JsonNode inputNode) {
        RoomDto dto = jsonNodeToDto(inputNode);
        if(dto == null) return "";
        RoomEntity entity = get(dto);
        if(entity != null){
          boolean ret = update(newEntity(dto));
          if(ret) return skinized(get(dto));
        }
        return "";
    }
    
    public boolean delete(JsonNode inputNode) {
        RoomDto dto = jsonNodeToDto(inputNode);
        if(dto == null) return false;
        RoomEntity entity = get(dto);
        return delete(entity);
    }
    
    public String getSkinizedKids(JsonNode node, String kidSkinType) {
        RoomDto dto = jsonNodeToDto(node);
        if(dto == null) return "";
        RoomEntity entity = get(dto);

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
        
        return "";
    }
    
    @Override
    public String read(Map<String, String> from, Map<String, String> to, Map<String, List<String>> maps, int limit) {
        List<RoomDto> dtos = new ArrayList<>();
        Object[] keySet = maps.keySet().toArray();
        int idx[] = new int[keySet.length];
            
        while(true){
            RoomDtoExample example = example();
            example.setOrderByClause("cdate desc limit " + limit);
            RoomDtoExample.Criteria criteria = example.or();
      
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
        
            List<RoomDto> selected = dao.selectByExample(example);
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
        for(RoomEntity entity : getAll()) {
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


	protected MapSet<RoomEntity> locationMapSet = new MapSet<>();

	public List<RoomEntity> getByLocationId(String locationId) {
		return locationMapSet.get(LocationEntity.newMapKey(locationId));
	}

    @Override
    public RoomEntity get(RoomDto dto) {
        return map.get(RoomEntity.newMapKey(dto));
    }
       
    protected boolean checkCreated(RoomDto dto){
        if(map.containsKey(RoomEntity.newMapKey(dto))) return true;
        return false;
    }
    
    protected RoomEntity load(RoomDto dto) {
        RoomEntity entity = newEntity(dto);
        map.put(entity.mapKey(), entity);    

		locationMapSet.put(LocationEntity.newMapKey(entity.getLocationId()), entity);
        
        return entity;
    }
    
    protected void loaded(RoomEntity entity){
        loadPublisher.publish(new LoadEvent<RoomEntity>(cloneOf(entity)));
    }
        
    @Override
    protected void updated(List<RoomEntity> entities) {
        for(RoomEntity entity : entities) updatePublisher.publish(new UpdateEvent<RoomEntity>(cloneOf(entity)));
    }
  
    @Override
    protected void daoDeleted(List<RoomEntity> entities) {
        super.daoDeleted(entities);
        for(RoomEntity entity : entities) deletePublisher.publish(new DeleteEvent<RoomEntity>(cloneOf(entity)));
		List<RoomSensorEntity> roomSensorList = new ArrayList<>();
		for(RoomEntity each : entities) roomSensorList.addAll(Repos.repo(RoomSensorRepository.class).getByRoomId(each.getRoomId()));
		Repos.repo(RoomSensorRepository.class).daoDeleted(roomSensorList);

    }
    
    @Override
    protected List<RoomDto> dtoListToStart() {
        return dao.selectAll();
    }
}
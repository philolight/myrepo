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
import com.lge.sm.cr_data_store.repository.SensorRepository;
import com.lge.sm.cr_data_store.dao.SensorDao;
import com.lge.sm.cr_data_store.entity.SlmEntity;
import com.lge.sm.cr_data_store.repository.SlmRepository;
import com.lge.sm.cr_data_store.entity.RoomSensorEntity;
import com.lge.sm.cr_data_store.repository.RoomSensorRepository;

import com.lge.sm.cr_data_store.entity.SensorEntity;
import com.lge.sm.cr_data_store.dto.SensorDto;
import com.lge.sm.cr_data_store.dto.SensorDtoExample;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.lge.sm.cr_data_store.repository.SkinRepository;
import com.lge.sm.cr_data_store.entity.SkinEntity;

abstract public class ASensorRepository extends CacheableRepository<SensorEntity, SensorDao, SensorDto, SensorDtoExample>{
    @SuppressWarnings("unchecked")  
    private KindredEventPublisher<LoadEvent<SensorEntity>> loadPublisher = (KindredEventPublisher<LoadEvent<SensorEntity>>)EventBroker.getPublisher(SensorEntity.class, LoadEvent.class);
    @SuppressWarnings("unchecked")  
    private KindredEventPublisher<CreateEvent<SensorEntity>> createPublisher = (KindredEventPublisher<CreateEvent<SensorEntity>>)EventBroker.getPublisher(SensorEntity.class, CreateEvent.class);
    @SuppressWarnings("unchecked")
    private KindredEventPublisher<UpdateEvent<SensorEntity>> updatePublisher = (KindredEventPublisher<UpdateEvent<SensorEntity>>)EventBroker.getPublisher(SensorEntity.class, UpdateEvent.class);
    @SuppressWarnings("unchecked")
    private KindredEventPublisher<DeleteEvent<SensorEntity>> deletePublisher = (KindredEventPublisher<DeleteEvent<SensorEntity>>)EventBroker.getPublisher(SensorEntity.class, DeleteEvent.class);

    @Autowired
    public ASensorRepository(SensorDao dao) {
        super(dao);
    }
    


    @Override
    public SensorEntity create(SensorDto dto) throws IllegalArgumentException {
        if(checkCreated(dto) == true) throw new IllegalArgumentException("Already created : " + ToString.toLine(dto));    
        if(checkForeignKeyEntityExist(dto) == false) throw new IllegalArgumentException("No record of foreign key when create : " + ToString.toLine(dto));
        if(dao.insert(dto) == false) throw new IllegalArgumentException();
            
        SensorEntity ret = load(dto);
        createPublisher.publish(new CreateEvent<SensorEntity>(cloneOf(ret)));
        
        return ret;
    }
  
    protected boolean checkForeignKeyEntityExist(SensorDto dto) {
		if(Repos.repo(SlmRepository.class).getByMapKey(SlmEntity.newMapKey(dto.getSlmId())) == null) return false;

        return true;
    }
        
    public SensorEntity cloneOf(SensorEntity entity) {
        SensorEntity ret = newEntity(entity.getDto());

        return ret;
    }
  
    protected SensorEntity newEntity(SensorDto dto){
        return new SensorEntity(dto);
    }
    
    @Transactional
    @Override
    protected boolean deleteDao(List<SensorEntity> entities) {
        super.deleteDao(entities);
		List<RoomSensorEntity> roomSensorList = new ArrayList<>();
		for(SensorEntity each : entities) roomSensorList.addAll(Repos.repo(RoomSensorRepository.class).getBySensorId(each.getSensorId()));
		if(Repos.repo(RoomSensorRepository.class).delete(roomSensorList) == false) return false;
 
        return dao.delete(Repos.repo(SensorRepository.class).getDtoList(entities));
    }
   
    @Override
    protected boolean initDescendent() {


		parentSkinTypes.add(SlmEntity.skinType());

		kidSkinTypes.add(RoomSensorEntity.skinType());
        return true;
    }
    
    public SensorDtoExample example(){
        return new SensorDtoExample();
    }
    
    private SensorDtoExample criteriaFactory = new SensorDtoExample();
    public SensorDtoExample.Criteria criteria(){
        return criteriaFactory.createCriteria();
    }
    
    public List<SensorEntity> getByCriteria(SensorDtoExample.Criteria c){
        SensorDtoExample newExample = new SensorDtoExample();
        newExample.or(c);
        List<SensorDto> dtos = dao.selectByExample(newExample);
        List<SensorEntity> entities = new ArrayList<>();
        if(dtos == null) return entities;
        for(SensorDto each : dtos) entities.add(load(each));
      
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
            SensorDto dto = om.treeToValue(inputNode, SensorDto.class);
            SensorEntity entity = get(dto);
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
  
    public String skinized(SensorEntity entity) {
        return skinize(entity);
    }
    
    protected String skinize(SensorEntity entity) {
        return skinize(entity.getDto());
    }
  
    protected String skinize(SensorDto dto) {
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
        return SensorEntity.skinType();
    }

    @Override
    public Skin skin() {
        SkinEntity entity = Repos.repo(SkinRepository.class).getByMapKey(SkinEntity.newMapKey(skinType()));
        return entity.skin(getParentSkinTypes(), getKidSkinTypes());
    }
    
    protected SensorDto jsonNodeToDto(JsonNode node) {
        ObjectMapper om = JsonUtil.objectMapper();
        try {
            SensorDto dto = om.treeToValue(node, SensorDto.class);
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
        SensorDto dto = jsonNodeToDto(inputNode);
        if(dto == null) return "";
        SensorEntity entity = create(dto);
        if(entity != null) return skinized(entity);
        return "";
    }
    
    public String update(JsonNode inputNode) {
        SensorDto dto = jsonNodeToDto(inputNode);
        if(dto == null) return "";
        SensorEntity entity = get(dto);
        if(entity != null){
          boolean ret = update(newEntity(dto));
          if(ret) return skinized(get(dto));
        }
        return "";
    }
    
    public boolean delete(JsonNode inputNode) {
        SensorDto dto = jsonNodeToDto(inputNode);
        if(dto == null) return false;
        SensorEntity entity = get(dto);
        return delete(entity);
    }
    
    public String getSkinizedKids(JsonNode node, String kidSkinType) {
        SensorDto dto = jsonNodeToDto(node);
        if(dto == null) return "";
        SensorEntity entity = get(dto);

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
        List<SensorDto> dtos = new ArrayList<>();
        Object[] keySet = maps.keySet().toArray();
        int idx[] = new int[keySet.length];
            
        while(true){
            SensorDtoExample example = example();
            example.setOrderByClause("cdate desc limit " + limit);
            SensorDtoExample.Criteria criteria = example.or();
      
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
        
            List<SensorDto> selected = dao.selectByExample(example);
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
        for(SensorEntity entity : getAll()) {
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


	protected MapSet<SensorEntity> slmMapSet = new MapSet<>();

	public List<SensorEntity> getBySlmId(String slmId) {
		return slmMapSet.get(SlmEntity.newMapKey(slmId));
	}

    @Override
    public SensorEntity get(SensorDto dto) {
        return map.get(SensorEntity.newMapKey(dto));
    }
       
    protected boolean checkCreated(SensorDto dto){
        if(map.containsKey(SensorEntity.newMapKey(dto))) return true;
        return false;
    }
    
    protected SensorEntity load(SensorDto dto) {
        SensorEntity entity = newEntity(dto);
        map.put(entity.mapKey(), entity);    

		slmMapSet.put(SlmEntity.newMapKey(entity.getSlmId()), entity);
        
        return entity;
    }
    
    protected void loaded(SensorEntity entity){
        loadPublisher.publish(new LoadEvent<SensorEntity>(cloneOf(entity)));
    }
        
    @Override
    protected void updated(List<SensorEntity> entities) {
        for(SensorEntity entity : entities) updatePublisher.publish(new UpdateEvent<SensorEntity>(cloneOf(entity)));
    }
  
    @Override
    protected void daoDeleted(List<SensorEntity> entities) {
        super.daoDeleted(entities);
        for(SensorEntity entity : entities) deletePublisher.publish(new DeleteEvent<SensorEntity>(cloneOf(entity)));
		List<RoomSensorEntity> roomSensorList = new ArrayList<>();
		for(SensorEntity each : entities) roomSensorList.addAll(Repos.repo(RoomSensorRepository.class).getBySensorId(each.getSensorId()));
		Repos.repo(RoomSensorRepository.class).daoDeleted(roomSensorList);

    }
    
    @Override
    protected List<SensorDto> dtoListToStart() {
        return dao.selectAll();
    }
}
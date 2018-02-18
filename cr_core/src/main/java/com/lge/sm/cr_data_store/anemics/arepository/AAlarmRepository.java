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
import com.lge.sm.cr_data_store.repository.AlarmRepository;
import com.lge.sm.cr_data_store.dao.AlarmDao;

import com.lge.sm.cr_data_store.entity.AlarmEntity;
import com.lge.sm.cr_data_store.dto.AlarmDto;
import com.lge.sm.cr_data_store.dto.AlarmDtoExample;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.lge.sm.cr_data_store.repository.SkinRepository;
import com.lge.sm.cr_data_store.entity.SkinEntity;

abstract public class AAlarmRepository extends CacheableRepository<AlarmEntity, AlarmDao, AlarmDto, AlarmDtoExample>{
    @SuppressWarnings("unchecked")  
    private KindredEventPublisher<LoadEvent<AlarmEntity>> loadPublisher = (KindredEventPublisher<LoadEvent<AlarmEntity>>)EventBroker.getPublisher(AlarmEntity.class, LoadEvent.class);
    @SuppressWarnings("unchecked")  
    private KindredEventPublisher<CreateEvent<AlarmEntity>> createPublisher = (KindredEventPublisher<CreateEvent<AlarmEntity>>)EventBroker.getPublisher(AlarmEntity.class, CreateEvent.class);
    @SuppressWarnings("unchecked")
    private KindredEventPublisher<UpdateEvent<AlarmEntity>> updatePublisher = (KindredEventPublisher<UpdateEvent<AlarmEntity>>)EventBroker.getPublisher(AlarmEntity.class, UpdateEvent.class);
    @SuppressWarnings("unchecked")
    private KindredEventPublisher<DeleteEvent<AlarmEntity>> deletePublisher = (KindredEventPublisher<DeleteEvent<AlarmEntity>>)EventBroker.getPublisher(AlarmEntity.class, DeleteEvent.class);

    @Autowired
    public AAlarmRepository(AlarmDao dao) {
        super(dao);
    }
    

	protected volatile long lastId = -1;
	public long getNextId() {
		return ++lastId;
	}

    @Override
    public AlarmEntity create(AlarmDto dto) throws IllegalArgumentException {
    	dto.setCdate(DateStringUtil.getCurrentDateString(DateStringUtil.gmtTimeZoneId));
		dto.setAlarmId(getNextId());
        if(checkCreated(dto) == true) throw new IllegalArgumentException("Already created : " + ToString.toLine(dto));    
        if(checkForeignKeyEntityExist(dto) == false) throw new IllegalArgumentException("No record of foreign key when create : " + ToString.toLine(dto));
        if(dao.insert(dto) == false) throw new IllegalArgumentException();
            
        AlarmEntity ret = load(dto);
        createPublisher.publish(new CreateEvent<AlarmEntity>(cloneOf(ret)));
        
        return ret;
    }
  
    protected boolean checkForeignKeyEntityExist(AlarmDto dto) {

        return true;
    }
        
    public AlarmEntity cloneOf(AlarmEntity entity) {
        AlarmEntity ret = newEntity(entity.getDto());

        return ret;
    }
  
    protected AlarmEntity newEntity(AlarmDto dto){
        return new AlarmEntity(dto);
    }
    
    @Transactional
    @Override
    protected boolean deleteDao(List<AlarmEntity> entities) {
        super.deleteDao(entities);
 
        return dao.delete(Repos.repo(AlarmRepository.class).getDtoList(entities));
    }
   
    @Override
    protected boolean initDescendent() {

		lastId = dao.getLastId();


        return true;
    }
    
    public AlarmDtoExample example(){
        return new AlarmDtoExample();
    }
    
    private AlarmDtoExample criteriaFactory = new AlarmDtoExample();
    public AlarmDtoExample.Criteria criteria(){
        return criteriaFactory.createCriteria();
    }
    
    public List<AlarmEntity> getByCriteria(AlarmDtoExample.Criteria c){
        AlarmDtoExample newExample = new AlarmDtoExample();
        newExample.or(c);
        List<AlarmDto> dtos = dao.selectByExample(newExample);
        List<AlarmEntity> entities = new ArrayList<>();
        if(dtos == null) return entities;
        for(AlarmDto each : dtos) entities.add(load(each));
      
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
            AlarmDto dto = om.treeToValue(inputNode, AlarmDto.class);
            AlarmEntity entity = get(dto);
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
  
    public String skinized(AlarmEntity entity) {
        return skinize(entity);
    }
    
    protected String skinize(AlarmEntity entity) {
        return skinize(entity.getDto());
    }
  
    protected String skinize(AlarmDto dto) {
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
        return AlarmEntity.skinType();
    }

    @Override
    public Skin skin() {
        SkinEntity entity = Repos.repo(SkinRepository.class).getByMapKey(SkinEntity.newMapKey(skinType()));
        return entity.skin(getParentSkinTypes(), getKidSkinTypes());
    }
    
    protected AlarmDto jsonNodeToDto(JsonNode node) {
        ObjectMapper om = JsonUtil.objectMapper();
        try {
            AlarmDto dto = om.treeToValue(node, AlarmDto.class);
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
    	List<AlarmDto> dtoList = new ArrayList<>();
		for(JsonNode each : nodeList) {
	        AlarmDto dto = jsonNodeToDto(each);
	        if(dto == null) return "";
	        dtoList.add(dto);
		}
		
    	List<AlarmEntity> entityList = new ArrayList<>();
		for(AlarmDto dto : dtoList) {
	        AlarmEntity entity = create(dto);
	        if(entity == null) Logger.error(TAG, "Failed to create : " + ToString.toLine(dto));
	        else entityList.add(entity);
		}
		
        StringBuffer ret = new StringBuffer();
        ret.append("[");
        for(int i = 0; i < entityList.size(); i++) {
        	AlarmEntity entity = entityList.get(i);
            ret.append(skinize(entity));
            if(i != entityList.size() - 1) ret.append(",");
        }
        ret.append("]");
        
        return ret.toString();
    }
    
    public String update(JsonNode nodeList) {
    	List<AlarmDto> dtoList = new ArrayList<>();
		for(JsonNode each : nodeList) {
	        AlarmDto dto = jsonNodeToDto(each);
	        System.out.println(ToString.toLine(dto));
	        if(dto == null) return "";
	        dtoList.add(dto);
		}
		
    	List<AlarmEntity> entityList = new ArrayList<>();
		for(AlarmDto dto : dtoList) {
	        AlarmEntity entity = newEntity(dto);
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
        	AlarmEntity entity = entityList.get(i);
            ret.append(skinize(entity));
            if(i != entityList.size() - 1) ret.append(",");
        }
        ret.append("]");
        
        return ret.toString();
    }
    
    public String delete(JsonNode nodeList) {
    	List<AlarmDto> dtoList = new ArrayList<>();
		for(JsonNode each : nodeList) {
	        AlarmDto dto = jsonNodeToDto(each);
	        System.out.println(ToString.toLine(dto));
	        if(dto == null) return "";
	        dtoList.add(dto);
		}
		
    	List<AlarmEntity> entityList = new ArrayList<>();
		for(AlarmDto dto : dtoList) {
	        AlarmEntity entity = get(dto);
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
        	AlarmEntity entity = entityList.get(i);
            ret.append(skinize(entity));
            if(i != entityList.size() - 1) ret.append(",");
        }
        ret.append("]");
        
        return ret.toString();
    }
    
    public String getSkinizedKids(JsonNode node, String kidSkinType) {
        AlarmDto dto = jsonNodeToDto(node);
        if(dto == null) return "";
        AlarmEntity entity = get(dto);
        if(entity == null) return "";

        
        return "";
    }
    
    @Override
    public String read(Map<String, String> from, Map<String, String> to, Map<String, List<String>> maps, int limit) {
        List<AlarmDto> dtos = new ArrayList<>();
        Object[] keySet = maps.keySet().toArray();
        int idx[] = new int[keySet.length];
            
        while(true){
            AlarmDtoExample example = example();
            example.setOrderByClause("cdate desc limit " + limit);
            AlarmDtoExample.Criteria criteria = example.or();
      
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
        
            List<AlarmDto> selected = dao.selectByExample(example);
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
        for(AlarmEntity entity : getAll()) {
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
    public AlarmEntity get(AlarmDto dto) {
        return map.get(AlarmEntity.newMapKey(dto));
    }
       
    protected boolean checkCreated(AlarmDto dto){
        if(map.containsKey(AlarmEntity.newMapKey(dto))) return true;
        return false;
    }
    
    protected AlarmEntity load(AlarmDto dto) {
        AlarmEntity entity = newEntity(dto);
        map.put(entity.mapKey(), entity);    

        
        return entity;
    }
    
    protected void loaded(AlarmEntity entity){
        loadPublisher.publish(new LoadEvent<AlarmEntity>(cloneOf(entity)));
    }
        
    @Override
    protected void updated(List<AlarmEntity> entities) {
        for(AlarmEntity entity : entities) updatePublisher.publish(new UpdateEvent<AlarmEntity>(cloneOf(entity)));
    }
  
    @Override
    protected void daoDeleted(List<AlarmEntity> entities) {
        super.daoDeleted(entities);
        for(AlarmEntity entity : entities) deletePublisher.publish(new DeleteEvent<AlarmEntity>(cloneOf(entity)));

    }
    
    @Override
    protected List<AlarmDto> dtoListToStart() {
        return dao.selectAll();
    }
}
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
import com.lge.sm.cr_data_store.repository.EventHistoryRepository;
import com.lge.sm.cr_data_store.dao.EventHistoryDao;

import com.lge.sm.cr_data_store.entity.EventHistoryEntity;
import com.lge.sm.cr_data_store.dto.EventHistoryDto;
import com.lge.sm.cr_data_store.dto.EventHistoryDtoExample;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.lge.sm.cr_data_store.repository.SkinRepository;
import com.lge.sm.cr_data_store.entity.SkinEntity;

abstract public class AEventHistoryRepository extends PermanenceRepository<EventHistoryEntity, EventHistoryDao, EventHistoryDto, EventHistoryDtoExample>{
    @SuppressWarnings("unchecked")  
    private KindredEventPublisher<LoadEvent<EventHistoryEntity>> loadPublisher = (KindredEventPublisher<LoadEvent<EventHistoryEntity>>)EventBroker.getPublisher(EventHistoryEntity.class, LoadEvent.class);
    @SuppressWarnings("unchecked")  
    private KindredEventPublisher<CreateEvent<EventHistoryEntity>> createPublisher = (KindredEventPublisher<CreateEvent<EventHistoryEntity>>)EventBroker.getPublisher(EventHistoryEntity.class, CreateEvent.class);
    @SuppressWarnings("unchecked")
    private KindredEventPublisher<UpdateEvent<EventHistoryEntity>> updatePublisher = (KindredEventPublisher<UpdateEvent<EventHistoryEntity>>)EventBroker.getPublisher(EventHistoryEntity.class, UpdateEvent.class);
    @SuppressWarnings("unchecked")
    private KindredEventPublisher<DeleteEvent<EventHistoryEntity>> deletePublisher = (KindredEventPublisher<DeleteEvent<EventHistoryEntity>>)EventBroker.getPublisher(EventHistoryEntity.class, DeleteEvent.class);

    @Autowired
    public AEventHistoryRepository(EventHistoryDao dao) {
        super(dao);
    }
    


    @Override
    public EventHistoryEntity create(EventHistoryDto dto) throws IllegalArgumentException {
    	dto.setCdate(DateStringUtil.getCurrentDateString(DateStringUtil.gmtTimeZoneId));

        if(checkCreated(dto) == true) throw new IllegalArgumentException("Already created : " + ToString.toLine(dto));    
        if(checkForeignKeyEntityExist(dto) == false) throw new IllegalArgumentException("No record of foreign key when create : " + ToString.toLine(dto));
        if(dao.insert(dto) == false) throw new IllegalArgumentException();
            
        EventHistoryEntity ret = load(dto);
        createPublisher.publish(new CreateEvent<EventHistoryEntity>(cloneOf(ret)));
        
        return ret;
    }
  
    protected boolean checkForeignKeyEntityExist(EventHistoryDto dto) {

        return true;
    }
        
    public EventHistoryEntity cloneOf(EventHistoryEntity entity) {
        EventHistoryEntity ret = newEntity(entity.getDto());

        return ret;
    }
  
    protected EventHistoryEntity newEntity(EventHistoryDto dto){
        return new EventHistoryEntity(dto);
    }
    
    @Transactional
    @Override
    protected boolean deleteDao(List<EventHistoryEntity> entities) {
        super.deleteDao(entities);
 
        return dao.delete(Repos.repo(EventHistoryRepository.class).getDtoList(entities));
    }
   
    @Override
    protected boolean initDescendent() {



        return true;
    }
    
    public EventHistoryDtoExample example(){
        return new EventHistoryDtoExample();
    }
    
    private EventHistoryDtoExample criteriaFactory = new EventHistoryDtoExample();
    public EventHistoryDtoExample.Criteria criteria(){
        return criteriaFactory.createCriteria();
    }
    
    public List<EventHistoryEntity> getByCriteria(EventHistoryDtoExample.Criteria c){
        EventHistoryDtoExample newExample = new EventHistoryDtoExample();
        newExample.or(c);
        List<EventHistoryDto> dtos = dao.selectByExample(newExample);
        List<EventHistoryEntity> entities = new ArrayList<>();
        if(dtos == null) return entities;
        for(EventHistoryDto each : dtos) entities.add(load(each));
      
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
            EventHistoryDto dto = om.treeToValue(inputNode, EventHistoryDto.class);
            EventHistoryEntity entity = get(dto);
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
  
    public String skinized(EventHistoryEntity entity) {
        return skinize(entity);
    }
    
    protected String skinize(EventHistoryEntity entity) {
        return skinize(entity.getDto());
    }
  
    protected String skinize(EventHistoryDto dto) {
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
        return EventHistoryEntity.skinType();
    }

    @Override
    public Skin skin() {
        SkinEntity entity = Repos.repo(SkinRepository.class).getByMapKey(SkinEntity.newMapKey(skinType()));
        return entity.skin(getParentSkinTypes(), getKidSkinTypes());
    }
    
    protected EventHistoryDto jsonNodeToDto(JsonNode node) {
        ObjectMapper om = JsonUtil.objectMapper();
        try {
            EventHistoryDto dto = om.treeToValue(node, EventHistoryDto.class);
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
    	List<EventHistoryDto> dtoList = new ArrayList<>();
		for(JsonNode each : nodeList) {
	        EventHistoryDto dto = jsonNodeToDto(each);
	        if(dto == null) return "";
	        dtoList.add(dto);
		}
		
    	List<EventHistoryEntity> entityList = new ArrayList<>();
		for(EventHistoryDto dto : dtoList) {
	        EventHistoryEntity entity = create(dto);
	        if(entity == null) Logger.error(TAG, "Failed to create : " + ToString.toLine(dto));
	        else entityList.add(entity);
		}
		
        StringBuffer ret = new StringBuffer();
        ret.append("[");
        for(int i = 0; i < entityList.size(); i++) {
        	EventHistoryEntity entity = entityList.get(i);
            ret.append(skinize(entity));
            if(i != entityList.size() - 1) ret.append(",");
        }
        ret.append("]");
        
        return ret.toString();
    }
    
    public String update(JsonNode nodeList) {
    	List<EventHistoryDto> dtoList = new ArrayList<>();
		for(JsonNode each : nodeList) {
	        EventHistoryDto dto = jsonNodeToDto(each);
	        System.out.println(ToString.toLine(dto));
	        if(dto == null) return "";
	        dtoList.add(dto);
		}
		
    	List<EventHistoryEntity> entityList = new ArrayList<>();
		for(EventHistoryDto dto : dtoList) {
	        EventHistoryEntity entity = newEntity(dto);
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
        	EventHistoryEntity entity = entityList.get(i);
            ret.append(skinize(entity));
            if(i != entityList.size() - 1) ret.append(",");
        }
        ret.append("]");
        
        return ret.toString();
    }
    
    public String delete(JsonNode nodeList) {
    	List<EventHistoryDto> dtoList = new ArrayList<>();
		for(JsonNode each : nodeList) {
	        EventHistoryDto dto = jsonNodeToDto(each);
	        System.out.println(ToString.toLine(dto));
	        if(dto == null) return "";
	        dtoList.add(dto);
		}
		
    	List<EventHistoryEntity> entityList = new ArrayList<>();
		for(EventHistoryDto dto : dtoList) {
	        EventHistoryEntity entity = get(dto);
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
        	EventHistoryEntity entity = entityList.get(i);
            ret.append(skinize(entity));
            if(i != entityList.size() - 1) ret.append(",");
        }
        ret.append("]");
        
        return ret.toString();
    }
    
    public String getSkinizedKids(JsonNode node, String kidSkinType) {
        EventHistoryDto dto = jsonNodeToDto(node);
        if(dto == null) return "";
        EventHistoryEntity entity = get(dto);
        if(entity == null) return "";

        
        return "";
    }
    
    @Override
    public String read(Map<String, String> from, Map<String, String> to, Map<String, List<String>> maps, int limit) {
        List<EventHistoryDto> dtos = new ArrayList<>();
        Object[] keySet = maps.keySet().toArray();
        int idx[] = new int[keySet.length];
            
        while(true){
            EventHistoryDtoExample example = example();
            example.setOrderByClause("cdate desc limit " + limit);
            EventHistoryDtoExample.Criteria criteria = example.or();
      
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
        
            List<EventHistoryDto> selected = dao.selectByExample(example);
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
      return JsonUtil.toJsonString(new ArrayList<>());
    }

// ----------------------------------------------------------------------------------------------------------------------------------------

    protected EventHistoryEntity load(EventHistoryDto dto) {
        EventHistoryEntity entity = newEntity(dto);   
        return entity;
    }

    protected boolean checkCreated(EventHistoryDto dto){ return false; }
    
    @Override
    public EventHistoryEntity get(EventHistoryDto dto) {
        EventHistoryEntity entity = newEntity(dao.select(dto.getName(),dto.getCdate()));
        return entity;
    }
}


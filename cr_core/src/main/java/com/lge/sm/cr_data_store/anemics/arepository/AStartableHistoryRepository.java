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
import com.lge.sm.cr_data_store.repository.StartableHistoryRepository;
import com.lge.sm.cr_data_store.dao.StartableHistoryDao;
import com.lge.sm.cr_data_store.entity.StartableEntity;
import com.lge.sm.cr_data_store.repository.StartableRepository;

import com.lge.sm.cr_data_store.entity.StartableHistoryEntity;
import com.lge.sm.cr_data_store.dto.StartableHistoryDto;
import com.lge.sm.cr_data_store.dto.StartableHistoryDtoExample;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.lge.sm.cr_data_store.repository.SkinRepository;
import com.lge.sm.cr_data_store.entity.SkinEntity;

abstract public class AStartableHistoryRepository extends PermanenceRepository<StartableHistoryEntity, StartableHistoryDao, StartableHistoryDto, StartableHistoryDtoExample>{
    @SuppressWarnings("unchecked")  
    private KindredEventPublisher<LoadEvent<StartableHistoryEntity>> loadPublisher = (KindredEventPublisher<LoadEvent<StartableHistoryEntity>>)EventBroker.getPublisher(StartableHistoryEntity.class, LoadEvent.class);
    @SuppressWarnings("unchecked")  
    private KindredEventPublisher<CreateEvent<StartableHistoryEntity>> createPublisher = (KindredEventPublisher<CreateEvent<StartableHistoryEntity>>)EventBroker.getPublisher(StartableHistoryEntity.class, CreateEvent.class);
    @SuppressWarnings("unchecked")
    private KindredEventPublisher<UpdateEvent<StartableHistoryEntity>> updatePublisher = (KindredEventPublisher<UpdateEvent<StartableHistoryEntity>>)EventBroker.getPublisher(StartableHistoryEntity.class, UpdateEvent.class);
    @SuppressWarnings("unchecked")
    private KindredEventPublisher<DeleteEvent<StartableHistoryEntity>> deletePublisher = (KindredEventPublisher<DeleteEvent<StartableHistoryEntity>>)EventBroker.getPublisher(StartableHistoryEntity.class, DeleteEvent.class);

    @Autowired
    public AStartableHistoryRepository(StartableHistoryDao dao) {
        super(dao);
    }
    


    @Override
    public StartableHistoryEntity create(StartableHistoryDto dto) throws IllegalArgumentException {
    	dto.setCdate(DateStringUtil.getCurrentDateString(DateStringUtil.gmtTimeZoneId));

        if(checkCreated(dto) == true) throw new IllegalArgumentException("Already created : " + ToString.toLine(dto));    
        if(checkForeignKeyEntityExist(dto) == false) throw new IllegalArgumentException("No record of foreign key when create : " + ToString.toLine(dto));
        if(dao.insert(dto) == false) throw new IllegalArgumentException();
            
        StartableHistoryEntity ret = load(dto);
        createPublisher.publish(new CreateEvent<StartableHistoryEntity>(cloneOf(ret)));
        
        return ret;
    }
  
    protected boolean checkForeignKeyEntityExist(StartableHistoryDto dto) {
		if(dto.getStartableId() != null && Repos.repo(StartableRepository.class).getByMapKey(StartableEntity.newMapKey(dto.getStartableId())) == null) return false;

        return true;
    }
        
    public StartableHistoryEntity cloneOf(StartableHistoryEntity entity) {
        StartableHistoryEntity ret = newEntity(entity.getDto());

        return ret;
    }
  
    protected StartableHistoryEntity newEntity(StartableHistoryDto dto){
        return new StartableHistoryEntity(dto);
    }
    
    @Transactional
    @Override
    protected boolean deleteDao(List<StartableHistoryEntity> entities) {
        super.deleteDao(entities);
 
        return dao.delete(Repos.repo(StartableHistoryRepository.class).getDtoList(entities));
    }
   
    @Override
    protected boolean initDescendent() {


		parentSkinTypes.add(StartableEntity.skinType());

        return true;
    }
    
    public StartableHistoryDtoExample example(){
        return new StartableHistoryDtoExample();
    }
    
    private StartableHistoryDtoExample criteriaFactory = new StartableHistoryDtoExample();
    public StartableHistoryDtoExample.Criteria criteria(){
        return criteriaFactory.createCriteria();
    }
    
    public List<StartableHistoryEntity> getByCriteria(StartableHistoryDtoExample.Criteria c){
        StartableHistoryDtoExample newExample = new StartableHistoryDtoExample();
        newExample.or(c);
        List<StartableHistoryDto> dtos = dao.selectByExample(newExample);
        List<StartableHistoryEntity> entities = new ArrayList<>();
        if(dtos == null) return entities;
        for(StartableHistoryDto each : dtos) entities.add(load(each));
      
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
            StartableHistoryDto dto = om.treeToValue(inputNode, StartableHistoryDto.class);
            StartableHistoryEntity entity = get(dto);
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
  
    public String skinized(StartableHistoryEntity entity) {
        return skinize(entity);
    }
    
    protected String skinize(StartableHistoryEntity entity) {
        return skinize(entity.getDto());
    }
  
    protected String skinize(StartableHistoryDto dto) {
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
        return StartableHistoryEntity.skinType();
    }

    @Override
    public Skin skin() {
        SkinEntity entity = Repos.repo(SkinRepository.class).getByMapKey(SkinEntity.newMapKey(skinType()));
        return entity.skin(getParentSkinTypes(), getKidSkinTypes());
    }
    
    protected StartableHistoryDto jsonNodeToDto(JsonNode node) {
        ObjectMapper om = JsonUtil.objectMapper();
        try {
            StartableHistoryDto dto = om.treeToValue(node, StartableHistoryDto.class);
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
    	List<StartableHistoryDto> dtoList = new ArrayList<>();
		for(JsonNode each : nodeList) {
	        StartableHistoryDto dto = jsonNodeToDto(each);
	        if(dto == null) return "";
	        dtoList.add(dto);
		}
		
    	List<StartableHistoryEntity> entityList = new ArrayList<>();
		for(StartableHistoryDto dto : dtoList) {
	        StartableHistoryEntity entity = create(dto);
	        if(entity == null) Logger.error(TAG, "Failed to create : " + ToString.toLine(dto));
	        else entityList.add(entity);
		}
		
        StringBuffer ret = new StringBuffer();
        ret.append("[");
        for(int i = 0; i < entityList.size(); i++) {
        	StartableHistoryEntity entity = entityList.get(i);
            ret.append(skinize(entity));
            if(i != entityList.size() - 1) ret.append(",");
        }
        ret.append("]");
        
        return ret.toString();
    }
    
    public String update(JsonNode nodeList) {
    	List<StartableHistoryDto> dtoList = new ArrayList<>();
		for(JsonNode each : nodeList) {
	        StartableHistoryDto dto = jsonNodeToDto(each);
	        System.out.println(ToString.toLine(dto));
	        if(dto == null) return "";
	        dtoList.add(dto);
		}
		
    	List<StartableHistoryEntity> entityList = new ArrayList<>();
		for(StartableHistoryDto dto : dtoList) {
	        StartableHistoryEntity entity = newEntity(dto);
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
        	StartableHistoryEntity entity = entityList.get(i);
            ret.append(skinize(entity));
            if(i != entityList.size() - 1) ret.append(",");
        }
        ret.append("]");
        
        return ret.toString();
    }
    
    public String delete(JsonNode nodeList) {
    	List<StartableHistoryDto> dtoList = new ArrayList<>();
		for(JsonNode each : nodeList) {
	        StartableHistoryDto dto = jsonNodeToDto(each);
	        System.out.println(ToString.toLine(dto));
	        if(dto == null) return "";
	        dtoList.add(dto);
		}
		
    	List<StartableHistoryEntity> entityList = new ArrayList<>();
		for(StartableHistoryDto dto : dtoList) {
	        StartableHistoryEntity entity = get(dto);
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
        	StartableHistoryEntity entity = entityList.get(i);
            ret.append(skinize(entity));
            if(i != entityList.size() - 1) ret.append(",");
        }
        ret.append("]");
        
        return ret.toString();
    }
    
    public String getSkinizedKids(JsonNode node, String kidSkinType) {
        StartableHistoryDto dto = jsonNodeToDto(node);
        if(dto == null) return "";
        StartableHistoryEntity entity = get(dto);
        if(entity == null) return "";

        
        return "";
    }
    
    @Override
    public String read(Map<String, String> from, Map<String, String> to, Map<String, List<String>> maps, int limit) {
        List<StartableHistoryDto> dtos = new ArrayList<>();
        Object[] keySet = maps.keySet().toArray();
        int idx[] = new int[keySet.length];
            
        while(true){
            StartableHistoryDtoExample example = example();
            example.setOrderByClause("cdate desc limit " + limit);
            StartableHistoryDtoExample.Criteria criteria = example.or();
      
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
        
            List<StartableHistoryDto> selected = dao.selectByExample(example);
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

    protected StartableHistoryEntity load(StartableHistoryDto dto) {
        StartableHistoryEntity entity = newEntity(dto);   
        return entity;
    }

    protected boolean checkCreated(StartableHistoryDto dto){ return false; }
    
    @Override
    public StartableHistoryEntity get(StartableHistoryDto dto) {
        StartableHistoryEntity entity = newEntity(dao.select(dto.getStartableId(),dto.getCdate()));
        return entity;
    }
}


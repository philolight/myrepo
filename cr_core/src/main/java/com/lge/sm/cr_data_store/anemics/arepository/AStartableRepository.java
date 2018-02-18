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
import com.lge.sm.cr_data_store.repository.StartableRepository;
import com.lge.sm.cr_data_store.dao.StartableDao;
import com.lge.sm.cr_data_store.entity.ServiceEntity;
import com.lge.sm.cr_data_store.repository.ServiceRepository;

import com.lge.sm.cr_data_store.entity.StartableEntity;
import com.lge.sm.cr_data_store.dto.StartableDto;
import com.lge.sm.cr_data_store.dto.StartableDtoExample;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.lge.sm.cr_data_store.repository.SkinRepository;
import com.lge.sm.cr_data_store.entity.SkinEntity;

abstract public class AStartableRepository extends CacheableRepository<StartableEntity, StartableDao, StartableDto, StartableDtoExample>{
    @SuppressWarnings("unchecked")  
    private KindredEventPublisher<LoadEvent<StartableEntity>> loadPublisher = (KindredEventPublisher<LoadEvent<StartableEntity>>)EventBroker.getPublisher(StartableEntity.class, LoadEvent.class);
    @SuppressWarnings("unchecked")  
    private KindredEventPublisher<CreateEvent<StartableEntity>> createPublisher = (KindredEventPublisher<CreateEvent<StartableEntity>>)EventBroker.getPublisher(StartableEntity.class, CreateEvent.class);
    @SuppressWarnings("unchecked")
    private KindredEventPublisher<UpdateEvent<StartableEntity>> updatePublisher = (KindredEventPublisher<UpdateEvent<StartableEntity>>)EventBroker.getPublisher(StartableEntity.class, UpdateEvent.class);
    @SuppressWarnings("unchecked")
    private KindredEventPublisher<DeleteEvent<StartableEntity>> deletePublisher = (KindredEventPublisher<DeleteEvent<StartableEntity>>)EventBroker.getPublisher(StartableEntity.class, DeleteEvent.class);

    @Autowired
    public AStartableRepository(StartableDao dao) {
        super(dao);
    }
    


    @Override
    public StartableEntity create(StartableDto dto) throws IllegalArgumentException {
    	dto.setCdate(DateStringUtil.getCurrentDateString(DateStringUtil.gmtTimeZoneId));

        if(checkCreated(dto) == true) throw new IllegalArgumentException("Already created : " + ToString.toLine(dto));    
        if(checkForeignKeyEntityExist(dto) == false) throw new IllegalArgumentException("No record of foreign key when create : " + ToString.toLine(dto));
        if(dao.insert(dto) == false) throw new IllegalArgumentException();
            
        StartableEntity ret = load(dto);
        createPublisher.publish(new CreateEvent<StartableEntity>(cloneOf(ret)));
        
        return ret;
    }
  
    protected boolean checkForeignKeyEntityExist(StartableDto dto) {

        return true;
    }
        
    public StartableEntity cloneOf(StartableEntity entity) {
        StartableEntity ret = newEntity(entity.getDto());

        return ret;
    }
  
    protected StartableEntity newEntity(StartableDto dto){
        return new StartableEntity(dto);
    }
    
    @Transactional
    @Override
    protected boolean deleteDao(List<StartableEntity> entities) {
        super.deleteDao(entities);
		List<ServiceEntity> serviceList = new ArrayList<>();
		for(StartableEntity each : entities) serviceList.addAll(Repos.repo(ServiceRepository.class).getByStartableId(each.getStartableId()));
		if(serviceList.size() != 0) {
			if(Repos.repo(ServiceRepository.class).delete(serviceList) == false) return false;
		}
 
        return dao.delete(Repos.repo(StartableRepository.class).getDtoList(entities));
    }
   
    @Override
    protected boolean initDescendent() {



		kidSkinTypes.add(ServiceEntity.skinType());
        return true;
    }
    
    public StartableDtoExample example(){
        return new StartableDtoExample();
    }
    
    private StartableDtoExample criteriaFactory = new StartableDtoExample();
    public StartableDtoExample.Criteria criteria(){
        return criteriaFactory.createCriteria();
    }
    
    public List<StartableEntity> getByCriteria(StartableDtoExample.Criteria c){
        StartableDtoExample newExample = new StartableDtoExample();
        newExample.or(c);
        List<StartableDto> dtos = dao.selectByExample(newExample);
        List<StartableEntity> entities = new ArrayList<>();
        if(dtos == null) return entities;
        for(StartableDto each : dtos) entities.add(load(each));
      
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
            StartableDto dto = om.treeToValue(inputNode, StartableDto.class);
            StartableEntity entity = get(dto);
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
  
    public String skinized(StartableEntity entity) {
        return skinize(entity);
    }
    
    protected String skinize(StartableEntity entity) {
        return skinize(entity.getDto());
    }
  
    protected String skinize(StartableDto dto) {
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
        return StartableEntity.skinType();
    }

    @Override
    public Skin skin() {
        SkinEntity entity = Repos.repo(SkinRepository.class).getByMapKey(SkinEntity.newMapKey(skinType()));
        return entity.skin(getParentSkinTypes(), getKidSkinTypes());
    }
    
    protected StartableDto jsonNodeToDto(JsonNode node) {
        ObjectMapper om = JsonUtil.objectMapper();
        try {
            StartableDto dto = om.treeToValue(node, StartableDto.class);
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
    	List<StartableDto> dtoList = new ArrayList<>();
		for(JsonNode each : nodeList) {
	        StartableDto dto = jsonNodeToDto(each);
	        if(dto == null) return "";
	        dtoList.add(dto);
		}
		
    	List<StartableEntity> entityList = new ArrayList<>();
		for(StartableDto dto : dtoList) {
	        StartableEntity entity = create(dto);
	        if(entity == null) Logger.error(TAG, "Failed to create : " + ToString.toLine(dto));
	        else entityList.add(entity);
		}
		
        StringBuffer ret = new StringBuffer();
        ret.append("[");
        for(int i = 0; i < entityList.size(); i++) {
        	StartableEntity entity = entityList.get(i);
            ret.append(skinize(entity));
            if(i != entityList.size() - 1) ret.append(",");
        }
        ret.append("]");
        
        return ret.toString();
    }
    
    public String update(JsonNode nodeList) {
    	List<StartableDto> dtoList = new ArrayList<>();
		for(JsonNode each : nodeList) {
	        StartableDto dto = jsonNodeToDto(each);
	        System.out.println(ToString.toLine(dto));
	        if(dto == null) return "";
	        dtoList.add(dto);
		}
		
    	List<StartableEntity> entityList = new ArrayList<>();
		for(StartableDto dto : dtoList) {
	        StartableEntity entity = newEntity(dto);
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
        	StartableEntity entity = entityList.get(i);
            ret.append(skinize(entity));
            if(i != entityList.size() - 1) ret.append(",");
        }
        ret.append("]");
        
        return ret.toString();
    }
    
    public String delete(JsonNode nodeList) {
    	List<StartableDto> dtoList = new ArrayList<>();
		for(JsonNode each : nodeList) {
	        StartableDto dto = jsonNodeToDto(each);
	        System.out.println(ToString.toLine(dto));
	        if(dto == null) return "";
	        dtoList.add(dto);
		}
		
    	List<StartableEntity> entityList = new ArrayList<>();
		for(StartableDto dto : dtoList) {
	        StartableEntity entity = get(dto);
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
        	StartableEntity entity = entityList.get(i);
            ret.append(skinize(entity));
            if(i != entityList.size() - 1) ret.append(",");
        }
        ret.append("]");
        
        return ret.toString();
    }
    
    public String getSkinizedKids(JsonNode node, String kidSkinType) {
        StartableDto dto = jsonNodeToDto(node);
        if(dto == null) return "";
        StartableEntity entity = get(dto);
        if(entity == null) return "";

		if(kidSkinType.equals("Service")) {
			List<ServiceEntity> list = entity.getServiceEntityList();
			ServiceRepository repo = Repos.repo(ServiceRepository.class);
			StringBuffer ret = new StringBuffer();
			ret.append("[");
			for(int i = 0; i < list.size(); i++) {
				ServiceEntity each = list.get(i);
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
        List<StartableDto> dtos = new ArrayList<>();
        Object[] keySet = maps.keySet().toArray();
        int idx[] = new int[keySet.length];
            
        while(true){
            StartableDtoExample example = example();
            example.setOrderByClause("cdate desc limit " + limit);
            StartableDtoExample.Criteria criteria = example.or();
      
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
        
            List<StartableDto> selected = dao.selectByExample(example);
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
        for(StartableEntity entity : getAll()) {
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
    public StartableEntity get(StartableDto dto) {
        return map.get(StartableEntity.newMapKey(dto));
    }
       
    protected boolean checkCreated(StartableDto dto){
        if(map.containsKey(StartableEntity.newMapKey(dto))) return true;
        return false;
    }
    
    protected StartableEntity load(StartableDto dto) {
        StartableEntity entity = newEntity(dto);
        map.put(entity.mapKey(), entity);    

        
        return entity;
    }
    
    protected void loaded(StartableEntity entity){
        loadPublisher.publish(new LoadEvent<StartableEntity>(cloneOf(entity)));
    }
        
    @Override
    protected void updated(List<StartableEntity> entities) {
        for(StartableEntity entity : entities) updatePublisher.publish(new UpdateEvent<StartableEntity>(cloneOf(entity)));
    }
  
    @Override
    protected void daoDeleted(List<StartableEntity> entities) {
        super.daoDeleted(entities);
        for(StartableEntity entity : entities) deletePublisher.publish(new DeleteEvent<StartableEntity>(cloneOf(entity)));

    }
    
    @Override
    protected List<StartableDto> dtoListToStart() {
        return dao.selectAll();
    }
}
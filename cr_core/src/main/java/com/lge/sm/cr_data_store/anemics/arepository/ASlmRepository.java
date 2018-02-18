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
import com.lge.sm.cr_data_store.repository.SlmRepository;
import com.lge.sm.cr_data_store.dao.SlmDao;
import com.lge.sm.cr_data_store.entity.SensorEntity;
import com.lge.sm.cr_data_store.repository.SensorRepository;

import com.lge.sm.cr_data_store.entity.SlmEntity;
import com.lge.sm.cr_data_store.dto.SlmDto;
import com.lge.sm.cr_data_store.dto.SlmDtoExample;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.lge.sm.cr_data_store.repository.SkinRepository;
import com.lge.sm.cr_data_store.entity.SkinEntity;

abstract public class ASlmRepository extends CacheableRepository<SlmEntity, SlmDao, SlmDto, SlmDtoExample>{
    @SuppressWarnings("unchecked")  
    private KindredEventPublisher<LoadEvent<SlmEntity>> loadPublisher = (KindredEventPublisher<LoadEvent<SlmEntity>>)EventBroker.getPublisher(SlmEntity.class, LoadEvent.class);
    @SuppressWarnings("unchecked")  
    private KindredEventPublisher<CreateEvent<SlmEntity>> createPublisher = (KindredEventPublisher<CreateEvent<SlmEntity>>)EventBroker.getPublisher(SlmEntity.class, CreateEvent.class);
    @SuppressWarnings("unchecked")
    private KindredEventPublisher<UpdateEvent<SlmEntity>> updatePublisher = (KindredEventPublisher<UpdateEvent<SlmEntity>>)EventBroker.getPublisher(SlmEntity.class, UpdateEvent.class);
    @SuppressWarnings("unchecked")
    private KindredEventPublisher<DeleteEvent<SlmEntity>> deletePublisher = (KindredEventPublisher<DeleteEvent<SlmEntity>>)EventBroker.getPublisher(SlmEntity.class, DeleteEvent.class);

    @Autowired
    public ASlmRepository(SlmDao dao) {
        super(dao);
    }
    


    @Override
    public SlmEntity create(SlmDto dto) throws IllegalArgumentException {
    	dto.setCdate(DateStringUtil.getCurrentDateString(DateStringUtil.gmtTimeZoneId));

        if(checkCreated(dto) == true) throw new IllegalArgumentException("Already created : " + ToString.toLine(dto));    
        if(checkForeignKeyEntityExist(dto) == false) throw new IllegalArgumentException("No record of foreign key when create : " + ToString.toLine(dto));
        if(dao.insert(dto) == false) throw new IllegalArgumentException();
            
        SlmEntity ret = load(dto);
        createPublisher.publish(new CreateEvent<SlmEntity>(cloneOf(ret)));
        
        return ret;
    }
  
    protected boolean checkForeignKeyEntityExist(SlmDto dto) {

        return true;
    }
        
    public SlmEntity cloneOf(SlmEntity entity) {
        SlmEntity ret = newEntity(entity.getDto());

        return ret;
    }
  
    protected SlmEntity newEntity(SlmDto dto){
        return new SlmEntity(dto);
    }
    
    @Transactional
    @Override
    protected boolean deleteDao(List<SlmEntity> entities) {
        super.deleteDao(entities);
		List<SensorEntity> sensorList = new ArrayList<>();
		for(SlmEntity each : entities) sensorList.addAll(Repos.repo(SensorRepository.class).getBySlmId(each.getSlmId()));
		if(sensorList.size() != 0) {
			if(Repos.repo(SensorRepository.class).delete(sensorList) == false) return false;
		}
 
        return dao.delete(Repos.repo(SlmRepository.class).getDtoList(entities));
    }
   
    @Override
    protected boolean initDescendent() {



		kidSkinTypes.add(SensorEntity.skinType());
        return true;
    }
    
    public SlmDtoExample example(){
        return new SlmDtoExample();
    }
    
    private SlmDtoExample criteriaFactory = new SlmDtoExample();
    public SlmDtoExample.Criteria criteria(){
        return criteriaFactory.createCriteria();
    }
    
    public List<SlmEntity> getByCriteria(SlmDtoExample.Criteria c){
        SlmDtoExample newExample = new SlmDtoExample();
        newExample.or(c);
        List<SlmDto> dtos = dao.selectByExample(newExample);
        List<SlmEntity> entities = new ArrayList<>();
        if(dtos == null) return entities;
        for(SlmDto each : dtos) entities.add(load(each));
      
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
            SlmDto dto = om.treeToValue(inputNode, SlmDto.class);
            SlmEntity entity = get(dto);
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
  
    public String skinized(SlmEntity entity) {
        return skinize(entity);
    }
    
    protected String skinize(SlmEntity entity) {
        return skinize(entity.getDto());
    }
  
    protected String skinize(SlmDto dto) {
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
        return SlmEntity.skinType();
    }

    @Override
    public Skin skin() {
        SkinEntity entity = Repos.repo(SkinRepository.class).getByMapKey(SkinEntity.newMapKey(skinType()));
        return entity.skin(getParentSkinTypes(), getKidSkinTypes());
    }
    
    protected SlmDto jsonNodeToDto(JsonNode node) {
        ObjectMapper om = JsonUtil.objectMapper();
        try {
            SlmDto dto = om.treeToValue(node, SlmDto.class);
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
    	List<SlmDto> dtoList = new ArrayList<>();
		for(JsonNode each : nodeList) {
	        SlmDto dto = jsonNodeToDto(each);
	        if(dto == null) return "";
	        dtoList.add(dto);
		}
		
    	List<SlmEntity> entityList = new ArrayList<>();
		for(SlmDto dto : dtoList) {
	        SlmEntity entity = create(dto);
	        if(entity == null) Logger.error(TAG, "Failed to create : " + ToString.toLine(dto));
	        else entityList.add(entity);
		}
		
        StringBuffer ret = new StringBuffer();
        ret.append("[");
        for(int i = 0; i < entityList.size(); i++) {
        	SlmEntity entity = entityList.get(i);
            ret.append(skinize(entity));
            if(i != entityList.size() - 1) ret.append(",");
        }
        ret.append("]");
        
        return ret.toString();
    }
    
    public String update(JsonNode nodeList) {
    	List<SlmDto> dtoList = new ArrayList<>();
		for(JsonNode each : nodeList) {
	        SlmDto dto = jsonNodeToDto(each);
	        System.out.println(ToString.toLine(dto));
	        if(dto == null) return "";
	        dtoList.add(dto);
		}
		
    	List<SlmEntity> entityList = new ArrayList<>();
		for(SlmDto dto : dtoList) {
	        SlmEntity entity = newEntity(dto);
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
        	SlmEntity entity = entityList.get(i);
            ret.append(skinize(entity));
            if(i != entityList.size() - 1) ret.append(",");
        }
        ret.append("]");
        
        return ret.toString();
    }
    
    public String delete(JsonNode nodeList) {
    	List<SlmDto> dtoList = new ArrayList<>();
		for(JsonNode each : nodeList) {
	        SlmDto dto = jsonNodeToDto(each);
	        System.out.println(ToString.toLine(dto));
	        if(dto == null) return "";
	        dtoList.add(dto);
		}
		
    	List<SlmEntity> entityList = new ArrayList<>();
		for(SlmDto dto : dtoList) {
	        SlmEntity entity = get(dto);
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
        	SlmEntity entity = entityList.get(i);
            ret.append(skinize(entity));
            if(i != entityList.size() - 1) ret.append(",");
        }
        ret.append("]");
        
        return ret.toString();
    }
    
    public String getSkinizedKids(JsonNode node, String kidSkinType) {
        SlmDto dto = jsonNodeToDto(node);
        if(dto == null) return "";
        SlmEntity entity = get(dto);
        if(entity == null) return "";

		if(kidSkinType.equals("Sensor")) {
			List<SensorEntity> list = entity.getSensorEntityList();
			SensorRepository repo = Repos.repo(SensorRepository.class);
			StringBuffer ret = new StringBuffer();
			ret.append("[");
			for(int i = 0; i < list.size(); i++) {
				SensorEntity each = list.get(i);
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
        List<SlmDto> dtos = new ArrayList<>();
        Object[] keySet = maps.keySet().toArray();
        int idx[] = new int[keySet.length];
            
        while(true){
            SlmDtoExample example = example();
            example.setOrderByClause("cdate desc limit " + limit);
            SlmDtoExample.Criteria criteria = example.or();
      
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
        
            List<SlmDto> selected = dao.selectByExample(example);
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
        for(SlmEntity entity : getAll()) {
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
    public SlmEntity get(SlmDto dto) {
        return map.get(SlmEntity.newMapKey(dto));
    }
       
    protected boolean checkCreated(SlmDto dto){
        if(map.containsKey(SlmEntity.newMapKey(dto))) return true;
        return false;
    }
    
    protected SlmEntity load(SlmDto dto) {
        SlmEntity entity = newEntity(dto);
        map.put(entity.mapKey(), entity);    

        
        return entity;
    }
    
    protected void loaded(SlmEntity entity){
        loadPublisher.publish(new LoadEvent<SlmEntity>(cloneOf(entity)));
    }
        
    @Override
    protected void updated(List<SlmEntity> entities) {
        for(SlmEntity entity : entities) updatePublisher.publish(new UpdateEvent<SlmEntity>(cloneOf(entity)));
    }
  
    @Override
    protected void daoDeleted(List<SlmEntity> entities) {
        super.daoDeleted(entities);
        for(SlmEntity entity : entities) deletePublisher.publish(new DeleteEvent<SlmEntity>(cloneOf(entity)));

    }
    
    @Override
    protected List<SlmDto> dtoListToStart() {
        return dao.selectAll();
    }
}
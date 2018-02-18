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
import com.lge.sm.cr_data_store.repository.DriverTypeRepository;
import com.lge.sm.cr_data_store.dao.DriverTypeDao;
import com.lge.sm.cr_data_store.entity.DriverEntity;
import com.lge.sm.cr_data_store.repository.DriverRepository;

import com.lge.sm.cr_data_store.entity.DriverTypeEntity;
import com.lge.sm.cr_data_store.dto.DriverTypeDto;
import com.lge.sm.cr_data_store.dto.DriverTypeDtoExample;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.lge.sm.cr_data_store.repository.SkinRepository;
import com.lge.sm.cr_data_store.entity.SkinEntity;

abstract public class ADriverTypeRepository extends CacheableRepository<DriverTypeEntity, DriverTypeDao, DriverTypeDto, DriverTypeDtoExample>{
    @SuppressWarnings("unchecked")  
    private KindredEventPublisher<LoadEvent<DriverTypeEntity>> loadPublisher = (KindredEventPublisher<LoadEvent<DriverTypeEntity>>)EventBroker.getPublisher(DriverTypeEntity.class, LoadEvent.class);
    @SuppressWarnings("unchecked")  
    private KindredEventPublisher<CreateEvent<DriverTypeEntity>> createPublisher = (KindredEventPublisher<CreateEvent<DriverTypeEntity>>)EventBroker.getPublisher(DriverTypeEntity.class, CreateEvent.class);
    @SuppressWarnings("unchecked")
    private KindredEventPublisher<UpdateEvent<DriverTypeEntity>> updatePublisher = (KindredEventPublisher<UpdateEvent<DriverTypeEntity>>)EventBroker.getPublisher(DriverTypeEntity.class, UpdateEvent.class);
    @SuppressWarnings("unchecked")
    private KindredEventPublisher<DeleteEvent<DriverTypeEntity>> deletePublisher = (KindredEventPublisher<DeleteEvent<DriverTypeEntity>>)EventBroker.getPublisher(DriverTypeEntity.class, DeleteEvent.class);

    @Autowired
    public ADriverTypeRepository(DriverTypeDao dao) {
        super(dao);
    }
    


    @Override
    public DriverTypeEntity create(DriverTypeDto dto) throws IllegalArgumentException {
    	dto.setCdate(DateStringUtil.getCurrentDateString(DateStringUtil.gmtTimeZoneId));

        if(checkCreated(dto) == true) throw new IllegalArgumentException("Already created : " + ToString.toLine(dto));    
        if(checkForeignKeyEntityExist(dto) == false) throw new IllegalArgumentException("No record of foreign key when create : " + ToString.toLine(dto));
        if(dao.insert(dto) == false) throw new IllegalArgumentException();
            
        DriverTypeEntity ret = load(dto);
        createPublisher.publish(new CreateEvent<DriverTypeEntity>(cloneOf(ret)));
        
        return ret;
    }
  
    protected boolean checkForeignKeyEntityExist(DriverTypeDto dto) {

        return true;
    }
        
    public DriverTypeEntity cloneOf(DriverTypeEntity entity) {
        DriverTypeEntity ret = newEntity(entity.getDto());

        return ret;
    }
  
    protected DriverTypeEntity newEntity(DriverTypeDto dto){
        return new DriverTypeEntity(dto);
    }
    
    @Transactional
    @Override
    protected boolean deleteDao(List<DriverTypeEntity> entities) {
        super.deleteDao(entities);
		List<DriverEntity> driverList = new ArrayList<>();
		for(DriverTypeEntity each : entities) driverList.addAll(Repos.repo(DriverRepository.class).getByDriverTypeId(each.getDriverTypeId()));
		if(driverList.size() != 0) {
			if(Repos.repo(DriverRepository.class).delete(driverList) == false) return false;
		}
 
        return dao.delete(Repos.repo(DriverTypeRepository.class).getDtoList(entities));
    }
   
    @Override
    protected boolean initDescendent() {



		kidSkinTypes.add(DriverEntity.skinType());
        return true;
    }
    
    public DriverTypeDtoExample example(){
        return new DriverTypeDtoExample();
    }
    
    private DriverTypeDtoExample criteriaFactory = new DriverTypeDtoExample();
    public DriverTypeDtoExample.Criteria criteria(){
        return criteriaFactory.createCriteria();
    }
    
    public List<DriverTypeEntity> getByCriteria(DriverTypeDtoExample.Criteria c){
        DriverTypeDtoExample newExample = new DriverTypeDtoExample();
        newExample.or(c);
        List<DriverTypeDto> dtos = dao.selectByExample(newExample);
        List<DriverTypeEntity> entities = new ArrayList<>();
        if(dtos == null) return entities;
        for(DriverTypeDto each : dtos) entities.add(load(each));
      
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
            DriverTypeDto dto = om.treeToValue(inputNode, DriverTypeDto.class);
            DriverTypeEntity entity = get(dto);
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
  
    public String skinized(DriverTypeEntity entity) {
        return skinize(entity);
    }
    
    protected String skinize(DriverTypeEntity entity) {
        return skinize(entity.getDto());
    }
  
    protected String skinize(DriverTypeDto dto) {
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
        return DriverTypeEntity.skinType();
    }

    @Override
    public Skin skin() {
        SkinEntity entity = Repos.repo(SkinRepository.class).getByMapKey(SkinEntity.newMapKey(skinType()));
        return entity.skin(getParentSkinTypes(), getKidSkinTypes());
    }
    
    protected DriverTypeDto jsonNodeToDto(JsonNode node) {
        ObjectMapper om = JsonUtil.objectMapper();
        try {
            DriverTypeDto dto = om.treeToValue(node, DriverTypeDto.class);
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
    	List<DriverTypeDto> dtoList = new ArrayList<>();
		for(JsonNode each : nodeList) {
	        DriverTypeDto dto = jsonNodeToDto(each);
	        if(dto == null) return "";
	        dtoList.add(dto);
		}
		
    	List<DriverTypeEntity> entityList = new ArrayList<>();
		for(DriverTypeDto dto : dtoList) {
	        DriverTypeEntity entity = create(dto);
	        if(entity == null) Logger.error(TAG, "Failed to create : " + ToString.toLine(dto));
	        else entityList.add(entity);
		}
		
        StringBuffer ret = new StringBuffer();
        ret.append("[");
        for(int i = 0; i < entityList.size(); i++) {
        	DriverTypeEntity entity = entityList.get(i);
            ret.append(skinize(entity));
            if(i != entityList.size() - 1) ret.append(",");
        }
        ret.append("]");
        
        return ret.toString();
    }
    
    public String update(JsonNode nodeList) {
    	List<DriverTypeDto> dtoList = new ArrayList<>();
		for(JsonNode each : nodeList) {
	        DriverTypeDto dto = jsonNodeToDto(each);
	        System.out.println(ToString.toLine(dto));
	        if(dto == null) return "";
	        dtoList.add(dto);
		}
		
    	List<DriverTypeEntity> entityList = new ArrayList<>();
		for(DriverTypeDto dto : dtoList) {
	        DriverTypeEntity entity = newEntity(dto);
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
        	DriverTypeEntity entity = entityList.get(i);
            ret.append(skinize(entity));
            if(i != entityList.size() - 1) ret.append(",");
        }
        ret.append("]");
        
        return ret.toString();
    }
    
    public String delete(JsonNode nodeList) {
    	List<DriverTypeDto> dtoList = new ArrayList<>();
		for(JsonNode each : nodeList) {
	        DriverTypeDto dto = jsonNodeToDto(each);
	        System.out.println(ToString.toLine(dto));
	        if(dto == null) return "";
	        dtoList.add(dto);
		}
		
    	List<DriverTypeEntity> entityList = new ArrayList<>();
		for(DriverTypeDto dto : dtoList) {
	        DriverTypeEntity entity = get(dto);
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
        	DriverTypeEntity entity = entityList.get(i);
            ret.append(skinize(entity));
            if(i != entityList.size() - 1) ret.append(",");
        }
        ret.append("]");
        
        return ret.toString();
    }
    
    public String getSkinizedKids(JsonNode node, String kidSkinType) {
        DriverTypeDto dto = jsonNodeToDto(node);
        if(dto == null) return "";
        DriverTypeEntity entity = get(dto);
        if(entity == null) return "";

		if(kidSkinType.equals("Driver")) {
			List<DriverEntity> list = entity.getDriverEntityList();
			DriverRepository repo = Repos.repo(DriverRepository.class);
			StringBuffer ret = new StringBuffer();
			ret.append("[");
			for(int i = 0; i < list.size(); i++) {
				DriverEntity each = list.get(i);
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
        List<DriverTypeDto> dtos = new ArrayList<>();
        Object[] keySet = maps.keySet().toArray();
        int idx[] = new int[keySet.length];
            
        while(true){
            DriverTypeDtoExample example = example();
            example.setOrderByClause("cdate desc limit " + limit);
            DriverTypeDtoExample.Criteria criteria = example.or();
      
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
        
            List<DriverTypeDto> selected = dao.selectByExample(example);
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
        for(DriverTypeEntity entity : getAll()) {
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
    public DriverTypeEntity get(DriverTypeDto dto) {
        return map.get(DriverTypeEntity.newMapKey(dto));
    }
       
    protected boolean checkCreated(DriverTypeDto dto){
        if(map.containsKey(DriverTypeEntity.newMapKey(dto))) return true;
        return false;
    }
    
    protected DriverTypeEntity load(DriverTypeDto dto) {
        DriverTypeEntity entity = newEntity(dto);
        map.put(entity.mapKey(), entity);    

        
        return entity;
    }
    
    protected void loaded(DriverTypeEntity entity){
        loadPublisher.publish(new LoadEvent<DriverTypeEntity>(cloneOf(entity)));
    }
        
    @Override
    protected void updated(List<DriverTypeEntity> entities) {
        for(DriverTypeEntity entity : entities) updatePublisher.publish(new UpdateEvent<DriverTypeEntity>(cloneOf(entity)));
    }
  
    @Override
    protected void daoDeleted(List<DriverTypeEntity> entities) {
        super.daoDeleted(entities);
        for(DriverTypeEntity entity : entities) deletePublisher.publish(new DeleteEvent<DriverTypeEntity>(cloneOf(entity)));

    }
    
    @Override
    protected List<DriverTypeDto> dtoListToStart() {
        return dao.selectAll();
    }
}
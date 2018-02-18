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
import com.lge.sm.cr_data_store.repository.NumericRangeRepository;
import com.lge.sm.cr_data_store.dao.NumericRangeDao;
import com.lge.sm.cr_data_store.entity.FieldSkinEntity;
import com.lge.sm.cr_data_store.repository.FieldSkinRepository;

import com.lge.sm.cr_data_store.entity.NumericRangeEntity;
import com.lge.sm.cr_data_store.dto.NumericRangeDto;
import com.lge.sm.cr_data_store.dto.NumericRangeDtoExample;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.lge.sm.cr_data_store.repository.SkinRepository;
import com.lge.sm.cr_data_store.entity.SkinEntity;

abstract public class ANumericRangeRepository extends CacheableRepository<NumericRangeEntity, NumericRangeDao, NumericRangeDto, NumericRangeDtoExample>{
    @SuppressWarnings("unchecked")  
    private KindredEventPublisher<LoadEvent<NumericRangeEntity>> loadPublisher = (KindredEventPublisher<LoadEvent<NumericRangeEntity>>)EventBroker.getPublisher(NumericRangeEntity.class, LoadEvent.class);
    @SuppressWarnings("unchecked")  
    private KindredEventPublisher<CreateEvent<NumericRangeEntity>> createPublisher = (KindredEventPublisher<CreateEvent<NumericRangeEntity>>)EventBroker.getPublisher(NumericRangeEntity.class, CreateEvent.class);
    @SuppressWarnings("unchecked")
    private KindredEventPublisher<UpdateEvent<NumericRangeEntity>> updatePublisher = (KindredEventPublisher<UpdateEvent<NumericRangeEntity>>)EventBroker.getPublisher(NumericRangeEntity.class, UpdateEvent.class);
    @SuppressWarnings("unchecked")
    private KindredEventPublisher<DeleteEvent<NumericRangeEntity>> deletePublisher = (KindredEventPublisher<DeleteEvent<NumericRangeEntity>>)EventBroker.getPublisher(NumericRangeEntity.class, DeleteEvent.class);

    @Autowired
    public ANumericRangeRepository(NumericRangeDao dao) {
        super(dao);
    }
    

	protected volatile long lastId = -1;
	public long getNextId() {
		return ++lastId;
	}

    @Override
    public NumericRangeEntity create(NumericRangeDto dto) throws IllegalArgumentException {
    	dto.setCdate(DateStringUtil.getCurrentDateString(DateStringUtil.gmtTimeZoneId));
		dto.setNumericRangeId(getNextId());
        if(checkCreated(dto) == true) throw new IllegalArgumentException("Already created : " + ToString.toLine(dto));    
        if(checkForeignKeyEntityExist(dto) == false) throw new IllegalArgumentException("No record of foreign key when create : " + ToString.toLine(dto));
        if(dao.insert(dto) == false) throw new IllegalArgumentException();
            
        NumericRangeEntity ret = load(dto);
        createPublisher.publish(new CreateEvent<NumericRangeEntity>(cloneOf(ret)));
        
        return ret;
    }
  
    protected boolean checkForeignKeyEntityExist(NumericRangeDto dto) {
		if(dto.getFieldSkinId() != null && Repos.repo(FieldSkinRepository.class).getByMapKey(FieldSkinEntity.newMapKey(dto.getFieldSkinId())) == null) return false;

        return true;
    }
        
    public NumericRangeEntity cloneOf(NumericRangeEntity entity) {
        NumericRangeEntity ret = newEntity(entity.getDto());

        return ret;
    }
  
    protected NumericRangeEntity newEntity(NumericRangeDto dto){
        return new NumericRangeEntity(dto);
    }
    
    @Transactional
    @Override
    protected boolean deleteDao(List<NumericRangeEntity> entities) {
        super.deleteDao(entities);
 
        return dao.delete(Repos.repo(NumericRangeRepository.class).getDtoList(entities));
    }
   
    @Override
    protected boolean initDescendent() {

		lastId = dao.getLastId();

		parentSkinTypes.add(FieldSkinEntity.skinType());

        return true;
    }
    
    public NumericRangeDtoExample example(){
        return new NumericRangeDtoExample();
    }
    
    private NumericRangeDtoExample criteriaFactory = new NumericRangeDtoExample();
    public NumericRangeDtoExample.Criteria criteria(){
        return criteriaFactory.createCriteria();
    }
    
    public List<NumericRangeEntity> getByCriteria(NumericRangeDtoExample.Criteria c){
        NumericRangeDtoExample newExample = new NumericRangeDtoExample();
        newExample.or(c);
        List<NumericRangeDto> dtos = dao.selectByExample(newExample);
        List<NumericRangeEntity> entities = new ArrayList<>();
        if(dtos == null) return entities;
        for(NumericRangeDto each : dtos) entities.add(load(each));
      
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
            NumericRangeDto dto = om.treeToValue(inputNode, NumericRangeDto.class);
            NumericRangeEntity entity = get(dto);
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
  
    public String skinized(NumericRangeEntity entity) {
        return skinize(entity);
    }
    
    protected String skinize(NumericRangeEntity entity) {
        return skinize(entity.getDto());
    }
  
    protected String skinize(NumericRangeDto dto) {
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
        return NumericRangeEntity.skinType();
    }

    @Override
    public Skin skin() {
        SkinEntity entity = Repos.repo(SkinRepository.class).getByMapKey(SkinEntity.newMapKey(skinType()));
        return entity.skin(getParentSkinTypes(), getKidSkinTypes());
    }
    
    protected NumericRangeDto jsonNodeToDto(JsonNode node) {
        ObjectMapper om = JsonUtil.objectMapper();
        try {
            NumericRangeDto dto = om.treeToValue(node, NumericRangeDto.class);
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
    	List<NumericRangeDto> dtoList = new ArrayList<>();
		for(JsonNode each : nodeList) {
	        NumericRangeDto dto = jsonNodeToDto(each);
	        if(dto == null) return "";
	        dtoList.add(dto);
		}
		
    	List<NumericRangeEntity> entityList = new ArrayList<>();
		for(NumericRangeDto dto : dtoList) {
	        NumericRangeEntity entity = create(dto);
	        if(entity == null) Logger.error(TAG, "Failed to create : " + ToString.toLine(dto));
	        else entityList.add(entity);
		}
		
        StringBuffer ret = new StringBuffer();
        ret.append("[");
        for(int i = 0; i < entityList.size(); i++) {
        	NumericRangeEntity entity = entityList.get(i);
            ret.append(skinize(entity));
            if(i != entityList.size() - 1) ret.append(",");
        }
        ret.append("]");
        
        return ret.toString();
    }
    
    public String update(JsonNode nodeList) {
    	List<NumericRangeDto> dtoList = new ArrayList<>();
		for(JsonNode each : nodeList) {
	        NumericRangeDto dto = jsonNodeToDto(each);
	        System.out.println(ToString.toLine(dto));
	        if(dto == null) return "";
	        dtoList.add(dto);
		}
		
    	List<NumericRangeEntity> entityList = new ArrayList<>();
		for(NumericRangeDto dto : dtoList) {
	        NumericRangeEntity entity = newEntity(dto);
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
        	NumericRangeEntity entity = entityList.get(i);
            ret.append(skinize(entity));
            if(i != entityList.size() - 1) ret.append(",");
        }
        ret.append("]");
        
        return ret.toString();
    }
    
    public String delete(JsonNode nodeList) {
    	List<NumericRangeDto> dtoList = new ArrayList<>();
		for(JsonNode each : nodeList) {
	        NumericRangeDto dto = jsonNodeToDto(each);
	        System.out.println(ToString.toLine(dto));
	        if(dto == null) return "";
	        dtoList.add(dto);
		}
		
    	List<NumericRangeEntity> entityList = new ArrayList<>();
		for(NumericRangeDto dto : dtoList) {
	        NumericRangeEntity entity = get(dto);
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
        	NumericRangeEntity entity = entityList.get(i);
            ret.append(skinize(entity));
            if(i != entityList.size() - 1) ret.append(",");
        }
        ret.append("]");
        
        return ret.toString();
    }
    
    public String getSkinizedKids(JsonNode node, String kidSkinType) {
        NumericRangeDto dto = jsonNodeToDto(node);
        if(dto == null) return "";
        NumericRangeEntity entity = get(dto);
        if(entity == null) return "";

        
        return "";
    }
    
    @Override
    public String read(Map<String, String> from, Map<String, String> to, Map<String, List<String>> maps, int limit) {
        List<NumericRangeDto> dtos = new ArrayList<>();
        Object[] keySet = maps.keySet().toArray();
        int idx[] = new int[keySet.length];
            
        while(true){
            NumericRangeDtoExample example = example();
            example.setOrderByClause("cdate desc limit " + limit);
            NumericRangeDtoExample.Criteria criteria = example.or();
      
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
        
            List<NumericRangeDto> selected = dao.selectByExample(example);
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
        for(NumericRangeEntity entity : getAll()) {
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


	protected MapSet<NumericRangeEntity> fieldSkinMapSet = new MapSet<>();

	public List<NumericRangeEntity> getByFieldSkinId(String fieldSkinId) {
		return fieldSkinMapSet.get(FieldSkinEntity.newMapKey(fieldSkinId));
	}

    @Override
    public NumericRangeEntity get(NumericRangeDto dto) {
        return map.get(NumericRangeEntity.newMapKey(dto));
    }
       
    protected boolean checkCreated(NumericRangeDto dto){
        if(map.containsKey(NumericRangeEntity.newMapKey(dto))) return true;
        return false;
    }
    
    protected NumericRangeEntity load(NumericRangeDto dto) {
        NumericRangeEntity entity = newEntity(dto);
        map.put(entity.mapKey(), entity);    

		fieldSkinMapSet.put(FieldSkinEntity.newMapKey(entity.getFieldSkinId()), entity);
        
        return entity;
    }
    
    protected void loaded(NumericRangeEntity entity){
        loadPublisher.publish(new LoadEvent<NumericRangeEntity>(cloneOf(entity)));
    }
        
    @Override
    protected void updated(List<NumericRangeEntity> entities) {
        for(NumericRangeEntity entity : entities) updatePublisher.publish(new UpdateEvent<NumericRangeEntity>(cloneOf(entity)));
    }
  
    @Override
    protected void daoDeleted(List<NumericRangeEntity> entities) {
        super.daoDeleted(entities);
        for(NumericRangeEntity entity : entities) deletePublisher.publish(new DeleteEvent<NumericRangeEntity>(cloneOf(entity)));
		for(NumericRangeEntity each : entities) fieldSkinMapSet.remove(FieldSkinEntity.newMapKey(each.getFieldSkinId()), each);
    }
    
    @Override
    protected List<NumericRangeDto> dtoListToStart() {
        return dao.selectAll();
    }
}
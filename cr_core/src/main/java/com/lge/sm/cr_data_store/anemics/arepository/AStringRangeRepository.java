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
import com.lge.sm.cr_data_store.repository.StringRangeRepository;
import com.lge.sm.cr_data_store.dao.StringRangeDao;
import com.lge.sm.cr_data_store.entity.FieldSkinEntity;
import com.lge.sm.cr_data_store.repository.FieldSkinRepository;

import com.lge.sm.cr_data_store.entity.StringRangeEntity;
import com.lge.sm.cr_data_store.dto.StringRangeDto;
import com.lge.sm.cr_data_store.dto.StringRangeDtoExample;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.lge.sm.cr_data_store.repository.SkinRepository;
import com.lge.sm.cr_data_store.entity.SkinEntity;

abstract public class AStringRangeRepository extends CacheableRepository<StringRangeEntity, StringRangeDao, StringRangeDto, StringRangeDtoExample>{
    @SuppressWarnings("unchecked")  
    private KindredEventPublisher<LoadEvent<StringRangeEntity>> loadPublisher = (KindredEventPublisher<LoadEvent<StringRangeEntity>>)EventBroker.getPublisher(StringRangeEntity.class, LoadEvent.class);
    @SuppressWarnings("unchecked")  
    private KindredEventPublisher<CreateEvent<StringRangeEntity>> createPublisher = (KindredEventPublisher<CreateEvent<StringRangeEntity>>)EventBroker.getPublisher(StringRangeEntity.class, CreateEvent.class);
    @SuppressWarnings("unchecked")
    private KindredEventPublisher<UpdateEvent<StringRangeEntity>> updatePublisher = (KindredEventPublisher<UpdateEvent<StringRangeEntity>>)EventBroker.getPublisher(StringRangeEntity.class, UpdateEvent.class);
    @SuppressWarnings("unchecked")
    private KindredEventPublisher<DeleteEvent<StringRangeEntity>> deletePublisher = (KindredEventPublisher<DeleteEvent<StringRangeEntity>>)EventBroker.getPublisher(StringRangeEntity.class, DeleteEvent.class);

    @Autowired
    public AStringRangeRepository(StringRangeDao dao) {
        super(dao);
    }
    

	protected volatile long lastId = -1;
	public long getNextId() {
		return ++lastId;
	}

    @Override
    public StringRangeEntity create(StringRangeDto dto) throws IllegalArgumentException {
    	dto.setCdate(DateStringUtil.getCurrentDateString(DateStringUtil.gmtTimeZoneId));
		dto.setStringRangeId(getNextId());
        if(checkCreated(dto) == true) throw new IllegalArgumentException("Already created : " + ToString.toLine(dto));    
        if(checkForeignKeyEntityExist(dto) == false) throw new IllegalArgumentException("No record of foreign key when create : " + ToString.toLine(dto));
        if(dao.insert(dto) == false) throw new IllegalArgumentException();
            
        StringRangeEntity ret = load(dto);
        createPublisher.publish(new CreateEvent<StringRangeEntity>(cloneOf(ret)));
        
        return ret;
    }
  
    protected boolean checkForeignKeyEntityExist(StringRangeDto dto) {
		if(dto.getFieldSkinId() != null && Repos.repo(FieldSkinRepository.class).getByMapKey(FieldSkinEntity.newMapKey(dto.getFieldSkinId())) == null) return false;

        return true;
    }
        
    public StringRangeEntity cloneOf(StringRangeEntity entity) {
        StringRangeEntity ret = newEntity(entity.getDto());

        return ret;
    }
  
    protected StringRangeEntity newEntity(StringRangeDto dto){
        return new StringRangeEntity(dto);
    }
    
    @Transactional
    @Override
    protected boolean deleteDao(List<StringRangeEntity> entities) {
        super.deleteDao(entities);
 
        return dao.delete(Repos.repo(StringRangeRepository.class).getDtoList(entities));
    }
   
    @Override
    protected boolean initDescendent() {

		lastId = dao.getLastId();

		parentSkinTypes.add(FieldSkinEntity.skinType());

        return true;
    }
    
    public StringRangeDtoExample example(){
        return new StringRangeDtoExample();
    }
    
    private StringRangeDtoExample criteriaFactory = new StringRangeDtoExample();
    public StringRangeDtoExample.Criteria criteria(){
        return criteriaFactory.createCriteria();
    }
    
    public List<StringRangeEntity> getByCriteria(StringRangeDtoExample.Criteria c){
        StringRangeDtoExample newExample = new StringRangeDtoExample();
        newExample.or(c);
        List<StringRangeDto> dtos = dao.selectByExample(newExample);
        List<StringRangeEntity> entities = new ArrayList<>();
        if(dtos == null) return entities;
        for(StringRangeDto each : dtos) entities.add(load(each));
      
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
            StringRangeDto dto = om.treeToValue(inputNode, StringRangeDto.class);
            StringRangeEntity entity = get(dto);
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
  
    public String skinized(StringRangeEntity entity) {
        return skinize(entity);
    }
    
    protected String skinize(StringRangeEntity entity) {
        return skinize(entity.getDto());
    }
  
    protected String skinize(StringRangeDto dto) {
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
        return StringRangeEntity.skinType();
    }

    @Override
    public Skin skin() {
        SkinEntity entity = Repos.repo(SkinRepository.class).getByMapKey(SkinEntity.newMapKey(skinType()));
        return entity.skin(getParentSkinTypes(), getKidSkinTypes());
    }
    
    protected StringRangeDto jsonNodeToDto(JsonNode node) {
        ObjectMapper om = JsonUtil.objectMapper();
        try {
            StringRangeDto dto = om.treeToValue(node, StringRangeDto.class);
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
    	List<StringRangeDto> dtoList = new ArrayList<>();
		for(JsonNode each : nodeList) {
	        StringRangeDto dto = jsonNodeToDto(each);
	        if(dto == null) return "";
	        dtoList.add(dto);
		}
		
    	List<StringRangeEntity> entityList = new ArrayList<>();
		for(StringRangeDto dto : dtoList) {
	        StringRangeEntity entity = create(dto);
	        if(entity == null) Logger.error(TAG, "Failed to create : " + ToString.toLine(dto));
	        else entityList.add(entity);
		}
		
        StringBuffer ret = new StringBuffer();
        ret.append("[");
        for(int i = 0; i < entityList.size(); i++) {
        	StringRangeEntity entity = entityList.get(i);
            ret.append(skinize(entity));
            if(i != entityList.size() - 1) ret.append(",");
        }
        ret.append("]");
        
        return ret.toString();
    }
    
    public String update(JsonNode nodeList) {
    	List<StringRangeDto> dtoList = new ArrayList<>();
		for(JsonNode each : nodeList) {
	        StringRangeDto dto = jsonNodeToDto(each);
	        System.out.println(ToString.toLine(dto));
	        if(dto == null) return "";
	        dtoList.add(dto);
		}
		
    	List<StringRangeEntity> entityList = new ArrayList<>();
		for(StringRangeDto dto : dtoList) {
	        StringRangeEntity entity = newEntity(dto);
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
        	StringRangeEntity entity = entityList.get(i);
            ret.append(skinize(entity));
            if(i != entityList.size() - 1) ret.append(",");
        }
        ret.append("]");
        
        return ret.toString();
    }
    
    public String delete(JsonNode nodeList) {
    	List<StringRangeDto> dtoList = new ArrayList<>();
		for(JsonNode each : nodeList) {
	        StringRangeDto dto = jsonNodeToDto(each);
	        System.out.println(ToString.toLine(dto));
	        if(dto == null) return "";
	        dtoList.add(dto);
		}
		
    	List<StringRangeEntity> entityList = new ArrayList<>();
		for(StringRangeDto dto : dtoList) {
	        StringRangeEntity entity = get(dto);
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
        	StringRangeEntity entity = entityList.get(i);
            ret.append(skinize(entity));
            if(i != entityList.size() - 1) ret.append(",");
        }
        ret.append("]");
        
        return ret.toString();
    }
    
    public String getSkinizedKids(JsonNode node, String kidSkinType) {
        StringRangeDto dto = jsonNodeToDto(node);
        if(dto == null) return "";
        StringRangeEntity entity = get(dto);
        if(entity == null) return "";

        
        return "";
    }
    
    @Override
    public String read(Map<String, String> from, Map<String, String> to, Map<String, List<String>> maps, int limit) {
        List<StringRangeDto> dtos = new ArrayList<>();
        Object[] keySet = maps.keySet().toArray();
        int idx[] = new int[keySet.length];
            
        while(true){
            StringRangeDtoExample example = example();
            example.setOrderByClause("cdate desc limit " + limit);
            StringRangeDtoExample.Criteria criteria = example.or();
      
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
        
            List<StringRangeDto> selected = dao.selectByExample(example);
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
        for(StringRangeEntity entity : getAll()) {
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


	protected MapSet<StringRangeEntity> fieldSkinMapSet = new MapSet<>();

	public List<StringRangeEntity> getByFieldSkinId(String fieldSkinId) {
		return fieldSkinMapSet.get(FieldSkinEntity.newMapKey(fieldSkinId));
	}

    @Override
    public StringRangeEntity get(StringRangeDto dto) {
        return map.get(StringRangeEntity.newMapKey(dto));
    }
       
    protected boolean checkCreated(StringRangeDto dto){
        if(map.containsKey(StringRangeEntity.newMapKey(dto))) return true;
        return false;
    }
    
    protected StringRangeEntity load(StringRangeDto dto) {
        StringRangeEntity entity = newEntity(dto);
        map.put(entity.mapKey(), entity);    

		fieldSkinMapSet.put(FieldSkinEntity.newMapKey(entity.getFieldSkinId()), entity);
        
        return entity;
    }
    
    protected void loaded(StringRangeEntity entity){
        loadPublisher.publish(new LoadEvent<StringRangeEntity>(cloneOf(entity)));
    }
        
    @Override
    protected void updated(List<StringRangeEntity> entities) {
        for(StringRangeEntity entity : entities) updatePublisher.publish(new UpdateEvent<StringRangeEntity>(cloneOf(entity)));
    }
  
    @Override
    protected void daoDeleted(List<StringRangeEntity> entities) {
        super.daoDeleted(entities);
        for(StringRangeEntity entity : entities) deletePublisher.publish(new DeleteEvent<StringRangeEntity>(cloneOf(entity)));
		for(StringRangeEntity each : entities) fieldSkinMapSet.remove(FieldSkinEntity.newMapKey(each.getFieldSkinId()), each);
    }
    
    @Override
    protected List<StringRangeDto> dtoListToStart() {
        return dao.selectAll();
    }
}
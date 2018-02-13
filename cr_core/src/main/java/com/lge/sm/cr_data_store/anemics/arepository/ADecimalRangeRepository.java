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
import com.lge.sm.cr_data_store.repository.DecimalRangeRepository;
import com.lge.sm.cr_data_store.dao.DecimalRangeDao;
import com.lge.sm.cr_data_store.entity.FieldSkinEntity;
import com.lge.sm.cr_data_store.repository.FieldSkinRepository;

import com.lge.sm.cr_data_store.entity.DecimalRangeEntity;
import com.lge.sm.cr_data_store.dto.DecimalRangeDto;
import com.lge.sm.cr_data_store.dto.DecimalRangeDtoExample;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.lge.sm.cr_data_store.repository.SkinRepository;
import com.lge.sm.cr_data_store.entity.SkinEntity;

abstract public class ADecimalRangeRepository extends CacheableRepository<DecimalRangeEntity, DecimalRangeDao, DecimalRangeDto, DecimalRangeDtoExample>{
    @SuppressWarnings("unchecked")  
    private KindredEventPublisher<LoadEvent<DecimalRangeEntity>> loadPublisher = (KindredEventPublisher<LoadEvent<DecimalRangeEntity>>)EventBroker.getPublisher(DecimalRangeEntity.class, LoadEvent.class);
    @SuppressWarnings("unchecked")  
    private KindredEventPublisher<CreateEvent<DecimalRangeEntity>> createPublisher = (KindredEventPublisher<CreateEvent<DecimalRangeEntity>>)EventBroker.getPublisher(DecimalRangeEntity.class, CreateEvent.class);
    @SuppressWarnings("unchecked")
    private KindredEventPublisher<UpdateEvent<DecimalRangeEntity>> updatePublisher = (KindredEventPublisher<UpdateEvent<DecimalRangeEntity>>)EventBroker.getPublisher(DecimalRangeEntity.class, UpdateEvent.class);
    @SuppressWarnings("unchecked")
    private KindredEventPublisher<DeleteEvent<DecimalRangeEntity>> deletePublisher = (KindredEventPublisher<DeleteEvent<DecimalRangeEntity>>)EventBroker.getPublisher(DecimalRangeEntity.class, DeleteEvent.class);

    @Autowired
    public ADecimalRangeRepository(DecimalRangeDao dao) {
        super(dao);
    }
    

	protected volatile long lastId = -1;
	public long getNextId() {
		return ++lastId;
	}

    @Override
    public DecimalRangeEntity create(DecimalRangeDto dto) throws IllegalArgumentException {
        if(checkCreated(dto) == true) throw new IllegalArgumentException("Already created : " + ToString.toLine(dto));    
        if(checkForeignKeyEntityExist(dto) == false) throw new IllegalArgumentException("No record of foreign key when create : " + ToString.toLine(dto));
        if(dao.insert(dto) == false) throw new IllegalArgumentException();
            
        DecimalRangeEntity ret = load(dto);
        createPublisher.publish(new CreateEvent<DecimalRangeEntity>(cloneOf(ret)));
        
        return ret;
    }
  
    protected boolean checkForeignKeyEntityExist(DecimalRangeDto dto) {
		if(Repos.repo(FieldSkinRepository.class).getByMapKey(FieldSkinEntity.newMapKey(dto.getFieldSkinId())) == null) return false;

        return true;
    }
        
    public DecimalRangeEntity cloneOf(DecimalRangeEntity entity) {
        DecimalRangeEntity ret = newEntity(entity.getDto());

        return ret;
    }
  
    protected DecimalRangeEntity newEntity(DecimalRangeDto dto){
        return new DecimalRangeEntity(dto);
    }
    
    @Transactional
    @Override
    protected boolean deleteDao(List<DecimalRangeEntity> entities) {
        super.deleteDao(entities);
 
        return dao.delete(Repos.repo(DecimalRangeRepository.class).getDtoList(entities));
    }
   
    @Override
    protected boolean initDescendent() {

		lastId = dao.getLastId();

		parentSkinTypes.add(FieldSkinEntity.skinType());

        return true;
    }
    
    public DecimalRangeDtoExample example(){
        return new DecimalRangeDtoExample();
    }
    
    private DecimalRangeDtoExample criteriaFactory = new DecimalRangeDtoExample();
    public DecimalRangeDtoExample.Criteria criteria(){
        return criteriaFactory.createCriteria();
    }
    
    public List<DecimalRangeEntity> getByCriteria(DecimalRangeDtoExample.Criteria c){
        DecimalRangeDtoExample newExample = new DecimalRangeDtoExample();
        newExample.or(c);
        List<DecimalRangeDto> dtos = dao.selectByExample(newExample);
        List<DecimalRangeEntity> entities = new ArrayList<>();
        if(dtos == null) return entities;
        for(DecimalRangeDto each : dtos) entities.add(load(each));
      
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
            DecimalRangeDto dto = om.treeToValue(inputNode, DecimalRangeDto.class);
            DecimalRangeEntity entity = get(dto);
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
  
    public String skinized(DecimalRangeEntity entity) {
        return skinize(entity);
    }
    
    protected String skinize(DecimalRangeEntity entity) {
        return skinize(entity.getDto());
    }
  
    protected String skinize(DecimalRangeDto dto) {
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
        return DecimalRangeEntity.skinType();
    }

    @Override
    public Skin skin() {
        SkinEntity entity = Repos.repo(SkinRepository.class).getByMapKey(SkinEntity.newMapKey(skinType()));
        return entity.skin(getParentSkinTypes(), getKidSkinTypes());
    }
    
    protected DecimalRangeDto jsonNodeToDto(JsonNode node) {
        ObjectMapper om = JsonUtil.objectMapper();
        try {
            DecimalRangeDto dto = om.treeToValue(node, DecimalRangeDto.class);
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
        DecimalRangeDto dto = jsonNodeToDto(inputNode);
        if(dto == null) return "";
        DecimalRangeEntity entity = create(dto);
        if(entity != null) return skinized(entity);
        return "";
    }
    
    public String update(JsonNode inputNode) {
        DecimalRangeDto dto = jsonNodeToDto(inputNode);
        if(dto == null) return "";
        DecimalRangeEntity entity = get(dto);
        if(entity != null){
          boolean ret = update(newEntity(dto));
          if(ret) return skinized(get(dto));
        }
        return "";
    }
    
    public boolean delete(JsonNode inputNode) {
        DecimalRangeDto dto = jsonNodeToDto(inputNode);
        if(dto == null) return false;
        DecimalRangeEntity entity = get(dto);
        return delete(entity);
    }
    
    public String getSkinizedKids(JsonNode node, String kidSkinType) {
        DecimalRangeDto dto = jsonNodeToDto(node);
        if(dto == null) return "";
        DecimalRangeEntity entity = get(dto);

        
        return "";
    }
    
    @Override
    public String read(Map<String, String> from, Map<String, String> to, Map<String, List<String>> maps, int limit) {
        List<DecimalRangeDto> dtos = new ArrayList<>();
        Object[] keySet = maps.keySet().toArray();
        int idx[] = new int[keySet.length];
            
        while(true){
            DecimalRangeDtoExample example = example();
            example.setOrderByClause("cdate desc limit " + limit);
            DecimalRangeDtoExample.Criteria criteria = example.or();
      
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
        
            List<DecimalRangeDto> selected = dao.selectByExample(example);
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
        for(DecimalRangeEntity entity : getAll()) {
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


	protected MapSet<DecimalRangeEntity> fieldSkinMapSet = new MapSet<>();

	public List<DecimalRangeEntity> getByFieldSkinId(String fieldSkinId) {
		return fieldSkinMapSet.get(FieldSkinEntity.newMapKey(fieldSkinId));
	}

    @Override
    public DecimalRangeEntity get(DecimalRangeDto dto) {
        return map.get(DecimalRangeEntity.newMapKey(dto));
    }
       
    protected boolean checkCreated(DecimalRangeDto dto){
        if(map.containsKey(DecimalRangeEntity.newMapKey(dto))) return true;
        return false;
    }
    
    protected DecimalRangeEntity load(DecimalRangeDto dto) {
        DecimalRangeEntity entity = newEntity(dto);
        map.put(entity.mapKey(), entity);    

		fieldSkinMapSet.put(FieldSkinEntity.newMapKey(entity.getFieldSkinId()), entity);
        
        return entity;
    }
    
    protected void loaded(DecimalRangeEntity entity){
        loadPublisher.publish(new LoadEvent<DecimalRangeEntity>(cloneOf(entity)));
    }
        
    @Override
    protected void updated(List<DecimalRangeEntity> entities) {
        for(DecimalRangeEntity entity : entities) updatePublisher.publish(new UpdateEvent<DecimalRangeEntity>(cloneOf(entity)));
    }
  
    @Override
    protected void daoDeleted(List<DecimalRangeEntity> entities) {
        super.daoDeleted(entities);
        for(DecimalRangeEntity entity : entities) deletePublisher.publish(new DeleteEvent<DecimalRangeEntity>(cloneOf(entity)));

    }
    
    @Override
    protected List<DecimalRangeDto> dtoListToStart() {
        return dao.selectAll();
    }
}
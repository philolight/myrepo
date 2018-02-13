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
import com.lge.sm.cr_data_store.repository.WatchableRepository;
import com.lge.sm.cr_data_store.dao.WatchableDao;

import com.lge.sm.cr_data_store.entity.WatchableEntity;
import com.lge.sm.cr_data_store.dto.WatchableDto;
import com.lge.sm.cr_data_store.dto.WatchableDtoExample;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.lge.sm.cr_data_store.repository.SkinRepository;
import com.lge.sm.cr_data_store.entity.SkinEntity;

abstract public class AWatchableRepository extends CacheableRepository<WatchableEntity, WatchableDao, WatchableDto, WatchableDtoExample>{
    @SuppressWarnings("unchecked")  
    private KindredEventPublisher<LoadEvent<WatchableEntity>> loadPublisher = (KindredEventPublisher<LoadEvent<WatchableEntity>>)EventBroker.getPublisher(WatchableEntity.class, LoadEvent.class);
    @SuppressWarnings("unchecked")  
    private KindredEventPublisher<CreateEvent<WatchableEntity>> createPublisher = (KindredEventPublisher<CreateEvent<WatchableEntity>>)EventBroker.getPublisher(WatchableEntity.class, CreateEvent.class);
    @SuppressWarnings("unchecked")
    private KindredEventPublisher<UpdateEvent<WatchableEntity>> updatePublisher = (KindredEventPublisher<UpdateEvent<WatchableEntity>>)EventBroker.getPublisher(WatchableEntity.class, UpdateEvent.class);
    @SuppressWarnings("unchecked")
    private KindredEventPublisher<DeleteEvent<WatchableEntity>> deletePublisher = (KindredEventPublisher<DeleteEvent<WatchableEntity>>)EventBroker.getPublisher(WatchableEntity.class, DeleteEvent.class);

    @Autowired
    public AWatchableRepository(WatchableDao dao) {
        super(dao);
    }
    


    @Override
    public WatchableEntity create(WatchableDto dto) throws IllegalArgumentException {
        if(checkCreated(dto) == true) throw new IllegalArgumentException("Already created : " + ToString.toLine(dto));    
        if(checkForeignKeyEntityExist(dto) == false) throw new IllegalArgumentException("No record of foreign key when create : " + ToString.toLine(dto));
        if(dao.insert(dto) == false) throw new IllegalArgumentException();
            
        WatchableEntity ret = load(dto);
        createPublisher.publish(new CreateEvent<WatchableEntity>(cloneOf(ret)));
        
        return ret;
    }
  
    protected boolean checkForeignKeyEntityExist(WatchableDto dto) {

        return true;
    }
        
    public WatchableEntity cloneOf(WatchableEntity entity) {
        WatchableEntity ret = newEntity(entity.getDto());

        return ret;
    }
  
    protected WatchableEntity newEntity(WatchableDto dto){
        return new WatchableEntity(dto);
    }
    
    @Transactional
    @Override
    protected boolean deleteDao(List<WatchableEntity> entities) {
        super.deleteDao(entities);
 
        return dao.delete(Repos.repo(WatchableRepository.class).getDtoList(entities));
    }
   
    @Override
    protected boolean initDescendent() {



        return true;
    }
    
    public WatchableDtoExample example(){
        return new WatchableDtoExample();
    }
    
    private WatchableDtoExample criteriaFactory = new WatchableDtoExample();
    public WatchableDtoExample.Criteria criteria(){
        return criteriaFactory.createCriteria();
    }
    
    public List<WatchableEntity> getByCriteria(WatchableDtoExample.Criteria c){
        WatchableDtoExample newExample = new WatchableDtoExample();
        newExample.or(c);
        List<WatchableDto> dtos = dao.selectByExample(newExample);
        List<WatchableEntity> entities = new ArrayList<>();
        if(dtos == null) return entities;
        for(WatchableDto each : dtos) entities.add(load(each));
      
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
            WatchableDto dto = om.treeToValue(inputNode, WatchableDto.class);
            WatchableEntity entity = get(dto);
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
  
    public String skinized(WatchableEntity entity) {
        return skinize(entity);
    }
    
    protected String skinize(WatchableEntity entity) {
        return skinize(entity.getDto());
    }
  
    protected String skinize(WatchableDto dto) {
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
        return WatchableEntity.skinType();
    }

    @Override
    public Skin skin() {
        SkinEntity entity = Repos.repo(SkinRepository.class).getByMapKey(SkinEntity.newMapKey(skinType()));
        return entity.skin(getParentSkinTypes(), getKidSkinTypes());
    }
    
    protected WatchableDto jsonNodeToDto(JsonNode node) {
        ObjectMapper om = JsonUtil.objectMapper();
        try {
            WatchableDto dto = om.treeToValue(node, WatchableDto.class);
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
        WatchableDto dto = jsonNodeToDto(inputNode);
        if(dto == null) return "";
        WatchableEntity entity = create(dto);
        if(entity != null) return skinized(entity);
        return "";
    }
    
    public String update(JsonNode inputNode) {
        WatchableDto dto = jsonNodeToDto(inputNode);
        if(dto == null) return "";
        WatchableEntity entity = get(dto);
        if(entity != null){
          boolean ret = update(newEntity(dto));
          if(ret) return skinized(get(dto));
        }
        return "";
    }
    
    public boolean delete(JsonNode inputNode) {
        WatchableDto dto = jsonNodeToDto(inputNode);
        if(dto == null) return false;
        WatchableEntity entity = get(dto);
        return delete(entity);
    }
    
    public String getSkinizedKids(JsonNode node, String kidSkinType) {
        WatchableDto dto = jsonNodeToDto(node);
        if(dto == null) return "";
        WatchableEntity entity = get(dto);

        
        return "";
    }
    
    @Override
    public String read(Map<String, String> from, Map<String, String> to, Map<String, List<String>> maps, int limit) {
        List<WatchableDto> dtos = new ArrayList<>();
        Object[] keySet = maps.keySet().toArray();
        int idx[] = new int[keySet.length];
            
        while(true){
            WatchableDtoExample example = example();
            example.setOrderByClause("cdate desc limit " + limit);
            WatchableDtoExample.Criteria criteria = example.or();
      
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
        
            List<WatchableDto> selected = dao.selectByExample(example);
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
        for(WatchableEntity entity : getAll()) {
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
    public WatchableEntity get(WatchableDto dto) {
        return map.get(WatchableEntity.newMapKey(dto));
    }
       
    protected boolean checkCreated(WatchableDto dto){
        if(map.containsKey(WatchableEntity.newMapKey(dto))) return true;
        return false;
    }
    
    protected WatchableEntity load(WatchableDto dto) {
        WatchableEntity entity = newEntity(dto);
        map.put(entity.mapKey(), entity);    

        
        return entity;
    }
    
    protected void loaded(WatchableEntity entity){
        loadPublisher.publish(new LoadEvent<WatchableEntity>(cloneOf(entity)));
    }
        
    @Override
    protected void updated(List<WatchableEntity> entities) {
        for(WatchableEntity entity : entities) updatePublisher.publish(new UpdateEvent<WatchableEntity>(cloneOf(entity)));
    }
  
    @Override
    protected void daoDeleted(List<WatchableEntity> entities) {
        super.daoDeleted(entities);
        for(WatchableEntity entity : entities) deletePublisher.publish(new DeleteEvent<WatchableEntity>(cloneOf(entity)));

    }
    
    @Override
    protected List<WatchableDto> dtoListToStart() {
        return dao.selectAll();
    }
}
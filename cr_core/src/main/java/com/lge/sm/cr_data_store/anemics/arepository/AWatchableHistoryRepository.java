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
import com.lge.sm.cr_data_store.repository.WatchableHistoryRepository;
import com.lge.sm.cr_data_store.dao.WatchableHistoryDao;
import com.lge.sm.cr_data_store.entity.WatchableEntity;
import com.lge.sm.cr_data_store.repository.WatchableRepository;

import com.lge.sm.cr_data_store.entity.WatchableHistoryEntity;
import com.lge.sm.cr_data_store.dto.WatchableHistoryDto;
import com.lge.sm.cr_data_store.dto.WatchableHistoryDtoExample;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.lge.sm.cr_data_store.repository.SkinRepository;
import com.lge.sm.cr_data_store.entity.SkinEntity;

abstract public class AWatchableHistoryRepository extends PermanenceRepository<WatchableHistoryEntity, WatchableHistoryDao, WatchableHistoryDto, WatchableHistoryDtoExample>{
    @SuppressWarnings("unchecked")  
    private KindredEventPublisher<LoadEvent<WatchableHistoryEntity>> loadPublisher = (KindredEventPublisher<LoadEvent<WatchableHistoryEntity>>)EventBroker.getPublisher(WatchableHistoryEntity.class, LoadEvent.class);
    @SuppressWarnings("unchecked")  
    private KindredEventPublisher<CreateEvent<WatchableHistoryEntity>> createPublisher = (KindredEventPublisher<CreateEvent<WatchableHistoryEntity>>)EventBroker.getPublisher(WatchableHistoryEntity.class, CreateEvent.class);
    @SuppressWarnings("unchecked")
    private KindredEventPublisher<UpdateEvent<WatchableHistoryEntity>> updatePublisher = (KindredEventPublisher<UpdateEvent<WatchableHistoryEntity>>)EventBroker.getPublisher(WatchableHistoryEntity.class, UpdateEvent.class);
    @SuppressWarnings("unchecked")
    private KindredEventPublisher<DeleteEvent<WatchableHistoryEntity>> deletePublisher = (KindredEventPublisher<DeleteEvent<WatchableHistoryEntity>>)EventBroker.getPublisher(WatchableHistoryEntity.class, DeleteEvent.class);

    @Autowired
    public AWatchableHistoryRepository(WatchableHistoryDao dao) {
        super(dao);
    }
    


    @Override
    public WatchableHistoryEntity create(WatchableHistoryDto dto) throws IllegalArgumentException {
        if(checkCreated(dto) == true) throw new IllegalArgumentException("Already created : " + ToString.toLine(dto));    
        if(checkForeignKeyEntityExist(dto) == false) throw new IllegalArgumentException("No record of foreign key when create : " + ToString.toLine(dto));
        if(dao.insert(dto) == false) throw new IllegalArgumentException();
            
        WatchableHistoryEntity ret = load(dto);
        createPublisher.publish(new CreateEvent<WatchableHistoryEntity>(cloneOf(ret)));
        
        return ret;
    }
  
    protected boolean checkForeignKeyEntityExist(WatchableHistoryDto dto) {
		if(Repos.repo(WatchableRepository.class).getByMapKey(WatchableEntity.newMapKey(dto.getWatchableId())) == null) return false;

        return true;
    }
        
    public WatchableHistoryEntity cloneOf(WatchableHistoryEntity entity) {
        WatchableHistoryEntity ret = newEntity(entity.getDto());

        return ret;
    }
  
    protected WatchableHistoryEntity newEntity(WatchableHistoryDto dto){
        return new WatchableHistoryEntity(dto);
    }
    
    @Transactional
    @Override
    protected boolean deleteDao(List<WatchableHistoryEntity> entities) {
        super.deleteDao(entities);
 
        return dao.delete(Repos.repo(WatchableHistoryRepository.class).getDtoList(entities));
    }
   
    @Override
    protected boolean initDescendent() {


		parentSkinTypes.add(WatchableEntity.skinType());

        return true;
    }
    
    public WatchableHistoryDtoExample example(){
        return new WatchableHistoryDtoExample();
    }
    
    private WatchableHistoryDtoExample criteriaFactory = new WatchableHistoryDtoExample();
    public WatchableHistoryDtoExample.Criteria criteria(){
        return criteriaFactory.createCriteria();
    }
    
    public List<WatchableHistoryEntity> getByCriteria(WatchableHistoryDtoExample.Criteria c){
        WatchableHistoryDtoExample newExample = new WatchableHistoryDtoExample();
        newExample.or(c);
        List<WatchableHistoryDto> dtos = dao.selectByExample(newExample);
        List<WatchableHistoryEntity> entities = new ArrayList<>();
        if(dtos == null) return entities;
        for(WatchableHistoryDto each : dtos) entities.add(load(each));
      
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
            WatchableHistoryDto dto = om.treeToValue(inputNode, WatchableHistoryDto.class);
            WatchableHistoryEntity entity = get(dto);
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
  
    public String skinized(WatchableHistoryEntity entity) {
        return skinize(entity);
    }
    
    protected String skinize(WatchableHistoryEntity entity) {
        return skinize(entity.getDto());
    }
  
    protected String skinize(WatchableHistoryDto dto) {
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
        return WatchableHistoryEntity.skinType();
    }

    @Override
    public Skin skin() {
        SkinEntity entity = Repos.repo(SkinRepository.class).getByMapKey(SkinEntity.newMapKey(skinType()));
        return entity.skin(getParentSkinTypes(), getKidSkinTypes());
    }
    
    protected WatchableHistoryDto jsonNodeToDto(JsonNode node) {
        ObjectMapper om = JsonUtil.objectMapper();
        try {
            WatchableHistoryDto dto = om.treeToValue(node, WatchableHistoryDto.class);
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
        WatchableHistoryDto dto = jsonNodeToDto(inputNode);
        if(dto == null) return "";
        WatchableHistoryEntity entity = create(dto);
        if(entity != null) return skinized(entity);
        return "";
    }
    
    public String update(JsonNode inputNode) {
        WatchableHistoryDto dto = jsonNodeToDto(inputNode);
        if(dto == null) return "";
        WatchableHistoryEntity entity = get(dto);
        if(entity != null){
          boolean ret = update(newEntity(dto));
          if(ret) return skinized(get(dto));
        }
        return "";
    }
    
    public boolean delete(JsonNode inputNode) {
        WatchableHistoryDto dto = jsonNodeToDto(inputNode);
        if(dto == null) return false;
        WatchableHistoryEntity entity = get(dto);
        return delete(entity);
    }
    
    public String getSkinizedKids(JsonNode node, String kidSkinType) {
        WatchableHistoryDto dto = jsonNodeToDto(node);
        if(dto == null) return "";
        WatchableHistoryEntity entity = get(dto);

        
        return "";
    }
    
    @Override
    public String read(Map<String, String> from, Map<String, String> to, Map<String, List<String>> maps, int limit) {
        List<WatchableHistoryDto> dtos = new ArrayList<>();
        Object[] keySet = maps.keySet().toArray();
        int idx[] = new int[keySet.length];
            
        while(true){
            WatchableHistoryDtoExample example = example();
            example.setOrderByClause("cdate desc limit " + limit);
            WatchableHistoryDtoExample.Criteria criteria = example.or();
      
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
        
            List<WatchableHistoryDto> selected = dao.selectByExample(example);
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

    protected WatchableHistoryEntity load(WatchableHistoryDto dto) {
        WatchableHistoryEntity entity = newEntity(dto);   
        return entity;
    }

    protected boolean checkCreated(WatchableHistoryDto dto){ return false; }
    
    @Override
    public WatchableHistoryEntity get(WatchableHistoryDto dto) {
        WatchableHistoryEntity entity = newEntity(dao.select(dto.getWatchableId(),dto.getCdate()));
        return entity;
    }
}


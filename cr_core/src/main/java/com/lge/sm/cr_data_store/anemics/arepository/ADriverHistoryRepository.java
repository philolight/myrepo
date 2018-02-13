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
import com.lge.sm.cr_data_store.repository.DriverHistoryRepository;
import com.lge.sm.cr_data_store.dao.DriverHistoryDao;
import com.lge.sm.cr_data_store.entity.DriverEntity;
import com.lge.sm.cr_data_store.repository.DriverRepository;

import com.lge.sm.cr_data_store.entity.DriverHistoryEntity;
import com.lge.sm.cr_data_store.dto.DriverHistoryDto;
import com.lge.sm.cr_data_store.dto.DriverHistoryDtoExample;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.lge.sm.cr_data_store.repository.SkinRepository;
import com.lge.sm.cr_data_store.entity.SkinEntity;

abstract public class ADriverHistoryRepository extends PermanenceRepository<DriverHistoryEntity, DriverHistoryDao, DriverHistoryDto, DriverHistoryDtoExample>{
    @SuppressWarnings("unchecked")  
    private KindredEventPublisher<LoadEvent<DriverHistoryEntity>> loadPublisher = (KindredEventPublisher<LoadEvent<DriverHistoryEntity>>)EventBroker.getPublisher(DriverHistoryEntity.class, LoadEvent.class);
    @SuppressWarnings("unchecked")  
    private KindredEventPublisher<CreateEvent<DriverHistoryEntity>> createPublisher = (KindredEventPublisher<CreateEvent<DriverHistoryEntity>>)EventBroker.getPublisher(DriverHistoryEntity.class, CreateEvent.class);
    @SuppressWarnings("unchecked")
    private KindredEventPublisher<UpdateEvent<DriverHistoryEntity>> updatePublisher = (KindredEventPublisher<UpdateEvent<DriverHistoryEntity>>)EventBroker.getPublisher(DriverHistoryEntity.class, UpdateEvent.class);
    @SuppressWarnings("unchecked")
    private KindredEventPublisher<DeleteEvent<DriverHistoryEntity>> deletePublisher = (KindredEventPublisher<DeleteEvent<DriverHistoryEntity>>)EventBroker.getPublisher(DriverHistoryEntity.class, DeleteEvent.class);

    @Autowired
    public ADriverHistoryRepository(DriverHistoryDao dao) {
        super(dao);
    }
    


    @Override
    public DriverHistoryEntity create(DriverHistoryDto dto) throws IllegalArgumentException {
        if(checkCreated(dto) == true) throw new IllegalArgumentException("Already created : " + ToString.toLine(dto));    
        if(checkForeignKeyEntityExist(dto) == false) throw new IllegalArgumentException("No record of foreign key when create : " + ToString.toLine(dto));
        if(dao.insert(dto) == false) throw new IllegalArgumentException();
            
        DriverHistoryEntity ret = load(dto);
        createPublisher.publish(new CreateEvent<DriverHistoryEntity>(cloneOf(ret)));
        
        return ret;
    }
  
    protected boolean checkForeignKeyEntityExist(DriverHistoryDto dto) {
		if(Repos.repo(DriverRepository.class).getByMapKey(DriverEntity.newMapKey(dto.getDriverId())) == null) return false;

        return true;
    }
        
    public DriverHistoryEntity cloneOf(DriverHistoryEntity entity) {
        DriverHistoryEntity ret = newEntity(entity.getDto());

        return ret;
    }
  
    protected DriverHistoryEntity newEntity(DriverHistoryDto dto){
        return new DriverHistoryEntity(dto);
    }
    
    @Transactional
    @Override
    protected boolean deleteDao(List<DriverHistoryEntity> entities) {
        super.deleteDao(entities);
 
        return dao.delete(Repos.repo(DriverHistoryRepository.class).getDtoList(entities));
    }
   
    @Override
    protected boolean initDescendent() {


		parentSkinTypes.add(DriverEntity.skinType());

        return true;
    }
    
    public DriverHistoryDtoExample example(){
        return new DriverHistoryDtoExample();
    }
    
    private DriverHistoryDtoExample criteriaFactory = new DriverHistoryDtoExample();
    public DriverHistoryDtoExample.Criteria criteria(){
        return criteriaFactory.createCriteria();
    }
    
    public List<DriverHistoryEntity> getByCriteria(DriverHistoryDtoExample.Criteria c){
        DriverHistoryDtoExample newExample = new DriverHistoryDtoExample();
        newExample.or(c);
        List<DriverHistoryDto> dtos = dao.selectByExample(newExample);
        List<DriverHistoryEntity> entities = new ArrayList<>();
        if(dtos == null) return entities;
        for(DriverHistoryDto each : dtos) entities.add(load(each));
      
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
            DriverHistoryDto dto = om.treeToValue(inputNode, DriverHistoryDto.class);
            DriverHistoryEntity entity = get(dto);
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
  
    public String skinized(DriverHistoryEntity entity) {
        return skinize(entity);
    }
    
    protected String skinize(DriverHistoryEntity entity) {
        return skinize(entity.getDto());
    }
  
    protected String skinize(DriverHistoryDto dto) {
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
        return DriverHistoryEntity.skinType();
    }

    @Override
    public Skin skin() {
        SkinEntity entity = Repos.repo(SkinRepository.class).getByMapKey(SkinEntity.newMapKey(skinType()));
        return entity.skin(getParentSkinTypes(), getKidSkinTypes());
    }
    
    protected DriverHistoryDto jsonNodeToDto(JsonNode node) {
        ObjectMapper om = JsonUtil.objectMapper();
        try {
            DriverHistoryDto dto = om.treeToValue(node, DriverHistoryDto.class);
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
        DriverHistoryDto dto = jsonNodeToDto(inputNode);
        if(dto == null) return "";
        DriverHistoryEntity entity = create(dto);
        if(entity != null) return skinized(entity);
        return "";
    }
    
    public String update(JsonNode inputNode) {
        DriverHistoryDto dto = jsonNodeToDto(inputNode);
        if(dto == null) return "";
        DriverHistoryEntity entity = get(dto);
        if(entity != null){
          boolean ret = update(newEntity(dto));
          if(ret) return skinized(get(dto));
        }
        return "";
    }
    
    public boolean delete(JsonNode inputNode) {
        DriverHistoryDto dto = jsonNodeToDto(inputNode);
        if(dto == null) return false;
        DriverHistoryEntity entity = get(dto);
        return delete(entity);
    }
    
    public String getSkinizedKids(JsonNode node, String kidSkinType) {
        DriverHistoryDto dto = jsonNodeToDto(node);
        if(dto == null) return "";
        DriverHistoryEntity entity = get(dto);

        
        return "";
    }
    
    @Override
    public String read(Map<String, String> from, Map<String, String> to, Map<String, List<String>> maps, int limit) {
        List<DriverHistoryDto> dtos = new ArrayList<>();
        Object[] keySet = maps.keySet().toArray();
        int idx[] = new int[keySet.length];
            
        while(true){
            DriverHistoryDtoExample example = example();
            example.setOrderByClause("cdate desc limit " + limit);
            DriverHistoryDtoExample.Criteria criteria = example.or();
      
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
        
            List<DriverHistoryDto> selected = dao.selectByExample(example);
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

    protected DriverHistoryEntity load(DriverHistoryDto dto) {
        DriverHistoryEntity entity = newEntity(dto);   
        return entity;
    }

    protected boolean checkCreated(DriverHistoryDto dto){ return false; }
    
    @Override
    public DriverHistoryEntity get(DriverHistoryDto dto) {
        DriverHistoryEntity entity = newEntity(dao.select(dto.getCdate(),dto.getDriverId()));
        return entity;
    }
}


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
import com.lge.sm.cr_data_store.repository.UrlRepository;
import com.lge.sm.cr_data_store.dao.UrlDao;
import com.lge.sm.cr_data_store.entity.DriverEntity;
import com.lge.sm.cr_data_store.repository.DriverRepository;

import com.lge.sm.cr_data_store.entity.UrlEntity;
import com.lge.sm.cr_data_store.dto.UrlDto;
import com.lge.sm.cr_data_store.dto.UrlDtoExample;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.lge.sm.cr_data_store.repository.SkinRepository;
import com.lge.sm.cr_data_store.entity.SkinEntity;

abstract public class AUrlRepository extends CacheableRepository<UrlEntity, UrlDao, UrlDto, UrlDtoExample>{
    @SuppressWarnings("unchecked")  
    private KindredEventPublisher<LoadEvent<UrlEntity>> loadPublisher = (KindredEventPublisher<LoadEvent<UrlEntity>>)EventBroker.getPublisher(UrlEntity.class, LoadEvent.class);
    @SuppressWarnings("unchecked")  
    private KindredEventPublisher<CreateEvent<UrlEntity>> createPublisher = (KindredEventPublisher<CreateEvent<UrlEntity>>)EventBroker.getPublisher(UrlEntity.class, CreateEvent.class);
    @SuppressWarnings("unchecked")
    private KindredEventPublisher<UpdateEvent<UrlEntity>> updatePublisher = (KindredEventPublisher<UpdateEvent<UrlEntity>>)EventBroker.getPublisher(UrlEntity.class, UpdateEvent.class);
    @SuppressWarnings("unchecked")
    private KindredEventPublisher<DeleteEvent<UrlEntity>> deletePublisher = (KindredEventPublisher<DeleteEvent<UrlEntity>>)EventBroker.getPublisher(UrlEntity.class, DeleteEvent.class);

    @Autowired
    public AUrlRepository(UrlDao dao) {
        super(dao);
    }
    


    @Override
    public UrlEntity create(UrlDto dto) throws IllegalArgumentException {
        if(checkCreated(dto) == true) throw new IllegalArgumentException("Already created : " + ToString.toLine(dto));    
        if(checkForeignKeyEntityExist(dto) == false) throw new IllegalArgumentException("No record of foreign key when create : " + ToString.toLine(dto));
        if(dao.insert(dto) == false) throw new IllegalArgumentException();
            
        UrlEntity ret = load(dto);
        createPublisher.publish(new CreateEvent<UrlEntity>(cloneOf(ret)));
        
        return ret;
    }
  
    protected boolean checkForeignKeyEntityExist(UrlDto dto) {
		if(Repos.repo(DriverRepository.class).getByMapKey(DriverEntity.newMapKey(dto.getDriverId())) == null) return false;

        return true;
    }
        
    public UrlEntity cloneOf(UrlEntity entity) {
        UrlEntity ret = newEntity(entity.getDto());

        return ret;
    }
  
    protected UrlEntity newEntity(UrlDto dto){
        return new UrlEntity(dto);
    }
    
    @Transactional
    @Override
    protected boolean deleteDao(List<UrlEntity> entities) {
        super.deleteDao(entities);
 
        return dao.delete(Repos.repo(UrlRepository.class).getDtoList(entities));
    }
   
    @Override
    protected boolean initDescendent() {


		parentSkinTypes.add(DriverEntity.skinType());

        return true;
    }
    
    public UrlDtoExample example(){
        return new UrlDtoExample();
    }
    
    private UrlDtoExample criteriaFactory = new UrlDtoExample();
    public UrlDtoExample.Criteria criteria(){
        return criteriaFactory.createCriteria();
    }
    
    public List<UrlEntity> getByCriteria(UrlDtoExample.Criteria c){
        UrlDtoExample newExample = new UrlDtoExample();
        newExample.or(c);
        List<UrlDto> dtos = dao.selectByExample(newExample);
        List<UrlEntity> entities = new ArrayList<>();
        if(dtos == null) return entities;
        for(UrlDto each : dtos) entities.add(load(each));
      
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
            UrlDto dto = om.treeToValue(inputNode, UrlDto.class);
            UrlEntity entity = get(dto);
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
  
    public String skinized(UrlEntity entity) {
        return skinize(entity);
    }
    
    protected String skinize(UrlEntity entity) {
        return skinize(entity.getDto());
    }
  
    protected String skinize(UrlDto dto) {
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
        return UrlEntity.skinType();
    }

    @Override
    public Skin skin() {
        SkinEntity entity = Repos.repo(SkinRepository.class).getByMapKey(SkinEntity.newMapKey(skinType()));
        return entity.skin(getParentSkinTypes(), getKidSkinTypes());
    }
    
    protected UrlDto jsonNodeToDto(JsonNode node) {
        ObjectMapper om = JsonUtil.objectMapper();
        try {
            UrlDto dto = om.treeToValue(node, UrlDto.class);
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
        UrlDto dto = jsonNodeToDto(inputNode);
        if(dto == null) return "";
        UrlEntity entity = create(dto);
        if(entity != null) return skinized(entity);
        return "";
    }
    
    public String update(JsonNode inputNode) {
        UrlDto dto = jsonNodeToDto(inputNode);
        if(dto == null) return "";
        UrlEntity entity = get(dto);
        if(entity != null){
          boolean ret = update(newEntity(dto));
          if(ret) return skinized(get(dto));
        }
        return "";
    }
    
    public boolean delete(JsonNode inputNode) {
        UrlDto dto = jsonNodeToDto(inputNode);
        if(dto == null) return false;
        UrlEntity entity = get(dto);
        return delete(entity);
    }
    
    public String getSkinizedKids(JsonNode node, String kidSkinType) {
        UrlDto dto = jsonNodeToDto(node);
        if(dto == null) return "";
        UrlEntity entity = get(dto);

        
        return "";
    }
    
    @Override
    public String read(Map<String, String> from, Map<String, String> to, Map<String, List<String>> maps, int limit) {
        List<UrlDto> dtos = new ArrayList<>();
        Object[] keySet = maps.keySet().toArray();
        int idx[] = new int[keySet.length];
            
        while(true){
            UrlDtoExample example = example();
            example.setOrderByClause("cdate desc limit " + limit);
            UrlDtoExample.Criteria criteria = example.or();
      
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
        
            List<UrlDto> selected = dao.selectByExample(example);
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
        for(UrlEntity entity : getAll()) {
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


	protected MapSet<UrlEntity> driverMapSet = new MapSet<>();

	public List<UrlEntity> getByDriverId(Long driverId) {
		return driverMapSet.get(DriverEntity.newMapKey(driverId));
	}

    @Override
    public UrlEntity get(UrlDto dto) {
        return map.get(UrlEntity.newMapKey(dto));
    }
       
    protected boolean checkCreated(UrlDto dto){
        if(map.containsKey(UrlEntity.newMapKey(dto))) return true;
        return false;
    }
    
    protected UrlEntity load(UrlDto dto) {
        UrlEntity entity = newEntity(dto);
        map.put(entity.mapKey(), entity);    

		driverMapSet.put(DriverEntity.newMapKey(entity.getDriverId()), entity);
        
        return entity;
    }
    
    protected void loaded(UrlEntity entity){
        loadPublisher.publish(new LoadEvent<UrlEntity>(cloneOf(entity)));
    }
        
    @Override
    protected void updated(List<UrlEntity> entities) {
        for(UrlEntity entity : entities) updatePublisher.publish(new UpdateEvent<UrlEntity>(cloneOf(entity)));
    }
  
    @Override
    protected void daoDeleted(List<UrlEntity> entities) {
        super.daoDeleted(entities);
        for(UrlEntity entity : entities) deletePublisher.publish(new DeleteEvent<UrlEntity>(cloneOf(entity)));

    }
    
    @Override
    protected List<UrlDto> dtoListToStart() {
        return dao.selectAll();
    }
}
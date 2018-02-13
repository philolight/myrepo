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
import com.lge.sm.cr_data_store.repository.PointAttributeRepository;
import com.lge.sm.cr_data_store.dao.PointAttributeDao;
import com.lge.sm.cr_data_store.entity.PointEntity;
import com.lge.sm.cr_data_store.repository.PointRepository;

import com.lge.sm.cr_data_store.entity.PointAttributeEntity;
import com.lge.sm.cr_data_store.dto.PointAttributeDto;
import com.lge.sm.cr_data_store.dto.PointAttributeDtoExample;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.lge.sm.cr_data_store.repository.SkinRepository;
import com.lge.sm.cr_data_store.entity.SkinEntity;

abstract public class APointAttributeRepository extends CacheableRepository<PointAttributeEntity, PointAttributeDao, PointAttributeDto, PointAttributeDtoExample>{
    @SuppressWarnings("unchecked")  
    private KindredEventPublisher<LoadEvent<PointAttributeEntity>> loadPublisher = (KindredEventPublisher<LoadEvent<PointAttributeEntity>>)EventBroker.getPublisher(PointAttributeEntity.class, LoadEvent.class);
    @SuppressWarnings("unchecked")  
    private KindredEventPublisher<CreateEvent<PointAttributeEntity>> createPublisher = (KindredEventPublisher<CreateEvent<PointAttributeEntity>>)EventBroker.getPublisher(PointAttributeEntity.class, CreateEvent.class);
    @SuppressWarnings("unchecked")
    private KindredEventPublisher<UpdateEvent<PointAttributeEntity>> updatePublisher = (KindredEventPublisher<UpdateEvent<PointAttributeEntity>>)EventBroker.getPublisher(PointAttributeEntity.class, UpdateEvent.class);
    @SuppressWarnings("unchecked")
    private KindredEventPublisher<DeleteEvent<PointAttributeEntity>> deletePublisher = (KindredEventPublisher<DeleteEvent<PointAttributeEntity>>)EventBroker.getPublisher(PointAttributeEntity.class, DeleteEvent.class);

    @Autowired
    public APointAttributeRepository(PointAttributeDao dao) {
        super(dao);
    }
    

	protected volatile long lastId = -1;
	public long getNextId() {
		return ++lastId;
	}

    @Override
    public PointAttributeEntity create(PointAttributeDto dto) throws IllegalArgumentException {
        if(checkCreated(dto) == true) throw new IllegalArgumentException("Already created : " + ToString.toLine(dto));    
        if(checkForeignKeyEntityExist(dto) == false) throw new IllegalArgumentException("No record of foreign key when create : " + ToString.toLine(dto));
        if(dao.insert(dto) == false) throw new IllegalArgumentException();
            
        PointAttributeEntity ret = load(dto);
        createPublisher.publish(new CreateEvent<PointAttributeEntity>(cloneOf(ret)));
        
        return ret;
    }
  
    protected boolean checkForeignKeyEntityExist(PointAttributeDto dto) {
		if(Repos.repo(PointRepository.class).getByMapKey(PointEntity.newMapKey(dto.getPointId())) == null) return false;

        return true;
    }
        
    public PointAttributeEntity cloneOf(PointAttributeEntity entity) {
        PointAttributeEntity ret = newEntity(entity.getDto());

        return ret;
    }
  
    protected PointAttributeEntity newEntity(PointAttributeDto dto){
        return new PointAttributeEntity(dto);
    }
    
    @Transactional
    @Override
    protected boolean deleteDao(List<PointAttributeEntity> entities) {
        super.deleteDao(entities);
 
        return dao.delete(Repos.repo(PointAttributeRepository.class).getDtoList(entities));
    }
   
    @Override
    protected boolean initDescendent() {

		lastId = dao.getLastId();

		parentSkinTypes.add(PointEntity.skinType());

        return true;
    }
    
    public PointAttributeDtoExample example(){
        return new PointAttributeDtoExample();
    }
    
    private PointAttributeDtoExample criteriaFactory = new PointAttributeDtoExample();
    public PointAttributeDtoExample.Criteria criteria(){
        return criteriaFactory.createCriteria();
    }
    
    public List<PointAttributeEntity> getByCriteria(PointAttributeDtoExample.Criteria c){
        PointAttributeDtoExample newExample = new PointAttributeDtoExample();
        newExample.or(c);
        List<PointAttributeDto> dtos = dao.selectByExample(newExample);
        List<PointAttributeEntity> entities = new ArrayList<>();
        if(dtos == null) return entities;
        for(PointAttributeDto each : dtos) entities.add(load(each));
      
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
            PointAttributeDto dto = om.treeToValue(inputNode, PointAttributeDto.class);
            PointAttributeEntity entity = get(dto);
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
  
    public String skinized(PointAttributeEntity entity) {
        return skinize(entity);
    }
    
    protected String skinize(PointAttributeEntity entity) {
        return skinize(entity.getDto());
    }
  
    protected String skinize(PointAttributeDto dto) {
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
        return PointAttributeEntity.skinType();
    }

    @Override
    public Skin skin() {
        SkinEntity entity = Repos.repo(SkinRepository.class).getByMapKey(SkinEntity.newMapKey(skinType()));
        return entity.skin(getParentSkinTypes(), getKidSkinTypes());
    }
    
    protected PointAttributeDto jsonNodeToDto(JsonNode node) {
        ObjectMapper om = JsonUtil.objectMapper();
        try {
            PointAttributeDto dto = om.treeToValue(node, PointAttributeDto.class);
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
        PointAttributeDto dto = jsonNodeToDto(inputNode);
        if(dto == null) return "";
        PointAttributeEntity entity = create(dto);
        if(entity != null) return skinized(entity);
        return "";
    }
    
    public String update(JsonNode inputNode) {
        PointAttributeDto dto = jsonNodeToDto(inputNode);
        if(dto == null) return "";
        PointAttributeEntity entity = get(dto);
        if(entity != null){
          boolean ret = update(newEntity(dto));
          if(ret) return skinized(get(dto));
        }
        return "";
    }
    
    public boolean delete(JsonNode inputNode) {
        PointAttributeDto dto = jsonNodeToDto(inputNode);
        if(dto == null) return false;
        PointAttributeEntity entity = get(dto);
        return delete(entity);
    }
    
    public String getSkinizedKids(JsonNode node, String kidSkinType) {
        PointAttributeDto dto = jsonNodeToDto(node);
        if(dto == null) return "";
        PointAttributeEntity entity = get(dto);

        
        return "";
    }
    
    @Override
    public String read(Map<String, String> from, Map<String, String> to, Map<String, List<String>> maps, int limit) {
        List<PointAttributeDto> dtos = new ArrayList<>();
        Object[] keySet = maps.keySet().toArray();
        int idx[] = new int[keySet.length];
            
        while(true){
            PointAttributeDtoExample example = example();
            example.setOrderByClause("cdate desc limit " + limit);
            PointAttributeDtoExample.Criteria criteria = example.or();
      
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
        
            List<PointAttributeDto> selected = dao.selectByExample(example);
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
        for(PointAttributeEntity entity : getAll()) {
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


	protected MapSet<PointAttributeEntity> pointMapSet = new MapSet<>();

	public List<PointAttributeEntity> getByPointId(Long pointId) {
		return pointMapSet.get(PointEntity.newMapKey(pointId));
	}

    @Override
    public PointAttributeEntity get(PointAttributeDto dto) {
        return map.get(PointAttributeEntity.newMapKey(dto));
    }
       
    protected boolean checkCreated(PointAttributeDto dto){
        if(map.containsKey(PointAttributeEntity.newMapKey(dto))) return true;
        return false;
    }
    
    protected PointAttributeEntity load(PointAttributeDto dto) {
        PointAttributeEntity entity = newEntity(dto);
        map.put(entity.mapKey(), entity);    

		pointMapSet.put(PointEntity.newMapKey(entity.getPointId()), entity);
        
        return entity;
    }
    
    protected void loaded(PointAttributeEntity entity){
        loadPublisher.publish(new LoadEvent<PointAttributeEntity>(cloneOf(entity)));
    }
        
    @Override
    protected void updated(List<PointAttributeEntity> entities) {
        for(PointAttributeEntity entity : entities) updatePublisher.publish(new UpdateEvent<PointAttributeEntity>(cloneOf(entity)));
    }
  
    @Override
    protected void daoDeleted(List<PointAttributeEntity> entities) {
        super.daoDeleted(entities);
        for(PointAttributeEntity entity : entities) deletePublisher.publish(new DeleteEvent<PointAttributeEntity>(cloneOf(entity)));

    }
    
    @Override
    protected List<PointAttributeDto> dtoListToStart() {
        return dao.selectAll();
    }
}
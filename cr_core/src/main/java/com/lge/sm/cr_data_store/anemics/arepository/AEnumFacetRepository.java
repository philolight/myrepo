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
import com.lge.sm.cr_data_store.repository.EnumFacetRepository;
import com.lge.sm.cr_data_store.dao.EnumFacetDao;
import com.lge.sm.cr_data_store.entity.FieldSkinEntity;
import com.lge.sm.cr_data_store.repository.FieldSkinRepository;

import com.lge.sm.cr_data_store.entity.EnumFacetEntity;
import com.lge.sm.cr_data_store.dto.EnumFacetDto;
import com.lge.sm.cr_data_store.dto.EnumFacetDtoExample;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.lge.sm.cr_data_store.repository.SkinRepository;
import com.lge.sm.cr_data_store.entity.SkinEntity;

abstract public class AEnumFacetRepository extends CacheableRepository<EnumFacetEntity, EnumFacetDao, EnumFacetDto, EnumFacetDtoExample>{
    @SuppressWarnings("unchecked")  
    private KindredEventPublisher<LoadEvent<EnumFacetEntity>> loadPublisher = (KindredEventPublisher<LoadEvent<EnumFacetEntity>>)EventBroker.getPublisher(EnumFacetEntity.class, LoadEvent.class);
    @SuppressWarnings("unchecked")  
    private KindredEventPublisher<CreateEvent<EnumFacetEntity>> createPublisher = (KindredEventPublisher<CreateEvent<EnumFacetEntity>>)EventBroker.getPublisher(EnumFacetEntity.class, CreateEvent.class);
    @SuppressWarnings("unchecked")
    private KindredEventPublisher<UpdateEvent<EnumFacetEntity>> updatePublisher = (KindredEventPublisher<UpdateEvent<EnumFacetEntity>>)EventBroker.getPublisher(EnumFacetEntity.class, UpdateEvent.class);
    @SuppressWarnings("unchecked")
    private KindredEventPublisher<DeleteEvent<EnumFacetEntity>> deletePublisher = (KindredEventPublisher<DeleteEvent<EnumFacetEntity>>)EventBroker.getPublisher(EnumFacetEntity.class, DeleteEvent.class);

    @Autowired
    public AEnumFacetRepository(EnumFacetDao dao) {
        super(dao);
    }
    

	protected volatile long lastId = -1;
	public long getNextId() {
		return ++lastId;
	}

    @Override
    public EnumFacetEntity create(EnumFacetDto dto) throws IllegalArgumentException {
        if(checkCreated(dto) == true) throw new IllegalArgumentException("Already created : " + ToString.toLine(dto));    
        if(checkForeignKeyEntityExist(dto) == false) throw new IllegalArgumentException("No record of foreign key when create : " + ToString.toLine(dto));
        if(dao.insert(dto) == false) throw new IllegalArgumentException();
            
        EnumFacetEntity ret = load(dto);
        createPublisher.publish(new CreateEvent<EnumFacetEntity>(cloneOf(ret)));
        
        return ret;
    }
  
    protected boolean checkForeignKeyEntityExist(EnumFacetDto dto) {
		if(Repos.repo(FieldSkinRepository.class).getByMapKey(FieldSkinEntity.newMapKey(dto.getFieldSkinId())) == null) return false;

        return true;
    }
        
    public EnumFacetEntity cloneOf(EnumFacetEntity entity) {
        EnumFacetEntity ret = newEntity(entity.getDto());

        return ret;
    }
  
    protected EnumFacetEntity newEntity(EnumFacetDto dto){
        return new EnumFacetEntity(dto);
    }
    
    @Transactional
    @Override
    protected boolean deleteDao(List<EnumFacetEntity> entities) {
        super.deleteDao(entities);
 
        return dao.delete(Repos.repo(EnumFacetRepository.class).getDtoList(entities));
    }
   
    @Override
    protected boolean initDescendent() {

		lastId = dao.getLastId();

		parentSkinTypes.add(FieldSkinEntity.skinType());

        return true;
    }
    
    public EnumFacetDtoExample example(){
        return new EnumFacetDtoExample();
    }
    
    private EnumFacetDtoExample criteriaFactory = new EnumFacetDtoExample();
    public EnumFacetDtoExample.Criteria criteria(){
        return criteriaFactory.createCriteria();
    }
    
    public List<EnumFacetEntity> getByCriteria(EnumFacetDtoExample.Criteria c){
        EnumFacetDtoExample newExample = new EnumFacetDtoExample();
        newExample.or(c);
        List<EnumFacetDto> dtos = dao.selectByExample(newExample);
        List<EnumFacetEntity> entities = new ArrayList<>();
        if(dtos == null) return entities;
        for(EnumFacetDto each : dtos) entities.add(load(each));
      
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
            EnumFacetDto dto = om.treeToValue(inputNode, EnumFacetDto.class);
            EnumFacetEntity entity = get(dto);
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
  
    public String skinized(EnumFacetEntity entity) {
        return skinize(entity);
    }
    
    protected String skinize(EnumFacetEntity entity) {
        return skinize(entity.getDto());
    }
  
    protected String skinize(EnumFacetDto dto) {
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
        return EnumFacetEntity.skinType();
    }

    @Override
    public Skin skin() {
        SkinEntity entity = Repos.repo(SkinRepository.class).getByMapKey(SkinEntity.newMapKey(skinType()));
        return entity.skin(getParentSkinTypes(), getKidSkinTypes());
    }
    
    protected EnumFacetDto jsonNodeToDto(JsonNode node) {
        ObjectMapper om = JsonUtil.objectMapper();
        try {
            EnumFacetDto dto = om.treeToValue(node, EnumFacetDto.class);
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
        EnumFacetDto dto = jsonNodeToDto(inputNode);
        if(dto == null) return "";
        EnumFacetEntity entity = create(dto);
        if(entity != null) return skinized(entity);
        return "";
    }
    
    public String update(JsonNode inputNode) {
        EnumFacetDto dto = jsonNodeToDto(inputNode);
        if(dto == null) return "";
        EnumFacetEntity entity = get(dto);
        if(entity != null){
          boolean ret = update(newEntity(dto));
          if(ret) return skinized(get(dto));
        }
        return "";
    }
    
    public boolean delete(JsonNode inputNode) {
        EnumFacetDto dto = jsonNodeToDto(inputNode);
        if(dto == null) return false;
        EnumFacetEntity entity = get(dto);
        return delete(entity);
    }
    
    public String getSkinizedKids(JsonNode node, String kidSkinType) {
        EnumFacetDto dto = jsonNodeToDto(node);
        if(dto == null) return "";
        EnumFacetEntity entity = get(dto);

        
        return "";
    }
    
    @Override
    public String read(Map<String, String> from, Map<String, String> to, Map<String, List<String>> maps, int limit) {
        List<EnumFacetDto> dtos = new ArrayList<>();
        Object[] keySet = maps.keySet().toArray();
        int idx[] = new int[keySet.length];
            
        while(true){
            EnumFacetDtoExample example = example();
            example.setOrderByClause("cdate desc limit " + limit);
            EnumFacetDtoExample.Criteria criteria = example.or();
      
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
        
            List<EnumFacetDto> selected = dao.selectByExample(example);
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
        for(EnumFacetEntity entity : getAll()) {
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


	protected MapSet<EnumFacetEntity> fieldSkinMapSet = new MapSet<>();

	public List<EnumFacetEntity> getByFieldSkinId(String fieldSkinId) {
		return fieldSkinMapSet.get(FieldSkinEntity.newMapKey(fieldSkinId));
	}

    @Override
    public EnumFacetEntity get(EnumFacetDto dto) {
        return map.get(EnumFacetEntity.newMapKey(dto));
    }
       
    protected boolean checkCreated(EnumFacetDto dto){
        if(map.containsKey(EnumFacetEntity.newMapKey(dto))) return true;
        return false;
    }
    
    protected EnumFacetEntity load(EnumFacetDto dto) {
        EnumFacetEntity entity = newEntity(dto);
        map.put(entity.mapKey(), entity);    

		fieldSkinMapSet.put(FieldSkinEntity.newMapKey(entity.getFieldSkinId()), entity);
        
        return entity;
    }
    
    protected void loaded(EnumFacetEntity entity){
        loadPublisher.publish(new LoadEvent<EnumFacetEntity>(cloneOf(entity)));
    }
        
    @Override
    protected void updated(List<EnumFacetEntity> entities) {
        for(EnumFacetEntity entity : entities) updatePublisher.publish(new UpdateEvent<EnumFacetEntity>(cloneOf(entity)));
    }
  
    @Override
    protected void daoDeleted(List<EnumFacetEntity> entities) {
        super.daoDeleted(entities);
        for(EnumFacetEntity entity : entities) deletePublisher.publish(new DeleteEvent<EnumFacetEntity>(cloneOf(entity)));

    }
    
    @Override
    protected List<EnumFacetDto> dtoListToStart() {
        return dao.selectAll();
    }
}
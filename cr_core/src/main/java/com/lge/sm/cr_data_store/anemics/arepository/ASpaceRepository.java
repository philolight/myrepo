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
import com.lge.sm.cr_data_store.repository.SpaceRepository;
import com.lge.sm.cr_data_store.dao.SpaceDao;
import com.lge.sm.cr_data_store.entity.SpaceEntity;
import com.lge.sm.cr_data_store.repository.SpaceRepository;
import com.lge.sm.cr_data_store.entity.SpaceEntity;
import com.lge.sm.cr_data_store.repository.SpaceRepository;

import com.lge.sm.cr_data_store.entity.SpaceEntity;
import com.lge.sm.cr_data_store.dto.SpaceDto;
import com.lge.sm.cr_data_store.dto.SpaceDtoExample;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.lge.sm.cr_data_store.repository.SkinRepository;
import com.lge.sm.cr_data_store.entity.SkinEntity;

abstract public class ASpaceRepository extends CacheableRepository<SpaceEntity, SpaceDao, SpaceDto, SpaceDtoExample>{
    @SuppressWarnings("unchecked")  
    private KindredEventPublisher<LoadEvent<SpaceEntity>> loadPublisher = (KindredEventPublisher<LoadEvent<SpaceEntity>>)EventBroker.getPublisher(SpaceEntity.class, LoadEvent.class);
    @SuppressWarnings("unchecked")  
    private KindredEventPublisher<CreateEvent<SpaceEntity>> createPublisher = (KindredEventPublisher<CreateEvent<SpaceEntity>>)EventBroker.getPublisher(SpaceEntity.class, CreateEvent.class);
    @SuppressWarnings("unchecked")
    private KindredEventPublisher<UpdateEvent<SpaceEntity>> updatePublisher = (KindredEventPublisher<UpdateEvent<SpaceEntity>>)EventBroker.getPublisher(SpaceEntity.class, UpdateEvent.class);
    @SuppressWarnings("unchecked")
    private KindredEventPublisher<DeleteEvent<SpaceEntity>> deletePublisher = (KindredEventPublisher<DeleteEvent<SpaceEntity>>)EventBroker.getPublisher(SpaceEntity.class, DeleteEvent.class);

    @Autowired
    public ASpaceRepository(SpaceDao dao) {
        super(dao);
    }
    

	protected volatile long lastId = -1;
	public long getNextId() {
		return ++lastId;
	}

    @Override
    public SpaceEntity create(SpaceDto dto) throws IllegalArgumentException {
    	dto.setCdate(DateStringUtil.getCurrentDateString(DateStringUtil.gmtTimeZoneId));
		dto.setSpaceId(getNextId());
        if(checkCreated(dto) == true) throw new IllegalArgumentException("Already created : " + ToString.toLine(dto));    
        if(checkForeignKeyEntityExist(dto) == false) throw new IllegalArgumentException("No record of foreign key when create : " + ToString.toLine(dto));
        if(dao.insert(dto) == false) throw new IllegalArgumentException();
            
        SpaceEntity ret = load(dto);
        createPublisher.publish(new CreateEvent<SpaceEntity>(cloneOf(ret)));
        
        return ret;
    }
  
    protected boolean checkForeignKeyEntityExist(SpaceDto dto) {
		if(dto.getParentSpaceId() != null && Repos.repo(SpaceRepository.class).getByMapKey(SpaceEntity.newMapKey(dto.getParentSpaceId())) == null) return false;

        return true;
    }
        
    public SpaceEntity cloneOf(SpaceEntity entity) {
        SpaceEntity ret = newEntity(entity.getDto());

        return ret;
    }
  
    protected SpaceEntity newEntity(SpaceDto dto){
        return new SpaceEntity(dto);
    }
    
    @Transactional
    @Override
    protected boolean deleteDao(List<SpaceEntity> entities) {
        super.deleteDao(entities);
		List<SpaceEntity> spaceList = new ArrayList<>();
		for(SpaceEntity each : entities) spaceList.addAll(Repos.repo(SpaceRepository.class).getBySpaceId(each.getSpaceId()));
		if(spaceList.size() != 0) {
			if(Repos.repo(SpaceRepository.class).delete(spaceList) == false) return false;
		}
 
        return dao.delete(Repos.repo(SpaceRepository.class).getDtoList(entities));
    }
   
    @Override
    protected boolean initDescendent() {

		lastId = dao.getLastId();

		parentSkinTypes.add(SpaceEntity.skinType());

		kidSkinTypes.add(SpaceEntity.skinType());
        return true;
    }
    
    public SpaceDtoExample example(){
        return new SpaceDtoExample();
    }
    
    private SpaceDtoExample criteriaFactory = new SpaceDtoExample();
    public SpaceDtoExample.Criteria criteria(){
        return criteriaFactory.createCriteria();
    }
    
    public List<SpaceEntity> getByCriteria(SpaceDtoExample.Criteria c){
        SpaceDtoExample newExample = new SpaceDtoExample();
        newExample.or(c);
        List<SpaceDto> dtos = dao.selectByExample(newExample);
        List<SpaceEntity> entities = new ArrayList<>();
        if(dtos == null) return entities;
        for(SpaceDto each : dtos) entities.add(load(each));
      
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
            SpaceDto dto = om.treeToValue(inputNode, SpaceDto.class);
            SpaceEntity entity = get(dto);
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
  
    public String skinized(SpaceEntity entity) {
        return skinize(entity);
    }
    
    protected String skinize(SpaceEntity entity) {
        return skinize(entity.getDto());
    }
  
    protected String skinize(SpaceDto dto) {
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
        return SpaceEntity.skinType();
    }

    @Override
    public Skin skin() {
        SkinEntity entity = Repos.repo(SkinRepository.class).getByMapKey(SkinEntity.newMapKey(skinType()));
        return entity.skin(getParentSkinTypes(), getKidSkinTypes());
    }
    
    protected SpaceDto jsonNodeToDto(JsonNode node) {
        ObjectMapper om = JsonUtil.objectMapper();
        try {
            SpaceDto dto = om.treeToValue(node, SpaceDto.class);
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
    	List<SpaceDto> dtoList = new ArrayList<>();
		for(JsonNode each : nodeList) {
	        SpaceDto dto = jsonNodeToDto(each);
	        if(dto == null) return "";
	        dtoList.add(dto);
		}
		
    	List<SpaceEntity> entityList = new ArrayList<>();
		for(SpaceDto dto : dtoList) {
	        SpaceEntity entity = create(dto);
	        if(entity == null) Logger.error(TAG, "Failed to create : " + ToString.toLine(dto));
	        else entityList.add(entity);
		}
		
        StringBuffer ret = new StringBuffer();
        ret.append("[");
        for(int i = 0; i < entityList.size(); i++) {
        	SpaceEntity entity = entityList.get(i);
            ret.append(skinize(entity));
            if(i != entityList.size() - 1) ret.append(",");
        }
        ret.append("]");
        
        return ret.toString();
    }
    
    public String update(JsonNode nodeList) {
    	List<SpaceDto> dtoList = new ArrayList<>();
		for(JsonNode each : nodeList) {
	        SpaceDto dto = jsonNodeToDto(each);
	        System.out.println(ToString.toLine(dto));
	        if(dto == null) return "";
	        dtoList.add(dto);
		}
		
    	List<SpaceEntity> entityList = new ArrayList<>();
		for(SpaceDto dto : dtoList) {
	        SpaceEntity entity = newEntity(dto);
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
        	SpaceEntity entity = entityList.get(i);
            ret.append(skinize(entity));
            if(i != entityList.size() - 1) ret.append(",");
        }
        ret.append("]");
        
        return ret.toString();
    }
    
    public String delete(JsonNode nodeList) {
    	List<SpaceDto> dtoList = new ArrayList<>();
		for(JsonNode each : nodeList) {
	        SpaceDto dto = jsonNodeToDto(each);
	        System.out.println(ToString.toLine(dto));
	        if(dto == null) return "";
	        dtoList.add(dto);
		}
		
    	List<SpaceEntity> entityList = new ArrayList<>();
		for(SpaceDto dto : dtoList) {
	        SpaceEntity entity = get(dto);
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
        	SpaceEntity entity = entityList.get(i);
            ret.append(skinize(entity));
            if(i != entityList.size() - 1) ret.append(",");
        }
        ret.append("]");
        
        return ret.toString();
    }
    
    public String getSkinizedKids(JsonNode node, String kidSkinType) {
        SpaceDto dto = jsonNodeToDto(node);
        if(dto == null) return "";
        SpaceEntity entity = get(dto);
        if(entity == null) return "";

		if(kidSkinType.equals("Space")) {
			List<SpaceEntity> list = entity.getSpaceEntityList();
			SpaceRepository repo = Repos.repo(SpaceRepository.class);
			StringBuffer ret = new StringBuffer();
			ret.append("[");
			for(int i = 0; i < list.size(); i++) {
				SpaceEntity each = list.get(i);
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
        List<SpaceDto> dtos = new ArrayList<>();
        Object[] keySet = maps.keySet().toArray();
        int idx[] = new int[keySet.length];
            
        while(true){
            SpaceDtoExample example = example();
            example.setOrderByClause("cdate desc limit " + limit);
            SpaceDtoExample.Criteria criteria = example.or();
      
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
        
            List<SpaceDto> selected = dao.selectByExample(example);
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
        for(SpaceEntity entity : getAll()) {
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


	protected MapSet<SpaceEntity> spaceMapSet = new MapSet<>();

	public List<SpaceEntity> getBySpaceId(Long parentSpaceId) {
		return spaceMapSet.get(SpaceEntity.newMapKey(parentSpaceId));
	}

    @Override
    public SpaceEntity get(SpaceDto dto) {
        return map.get(SpaceEntity.newMapKey(dto));
    }
       
    protected boolean checkCreated(SpaceDto dto){
        if(map.containsKey(SpaceEntity.newMapKey(dto))) return true;
        return false;
    }
    
    protected SpaceEntity load(SpaceDto dto) {
        SpaceEntity entity = newEntity(dto);
        map.put(entity.mapKey(), entity);    

		spaceMapSet.put(SpaceEntity.newMapKey(entity.getParentSpaceId()), entity);
        
        return entity;
    }
    
    protected void loaded(SpaceEntity entity){
        loadPublisher.publish(new LoadEvent<SpaceEntity>(cloneOf(entity)));
    }
        
    @Override
    protected void updated(List<SpaceEntity> entities) {
        for(SpaceEntity entity : entities) updatePublisher.publish(new UpdateEvent<SpaceEntity>(cloneOf(entity)));
    }
  
    @Override
    protected void daoDeleted(List<SpaceEntity> entities) {
        super.daoDeleted(entities);
        for(SpaceEntity entity : entities) deletePublisher.publish(new DeleteEvent<SpaceEntity>(cloneOf(entity)));
		for(SpaceEntity each : entities) spaceMapSet.remove(SpaceEntity.newMapKey(each.getSpaceId()), each);
    }
    
    @Override
    protected List<SpaceDto> dtoListToStart() {
        return dao.selectAll();
    }
}
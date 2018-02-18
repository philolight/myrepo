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
import com.lge.sm.cr_data_store.repository.SkinRepository;
import com.lge.sm.cr_data_store.dao.SkinDao;
import com.lge.sm.cr_data_store.entity.FieldSkinEntity;
import com.lge.sm.cr_data_store.repository.FieldSkinRepository;

import com.lge.sm.cr_data_store.entity.SkinEntity;
import com.lge.sm.cr_data_store.dto.SkinDto;
import com.lge.sm.cr_data_store.dto.SkinDtoExample;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.lge.sm.cr_data_store.repository.SkinRepository;
import com.lge.sm.cr_data_store.entity.SkinEntity;

abstract public class ASkinRepository extends CacheableRepository<SkinEntity, SkinDao, SkinDto, SkinDtoExample>{
    @SuppressWarnings("unchecked")  
    private KindredEventPublisher<LoadEvent<SkinEntity>> loadPublisher = (KindredEventPublisher<LoadEvent<SkinEntity>>)EventBroker.getPublisher(SkinEntity.class, LoadEvent.class);
    @SuppressWarnings("unchecked")  
    private KindredEventPublisher<CreateEvent<SkinEntity>> createPublisher = (KindredEventPublisher<CreateEvent<SkinEntity>>)EventBroker.getPublisher(SkinEntity.class, CreateEvent.class);
    @SuppressWarnings("unchecked")
    private KindredEventPublisher<UpdateEvent<SkinEntity>> updatePublisher = (KindredEventPublisher<UpdateEvent<SkinEntity>>)EventBroker.getPublisher(SkinEntity.class, UpdateEvent.class);
    @SuppressWarnings("unchecked")
    private KindredEventPublisher<DeleteEvent<SkinEntity>> deletePublisher = (KindredEventPublisher<DeleteEvent<SkinEntity>>)EventBroker.getPublisher(SkinEntity.class, DeleteEvent.class);

    @Autowired
    public ASkinRepository(SkinDao dao) {
        super(dao);
    }
    


    @Override
    public SkinEntity create(SkinDto dto) throws IllegalArgumentException {
    	dto.setCdate(DateStringUtil.getCurrentDateString(DateStringUtil.gmtTimeZoneId));

        if(checkCreated(dto) == true) throw new IllegalArgumentException("Already created : " + ToString.toLine(dto));    
        if(checkForeignKeyEntityExist(dto) == false) throw new IllegalArgumentException("No record of foreign key when create : " + ToString.toLine(dto));
        if(dao.insert(dto) == false) throw new IllegalArgumentException();
            
        SkinEntity ret = load(dto);
        createPublisher.publish(new CreateEvent<SkinEntity>(cloneOf(ret)));
        
        return ret;
    }
  
    protected boolean checkForeignKeyEntityExist(SkinDto dto) {

        return true;
    }
        
    public SkinEntity cloneOf(SkinEntity entity) {
        SkinEntity ret = newEntity(entity.getDto());

        return ret;
    }
  
    protected SkinEntity newEntity(SkinDto dto){
        return new SkinEntity(dto);
    }
    
    @Transactional
    @Override
    protected boolean deleteDao(List<SkinEntity> entities) {
        super.deleteDao(entities);
		List<FieldSkinEntity> fieldSkinList = new ArrayList<>();
		for(SkinEntity each : entities) fieldSkinList.addAll(Repos.repo(FieldSkinRepository.class).getBySkinId(each.getSkinId()));
		if(fieldSkinList.size() != 0) {
			if(Repos.repo(FieldSkinRepository.class).delete(fieldSkinList) == false) return false;
		}
 
        return dao.delete(Repos.repo(SkinRepository.class).getDtoList(entities));
    }
   
    @Override
    protected boolean initDescendent() {



		kidSkinTypes.add(FieldSkinEntity.skinType());
        return true;
    }
    
    public SkinDtoExample example(){
        return new SkinDtoExample();
    }
    
    private SkinDtoExample criteriaFactory = new SkinDtoExample();
    public SkinDtoExample.Criteria criteria(){
        return criteriaFactory.createCriteria();
    }
    
    public List<SkinEntity> getByCriteria(SkinDtoExample.Criteria c){
        SkinDtoExample newExample = new SkinDtoExample();
        newExample.or(c);
        List<SkinDto> dtos = dao.selectByExample(newExample);
        List<SkinEntity> entities = new ArrayList<>();
        if(dtos == null) return entities;
        for(SkinDto each : dtos) entities.add(load(each));
      
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
            SkinDto dto = om.treeToValue(inputNode, SkinDto.class);
            SkinEntity entity = get(dto);
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
  
    public String skinized(SkinEntity entity) {
        return skinize(entity);
    }
    
    protected String skinize(SkinEntity entity) {
        return skinize(entity.getDto());
    }
  
    protected String skinize(SkinDto dto) {
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
        return SkinEntity.skinType();
    }

    @Override
    public Skin skin() {
        SkinEntity entity = Repos.repo(SkinRepository.class).getByMapKey(SkinEntity.newMapKey(skinType()));
        return entity.skin(getParentSkinTypes(), getKidSkinTypes());
    }
    
    protected SkinDto jsonNodeToDto(JsonNode node) {
        ObjectMapper om = JsonUtil.objectMapper();
        try {
            SkinDto dto = om.treeToValue(node, SkinDto.class);
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
    	List<SkinDto> dtoList = new ArrayList<>();
		for(JsonNode each : nodeList) {
	        SkinDto dto = jsonNodeToDto(each);
	        if(dto == null) return "";
	        dtoList.add(dto);
		}
		
    	List<SkinEntity> entityList = new ArrayList<>();
		for(SkinDto dto : dtoList) {
	        SkinEntity entity = create(dto);
	        if(entity == null) Logger.error(TAG, "Failed to create : " + ToString.toLine(dto));
	        else entityList.add(entity);
		}
		
        StringBuffer ret = new StringBuffer();
        ret.append("[");
        for(int i = 0; i < entityList.size(); i++) {
        	SkinEntity entity = entityList.get(i);
            ret.append(skinize(entity));
            if(i != entityList.size() - 1) ret.append(",");
        }
        ret.append("]");
        
        return ret.toString();
    }
    
    public String update(JsonNode nodeList) {
    	List<SkinDto> dtoList = new ArrayList<>();
		for(JsonNode each : nodeList) {
	        SkinDto dto = jsonNodeToDto(each);
	        System.out.println(ToString.toLine(dto));
	        if(dto == null) return "";
	        dtoList.add(dto);
		}
		
    	List<SkinEntity> entityList = new ArrayList<>();
		for(SkinDto dto : dtoList) {
	        SkinEntity entity = newEntity(dto);
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
        	SkinEntity entity = entityList.get(i);
            ret.append(skinize(entity));
            if(i != entityList.size() - 1) ret.append(",");
        }
        ret.append("]");
        
        return ret.toString();
    }
    
    public String delete(JsonNode nodeList) {
    	List<SkinDto> dtoList = new ArrayList<>();
		for(JsonNode each : nodeList) {
	        SkinDto dto = jsonNodeToDto(each);
	        System.out.println(ToString.toLine(dto));
	        if(dto == null) return "";
	        dtoList.add(dto);
		}
		
    	List<SkinEntity> entityList = new ArrayList<>();
		for(SkinDto dto : dtoList) {
	        SkinEntity entity = get(dto);
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
        	SkinEntity entity = entityList.get(i);
            ret.append(skinize(entity));
            if(i != entityList.size() - 1) ret.append(",");
        }
        ret.append("]");
        
        return ret.toString();
    }
    
    public String getSkinizedKids(JsonNode node, String kidSkinType) {
        SkinDto dto = jsonNodeToDto(node);
        if(dto == null) return "";
        SkinEntity entity = get(dto);
        if(entity == null) return "";

		if(kidSkinType.equals("FieldSkin")) {
			List<FieldSkinEntity> list = entity.getFieldSkinEntityList();
			FieldSkinRepository repo = Repos.repo(FieldSkinRepository.class);
			StringBuffer ret = new StringBuffer();
			ret.append("[");
			for(int i = 0; i < list.size(); i++) {
				FieldSkinEntity each = list.get(i);
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
        List<SkinDto> dtos = new ArrayList<>();
        Object[] keySet = maps.keySet().toArray();
        int idx[] = new int[keySet.length];
            
        while(true){
            SkinDtoExample example = example();
            example.setOrderByClause("cdate desc limit " + limit);
            SkinDtoExample.Criteria criteria = example.or();
      
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
        
            List<SkinDto> selected = dao.selectByExample(example);
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
        for(SkinEntity entity : getAll()) {
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
    public SkinEntity get(SkinDto dto) {
        return map.get(SkinEntity.newMapKey(dto));
    }
       
    protected boolean checkCreated(SkinDto dto){
        if(map.containsKey(SkinEntity.newMapKey(dto))) return true;
        return false;
    }
    
    protected SkinEntity load(SkinDto dto) {
        SkinEntity entity = newEntity(dto);
        map.put(entity.mapKey(), entity);    

        
        return entity;
    }
    
    protected void loaded(SkinEntity entity){
        loadPublisher.publish(new LoadEvent<SkinEntity>(cloneOf(entity)));
    }
        
    @Override
    protected void updated(List<SkinEntity> entities) {
        for(SkinEntity entity : entities) updatePublisher.publish(new UpdateEvent<SkinEntity>(cloneOf(entity)));
    }
  
    @Override
    protected void daoDeleted(List<SkinEntity> entities) {
        super.daoDeleted(entities);
        for(SkinEntity entity : entities) deletePublisher.publish(new DeleteEvent<SkinEntity>(cloneOf(entity)));

    }
    
    @Override
    protected List<SkinDto> dtoListToStart() {
        return dao.selectAll();
    }
}
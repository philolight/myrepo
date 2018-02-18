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
import com.lge.sm.cr_data_store.repository.UserAuthorityRepository;
import com.lge.sm.cr_data_store.dao.UserAuthorityDao;
import com.lge.sm.cr_data_store.entity.UserEntity;
import com.lge.sm.cr_data_store.repository.UserRepository;
import com.lge.sm.cr_data_store.entity.AuthorityEntity;
import com.lge.sm.cr_data_store.repository.AuthorityRepository;

import com.lge.sm.cr_data_store.entity.UserAuthorityEntity;
import com.lge.sm.cr_data_store.dto.UserAuthorityDto;
import com.lge.sm.cr_data_store.dto.UserAuthorityDtoExample;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.lge.sm.cr_data_store.repository.SkinRepository;
import com.lge.sm.cr_data_store.entity.SkinEntity;

abstract public class AUserAuthorityRepository extends CacheableRepository<UserAuthorityEntity, UserAuthorityDao, UserAuthorityDto, UserAuthorityDtoExample>{
    @SuppressWarnings("unchecked")  
    private KindredEventPublisher<LoadEvent<UserAuthorityEntity>> loadPublisher = (KindredEventPublisher<LoadEvent<UserAuthorityEntity>>)EventBroker.getPublisher(UserAuthorityEntity.class, LoadEvent.class);
    @SuppressWarnings("unchecked")  
    private KindredEventPublisher<CreateEvent<UserAuthorityEntity>> createPublisher = (KindredEventPublisher<CreateEvent<UserAuthorityEntity>>)EventBroker.getPublisher(UserAuthorityEntity.class, CreateEvent.class);
    @SuppressWarnings("unchecked")
    private KindredEventPublisher<UpdateEvent<UserAuthorityEntity>> updatePublisher = (KindredEventPublisher<UpdateEvent<UserAuthorityEntity>>)EventBroker.getPublisher(UserAuthorityEntity.class, UpdateEvent.class);
    @SuppressWarnings("unchecked")
    private KindredEventPublisher<DeleteEvent<UserAuthorityEntity>> deletePublisher = (KindredEventPublisher<DeleteEvent<UserAuthorityEntity>>)EventBroker.getPublisher(UserAuthorityEntity.class, DeleteEvent.class);

    @Autowired
    public AUserAuthorityRepository(UserAuthorityDao dao) {
        super(dao);
    }
    


    @Override
    public UserAuthorityEntity create(UserAuthorityDto dto) throws IllegalArgumentException {
    	dto.setCdate(DateStringUtil.getCurrentDateString(DateStringUtil.gmtTimeZoneId));

        if(checkCreated(dto) == true) throw new IllegalArgumentException("Already created : " + ToString.toLine(dto));    
        if(checkForeignKeyEntityExist(dto) == false) throw new IllegalArgumentException("No record of foreign key when create : " + ToString.toLine(dto));
        if(dao.insert(dto) == false) throw new IllegalArgumentException();
            
        UserAuthorityEntity ret = load(dto);
        createPublisher.publish(new CreateEvent<UserAuthorityEntity>(cloneOf(ret)));
        
        return ret;
    }
  
    protected boolean checkForeignKeyEntityExist(UserAuthorityDto dto) {
		if(dto.getUserId() != null && Repos.repo(UserRepository.class).getByMapKey(UserEntity.newMapKey(dto.getUserId())) == null) return false;
		if(dto.getAuthorityId() != null && Repos.repo(AuthorityRepository.class).getByMapKey(AuthorityEntity.newMapKey(dto.getAuthorityId())) == null) return false;

        return true;
    }
        
    public UserAuthorityEntity cloneOf(UserAuthorityEntity entity) {
        UserAuthorityEntity ret = newEntity(entity.getDto());

        return ret;
    }
  
    protected UserAuthorityEntity newEntity(UserAuthorityDto dto){
        return new UserAuthorityEntity(dto);
    }
    
    @Transactional
    @Override
    protected boolean deleteDao(List<UserAuthorityEntity> entities) {
        super.deleteDao(entities);
 
        return dao.delete(Repos.repo(UserAuthorityRepository.class).getDtoList(entities));
    }
   
    @Override
    protected boolean initDescendent() {


		parentSkinTypes.add(UserEntity.skinType());
		parentSkinTypes.add(AuthorityEntity.skinType());

        return true;
    }
    
    public UserAuthorityDtoExample example(){
        return new UserAuthorityDtoExample();
    }
    
    private UserAuthorityDtoExample criteriaFactory = new UserAuthorityDtoExample();
    public UserAuthorityDtoExample.Criteria criteria(){
        return criteriaFactory.createCriteria();
    }
    
    public List<UserAuthorityEntity> getByCriteria(UserAuthorityDtoExample.Criteria c){
        UserAuthorityDtoExample newExample = new UserAuthorityDtoExample();
        newExample.or(c);
        List<UserAuthorityDto> dtos = dao.selectByExample(newExample);
        List<UserAuthorityEntity> entities = new ArrayList<>();
        if(dtos == null) return entities;
        for(UserAuthorityDto each : dtos) entities.add(load(each));
      
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
            UserAuthorityDto dto = om.treeToValue(inputNode, UserAuthorityDto.class);
            UserAuthorityEntity entity = get(dto);
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
  
    public String skinized(UserAuthorityEntity entity) {
        return skinize(entity);
    }
    
    protected String skinize(UserAuthorityEntity entity) {
        return skinize(entity.getDto());
    }
  
    protected String skinize(UserAuthorityDto dto) {
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
        return UserAuthorityEntity.skinType();
    }

    @Override
    public Skin skin() {
        SkinEntity entity = Repos.repo(SkinRepository.class).getByMapKey(SkinEntity.newMapKey(skinType()));
        return entity.skin(getParentSkinTypes(), getKidSkinTypes());
    }
    
    protected UserAuthorityDto jsonNodeToDto(JsonNode node) {
        ObjectMapper om = JsonUtil.objectMapper();
        try {
            UserAuthorityDto dto = om.treeToValue(node, UserAuthorityDto.class);
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
    	List<UserAuthorityDto> dtoList = new ArrayList<>();
		for(JsonNode each : nodeList) {
	        UserAuthorityDto dto = jsonNodeToDto(each);
	        if(dto == null) return "";
	        dtoList.add(dto);
		}
		
    	List<UserAuthorityEntity> entityList = new ArrayList<>();
		for(UserAuthorityDto dto : dtoList) {
	        UserAuthorityEntity entity = create(dto);
	        if(entity == null) Logger.error(TAG, "Failed to create : " + ToString.toLine(dto));
	        else entityList.add(entity);
		}
		
        StringBuffer ret = new StringBuffer();
        ret.append("[");
        for(int i = 0; i < entityList.size(); i++) {
        	UserAuthorityEntity entity = entityList.get(i);
            ret.append(skinize(entity));
            if(i != entityList.size() - 1) ret.append(",");
        }
        ret.append("]");
        
        return ret.toString();
    }
    
    public String update(JsonNode nodeList) {
    	List<UserAuthorityDto> dtoList = new ArrayList<>();
		for(JsonNode each : nodeList) {
	        UserAuthorityDto dto = jsonNodeToDto(each);
	        System.out.println(ToString.toLine(dto));
	        if(dto == null) return "";
	        dtoList.add(dto);
		}
		
    	List<UserAuthorityEntity> entityList = new ArrayList<>();
		for(UserAuthorityDto dto : dtoList) {
	        UserAuthorityEntity entity = newEntity(dto);
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
        	UserAuthorityEntity entity = entityList.get(i);
            ret.append(skinize(entity));
            if(i != entityList.size() - 1) ret.append(",");
        }
        ret.append("]");
        
        return ret.toString();
    }
    
    public String delete(JsonNode nodeList) {
    	List<UserAuthorityDto> dtoList = new ArrayList<>();
		for(JsonNode each : nodeList) {
	        UserAuthorityDto dto = jsonNodeToDto(each);
	        System.out.println(ToString.toLine(dto));
	        if(dto == null) return "";
	        dtoList.add(dto);
		}
		
    	List<UserAuthorityEntity> entityList = new ArrayList<>();
		for(UserAuthorityDto dto : dtoList) {
	        UserAuthorityEntity entity = get(dto);
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
        	UserAuthorityEntity entity = entityList.get(i);
            ret.append(skinize(entity));
            if(i != entityList.size() - 1) ret.append(",");
        }
        ret.append("]");
        
        return ret.toString();
    }
    
    public String getSkinizedKids(JsonNode node, String kidSkinType) {
        UserAuthorityDto dto = jsonNodeToDto(node);
        if(dto == null) return "";
        UserAuthorityEntity entity = get(dto);
        if(entity == null) return "";

        
        return "";
    }
    
    @Override
    public String read(Map<String, String> from, Map<String, String> to, Map<String, List<String>> maps, int limit) {
        List<UserAuthorityDto> dtos = new ArrayList<>();
        Object[] keySet = maps.keySet().toArray();
        int idx[] = new int[keySet.length];
            
        while(true){
            UserAuthorityDtoExample example = example();
            example.setOrderByClause("cdate desc limit " + limit);
            UserAuthorityDtoExample.Criteria criteria = example.or();
      
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
        
            List<UserAuthorityDto> selected = dao.selectByExample(example);
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
        for(UserAuthorityEntity entity : getAll()) {
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


	protected MapSet<UserAuthorityEntity> userMapSet = new MapSet<>();
	protected MapSet<UserAuthorityEntity> authorityMapSet = new MapSet<>();

	public List<UserAuthorityEntity> getByUserId(String userId) {
		return userMapSet.get(UserEntity.newMapKey(userId));
	}
	public List<UserAuthorityEntity> getByAuthorityId(Long authorityId) {
		return authorityMapSet.get(AuthorityEntity.newMapKey(authorityId));
	}

    @Override
    public UserAuthorityEntity get(UserAuthorityDto dto) {
        return map.get(UserAuthorityEntity.newMapKey(dto));
    }
       
    protected boolean checkCreated(UserAuthorityDto dto){
        if(map.containsKey(UserAuthorityEntity.newMapKey(dto))) return true;
        return false;
    }
    
    protected UserAuthorityEntity load(UserAuthorityDto dto) {
        UserAuthorityEntity entity = newEntity(dto);
        map.put(entity.mapKey(), entity);    

		userMapSet.put(UserEntity.newMapKey(entity.getUserId()), entity);
		authorityMapSet.put(AuthorityEntity.newMapKey(entity.getAuthorityId()), entity);
        
        return entity;
    }
    
    protected void loaded(UserAuthorityEntity entity){
        loadPublisher.publish(new LoadEvent<UserAuthorityEntity>(cloneOf(entity)));
    }
        
    @Override
    protected void updated(List<UserAuthorityEntity> entities) {
        for(UserAuthorityEntity entity : entities) updatePublisher.publish(new UpdateEvent<UserAuthorityEntity>(cloneOf(entity)));
    }
  
    @Override
    protected void daoDeleted(List<UserAuthorityEntity> entities) {
        super.daoDeleted(entities);
        for(UserAuthorityEntity entity : entities) deletePublisher.publish(new DeleteEvent<UserAuthorityEntity>(cloneOf(entity)));
		for(UserAuthorityEntity each : entities) userMapSet.remove(UserEntity.newMapKey(each.getUserId()), each);		for(UserAuthorityEntity each : entities) authorityMapSet.remove(AuthorityEntity.newMapKey(each.getAuthorityId()), each);
    }
    
    @Override
    protected List<UserAuthorityDto> dtoListToStart() {
        return dao.selectAll();
    }
}
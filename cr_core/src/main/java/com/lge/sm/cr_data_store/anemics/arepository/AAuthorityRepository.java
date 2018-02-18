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
import com.lge.sm.cr_data_store.repository.AuthorityRepository;
import com.lge.sm.cr_data_store.dao.AuthorityDao;
import com.lge.sm.cr_data_store.entity.PartyAuthorityEntity;
import com.lge.sm.cr_data_store.repository.PartyAuthorityRepository;
import com.lge.sm.cr_data_store.entity.ServiceAuthorityEntity;
import com.lge.sm.cr_data_store.repository.ServiceAuthorityRepository;
import com.lge.sm.cr_data_store.entity.AuthorityLocationEntity;
import com.lge.sm.cr_data_store.repository.AuthorityLocationRepository;
import com.lge.sm.cr_data_store.entity.UserAuthorityEntity;
import com.lge.sm.cr_data_store.repository.UserAuthorityRepository;

import com.lge.sm.cr_data_store.entity.AuthorityEntity;
import com.lge.sm.cr_data_store.dto.AuthorityDto;
import com.lge.sm.cr_data_store.dto.AuthorityDtoExample;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.lge.sm.cr_data_store.repository.SkinRepository;
import com.lge.sm.cr_data_store.entity.SkinEntity;

abstract public class AAuthorityRepository extends CacheableRepository<AuthorityEntity, AuthorityDao, AuthorityDto, AuthorityDtoExample>{
    @SuppressWarnings("unchecked")  
    private KindredEventPublisher<LoadEvent<AuthorityEntity>> loadPublisher = (KindredEventPublisher<LoadEvent<AuthorityEntity>>)EventBroker.getPublisher(AuthorityEntity.class, LoadEvent.class);
    @SuppressWarnings("unchecked")  
    private KindredEventPublisher<CreateEvent<AuthorityEntity>> createPublisher = (KindredEventPublisher<CreateEvent<AuthorityEntity>>)EventBroker.getPublisher(AuthorityEntity.class, CreateEvent.class);
    @SuppressWarnings("unchecked")
    private KindredEventPublisher<UpdateEvent<AuthorityEntity>> updatePublisher = (KindredEventPublisher<UpdateEvent<AuthorityEntity>>)EventBroker.getPublisher(AuthorityEntity.class, UpdateEvent.class);
    @SuppressWarnings("unchecked")
    private KindredEventPublisher<DeleteEvent<AuthorityEntity>> deletePublisher = (KindredEventPublisher<DeleteEvent<AuthorityEntity>>)EventBroker.getPublisher(AuthorityEntity.class, DeleteEvent.class);

    @Autowired
    public AAuthorityRepository(AuthorityDao dao) {
        super(dao);
    }
    

	protected volatile long lastId = -1;
	public long getNextId() {
		return ++lastId;
	}

    @Override
    public AuthorityEntity create(AuthorityDto dto) throws IllegalArgumentException {
    	dto.setCdate(DateStringUtil.getCurrentDateString(DateStringUtil.gmtTimeZoneId));
		dto.setAuthorityId(getNextId());
        if(checkCreated(dto) == true) throw new IllegalArgumentException("Already created : " + ToString.toLine(dto));    
        if(checkForeignKeyEntityExist(dto) == false) throw new IllegalArgumentException("No record of foreign key when create : " + ToString.toLine(dto));
        if(dao.insert(dto) == false) throw new IllegalArgumentException();
            
        AuthorityEntity ret = load(dto);
        createPublisher.publish(new CreateEvent<AuthorityEntity>(cloneOf(ret)));
        
        return ret;
    }
  
    protected boolean checkForeignKeyEntityExist(AuthorityDto dto) {

        return true;
    }
        
    public AuthorityEntity cloneOf(AuthorityEntity entity) {
        AuthorityEntity ret = newEntity(entity.getDto());

        return ret;
    }
  
    protected AuthorityEntity newEntity(AuthorityDto dto){
        return new AuthorityEntity(dto);
    }
    
    @Transactional
    @Override
    protected boolean deleteDao(List<AuthorityEntity> entities) {
        super.deleteDao(entities);
		List<PartyAuthorityEntity> partyAuthorityList = new ArrayList<>();
		for(AuthorityEntity each : entities) partyAuthorityList.addAll(Repos.repo(PartyAuthorityRepository.class).getByAuthorityId(each.getAuthorityId()));
		if(partyAuthorityList.size() != 0) {
			if(Repos.repo(PartyAuthorityRepository.class).delete(partyAuthorityList) == false) return false;
		}
		List<ServiceAuthorityEntity> serviceAuthorityList = new ArrayList<>();
		for(AuthorityEntity each : entities) serviceAuthorityList.addAll(Repos.repo(ServiceAuthorityRepository.class).getByAuthorityId(each.getAuthorityId()));
		if(serviceAuthorityList.size() != 0) {
			if(Repos.repo(ServiceAuthorityRepository.class).delete(serviceAuthorityList) == false) return false;
		}
		List<AuthorityLocationEntity> authorityLocationList = new ArrayList<>();
		for(AuthorityEntity each : entities) authorityLocationList.addAll(Repos.repo(AuthorityLocationRepository.class).getByAuthorityId(each.getAuthorityId()));
		if(authorityLocationList.size() != 0) {
			if(Repos.repo(AuthorityLocationRepository.class).delete(authorityLocationList) == false) return false;
		}
		List<UserAuthorityEntity> userAuthorityList = new ArrayList<>();
		for(AuthorityEntity each : entities) userAuthorityList.addAll(Repos.repo(UserAuthorityRepository.class).getByAuthorityId(each.getAuthorityId()));
		if(userAuthorityList.size() != 0) {
			if(Repos.repo(UserAuthorityRepository.class).delete(userAuthorityList) == false) return false;
		}
 
        return dao.delete(Repos.repo(AuthorityRepository.class).getDtoList(entities));
    }
   
    @Override
    protected boolean initDescendent() {

		lastId = dao.getLastId();


		kidSkinTypes.add(PartyAuthorityEntity.skinType());
		kidSkinTypes.add(ServiceAuthorityEntity.skinType());
		kidSkinTypes.add(AuthorityLocationEntity.skinType());
		kidSkinTypes.add(UserAuthorityEntity.skinType());
        return true;
    }
    
    public AuthorityDtoExample example(){
        return new AuthorityDtoExample();
    }
    
    private AuthorityDtoExample criteriaFactory = new AuthorityDtoExample();
    public AuthorityDtoExample.Criteria criteria(){
        return criteriaFactory.createCriteria();
    }
    
    public List<AuthorityEntity> getByCriteria(AuthorityDtoExample.Criteria c){
        AuthorityDtoExample newExample = new AuthorityDtoExample();
        newExample.or(c);
        List<AuthorityDto> dtos = dao.selectByExample(newExample);
        List<AuthorityEntity> entities = new ArrayList<>();
        if(dtos == null) return entities;
        for(AuthorityDto each : dtos) entities.add(load(each));
      
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
            AuthorityDto dto = om.treeToValue(inputNode, AuthorityDto.class);
            AuthorityEntity entity = get(dto);
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
  
    public String skinized(AuthorityEntity entity) {
        return skinize(entity);
    }
    
    protected String skinize(AuthorityEntity entity) {
        return skinize(entity.getDto());
    }
  
    protected String skinize(AuthorityDto dto) {
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
        return AuthorityEntity.skinType();
    }

    @Override
    public Skin skin() {
        SkinEntity entity = Repos.repo(SkinRepository.class).getByMapKey(SkinEntity.newMapKey(skinType()));
        return entity.skin(getParentSkinTypes(), getKidSkinTypes());
    }
    
    protected AuthorityDto jsonNodeToDto(JsonNode node) {
        ObjectMapper om = JsonUtil.objectMapper();
        try {
            AuthorityDto dto = om.treeToValue(node, AuthorityDto.class);
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
    	List<AuthorityDto> dtoList = new ArrayList<>();
		for(JsonNode each : nodeList) {
	        AuthorityDto dto = jsonNodeToDto(each);
	        if(dto == null) return "";
	        dtoList.add(dto);
		}
		
    	List<AuthorityEntity> entityList = new ArrayList<>();
		for(AuthorityDto dto : dtoList) {
	        AuthorityEntity entity = create(dto);
	        if(entity == null) Logger.error(TAG, "Failed to create : " + ToString.toLine(dto));
	        else entityList.add(entity);
		}
		
        StringBuffer ret = new StringBuffer();
        ret.append("[");
        for(int i = 0; i < entityList.size(); i++) {
        	AuthorityEntity entity = entityList.get(i);
            ret.append(skinize(entity));
            if(i != entityList.size() - 1) ret.append(",");
        }
        ret.append("]");
        
        return ret.toString();
    }
    
    public String update(JsonNode nodeList) {
    	List<AuthorityDto> dtoList = new ArrayList<>();
		for(JsonNode each : nodeList) {
	        AuthorityDto dto = jsonNodeToDto(each);
	        System.out.println(ToString.toLine(dto));
	        if(dto == null) return "";
	        dtoList.add(dto);
		}
		
    	List<AuthorityEntity> entityList = new ArrayList<>();
		for(AuthorityDto dto : dtoList) {
	        AuthorityEntity entity = newEntity(dto);
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
        	AuthorityEntity entity = entityList.get(i);
            ret.append(skinize(entity));
            if(i != entityList.size() - 1) ret.append(",");
        }
        ret.append("]");
        
        return ret.toString();
    }
    
    public String delete(JsonNode nodeList) {
    	List<AuthorityDto> dtoList = new ArrayList<>();
		for(JsonNode each : nodeList) {
	        AuthorityDto dto = jsonNodeToDto(each);
	        System.out.println(ToString.toLine(dto));
	        if(dto == null) return "";
	        dtoList.add(dto);
		}
		
    	List<AuthorityEntity> entityList = new ArrayList<>();
		for(AuthorityDto dto : dtoList) {
	        AuthorityEntity entity = get(dto);
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
        	AuthorityEntity entity = entityList.get(i);
            ret.append(skinize(entity));
            if(i != entityList.size() - 1) ret.append(",");
        }
        ret.append("]");
        
        return ret.toString();
    }
    
    public String getSkinizedKids(JsonNode node, String kidSkinType) {
        AuthorityDto dto = jsonNodeToDto(node);
        if(dto == null) return "";
        AuthorityEntity entity = get(dto);
        if(entity == null) return "";

		if(kidSkinType.equals("PartyAuthority")) {
			List<PartyAuthorityEntity> list = entity.getPartyAuthorityEntityList();
			PartyAuthorityRepository repo = Repos.repo(PartyAuthorityRepository.class);
			StringBuffer ret = new StringBuffer();
			ret.append("[");
			for(int i = 0; i < list.size(); i++) {
				PartyAuthorityEntity each = list.get(i);
				ret.append(repo.skinized(each));
				if(i != list.size()-1) ret.append(",");
			}
			ret.append("]");
			return ret.toString();
		}
		if(kidSkinType.equals("ServiceAuthority")) {
			List<ServiceAuthorityEntity> list = entity.getServiceAuthorityEntityList();
			ServiceAuthorityRepository repo = Repos.repo(ServiceAuthorityRepository.class);
			StringBuffer ret = new StringBuffer();
			ret.append("[");
			for(int i = 0; i < list.size(); i++) {
				ServiceAuthorityEntity each = list.get(i);
				ret.append(repo.skinized(each));
				if(i != list.size()-1) ret.append(",");
			}
			ret.append("]");
			return ret.toString();
		}
		if(kidSkinType.equals("AuthorityLocation")) {
			List<AuthorityLocationEntity> list = entity.getAuthorityLocationEntityList();
			AuthorityLocationRepository repo = Repos.repo(AuthorityLocationRepository.class);
			StringBuffer ret = new StringBuffer();
			ret.append("[");
			for(int i = 0; i < list.size(); i++) {
				AuthorityLocationEntity each = list.get(i);
				ret.append(repo.skinized(each));
				if(i != list.size()-1) ret.append(",");
			}
			ret.append("]");
			return ret.toString();
		}
		if(kidSkinType.equals("UserAuthority")) {
			List<UserAuthorityEntity> list = entity.getUserAuthorityEntityList();
			UserAuthorityRepository repo = Repos.repo(UserAuthorityRepository.class);
			StringBuffer ret = new StringBuffer();
			ret.append("[");
			for(int i = 0; i < list.size(); i++) {
				UserAuthorityEntity each = list.get(i);
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
        List<AuthorityDto> dtos = new ArrayList<>();
        Object[] keySet = maps.keySet().toArray();
        int idx[] = new int[keySet.length];
            
        while(true){
            AuthorityDtoExample example = example();
            example.setOrderByClause("cdate desc limit " + limit);
            AuthorityDtoExample.Criteria criteria = example.or();
      
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
        
            List<AuthorityDto> selected = dao.selectByExample(example);
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
        for(AuthorityEntity entity : getAll()) {
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
    public AuthorityEntity get(AuthorityDto dto) {
        return map.get(AuthorityEntity.newMapKey(dto));
    }
       
    protected boolean checkCreated(AuthorityDto dto){
        if(map.containsKey(AuthorityEntity.newMapKey(dto))) return true;
        return false;
    }
    
    protected AuthorityEntity load(AuthorityDto dto) {
        AuthorityEntity entity = newEntity(dto);
        map.put(entity.mapKey(), entity);    

        
        return entity;
    }
    
    protected void loaded(AuthorityEntity entity){
        loadPublisher.publish(new LoadEvent<AuthorityEntity>(cloneOf(entity)));
    }
        
    @Override
    protected void updated(List<AuthorityEntity> entities) {
        for(AuthorityEntity entity : entities) updatePublisher.publish(new UpdateEvent<AuthorityEntity>(cloneOf(entity)));
    }
  
    @Override
    protected void daoDeleted(List<AuthorityEntity> entities) {
        super.daoDeleted(entities);
        for(AuthorityEntity entity : entities) deletePublisher.publish(new DeleteEvent<AuthorityEntity>(cloneOf(entity)));

    }
    
    @Override
    protected List<AuthorityDto> dtoListToStart() {
        return dao.selectAll();
    }
}
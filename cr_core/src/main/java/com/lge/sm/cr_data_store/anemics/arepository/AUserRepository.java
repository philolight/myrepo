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
import com.lge.sm.cr_data_store.repository.UserRepository;
import com.lge.sm.cr_data_store.dao.UserDao;
import com.lge.sm.cr_data_store.entity.PartyUserEntity;
import com.lge.sm.cr_data_store.repository.PartyUserRepository;
import com.lge.sm.cr_data_store.entity.UserAuthorityEntity;
import com.lge.sm.cr_data_store.repository.UserAuthorityRepository;
import com.lge.sm.cr_data_store.entity.PersonEntity;
import com.lge.sm.cr_data_store.repository.PersonRepository;

import com.lge.sm.cr_data_store.entity.UserEntity;
import com.lge.sm.cr_data_store.dto.UserDto;
import com.lge.sm.cr_data_store.dto.UserDtoExample;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.lge.sm.cr_data_store.repository.SkinRepository;
import com.lge.sm.cr_data_store.entity.SkinEntity;

abstract public class AUserRepository extends CacheableRepository<UserEntity, UserDao, UserDto, UserDtoExample>{
    @SuppressWarnings("unchecked")  
    private KindredEventPublisher<LoadEvent<UserEntity>> loadPublisher = (KindredEventPublisher<LoadEvent<UserEntity>>)EventBroker.getPublisher(UserEntity.class, LoadEvent.class);
    @SuppressWarnings("unchecked")  
    private KindredEventPublisher<CreateEvent<UserEntity>> createPublisher = (KindredEventPublisher<CreateEvent<UserEntity>>)EventBroker.getPublisher(UserEntity.class, CreateEvent.class);
    @SuppressWarnings("unchecked")
    private KindredEventPublisher<UpdateEvent<UserEntity>> updatePublisher = (KindredEventPublisher<UpdateEvent<UserEntity>>)EventBroker.getPublisher(UserEntity.class, UpdateEvent.class);
    @SuppressWarnings("unchecked")
    private KindredEventPublisher<DeleteEvent<UserEntity>> deletePublisher = (KindredEventPublisher<DeleteEvent<UserEntity>>)EventBroker.getPublisher(UserEntity.class, DeleteEvent.class);

    @Autowired
    public AUserRepository(UserDao dao) {
        super(dao);
    }
    


    @Override
    public UserEntity create(UserDto dto) throws IllegalArgumentException {
        if(checkCreated(dto) == true) throw new IllegalArgumentException("Already created : " + ToString.toLine(dto));    
        if(checkForeignKeyEntityExist(dto) == false) throw new IllegalArgumentException("No record of foreign key when create : " + ToString.toLine(dto));
        if(dao.insert(dto) == false) throw new IllegalArgumentException();
            
        UserEntity ret = load(dto);
        createPublisher.publish(new CreateEvent<UserEntity>(cloneOf(ret)));
        
        return ret;
    }
  
    protected boolean checkForeignKeyEntityExist(UserDto dto) {

        return true;
    }
        
    public UserEntity cloneOf(UserEntity entity) {
        UserEntity ret = newEntity(entity.getDto());

        return ret;
    }
  
    protected UserEntity newEntity(UserDto dto){
        return new UserEntity(dto);
    }
    
    @Transactional
    @Override
    protected boolean deleteDao(List<UserEntity> entities) {
        super.deleteDao(entities);
		List<PartyUserEntity> partyUserList = new ArrayList<>();
		for(UserEntity each : entities) partyUserList.addAll(Repos.repo(PartyUserRepository.class).getByUserId(each.getUserId()));
		if(Repos.repo(PartyUserRepository.class).delete(partyUserList) == false) return false;
		List<UserAuthorityEntity> userAuthorityList = new ArrayList<>();
		for(UserEntity each : entities) userAuthorityList.addAll(Repos.repo(UserAuthorityRepository.class).getByUserId(each.getUserId()));
		if(Repos.repo(UserAuthorityRepository.class).delete(userAuthorityList) == false) return false;
		List<PersonEntity> personList = new ArrayList<>();
		for(UserEntity each : entities) personList.addAll(Repos.repo(PersonRepository.class).getByUserId(each.getUserId()));
		if(Repos.repo(PersonRepository.class).delete(personList) == false) return false;
 
        return dao.delete(Repos.repo(UserRepository.class).getDtoList(entities));
    }
   
    @Override
    protected boolean initDescendent() {



		kidSkinTypes.add(PartyUserEntity.skinType());
		kidSkinTypes.add(UserAuthorityEntity.skinType());
		kidSkinTypes.add(PersonEntity.skinType());
        return true;
    }
    
    public UserDtoExample example(){
        return new UserDtoExample();
    }
    
    private UserDtoExample criteriaFactory = new UserDtoExample();
    public UserDtoExample.Criteria criteria(){
        return criteriaFactory.createCriteria();
    }
    
    public List<UserEntity> getByCriteria(UserDtoExample.Criteria c){
        UserDtoExample newExample = new UserDtoExample();
        newExample.or(c);
        List<UserDto> dtos = dao.selectByExample(newExample);
        List<UserEntity> entities = new ArrayList<>();
        if(dtos == null) return entities;
        for(UserDto each : dtos) entities.add(load(each));
      
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
            UserDto dto = om.treeToValue(inputNode, UserDto.class);
            UserEntity entity = get(dto);
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
  
    public String skinized(UserEntity entity) {
        return skinize(entity);
    }
    
    protected String skinize(UserEntity entity) {
        return skinize(entity.getDto());
    }
  
    protected String skinize(UserDto dto) {
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
        return UserEntity.skinType();
    }

    @Override
    public Skin skin() {
        SkinEntity entity = Repos.repo(SkinRepository.class).getByMapKey(SkinEntity.newMapKey(skinType()));
        return entity.skin(getParentSkinTypes(), getKidSkinTypes());
    }
    
    protected UserDto jsonNodeToDto(JsonNode node) {
        ObjectMapper om = JsonUtil.objectMapper();
        try {
            UserDto dto = om.treeToValue(node, UserDto.class);
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
        UserDto dto = jsonNodeToDto(inputNode);
        if(dto == null) return "";
        UserEntity entity = create(dto);
        if(entity != null) return skinized(entity);
        return "";
    }
    
    public String update(JsonNode inputNode) {
        UserDto dto = jsonNodeToDto(inputNode);
        if(dto == null) return "";
        UserEntity entity = get(dto);
        if(entity != null){
          boolean ret = update(newEntity(dto));
          if(ret) return skinized(get(dto));
        }
        return "";
    }
    
    public boolean delete(JsonNode inputNode) {
        UserDto dto = jsonNodeToDto(inputNode);
        if(dto == null) return false;
        UserEntity entity = get(dto);
        return delete(entity);
    }
    
    public String getSkinizedKids(JsonNode node, String kidSkinType) {
        UserDto dto = jsonNodeToDto(node);
        if(dto == null) return "";
        UserEntity entity = get(dto);

		if(kidSkinType.equals("PartyUser")) {
			List<PartyUserEntity> list = entity.getPartyUserEntityList();
			PartyUserRepository repo = Repos.repo(PartyUserRepository.class);
			StringBuffer ret = new StringBuffer();
			ret.append("[");
			for(int i = 0; i < list.size(); i++) {
				PartyUserEntity each = list.get(i);
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
		if(kidSkinType.equals("Person")) {
			List<PersonEntity> list = entity.getPersonEntityList();
			PersonRepository repo = Repos.repo(PersonRepository.class);
			StringBuffer ret = new StringBuffer();
			ret.append("[");
			for(int i = 0; i < list.size(); i++) {
				PersonEntity each = list.get(i);
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
        List<UserDto> dtos = new ArrayList<>();
        Object[] keySet = maps.keySet().toArray();
        int idx[] = new int[keySet.length];
            
        while(true){
            UserDtoExample example = example();
            example.setOrderByClause("cdate desc limit " + limit);
            UserDtoExample.Criteria criteria = example.or();
      
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
        
            List<UserDto> selected = dao.selectByExample(example);
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
        for(UserEntity entity : getAll()) {
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
    public UserEntity get(UserDto dto) {
        return map.get(UserEntity.newMapKey(dto));
    }
       
    protected boolean checkCreated(UserDto dto){
        if(map.containsKey(UserEntity.newMapKey(dto))) return true;
        return false;
    }
    
    protected UserEntity load(UserDto dto) {
        UserEntity entity = newEntity(dto);
        map.put(entity.mapKey(), entity);    

        
        return entity;
    }
    
    protected void loaded(UserEntity entity){
        loadPublisher.publish(new LoadEvent<UserEntity>(cloneOf(entity)));
    }
        
    @Override
    protected void updated(List<UserEntity> entities) {
        for(UserEntity entity : entities) updatePublisher.publish(new UpdateEvent<UserEntity>(cloneOf(entity)));
    }
  
    @Override
    protected void daoDeleted(List<UserEntity> entities) {
        super.daoDeleted(entities);
        for(UserEntity entity : entities) deletePublisher.publish(new DeleteEvent<UserEntity>(cloneOf(entity)));
		List<PartyUserEntity> partyUserList = new ArrayList<>();
		for(UserEntity each : entities) partyUserList.addAll(Repos.repo(PartyUserRepository.class).getByUserId(each.getUserId()));
		Repos.repo(PartyUserRepository.class).daoDeleted(partyUserList);
		List<UserAuthorityEntity> userAuthorityList = new ArrayList<>();
		for(UserEntity each : entities) userAuthorityList.addAll(Repos.repo(UserAuthorityRepository.class).getByUserId(each.getUserId()));
		Repos.repo(UserAuthorityRepository.class).daoDeleted(userAuthorityList);
		List<PersonEntity> personList = new ArrayList<>();
		for(UserEntity each : entities) personList.addAll(Repos.repo(PersonRepository.class).getByUserId(each.getUserId()));
		Repos.repo(PersonRepository.class).daoDeleted(personList);

    }
    
    @Override
    protected List<UserDto> dtoListToStart() {
        return dao.selectAll();
    }
}
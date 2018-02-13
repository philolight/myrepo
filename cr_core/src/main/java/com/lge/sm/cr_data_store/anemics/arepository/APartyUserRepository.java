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
import com.lge.sm.cr_data_store.repository.PartyUserRepository;
import com.lge.sm.cr_data_store.dao.PartyUserDao;
import com.lge.sm.cr_data_store.entity.UserEntity;
import com.lge.sm.cr_data_store.repository.UserRepository;
import com.lge.sm.cr_data_store.entity.PartyEntity;
import com.lge.sm.cr_data_store.repository.PartyRepository;

import com.lge.sm.cr_data_store.entity.PartyUserEntity;
import com.lge.sm.cr_data_store.dto.PartyUserDto;
import com.lge.sm.cr_data_store.dto.PartyUserDtoExample;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.lge.sm.cr_data_store.repository.SkinRepository;
import com.lge.sm.cr_data_store.entity.SkinEntity;

abstract public class APartyUserRepository extends CacheableRepository<PartyUserEntity, PartyUserDao, PartyUserDto, PartyUserDtoExample>{
    @SuppressWarnings("unchecked")  
    private KindredEventPublisher<LoadEvent<PartyUserEntity>> loadPublisher = (KindredEventPublisher<LoadEvent<PartyUserEntity>>)EventBroker.getPublisher(PartyUserEntity.class, LoadEvent.class);
    @SuppressWarnings("unchecked")  
    private KindredEventPublisher<CreateEvent<PartyUserEntity>> createPublisher = (KindredEventPublisher<CreateEvent<PartyUserEntity>>)EventBroker.getPublisher(PartyUserEntity.class, CreateEvent.class);
    @SuppressWarnings("unchecked")
    private KindredEventPublisher<UpdateEvent<PartyUserEntity>> updatePublisher = (KindredEventPublisher<UpdateEvent<PartyUserEntity>>)EventBroker.getPublisher(PartyUserEntity.class, UpdateEvent.class);
    @SuppressWarnings("unchecked")
    private KindredEventPublisher<DeleteEvent<PartyUserEntity>> deletePublisher = (KindredEventPublisher<DeleteEvent<PartyUserEntity>>)EventBroker.getPublisher(PartyUserEntity.class, DeleteEvent.class);

    @Autowired
    public APartyUserRepository(PartyUserDao dao) {
        super(dao);
    }
    


    @Override
    public PartyUserEntity create(PartyUserDto dto) throws IllegalArgumentException {
        if(checkCreated(dto) == true) throw new IllegalArgumentException("Already created : " + ToString.toLine(dto));    
        if(checkForeignKeyEntityExist(dto) == false) throw new IllegalArgumentException("No record of foreign key when create : " + ToString.toLine(dto));
        if(dao.insert(dto) == false) throw new IllegalArgumentException();
            
        PartyUserEntity ret = load(dto);
        createPublisher.publish(new CreateEvent<PartyUserEntity>(cloneOf(ret)));
        
        return ret;
    }
  
    protected boolean checkForeignKeyEntityExist(PartyUserDto dto) {
		if(Repos.repo(UserRepository.class).getByMapKey(UserEntity.newMapKey(dto.getUserId())) == null) return false;
		if(Repos.repo(PartyRepository.class).getByMapKey(PartyEntity.newMapKey(dto.getPartyId())) == null) return false;

        return true;
    }
        
    public PartyUserEntity cloneOf(PartyUserEntity entity) {
        PartyUserEntity ret = newEntity(entity.getDto());

        return ret;
    }
  
    protected PartyUserEntity newEntity(PartyUserDto dto){
        return new PartyUserEntity(dto);
    }
    
    @Transactional
    @Override
    protected boolean deleteDao(List<PartyUserEntity> entities) {
        super.deleteDao(entities);
 
        return dao.delete(Repos.repo(PartyUserRepository.class).getDtoList(entities));
    }
   
    @Override
    protected boolean initDescendent() {


		parentSkinTypes.add(UserEntity.skinType());
		parentSkinTypes.add(PartyEntity.skinType());

        return true;
    }
    
    public PartyUserDtoExample example(){
        return new PartyUserDtoExample();
    }
    
    private PartyUserDtoExample criteriaFactory = new PartyUserDtoExample();
    public PartyUserDtoExample.Criteria criteria(){
        return criteriaFactory.createCriteria();
    }
    
    public List<PartyUserEntity> getByCriteria(PartyUserDtoExample.Criteria c){
        PartyUserDtoExample newExample = new PartyUserDtoExample();
        newExample.or(c);
        List<PartyUserDto> dtos = dao.selectByExample(newExample);
        List<PartyUserEntity> entities = new ArrayList<>();
        if(dtos == null) return entities;
        for(PartyUserDto each : dtos) entities.add(load(each));
      
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
            PartyUserDto dto = om.treeToValue(inputNode, PartyUserDto.class);
            PartyUserEntity entity = get(dto);
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
  
    public String skinized(PartyUserEntity entity) {
        return skinize(entity);
    }
    
    protected String skinize(PartyUserEntity entity) {
        return skinize(entity.getDto());
    }
  
    protected String skinize(PartyUserDto dto) {
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
        return PartyUserEntity.skinType();
    }

    @Override
    public Skin skin() {
        SkinEntity entity = Repos.repo(SkinRepository.class).getByMapKey(SkinEntity.newMapKey(skinType()));
        return entity.skin(getParentSkinTypes(), getKidSkinTypes());
    }
    
    protected PartyUserDto jsonNodeToDto(JsonNode node) {
        ObjectMapper om = JsonUtil.objectMapper();
        try {
            PartyUserDto dto = om.treeToValue(node, PartyUserDto.class);
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
        PartyUserDto dto = jsonNodeToDto(inputNode);
        if(dto == null) return "";
        PartyUserEntity entity = create(dto);
        if(entity != null) return skinized(entity);
        return "";
    }
    
    public String update(JsonNode inputNode) {
        PartyUserDto dto = jsonNodeToDto(inputNode);
        if(dto == null) return "";
        PartyUserEntity entity = get(dto);
        if(entity != null){
          boolean ret = update(newEntity(dto));
          if(ret) return skinized(get(dto));
        }
        return "";
    }
    
    public boolean delete(JsonNode inputNode) {
        PartyUserDto dto = jsonNodeToDto(inputNode);
        if(dto == null) return false;
        PartyUserEntity entity = get(dto);
        return delete(entity);
    }
    
    public String getSkinizedKids(JsonNode node, String kidSkinType) {
        PartyUserDto dto = jsonNodeToDto(node);
        if(dto == null) return "";
        PartyUserEntity entity = get(dto);

        
        return "";
    }
    
    @Override
    public String read(Map<String, String> from, Map<String, String> to, Map<String, List<String>> maps, int limit) {
        List<PartyUserDto> dtos = new ArrayList<>();
        Object[] keySet = maps.keySet().toArray();
        int idx[] = new int[keySet.length];
            
        while(true){
            PartyUserDtoExample example = example();
            example.setOrderByClause("cdate desc limit " + limit);
            PartyUserDtoExample.Criteria criteria = example.or();
      
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
        
            List<PartyUserDto> selected = dao.selectByExample(example);
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
        for(PartyUserEntity entity : getAll()) {
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


	protected MapSet<PartyUserEntity> userMapSet = new MapSet<>();
	protected MapSet<PartyUserEntity> partyMapSet = new MapSet<>();

	public List<PartyUserEntity> getByUserId(String userId) {
		return userMapSet.get(UserEntity.newMapKey(userId));
	}
	public List<PartyUserEntity> getByPartyId(String partyId) {
		return partyMapSet.get(PartyEntity.newMapKey(partyId));
	}

    @Override
    public PartyUserEntity get(PartyUserDto dto) {
        return map.get(PartyUserEntity.newMapKey(dto));
    }
       
    protected boolean checkCreated(PartyUserDto dto){
        if(map.containsKey(PartyUserEntity.newMapKey(dto))) return true;
        return false;
    }
    
    protected PartyUserEntity load(PartyUserDto dto) {
        PartyUserEntity entity = newEntity(dto);
        map.put(entity.mapKey(), entity);    

		userMapSet.put(UserEntity.newMapKey(entity.getUserId()), entity);
		partyMapSet.put(PartyEntity.newMapKey(entity.getPartyId()), entity);
        
        return entity;
    }
    
    protected void loaded(PartyUserEntity entity){
        loadPublisher.publish(new LoadEvent<PartyUserEntity>(cloneOf(entity)));
    }
        
    @Override
    protected void updated(List<PartyUserEntity> entities) {
        for(PartyUserEntity entity : entities) updatePublisher.publish(new UpdateEvent<PartyUserEntity>(cloneOf(entity)));
    }
  
    @Override
    protected void daoDeleted(List<PartyUserEntity> entities) {
        super.daoDeleted(entities);
        for(PartyUserEntity entity : entities) deletePublisher.publish(new DeleteEvent<PartyUserEntity>(cloneOf(entity)));

    }
    
    @Override
    protected List<PartyUserDto> dtoListToStart() {
        return dao.selectAll();
    }
}
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
import com.lge.sm.cr_data_store.repository.PersonRepository;
import com.lge.sm.cr_data_store.dao.PersonDao;
import com.lge.sm.cr_data_store.entity.UserEntity;
import com.lge.sm.cr_data_store.repository.UserRepository;

import com.lge.sm.cr_data_store.entity.PersonEntity;
import com.lge.sm.cr_data_store.dto.PersonDto;
import com.lge.sm.cr_data_store.dto.PersonDtoExample;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.lge.sm.cr_data_store.repository.SkinRepository;
import com.lge.sm.cr_data_store.entity.SkinEntity;

abstract public class APersonRepository extends CacheableRepository<PersonEntity, PersonDao, PersonDto, PersonDtoExample>{
    @SuppressWarnings("unchecked")  
    private KindredEventPublisher<LoadEvent<PersonEntity>> loadPublisher = (KindredEventPublisher<LoadEvent<PersonEntity>>)EventBroker.getPublisher(PersonEntity.class, LoadEvent.class);
    @SuppressWarnings("unchecked")  
    private KindredEventPublisher<CreateEvent<PersonEntity>> createPublisher = (KindredEventPublisher<CreateEvent<PersonEntity>>)EventBroker.getPublisher(PersonEntity.class, CreateEvent.class);
    @SuppressWarnings("unchecked")
    private KindredEventPublisher<UpdateEvent<PersonEntity>> updatePublisher = (KindredEventPublisher<UpdateEvent<PersonEntity>>)EventBroker.getPublisher(PersonEntity.class, UpdateEvent.class);
    @SuppressWarnings("unchecked")
    private KindredEventPublisher<DeleteEvent<PersonEntity>> deletePublisher = (KindredEventPublisher<DeleteEvent<PersonEntity>>)EventBroker.getPublisher(PersonEntity.class, DeleteEvent.class);

    @Autowired
    public APersonRepository(PersonDao dao) {
        super(dao);
    }
    


    @Override
    public PersonEntity create(PersonDto dto) throws IllegalArgumentException {
        if(checkCreated(dto) == true) throw new IllegalArgumentException("Already created : " + ToString.toLine(dto));    
        if(checkForeignKeyEntityExist(dto) == false) throw new IllegalArgumentException("No record of foreign key when create : " + ToString.toLine(dto));
        if(dao.insert(dto) == false) throw new IllegalArgumentException();
            
        PersonEntity ret = load(dto);
        createPublisher.publish(new CreateEvent<PersonEntity>(cloneOf(ret)));
        
        return ret;
    }
  
    protected boolean checkForeignKeyEntityExist(PersonDto dto) {
		if(Repos.repo(UserRepository.class).getByMapKey(UserEntity.newMapKey(dto.getUserId())) == null) return false;

        return true;
    }
        
    public PersonEntity cloneOf(PersonEntity entity) {
        PersonEntity ret = newEntity(entity.getDto());

        return ret;
    }
  
    protected PersonEntity newEntity(PersonDto dto){
        return new PersonEntity(dto);
    }
    
    @Transactional
    @Override
    protected boolean deleteDao(List<PersonEntity> entities) {
        super.deleteDao(entities);
 
        return dao.delete(Repos.repo(PersonRepository.class).getDtoList(entities));
    }
   
    @Override
    protected boolean initDescendent() {


		parentSkinTypes.add(UserEntity.skinType());

        return true;
    }
    
    public PersonDtoExample example(){
        return new PersonDtoExample();
    }
    
    private PersonDtoExample criteriaFactory = new PersonDtoExample();
    public PersonDtoExample.Criteria criteria(){
        return criteriaFactory.createCriteria();
    }
    
    public List<PersonEntity> getByCriteria(PersonDtoExample.Criteria c){
        PersonDtoExample newExample = new PersonDtoExample();
        newExample.or(c);
        List<PersonDto> dtos = dao.selectByExample(newExample);
        List<PersonEntity> entities = new ArrayList<>();
        if(dtos == null) return entities;
        for(PersonDto each : dtos) entities.add(load(each));
      
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
            PersonDto dto = om.treeToValue(inputNode, PersonDto.class);
            PersonEntity entity = get(dto);
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
  
    public String skinized(PersonEntity entity) {
        return skinize(entity);
    }
    
    protected String skinize(PersonEntity entity) {
        return skinize(entity.getDto());
    }
  
    protected String skinize(PersonDto dto) {
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
        return PersonEntity.skinType();
    }

    @Override
    public Skin skin() {
        SkinEntity entity = Repos.repo(SkinRepository.class).getByMapKey(SkinEntity.newMapKey(skinType()));
        return entity.skin(getParentSkinTypes(), getKidSkinTypes());
    }
    
    protected PersonDto jsonNodeToDto(JsonNode node) {
        ObjectMapper om = JsonUtil.objectMapper();
        try {
            PersonDto dto = om.treeToValue(node, PersonDto.class);
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
        PersonDto dto = jsonNodeToDto(inputNode);
        if(dto == null) return "";
        PersonEntity entity = create(dto);
        if(entity != null) return skinized(entity);
        return "";
    }
    
    public String update(JsonNode inputNode) {
        PersonDto dto = jsonNodeToDto(inputNode);
        if(dto == null) return "";
        PersonEntity entity = get(dto);
        if(entity != null){
          boolean ret = update(newEntity(dto));
          if(ret) return skinized(get(dto));
        }
        return "";
    }
    
    public boolean delete(JsonNode inputNode) {
        PersonDto dto = jsonNodeToDto(inputNode);
        if(dto == null) return false;
        PersonEntity entity = get(dto);
        return delete(entity);
    }
    
    public String getSkinizedKids(JsonNode node, String kidSkinType) {
        PersonDto dto = jsonNodeToDto(node);
        if(dto == null) return "";
        PersonEntity entity = get(dto);

        
        return "";
    }
    
    @Override
    public String read(Map<String, String> from, Map<String, String> to, Map<String, List<String>> maps, int limit) {
        List<PersonDto> dtos = new ArrayList<>();
        Object[] keySet = maps.keySet().toArray();
        int idx[] = new int[keySet.length];
            
        while(true){
            PersonDtoExample example = example();
            example.setOrderByClause("cdate desc limit " + limit);
            PersonDtoExample.Criteria criteria = example.or();
      
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
        
            List<PersonDto> selected = dao.selectByExample(example);
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
        for(PersonEntity entity : getAll()) {
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


	protected MapSet<PersonEntity> userMapSet = new MapSet<>();

	public List<PersonEntity> getByUserId(String userId) {
		return userMapSet.get(UserEntity.newMapKey(userId));
	}

    @Override
    public PersonEntity get(PersonDto dto) {
        return map.get(PersonEntity.newMapKey(dto));
    }
       
    protected boolean checkCreated(PersonDto dto){
        if(map.containsKey(PersonEntity.newMapKey(dto))) return true;
        return false;
    }
    
    protected PersonEntity load(PersonDto dto) {
        PersonEntity entity = newEntity(dto);
        map.put(entity.mapKey(), entity);    

		userMapSet.put(UserEntity.newMapKey(entity.getUserId()), entity);
        
        return entity;
    }
    
    protected void loaded(PersonEntity entity){
        loadPublisher.publish(new LoadEvent<PersonEntity>(cloneOf(entity)));
    }
        
    @Override
    protected void updated(List<PersonEntity> entities) {
        for(PersonEntity entity : entities) updatePublisher.publish(new UpdateEvent<PersonEntity>(cloneOf(entity)));
    }
  
    @Override
    protected void daoDeleted(List<PersonEntity> entities) {
        super.daoDeleted(entities);
        for(PersonEntity entity : entities) deletePublisher.publish(new DeleteEvent<PersonEntity>(cloneOf(entity)));

    }
    
    @Override
    protected List<PersonDto> dtoListToStart() {
        return dao.selectAll();
    }
}
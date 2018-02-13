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
import com.lge.sm.cr_data_store.repository.AuthorityLocationRepository;
import com.lge.sm.cr_data_store.dao.AuthorityLocationDao;
import com.lge.sm.cr_data_store.entity.AuthorityEntity;
import com.lge.sm.cr_data_store.repository.AuthorityRepository;
import com.lge.sm.cr_data_store.entity.LocationEntity;
import com.lge.sm.cr_data_store.repository.LocationRepository;

import com.lge.sm.cr_data_store.entity.AuthorityLocationEntity;
import com.lge.sm.cr_data_store.dto.AuthorityLocationDto;
import com.lge.sm.cr_data_store.dto.AuthorityLocationDtoExample;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.lge.sm.cr_data_store.repository.SkinRepository;
import com.lge.sm.cr_data_store.entity.SkinEntity;

abstract public class AAuthorityLocationRepository extends CacheableRepository<AuthorityLocationEntity, AuthorityLocationDao, AuthorityLocationDto, AuthorityLocationDtoExample>{
    @SuppressWarnings("unchecked")  
    private KindredEventPublisher<LoadEvent<AuthorityLocationEntity>> loadPublisher = (KindredEventPublisher<LoadEvent<AuthorityLocationEntity>>)EventBroker.getPublisher(AuthorityLocationEntity.class, LoadEvent.class);
    @SuppressWarnings("unchecked")  
    private KindredEventPublisher<CreateEvent<AuthorityLocationEntity>> createPublisher = (KindredEventPublisher<CreateEvent<AuthorityLocationEntity>>)EventBroker.getPublisher(AuthorityLocationEntity.class, CreateEvent.class);
    @SuppressWarnings("unchecked")
    private KindredEventPublisher<UpdateEvent<AuthorityLocationEntity>> updatePublisher = (KindredEventPublisher<UpdateEvent<AuthorityLocationEntity>>)EventBroker.getPublisher(AuthorityLocationEntity.class, UpdateEvent.class);
    @SuppressWarnings("unchecked")
    private KindredEventPublisher<DeleteEvent<AuthorityLocationEntity>> deletePublisher = (KindredEventPublisher<DeleteEvent<AuthorityLocationEntity>>)EventBroker.getPublisher(AuthorityLocationEntity.class, DeleteEvent.class);

    @Autowired
    public AAuthorityLocationRepository(AuthorityLocationDao dao) {
        super(dao);
    }
    


    @Override
    public AuthorityLocationEntity create(AuthorityLocationDto dto) throws IllegalArgumentException {
        if(checkCreated(dto) == true) throw new IllegalArgumentException("Already created : " + ToString.toLine(dto));    
        if(checkForeignKeyEntityExist(dto) == false) throw new IllegalArgumentException("No record of foreign key when create : " + ToString.toLine(dto));
        if(dao.insert(dto) == false) throw new IllegalArgumentException();
            
        AuthorityLocationEntity ret = load(dto);
        createPublisher.publish(new CreateEvent<AuthorityLocationEntity>(cloneOf(ret)));
        
        return ret;
    }
  
    protected boolean checkForeignKeyEntityExist(AuthorityLocationDto dto) {
		if(Repos.repo(AuthorityRepository.class).getByMapKey(AuthorityEntity.newMapKey(dto.getAuthorityId())) == null) return false;
		if(Repos.repo(LocationRepository.class).getByMapKey(LocationEntity.newMapKey(dto.getLocationId())) == null) return false;

        return true;
    }
        
    public AuthorityLocationEntity cloneOf(AuthorityLocationEntity entity) {
        AuthorityLocationEntity ret = newEntity(entity.getDto());

        return ret;
    }
  
    protected AuthorityLocationEntity newEntity(AuthorityLocationDto dto){
        return new AuthorityLocationEntity(dto);
    }
    
    @Transactional
    @Override
    protected boolean deleteDao(List<AuthorityLocationEntity> entities) {
        super.deleteDao(entities);
 
        return dao.delete(Repos.repo(AuthorityLocationRepository.class).getDtoList(entities));
    }
   
    @Override
    protected boolean initDescendent() {


		parentSkinTypes.add(AuthorityEntity.skinType());
		parentSkinTypes.add(LocationEntity.skinType());

        return true;
    }
    
    public AuthorityLocationDtoExample example(){
        return new AuthorityLocationDtoExample();
    }
    
    private AuthorityLocationDtoExample criteriaFactory = new AuthorityLocationDtoExample();
    public AuthorityLocationDtoExample.Criteria criteria(){
        return criteriaFactory.createCriteria();
    }
    
    public List<AuthorityLocationEntity> getByCriteria(AuthorityLocationDtoExample.Criteria c){
        AuthorityLocationDtoExample newExample = new AuthorityLocationDtoExample();
        newExample.or(c);
        List<AuthorityLocationDto> dtos = dao.selectByExample(newExample);
        List<AuthorityLocationEntity> entities = new ArrayList<>();
        if(dtos == null) return entities;
        for(AuthorityLocationDto each : dtos) entities.add(load(each));
      
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
            AuthorityLocationDto dto = om.treeToValue(inputNode, AuthorityLocationDto.class);
            AuthorityLocationEntity entity = get(dto);
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
  
    public String skinized(AuthorityLocationEntity entity) {
        return skinize(entity);
    }
    
    protected String skinize(AuthorityLocationEntity entity) {
        return skinize(entity.getDto());
    }
  
    protected String skinize(AuthorityLocationDto dto) {
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
        return AuthorityLocationEntity.skinType();
    }

    @Override
    public Skin skin() {
        SkinEntity entity = Repos.repo(SkinRepository.class).getByMapKey(SkinEntity.newMapKey(skinType()));
        return entity.skin(getParentSkinTypes(), getKidSkinTypes());
    }
    
    protected AuthorityLocationDto jsonNodeToDto(JsonNode node) {
        ObjectMapper om = JsonUtil.objectMapper();
        try {
            AuthorityLocationDto dto = om.treeToValue(node, AuthorityLocationDto.class);
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
        AuthorityLocationDto dto = jsonNodeToDto(inputNode);
        if(dto == null) return "";
        AuthorityLocationEntity entity = create(dto);
        if(entity != null) return skinized(entity);
        return "";
    }
    
    public String update(JsonNode inputNode) {
        AuthorityLocationDto dto = jsonNodeToDto(inputNode);
        if(dto == null) return "";
        AuthorityLocationEntity entity = get(dto);
        if(entity != null){
          boolean ret = update(newEntity(dto));
          if(ret) return skinized(get(dto));
        }
        return "";
    }
    
    public boolean delete(JsonNode inputNode) {
        AuthorityLocationDto dto = jsonNodeToDto(inputNode);
        if(dto == null) return false;
        AuthorityLocationEntity entity = get(dto);
        return delete(entity);
    }
    
    public String getSkinizedKids(JsonNode node, String kidSkinType) {
        AuthorityLocationDto dto = jsonNodeToDto(node);
        if(dto == null) return "";
        AuthorityLocationEntity entity = get(dto);

        
        return "";
    }
    
    @Override
    public String read(Map<String, String> from, Map<String, String> to, Map<String, List<String>> maps, int limit) {
        List<AuthorityLocationDto> dtos = new ArrayList<>();
        Object[] keySet = maps.keySet().toArray();
        int idx[] = new int[keySet.length];
            
        while(true){
            AuthorityLocationDtoExample example = example();
            example.setOrderByClause("cdate desc limit " + limit);
            AuthorityLocationDtoExample.Criteria criteria = example.or();
      
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
        
            List<AuthorityLocationDto> selected = dao.selectByExample(example);
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
        for(AuthorityLocationEntity entity : getAll()) {
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


	protected MapSet<AuthorityLocationEntity> authorityMapSet = new MapSet<>();
	protected MapSet<AuthorityLocationEntity> locationMapSet = new MapSet<>();

	public List<AuthorityLocationEntity> getByAuthorityId(Long authorityId) {
		return authorityMapSet.get(AuthorityEntity.newMapKey(authorityId));
	}
	public List<AuthorityLocationEntity> getByLocationId(String locationId) {
		return locationMapSet.get(LocationEntity.newMapKey(locationId));
	}

    @Override
    public AuthorityLocationEntity get(AuthorityLocationDto dto) {
        return map.get(AuthorityLocationEntity.newMapKey(dto));
    }
       
    protected boolean checkCreated(AuthorityLocationDto dto){
        if(map.containsKey(AuthorityLocationEntity.newMapKey(dto))) return true;
        return false;
    }
    
    protected AuthorityLocationEntity load(AuthorityLocationDto dto) {
        AuthorityLocationEntity entity = newEntity(dto);
        map.put(entity.mapKey(), entity);    

		authorityMapSet.put(AuthorityEntity.newMapKey(entity.getAuthorityId()), entity);
		locationMapSet.put(LocationEntity.newMapKey(entity.getLocationId()), entity);
        
        return entity;
    }
    
    protected void loaded(AuthorityLocationEntity entity){
        loadPublisher.publish(new LoadEvent<AuthorityLocationEntity>(cloneOf(entity)));
    }
        
    @Override
    protected void updated(List<AuthorityLocationEntity> entities) {
        for(AuthorityLocationEntity entity : entities) updatePublisher.publish(new UpdateEvent<AuthorityLocationEntity>(cloneOf(entity)));
    }
  
    @Override
    protected void daoDeleted(List<AuthorityLocationEntity> entities) {
        super.daoDeleted(entities);
        for(AuthorityLocationEntity entity : entities) deletePublisher.publish(new DeleteEvent<AuthorityLocationEntity>(cloneOf(entity)));

    }
    
    @Override
    protected List<AuthorityLocationDto> dtoListToStart() {
        return dao.selectAll();
    }
}
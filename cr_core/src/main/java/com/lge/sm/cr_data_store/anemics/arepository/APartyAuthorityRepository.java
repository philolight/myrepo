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
import com.lge.sm.cr_data_store.repository.PartyAuthorityRepository;
import com.lge.sm.cr_data_store.dao.PartyAuthorityDao;
import com.lge.sm.cr_data_store.entity.AuthorityEntity;
import com.lge.sm.cr_data_store.repository.AuthorityRepository;
import com.lge.sm.cr_data_store.entity.PartyEntity;
import com.lge.sm.cr_data_store.repository.PartyRepository;

import com.lge.sm.cr_data_store.entity.PartyAuthorityEntity;
import com.lge.sm.cr_data_store.dto.PartyAuthorityDto;
import com.lge.sm.cr_data_store.dto.PartyAuthorityDtoExample;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.lge.sm.cr_data_store.repository.SkinRepository;
import com.lge.sm.cr_data_store.entity.SkinEntity;

abstract public class APartyAuthorityRepository extends CacheableRepository<PartyAuthorityEntity, PartyAuthorityDao, PartyAuthorityDto, PartyAuthorityDtoExample>{
    @SuppressWarnings("unchecked")  
    private KindredEventPublisher<LoadEvent<PartyAuthorityEntity>> loadPublisher = (KindredEventPublisher<LoadEvent<PartyAuthorityEntity>>)EventBroker.getPublisher(PartyAuthorityEntity.class, LoadEvent.class);
    @SuppressWarnings("unchecked")  
    private KindredEventPublisher<CreateEvent<PartyAuthorityEntity>> createPublisher = (KindredEventPublisher<CreateEvent<PartyAuthorityEntity>>)EventBroker.getPublisher(PartyAuthorityEntity.class, CreateEvent.class);
    @SuppressWarnings("unchecked")
    private KindredEventPublisher<UpdateEvent<PartyAuthorityEntity>> updatePublisher = (KindredEventPublisher<UpdateEvent<PartyAuthorityEntity>>)EventBroker.getPublisher(PartyAuthorityEntity.class, UpdateEvent.class);
    @SuppressWarnings("unchecked")
    private KindredEventPublisher<DeleteEvent<PartyAuthorityEntity>> deletePublisher = (KindredEventPublisher<DeleteEvent<PartyAuthorityEntity>>)EventBroker.getPublisher(PartyAuthorityEntity.class, DeleteEvent.class);

    @Autowired
    public APartyAuthorityRepository(PartyAuthorityDao dao) {
        super(dao);
    }
    


    @Override
    public PartyAuthorityEntity create(PartyAuthorityDto dto) throws IllegalArgumentException {
        if(checkCreated(dto) == true) throw new IllegalArgumentException("Already created : " + ToString.toLine(dto));    
        if(checkForeignKeyEntityExist(dto) == false) throw new IllegalArgumentException("No record of foreign key when create : " + ToString.toLine(dto));
        if(dao.insert(dto) == false) throw new IllegalArgumentException();
            
        PartyAuthorityEntity ret = load(dto);
        createPublisher.publish(new CreateEvent<PartyAuthorityEntity>(cloneOf(ret)));
        
        return ret;
    }
  
    protected boolean checkForeignKeyEntityExist(PartyAuthorityDto dto) {
		if(Repos.repo(AuthorityRepository.class).getByMapKey(AuthorityEntity.newMapKey(dto.getAuthorityId())) == null) return false;
		if(Repos.repo(PartyRepository.class).getByMapKey(PartyEntity.newMapKey(dto.getPartyId())) == null) return false;

        return true;
    }
        
    public PartyAuthorityEntity cloneOf(PartyAuthorityEntity entity) {
        PartyAuthorityEntity ret = newEntity(entity.getDto());

        return ret;
    }
  
    protected PartyAuthorityEntity newEntity(PartyAuthorityDto dto){
        return new PartyAuthorityEntity(dto);
    }
    
    @Transactional
    @Override
    protected boolean deleteDao(List<PartyAuthorityEntity> entities) {
        super.deleteDao(entities);
 
        return dao.delete(Repos.repo(PartyAuthorityRepository.class).getDtoList(entities));
    }
   
    @Override
    protected boolean initDescendent() {


		parentSkinTypes.add(AuthorityEntity.skinType());
		parentSkinTypes.add(PartyEntity.skinType());

        return true;
    }
    
    public PartyAuthorityDtoExample example(){
        return new PartyAuthorityDtoExample();
    }
    
    private PartyAuthorityDtoExample criteriaFactory = new PartyAuthorityDtoExample();
    public PartyAuthorityDtoExample.Criteria criteria(){
        return criteriaFactory.createCriteria();
    }
    
    public List<PartyAuthorityEntity> getByCriteria(PartyAuthorityDtoExample.Criteria c){
        PartyAuthorityDtoExample newExample = new PartyAuthorityDtoExample();
        newExample.or(c);
        List<PartyAuthorityDto> dtos = dao.selectByExample(newExample);
        List<PartyAuthorityEntity> entities = new ArrayList<>();
        if(dtos == null) return entities;
        for(PartyAuthorityDto each : dtos) entities.add(load(each));
      
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
            PartyAuthorityDto dto = om.treeToValue(inputNode, PartyAuthorityDto.class);
            PartyAuthorityEntity entity = get(dto);
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
  
    public String skinized(PartyAuthorityEntity entity) {
        return skinize(entity);
    }
    
    protected String skinize(PartyAuthorityEntity entity) {
        return skinize(entity.getDto());
    }
  
    protected String skinize(PartyAuthorityDto dto) {
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
        return PartyAuthorityEntity.skinType();
    }

    @Override
    public Skin skin() {
        SkinEntity entity = Repos.repo(SkinRepository.class).getByMapKey(SkinEntity.newMapKey(skinType()));
        return entity.skin(getParentSkinTypes(), getKidSkinTypes());
    }
    
    protected PartyAuthorityDto jsonNodeToDto(JsonNode node) {
        ObjectMapper om = JsonUtil.objectMapper();
        try {
            PartyAuthorityDto dto = om.treeToValue(node, PartyAuthorityDto.class);
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
        PartyAuthorityDto dto = jsonNodeToDto(inputNode);
        if(dto == null) return "";
        PartyAuthorityEntity entity = create(dto);
        if(entity != null) return skinized(entity);
        return "";
    }
    
    public String update(JsonNode inputNode) {
        PartyAuthorityDto dto = jsonNodeToDto(inputNode);
        if(dto == null) return "";
        PartyAuthorityEntity entity = get(dto);
        if(entity != null){
          boolean ret = update(newEntity(dto));
          if(ret) return skinized(get(dto));
        }
        return "";
    }
    
    public boolean delete(JsonNode inputNode) {
        PartyAuthorityDto dto = jsonNodeToDto(inputNode);
        if(dto == null) return false;
        PartyAuthorityEntity entity = get(dto);
        return delete(entity);
    }
    
    public String getSkinizedKids(JsonNode node, String kidSkinType) {
        PartyAuthorityDto dto = jsonNodeToDto(node);
        if(dto == null) return "";
        PartyAuthorityEntity entity = get(dto);

        
        return "";
    }
    
    @Override
    public String read(Map<String, String> from, Map<String, String> to, Map<String, List<String>> maps, int limit) {
        List<PartyAuthorityDto> dtos = new ArrayList<>();
        Object[] keySet = maps.keySet().toArray();
        int idx[] = new int[keySet.length];
            
        while(true){
            PartyAuthorityDtoExample example = example();
            example.setOrderByClause("cdate desc limit " + limit);
            PartyAuthorityDtoExample.Criteria criteria = example.or();
      
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
        
            List<PartyAuthorityDto> selected = dao.selectByExample(example);
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
        for(PartyAuthorityEntity entity : getAll()) {
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


	protected MapSet<PartyAuthorityEntity> authorityMapSet = new MapSet<>();
	protected MapSet<PartyAuthorityEntity> partyMapSet = new MapSet<>();

	public List<PartyAuthorityEntity> getByAuthorityId(Long authorityId) {
		return authorityMapSet.get(AuthorityEntity.newMapKey(authorityId));
	}
	public List<PartyAuthorityEntity> getByPartyId(String partyId) {
		return partyMapSet.get(PartyEntity.newMapKey(partyId));
	}

    @Override
    public PartyAuthorityEntity get(PartyAuthorityDto dto) {
        return map.get(PartyAuthorityEntity.newMapKey(dto));
    }
       
    protected boolean checkCreated(PartyAuthorityDto dto){
        if(map.containsKey(PartyAuthorityEntity.newMapKey(dto))) return true;
        return false;
    }
    
    protected PartyAuthorityEntity load(PartyAuthorityDto dto) {
        PartyAuthorityEntity entity = newEntity(dto);
        map.put(entity.mapKey(), entity);    

		authorityMapSet.put(AuthorityEntity.newMapKey(entity.getAuthorityId()), entity);
		partyMapSet.put(PartyEntity.newMapKey(entity.getPartyId()), entity);
        
        return entity;
    }
    
    protected void loaded(PartyAuthorityEntity entity){
        loadPublisher.publish(new LoadEvent<PartyAuthorityEntity>(cloneOf(entity)));
    }
        
    @Override
    protected void updated(List<PartyAuthorityEntity> entities) {
        for(PartyAuthorityEntity entity : entities) updatePublisher.publish(new UpdateEvent<PartyAuthorityEntity>(cloneOf(entity)));
    }
  
    @Override
    protected void daoDeleted(List<PartyAuthorityEntity> entities) {
        super.daoDeleted(entities);
        for(PartyAuthorityEntity entity : entities) deletePublisher.publish(new DeleteEvent<PartyAuthorityEntity>(cloneOf(entity)));

    }
    
    @Override
    protected List<PartyAuthorityDto> dtoListToStart() {
        return dao.selectAll();
    }
}
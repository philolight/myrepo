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
import com.lge.sm.cr_data_store.repository.PartyRepository;
import com.lge.sm.cr_data_store.dao.PartyDao;
import com.lge.sm.cr_data_store.entity.PartyUserEntity;
import com.lge.sm.cr_data_store.repository.PartyUserRepository;
import com.lge.sm.cr_data_store.entity.PartyAuthorityEntity;
import com.lge.sm.cr_data_store.repository.PartyAuthorityRepository;

import com.lge.sm.cr_data_store.entity.PartyEntity;
import com.lge.sm.cr_data_store.dto.PartyDto;
import com.lge.sm.cr_data_store.dto.PartyDtoExample;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.lge.sm.cr_data_store.repository.SkinRepository;
import com.lge.sm.cr_data_store.entity.SkinEntity;

abstract public class APartyRepository extends CacheableRepository<PartyEntity, PartyDao, PartyDto, PartyDtoExample>{
    @SuppressWarnings("unchecked")  
    private KindredEventPublisher<LoadEvent<PartyEntity>> loadPublisher = (KindredEventPublisher<LoadEvent<PartyEntity>>)EventBroker.getPublisher(PartyEntity.class, LoadEvent.class);
    @SuppressWarnings("unchecked")  
    private KindredEventPublisher<CreateEvent<PartyEntity>> createPublisher = (KindredEventPublisher<CreateEvent<PartyEntity>>)EventBroker.getPublisher(PartyEntity.class, CreateEvent.class);
    @SuppressWarnings("unchecked")
    private KindredEventPublisher<UpdateEvent<PartyEntity>> updatePublisher = (KindredEventPublisher<UpdateEvent<PartyEntity>>)EventBroker.getPublisher(PartyEntity.class, UpdateEvent.class);
    @SuppressWarnings("unchecked")
    private KindredEventPublisher<DeleteEvent<PartyEntity>> deletePublisher = (KindredEventPublisher<DeleteEvent<PartyEntity>>)EventBroker.getPublisher(PartyEntity.class, DeleteEvent.class);

    @Autowired
    public APartyRepository(PartyDao dao) {
        super(dao);
    }
    


    @Override
    public PartyEntity create(PartyDto dto) throws IllegalArgumentException {
    	dto.setCdate(DateStringUtil.getCurrentDateString(DateStringUtil.gmtTimeZoneId));

        if(checkCreated(dto) == true) throw new IllegalArgumentException("Already created : " + ToString.toLine(dto));    
        if(checkForeignKeyEntityExist(dto) == false) throw new IllegalArgumentException("No record of foreign key when create : " + ToString.toLine(dto));
        if(dao.insert(dto) == false) throw new IllegalArgumentException();
            
        PartyEntity ret = load(dto);
        createPublisher.publish(new CreateEvent<PartyEntity>(cloneOf(ret)));
        
        return ret;
    }
  
    protected boolean checkForeignKeyEntityExist(PartyDto dto) {

        return true;
    }
        
    public PartyEntity cloneOf(PartyEntity entity) {
        PartyEntity ret = newEntity(entity.getDto());

        return ret;
    }
  
    protected PartyEntity newEntity(PartyDto dto){
        return new PartyEntity(dto);
    }
    
    @Transactional
    @Override
    protected boolean deleteDao(List<PartyEntity> entities) {
        super.deleteDao(entities);
		List<PartyUserEntity> partyUserList = new ArrayList<>();
		for(PartyEntity each : entities) partyUserList.addAll(Repos.repo(PartyUserRepository.class).getByPartyId(each.getPartyId()));
		if(partyUserList.size() != 0) {
			if(Repos.repo(PartyUserRepository.class).delete(partyUserList) == false) return false;
		}
		List<PartyAuthorityEntity> partyAuthorityList = new ArrayList<>();
		for(PartyEntity each : entities) partyAuthorityList.addAll(Repos.repo(PartyAuthorityRepository.class).getByPartyId(each.getPartyId()));
		if(partyAuthorityList.size() != 0) {
			if(Repos.repo(PartyAuthorityRepository.class).delete(partyAuthorityList) == false) return false;
		}
 
        return dao.delete(Repos.repo(PartyRepository.class).getDtoList(entities));
    }
   
    @Override
    protected boolean initDescendent() {



		kidSkinTypes.add(PartyUserEntity.skinType());
		kidSkinTypes.add(PartyAuthorityEntity.skinType());
        return true;
    }
    
    public PartyDtoExample example(){
        return new PartyDtoExample();
    }
    
    private PartyDtoExample criteriaFactory = new PartyDtoExample();
    public PartyDtoExample.Criteria criteria(){
        return criteriaFactory.createCriteria();
    }
    
    public List<PartyEntity> getByCriteria(PartyDtoExample.Criteria c){
        PartyDtoExample newExample = new PartyDtoExample();
        newExample.or(c);
        List<PartyDto> dtos = dao.selectByExample(newExample);
        List<PartyEntity> entities = new ArrayList<>();
        if(dtos == null) return entities;
        for(PartyDto each : dtos) entities.add(load(each));
      
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
            PartyDto dto = om.treeToValue(inputNode, PartyDto.class);
            PartyEntity entity = get(dto);
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
  
    public String skinized(PartyEntity entity) {
        return skinize(entity);
    }
    
    protected String skinize(PartyEntity entity) {
        return skinize(entity.getDto());
    }
  
    protected String skinize(PartyDto dto) {
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
        return PartyEntity.skinType();
    }

    @Override
    public Skin skin() {
        SkinEntity entity = Repos.repo(SkinRepository.class).getByMapKey(SkinEntity.newMapKey(skinType()));
        return entity.skin(getParentSkinTypes(), getKidSkinTypes());
    }
    
    protected PartyDto jsonNodeToDto(JsonNode node) {
        ObjectMapper om = JsonUtil.objectMapper();
        try {
            PartyDto dto = om.treeToValue(node, PartyDto.class);
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
    	List<PartyDto> dtoList = new ArrayList<>();
		for(JsonNode each : nodeList) {
	        PartyDto dto = jsonNodeToDto(each);
	        if(dto == null) return "";
	        dtoList.add(dto);
		}
		
    	List<PartyEntity> entityList = new ArrayList<>();
		for(PartyDto dto : dtoList) {
	        PartyEntity entity = create(dto);
	        if(entity == null) Logger.error(TAG, "Failed to create : " + ToString.toLine(dto));
	        else entityList.add(entity);
		}
		
        StringBuffer ret = new StringBuffer();
        ret.append("[");
        for(int i = 0; i < entityList.size(); i++) {
        	PartyEntity entity = entityList.get(i);
            ret.append(skinize(entity));
            if(i != entityList.size() - 1) ret.append(",");
        }
        ret.append("]");
        
        return ret.toString();
    }
    
    public String update(JsonNode nodeList) {
    	List<PartyDto> dtoList = new ArrayList<>();
		for(JsonNode each : nodeList) {
	        PartyDto dto = jsonNodeToDto(each);
	        System.out.println(ToString.toLine(dto));
	        if(dto == null) return "";
	        dtoList.add(dto);
		}
		
    	List<PartyEntity> entityList = new ArrayList<>();
		for(PartyDto dto : dtoList) {
	        PartyEntity entity = newEntity(dto);
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
        	PartyEntity entity = entityList.get(i);
            ret.append(skinize(entity));
            if(i != entityList.size() - 1) ret.append(",");
        }
        ret.append("]");
        
        return ret.toString();
    }
    
    public String delete(JsonNode nodeList) {
    	List<PartyDto> dtoList = new ArrayList<>();
		for(JsonNode each : nodeList) {
	        PartyDto dto = jsonNodeToDto(each);
	        System.out.println(ToString.toLine(dto));
	        if(dto == null) return "";
	        dtoList.add(dto);
		}
		
    	List<PartyEntity> entityList = new ArrayList<>();
		for(PartyDto dto : dtoList) {
	        PartyEntity entity = get(dto);
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
        	PartyEntity entity = entityList.get(i);
            ret.append(skinize(entity));
            if(i != entityList.size() - 1) ret.append(",");
        }
        ret.append("]");
        
        return ret.toString();
    }
    
    public String getSkinizedKids(JsonNode node, String kidSkinType) {
        PartyDto dto = jsonNodeToDto(node);
        if(dto == null) return "";
        PartyEntity entity = get(dto);
        if(entity == null) return "";

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
        
        return "";
    }
    
    @Override
    public String read(Map<String, String> from, Map<String, String> to, Map<String, List<String>> maps, int limit) {
        List<PartyDto> dtos = new ArrayList<>();
        Object[] keySet = maps.keySet().toArray();
        int idx[] = new int[keySet.length];
            
        while(true){
            PartyDtoExample example = example();
            example.setOrderByClause("cdate desc limit " + limit);
            PartyDtoExample.Criteria criteria = example.or();
      
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
        
            List<PartyDto> selected = dao.selectByExample(example);
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
        for(PartyEntity entity : getAll()) {
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
    public PartyEntity get(PartyDto dto) {
        return map.get(PartyEntity.newMapKey(dto));
    }
       
    protected boolean checkCreated(PartyDto dto){
        if(map.containsKey(PartyEntity.newMapKey(dto))) return true;
        return false;
    }
    
    protected PartyEntity load(PartyDto dto) {
        PartyEntity entity = newEntity(dto);
        map.put(entity.mapKey(), entity);    

        
        return entity;
    }
    
    protected void loaded(PartyEntity entity){
        loadPublisher.publish(new LoadEvent<PartyEntity>(cloneOf(entity)));
    }
        
    @Override
    protected void updated(List<PartyEntity> entities) {
        for(PartyEntity entity : entities) updatePublisher.publish(new UpdateEvent<PartyEntity>(cloneOf(entity)));
    }
  
    @Override
    protected void daoDeleted(List<PartyEntity> entities) {
        super.daoDeleted(entities);
        for(PartyEntity entity : entities) deletePublisher.publish(new DeleteEvent<PartyEntity>(cloneOf(entity)));

    }
    
    @Override
    protected List<PartyDto> dtoListToStart() {
        return dao.selectAll();
    }
}
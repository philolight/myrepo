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
import com.lge.sm.cr_data_store.repository.ServiceAuthorityRepository;
import com.lge.sm.cr_data_store.dao.ServiceAuthorityDao;
import com.lge.sm.cr_data_store.entity.AuthorityEntity;
import com.lge.sm.cr_data_store.repository.AuthorityRepository;
import com.lge.sm.cr_data_store.entity.ServiceEntity;
import com.lge.sm.cr_data_store.repository.ServiceRepository;

import com.lge.sm.cr_data_store.entity.ServiceAuthorityEntity;
import com.lge.sm.cr_data_store.dto.ServiceAuthorityDto;
import com.lge.sm.cr_data_store.dto.ServiceAuthorityDtoExample;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.lge.sm.cr_data_store.repository.SkinRepository;
import com.lge.sm.cr_data_store.entity.SkinEntity;

abstract public class AServiceAuthorityRepository extends CacheableRepository<ServiceAuthorityEntity, ServiceAuthorityDao, ServiceAuthorityDto, ServiceAuthorityDtoExample>{
    @SuppressWarnings("unchecked")  
    private KindredEventPublisher<LoadEvent<ServiceAuthorityEntity>> loadPublisher = (KindredEventPublisher<LoadEvent<ServiceAuthorityEntity>>)EventBroker.getPublisher(ServiceAuthorityEntity.class, LoadEvent.class);
    @SuppressWarnings("unchecked")  
    private KindredEventPublisher<CreateEvent<ServiceAuthorityEntity>> createPublisher = (KindredEventPublisher<CreateEvent<ServiceAuthorityEntity>>)EventBroker.getPublisher(ServiceAuthorityEntity.class, CreateEvent.class);
    @SuppressWarnings("unchecked")
    private KindredEventPublisher<UpdateEvent<ServiceAuthorityEntity>> updatePublisher = (KindredEventPublisher<UpdateEvent<ServiceAuthorityEntity>>)EventBroker.getPublisher(ServiceAuthorityEntity.class, UpdateEvent.class);
    @SuppressWarnings("unchecked")
    private KindredEventPublisher<DeleteEvent<ServiceAuthorityEntity>> deletePublisher = (KindredEventPublisher<DeleteEvent<ServiceAuthorityEntity>>)EventBroker.getPublisher(ServiceAuthorityEntity.class, DeleteEvent.class);

    @Autowired
    public AServiceAuthorityRepository(ServiceAuthorityDao dao) {
        super(dao);
    }
    


    @Override
    public ServiceAuthorityEntity create(ServiceAuthorityDto dto) throws IllegalArgumentException {
    	dto.setCdate(DateStringUtil.getCurrentDateString(DateStringUtil.gmtTimeZoneId));

        if(checkCreated(dto) == true) throw new IllegalArgumentException("Already created : " + ToString.toLine(dto));    
        if(checkForeignKeyEntityExist(dto) == false) throw new IllegalArgumentException("No record of foreign key when create : " + ToString.toLine(dto));
        if(dao.insert(dto) == false) throw new IllegalArgumentException();
            
        ServiceAuthorityEntity ret = load(dto);
        createPublisher.publish(new CreateEvent<ServiceAuthorityEntity>(cloneOf(ret)));
        
        return ret;
    }
  
    protected boolean checkForeignKeyEntityExist(ServiceAuthorityDto dto) {
		if(dto.getAuthorityId() != null && Repos.repo(AuthorityRepository.class).getByMapKey(AuthorityEntity.newMapKey(dto.getAuthorityId())) == null) return false;
		if(dto.getServiceId() != null && Repos.repo(ServiceRepository.class).getByMapKey(ServiceEntity.newMapKey(dto.getServiceId())) == null) return false;

        return true;
    }
        
    public ServiceAuthorityEntity cloneOf(ServiceAuthorityEntity entity) {
        ServiceAuthorityEntity ret = newEntity(entity.getDto());

        return ret;
    }
  
    protected ServiceAuthorityEntity newEntity(ServiceAuthorityDto dto){
        return new ServiceAuthorityEntity(dto);
    }
    
    @Transactional
    @Override
    protected boolean deleteDao(List<ServiceAuthorityEntity> entities) {
        super.deleteDao(entities);
 
        return dao.delete(Repos.repo(ServiceAuthorityRepository.class).getDtoList(entities));
    }
   
    @Override
    protected boolean initDescendent() {


		parentSkinTypes.add(AuthorityEntity.skinType());
		parentSkinTypes.add(ServiceEntity.skinType());

        return true;
    }
    
    public ServiceAuthorityDtoExample example(){
        return new ServiceAuthorityDtoExample();
    }
    
    private ServiceAuthorityDtoExample criteriaFactory = new ServiceAuthorityDtoExample();
    public ServiceAuthorityDtoExample.Criteria criteria(){
        return criteriaFactory.createCriteria();
    }
    
    public List<ServiceAuthorityEntity> getByCriteria(ServiceAuthorityDtoExample.Criteria c){
        ServiceAuthorityDtoExample newExample = new ServiceAuthorityDtoExample();
        newExample.or(c);
        List<ServiceAuthorityDto> dtos = dao.selectByExample(newExample);
        List<ServiceAuthorityEntity> entities = new ArrayList<>();
        if(dtos == null) return entities;
        for(ServiceAuthorityDto each : dtos) entities.add(load(each));
      
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
            ServiceAuthorityDto dto = om.treeToValue(inputNode, ServiceAuthorityDto.class);
            ServiceAuthorityEntity entity = get(dto);
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
  
    public String skinized(ServiceAuthorityEntity entity) {
        return skinize(entity);
    }
    
    protected String skinize(ServiceAuthorityEntity entity) {
        return skinize(entity.getDto());
    }
  
    protected String skinize(ServiceAuthorityDto dto) {
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
        return ServiceAuthorityEntity.skinType();
    }

    @Override
    public Skin skin() {
        SkinEntity entity = Repos.repo(SkinRepository.class).getByMapKey(SkinEntity.newMapKey(skinType()));
        return entity.skin(getParentSkinTypes(), getKidSkinTypes());
    }
    
    protected ServiceAuthorityDto jsonNodeToDto(JsonNode node) {
        ObjectMapper om = JsonUtil.objectMapper();
        try {
            ServiceAuthorityDto dto = om.treeToValue(node, ServiceAuthorityDto.class);
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
    	List<ServiceAuthorityDto> dtoList = new ArrayList<>();
		for(JsonNode each : nodeList) {
	        ServiceAuthorityDto dto = jsonNodeToDto(each);
	        if(dto == null) return "";
	        dtoList.add(dto);
		}
		
    	List<ServiceAuthorityEntity> entityList = new ArrayList<>();
		for(ServiceAuthorityDto dto : dtoList) {
	        ServiceAuthorityEntity entity = create(dto);
	        if(entity == null) Logger.error(TAG, "Failed to create : " + ToString.toLine(dto));
	        else entityList.add(entity);
		}
		
        StringBuffer ret = new StringBuffer();
        ret.append("[");
        for(int i = 0; i < entityList.size(); i++) {
        	ServiceAuthorityEntity entity = entityList.get(i);
            ret.append(skinize(entity));
            if(i != entityList.size() - 1) ret.append(",");
        }
        ret.append("]");
        
        return ret.toString();
    }
    
    public String update(JsonNode nodeList) {
    	List<ServiceAuthorityDto> dtoList = new ArrayList<>();
		for(JsonNode each : nodeList) {
	        ServiceAuthorityDto dto = jsonNodeToDto(each);
	        System.out.println(ToString.toLine(dto));
	        if(dto == null) return "";
	        dtoList.add(dto);
		}
		
    	List<ServiceAuthorityEntity> entityList = new ArrayList<>();
		for(ServiceAuthorityDto dto : dtoList) {
	        ServiceAuthorityEntity entity = newEntity(dto);
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
        	ServiceAuthorityEntity entity = entityList.get(i);
            ret.append(skinize(entity));
            if(i != entityList.size() - 1) ret.append(",");
        }
        ret.append("]");
        
        return ret.toString();
    }
    
    public String delete(JsonNode nodeList) {
    	List<ServiceAuthorityDto> dtoList = new ArrayList<>();
		for(JsonNode each : nodeList) {
	        ServiceAuthorityDto dto = jsonNodeToDto(each);
	        System.out.println(ToString.toLine(dto));
	        if(dto == null) return "";
	        dtoList.add(dto);
		}
		
    	List<ServiceAuthorityEntity> entityList = new ArrayList<>();
		for(ServiceAuthorityDto dto : dtoList) {
	        ServiceAuthorityEntity entity = get(dto);
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
        	ServiceAuthorityEntity entity = entityList.get(i);
            ret.append(skinize(entity));
            if(i != entityList.size() - 1) ret.append(",");
        }
        ret.append("]");
        
        return ret.toString();
    }
    
    public String getSkinizedKids(JsonNode node, String kidSkinType) {
        ServiceAuthorityDto dto = jsonNodeToDto(node);
        if(dto == null) return "";
        ServiceAuthorityEntity entity = get(dto);
        if(entity == null) return "";

        
        return "";
    }
    
    @Override
    public String read(Map<String, String> from, Map<String, String> to, Map<String, List<String>> maps, int limit) {
        List<ServiceAuthorityDto> dtos = new ArrayList<>();
        Object[] keySet = maps.keySet().toArray();
        int idx[] = new int[keySet.length];
            
        while(true){
            ServiceAuthorityDtoExample example = example();
            example.setOrderByClause("cdate desc limit " + limit);
            ServiceAuthorityDtoExample.Criteria criteria = example.or();
      
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
        
            List<ServiceAuthorityDto> selected = dao.selectByExample(example);
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
        for(ServiceAuthorityEntity entity : getAll()) {
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


	protected MapSet<ServiceAuthorityEntity> authorityMapSet = new MapSet<>();
	protected MapSet<ServiceAuthorityEntity> serviceMapSet = new MapSet<>();

	public List<ServiceAuthorityEntity> getByAuthorityId(Long authorityId) {
		return authorityMapSet.get(AuthorityEntity.newMapKey(authorityId));
	}
	public List<ServiceAuthorityEntity> getByServiceId(String serviceId) {
		return serviceMapSet.get(ServiceEntity.newMapKey(serviceId));
	}

    @Override
    public ServiceAuthorityEntity get(ServiceAuthorityDto dto) {
        return map.get(ServiceAuthorityEntity.newMapKey(dto));
    }
       
    protected boolean checkCreated(ServiceAuthorityDto dto){
        if(map.containsKey(ServiceAuthorityEntity.newMapKey(dto))) return true;
        return false;
    }
    
    protected ServiceAuthorityEntity load(ServiceAuthorityDto dto) {
        ServiceAuthorityEntity entity = newEntity(dto);
        map.put(entity.mapKey(), entity);    

		authorityMapSet.put(AuthorityEntity.newMapKey(entity.getAuthorityId()), entity);
		serviceMapSet.put(ServiceEntity.newMapKey(entity.getServiceId()), entity);
        
        return entity;
    }
    
    protected void loaded(ServiceAuthorityEntity entity){
        loadPublisher.publish(new LoadEvent<ServiceAuthorityEntity>(cloneOf(entity)));
    }
        
    @Override
    protected void updated(List<ServiceAuthorityEntity> entities) {
        for(ServiceAuthorityEntity entity : entities) updatePublisher.publish(new UpdateEvent<ServiceAuthorityEntity>(cloneOf(entity)));
    }
  
    @Override
    protected void daoDeleted(List<ServiceAuthorityEntity> entities) {
        super.daoDeleted(entities);
        for(ServiceAuthorityEntity entity : entities) deletePublisher.publish(new DeleteEvent<ServiceAuthorityEntity>(cloneOf(entity)));
		for(ServiceAuthorityEntity each : entities) authorityMapSet.remove(AuthorityEntity.newMapKey(each.getAuthorityId()), each);		for(ServiceAuthorityEntity each : entities) serviceMapSet.remove(ServiceEntity.newMapKey(each.getServiceId()), each);
    }
    
    @Override
    protected List<ServiceAuthorityDto> dtoListToStart() {
        return dao.selectAll();
    }
}
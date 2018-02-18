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
import com.lge.sm.cr_data_store.repository.ServiceRepository;
import com.lge.sm.cr_data_store.dao.ServiceDao;
import com.lge.sm.cr_data_store.entity.StartableEntity;
import com.lge.sm.cr_data_store.repository.StartableRepository;
import com.lge.sm.cr_data_store.entity.ServiceAuthorityEntity;
import com.lge.sm.cr_data_store.repository.ServiceAuthorityRepository;

import com.lge.sm.cr_data_store.entity.ServiceEntity;
import com.lge.sm.cr_data_store.dto.ServiceDto;
import com.lge.sm.cr_data_store.dto.ServiceDtoExample;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.lge.sm.cr_data_store.repository.SkinRepository;
import com.lge.sm.cr_data_store.entity.SkinEntity;

abstract public class AServiceRepository extends CacheableRepository<ServiceEntity, ServiceDao, ServiceDto, ServiceDtoExample>{
    @SuppressWarnings("unchecked")  
    private KindredEventPublisher<LoadEvent<ServiceEntity>> loadPublisher = (KindredEventPublisher<LoadEvent<ServiceEntity>>)EventBroker.getPublisher(ServiceEntity.class, LoadEvent.class);
    @SuppressWarnings("unchecked")  
    private KindredEventPublisher<CreateEvent<ServiceEntity>> createPublisher = (KindredEventPublisher<CreateEvent<ServiceEntity>>)EventBroker.getPublisher(ServiceEntity.class, CreateEvent.class);
    @SuppressWarnings("unchecked")
    private KindredEventPublisher<UpdateEvent<ServiceEntity>> updatePublisher = (KindredEventPublisher<UpdateEvent<ServiceEntity>>)EventBroker.getPublisher(ServiceEntity.class, UpdateEvent.class);
    @SuppressWarnings("unchecked")
    private KindredEventPublisher<DeleteEvent<ServiceEntity>> deletePublisher = (KindredEventPublisher<DeleteEvent<ServiceEntity>>)EventBroker.getPublisher(ServiceEntity.class, DeleteEvent.class);

    @Autowired
    public AServiceRepository(ServiceDao dao) {
        super(dao);
    }
    


    @Override
    public ServiceEntity create(ServiceDto dto) throws IllegalArgumentException {
    	dto.setCdate(DateStringUtil.getCurrentDateString(DateStringUtil.gmtTimeZoneId));

        if(checkCreated(dto) == true) throw new IllegalArgumentException("Already created : " + ToString.toLine(dto));    
        if(checkForeignKeyEntityExist(dto) == false) throw new IllegalArgumentException("No record of foreign key when create : " + ToString.toLine(dto));
        if(dao.insert(dto) == false) throw new IllegalArgumentException();
            
        ServiceEntity ret = load(dto);
        createPublisher.publish(new CreateEvent<ServiceEntity>(cloneOf(ret)));
        
        return ret;
    }
  
    protected boolean checkForeignKeyEntityExist(ServiceDto dto) {
		if(dto.getStartableId() != null && Repos.repo(StartableRepository.class).getByMapKey(StartableEntity.newMapKey(dto.getStartableId())) == null) return false;

        return true;
    }
        
    public ServiceEntity cloneOf(ServiceEntity entity) {
        ServiceEntity ret = newEntity(entity.getDto());

        return ret;
    }
  
    protected ServiceEntity newEntity(ServiceDto dto){
        return new ServiceEntity(dto);
    }
    
    @Transactional
    @Override
    protected boolean deleteDao(List<ServiceEntity> entities) {
        super.deleteDao(entities);
		List<ServiceAuthorityEntity> serviceAuthorityList = new ArrayList<>();
		for(ServiceEntity each : entities) serviceAuthorityList.addAll(Repos.repo(ServiceAuthorityRepository.class).getByServiceId(each.getServiceId()));
		if(serviceAuthorityList.size() != 0) {
			if(Repos.repo(ServiceAuthorityRepository.class).delete(serviceAuthorityList) == false) return false;
		}
 
        return dao.delete(Repos.repo(ServiceRepository.class).getDtoList(entities));
    }
   
    @Override
    protected boolean initDescendent() {


		parentSkinTypes.add(StartableEntity.skinType());

		kidSkinTypes.add(ServiceAuthorityEntity.skinType());
        return true;
    }
    
    public ServiceDtoExample example(){
        return new ServiceDtoExample();
    }
    
    private ServiceDtoExample criteriaFactory = new ServiceDtoExample();
    public ServiceDtoExample.Criteria criteria(){
        return criteriaFactory.createCriteria();
    }
    
    public List<ServiceEntity> getByCriteria(ServiceDtoExample.Criteria c){
        ServiceDtoExample newExample = new ServiceDtoExample();
        newExample.or(c);
        List<ServiceDto> dtos = dao.selectByExample(newExample);
        List<ServiceEntity> entities = new ArrayList<>();
        if(dtos == null) return entities;
        for(ServiceDto each : dtos) entities.add(load(each));
      
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
            ServiceDto dto = om.treeToValue(inputNode, ServiceDto.class);
            ServiceEntity entity = get(dto);
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
  
    public String skinized(ServiceEntity entity) {
        return skinize(entity);
    }
    
    protected String skinize(ServiceEntity entity) {
        return skinize(entity.getDto());
    }
  
    protected String skinize(ServiceDto dto) {
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
        return ServiceEntity.skinType();
    }

    @Override
    public Skin skin() {
        SkinEntity entity = Repos.repo(SkinRepository.class).getByMapKey(SkinEntity.newMapKey(skinType()));
        return entity.skin(getParentSkinTypes(), getKidSkinTypes());
    }
    
    protected ServiceDto jsonNodeToDto(JsonNode node) {
        ObjectMapper om = JsonUtil.objectMapper();
        try {
            ServiceDto dto = om.treeToValue(node, ServiceDto.class);
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
    	List<ServiceDto> dtoList = new ArrayList<>();
		for(JsonNode each : nodeList) {
	        ServiceDto dto = jsonNodeToDto(each);
	        if(dto == null) return "";
	        dtoList.add(dto);
		}
		
    	List<ServiceEntity> entityList = new ArrayList<>();
		for(ServiceDto dto : dtoList) {
	        ServiceEntity entity = create(dto);
	        if(entity == null) Logger.error(TAG, "Failed to create : " + ToString.toLine(dto));
	        else entityList.add(entity);
		}
		
        StringBuffer ret = new StringBuffer();
        ret.append("[");
        for(int i = 0; i < entityList.size(); i++) {
        	ServiceEntity entity = entityList.get(i);
            ret.append(skinize(entity));
            if(i != entityList.size() - 1) ret.append(",");
        }
        ret.append("]");
        
        return ret.toString();
    }
    
    public String update(JsonNode nodeList) {
    	List<ServiceDto> dtoList = new ArrayList<>();
		for(JsonNode each : nodeList) {
	        ServiceDto dto = jsonNodeToDto(each);
	        System.out.println(ToString.toLine(dto));
	        if(dto == null) return "";
	        dtoList.add(dto);
		}
		
    	List<ServiceEntity> entityList = new ArrayList<>();
		for(ServiceDto dto : dtoList) {
	        ServiceEntity entity = newEntity(dto);
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
        	ServiceEntity entity = entityList.get(i);
            ret.append(skinize(entity));
            if(i != entityList.size() - 1) ret.append(",");
        }
        ret.append("]");
        
        return ret.toString();
    }
    
    public String delete(JsonNode nodeList) {
    	List<ServiceDto> dtoList = new ArrayList<>();
		for(JsonNode each : nodeList) {
	        ServiceDto dto = jsonNodeToDto(each);
	        System.out.println(ToString.toLine(dto));
	        if(dto == null) return "";
	        dtoList.add(dto);
		}
		
    	List<ServiceEntity> entityList = new ArrayList<>();
		for(ServiceDto dto : dtoList) {
	        ServiceEntity entity = get(dto);
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
        	ServiceEntity entity = entityList.get(i);
            ret.append(skinize(entity));
            if(i != entityList.size() - 1) ret.append(",");
        }
        ret.append("]");
        
        return ret.toString();
    }
    
    public String getSkinizedKids(JsonNode node, String kidSkinType) {
        ServiceDto dto = jsonNodeToDto(node);
        if(dto == null) return "";
        ServiceEntity entity = get(dto);
        if(entity == null) return "";

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
        
        return "";
    }
    
    @Override
    public String read(Map<String, String> from, Map<String, String> to, Map<String, List<String>> maps, int limit) {
        List<ServiceDto> dtos = new ArrayList<>();
        Object[] keySet = maps.keySet().toArray();
        int idx[] = new int[keySet.length];
            
        while(true){
            ServiceDtoExample example = example();
            example.setOrderByClause("cdate desc limit " + limit);
            ServiceDtoExample.Criteria criteria = example.or();
      
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
        
            List<ServiceDto> selected = dao.selectByExample(example);
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
        for(ServiceEntity entity : getAll()) {
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


	protected MapSet<ServiceEntity> startableMapSet = new MapSet<>();

	public List<ServiceEntity> getByStartableId(String startableId) {
		return startableMapSet.get(StartableEntity.newMapKey(startableId));
	}

    @Override
    public ServiceEntity get(ServiceDto dto) {
        return map.get(ServiceEntity.newMapKey(dto));
    }
       
    protected boolean checkCreated(ServiceDto dto){
        if(map.containsKey(ServiceEntity.newMapKey(dto))) return true;
        return false;
    }
    
    protected ServiceEntity load(ServiceDto dto) {
        ServiceEntity entity = newEntity(dto);
        map.put(entity.mapKey(), entity);    

		startableMapSet.put(StartableEntity.newMapKey(entity.getStartableId()), entity);
        
        return entity;
    }
    
    protected void loaded(ServiceEntity entity){
        loadPublisher.publish(new LoadEvent<ServiceEntity>(cloneOf(entity)));
    }
        
    @Override
    protected void updated(List<ServiceEntity> entities) {
        for(ServiceEntity entity : entities) updatePublisher.publish(new UpdateEvent<ServiceEntity>(cloneOf(entity)));
    }
  
    @Override
    protected void daoDeleted(List<ServiceEntity> entities) {
        super.daoDeleted(entities);
        for(ServiceEntity entity : entities) deletePublisher.publish(new DeleteEvent<ServiceEntity>(cloneOf(entity)));
		for(ServiceEntity each : entities) startableMapSet.remove(StartableEntity.newMapKey(each.getStartableId()), each);
    }
    
    @Override
    protected List<ServiceDto> dtoListToStart() {
        return dao.selectAll();
    }
}
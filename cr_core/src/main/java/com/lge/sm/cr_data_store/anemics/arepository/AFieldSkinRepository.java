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
import com.lge.sm.cr_data_store.repository.FieldSkinRepository;
import com.lge.sm.cr_data_store.dao.FieldSkinDao;
import com.lge.sm.cr_data_store.entity.SkinEntity;
import com.lge.sm.cr_data_store.repository.SkinRepository;
import com.lge.sm.cr_data_store.entity.NumericRangeEntity;
import com.lge.sm.cr_data_store.repository.NumericRangeRepository;
import com.lge.sm.cr_data_store.entity.EnumFacetEntity;
import com.lge.sm.cr_data_store.repository.EnumFacetRepository;
import com.lge.sm.cr_data_store.entity.StringRangeEntity;
import com.lge.sm.cr_data_store.repository.StringRangeRepository;
import com.lge.sm.cr_data_store.entity.DecimalRangeEntity;
import com.lge.sm.cr_data_store.repository.DecimalRangeRepository;

import com.lge.sm.cr_data_store.entity.FieldSkinEntity;
import com.lge.sm.cr_data_store.dto.FieldSkinDto;
import com.lge.sm.cr_data_store.dto.FieldSkinDtoExample;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.lge.sm.cr_data_store.repository.SkinRepository;
import com.lge.sm.cr_data_store.entity.SkinEntity;

abstract public class AFieldSkinRepository extends CacheableRepository<FieldSkinEntity, FieldSkinDao, FieldSkinDto, FieldSkinDtoExample>{
    @SuppressWarnings("unchecked")  
    private KindredEventPublisher<LoadEvent<FieldSkinEntity>> loadPublisher = (KindredEventPublisher<LoadEvent<FieldSkinEntity>>)EventBroker.getPublisher(FieldSkinEntity.class, LoadEvent.class);
    @SuppressWarnings("unchecked")  
    private KindredEventPublisher<CreateEvent<FieldSkinEntity>> createPublisher = (KindredEventPublisher<CreateEvent<FieldSkinEntity>>)EventBroker.getPublisher(FieldSkinEntity.class, CreateEvent.class);
    @SuppressWarnings("unchecked")
    private KindredEventPublisher<UpdateEvent<FieldSkinEntity>> updatePublisher = (KindredEventPublisher<UpdateEvent<FieldSkinEntity>>)EventBroker.getPublisher(FieldSkinEntity.class, UpdateEvent.class);
    @SuppressWarnings("unchecked")
    private KindredEventPublisher<DeleteEvent<FieldSkinEntity>> deletePublisher = (KindredEventPublisher<DeleteEvent<FieldSkinEntity>>)EventBroker.getPublisher(FieldSkinEntity.class, DeleteEvent.class);

    @Autowired
    public AFieldSkinRepository(FieldSkinDao dao) {
        super(dao);
    }
    


    @Override
    public FieldSkinEntity create(FieldSkinDto dto) throws IllegalArgumentException {
        if(checkCreated(dto) == true) throw new IllegalArgumentException("Already created : " + ToString.toLine(dto));    
        if(checkForeignKeyEntityExist(dto) == false) throw new IllegalArgumentException("No record of foreign key when create : " + ToString.toLine(dto));
        if(dao.insert(dto) == false) throw new IllegalArgumentException();
            
        FieldSkinEntity ret = load(dto);
        createPublisher.publish(new CreateEvent<FieldSkinEntity>(cloneOf(ret)));
        
        return ret;
    }
  
    protected boolean checkForeignKeyEntityExist(FieldSkinDto dto) {
		if(Repos.repo(SkinRepository.class).getByMapKey(SkinEntity.newMapKey(dto.getSkinId())) == null) return false;

        return true;
    }
        
    public FieldSkinEntity cloneOf(FieldSkinEntity entity) {
        FieldSkinEntity ret = newEntity(entity.getDto());

        return ret;
    }
  
    protected FieldSkinEntity newEntity(FieldSkinDto dto){
        return new FieldSkinEntity(dto);
    }
    
    @Transactional
    @Override
    protected boolean deleteDao(List<FieldSkinEntity> entities) {
        super.deleteDao(entities);
		List<NumericRangeEntity> numericRangeList = new ArrayList<>();
		for(FieldSkinEntity each : entities) numericRangeList.addAll(Repos.repo(NumericRangeRepository.class).getByFieldSkinId(each.getFieldSkinId()));
		if(Repos.repo(NumericRangeRepository.class).delete(numericRangeList) == false) return false;
		List<EnumFacetEntity> enumFacetList = new ArrayList<>();
		for(FieldSkinEntity each : entities) enumFacetList.addAll(Repos.repo(EnumFacetRepository.class).getByFieldSkinId(each.getFieldSkinId()));
		if(Repos.repo(EnumFacetRepository.class).delete(enumFacetList) == false) return false;
		List<StringRangeEntity> stringRangeList = new ArrayList<>();
		for(FieldSkinEntity each : entities) stringRangeList.addAll(Repos.repo(StringRangeRepository.class).getByFieldSkinId(each.getFieldSkinId()));
		if(Repos.repo(StringRangeRepository.class).delete(stringRangeList) == false) return false;
		List<DecimalRangeEntity> decimalRangeList = new ArrayList<>();
		for(FieldSkinEntity each : entities) decimalRangeList.addAll(Repos.repo(DecimalRangeRepository.class).getByFieldSkinId(each.getFieldSkinId()));
		if(Repos.repo(DecimalRangeRepository.class).delete(decimalRangeList) == false) return false;
 
        return dao.delete(Repos.repo(FieldSkinRepository.class).getDtoList(entities));
    }
   
    @Override
    protected boolean initDescendent() {


		parentSkinTypes.add(SkinEntity.skinType());

		kidSkinTypes.add(NumericRangeEntity.skinType());
		kidSkinTypes.add(EnumFacetEntity.skinType());
		kidSkinTypes.add(StringRangeEntity.skinType());
		kidSkinTypes.add(DecimalRangeEntity.skinType());
        return true;
    }
    
    public FieldSkinDtoExample example(){
        return new FieldSkinDtoExample();
    }
    
    private FieldSkinDtoExample criteriaFactory = new FieldSkinDtoExample();
    public FieldSkinDtoExample.Criteria criteria(){
        return criteriaFactory.createCriteria();
    }
    
    public List<FieldSkinEntity> getByCriteria(FieldSkinDtoExample.Criteria c){
        FieldSkinDtoExample newExample = new FieldSkinDtoExample();
        newExample.or(c);
        List<FieldSkinDto> dtos = dao.selectByExample(newExample);
        List<FieldSkinEntity> entities = new ArrayList<>();
        if(dtos == null) return entities;
        for(FieldSkinDto each : dtos) entities.add(load(each));
      
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
            FieldSkinDto dto = om.treeToValue(inputNode, FieldSkinDto.class);
            FieldSkinEntity entity = get(dto);
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
  
    public String skinized(FieldSkinEntity entity) {
        return skinize(entity);
    }
    
    protected String skinize(FieldSkinEntity entity) {
        return skinize(entity.getDto());
    }
  
    protected String skinize(FieldSkinDto dto) {
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
        return FieldSkinEntity.skinType();
    }

    @Override
    public Skin skin() {
        SkinEntity entity = Repos.repo(SkinRepository.class).getByMapKey(SkinEntity.newMapKey(skinType()));
        return entity.skin(getParentSkinTypes(), getKidSkinTypes());
    }
    
    protected FieldSkinDto jsonNodeToDto(JsonNode node) {
        ObjectMapper om = JsonUtil.objectMapper();
        try {
            FieldSkinDto dto = om.treeToValue(node, FieldSkinDto.class);
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
        FieldSkinDto dto = jsonNodeToDto(inputNode);
        if(dto == null) return "";
        FieldSkinEntity entity = create(dto);
        if(entity != null) return skinized(entity);
        return "";
    }
    
    public String update(JsonNode inputNode) {
        FieldSkinDto dto = jsonNodeToDto(inputNode);
        if(dto == null) return "";
        FieldSkinEntity entity = get(dto);
        if(entity != null){
          boolean ret = update(newEntity(dto));
          if(ret) return skinized(get(dto));
        }
        return "";
    }
    
    public boolean delete(JsonNode inputNode) {
        FieldSkinDto dto = jsonNodeToDto(inputNode);
        if(dto == null) return false;
        FieldSkinEntity entity = get(dto);
        return delete(entity);
    }
    
    public String getSkinizedKids(JsonNode node, String kidSkinType) {
        FieldSkinDto dto = jsonNodeToDto(node);
        if(dto == null) return "";
        FieldSkinEntity entity = get(dto);

		if(kidSkinType.equals("NumericRange")) {
			List<NumericRangeEntity> list = entity.getNumericRangeEntityList();
			NumericRangeRepository repo = Repos.repo(NumericRangeRepository.class);
			StringBuffer ret = new StringBuffer();
			ret.append("[");
			for(int i = 0; i < list.size(); i++) {
				NumericRangeEntity each = list.get(i);
				ret.append(repo.skinized(each));
				if(i != list.size()-1) ret.append(",");
			}
			ret.append("]");
			return ret.toString();
		}
		if(kidSkinType.equals("EnumFacet")) {
			List<EnumFacetEntity> list = entity.getEnumFacetEntityList();
			EnumFacetRepository repo = Repos.repo(EnumFacetRepository.class);
			StringBuffer ret = new StringBuffer();
			ret.append("[");
			for(int i = 0; i < list.size(); i++) {
				EnumFacetEntity each = list.get(i);
				ret.append(repo.skinized(each));
				if(i != list.size()-1) ret.append(",");
			}
			ret.append("]");
			return ret.toString();
		}
		if(kidSkinType.equals("StringRange")) {
			List<StringRangeEntity> list = entity.getStringRangeEntityList();
			StringRangeRepository repo = Repos.repo(StringRangeRepository.class);
			StringBuffer ret = new StringBuffer();
			ret.append("[");
			for(int i = 0; i < list.size(); i++) {
				StringRangeEntity each = list.get(i);
				ret.append(repo.skinized(each));
				if(i != list.size()-1) ret.append(",");
			}
			ret.append("]");
			return ret.toString();
		}
		if(kidSkinType.equals("DecimalRange")) {
			List<DecimalRangeEntity> list = entity.getDecimalRangeEntityList();
			DecimalRangeRepository repo = Repos.repo(DecimalRangeRepository.class);
			StringBuffer ret = new StringBuffer();
			ret.append("[");
			for(int i = 0; i < list.size(); i++) {
				DecimalRangeEntity each = list.get(i);
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
        List<FieldSkinDto> dtos = new ArrayList<>();
        Object[] keySet = maps.keySet().toArray();
        int idx[] = new int[keySet.length];
            
        while(true){
            FieldSkinDtoExample example = example();
            example.setOrderByClause("cdate desc limit " + limit);
            FieldSkinDtoExample.Criteria criteria = example.or();
      
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
        
            List<FieldSkinDto> selected = dao.selectByExample(example);
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
        for(FieldSkinEntity entity : getAll()) {
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


	protected MapSet<FieldSkinEntity> skinMapSet = new MapSet<>();

	public List<FieldSkinEntity> getBySkinId(String skinId) {
		return skinMapSet.get(SkinEntity.newMapKey(skinId));
	}

    @Override
    public FieldSkinEntity get(FieldSkinDto dto) {
        return map.get(FieldSkinEntity.newMapKey(dto));
    }
       
    protected boolean checkCreated(FieldSkinDto dto){
        if(map.containsKey(FieldSkinEntity.newMapKey(dto))) return true;
        return false;
    }
    
    protected FieldSkinEntity load(FieldSkinDto dto) {
        FieldSkinEntity entity = newEntity(dto);
        map.put(entity.mapKey(), entity);    

		skinMapSet.put(SkinEntity.newMapKey(entity.getSkinId()), entity);
        
        return entity;
    }
    
    protected void loaded(FieldSkinEntity entity){
        loadPublisher.publish(new LoadEvent<FieldSkinEntity>(cloneOf(entity)));
    }
        
    @Override
    protected void updated(List<FieldSkinEntity> entities) {
        for(FieldSkinEntity entity : entities) updatePublisher.publish(new UpdateEvent<FieldSkinEntity>(cloneOf(entity)));
    }
  
    @Override
    protected void daoDeleted(List<FieldSkinEntity> entities) {
        super.daoDeleted(entities);
        for(FieldSkinEntity entity : entities) deletePublisher.publish(new DeleteEvent<FieldSkinEntity>(cloneOf(entity)));
		List<NumericRangeEntity> numericRangeList = new ArrayList<>();
		for(FieldSkinEntity each : entities) numericRangeList.addAll(Repos.repo(NumericRangeRepository.class).getByFieldSkinId(each.getFieldSkinId()));
		Repos.repo(NumericRangeRepository.class).daoDeleted(numericRangeList);
		List<EnumFacetEntity> enumFacetList = new ArrayList<>();
		for(FieldSkinEntity each : entities) enumFacetList.addAll(Repos.repo(EnumFacetRepository.class).getByFieldSkinId(each.getFieldSkinId()));
		Repos.repo(EnumFacetRepository.class).daoDeleted(enumFacetList);
		List<StringRangeEntity> stringRangeList = new ArrayList<>();
		for(FieldSkinEntity each : entities) stringRangeList.addAll(Repos.repo(StringRangeRepository.class).getByFieldSkinId(each.getFieldSkinId()));
		Repos.repo(StringRangeRepository.class).daoDeleted(stringRangeList);
		List<DecimalRangeEntity> decimalRangeList = new ArrayList<>();
		for(FieldSkinEntity each : entities) decimalRangeList.addAll(Repos.repo(DecimalRangeRepository.class).getByFieldSkinId(each.getFieldSkinId()));
		Repos.repo(DecimalRangeRepository.class).daoDeleted(decimalRangeList);

    }
    
    @Override
    protected List<FieldSkinDto> dtoListToStart() {
        return dao.selectAll();
    }
}
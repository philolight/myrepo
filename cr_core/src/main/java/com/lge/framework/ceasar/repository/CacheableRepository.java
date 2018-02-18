package com.lge.framework.ceasar.repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.transaction.annotation.Transactional;

import com.lge.framework.ceasar.dao.DataAccessObject;
import com.lge.framework.ceasar.entity.Entity;
import com.lge.framework.ceasar.entity.MapKey;
import com.lge.framework.ceasar.startable.StartableStatus;
import com.lge.framework.ceasar.util.ToString;

abstract public class CacheableRepository<ET extends Entity<DTO>, DAO extends DataAccessObject<DTO, EX>, DTO, EX>
	extends PermanenceRepository<ET, DAO, DTO, EX> {
	protected String TAG;

	protected Map<MapKey, ET> map = new ConcurrentHashMap<>();	// 동기화가 매우 중요
	
	public CacheableRepository(DAO dao){
		super(dao);
	}

	/**
	 * 시스템 기동 시 Repo.에 대한 초기화 작업을 수행하는 메소드. 
	 */
	@Override
	public boolean init() {
		super.init();
		if(getStartableStatus() != StartableStatus.DEFAULT) return true; // 중복 init() 방지
		
		List<DTO> dtos = dtoListToStart();
		if(dtos == null) {
			new Exception("Repository Start Error : " + this.getClass());
			return false;
		}
	
		for(DTO each : dtos) {
			ET entity = load(each);
			loaded(entity);
		}
		
		return initDescendent();
	}
	
	abstract protected List<DTO> dtoListToStart();
		
	abstract protected void loaded(ET entity);
	
	/**
	 * DTO 리스트를 입력으로 받아서 ET 리스트를 리턴하는 메소드.
	 */
	public List<ET> get(List<DTO> list) throws IllegalArgumentException {
		List<ET> ret = new ArrayList<ET>();
		for(DTO each : list) {
			ET object = get(each);
			if(object == null) throw new IllegalArgumentException();
		}
		
		return ret;
	}
		
	/**
	 * MapKey를 입력으로 받아서 ET를 리턴하는 메소드.
	 */
	public ET getByMapKey(MapKey mapKey) {
		return map.get(mapKey);
	}
	
	/**
	 * Repo.에 등록되어 있는 모든 ET를 리턴하는 메소드
	 */
	public List<ET> getAll(){
		synchronized(map) {
			return new ArrayList<>(map.values());
		}
	}
	
	/**
	 * ET의 필드 값이 수정될 때 호출되는 메소드.
	 * ET의 필드 값은 필드별로 수정됨.
	 */
	public boolean update(ET entity) {
		return update(Arrays.asList(entity));
	}
	
	@Transactional
	public boolean update(List<ET> entities) {
		if(dao.update(getDtoList(entities)) == false) return false;
		for(ET entity : entities) {
			map.put(entity.mapKey(), entity);
		}
		updated(entities);
		return true;
	}
	
	abstract protected void updated(List<ET> entities);
	
	/** Delete 이전에 하위 클래스들이 해야 할 작업을 정의하는 메소드.
	 *	주로 해당 entity에 대한 의존성이 있는 레코드들을 우선 제거하는데 사용.
	 */
	protected boolean deleteDao(List<ET> entities) { return true; }
	
	/** Delete 이후에 하위 클래스들이 해야 할 작업을 정의하는 메소드. */
	protected void daoDeleted(List<ET> entities) {
		for(ET entity : entities) {
			map.remove(entity.mapKey(), entity);
		}
	}
	
	@Override
	public boolean delete(List<ET> entities) {
		if(deleteDao(entities) == false) return false;
		daoDeleted(entities);
		return true;
	}	
	
	@Transactional
	@Override
	public boolean deleteAll() {
		List<ET> list = new ArrayList<>();
		list.addAll(map.values());
		return delete(list);
	}
		
	public List<DTO> getDtoList() {
		List<DTO> ret = new ArrayList<DTO>();
		
		for(Map.Entry<MapKey, ET> each : map.entrySet()) ret.add(each.getValue().getDto());
		return ret;
	}
}

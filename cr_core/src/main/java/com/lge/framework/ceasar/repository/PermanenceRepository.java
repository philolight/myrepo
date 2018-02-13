package com.lge.framework.ceasar.repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.lge.framework.ceasar.dao.DataAccessObject;
import com.lge.framework.ceasar.dao.DataSelectObject;
import com.lge.framework.ceasar.entity.Entity;
import com.lge.framework.ceasar.service.view.Skinner;
import com.lge.framework.ceasar.service.view.SkinnerManager;
import com.lge.framework.ceasar.startable.Startable;
import com.lge.framework.ceasar.startable.StartableStatus;
import com.lge.framework.ceasar.startable.StartableType;
import com.lge.framework.ceasar.startable.Starter;
import com.lge.framework.pacific.logger.Logger;

abstract public class PermanenceRepository <ET extends Entity<DTO>, DAO extends DataAccessObject<DTO, EX>, DTO, EX>
implements DtoCollector<DTO, EX>, Startable, Skinner{
	protected String TAG;

	//as a Startable ----------------------------------------------------------------------------------------------------------------------

	protected StartableStatus status = StartableStatus.DEFAULT;

	@Override
	public StartableType getStartableType() { return StartableType.REPOSITORY; }

	@Override
	public StartableStatus getStartableStatus() { return status; }

	@Override
	public void setStatus(StartableStatus status) {
		this.status = status;
	}

	//--------------------------------------------------------------------------------------------------------------------------------------

	protected DAO dao;

	public PermanenceRepository(DAO dao){
		this.dao = dao;

		Repos.add(this);
		Starter.add(this);
	}

	/** 시스템 기동 시 Repo.에 대한 초기화 작업을 수행하는 메소드. */
	@Override
	public boolean init() {
		if(getStartableStatus() != StartableStatus.DEFAULT) return true; // 중복 init() 방지
        SkinnerManager.regist(this);
		return initDescendent();
	}
	
	@Override public boolean start() { return true; }
	@Override public boolean stop() { return true; }

	// 하위 클래스에서 초기화가 더 필요한 경우에 Override 할 메소드.
	protected boolean initDescendent() {return true;}
	
	abstract protected ET load(DTO dto);

	/** DTO를 입력으로 받아서 ET를 생성하고, 생성된 ET를 리턴하는 메소드. */
	abstract public ET create(DTO dto) throws IllegalArgumentException;
	
	/** DTO를 입력으로 받아서 ET를 리턴하는 메소드. */
	abstract public ET get(DTO dto);

	public List<ET> getByExample(EX example){
		List<DTO> dtoList = dao.selectByExample(example);
		if(dtoList == null) {
			Logger.error(TAG, "Database select failed");
			return new ArrayList<>();
		}
		List<ET> ret = new ArrayList<>();

		for(DTO each : dtoList) {
			ET entity = load(each);
			ret.add(entity);
		}

		return ret;
	}
	
	/**
	 * ET의 필드 값이 수정될 때 호출되는 메소드.
	 * ET의 필드 값은 필드별로 수정됨.
	 */
	public boolean update(ET entity) {
		return update(Arrays.asList(entity));
	}
	
	public boolean update(List<ET> entities) {
		if(dao.update(getDtoList(entities)) == false) return false;
		return true;
	}

	public boolean delete(ET entity) {
		return delete(Arrays.asList(entity));
	}

	/** Delete 이전에 하위 클래스들이 해야 할 작업을 정의하는 메소드.
	 *	주로 해당 entity에 대한 의존성이 있는 레코드들을 우선 제거하는데 사용.
	 */
	protected boolean deleteDao(List<ET> entities) { return true; }

	public boolean delete(List<ET> entities) {
		if(deleteDao(entities) == false) return false;
		return true;
	}

	public boolean deleteAll() {
		return dao.deleteAll();
	}

	public List<DTO> getDtoList(List<ET> entityList){
		List<DTO> ret = new ArrayList<DTO>();
		for(ET each : entityList) ret.add(each.getDto());
		return ret;
	}

	public DataSelectObject<DTO, EX> getDso(){
		return dao;
	}

	abstract public ET cloneOf(ET entity);
}

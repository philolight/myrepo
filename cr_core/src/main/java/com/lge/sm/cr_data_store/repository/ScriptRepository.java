package com.lge.sm.cr_data_store.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Repository;

import com.lge.framework.ceasar.util.ListUtil;
import com.lge.sm.cr_data_store.anemics.arepository.AScriptRepository;
import com.lge.sm.cr_data_store.dao.ScriptDao;
import com.lge.sm.cr_data_store.dto.ScriptDto;
import com.lge.sm.cr_data_store.entity.ScriptEntity;

@Repository
public class ScriptRepository extends AScriptRepository{
	protected Map<String/*ScriptName*/, List<ScriptEntity>> scriptByNameMap = new ConcurrentHashMap<>();
	
    public ScriptRepository(ScriptDao dao) {
        super(dao);
        TAG = this.getClass().getSimpleName();
    }

    /** Entity가 생성되었을 때 scriptName으로 검색 가능하도록 load() 메소드를 오버라이드 함 */
    @Override
    protected ScriptEntity load(ScriptDto dto) {
    	ScriptEntity entity = super.load(dto);
        
    	synchronized(scriptByNameMap) {
	    	List<ScriptEntity> scriptEntities = scriptByNameMap.get(dto.getScriptName());
	    	if(scriptEntities == null) {
	    		scriptEntities = new ArrayList<>();
	    		scriptByNameMap.put(dto.getScriptName(), scriptEntities);
	    	}
    		scriptEntities.add(entity);
    	}
    	
        return entity;
    }
    
	/** Entity가 삭제되었을 때 scriptName으로 검색 가능하도록 load() 메소드를 오버라이드 함 */
	protected void daoDeleted(List<ScriptEntity> entities) {
		for(ScriptEntity entity : entities) {
			synchronized(scriptByNameMap) {
				List<ScriptEntity> list = scriptByNameMap.get(entity.getScriptName());
	    		list.remove(entity);
			}
		}
		super.daoDeleted(entities);
	}

    @Override
    public String getStartableId() {
        return this.getClass().getSimpleName();
    }

	public List<ScriptEntity> getByScriptName(String scriptName) {
		synchronized(scriptByNameMap) {
			List<ScriptEntity> list = scriptByNameMap.get(scriptName);
			return ListUtil.copy(list);
		}
	}
}

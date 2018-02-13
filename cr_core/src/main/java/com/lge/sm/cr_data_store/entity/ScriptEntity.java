package com.lge.sm.cr_data_store.entity;


import com.lge.framework.ceasar.repository.Repos;
import com.lge.framework.ceasar.service.view.Script;
import com.lge.sm.cr_data_store.anemics.aentity.AScriptEntity;
import com.lge.sm.cr_data_store.dto.ScriptDto;
import com.lge.sm.cr_data_store.repository.ScriptRepository;

public class ScriptEntity extends AScriptEntity implements Cloneable{
    private static final String TAG = ScriptEntity.class.getSimpleName();
  
    protected ScriptEntity() {} // for Serialize
  
    public ScriptEntity(ScriptDto dto) {
        super(dto);
    }

    @Override
    public boolean flush() {
        return Repos.repo(ScriptRepository.class).update(this);
    }
    
    @Override
    protected ScriptEntity clone() throws CloneNotSupportedException {
        return Repos.repo(ScriptRepository.class).cloneOf(this);
    }
    
    public Script skin() {
    	return new Script(dto);
    }
}

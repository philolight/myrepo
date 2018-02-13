package com.lge.framework.ceasar.service.view;

import com.lge.sm.cr_data_store.dto.ScriptDto;

public class Script {
    private Long scriptId;
    private String text;
    private String language;
    private String scriptName;
	public Script(ScriptDto dto) {
		scriptId = dto.getScriptId();
		text = dto.getText();
		language = dto.getLanguage();
		scriptName = dto.getScriptName();
	}
}

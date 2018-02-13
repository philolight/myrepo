package com.lge.framework.ceasar.service.view;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lge.framework.ceasar.repository.Repos;
import com.lge.framework.ceasar.util.DateStringUtil;
import com.lge.framework.ceasar.util.JsonUtil;
import com.lge.sm.cr_data_store.dto.FieldSkinDto;
import com.lge.sm.cr_data_store.dto.SkinDto;
import com.lge.sm.cr_data_store.dto.StringRangeDto;
import com.lge.sm.cr_data_store.repository.FieldSkinRepository;
import com.lge.sm.cr_data_store.repository.SkinRepository;
import com.lge.sm.cr_data_store.repository.StringRangeRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:com/lge/sm/cr_data_store/data_store_test_context.xml"})
public class SkinnerManagerTest {
	private SkinDto skinDto = new SkinDto();
	private FieldSkinDto fieldSkinDto = new FieldSkinDto();
	private StringRangeDto stringRangeDto = new StringRangeDto();
	
	@Before
	public void setUp() {
		Repos.repo(StringRangeRepository.class).init();
		Repos.repo(StringRangeRepository.class).deleteAll();
		System.out.println("setUp stringRange deleted");
		Repos.repo(StringRangeRepository.class).start();
		Repos.repo(FieldSkinRepository.class).init();
		Repos.repo(FieldSkinRepository.class).deleteAll();
		System.out.println("setUp field skin deleted");
		Repos.repo(FieldSkinRepository.class).start();
		Repos.repo(SkinRepository.class).init();
		Repos.repo(SkinRepository.class).deleteAll();
		System.out.println("setUp skin deleted");
		Repos.repo(SkinRepository.class).start();
		
		String cdate = DateStringUtil.getCurrentDateString("GMT");
		
		skinDto.setCdate(cdate);
		skinDto.setSkinId("Skin");
		Repos.repo(SkinRepository.class).create(skinDto);
				
		fieldSkinDto.setCdate(cdate);
		fieldSkinDto.setEditable(0);
		fieldSkinDto.setEncryption(0);
		fieldSkinDto.setFieldSkinId("Skin.skinId");
		fieldSkinDto.setHideTyping(0);
		fieldSkinDto.setName("skinId");
		fieldSkinDto.setNillable(0);
		fieldSkinDto.setSkinId("Skin");
		fieldSkinDto.setType("String");
		fieldSkinDto.setVisible(0);
		fieldSkinDto.setAutoFill(0);
		fieldSkinDto.setIsFk(0);
		fieldSkinDto.setIsPk(0);
		Repos.repo(FieldSkinRepository.class).create(fieldSkinDto);		
	
		stringRangeDto.setStringRangeId(Repos.repo(StringRangeRepository.class).getNextId());
		stringRangeDto.setCdate(cdate);
		stringRangeDto.setFieldSkinId("Skin.skinId");
		stringRangeDto.setLength(100);
		stringRangeDto.setRegex("*");
		Repos.repo(StringRangeRepository.class).create(stringRangeDto);
	}
	
	@After
	public void tearDown() {
		Repos.repo(StringRangeRepository.class).deleteAll();
		System.out.println("stringRange deleted");
		Repos.repo(FieldSkinRepository.class).deleteAll();
		System.out.println("field skin deleted");
		Repos.repo(SkinRepository.class).deleteAll();
		System.out.println("skin deleted");
	}
	
	@Test
	public void testGet() throws Exception{
		assertThat(Repos.repo(SkinRepository.class), equalTo(SkinnerManager.get("Skin")));
		
		Skin skin = SkinnerManager.get("Skin").skin();
		
		ObjectMapper om = JsonUtil.objectMapper();
		
		System.out.println(om.writeValueAsString(skin));
		
		JsonNode node = om.convertValue(skin, JsonNode.class);
		
//		System.out.println("node = " + om.writeValueAsString(node) );
		
		assertThat(om.writeValueAsString(node.get("skinType")), equalTo("\"Skin\""));
	}
}
package com.lge.sm.cr_core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;

import com.lge.framework.ceasar.logger.Logger;
import com.lge.sm.cr_core.cr_task_manager.CrTaskManager;

@ComponentScan({"com.lge.sm.cr_data_store", "com.lge.sm.web", "com.lge.sm.cr_core"}) // 순서가 중요
@SpringBootApplication
@ImportResource("classpath:spring/application_context.xml")
public class App {
  private static final String TAG = App.class.getSimpleName();
   
  public static void main(String[] args) throws Exception{
	  SpringApplication.run(App.class, args);
  }

  protected void finalize() {
	  Logger.debug(TAG, "CrTaskManager stop");
	  CrTaskManager.getInstance().stop();
  }
}

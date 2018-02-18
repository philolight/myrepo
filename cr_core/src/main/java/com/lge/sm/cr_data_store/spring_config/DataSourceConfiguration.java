package com.lge.sm.cr_data_store.spring_config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import com.lge.framework.ceasar.logger.Logger;

@Configuration
public class DataSourceConfiguration {
	private static final String TAG = DataSourceConfiguration.class.getSimpleName();
	
    @Bean
    public DataSource dataSource() {
    	Logger.info(TAG, "data source create");
    	
        DriverManagerDataSource dataSource = new DriverManagerDataSource(
        		"jdbc:mariadb://localhost:3306/smart_building",
        		"root",
        		"root"
        		);
        dataSource.setDriverClassName("org.mariadb.jdbc.Driver");
        
        Logger.info(TAG, "data source create done.");
        
        return dataSource;
    }
}
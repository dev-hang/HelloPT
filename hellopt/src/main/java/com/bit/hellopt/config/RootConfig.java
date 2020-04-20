package com.bit.hellopt.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@ComponentScan(basePackages = { "com.bit.hellopt" })
@Configuration
public class RootConfig {

	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("oracle.jdbc.driver.OracleDriver");

		dataSource.setUrl("jdbc:oracle:thin:@oracle-database-1.corysvhuak3l.ap-northeast-2.rds.amazonaws.com:1521:ORCL");
		dataSource.setUsername("admin");
	    dataSource.setPassword("danhobak");
	    return dataSource;
	}
	
//	@Bean
//	public DataSource dataSource() {
//		DriverManagerDataSource dataSource = new DriverManagerDataSource();
//		dataSource.setDriverClassName("oracle.jdbc.driver.OracleDriver");
//
//		dataSource.setUrl("jdbc:oracle:thin:@localhost:1521:xe");
//		dataSource.setUsername("hellosuna");
//	    dataSource.setPassword("hellosuna");
//	    return dataSource;
//	}
}

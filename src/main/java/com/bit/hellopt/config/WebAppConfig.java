package com.bit.hellopt.config;

import java.io.File;
import java.util.Properties;

import javax.servlet.MultipartConfigElement;
import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;


@EnableWebMvc
@ComponentScan(basePackages = { "com.bit.hellopt" })
//MyBatis 사용을 위한 Mapper 인터페이스 찾기
@MapperScan("com.bit.hellopt.data")
@Configuration
							//웹설정 가능한 인터페이스 상속받아서... 뷰 리졸버같은거..쓴다.. 
							//디슾패셔써블릿 설정들..
public class WebAppConfig implements WebMvcConfigurer {

	DataSource dataSource;
	@Autowired
	ApplicationContext applicationContext;
	
	//rootConfig에 있는거 의존성 주입한거
	public WebAppConfig(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	//바로 URL과 VIEW를 바로 매핑시켜줌
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {

		//registry.addViewController("/signupform").setViewName("signupForm");
		registry.addViewController("/review/insertform").setViewName("review/revInsertForm");
		registry.addViewController("/openClassForm").setViewName("openClassForm");
		registry.addViewController("/classDetail").setViewName("classDetail");
		registry.addViewController("/openClassForm").setViewName("class/openClassForm");
		registry.addViewController("/main").setViewName("main");
		registry.addViewController("/login").setViewName("user/login");
		registry.addViewController("/faq1").setViewName("faq1");
		registry.addViewController("/faq2").setViewName("faq2");
		registry.addViewController("/audition").setViewName("audition");
		registry.addViewController("/exerciseInfo").setViewName("exerciseInfo");
		registry.addViewController("/exerciseInfoList").setViewName("exerciseInfoList");
		registry.addViewController("/exerciseInfoTip").setViewName("exerciseInfoTip");
		registry.addViewController("/exerciseInfoTipList").setViewName("exerciseInfoTipList");
		registry.addViewController("/trainer").setViewName("trainer");
		registry.addViewController("/trainerinfo").setViewName("trainerinfo");
		registry.addViewController("/trainerinsert").setViewName("trainerinsert");
		registry.addViewController("/traineradmin").setViewName("traineradmin");
		registry.addViewController("/trainerupdate").setViewName("trainerupdate");
		registry.addViewController("/trainerupdatepage").setViewName("trainerupdatepage");
		registry.addViewController("/multi").setViewName("class/multi");
		registry.addViewController("/pop").setViewName("pop");
	}

	//Controller에서 View 리턴 시 View 위치와 확장자를 설정
	@Bean
	public ViewResolver viewResolver() {
		InternalResourceViewResolver bean = new InternalResourceViewResolver();

		bean.setViewClass(JstlView.class);
		bean.setPrefix("/WEB-INF/views/");
		bean.setSuffix(".jsp");

		return bean;
	}

	// 정적 파일(css, js, html)을 사용하기 위한 설정
	// webapp/resources에 정적 파일을들 놓고 resources/로 접근하면 사용가능
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
		registry.addResourceHandler("/file/**").addResourceLocations("file:///C:/hellopt_file/");
	}

	// MyBatis 연동을 위한 설정
	@Bean
	public SqlSessionFactory sqlSessionFactory() throws Exception {
	  SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
	  // 데이터베이스 설정 불러오기
	  factoryBean.setDataSource(dataSource);
	  // MyBatis 설정파일 불러오기
	  factoryBean.setConfigLocation(applicationContext.getResource("classpath:MyBatis-Config.xml"));
	  // Mapper 불러오기(여기서 mapper 파일을 등록하기 때문에 MyBatis 설정 파일에 매퍼를 등록하지 않아도 됨)
	  factoryBean.setMapperLocations(applicationContext.getResources("classpath:mappings/**.xml"));
	  return factoryBean.getObject();
	}
	
	@Bean
	public CommonsMultipartResolver multipartResolver() {
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
		//multipartResolver.setMaxUploadSize(1000000);
		multipartResolver.setDefaultEncoding("UTF-8");
		multipartResolver.setMaxUploadSize(52428800);
		return multipartResolver;
	}
	
	@Bean
	public MultipartConfigElement multipartConfigElement() {
		int maxUploadSizeInMb = 1024 * 1024;
		//File uploadDirectory 수정할것~!~!~!~!~!~!
//		File uploadDirectory = new File(System.getProperty("java.io.tmpdir"));
//		MultipartConfigElement multipartConfigElement;
		File uploadDirectory = new File("classpath:resources/images");
		MultipartConfigElement multipartConfigElement = new MultipartConfigElement(uploadDirectory.getAbsolutePath(), maxUploadSizeInMb, maxUploadSizeInMb * 2,maxUploadSizeInMb / 2);
		return multipartConfigElement;
	}
	
	@Bean
	public DataSourceTransactionManager transactionManager() {
		return new DataSourceTransactionManager(dataSource);
	}
	
	@Bean
	public JavaMailSender getJavaMailSender() {
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		mailSender.setHost("smtp.gmail.com");
		mailSender.setPort(587);
		
		mailSender.setUsername("username");
		mailSender.setPassword("password");
		
		Properties props = mailSender.getJavaMailProperties();
		props.put("mail.transport.protocol", "smtp");
	    props.put("mail.smtp.auth", "true");
	    props.put("mail.smtp.starttls.enable", "true");
	    props.put("mail.debug", "true");
		
		return mailSender;
	}
	

}

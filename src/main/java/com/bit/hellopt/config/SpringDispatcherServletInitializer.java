package com.bit.hellopt.config;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration.Dynamic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class SpringDispatcherServletInitializer extends AbstractAnnotationConfigDispatcherServletInitializer { // extend 뒤에 Servlet Dispatcher 하는것
	
	@Autowired
	MultipartConfigElement multipartConfig;
    
	@Override
    protected Class <?> [] getRootConfigClasses() {
        return new Class[] {
        	RootConfig.class,//기본설정 ->
            SecurityConfig.class

        };
    }

	//웹싸이트마다 사용하는 설정들
    @Override
    protected Class <?> [] getServletConfigClasses() {
        return new Class[] {
            WebAppConfig.class //웹사이트 설정
            , WebSocketConfig.class //웹소켓
        };
    }

    
    @Override
    protected String[] getServletMappings() {
        return new String[] {
            "/" // 루트 컨텍스트로 오는 요청 다 매핑함
        };
    }
  
    @Override
    protected void customizeRegistration(Dynamic registration) {
        registration.setMultipartConfig(multipartConfig);
    }
    
    
}

package com.bit.hellopt.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.server.standard.ServletServerContainerFactoryBean;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

import com.bit.hellopt.controller.meeting.MeetingAlarmHandler;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
																//https로 설정되어 있는것도 받겠다............
		registry.addHandler(new MeetingAlarmHandler(), "/alarm").setAllowedOrigins("https://hellopt.info")
				.addInterceptors(new HttpSessionHandshakeInterceptor());
	}
	
	@Bean
	public WebSocketHandler meetingAlarmHandler() {
		return new MeetingAlarmHandler();
	}
	
	@Bean
	public ServletServerContainerFactoryBean creatWebSocketContainer() {
		ServletServerContainerFactoryBean container = new ServletServerContainerFactoryBean();
		container.setMaxTextMessageBufferSize(8192);
		container.setMaxBinaryMessageBufferSize(8192);
		return container;
	}
	
	

}

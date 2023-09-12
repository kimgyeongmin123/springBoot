package com.example.demo.listener;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

// SevletEventClass..
// SevletContextEvent
// SevletContextAttributeEvent
// RequestContextEvent..
//

public class CustomContextRefreshedListener implements ApplicationListener<ContextRefreshedEvent>{

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		System.out.println("[LISTENER] CustomContextRefreshedListener's onApplicationEvent " +event.getSource());
	}
	

}

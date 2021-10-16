package com.revature.config;

import org.springframework.context.ApplicationContext;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.session.web.http.DefaultCookieSerializer;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class HttpConfig {
	
	private final ApplicationContext applicationContext;

	@EventListener
	public void onApplicationEvent(final ContextRefreshedEvent event) {
		final DefaultCookieSerializer cookieSerializer = this.applicationContext.getBean(DefaultCookieSerializer.class);
		System.out.println("Received DefaultCookieSerializer, Overriding SameSite Strict");
		cookieSerializer.setSameSite("None");
	}
}

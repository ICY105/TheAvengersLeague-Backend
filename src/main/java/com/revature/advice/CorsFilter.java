package com.revature.advice;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.revature.controller.Visitor;

@Component
@WebFilter("/*")
public class CorsFilter extends OncePerRequestFilter {
	
	private static final Map<String,Visitor> sessions = new HashMap<>();

	@Override
	protected void doFilterInternal(final HttpServletRequest req, final HttpServletResponse resp,
			final FilterChain chain) throws ServletException, IOException {
		resp.setHeader("Access-Control-Allow-Origin", "*");
		resp.setHeader("Access-Control-Allow-Credentials", "true");
		resp.setHeader("Access-Control-Allow-Methods", "GET,HEAD,OPTIONS,POST,PUT");
		resp.setHeader("Access-Control-Allow-Headers",
				"Access-Control-Allow-Headers, Origin, Accept, X-Requested-With, Content-Type, Access-Control-Request-Method, Access-Control-Request-Headers, Authorization");
		resp.setHeader("Access-Control-Expose-Headers", "Authorization");

		final HttpSession session = req.getSession();
		
		final String token = req.getHeader("Authorization");
		if(token == null || !sessions.containsKey(token)) {
			final String newToken = UUID.randomUUID().toString();
			final Visitor visitor = new Visitor();
			sessions.put(newToken, visitor);
			session.setAttribute("visitor", visitor);
			resp.setHeader("Authorization", newToken);
		} else {
			session.setAttribute("visitor", sessions.get(token));
			resp.setHeader("Authorization", token);
		}

		chain.doFilter(req, resp);
	}

}

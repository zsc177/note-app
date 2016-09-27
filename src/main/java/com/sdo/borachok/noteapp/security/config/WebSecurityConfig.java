package com.sdo.borachok.noteapp.security.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.sdo.borachok.noteapp.security.JwtAuthenticationEntryPoint;
import com.sdo.borachok.noteapp.security.JwtAuthenticationProvider;
import com.sdo.borachok.noteapp.security.JwtAuthenticationSuccessHandler;
import com.sdo.borachok.noteapp.security.JwtAuthenticationTokenFilter;

@Configuration
@EnableWebSecurity
@EnableAutoConfiguration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private JwtAuthenticationEntryPoint entryPoint;

	@Autowired
	private JwtAuthenticationProvider provider;

	@Autowired
	private JwtAuthenticationSuccessHandler successHandler;

	@Bean
	@Override
	public AuthenticationManager authenticationManager() throws Exception {
		return new ProviderManager(Arrays.asList(provider));
	}

	public JwtAuthenticationTokenFilter authenticationTokenFilter() throws Exception {

		JwtAuthenticationTokenFilter authenticationTokenFilter = new JwtAuthenticationTokenFilter();
		authenticationTokenFilter.setAuthenticationManager(authenticationManager());
		authenticationTokenFilter.setAuthenticationSuccessHandler(successHandler);

		return authenticationTokenFilter;
	}

	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		httpSecurity //
				.csrf().disable() //
				.authorizeRequests().anyRequest().authenticated().and() //
				.exceptionHandling().authenticationEntryPoint(entryPoint).and() //
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

		httpSecurity.addFilterBefore(authenticationTokenFilter(), UsernamePasswordAuthenticationFilter.class);

		httpSecurity.headers().cacheControl();
	}

	@Override
	public void configure(WebSecurity webSecurity) throws Exception {
		webSecurity.ignoring().antMatchers("/login").antMatchers(HttpMethod.POST, "/api/v1/persons");
	}
}

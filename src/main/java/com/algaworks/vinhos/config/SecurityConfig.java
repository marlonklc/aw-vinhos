package com.algaworks.vinhos.config;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 * Created by Marlon on 22/06/2017.
 */
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
			.withUser("joao").password("joao").roles("PESQUISAR_VINHO").and()
			.withUser("maria").password("maria").roles("CADASTRAR_VINHO", "PESQUISAR_VINHO");
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring()
			.antMatchers("/layout/**");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		configRoles(http);
		configLogin(http);
		configLogout(http);
	}

	private void configRoles(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.antMatchers("/vinhos").hasRole("PESQUISAR_VINHO")
			.antMatchers("/vinhos/**").hasRole("CADASTRAR_VINHO")
			.anyRequest().authenticated();
	}

	private void configLogin(HttpSecurity http) throws Exception {
		http.formLogin().loginPage("/login").permitAll();
	}

	private void configLogout(HttpSecurity http) throws Exception {
		http.logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"));
	}
}

package ch.hearc.stockarc.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import ch.hearc.stockarc.service.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	DataSource dataSource;

	@Qualifier("userDetailsServiceImpl")
	@Autowired
	private UserDetailsServiceImpl userDetailsService;

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/", "/home").authenticated()
				.antMatchers("/static/**", "/registration", "/forgotPassword", "/users/resetPassword",
						"/users/changePassword*", "/users/completeAccount*")
				.permitAll().antMatchers("/users/updatePassword*", "/users/savePassword*", "/updatePassword*")
				.hasAuthority("CHANGE_PASSWORD_PRIVILEGE")
				.antMatchers("/users/updateAccount*", "/users/saveAccount*", "/updateAccount*")
				.hasAuthority("CHANGE_ACCOUNT_PRIVILEGE").antMatchers("/admin").hasRole("ADMIN").anyRequest()
				.authenticated().and().formLogin().loginPage("/login").usernameParameter("email").permitAll().and()
				.logout().permitAll();

		http.exceptionHandling().accessDeniedPage("/403");
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/resources/**");
	}

	@Bean
	public AuthenticationManager customAuthenticationManager() throws Exception {
		return authenticationManager();
	}
}
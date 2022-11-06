package br.com.alura.mvc.mudi;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired //Ele serve para você pedir para o Spring – “eu quero uma instância de ‘DataSource’!” 
	private DataSource dataSource;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http

		.authorizeRequests()
			.anyRequest().authenticated()
		.and()
		.formLogin((form) -> form   
				.loginPage("/login") //dizendo para redirecionar para a página /login para fazer o login
				.defaultSuccessUrl("/usuario/pedido", true) //dizendo que toda vez que o usario fizer o login corretamente vá para página home
				.permitAll()) // dando permição para todos os usuarios 
		.logout(logout -> logout.logoutUrl("/logout")) //deslogando o usuario 
		.csrf().disable();								 
		
	}
	

	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
	
		
		auth.jdbcAuthentication()
			.dataSource(dataSource)
			.passwordEncoder(encoder);
	}

}

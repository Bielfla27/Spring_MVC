package br.com.alura.mvc.mudi.controller;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.alura.mvc.mudi.dto.RequisicaoNovoUsuario;
import br.com.alura.mvc.mudi.model.User;
import br.com.alura.mvc.mudi.repository.UserRepository;



@Controller
@RequestMapping("/cadastrar")
public class CadastrarController {
	
	@Autowired //Ele serve para você pedir para o Spring – “eu quero uma instância de ‘DataSource’!” 
	private DataSource dataSource;
	
	@Autowired
	private BCryptPasswordEncoder criptografia;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserDetailsManager autenticarUsuario;
	
	@GetMapping
	public String Cadastro(Model model) {
		model.addAttribute("user", new User());
		return "/cadastrar";
	}
	
	 @PostMapping
	 public String result(@ModelAttribute User user){
		 	String senhaCriptografada = criptografia.encode(user.getPassword());
		 	RequisicaoNovoUsuario usuarioRequisicao = new RequisicaoNovoUsuario(user.getUsername(), senhaCriptografada, true);
		 	User usuarioNovo = usuarioRequisicao.toUser();
		 	autenticaUsuario(user);
		 	userRepository.save(usuarioNovo);
	        return "/login";
	   }
	 
	 public UserDetailsManager autenticaUsuario(User userCadastrado) {
		 	UserDetails usuarioAutenticado = org.springframework.security.core.userdetails.User.builder()
					.username(userCadastrado.getUsername())
					.password(userCadastrado.getPassword())
					.roles("USER")
					.build();
				JdbcUserDetailsManager users = new JdbcUserDetailsManager(dataSource);
				users.createUser(usuarioAutenticado);
				return users;
	 }
}

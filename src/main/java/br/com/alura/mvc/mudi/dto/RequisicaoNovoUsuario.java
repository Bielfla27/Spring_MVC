package br.com.alura.mvc.mudi.dto;

import javax.persistence.Id;

import br.com.alura.mvc.mudi.model.User;

public class RequisicaoNovoUsuario {

	@Id
	private String username;
	private String password;
	private Boolean enabled;
	
	
		
	public RequisicaoNovoUsuario() {
		
	}
	
	public RequisicaoNovoUsuario(String username, String password, Boolean enabled) {
		this.username = username;
		this.password = password;
		this.enabled = enabled;
	}

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Boolean getEnabled() {
		return enabled;
	}
	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}
	
	public User toUser() {
		User usuario = new User();
		usuario.setUsername(username);
		usuario.setPassword(password);
		usuario.setEnabled(enabled);
		return usuario;
	}
	
}

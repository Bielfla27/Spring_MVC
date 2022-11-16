package br.com.alura.mvc.mudi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CadastrarController {
	

	@GetMapping
	@RequestMapping("/cadastrar")
	public String Cadastro() {
		return "/cadastrar";
	}

}

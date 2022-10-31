package br.com.alura.mvc.mudi.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller //integrando a classe com o controller
public class HelloController {
		
	@GetMapping("/hello") //dizendo para o spring que quando chegar uma requisição com o método /hello  a Action @GetMapping chamar esse método que vai levar para view hello.html
	public String hello(Model model) {
		model.addAttribute("nome", "Fofão");
		
		return "hello";
	}
}

package br.com.alura.mvc.mudi.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.alura.mvc.mudi.model.Oferta;
import br.com.alura.mvc.mudi.repository.OfertaRepository;

@Controller
@RequestMapping("Minhasoferta")
public class MinhasOfertasController {
	
	@Autowired //Ela serve para você pedir para o Spring – “eu quero uma instância de ‘PedidoRepository’!” 
	private OfertaRepository ofertaRepository;

	
	@GetMapping
	public String getBuscarMinhasOfertas(Model model,Principal principal) {
//		List<Oferta> ofertas =  ofertaRepository.findAllByUsuario(principal.getName());
//		model.addAttribute("oferta", ofertas);
		return "MinhasOfertas/MinhasOfertasHome";
	}

}

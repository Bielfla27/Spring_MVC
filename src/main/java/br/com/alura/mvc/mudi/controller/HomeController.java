package br.com.alura.mvc.mudi.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import br.com.alura.mvc.mudi.model.Pedido;

@Controller
public class HomeController {

	@GetMapping("/home")
	public String home(Model model){
		Pedido pedido = new Pedido();
		pedido.setNomeProduto("Xbox");
		pedido.setUrlImagem("https://http2.mlstatic.com/D_NQ_NP_2X_627914-MLA40655732617_022020-V.webp");
		pedido.setUrlProduto("https://www.mercadolivre.com.br/microsoft-xbox-one-s-1tb-standard-cor-branco/p/MLB14114827");
		pedido.setDescricao("Xbox o melhor sem duvida");
		
		List<Pedido> pedidos = Arrays.asList(pedido);
		model.addAttribute("pedidos", pedidos);
		
		return "home";
	}
}

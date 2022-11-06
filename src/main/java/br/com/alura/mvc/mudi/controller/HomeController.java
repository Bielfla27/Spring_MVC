package br.com.alura.mvc.mudi.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.alura.mvc.mudi.model.Pedido;
import br.com.alura.mvc.mudi.model.StatusPedido;
import br.com.alura.mvc.mudi.repository.PedidosRepository;
import ch.qos.logback.core.status.Status;

@Controller
@RequestMapping("/home")
public class HomeController {
	
	@Autowired //Ela serve para você pedir para o Spring – “eu quero uma instância de ‘PedidoRepository’!” 
	private PedidosRepository pedidoRepository;

	@GetMapping
	public String home(Model model,Principal principal){
	
		List<Pedido> pedidos = pedidoRepository.findAllByStatus(StatusPedido.ENTREGUE);
		model.addAttribute("pedidos", pedidos);
		
		//model.getAttribute(null); posso confirir e pegar os parêmetros pelo id assim 
		return "home";
	}
	
	
}


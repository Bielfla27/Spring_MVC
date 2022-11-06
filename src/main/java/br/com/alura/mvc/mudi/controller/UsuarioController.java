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

@Controller
@RequestMapping("usuario")
public class UsuarioController {
	
	@Autowired //Ela serve para você pedir para o Spring – “eu quero uma instância de ‘PedidoRepository’!” 
	private PedidosRepository pedidoRepository;

	@GetMapping("pedido")
	public String home(Model model,Principal principal){
	
		List<Pedido> pedidos = pedidoRepository.findAllByUsuario(principal.getName());
		model.addAttribute("pedidos", pedidos);
		
		//model.getAttribute(null); posso confirir e pegar os parêmetros pelo id assim 
		return "usuario/home";
	}
	
	@GetMapping("pedido/{status}") 
	public String porStatus(@PathVariable("status") String status, Model model ,Principal principal){
	
		List<Pedido> pedidos = pedidoRepository.findByStatusIUsuario(StatusPedido.valueOf(status.toUpperCase()), principal.getName());
		model.addAttribute("pedidos", pedidos);
		model.addAttribute("status", status);
		return "usuario/home";
	}
	
	@ExceptionHandler(IllegalArgumentException.class)
	public String onError() {
		return "redirect:/usuario/home";
	}
}

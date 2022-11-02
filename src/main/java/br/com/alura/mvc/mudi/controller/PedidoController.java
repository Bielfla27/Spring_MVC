package br.com.alura.mvc.mudi.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.alura.mvc.mudi.dto.RequisicaoNovoPedido;
import br.com.alura.mvc.mudi.model.Pedido;
import br.com.alura.mvc.mudi.repository.PedidosRepository;

@Controller
@RequestMapping("pedido")
public class PedidoController {
	
	@Autowired
	private PedidosRepository pedidosRepository;

	@GetMapping("formulario")
	public String formulario(RequisicaoNovoPedido novoPedido) {
	return "pedido/formulario";
	}
	
	@PostMapping("novo") //quando for uma requisição post usamos o postMapping
	public String novo(@Valid RequisicaoNovoPedido novoPedido, BindingResult result) {
		if(result.hasErrors()) {
			return "pedido/formulario";
		}
		Pedido pedido = novoPedido.toPedido();
		pedidosRepository.save(pedido);
		return "redirect:/home";
	}
}


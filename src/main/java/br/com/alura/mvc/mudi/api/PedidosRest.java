package br.com.alura.mvc.mudi.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.alura.mvc.mudi.model.Pedido;
import br.com.alura.mvc.mudi.model.StatusPedido;
import br.com.alura.mvc.mudi.repository.PedidosRepository;

@RestController
@RequestMapping("/api/pedidos")
public class PedidosRest {
	
	@Autowired
	private PedidosRepository pedidosRepository;
	
	@GetMapping("aguardando")
	public List<Pedido> getPedidoAguardandoOfertas(){
		
		Sort sort = Sort.by("id").descending(); // para fazer a consulta pela data e por ordem decrescente
		PageRequest paginação = PageRequest.of(0, 1, sort); // colocando paginação no site e dizendo quantos itens mostrar por cada página e ordenando pelo sort 
	
		return pedidosRepository.findByStatus(StatusPedido.AGUARDANDO, paginação);
	}

}

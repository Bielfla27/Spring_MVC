package br.com.alura.mvc.mudi.api;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.alura.mvc.mudi.dto.RequisicaoNovaOferta;
import br.com.alura.mvc.mudi.model.Oferta;
import br.com.alura.mvc.mudi.model.Pedido;
import br.com.alura.mvc.mudi.model.StatusPedido;
import br.com.alura.mvc.mudi.model.User;
import br.com.alura.mvc.mudi.repository.OfertaRepository;
import br.com.alura.mvc.mudi.repository.PedidosRepository;
import br.com.alura.mvc.mudi.repository.UserRepository;

@RestController
@RequestMapping("/api/ofertas")
public class OfertasRest {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PedidosRepository pedidosRepository;

	@Autowired //Ela serve para você pedir para o Spring – “eu quero uma instância de ‘PedidoRepository’!” 
	private OfertaRepository ofertaRepository;
	
	@GetMapping("minhasOfertas")
	public List<Oferta> getPedidoAguardandoOfertas(){
		
		String username = SecurityContextHolder.getContext().getAuthentication().getName(); //pegando o usuario logado
		User user =userRepository.findByUsername(username); // associando usuario logado com o do banco
		
		Sort sort = Sort.by("id").descending(); // para fazer a consulta pela data e por ordem decrescente
		PageRequest paginação = PageRequest.of(0, 10, sort); // colocando paginação no site e dizendo quantos itens mostrar por cada página e ordenando pelo sort 
	
		return ofertaRepository.findByUser(user, paginação);
	}
	
	@PostMapping
	public Oferta criaOferta(@Valid @RequestBody RequisicaoNovaOferta requisicao ) { //@RequestBody falo pro spring preencher esse objeto com oq veio na requisição
		Optional<Pedido> pedidoBuscado = pedidosRepository.findById(requisicao.getPedidoId());
		if(!pedidoBuscado.isPresent()) {
			return null; 
		}
		String username = SecurityContextHolder.getContext().getAuthentication().getName(); //pegando o usuario logado
		User user =userRepository.findByUsername(username); // associando usuario logado com o do banco
		
		Pedido pedido = pedidoBuscado.get();
		Oferta nova = requisicao.toOferta();
		nova.setPedido(pedido);
		nova.setUser(user);
		nova.setUrlImagem(pedido.getUrlImagem());
		nova.setUrlProduto(pedido.getUrlProduto());
		nova.setNomeProduto(pedido.getNomeProduto());
		pedido.getOfertas().add(nova);
		pedidosRepository.save(pedido);
		return nova;
	}

}

package br.com.alura.mvc.mudi.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import br.com.alura.mvc.mudi.model.Pedido;

@Repository //Estou dizendo para o Spring – “essa classe aqui é um repositório e eu quero que você gerencie essa classe e crie instâncias toda vez que alguém pedir” 
public class PedidosRepository {

	@PersistenceContext
	private EntityManager entityManager;
	
	public List<Pedido> retornaTodosOsPedidos(){
		Query query = entityManager.createQuery("select p from Pedido p" , Pedido.class);
		return query.getResultList();
	}
}

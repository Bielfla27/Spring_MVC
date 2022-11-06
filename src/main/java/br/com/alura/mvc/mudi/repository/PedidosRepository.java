package br.com.alura.mvc.mudi.repository;

import java.util.List;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.alura.mvc.mudi.model.Pedido;
import br.com.alura.mvc.mudi.model.StatusPedido;

@Repository //Estou dizendo para o Spring – “essa classe aqui é um repositório e eu quero que você gerencie essa classe e crie instâncias toda vez que alguém pedir” 
public interface PedidosRepository extends JpaRepository<Pedido, Long> {

	List<Pedido> findByStatus(StatusPedido status);

	@Query("Select p from Pedido p join p.user u where u.username = :username")
	List<Pedido> findAllByUsuario(@Param("username") String name);
	
	@Query("Select p from Pedido p join p.user u where u.username = :username and p.status = :status")
	List<Pedido> findByStatusIUsuario(@Param("status") StatusPedido status ,@Param("username") String name);
	
	

	
}

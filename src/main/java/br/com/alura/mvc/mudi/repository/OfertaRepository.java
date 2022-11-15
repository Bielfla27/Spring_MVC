package br.com.alura.mvc.mudi.repository;

import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.alura.mvc.mudi.model.Oferta;
import br.com.alura.mvc.mudi.model.User;

@Repository
public  interface OfertaRepository extends JpaRepository<Oferta, Long> {
	
	@Cacheable("oferta") //guardando o cache da aplicação para não ficar fazendo pesquisas repetidas ao banco de dados *ESSE EXEMPLO É BEM BÁSICO DE CACHE* 
	List<Oferta> findByUser(User user , Pageable sort);
}

package br.com.alura.mvc.mudi.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import br.com.alura.mvc.mudi.model.Oferta;

public class RequisicaoNovaOferta {
	
	private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	
	
	private UUID pedidoId;
	@NotNull
	@Pattern(regexp = "^\\d+(\\.\\d+{2})?$") //validação de valor
 	private String valor;
	@NotNull
	@Pattern(regexp = "^\\d{2}/\\d{2}/\\d{4}$") //validação de data
	@NotBlank
	private String dataDaEntrega;
	private String comentario;
	
	
	public UUID getPedidoId() {
		return pedidoId;
	}
	public void setPedidoId(UUID pedidoId) {
		this.pedidoId = pedidoId;
	}
	public String getValor() {
		return valor;
	}
	public void setValor(String valor) {
		this.valor = valor;
	}
	public String getDataDaEntrega() {
		return dataDaEntrega;
	}
	public void setDataDaEntrega(String dataDaEntrega) {
		this.dataDaEntrega = dataDaEntrega;
	}
	public String getComentario() {
		return comentario;
	}
	public void setComentario(String comentario) {
		this.comentario = comentario;
	}
	public Oferta toOferta() {
		Oferta oferta = new Oferta();
		oferta.setComentario(this.comentario);
		oferta.setDataDaEntrega(LocalDate.parse(this.dataDaEntrega, formatter));
		oferta.setValor(new BigDecimal(this.valor));
		
		return oferta;
	}
	
	

}

package br.com.projeto.ifood.model.DTO;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class AdicionarRestauranteDTO {

	
	@NotNull
	private String proprietario;
	
	private String cnpj;
	
	@Size(min = 3, max = 30)
	private String nomeFantasia;
	
	private LocalizacaoDTO localizacao;
	
	private Long numeroResdencia;
	
	private Long numeroCelular;
	
	private Long numeroComercial;
	
	public AdicionarRestauranteDTO() {
		
	}

	public String getProprietario() {
		return proprietario;
	}

	public void setProprietario(String proprietario) {
		this.proprietario = proprietario;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getNomeFantasia() {
		return nomeFantasia;
	}

	public void setNomeFantasia(String nomeFantasia) {
		this.nomeFantasia = nomeFantasia;
	}

	public LocalizacaoDTO getLocalizacao() {
		return localizacao;
	}

	public void setLocalizacao(LocalizacaoDTO localizacao) {
		this.localizacao = localizacao;
	}

	public Long getNumeroResdencia() {
		return numeroResdencia;
	}

	public void setNumeroResdencia(Long numeroResdencia) {
		this.numeroResdencia = numeroResdencia;
	}

	public Long getNumeroCelular() {
		return numeroCelular;
	}

	public void setNumeroCelular(Long numeroCelular) {
		this.numeroCelular = numeroCelular;
	}

	public Long getNumeroComercial() {
		return numeroComercial;
	}

	public void setNumeroComercial(Long numeroComercial) {
		this.numeroComercial = numeroComercial;
	}

	
	
}

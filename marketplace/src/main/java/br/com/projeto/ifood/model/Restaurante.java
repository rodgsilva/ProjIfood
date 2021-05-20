package br.com.projeto.ifood.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import io.vertx.mutiny.pgclient.PgPool;
import io.vertx.mutiny.sqlclient.Tuple;

public class Restaurante {
	
	
	private Long id;

	private String nome;
	
	private Localizacao localizacao;
	
	private String proprietario;
	
	private String cnpj;	
	
	private Date dataCriacao;
	
	private Date dataAtualizacao;
	
	private List<Telefone> telefone =new ArrayList<>(); 
	
    public Restaurante() {
		super();
	}



	public Restaurante(Long id, String nome, Localizacao localizacao) {
		super();
		this.id = id;
		this.nome = nome;
		this.localizacao = localizacao;
	}

	public void persist(PgPool pgPool) {	   
	    Long teste=localizacao.persist(pgPool);   
   try {
	   
	   pgPool.preparedQuery("insert into restaurante (id,nome, localizacao_id) values ($1, $2,$3)")
        	 .execute(Tuple.of(id,nome,localizacao.getId())).await().indefinitely();    
	
        	
   }catch (Exception e) {
	   System.out.println(teste);
	   System.out.println(e);
}   

    }
        
    public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	

	public List<Telefone> getTelefone() {
		return telefone;
	}



	public void setTelefone(List<Telefone> telefone) {
		this.telefone = telefone;
	}



	public Localizacao getLocalizacao() {
		return localizacao;
	}



	public void setLocalizacao(Localizacao localizacao) {
		this.localizacao = localizacao;
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



	public Date getDataCriacao() {
		return dataCriacao;
	}



	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}



	public Date getDataAtualizacao() {
		return dataAtualizacao;
	}



	public void setDataAtualizacao(Date dataAtualizacao) {
		this.dataAtualizacao = dataAtualizacao;
	}



	@Override
    public String toString() {
        return "Restaurante [id=" + id + ", nome=" + nome + ", localizacao=" + localizacao + "]";
    }


}

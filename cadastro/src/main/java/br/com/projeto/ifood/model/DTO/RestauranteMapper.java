package br.com.projeto.ifood.model.DTO;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import br.com.projeto.ifood.model.Restaurante;

@Mapper(componentModel = "cdi")
public interface RestauranteMapper {
	
	@Mapping(target = "nome", source = "nomeFantasia")
	@Mapping(target = "telefone", source = "numeroResdencia")
	public Restaurante toRestaurante(AdicionarRestauranteDTO dto);

	
	@Mapping(target = "nomeFantasia", source ="nome")
	@Mapping(target = "dataCriacao",dateFormat = "dd/MM/yyyy HH:mm:ss")
	public RestauranteDTO toRestauranteDTO(Restaurante r);
	
	@Mapping(target = "nome", source = "nomeFantasia")	
	public void toRestaurante(AtualizaRestauranteDTO dto, @MappingTarget Restaurante restaurante);

}

package br.com.projeto.ifood.model.DTO;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import br.com.projeto.ifood.model.Prato;

@Mapper(componentModel = "cdi")
public interface PratoMapper {
	
	 PratoDTO toDTO(Prato p);
	
	 Prato toPrato(AdicionarPratoDTO dto);
	 
	 void toPrato(AtualizaPratoDTO dto, @MappingTarget Prato prato);

}

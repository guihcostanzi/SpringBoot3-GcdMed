package gcd.med.api.controller;

import gcd.med.api.endereco.DadosEndereco;
import jakarta.validation.constraints.NotNull;

public record DadosEdicaoPaciente(
		
		@NotNull
		Long id,
		
		String nome,
		String telefone,
		
		DadosEndereco endereco
		
		) {

}
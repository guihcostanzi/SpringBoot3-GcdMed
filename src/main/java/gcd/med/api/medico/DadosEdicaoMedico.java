package gcd.med.api.medico;

import gcd.med.api.endereco.DadosEndereco;
import jakarta.validation.constraints.NotNull;

public record DadosEdicaoMedico( //Obs : parametros que podem ser alterados, na regra de negocio
		
		@NotNull
		Long id, // O id não será alterado, mas é necessário para identificar o objeto no banco.
		
		String nome,
		
		String telefone,
		
		DadosEndereco endereco) {

}

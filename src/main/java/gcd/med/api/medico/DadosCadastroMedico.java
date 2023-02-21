package gcd.med.api.medico;

import gcd.med.api.endereco.DadosEndereco;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosCadastroMedico(
		
		@NotBlank
		String nome,
		
		@NotBlank
		String crm,
		
		@NotBlank
		String telefone,
		
		@NotBlank
		@Email
		String email,
		
		@NotNull // NotBlank é apenas para String.
		Especialidade especialidade,
		
		@NotNull
		@Valid
		DadosEndereco endereco
		
		) {

}

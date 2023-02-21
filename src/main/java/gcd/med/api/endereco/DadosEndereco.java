package gcd.med.api.endereco;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record DadosEndereco(
		
		@NotBlank
		String logradouro, 
		
		@NotBlank
		String bairro,
		
		@NotBlank
		@Pattern(regexp = "\\d{8}")
		String cep, 
		
		@NotBlank
		String cidade,
		
		@NotBlank
		String uf,
		
		//Opcionais :  (Não colocar @NotBlank) 
		
		String complemento, 
		
		@Pattern(regexp = "\\d{0,8}")
		String numero) {

	
}

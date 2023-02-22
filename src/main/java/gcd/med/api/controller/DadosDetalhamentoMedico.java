package gcd.med.api.controller;

import gcd.med.api.endereco.Endereco;
import gcd.med.api.medico.Especialidade;
import gcd.med.api.medico.Medico;

public record DadosDetalhamentoMedico(
		
		Long id,
		String nome,
		String email,
		String crm,
		String telefone,
		Especialidade especialidade,
		Endereco endereco
		
		) {

	public DadosDetalhamentoMedico(Medico medico) {
		this(medico.getId(), medico.getNome(), medico.getEmail(), medico.getCrm(),
				medico.getTelefone(), medico.getEspecialidade(), medico.getEndereco());
	}

}

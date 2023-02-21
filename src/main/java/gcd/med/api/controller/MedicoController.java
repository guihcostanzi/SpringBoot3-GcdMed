package gcd.med.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import gcd.med.api.medico.Medico;
import gcd.med.api.medico.MedicoRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import gcd.med.api.medico.DadosCadastroMedico;

@RestController
@RequestMapping("medicos")
public class MedicoController {
	
	@Autowired
	private MedicoRepository repository;
	
	@Transactional
	@PostMapping
	public void cadastrar ( @RequestBody @Valid DadosCadastroMedico dados) {
		
		repository.save(new Medico(dados));
		
		System.out.println("Tudo certo ! O novo médico foi cadastrado no Banco de Dados.");
	}

}


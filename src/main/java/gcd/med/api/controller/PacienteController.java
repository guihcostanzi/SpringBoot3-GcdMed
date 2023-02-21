package gcd.med.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import gcd.med.api.paciente.DadosCadastroPaciente;
import gcd.med.api.paciente.Paciente;
import gcd.med.api.paciente.PacienteRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RequestMapping("pacientes")
@RestController
public class PacienteController {
	
	@Autowired
	private PacienteRepository repository;
	
	@PostMapping
	@Transactional
	public void cadastrar(@Valid @RequestBody DadosCadastroPaciente dados) {
		
		repository.save(new Paciente(dados));
		
		System.out.println("Cadastrado com sucesso !");
	}

}

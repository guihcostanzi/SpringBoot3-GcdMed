package gcd.med.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import gcd.med.api.paciente.PacienteRepository;

@RequestMapping("pacientes")
@RestController
public class PacienteController {
	
	@Autowired
	private PacienteRepository repository;

}

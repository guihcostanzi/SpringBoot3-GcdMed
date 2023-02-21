package gcd.med.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import gcd.med.api.medico.DadosCadastroMedico;
import gcd.med.api.medico.DadosListagemMedico;
import gcd.med.api.medico.Medico;
import gcd.med.api.medico.MedicoRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

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
	
	@GetMapping
	public Page<DadosListagemMedico> listar(@PageableDefault(size = 10, sort = {"nome"}) org.springframework.data.domain.Pageable paginacao){
	
		return repository.findAll(paginacao).map(DadosListagemMedico::new);
		
		//O método não deve devolver a entidade Medico diretamente, até por motivos de segurança.
		//Por isso, é devolvido um DTO ( Data Transfer Object).
	}

}


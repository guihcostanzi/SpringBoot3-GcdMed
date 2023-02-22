package gcd.med.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import gcd.med.api.medico.DadosCadastroMedico;
import gcd.med.api.medico.DadosEdicaoMedico;
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
	public ResponseEntity<DadosDetalhamentoMedico> cadastrar(@RequestBody @Valid DadosCadastroMedico dados, UriComponentsBuilder uriCompBuilder ) { //HTTP 201
		
		var medico = new Medico(dados);
		
		repository.save(medico); //O banco de dados irá gerar o Id e atribuir ao objeto medico.
		
		var uri = uriCompBuilder.path("/medicos/{id}").buildAndExpand(medico.getId()).toUri();
		
		return ResponseEntity.created(uri).body(new DadosDetalhamentoMedico(medico));
	}

	@GetMapping
	public ResponseEntity<Page<DadosListagemMedico>> listar(
			@PageableDefault(size = 10, sort = { "nome" }) org.springframework.data.domain.Pageable paginacao) { //HTTP 200

			var page = repository.findAll(paginacao).map(DadosListagemMedico::new);
			
			return ResponseEntity.ok(page);

		// O método não deve devolver a entidade Medico diretamente, até por motivos de
		// segurança.
		// Por isso, é devolvido um DTO ( Data Transfer Object).
	}

	@PutMapping
	@Transactional // Como está escrevendo no banco, tem q ser uma transação.
	public ResponseEntity<DadosDetalhamentoMedico> editar(@RequestBody @Valid DadosEdicaoMedico dados) { //HTTP 200

		var medico = repository.getReferenceById(dados.id());

		medico.atualizarInformacoes(dados);
		
		return ResponseEntity.ok(new DadosDetalhamentoMedico (medico)); //Retornar o objeto que foi atualizado.

	}

	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<Void> desativar(@PathVariable Long id) { //HTTP 204
 
		var medico = repository.getReferenceById(id);

		medico.desativar();
		
		return ResponseEntity.noContent().build();
		
		//Esse método recebe o parâmetro 'id' no link da requisição.
		//É um DELETE lógico, não removendo de fato os dados do Banco.

	}

}

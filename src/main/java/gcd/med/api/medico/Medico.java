package gcd.med.api.medico;

import java.util.Calendar;

import org.hibernate.annotations.CreationTimestamp;

import gcd.med.api.endereco.Endereco;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "T_MEDICOS")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Medico {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String nome;
	private String crm;
	private String telefone;
	private String email;

	@Enumerated(EnumType.STRING)
	private Especialidade especialidade;

	// Significa que o atributo multivalorado não será uma tabela, mas será acoplada
	// à tabela do médico.
	@Embedded
	private Endereco endereco;

	@CreationTimestamp // Automaticamente define a data de cadastro no banco de dados.
	@Temporal(TemporalType.DATE) // O TemporalType regula a precisão (Ex : Data, Data e Hora...)
	@Column(name = "data_cadastro")
	private Calendar dataCadastro;

	private Boolean ativo;

	public Medico(@Valid DadosCadastroMedico dados) {

		// Definido automaticamento durante o cadastro.
		this.ativo = true;

		this.crm = dados.crm();
		this.nome = dados.nome();
		this.email = dados.email();
		this.especialidade = dados.especialidade();
		this.telefone = dados.telefone();

		this.endereco = new Endereco(dados.endereco());

	}

	public void atualizarInformacoes(DadosEdicaoMedico dados) {

		if (dados.nome() != null) {
			this.nome = dados.nome();
		}

		if (dados.telefone() != null) {
			this.telefone = dados.nome();
		}

		if (dados.endereco() != null) {
			this.endereco.atualizarEndereco(dados.endereco());
		}

	}

	public void desativar() {
		this.ativo = false;
		
	}
}

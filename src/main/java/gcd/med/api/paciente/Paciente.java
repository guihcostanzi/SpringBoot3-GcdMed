package gcd.med.api.paciente;

import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;

import gcd.med.api.controller.DadosEdicaoPaciente;
import gcd.med.api.endereco.Endereco;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "T_PACIENTES")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Paciente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String nome;
	private String email;
	private String telefone;
	private String cpf;

	@Embedded
	private Endereco endereco;

	@CreationTimestamp
	@Temporal(TemporalType.DATE)
	@Column(name = "data_cadastro")
	private Date dataCadastro;

	private Boolean ativo;

	public Paciente(DadosCadastroPaciente dados) {

		this.ativo = true;

		this.nome = dados.nome();
		this.email = dados.email();
		this.cpf = dados.cpf();
		this.endereco = new Endereco(dados.endereco());
		this.telefone = dados.telefone();

	}

	public void atualizarInformacoes(DadosEdicaoPaciente dados) {

		if (dados.nome() != null) {
			this.nome = dados.nome();
		}
		if (dados.telefone() != null) {
			this.telefone = dados.telefone();
		}
		if (dados.endereco() != null) {
			this.endereco.atualizarEndereco(dados.endereco());
		}

	}

	public void desativar() {
		this.ativo = false;
		
	}

}

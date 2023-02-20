package gcd.med.api.medico;

import java.util.Calendar;

import org.hibernate.annotations.CreationTimestamp;

import gcd.med.api.endereco.Endereco;
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
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nome;
	private String crm;
	private String telefone;
	private String email;
	
	@Enumerated(EnumType.STRING)
	private Especialidade especialidade;
	
	//Significa que o atributo multivalorado não será uma tabela, mas será acoplada à tabela do médico.
	@Embedded
	private Endereco endereco;
	
	//Annotation válida para DATE e CALENDAR. O TemporalType regula a precisão (Ex : Data, Data e Hora...)	
	@CreationTimestamp
	@Temporal(TemporalType.DATE)
	private Calendar dataCadastro;
	
	private Boolean ativo;

}


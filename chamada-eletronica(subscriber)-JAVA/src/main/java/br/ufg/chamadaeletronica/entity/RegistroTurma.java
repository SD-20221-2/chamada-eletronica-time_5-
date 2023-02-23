package br.ufg.chamadaeletronica.entity;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(schema = "PUBLIC", name = "REGISTROS_TURMA")
@Getter @Setter
public class RegistroTurma implements Serializable{

	private static final long serialVersionUID = -5346222426951302987L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_REGISTRO")
	private Long id;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DATA_REGISTRO")
	private Date dataRegistro;
	
	@ManyToOne
	@JoinColumn(name = "ID_DISCENTE")
	private Discente discente;
	
	@ManyToOne
	@JoinColumn(name = "ID_TURMA")
	private Turma turma;

	
}

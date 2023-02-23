package br.ufg.chamadaeletronica.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(schema = "PUBLIC", name = "TURMA")
@Getter @Setter
public class Turma implements Serializable{

	private static final long serialVersionUID = 2403483839586191702L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_TURMA")
	private Long id;
	
	@Column(name = "NOME")
	private String nome;
	
	@Column(name = "CODIGO")
	private Long codigo;

	
}

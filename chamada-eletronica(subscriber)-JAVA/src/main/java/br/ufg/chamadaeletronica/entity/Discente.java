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
@Table(schema = "PUBLIC", name = "DISCENTE")
@Getter @Setter
public class Discente implements Serializable{

	private static final long serialVersionUID = -995928929484511305L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_DISCENTE")
	private Long id;
	
	@Column(name = "NOME")
	private String nome;
	
	@Column(name = "MATRICULA")
	private Long matricula;
	
	@Column(name = "TAG_ID")
	private String tagId;
	
}

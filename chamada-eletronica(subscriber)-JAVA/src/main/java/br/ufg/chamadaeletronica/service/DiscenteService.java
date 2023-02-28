package br.ufg.chamadaeletronica.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufg.chamadaeletronica.entity.Discente;
import br.ufg.chamadaeletronica.repository.DiscenteRepository;
import br.ufg.chamadaeletronica.service.DiscenteService;

@Service
public class DiscenteService {
	
	@Autowired
	DiscenteRepository repo;

	public List<Discente> findAll() {
		return repo.findAll();
	}
	
	
	public Discente findByTagId(String tagId) {
		return repo.findByTagId(tagId);
	}
	

}

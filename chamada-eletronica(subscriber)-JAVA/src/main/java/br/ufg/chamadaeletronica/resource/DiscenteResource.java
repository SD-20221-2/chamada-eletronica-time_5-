package br.ufg.chamadaeletronica.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.ufg.chamadaeletronica.entity.Discente;
import br.ufg.chamadaeletronica.service.DiscenteService;

@RestController
@RequestMapping("/discente")
public class DiscenteResource {
	
	@Autowired
	DiscenteService service;

	@GetMapping
	public ResponseEntity<List<Discente>> findAll() {
		List<Discente> discentes = service.findAll();
		return ResponseEntity.ok().body(discentes);
	}
	
}

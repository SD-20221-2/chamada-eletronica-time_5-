package br.ufg.chamadaeletronica.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.ufg.chamadaeletronica.entity.RegistroTurma;
import br.ufg.chamadaeletronica.service.RegistroTurmaService;

@CrossOrigin("*") 
@RestController
@RequestMapping("/registro")
public class RegistroTurmaResource {
	
	@Autowired
	RegistroTurmaService service;

	@GetMapping
	public ResponseEntity<List<RegistroTurma>> findAll() {
		List<RegistroTurma> registros = service.findAll();
		return ResponseEntity.ok().body(registros);
	}
	
}

package br.ufg.chamadaeletronica.service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufg.chamadaeletronica.entity.Discente;
import br.ufg.chamadaeletronica.entity.RegistroTurma;
import br.ufg.chamadaeletronica.entity.Turma;
import br.ufg.chamadaeletronica.repository.RegistroTurmaRepository;
import br.ufg.chamadaeletronica.service.RegistroTurmaService;

@Service
public class RegistroTurmaService {

	@Autowired
	RegistroTurmaRepository repo;
	

	public List<RegistroTurma> findAll() {
		return repo.findAll();
	}

	public void registrarPresenca(Discente discente) {
		RegistroTurma registro = new RegistroTurma();
		Turma turma = new Turma();
		turma.setId(1L);
		registro.setDataRegistro(new Date());
		registro.setDiscente(discente);
		registro.setTurma(turma);
		this.repo.save(registro);
	}

	public boolean existsPresencaDiaAtual(Long id) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
	    calendar.set(Calendar.MILLISECOND, 0);
		RegistroTurma registro = this.repo.findByDiscenteIdDate(id, calendar.getTime());
		if (registro == null) {
			return false;
		} else
			return true;
	}

}

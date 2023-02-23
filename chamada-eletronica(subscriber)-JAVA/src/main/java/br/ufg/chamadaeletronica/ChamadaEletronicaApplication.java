package br.ufg.chamadaeletronica;

import java.nio.charset.Charset;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.zeromq.*;
import org.zeromq.ZMQ.Context;
import org.zeromq.ZMQ.Socket;

import br.ufg.chamadaeletronica.entity.Discente;
import br.ufg.chamadaeletronica.service.DiscenteService;
import br.ufg.chamadaeletronica.service.RegistroTurmaService;


@SpringBootApplication
public class ChamadaEletronicaApplication {
	
	public static void main(String[] args) throws InterruptedException {
		
		//SpringApplication.run(ChamadaEletronicaApplication.class, args);
		ConfigurableApplicationContext contextSpring = SpringApplication.run(ChamadaEletronicaApplication.class, args);
		DiscenteService discenteService = contextSpring.getBean(DiscenteService.class);
		RegistroTurmaService registroService = contextSpring.getBean(RegistroTurmaService.class);
		
		
		
		final Context context = ZMQ.context(1);
		final Socket subscriber = context.socket(SocketType.SUB);
		subscriber.connect("tcp://localhost:5555");

		String filter = "TAG";
		subscriber.subscribe(filter.getBytes(Charset.forName("UTF-8")));
		while (true) {
			String msg = subscriber.recvStr();
			String tag = msg.substring(4);
			Discente discente = discenteService.findByTagId(tag);
			if(discente != null) {
				if(!registroService.existsPresencaDiaAtual(discente.getId()))
					registroService.registrarPresenca(discente);
			}
		}
	}
	

}

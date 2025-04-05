package org.desafiocresol.schedule;

import io.quarkus.scheduler.Scheduled;
import jakarta.inject.Inject;
import org.desafiocresol.service.EventoService;
import org.jboss.logging.Logger;

public class EncerraEventosSchedule {
    @Inject
    EventoService eventoService;

    @Inject
    Logger logger;

    @Scheduled(every = "50s")
    public void encerraEventos() {
       logger.info("Iniciada rotina de encerramento ou ativamento de eventos");
        int eventosFinalizados = eventoService.encerraEventos();

        if (eventosFinalizados > 0) {
            logger.info("Eventos finalizados:" + eventosFinalizados);
        }

        logger.info("finalizada rotina de encerramento de eventos");
    }
}

package org.desafiocresol.schedule;

import io.quarkus.scheduler.Scheduled;
import jakarta.inject.Inject;
import org.desafiocresol.service.EventoService;
import org.desafiocresol.websocket.EventoWebSocket;
import org.jboss.logging.Logger;

public class EncerraEventosSchedule {
    @Inject
    EventoService eventoService;

    @Inject
    Logger logger;

    @Inject
    EventoWebSocket eventoWebSocket;

    @Scheduled(every = "20s")
    public void encerraEventos() {
       logger.info("Iniciada rotina de encerramento ou ativamento de eventos");
        int eventosFinalizados = eventoService.encerraEventos();

        if (eventosFinalizados > 0) {
            logger.info("Eventos finalizados:" + eventosFinalizados);
            eventoWebSocket.onMessage(String.format("Encerrados %s eventos", eventosFinalizados));
        }

        logger.info("finalizada rotina de encerramento de eventos");
    }
}

package org.desafiocresol.websocket;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.websocket.OnClose;
import jakarta.websocket.OnMessage;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.ServerEndpoint;
import org.jboss.logging.Logger;
import java.util.ArrayList;
import java.util.List;

@ServerEndpoint("/comunica-eventos")
@ApplicationScoped
public class EventoWebSocket {
    private final List<Session> sessions = new ArrayList<>();

    @Inject
    Logger logger;

    @OnOpen
    public void onOpen(Session session) {
        sessions.add(session);
    }

    @OnClose
    public void onClose(Session session) {
        sessions.remove(session);
    }

    @OnMessage
    public void onMessage(String message) {
        broadcast(message);
    }

    private void broadcast(String message) {
        try {
            final ObjectMapper om = new ObjectMapper();
            final String msg = om.writeValueAsString(message);
            sessions.forEach(s -> {
                s.getAsyncRemote().sendObject(msg, result ->  {
                    if (result.getException() != null) {
                        logger.error("Erro ao enviar mensagem");
                    }
                });
            });
        } catch (Exception ex) {
            logger.error("Erro ao processar mensagem");
        }
    }
}

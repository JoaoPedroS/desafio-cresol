package org.desafiocresol.controller;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.desafiocresol.dto.EventoDTO;
import org.desafiocresol.service.EventoService;

@Path("/eventos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EventoController {

    @Inject
    EventoService service;

    @POST
    @Transactional
    public Response createEvento(EventoDTO dto) {
        return Response.ok(service.create(dto)).build();
    }
}

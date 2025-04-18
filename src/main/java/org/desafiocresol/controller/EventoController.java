package org.desafiocresol.controller;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.desafiocresol.dto.EventoDTO;
import org.desafiocresol.entity.Evento;
import org.desafiocresol.service.EventoService;

@Path("/evento")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EventoController {

    @Inject
    EventoService service;

    @POST
    @Transactional
    public Response createEvento(EventoDTO dto) {
         Evento evento = service.create(dto);
        return Response.ok(EventoDTO.from(evento)).build();
    }

    @GET
    public Response getAll(
            @DefaultValue("0") @QueryParam("page") Integer page,
            @DefaultValue("10") @QueryParam("pageSize") Integer pageSize
    )
    {
        return Response.ok(service.getAll(page, pageSize)).build();
    }

    @GET
    @Path("/{id}")
    public Response getById(@PathParam("id") Integer id) {
        Evento evento = service.findById(id);
        return Response.ok(EventoDTO.from(evento)).build();
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Response update(@PathParam("id") Integer id, EventoDTO dto) {
        Evento evento = service.update(id, dto);
        return Response.ok(EventoDTO.from(evento)).build();
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response delete(@PathParam("id") Integer id) {
        service.delete(id);
        return Response.noContent().build();
    }

}

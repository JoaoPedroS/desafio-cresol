package org.desafiocresol.controller;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.desafiocresol.dto.InstituicaoDTO;
import org.desafiocresol.entity.InstituicaoEntity;
import org.desafiocresol.service.InstituicaoService;

@Path("/instituicao")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class InstituicaoController {

    @Inject
    InstituicaoService service;

    @POST
    @Transactional
    public Response create(final InstituicaoDTO dto)
    {
        return Response.ok(service.create(dto)).build();
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
        return Response.ok(service.findById(id)).build();
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Response update(@PathParam("id") Integer id, InstituicaoDTO dto) {
        InstituicaoEntity instituicao = service.update(id, dto);
        return Response.ok(InstituicaoDTO.from(instituicao)).build();
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response delete(@PathParam("id") Integer id) {
        service.delete(id);
        return Response.noContent().build();
    }
}

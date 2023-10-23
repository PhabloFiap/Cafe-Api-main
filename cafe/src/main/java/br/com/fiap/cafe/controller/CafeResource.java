package br.com.fiap.cafe.controller;

import java.net.URI;
import java.util.List;

import br.com.fiap.cafe.model.Cafe;
import br.com.fiap.cafe.model.repository.CafeRepository;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.ResponseBuilder;
import jakarta.ws.rs.core.UriBuilder;

@Path("/cafe")
public class CafeResource {

	@GET
	@Produces(MediaType.APPLICATION_JSON)

	public Response findAll() {

		List<Cafe> retorno = CafeRepository.findAll();
		ResponseBuilder response = Response.ok();
		response.entity(retorno);
		return response.build();

	}

	@POST
	// @Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response salva(@Valid Cafe cafe) {

		Cafe resp = CafeRepository.salva(cafe);

		final URI cafeUri = UriBuilder.fromResource(CafeResource.class).path("/cafe/{id}").build(resp.getId());

		ResponseBuilder response = Response.created(cafeUri);
		response.entity(resp);
		return response.build();

	}

	@DELETE
	@Path("/{id}")
	public Response delete(@PathParam("id") Long cafeId) {

		if (CafeRepository.delete(cafeId)) {
			ResponseBuilder response = Response.noContent();
			return response.build();

		} else {
			System.out.println("nao foi possivel remover" + cafeId);
			ResponseBuilder response = Response.status(404);
			return response.build();
		}

	}
	
	@GET
	@Path("/{id}")
	public Response findById(@PathParam("id") Long cafeId) {
		Cafe cafe = CafeRepository.findById(cafeId);
			if(cafe != null) {
				ResponseBuilder response = Response.ok();
				response.entity(cafe);
				return response.build();
				
			}
			else {
				ResponseBuilder response = Response.noContent();
				return response.build();
			}
	}
	
	
	

}

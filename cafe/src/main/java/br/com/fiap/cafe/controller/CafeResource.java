package br.com.fiap.cafe.controller;

import java.util.List;

import br.com.fiap.cafe.model.Cafe;
import br.com.fiap.cafe.model.repository.CafeRepository;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.ResponseBuilder;

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

}

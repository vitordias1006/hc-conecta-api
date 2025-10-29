package br.com.fiap.resource;

import br.com.fiap.bo.PacienteBO;
import br.com.fiap.to.PacienteTO;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/pacientes")
public class PacienteResource {

    private PacienteBO pacienteBO = new PacienteBO();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response savePatient(PacienteTO pacienteTO) {
        PacienteTO resultado = pacienteBO.savePacient(pacienteTO);
        Response.ResponseBuilder response = null;

        if (resultado != null) {
            response = Response.created(null);
        } else{
            response = Response.status(400);
        }
        response.entity(resultado);
        return response.build();
    }

    @GET
    @Path("/{cd_paciente}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findById(@PathParam("cd_paciente") Long id){
        PacienteTO resultado = pacienteBO.findById(id);
        Response.ResponseBuilder response = null;
        if (resultado != null) {
            response = Response.ok();
        } else {
            response = Response.status(404);
        }
        response.entity(resultado);
        return response.build();
    }

    @PUT
    @Path("/atualiza-paciente/{cd_paciente}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updatePatient(@Valid PacienteTO pacienteTO, @PathParam("cd_paciente") Long id){
        pacienteTO.setId(id);
        PacienteTO resultado = pacienteBO.updatePacient(pacienteTO);
        Response.ResponseBuilder response = null;
        if (resultado != null) {
            response = Response.created(null);
        } else  {
            response = Response.status(400);
        }
        response.entity(resultado);
        return response.build();
    }

    @DELETE
    @Path("/{cd_paciente}")
    public Response deletePaciente(@PathParam("cd_paciente") Long id){
        Response.ResponseBuilder response = null;

        if (pacienteBO.deletePacient(id)) {
            response = Response.status(204);
        } else  {
            response = Response.status(404);
        }
        return response.build();
    }
}

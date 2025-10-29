package br.com.fiap.resource;

import br.com.fiap.bo.ConsultaBO;
import br.com.fiap.to.ConsultaTO;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.ArrayList;

@Path("/consultas")
public class ConsultaResource {
    private ConsultaBO consultaBO =  new ConsultaBO();

    @GET
    @Path("/consultas-paciente/{cd_paciente}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAllMyApointments(@PathParam("cd_paciente") Long idPaciente){
        ArrayList<ConsultaTO> resultado =  consultaBO.findAllMyApointments(idPaciente);
        Response.ResponseBuilder response = null;
        if (resultado != null) {
            response = Response.ok();
        } else {
            response = Response.status(404);
        }
        response.entity(resultado);
        return response.build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response saveConsulta(@Valid ConsultaTO consultaTO) throws Exception {
        ConsultaTO resultado = consultaBO.saveConsulta(consultaTO);
        Response.ResponseBuilder response = null;
        if (resultado != null) {
            response = Response.created(null);
        } else {
            response = Response.status(400);
        }
        response.entity(resultado);
        return response.build();
    }

    @PUT
    @Path("/{cd_consulta}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateConsulta(@Valid ConsultaTO consultaTO, @PathParam("cd_consulta") Long idConsulta) throws Exception {
        consultaTO.setIdConsulta(idConsulta);
        ConsultaTO resultado = consultaBO.updateAppointment(consultaTO);
        Response.ResponseBuilder response = null;

        if (resultado != null) {
            response = Response.created(null);
        } else {
            response = Response.status(400);
        }
        response.entity(resultado);
        return response.build();
    }

    @PUT
    @Path("/atualiza-consulta/{cd_consulta}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateAppointmentStatus(@Valid ConsultaTO consultaTO, @PathParam("cd_consulta") Long idConsulta){
        consultaTO.setIdConsulta(idConsulta);
        ConsultaTO resultado = consultaBO.updateAppointmentStatus(consultaTO);
        Response.ResponseBuilder response = null;
        if (resultado != null) {
            response = Response.created(null);
        } else {
            response = Response.status(400);
        }
        response.entity(resultado);
        return response.build();
    }

    @GET
    @Path("/{cd_consulta}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAppointmentById(@PathParam("cd_consulta") Long idConsulta){
        ConsultaTO resultado = consultaBO.findAppointmentById(idConsulta);
        Response.ResponseBuilder response = null;
        if (consultaBO != null) {
            response = Response.ok();
        } else  {
            response = Response.status(404);
        }
        response.entity(resultado);
        return response.build();
    }

    @DELETE
    @Path("/{cd_consulta}")
    public Response deleteConsulta(@PathParam("cd_consulta") Long idConsulta){
       Response.ResponseBuilder response = null;

       if (consultaBO.deleteAppointment(idConsulta)){
           response = Response.status(204);
       } else {
           response = Response.status(404);
       }
       return response.build();
    }

}

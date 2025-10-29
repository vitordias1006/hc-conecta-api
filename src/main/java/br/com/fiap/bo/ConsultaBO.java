package br.com.fiap.bo;

import br.com.fiap.dao.ConsultaDAO;
import br.com.fiap.to.ConsultaTO;

import java.time.LocalDate;
import java.util.ArrayList;

public class ConsultaBO {
    private ConsultaDAO consultaDAO;

    public ArrayList<ConsultaTO> findAllMyApointments(Long idPaciente){
        consultaDAO = new ConsultaDAO();
        if (idPaciente == null || idPaciente <= 0) {
            throw new IllegalArgumentException("ID do paciente inválido");
        }
        return consultaDAO.findAllMyAppointments(idPaciente);
    }

    public ConsultaTO saveConsulta(ConsultaTO consulta) throws Exception {
        consultaDAO = new ConsultaDAO();
        if (consulta.getDataConsulta().isBefore(LocalDate.now())){
            throw new Exception("Só é possível marcar consultas para o presente e futuro");
        }

        return consultaDAO.saveConsulta(consulta);
    }

    public ConsultaTO updateAppointment(ConsultaTO consulta) throws Exception {
        consultaDAO = new ConsultaDAO();
        if (consulta.getDataConsulta().isBefore(LocalDate.now())){
            throw new Exception("Só é possível remarcar consultas para o presente e futuro");
        }

        return consultaDAO.updateAppointment(consulta);
    }

    public ConsultaTO updateAppointmentStatus(ConsultaTO consulta){
        consultaDAO = new ConsultaDAO();

        return consultaDAO.updateAppointmentStatus(consulta);
    }

    public ConsultaTO findAppointmentById(Long idConsulta){
        consultaDAO = new ConsultaDAO();

        return consultaDAO.findAppointmentById(idConsulta);
    }

    public boolean deleteAppointment(Long idConsulta){
        consultaDAO = new ConsultaDAO();

        return consultaDAO.deleteAppointment(idConsulta);
    }
}

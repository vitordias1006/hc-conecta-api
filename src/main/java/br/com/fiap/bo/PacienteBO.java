package br.com.fiap.bo;

import br.com.fiap.dao.PacienteDAO;
import br.com.fiap.to.PacienteTO;

public class PacienteBO {

    private PacienteDAO pacienteDAO;

    public PacienteTO savePacient(PacienteTO pacienteTO){
        pacienteDAO = new PacienteDAO();

        return pacienteDAO.savePatient(pacienteTO);
    }

    public PacienteTO findById(Long id){
        pacienteDAO = new PacienteDAO();

        return pacienteDAO.findById(id);
    }

    public PacienteTO updatePacient(PacienteTO pacienteTO){
        pacienteDAO = new PacienteDAO();

        return pacienteDAO.updatePatient(pacienteTO);
    }

    public boolean deletePacient(Long id){
        pacienteDAO = new PacienteDAO();

        return pacienteDAO.deleteById(id);
    }

    public PacienteTO findByCpfAndPassword(String cpf, String password) {
        if(pacienteDAO == null){
            throw new RuntimeException("Paciente não existente");
        }
        return pacienteDAO.findByCpfAndPassword(cpf, password);
    }
}
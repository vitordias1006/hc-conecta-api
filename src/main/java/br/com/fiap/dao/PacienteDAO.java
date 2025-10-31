package br.com.fiap.dao;

import br.com.fiap.to.PacienteTO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PacienteDAO {

    public PacienteTO savePatient(PacienteTO pacienteTO){
        String sql = "Insert into paciente (nome, cpf, idade, senha) values (?, ?, ?, ?)";
        try(PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql, new String[]{"cd_paciente"})){
            ps.setString(1, pacienteTO.getNome());
            ps.setString(2, pacienteTO.getCpf());
            ps.setInt(3, pacienteTO.getIdade());
            ps.setString(4, pacienteTO.getSenha());

            int rows = ps.executeUpdate();
            if (rows == 0) {
                System.out.println("Erro ao inserir consulta!");
                return null;
            }
            long idPaciente;

            try (ResultSet rsKeys = ps.getGeneratedKeys()) {
                if (rsKeys.next()) {
                    idPaciente = rsKeys.getInt(1);
                    pacienteTO.setId(idPaciente);
                } else {
                    System.out.println("Não foi possível recuperar o ID da consulta.");
                    return null;
                }
            }

        } catch (SQLException e){
            System.out.println("Erro ao inserir paciente " + e.getMessage());
        } finally{
            ConnectionFactory.closeConnection();
        }
        return pacienteTO;
    }

    public PacienteTO findById(Long id){
        PacienteTO pacienteTO = new PacienteTO();
        String sql = "select * from paciente where cd_paciente=?";

        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)){
            ps.setLong(1, id);

            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                    pacienteTO.setId(rs.getLong("cd_paciente"));
                    pacienteTO.setNome(rs.getString("nome"));
                    pacienteTO.setCpf(rs.getString("cpf"));
                    pacienteTO.setIdade(rs.getInt("idade"));
                    pacienteTO.setSenha(rs.getString("senha"));
            } else {
                return null;
            }

        } catch (SQLException e){
            System.out.println("Erro ao buscar paciente " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return pacienteTO;
    }

    public PacienteTO updatePatient(PacienteTO pacienteTO){
        String sql = "UPDATE paciente set nome=?, idade=?, senha=? where cd_paciente=?";
        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)){
            ps.setString(1, pacienteTO.getNome());
            ps.setInt(2, pacienteTO.getIdade());
            ps.setString(3, pacienteTO.getSenha());
            ps.setLong(4, pacienteTO.getId());

            if (ps.executeUpdate() > 0){
                return pacienteTO;
            } else {
                return pacienteTO;
            }
        } catch (SQLException e){
            System.out.println("Erro ao atualizar paciente " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return null;
    }

    public boolean deleteById(Long id){
        String sql = "delete from paciente where cd_paciente=?";

        try(PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ps.setLong(1, id);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Erro ao deletar paciente " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return false;
    }
}

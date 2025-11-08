package br.com.fiap.dao;

import br.com.fiap.to.PacienteTO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PacienteDAO {

    public PacienteTO savePatient(PacienteTO pacienteTO){
        String sql = "Insert into paciente (nome, cpf, idade, senha) values (?, ?, ?, ?)";
        try(PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql, new String[]{"cd_paciente"})){
            ps.setString(1, pacienteTO.getName());
            ps.setString(2, pacienteTO.getCpf());
            ps.setInt(3, pacienteTO.getAge());
            ps.setString(4, pacienteTO.getPassword());

            int rows = ps.executeUpdate();
            if (rows == 0) {
                System.out.println("Erro ao inserir paciente!");
                return null;
            }
            long idPaciente;

            try (ResultSet rsKeys = ps.getGeneratedKeys()) {
                if (rsKeys.next()) {
                    idPaciente = rsKeys.getInt(1);
                    pacienteTO.setId(idPaciente);
                } else {
                    System.out.println("Não foi possível recuperar o ID do paciente.");
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
                    pacienteTO.setName(rs.getString("nome"));
                    pacienteTO.setCpf(rs.getString("cpf"));
                    pacienteTO.setAge(rs.getInt("idade"));
                    pacienteTO.setPassword(rs.getString("senha"));
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
            ps.setString(1, pacienteTO.getName());
            ps.setInt(2, pacienteTO.getAge());
            ps.setString(3, pacienteTO.getPassword());
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

    public PacienteTO findByCpfAndPassword(String cpf, String password) {
        PacienteTO paciente = null;
        String sql = "SELECT * FROM paciente WHERE cpf = ? AND senha = ?";

        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {

            ps.setString(1, cpf);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                paciente = new PacienteTO();
                paciente.setId(rs.getLong("cd_paciente"));
                paciente.setName(rs.getString("nome"));
                paciente.setCpf(rs.getString("cpf"));
                paciente.setPassword(rs.getString("password"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return paciente;
    }
}

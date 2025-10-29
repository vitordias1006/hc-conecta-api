package br.com.fiap.dao;

import br.com.fiap.enums.StatusConsultaEnum;
import br.com.fiap.to.ConsultaTO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ConsultaDAO {

    public ArrayList<ConsultaTO> findAllMyAppointments(Long idPaciente){
        ArrayList<ConsultaTO> consultas = new ArrayList<>();
        String sql = "select * from consulta where cd_paciente = ? ";
        try(PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)){
            ps.setLong(1, idPaciente);
            ResultSet resultSet = ps.executeQuery();

            if (resultSet != null) {
                while (resultSet.next()) {
                    ConsultaTO consulta = new ConsultaTO();
                    consulta.setIdConsulta(resultSet.getLong("cd_consulta"));
                    consulta.setIdPaciente(resultSet.getLong("cd_paciente"));
                    consulta.setDataConsulta(resultSet.getDate("data_consulta").toLocalDate());
                    consulta.setHoraConsulta(resultSet.getTime("hora_consulta").toLocalTime());
                    consulta.setEspecialidade(resultSet.getString("especialidade"));
                    consulta.setStatus(StatusConsultaEnum.valueOf(resultSet.getString("status")));
                    consultas.add(consulta);
                }
            } else {
                return null;
            }
        } catch (SQLException e) {
            System.out.println("Erro ao encontrar consultas do paciente " + e.getMessage());
        } finally{
            ConnectionFactory.closeConnection();
        }
        return consultas;
    }

    public ConsultaTO saveConsulta(ConsultaTO consulta) {
        String sqlInsertConsulta =
                "INSERT INTO consulta (cd_paciente, data_consulta, hora_consulta, especialidade) VALUES (?, ?, ?, ?)";

            try (PreparedStatement psInsert = ConnectionFactory.getConnection().prepareStatement(sqlInsertConsulta, new String[]{"cd_consulta"})) {
                psInsert.setLong(1, consulta.getIdPaciente());
                psInsert.setDate(2, java.sql.Date.valueOf(consulta.getDataConsulta()));
                psInsert.setTime(3, java.sql.Time.valueOf(consulta.getHoraConsulta()));
                psInsert.setString(4, consulta.getEspecialidade());

                int rows = psInsert.executeUpdate();
                if (rows == 0) {
                    System.out.println("Erro ao inserir consulta!");
                    return null;
                }

                long idConsulta;
                try (ResultSet rsKeys = psInsert.getGeneratedKeys()) {
                    if (rsKeys.next()) {
                        idConsulta = rsKeys.getInt(1);
                        consulta.setIdConsulta(idConsulta);
                    } else {
                        System.out.println("Não foi possível recuperar o ID da consulta.");
                        return null;
                    }
                }

                consulta.setStatus(StatusConsultaEnum.MARCADA);
            } catch (SQLException e){
                System.out.println("Erro ao inserir consulta! " + e.getMessage());
            } finally {
                ConnectionFactory.closeConnection();
            }
            return consulta;
    }

    public ConsultaTO updateAppointment(ConsultaTO consulta) {
        String sql = "UPDATE consulta SET data_consulta = ?, hora_consulta = ? WHERE cd_consulta = ?";
        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ps.setDate(1, java.sql.Date.valueOf(consulta.getDataConsulta()));
            ps.setTime(2, java.sql.Time.valueOf(consulta.getHoraConsulta()));
            ps.setLong(3, consulta.getIdConsulta());

            if (ps.executeUpdate() > 0) {
                return consulta;
            } else {
                return consulta;
            }

        } catch (SQLException e) {
            System.out.println("SQL ERROR: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return null;
    }

    public ConsultaTO updateAppointmentStatus(ConsultaTO consulta) {
        String sql = "UPDATE consulta SET status = ? WHERE cd_consulta = ?";

        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)){
            ps.setString(1, consulta.getStatus().toString());
            ps.setLong(2, consulta.getIdConsulta());

            if (ps.executeUpdate() > 0) {
                return consulta;
            } else {
                return consulta;
            }

        } catch (SQLException e) {
            System.out.println("SQL ERROR: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return null;
    }

    public ConsultaTO findAppointmentById(Long idConsulta) {
        ConsultaTO consulta = new ConsultaTO();
        String sql = "SELECT * FROM consulta WHERE cd_consulta = ?";

        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)){
            ps.setLong(1, idConsulta);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                consulta.setIdConsulta(rs.getLong("cd_consulta"));
                consulta.setIdPaciente(rs.getLong("cd_paciente"));
                consulta.setDataConsulta(rs.getDate("data_consulta").toLocalDate());
                consulta.setHoraConsulta(rs.getTime("hora_consulta").toLocalTime());
                consulta.setEspecialidade(rs.getString("especialidade"));
                consulta.setStatus(StatusConsultaEnum.valueOf(rs.getString("status")));
            } else {
                return null;
            }

        } catch (SQLException e) {
            System.out.println("Consulta não encontrada: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return consulta;
    }

    public boolean deleteAppointment(Long idConsulta) {
        String sql = "DELETE FROM consulta WHERE cd_consulta = ?";

        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)){
            ps.setLong(1, idConsulta);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("SQL ERROR: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return false;
    }

}

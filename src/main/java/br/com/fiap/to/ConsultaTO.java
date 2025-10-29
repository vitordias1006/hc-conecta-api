package br.com.fiap.to;

import br.com.fiap.enums.StatusConsultaEnum;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.time.LocalTime;

public class ConsultaTO {

    private Long idConsulta;

    private Long idPaciente;

    @NotNull
    @FutureOrPresent
    private LocalDate dataConsulta;

    @NotNull
    private LocalTime horaConsulta;

    private StatusConsultaEnum status;

    @NotBlank
    @Size(min = 5, max = 50)
    private String especialidade;

    public ConsultaTO(){

    }

    public ConsultaTO(Long idConsulta, Long idPaciente, LocalDate dataConsulta, LocalTime horaConsulta, String especialidade,  StatusConsultaEnum status) {
        this.idConsulta = idConsulta;
        this.idPaciente = idPaciente;
        this.dataConsulta = dataConsulta;
        this.horaConsulta = horaConsulta;
        this.especialidade = especialidade;
        this.status = status;
    }

    public Long getIdConsulta() {
        return idConsulta;
    }

    public void setIdConsulta(Long idConsulta) {
        this.idConsulta = idConsulta;
    }

    public Long getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(Long idPaciente) {
        this.idPaciente = idPaciente;
    }

    public LocalDate getDataConsulta() {
        return dataConsulta;
    }

    public void setDataConsulta(LocalDate dataConsulta) {
        this.dataConsulta = dataConsulta;
    }

    public LocalTime getHoraConsulta() {
        return horaConsulta;
    }

    public void setHoraConsulta(LocalTime horaConsulta) {
        this.horaConsulta = horaConsulta;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }

    public StatusConsultaEnum getStatus() {
        return status;
    }

    public void setStatus(StatusConsultaEnum status) {
        this.status = status;
    }
}

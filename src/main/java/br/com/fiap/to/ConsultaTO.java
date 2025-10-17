package br.com.fiap.to;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.time.LocalTime;

public class ConsultaTO {

    private Long idConsulta;

    private Long idPaciente;

    private LocalDate dataConsulta;

    private LocalTime horaConsulta;

    private String avaliacao;

    @NotBlank
    @Size(min = 5, max = 20)
    private String especialidade;
}

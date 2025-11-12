package br.com.fiap.to;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class PacienteTO {

    private Long id;

    @NotBlank(message = "Nome é obrigatório")
    @Size(min = 3, max = 100)
    private String name;

    @NotBlank(message = "CPF é obrigatório")
    @Size(min = 11, max = 11)
    private String cpf;

    @NotNull
    private Integer age;

    @NotBlank
    @Size(min = 8, max = 30)
    private String password;


    public PacienteTO() {
    }

    public PacienteTO(Long id, String name, String cpf, Integer age, String senha) {
        this.id = id;
        this.name = name;
        this.cpf = cpf;
        this.age = age;
        this.password = senha;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

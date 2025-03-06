package com.devsuperior.CRUD.dto;

import com.devsuperior.CRUD.entities.Client;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;

import java.time.LocalDate;

public class ClientDto {

    private Integer id;

    @NotBlank(message = "Campo requerido")
    private String name;
    private String cpf;
    private Double income;

    @PastOrPresent(message = "Não pode ser data futura")
    @JsonProperty("birthDate")
    private LocalDate birthDate;
    private Integer children;

    public ClientDto(Client client) {
        id = client.getId();
        children = client.getChildren();
        birthDate = client.getBirthDate();
        income = client.getIncome();
        cpf = client.getCpf();
        name = client.getName();
    }

    public ClientDto() {

    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public Double getIncome() {
        return income;
    }

    public void setIncome(Double income) {
        this.income = income;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public Integer getChildren() {
        return children;
    }

    public void setChildren(Integer children) {
        this.children = children;
    }
}

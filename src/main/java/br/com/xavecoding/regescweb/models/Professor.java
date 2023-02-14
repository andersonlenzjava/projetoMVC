package br.com.xavecoding.regescweb.models;


import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
public class Professor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String nome;
    private BigDecimal salario;
    @Enumerated(EnumType.STRING)
    private StatusProfessor statusProfessor;

    public Professor () {}

    public Professor(String nome, BigDecimal salario, StatusProfessor statusProfessor) {
        this.nome = nome;
        this.salario = salario;
        this.statusProfessor = statusProfessor;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setSalario(BigDecimal salario) {
        this.salario = salario;
    }

    public void setStatusProfessor(StatusProfessor statusProfessor) {
        this.statusProfessor = statusProfessor;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public StatusProfessor getStatusProfessor() {
        return statusProfessor;
    }

    @Override
    public String toString() {
        return "Professor{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", salario=" + salario +
                ", statusProfessor=" + statusProfessor +
                '}';
    }
}

package br.com.poo.dualidade.Entity;

import java.time.LocalDate;

public class DemandaEntity {
    private Integer id;
    private EmpresaEntity dono;
    private String titulo;
    private String descricao;
    private LocalDate prazo;
    private Boolean disponivel;
    private Boolean concluida;

    public DemandaEntity(EmpresaEntity dono, String titulo, String descricao, LocalDate prazo) {
        this.dono = dono;
        this.titulo = titulo;
        this.descricao = descricao;
        this.prazo = prazo;
        this.disponivel = true;
        this.concluida = false;
    }

    public DemandaEntity() {
        this.disponivel = true;
        this.concluida = false;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public EmpresaEntity getDono() {
        return dono;
    }

    public void setDono(EmpresaEntity dono) {
        this.dono = dono;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDate getPrazo() {
        return prazo;
    }

    public void setPrazo(LocalDate prazo) {
        this.prazo = prazo;
    }

    public Boolean getDisponivel() {
        return disponivel;
    }

    public void setDisponivel(Boolean disponivel) {
        this.disponivel = disponivel;
    }

    public Boolean getConcluida() {
        return concluida;
    }

    public void setConcluida(Boolean concluida) {
        this.concluida = concluida;
    }
}

package br.com.poo.dualidade.Entity;

import br.com.poo.dualidade.Repository.FakeDb;

import java.util.ArrayList;
import java.util.List;

public class WorkspaceEntity {
    private Integer id;
    private String nome;
    private List<DemandaEntity> demandas;

    public WorkspaceEntity(String nome) {
        this.nome = nome;
        this.demandas = new ArrayList<>();
    }

    public WorkspaceEntity() {
        this.demandas = new ArrayList<>();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<DemandaEntity> getDemandas() {
        return demandas;
    }

    public void setDemandas(List<DemandaEntity> demandas) {
        this.demandas = demandas;
    }
}

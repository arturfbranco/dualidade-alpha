package br.com.poo.dualidade.Repository;

import br.com.poo.dualidade.Entity.*;

import java.util.ArrayList;
import java.util.List;

public class FakeDb {
    public static Integer contatoSeq = -1;
    public static List<ContatoEntity> contatoEntities = new ArrayList<>();

    public static Integer demandaSeq = -1;
    public static List<DemandaEntity> demandaEntities = new ArrayList<>();

    public static Integer empresaSeq = -1;
    public static List<EmpresaEntity> empresaEntities = new ArrayList<>();

    public static Integer estudanteWorkspaceSeq = -1;
    public static List<EstudanteWorkspaceEntity> estudanteWorkspaceEntities = new ArrayList<>();

    public static Integer estudanteSeq = -1;
    public static List<EstudanteEntity> estudanteEntities = new ArrayList<>();

    public static Integer workspaceSeq = -1;
    public static List<WorkspaceEntity> workspaceEntities = new ArrayList<>();

    public static Integer getNextContatoId() {
        contatoSeq += 1;
        return contatoSeq;
    }

    public static Integer getNextDemandaId() {
        demandaSeq += 1;
        return demandaSeq;
    }

    public static Integer getNextEmpresaId() {
        empresaSeq += 1;
        return empresaSeq;
    }

    public static Integer getNextEstudanteWorkspaceId() {
        estudanteWorkspaceSeq += 1;
        return estudanteWorkspaceSeq;
    }

    public static Integer getNextEstudanteId() {
        estudanteSeq += 1;
        return estudanteSeq;
    }
    public static Integer getNextWorkspaceId() {
        workspaceSeq += 1;
        return workspaceSeq;
    }
}

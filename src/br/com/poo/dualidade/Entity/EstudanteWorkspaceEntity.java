package br.com.poo.dualidade.Entity;

public class EstudanteWorkspaceEntity {
    private Integer id;
    private EstudanteEntity estudante;
    private WorkspaceEntity workspace;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public EstudanteEntity getEstudante() {
        return estudante;
    }

    public void setEstudante(EstudanteEntity estudante) {
        this.estudante = estudante;
    }

    public WorkspaceEntity getWorkspace() {
        return workspace;
    }

    public void setWorkspace(WorkspaceEntity workspace) {
        this.workspace = workspace;
    }
}

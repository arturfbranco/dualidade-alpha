package br.com.poo.dualidade.Entity;

public class EstudanteEntity extends UsuarioEntity{
    private String instituicaoEnsino;

    public EstudanteEntity( UsuarioEntity usuario ) {
        super( usuario );
    }

    public EstudanteEntity() {
    }

    public String getInstituicaoEnsino() {
        return instituicaoEnsino;
    }

    public void setInstituicaoEnsino(String instituicaoEnsino) {
        this.instituicaoEnsino = instituicaoEnsino;
    }

}

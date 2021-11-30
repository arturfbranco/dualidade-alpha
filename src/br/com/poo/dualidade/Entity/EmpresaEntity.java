package br.com.poo.dualidade.Entity;

public class EmpresaEntity extends UsuarioEntity{
    private String cnpj;
    private String ramoAtuacao;

    public EmpresaEntity( UsuarioEntity usuario ) {
        super( usuario );
    }

    public EmpresaEntity() {
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getRamoAtuacao() {
        return ramoAtuacao;
    }

    public void setRamoAtuacao(String ramoAtuacao) {
        this.ramoAtuacao = ramoAtuacao;
    }
}

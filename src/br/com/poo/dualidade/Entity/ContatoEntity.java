package br.com.poo.dualidade.Entity;

public class ContatoEntity {
    private Integer id;
    private TipoContatoEnum tipoContato;
    private String valor;

    public ContatoEntity(TipoContatoEnum tipoContato, String valor) {
        this.tipoContato = tipoContato;
        this.valor = valor;
    }

    public ContatoEntity() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public TipoContatoEnum getTipoContato() {
        return tipoContato;
    }

    public void setTipoContato(TipoContatoEnum tipoContato) {
        this.tipoContato = tipoContato;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }
}

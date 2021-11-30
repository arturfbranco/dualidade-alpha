package br.com.poo.dualidade.Entity;

import java.util.List;

public class UsuarioEntity {
    private Integer id;
    private String username;
    private String senha;
    private String nome;
    private List<ContatoEntity> contatos;

    public UsuarioEntity( UsuarioEntity u ) {
        this.username = u.getUsername();
        this.senha = u.getSenha();
        this.nome = u.getNome();
        this.contatos = u.getContatos();
    }

    public UsuarioEntity() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<ContatoEntity> getContatos() {
        return contatos;
    }

    public void setContatos(List<ContatoEntity> contatos) {
        this.contatos = contatos;
    }
}

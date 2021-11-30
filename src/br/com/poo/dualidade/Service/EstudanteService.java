package br.com.poo.dualidade.Service;

import br.com.poo.dualidade.Entity.ContatoEntity;
import br.com.poo.dualidade.Entity.EstudanteEntity;
import br.com.poo.dualidade.Entity.WorkspaceEntity;
import br.com.poo.dualidade.Repository.ContatoRepository;
import br.com.poo.dualidade.Repository.EstudanteRepository;
import br.com.poo.dualidade.Repository.EstudanteWorkspaceRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class EstudanteService {

    private static EstudanteEntity estudanteEmSessao;
    private final EstudanteRepository estudanteRepository = new EstudanteRepository();
    public final EstudanteWorkspaceRepository estudanteWorkspaceRepository = new EstudanteWorkspaceRepository();
    private final ContatoRepository contatoRepository = new ContatoRepository();

    public EstudanteEntity cadastrarEstudante( EstudanteEntity estudante ){
        if( this.estudanteRepository.findByUsername( estudante.getUsername() ) == null ){
            estudante.setContatos( this.cadastrarContatos( estudante.getContatos() ) );
            return this.estudanteRepository.createEstudante( estudante );
        }
        return null;
    }
    public void atualizarEstudante( EstudanteEntity estudante, Integer id ){
        if( this.estudanteRepository.findByUsername( estudante.getUsername() ) == null ){
            estudante.setContatos( this.cadastrarContatos( estudante.getContatos() ) );
            estudanteEmSessao = this.estudanteRepository.updateEstudante( estudante, id );
        }
    }

    private List<ContatoEntity> cadastrarContatos(List<ContatoEntity> contatos ){
        List<ContatoEntity> contatosCadastrados = new ArrayList<>();
        for ( ContatoEntity contato : contatos ){
            contatosCadastrados.add( contatoRepository.createContato( contato ) );
        }
        return contatosCadastrados;
    }

    public boolean fazerLogin( String username, String senha ){
        EstudanteEntity estudante = this.estudanteRepository.findByUsername( username );
        if( estudante != null ){
            if( estudante.getSenha().equals( senha ) ){
                estudanteEmSessao = estudante;
                return true;
            }
        }
        return false;
    }
    public void fazerLogout(){
        estudanteEmSessao = null;
    }
    public EstudanteEntity buscarDadosEstudante(){
        return estudanteEmSessao;
    }

    public List<WorkspaceEntity> buscarWorkspaces(){
        return this.estudanteWorkspaceRepository
                .findByEstudante( estudanteEmSessao.getId() )
                .stream()
                .map( ew -> ew.getWorkspace() )
                .collect( Collectors.toList() );
    }
}

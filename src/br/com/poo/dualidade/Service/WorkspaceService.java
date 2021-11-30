package br.com.poo.dualidade.Service;

import br.com.poo.dualidade.Entity.DemandaEntity;
import br.com.poo.dualidade.Entity.EstudanteEntity;
import br.com.poo.dualidade.Entity.EstudanteWorkspaceEntity;
import br.com.poo.dualidade.Entity.WorkspaceEntity;
import br.com.poo.dualidade.Repository.DemandaRepository;
import br.com.poo.dualidade.Repository.EstudanteRepository;
import br.com.poo.dualidade.Repository.EstudanteWorkspaceRepository;
import br.com.poo.dualidade.Repository.WorkspaceRepository;

import java.util.List;
import java.util.stream.Collectors;

public class WorkspaceService {
    private static WorkspaceEntity workspaceEmUso;

    private final WorkspaceRepository workspaceRepository = new WorkspaceRepository();
    private final EstudanteWorkspaceRepository estudanteWorkspaceRepository = new EstudanteWorkspaceRepository();
    private final EstudanteRepository estudanteRepository = new EstudanteRepository();
    private final DemandaRepository demandaRepository = new DemandaRepository();

    private final EstudanteService estudanteService = new EstudanteService();

    public boolean acessarWorkspace( Integer workspaceId ){
        if( this.workspaceValido( workspaceId ) ){
            workspaceEmUso = this.workspaceRepository.findById( workspaceId );
            return true;
        }
        return false;
    }

    public WorkspaceEntity buscarWorkspaceEmUso(){
        return workspaceEmUso;
    }

    public boolean workspaceValido(Integer workspaceId){
        return estudanteWorkspaceRepository
                .findByEstudante( estudanteService.buscarDadosEstudante().getId() )
                .stream()
                .map( ew -> ew.getWorkspace().getId() )
                .anyMatch( id -> id.equals( workspaceId ) );
    }
    public void sairWorkspace(){
        workspaceEmUso = null;
    }

    public WorkspaceEntity criarWorkspace( WorkspaceEntity workspace ){
        WorkspaceEntity workspaceSalvo = this.workspaceRepository.createWorkspace( workspace );
        EstudanteWorkspaceEntity ew = new EstudanteWorkspaceEntity();
        ew.setWorkspace( workspaceSalvo );
        ew.setEstudante( estudanteService.buscarDadosEstudante() );
        this.estudanteWorkspaceRepository.createEstudanteWorkspace( ew );
        return workspaceSalvo;
    }

    public boolean adicionarEstudante(String username){
        EstudanteEntity estudante = this.estudanteRepository.findByUsername( username );
        if( estudante != null ){
            EstudanteWorkspaceEntity ew = new EstudanteWorkspaceEntity();
            ew.setEstudante( estudante );
            ew.setWorkspace( workspaceEmUso );
            this.estudanteWorkspaceRepository.createEstudanteWorkspace( ew );
            return true;
        }
        return false;
    }
    public boolean adicionarDemanda( Integer demandaId ){
        DemandaEntity demanda = this.demandaRepository.findById( demandaId );
        if( demanda != null ){
            if( demanda.getDisponivel() ){
                demanda.setDisponivel(false);
                this.demandaRepository.editarDemanda( demanda, demanda.getId() );
                workspaceEmUso.getDemandas().add( demanda );
                this.workspaceRepository.updateWorkspace( workspaceEmUso, workspaceEmUso.getId() );
                return true;
            }
        }
        return false;
    }
    public boolean concluirDemanda( Integer demandaId ){
        DemandaEntity demanda = buscarDemandaWorkspace( demandaId );
        if( demanda != null ){
            demanda.setConcluida( true );
            DemandaEntity demandaAlterada = this.demandaRepository.editarDemanda( demanda, demanda.getId() );
            this.alterarDemandaDaLista( demandaAlterada );
            this.workspaceRepository.updateWorkspace( workspaceEmUso, workspaceEmUso.getId() );
            return true;
        }
        return false;
    }

    private void alterarDemandaDaLista( DemandaEntity demandaNova ){
        for( int i = 0; i < workspaceEmUso.getDemandas().size(); i++ ){
            if( workspaceEmUso.getDemandas().get( i ).getId().equals( demandaNova.getId() ) ){
                workspaceEmUso.getDemandas().set( i, demandaNova );
            }
        }
    }

    public WorkspaceEntity verDadosWorkspace(){
        return workspaceEmUso;
    }

    public List<EstudanteEntity> verEstudantesWorkspace(){
        return this.estudanteWorkspaceRepository
                .findByWorkspace(workspaceEmUso.getId() )
                .stream()
                .map( ew -> ew.getEstudante() )
                .collect( Collectors.toList() );
    }

    public DemandaEntity buscarDemandaWorkspace( Integer id ){
        return workspaceEmUso.getDemandas()
                .stream()
                .filter( d -> d.getId().equals( id ) )
                .findFirst()
                .orElse( null );
    }

    public WorkspaceEntity buscarPorDemanda( Integer demandaId ){
        return this.workspaceRepository.findByDemanda( demandaId );
    }

    public List<DemandaEntity> buscarDemandasDisponiveis(){
        return demandaRepository.findByDisponivel( true );
    }
}

package br.com.poo.dualidade.Repository;

import br.com.poo.dualidade.Entity.WorkspaceEntity;

import java.util.List;
import java.util.stream.Collectors;

public class WorkspaceRepository {
    public WorkspaceEntity createWorkspace( WorkspaceEntity workspace ){
        workspace.setId( FakeDb.getNextWorkspaceId() );
        FakeDb.workspaceEntities.add( workspace );
        return workspace;
    }
    public WorkspaceEntity updateWorkspace( WorkspaceEntity workspace, Integer id ){
        workspace.setId( id );
        for ( int i = 0; i < FakeDb.workspaceEntities.size(); i++ ){
            if( FakeDb.workspaceEntities.get( i ).getId().equals( workspace.getId() ) ){
                FakeDb.workspaceEntities.set( i, workspace );
                return workspace;
            }
        }
        return null;
    }
    public List<WorkspaceEntity> findAll(){
        return FakeDb.workspaceEntities;
    }
    public WorkspaceEntity findById( Integer id ){
        return FakeDb.workspaceEntities
                .stream()
                .filter( workspace -> workspace.getId().equals( id ) )
                .findFirst()
                .orElse( null );
    }
    public WorkspaceEntity findByDemanda( Integer demandaId ){
        return FakeDb.workspaceEntities
                .stream()
                .filter( ws -> ws.getDemandas()
                        .stream()
                        .map( demanda -> demanda.getId() )
                        .collect( Collectors.toList() )
                        .contains( demandaId )
                )
                .findFirst()
                .orElse( null );
    }
}

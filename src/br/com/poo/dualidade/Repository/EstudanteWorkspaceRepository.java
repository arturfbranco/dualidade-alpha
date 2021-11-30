package br.com.poo.dualidade.Repository;

import br.com.poo.dualidade.Entity.EstudanteWorkspaceEntity;

import java.util.List;
import java.util.stream.Collectors;

public class EstudanteWorkspaceRepository {
    public EstudanteWorkspaceEntity createEstudanteWorkspace( EstudanteWorkspaceEntity estudanteWorkspace ){
        estudanteWorkspace.setId( FakeDb.getNextEstudanteWorkspaceId() );
        FakeDb.estudanteWorkspaceEntities.add( estudanteWorkspace );
        return estudanteWorkspace;
    }
    public EstudanteWorkspaceEntity updateEstudanteWorkspace( EstudanteWorkspaceEntity estudanteWorkspace, Integer id ){
        estudanteWorkspace.setId( id );
        for ( int i = 0; i < FakeDb.estudanteWorkspaceEntities.size(); i++ ){
            if( FakeDb.estudanteWorkspaceEntities.get( i ).getId().equals( estudanteWorkspace.getId() ) ){
                FakeDb.estudanteWorkspaceEntities.set( i, estudanteWorkspace );
                return estudanteWorkspace;
            }
        }
        return null;
    }
    public List<EstudanteWorkspaceEntity> findAll(){
        return FakeDb.estudanteWorkspaceEntities;
    }

    public EstudanteWorkspaceEntity findById( Integer id ){
        return FakeDb.estudanteWorkspaceEntities
                .stream()
                .filter( ew -> ew.getId().equals( id ) )
                .findFirst()
                .orElse( null );
    }

    public List<EstudanteWorkspaceEntity> findByEstudante( Integer estudanteId ){
        return FakeDb.estudanteWorkspaceEntities
                .stream()
                .filter( ew -> ew.getEstudante().getId().equals( estudanteId ) )
                .collect( Collectors.toList() );
    }
    public List<EstudanteWorkspaceEntity> findByWorkspace( Integer workspaceId ){
        return FakeDb.estudanteWorkspaceEntities
                .stream()
                .filter( ew -> ew.getWorkspace().getId().equals( workspaceId ) )
                .collect( Collectors.toList() );
    }
}

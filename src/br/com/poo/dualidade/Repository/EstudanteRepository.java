package br.com.poo.dualidade.Repository;

import br.com.poo.dualidade.Entity.EstudanteEntity;

import java.util.List;

public class EstudanteRepository {
    public EstudanteEntity createEstudante( EstudanteEntity estudante ){
        estudante.setId( FakeDb.getNextEstudanteId() );
        FakeDb.estudanteEntities.add( estudante );
        return estudante;
    }
    public EstudanteEntity updateEstudante( EstudanteEntity estudante, Integer id ){
        estudante.setId( id );
        for ( int i = 0; i < FakeDb.estudanteEntities.size(); i++ ){
            if( FakeDb.estudanteEntities.get(i).getId().equals( estudante.getId() ) ){
                FakeDb.estudanteEntities.set( i, estudante );
                return estudante;
            }
        }
        return null;
    }

    public List<EstudanteEntity> findAll(){
        return FakeDb.estudanteEntities;
    }

    public EstudanteEntity findById( Integer id ){
        return FakeDb.estudanteEntities
                .stream()
                .filter( estudante -> estudante.getId().equals( id ) )
                .findFirst()
                .orElse( null );
    }

    public EstudanteEntity findByUsername( String username ){
        return FakeDb.estudanteEntities
                .stream()
                .filter( estudante -> estudante.getUsername().equals( username ) )
                .findFirst()
                .orElse( null );
    }
}

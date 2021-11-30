package br.com.poo.dualidade.Repository;

import br.com.poo.dualidade.Entity.ContatoEntity;

public class ContatoRepository {

    public ContatoEntity createContato( ContatoEntity contato ){
        contato.setId( FakeDb.getNextContatoId() );
        FakeDb.contatoEntities.add( contato );
        return contato;
    }
    public ContatoEntity updateContato( ContatoEntity contato, Integer id ){
        contato.setId( id );
        for( int i = 0; i < FakeDb.contatoEntities.size(); i++ ){
            if( FakeDb.contatoEntities.get(i).getId().equals( contato.getId() ) ){
                FakeDb.contatoEntities.set( i, contato );
                return contato;
            }
        }
        return null;
    }

    public ContatoEntity findById( Integer id ){
        return FakeDb.contatoEntities
                .stream()
                .filter( contato -> contato.getId().equals( id ) )
                .findFirst()
                .get();
    }


}

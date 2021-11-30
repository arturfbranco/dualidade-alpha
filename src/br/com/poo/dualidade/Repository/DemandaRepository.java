package br.com.poo.dualidade.Repository;

import br.com.poo.dualidade.Entity.DemandaEntity;

import java.util.List;
import java.util.stream.Collectors;

public class DemandaRepository {

    public DemandaEntity createDemanda( DemandaEntity demanda ){
        demanda.setId( FakeDb.getNextDemandaId() );
        FakeDb.demandaEntities.add( demanda );
        return demanda;
    }
    public DemandaEntity editarDemanda( DemandaEntity demanda, Integer id ){
        demanda.setId( id );
        for ( int i = 0; i < FakeDb.demandaEntities.size(); i++ ){
            if( FakeDb.demandaEntities.get(i).getId().equals( demanda.getId() ) ){
                FakeDb.demandaEntities.set( i, demanda );
                return demanda;
            }
        }
        return null;
    }

    public List<DemandaEntity> findAll(){
        return FakeDb.demandaEntities;
    }
    public DemandaEntity findById( Integer id ){
        return FakeDb.demandaEntities
                .stream()
                .filter( demanda -> demanda.getId().equals( id ) )
                .findFirst()
                .orElse( null );
    }
    public List<DemandaEntity> findByDono( Integer idDono ){
        return FakeDb.demandaEntities
                .stream()
                .filter( demanda -> demanda.getDono().getId().equals( idDono ) )
                .collect( Collectors.toList() );
    }

    public List<DemandaEntity> findByDisponivel( Boolean isDisponivel ){
        return this.filtrarPorDisponivel( FakeDb.demandaEntities, isDisponivel );
    }

    public List<DemandaEntity> findByConcluida( Boolean isConcluida ){
        return this.filtrarPorConcluida( FakeDb.demandaEntities, isConcluida );
    }

    public List<DemandaEntity> findByDonoEDisponivel( Integer donoId, Boolean isDisponivel ){
        return this.filtrarPorDisponivel( this.findByDono( donoId ), isDisponivel );
    }

    public List<DemandaEntity> findByDonoEConcluida(Integer donoId, Boolean isConcluida){
        return this.filtrarPorConcluida( this.findByDono( donoId ), isConcluida );
    }
    public List<DemandaEntity> findByDonoEEmDesenvolvimento( Integer donoId ){
        return this.filtrarPorDisponivel( this.findByDonoEConcluida( donoId, false ), false );
    }

    private List<DemandaEntity> filtrarPorConcluida( List<DemandaEntity> demandas, Boolean isConcluida ){
        return demandas
                .stream()
                .filter( demanda -> demanda.getConcluida().equals( isConcluida ) )
                .collect( Collectors.toList() );
    }

    private List<DemandaEntity> filtrarPorDisponivel( List<DemandaEntity> demandas, Boolean isDisponivel ){
        return demandas
                .stream()
                .filter( demanda -> demanda.getDisponivel().equals( isDisponivel ) )
                .collect( Collectors.toList() );
    }
}

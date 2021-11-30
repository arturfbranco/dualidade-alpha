package br.com.poo.dualidade.Repository;

import br.com.poo.dualidade.Entity.EmpresaEntity;

import java.util.List;
import java.util.Optional;

public class EmpresaRepository {
    public EmpresaEntity createEmpresa(EmpresaEntity empresa) {
        empresa.setId(FakeDb.getNextEmpresaId());
        FakeDb.empresaEntities.add(empresa);
        return empresa;
    }

    public EmpresaEntity updateEmpresa(EmpresaEntity empresa, Integer id) {
        empresa.setId(id);
        for (int i = 0; i < FakeDb.empresaEntities.size(); i++) {
            if (FakeDb.empresaEntities.get(i).getId().equals(empresa.getId())) {
                FakeDb.empresaEntities.set(i, empresa);
                return empresa;
            }
        }
        return null;
    }

    public List<EmpresaEntity> findAll() {
        return FakeDb.empresaEntities;
    }

    public EmpresaEntity findById(Integer id) {
        return FakeDb.empresaEntities
                .stream()
                .filter(empresa -> empresa.getId().equals(id))
                .findFirst()
                .orElse( null );
    }

    public EmpresaEntity findByUsername( String username ){
        return FakeDb.empresaEntities
                .stream()
                .filter( empresa -> empresa.getUsername().equals( username ) )
                .findFirst()
                .orElse( null );
    }
}
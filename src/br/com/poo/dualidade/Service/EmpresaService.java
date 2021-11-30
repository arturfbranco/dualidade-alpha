package br.com.poo.dualidade.Service;
import br.com.poo.dualidade.Entity.ContatoEntity;
import br.com.poo.dualidade.Entity.DemandaEntity;
import br.com.poo.dualidade.Entity.EmpresaEntity;
import br.com.poo.dualidade.Repository.ContatoRepository;
import br.com.poo.dualidade.Repository.DemandaRepository;
import br.com.poo.dualidade.Repository.EmpresaRepository;

import java.util.ArrayList;
import java.util.List;

public class EmpresaService {
    public static EmpresaEntity empresaEmSessao;

    private final EmpresaRepository empresaRepository = new EmpresaRepository();
    private final ContatoRepository contatoRepository = new ContatoRepository();
    private final DemandaRepository demandaRepository = new DemandaRepository();

    public EmpresaEntity cadastrarEmpresa( EmpresaEntity empresa ){
        if( this.empresaRepository.findByUsername( empresa.getUsername() ) == null ){
            empresa.setContatos( this.cadastrarContatos( empresa.getContatos() ) );
            return this.empresaRepository.createEmpresa( empresa );
        }
        return null;
    }

    public void atualizarEmpresa( EmpresaEntity empresa, Integer id ){
        empresa.setContatos( this.cadastrarContatos( empresa.getContatos() ) );
        empresaEmSessao = this.empresaRepository.updateEmpresa( empresa, id );
    }

    private List<ContatoEntity> cadastrarContatos( List<ContatoEntity> contatos ){
        List<ContatoEntity> contatosCadastrados = new ArrayList<>();
        for ( ContatoEntity contato : contatos ){
            contatosCadastrados.add( contatoRepository.createContato( contato ) );
        }
        return contatosCadastrados;
    }

    public boolean fazerLogin( String username, String senha ){
        EmpresaEntity empresa = this.empresaRepository.findByUsername( username );
        if( empresa != null ){
            if( empresa.getSenha().equals( senha ) ){
                empresaEmSessao = empresa;
                return true;
            }
        }
        return false;
    }
    public void fazerLogout(){
        empresaEmSessao = null;
    }

    public EmpresaEntity buscarDadosEmpresa(){
        return empresaEmSessao;
    }

    public List<DemandaEntity> buscarDemandas(){
        return this.demandaRepository.findByDono( empresaEmSessao.getId() );
    }
    public List<DemandaEntity> buscarDemandasConcluidas(){
        return this.demandaRepository.findByDonoEConcluida( empresaEmSessao.getId(), true );
    }
    public List<DemandaEntity> buscarDemandasDisponiveis(){
        return this.demandaRepository.findByDonoEDisponivel( empresaEmSessao.getId(), true );
    }
    public List<DemandaEntity> buscarDemandasEmDesenvolvimento(){
        return this.demandaRepository.findByDonoEEmDesenvolvimento(empresaEmSessao.getId() );
    }
    public DemandaEntity criarDemanda( DemandaEntity demanda ){
        return this.demandaRepository.createDemanda( demanda );
    }
}

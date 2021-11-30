package br.com.poo.dualidade.View;

import br.com.poo.dualidade.Entity.DemandaEntity;
import br.com.poo.dualidade.Main;
import br.com.poo.dualidade.Service.EmpresaService;
import br.com.poo.dualidade.Service.WorkspaceService;

import java.time.LocalDate;
import java.util.Scanner;

public class DemandaView {
    private static final EmpresaService empresaService = new EmpresaService();
    private static final WorkspaceService workspaceService = new WorkspaceService();

    public static void demandaInicial(){
        Scanner scanner = new Scanner( System.in );
        int seletor = 0;
        while ( seletor != 6 ){
            Main.imprimirLinha();
            System.out.println( "HUB DE DEMANDAS" );
            System.out.println("1. Ver todas as minhas demandas.\n2. Ver demandas disponíveis.\n" +
                    "3. Ver demandas em desenvolvimento.\n4. Ver demandas concluídas." +
                    "\n5. Criar demanda.\n6. Voltar.");
            seletor = scanner.nextInt();
            if( seletor == 1 ) verTodas();
            if( seletor == 2 ) verDisponiveis();
            if( seletor == 3 ) verEmDesenvolvimento();
            if( seletor == 4 ) verConcluidas();
            if( seletor == 5 ) criarDemanda();
            if( seletor != 1
                    && seletor != 2
                    && seletor != 3
                    && seletor != 4
                    && seletor != 5
                    && seletor != 6 ) System.out.println( "Opção inválida. Tente novamente." );
        }
    }
    private static void verTodas(){
        Main.imprimirLinha();
        System.out.println( "Todas as demandas da empresa:" );
        for(DemandaEntity demanda : empresaService.buscarDemandas() ){
            imprimirDemanda( demanda );
        }
    }
    private static void verDisponiveis(){
        Main.imprimirLinha();
        System.out.println( "Demandas com Status DISPONÍVEL:" );
        for(DemandaEntity demanda : empresaService.buscarDemandasDisponiveis() ){
            imprimirDemanda( demanda );
        }
    }
    private static void verEmDesenvolvimento(){
        Main.imprimirLinha();
        System.out.println( "Demandas com Status EM DESENVOLVIMENTO:" );
        for(DemandaEntity demanda : empresaService.buscarDemandasEmDesenvolvimento() ){
            imprimirDemanda( demanda );
        }
    }
    private static void verConcluidas(){
        Main.imprimirLinha();
        System.out.println( "Demandas com Status CONCLUÍDA:" );
        for(DemandaEntity demanda : empresaService.buscarDemandasConcluidas() ){
            imprimirDemanda( demanda );
        }
    }
    private static void criarDemanda(){
        Scanner scanner = new Scanner( System.in );
        DemandaEntity demanda = new DemandaEntity();
        demanda.setDono( empresaService.buscarDadosEmpresa() );
        Main.imprimirLinha();
        System.out.println( "Nos dê algumas informações para registrarmos a sua demanda no sistema." );
        System.out.println( "Dê um título para a sua demanda:" );
        demanda.setTitulo( scanner.nextLine().toUpperCase() );
        System.out.println( "Faça uma breve descrição da demanda:" );
        demanda.setDescricao( scanner.nextLine() );
        System.out.println( "Informe o prazo para a conclusão da demanda (utilize o formato YYYY-MM-DD):" );
        demanda.setPrazo(LocalDate.parse( scanner.nextLine() ) );
        DemandaEntity demandaSalva = empresaService.criarDemanda( demanda );
        if( demandaSalva != null ){
            System.out.println( "Demanda registrada com sucesso!" );
            imprimirDemanda( demandaSalva );
        }else {
            System.out.println( "Erro ao registrar demanda. " +
                    "Confira os dados (sobretudo o formato da data) e tente novamente" );
        }

    }

    private static void imprimirDemanda( DemandaEntity demanda ){
        System.out.println( "ID: " + demanda.getId() );
        System.out.println( "Empresa Emissora: " + demanda.getDono().getNome() );
        System.out.println( "Título: " + demanda.getTitulo() );
        System.out.println( "Descrição: " + demanda.getDescricao() );
        System.out.println( "Prazo: " + demanda.getPrazo() );
        String status = demanda.getDisponivel() ? "DISPONÍVEL" : demanda.getConcluida() ? "CONCLUÍDA" : "EM DESENVOLVIMENTO";
        System.out.println( "Status: " + status );
        if( !demanda.getDisponivel() ){
            String workspaceResponsavel = workspaceService.buscarPorDemanda( demanda.getId() ).getNome();
            System.out.println( "Workspace responsável: " + workspaceResponsavel );
        }
        Main.imprimirLinha();
    }
}

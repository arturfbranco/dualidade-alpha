package br.com.poo.dualidade.View;

import br.com.poo.dualidade.Entity.ContatoEntity;
import br.com.poo.dualidade.Entity.DemandaEntity;
import br.com.poo.dualidade.Entity.EstudanteEntity;
import br.com.poo.dualidade.Entity.WorkspaceEntity;
import br.com.poo.dualidade.Main;
import br.com.poo.dualidade.Service.EstudanteService;
import br.com.poo.dualidade.Service.WorkspaceService;

import java.util.Scanner;

public class WorkspaceView {
    private static final WorkspaceService workspaceService = new WorkspaceService();
    private static final EstudanteService estudanteService = new EstudanteService();

    public static void workspacesInicial(){
        Scanner scanner = new Scanner( System.in );
        int seletor = 0;
        while ( seletor != 4 ){
            Main.imprimirLinha();
            System.out.println( "HUB DE WORKSPACES" );
            System.out.println("1. Ver meus Workspaces.\n2. Acessar um Workspace.\n" +
                    "3. Criar um Workspace.\n4. Voltar.");
            seletor = scanner.nextInt();
            if( seletor == 1 ) verTodos();
            if( seletor == 2 ) acessarWorkspace();
            if( seletor == 3 ) criarWorkspace();
            if( seletor != 1 && seletor != 2 && seletor != 3 && seletor != 4 )
                System.out.println( "Opção inválida. Tente novamente." );
        }
    }
    private static void verTodos(){
        Main.imprimirLinha();
        System.out.println( "Meus Workspaces:" );
        for (WorkspaceEntity ws : estudanteService.buscarWorkspaces()){
            System.out.println( "ID: " + ws.getId() );
            System.out.println( "Nome: " + ws.getNome() );
            Main.imprimirLinha();
        }
    }

    private static void criarWorkspace(){
        Scanner scanner = new Scanner(System.in);
        System.out.println( "Gerador de Workspaces.\nQual o nome do seu novo Workspace?" );
        WorkspaceEntity ws = workspaceService.criarWorkspace( new WorkspaceEntity( scanner.nextLine().toUpperCase() ) );
        System.out.println( "Workspace criado com sucesso!" +
                "\nVocê é até o momento o único integrante e ainda não há demandas." +
                "\nConecte-se ao seu novo Workspace para adicionar outros estudantes e procurar demandas." );
        exibirDadosCadastrais( ws );
    }

    private static void exibirDadosCadastrais( WorkspaceEntity ws ){
        System.out.println( "ID: " + ws.getId() );
        System.out.println( "Nome: " + ws.getNome() );
    }

    private static void acessarWorkspace(){
        Scanner scanner = new Scanner( System.in );
        System.out.println( "Informe o ID do Workspace a ser acessado." +
                "\nVocê pode visualizar os Workspaces dos quais faz parte através da opção 1 da tela anterior." );
        System.out.println( "ID:" );
        boolean sucesso = workspaceService.acessarWorkspace( scanner.nextInt() );
        if( sucesso ){
            System.out.println( "Conexão com o Workspace realizada com sucesso." );
            menuWorkspace();
        }else {
            System.out.println( "Workspace inválido. Tente novamente." );
        }
    }

    private static void menuWorkspace(){
        Scanner scanner = new Scanner( System.in );
        int seletor = 0;
        while ( seletor != 4 ){
            Main.imprimirLinha();
            System.out.println( "Conectado ao Workspace "
                    + workspaceService.buscarWorkspaceEmUso().getNome() );
            System.out.println( "1. Ver dados cadastrais.\n2. Demandas." +
                    "\n3. Estudantes.\n4. Desconectar do Workspace." );
            seletor = scanner.nextInt();
            if( seletor == 1 ) exibirDadosCadastrais( workspaceService.buscarWorkspaceEmUso() );
            if( seletor == 2 ) demandas();
            if( seletor == 3 ) estudantes();
            if( seletor == 4 ) workspaceService.sairWorkspace();
            if( seletor != 1 && seletor != 2 && seletor != 3 && seletor != 4 )
                System.out.println( "Opção inválida. Tente novamente." );
        }
    }

    private static void demandas(){
        Scanner scanner = new Scanner( System.in );
        int seletor = 0;
        while ( seletor != 5 ){
            Main.imprimirLinha();
            System.out.println( "1. Ver todas as demandas.\n2. Ver detalhes de uma demanda." +
                    "\n3. Adicionar demanda.\n4. Concluir demanda.\n5. Voltar." );
            seletor = scanner.nextInt();
            if( seletor == 1 ) exibirTodasDemandas();
            if( seletor == 2 ) exibirDetalhesDemanda();
            if( seletor == 3 ) adicionarDemanda();
            if( seletor == 4 ) concluirDemanda();
            if( seletor != 1 && seletor != 2 && seletor != 3 && seletor != 4 && seletor != 5 )
                System.out.println( "Opção inválida. Tente novamente." );
        }
    }
    private static void exibirTodasDemandas(){
        Main.imprimirLinha();
        System.out.println( "Demandas associadas ao Workspace:" );
        for( DemandaEntity demanda : workspaceService.buscarWorkspaceEmUso().getDemandas() ){
            imprimirUmaDemanda( demanda );
        }
    }

    private static void exibirDetalhesDemanda(){
        Scanner scanner = new Scanner( System.in );
        System.out.println( "Informe o ID da demanda para vê-la em detalhes." );
        DemandaEntity demanda = workspaceService.buscarDemandaWorkspace( scanner.nextInt() );
        if( demanda != null ){
            imprimirUmaDemanda( demanda );
        }else {
            System.out.println( "ID inválido. Tente novamente." );
        }
    }

    private static void adicionarDemanda(){
        Scanner scanner = new Scanner( System.in );
        Main.imprimirLinha();
        System.out.println( "Segue abaixo a lista de demandas disponíveis emitidas por empresas parceiras." +
                "\n Informe o ID da que desejar para adicioná-la ao Workspace." +
                "\n Para abortar, informe qualquer ID inválido." );
        for( DemandaEntity demanda : workspaceService.buscarDemandasDisponiveis() ){
            imprimirUmaDemanda( demanda );
        }
        System.out.println( "ID:" );
        boolean sucesso = workspaceService.adicionarDemanda( scanner.nextInt() );
        if( sucesso ){
            System.out.println( "Demanda adicionada ao Workspace com sucesso!" );
        }else {
            System.out.println( "ID inválido. Tente novamente." );
        }
    }

    private static void concluirDemanda(){
        Scanner scanner = new Scanner( System.in );
        Main.imprimirLinha();
        System.out.println( "Informe o ID da demanda que foi finalizada para alterar seu Status para concluído." );
        boolean sucesso = workspaceService.concluirDemanda( scanner.nextInt() );
        if( sucesso ){
            System.out.println( "Demanda concluída com sucesso!" );
        }else {
            System.out.println( "ID inválido. Tente novamente." );
        }
    }

    private static void imprimirUmaDemanda( DemandaEntity demanda ){
        System.out.println( "ID: " + demanda.getId() );
        System.out.println( "Título: " + demanda.getTitulo() );
        System.out.println( "Descrição: " + demanda.getDescricao() );
        System.out.println( "Prazo: " + demanda.getPrazo() );
        String status = demanda.getDisponivel() ? "DISPONÍVEL" : demanda.getConcluida() ? "CONCLUÍDA" : "EM DESENVOLVIMENTO";
        System.out.println( "Status: " + status );
        System.out.println( "Empresa Emissora: " + demanda.getDono().getNome() );
        System.out.println( "Contatos da empresa emissora:" );
        for( ContatoEntity contato : demanda.getDono().getContatos() ){
            System.out.println( "Tipo do contato: " + contato.getTipoContato() );
            System.out.println( "Contato: " + contato.getValor() );
            Main.imprimirLinha();
        }
        Main.imprimirLinha();
    }
    private static void estudantes(){
        Scanner scanner = new Scanner( System.in );
        int seletor = 0;
        while ( seletor != 3 ){
            Main.imprimirLinha();
            System.out.println( "1. Ver estudantes associados.\n2. Adicionar um estudante.\n3. Voltar." );
            seletor = scanner.nextInt();
            if( seletor == 1 ) exibirEstudantes();
            if( seletor == 2 ) adicionarEstudante();
            if( seletor != 1 && seletor != 2 && seletor != 3 )
                System.out.println( "Opção inválida. Tente novamente." );
        }
    }
    private static void exibirEstudantes(){
        Main.imprimirLinha();
        System.out.println( "Estudantes associados:" );
        for(EstudanteEntity estudante : workspaceService.verEstudantesWorkspace() ){
            System.out.println( "ID: " + estudante.getId() );
            System.out.println( "Username: " + estudante.getUsername() );
            System.out.println( "Nome: " + estudante.getNome() );
            System.out.println( "Instituição de ensino: " + estudante.getInstituicaoEnsino() );
            System.out.println( "Meios de contato:" );
            for( ContatoEntity contato : estudante.getContatos() ){
                System.out.println( contato.getTipoContato() + " / " + contato.getValor() );
            }
            Main.imprimirLinha();
        }
    }

    private static void adicionarEstudante(){
        Scanner scanner = new Scanner( System.in );
        Main.imprimirLinha();
        System.out.println( "Informe o Username do estudante a ser adicionado ao Workspace." );
        System.out.println( "USERNAME:" );
        boolean sucesso = workspaceService.adicionarEstudante( scanner.nextLine() );
        if( sucesso ){
            System.out.println( "Estudante associado ao Workspace com sucesso!" );
        }else {
            System.out.println( "Username inválido. Tente novamente." );
        }
    }
}

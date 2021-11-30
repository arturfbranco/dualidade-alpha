package br.com.poo.dualidade.View;

import br.com.poo.dualidade.Entity.ContatoEntity;
import br.com.poo.dualidade.Entity.EstudanteEntity;
import br.com.poo.dualidade.Main;
import br.com.poo.dualidade.Service.EstudanteService;

import java.util.Scanner;

public class EstudanteView {
    private static final EstudanteService estudanteService = new EstudanteService();

    public static void exibirEstudante( EstudanteEntity estudante ){
        System.out.println( "ID: " + estudante.getId() );
        System.out.println( "Username: " + estudante.getUsername() );
        System.out.println( "Nome completo: " + estudante.getNome() );
        System.out.println( "Instituição de ensino: " + estudante.getInstituicaoEnsino() );
        System.out.println( "Contatos:" );
        for( ContatoEntity contato : estudante.getContatos() ){
            System.out.println( contato.getTipoContato() );
            System.out.println( contato.getValor() );
            Main.imprimirLinha();
        }
    }

    public static void home(){
        Main.imprimirLinha();
        System.out.println( "Seja bem vindo à Tela Inicial do Estudante." );
        Scanner scanner = new Scanner( System.in );
        int seletor = 0;
        while ( seletor != 3 ){
            System.out.println( "1. Dados cadastrais.\n2. Meus Workspaces.\n3. Fazer Logout." );
            seletor = scanner.nextInt();
            if( seletor != 1 && seletor != 2 && seletor != 3 ) System.out.println( "Opção inválida. Tente novamente." );
            if( seletor == 1 ) dadosCadastrais();
            if( seletor == 2 ) WorkspaceView.workspacesInicial();
            if( seletor == 3 ) estudanteService.fazerLogout();
        }
    }
    private static void dadosCadastrais(){
        Main.imprimirLinha();
        Scanner scanner = new Scanner( System.in );
        int seletor = 0;
        while ( seletor != 3 ){
            Main.imprimirLinha();
            System.out.println( "Dados Cadastrais\nEscolha uma opção para continuar." +
                    "\n1. Ver meus dados.\n2. Atualizar meus dados.\n3. Voltar." );
            seletor = scanner.nextInt();
            if( seletor == 1 ) exibirEstudante( estudanteService.buscarDadosEstudante() );
            if( seletor == 2 ) GenericUsuarioView.atualizarUsuario( true );
            if( seletor != 1 && seletor != 2 && seletor != 3 ) System.out.println( "Opção inválida. Tente Novamente." );
        }
    }
}

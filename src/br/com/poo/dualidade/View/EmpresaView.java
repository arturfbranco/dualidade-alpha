package br.com.poo.dualidade.View;

import br.com.poo.dualidade.Entity.ContatoEntity;
import br.com.poo.dualidade.Entity.EmpresaEntity;
import br.com.poo.dualidade.Main;
import br.com.poo.dualidade.Service.EmpresaService;

import java.util.Scanner;

public class EmpresaView {
    private static final EmpresaService empresaService = new EmpresaService();

    public static void exibirEmpresa( EmpresaEntity empresa ){
        System.out.println( "ID: " + empresa.getId() );
        System.out.println( "Username: " + empresa.getUsername() );
        System.out.println( "Nome da empresa: " + empresa.getNome() );
        System.out.println( "CNPJ: " + empresa.getCnpj() );
        System.out.println( "Ramo de atuação: " + empresa.getRamoAtuacao() );
        System.out.println( "Contatos:" );
        for( ContatoEntity contato : empresa.getContatos() ){
            System.out.println( contato.getTipoContato() );
            System.out.println( contato.getValor() );
            Main.imprimirLinha();
        }
        Main.imprimirLinha();
    }

    public static void home(){
        Main.imprimirLinha();
        System.out.println( "Seja bem vindo à Tela Inicial para Empresas." );
        Scanner scanner = new Scanner( System.in );
        int seletor = 0;
        while ( seletor != 3 ){
            Main.imprimirLinha();
            System.out.println( "1. Dados cadastrais.\n2. Minhas demandas.\n3. Fazer Logout." );
            seletor = scanner.nextInt();
            if( seletor != 1 && seletor != 2 && seletor != 3 ) System.out.println( "Opção inválida. Tente novamente." );
            if( seletor == 1 ) dadosCadastrais();
            if( seletor == 2 ) DemandaView.demandaInicial();
            if( seletor == 3 ) empresaService.fazerLogout();
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
            if( seletor == 1 ) exibirEmpresa( empresaService.buscarDadosEmpresa() );
            if( seletor == 2 ) GenericUsuarioView.atualizarUsuario( false );
            if( seletor != 1 && seletor != 2 && seletor != 3 ) System.out.println( "Opção inválida. Tente Novamente." );
        }
    }
}

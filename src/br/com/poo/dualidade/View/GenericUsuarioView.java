package br.com.poo.dualidade.View;

import br.com.poo.dualidade.Entity.*;
import br.com.poo.dualidade.Main;
import br.com.poo.dualidade.Service.EmpresaService;
import br.com.poo.dualidade.Service.EstudanteService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GenericUsuarioView {
    private static EstudanteService estudanteService = new EstudanteService();
    private static EmpresaService empresaService = new EmpresaService();

    public static void comecar( boolean isEstudante ){
        Main.imprimirLinha();
        Scanner scanner = new Scanner( System.in );
        int seletor = 0;
        while ( seletor != 3 ){
            System.out.println( "Já possui uma conta?\n1. Fazer login.\n2. Cadastrar uma conta.\n3. Voltar à Tela Inicial." );
            seletor = scanner.nextInt();
            if( seletor == 1 ) fazerLogin( isEstudante );
            if( seletor == 2 ) fazerCadastro( isEstudante );
            if( seletor != 1 && seletor != 2 && seletor != 3 ) System.out.println( "Opção inválida. Tente novamente." );
        }
    }

    private static void fazerCadastro( boolean isEstudante ){
        UsuarioEntity usuario = solicitarDados();
        if( usuario.getContatos().isEmpty() ){
            System.out.println( "Ops! Parece que você não adicionou nenhum contato. Tente novamente." );
            return;
        }
        if( isEstudante ){
            EstudanteEntity estudante = solicitarDados( new EstudanteEntity( usuario ) );
            EstudanteEntity estudanteSalvo = estudanteService.cadastrarEstudante( estudante );
            EstudanteView.exibirEstudante( estudanteSalvo );
        }else {
            EmpresaEntity empresa = solicitarDados( new EmpresaEntity( usuario ) );
            EmpresaEntity empresaSalva = empresaService.cadastrarEmpresa( empresa );
            EmpresaView.exibirEmpresa( empresaSalva );
        }
        System.out.println( "Cadastro finalizado com sucesso." );
    }

    public static void atualizarUsuario( boolean isEstudante ){
        System.out.println( "Atualização de cadastro.\nPreencha novamente todos os dados seus dados. Caso deseje abortar a operação, escolha a opção finalizar sem adicionar nenhum contato." );
        UsuarioEntity usuario = solicitarDados();
        if( usuario.getContatos().isEmpty() ) return;
        if( isEstudante ){
            EstudanteEntity estudante = solicitarDados( new EstudanteEntity( usuario ) );
            estudanteService.atualizarEstudante( estudante, estudanteService.buscarDadosEstudante().getId() );
        }else {
            EmpresaEntity empresa = solicitarDados( new EmpresaEntity( usuario ) );
            empresaService.atualizarEmpresa( empresa, empresaService.buscarDadosEmpresa().getId() );
        }
        System.out.println( "Dados cadastrais atualizados com sucesso." );
    }

    private static EstudanteEntity solicitarDados( EstudanteEntity estudante ){
        Scanner scanner = new Scanner( System.in );
        System.out.println( "Informa a sua instituição de ensino:" );
        estudante.setInstituicaoEnsino( scanner.nextLine().toUpperCase() );
        return estudante;
    }
    private static EmpresaEntity solicitarDados( EmpresaEntity empresa ){
        Scanner scanner = new Scanner( System.in );
        System.out.println( "CNPJ:" );
        empresa.setCnpj( scanner.nextLine() );
        System.out.println( "Ramo de atuação:" );
        empresa.setRamoAtuacao( scanner.nextLine().toUpperCase() );
        return empresa;
    }

    private static UsuarioEntity solicitarDados(){
        Main.imprimirLinha();
        Scanner scanner = new Scanner( System.in );
        UsuarioEntity usuario = new UsuarioEntity();
        System.out.println( "Precisaremos agora de alguns dados seus." );
        System.out.println( "Username para acesso à plataforma:" );
        usuario.setUsername( scanner.nextLine() );
        System.out.println( "Senha:" );
        usuario.setSenha( scanner.nextLine() );
        System.out.println( "Nome:" );
        usuario.setNome( scanner.nextLine().toUpperCase() );
        usuario.setContatos( solicitarContatos() );
        return usuario;
    }

    private static List<ContatoEntity> solicitarContatos(){
        List<ContatoEntity> contatos = new ArrayList<>();
        Scanner scanner = new Scanner( System.in );
        int seletor = 0;
        System.out.println( "Adição de contatos. É necessário que seja fornecido ao menos um meio de contato." );
        while ( seletor != 2 ){
            int seletorTipoContato = 0;
            Main.imprimirLinha();
            System.out.println( "1. Adicionar Contato." );
            System.out.println( "2. Finalizar." );
            seletor = scanner.nextInt();
            if( seletor != 1 && seletor != 2 ) System.out.println( "Opção inválida. Tente novamente." );
            if( seletor == 1 ){
                TipoContatoEnum tipoContato = null;
                System.out.println( "Qual o tipo de contato que deseja adicionar?" );
                System.out.println( "1. Telefone\n2. WhatsApp\n3. E-mail" );
                seletorTipoContato = scanner.nextInt();
                if( seletorTipoContato == 1 ) tipoContato = TipoContatoEnum.TELEFONE;
                if( seletorTipoContato == 2 ) tipoContato = TipoContatoEnum.WHATSAPP;
                if( seletorTipoContato == 3 ) tipoContato = TipoContatoEnum.EMAIL;
                System.out.println( "Informe o contato:" );
                scanner.nextLine();
                String valor = scanner.nextLine();
                if ( tipoContato != null ){
                    contatos.add( new ContatoEntity( tipoContato, valor ) );
                }
            }
        }
        return contatos;
    }

    private static void fazerLogin( boolean isEstudante ){
        Main.imprimirLinha();
        Scanner scanner = new Scanner( System.in );
        System.out.println( "Username:" );
        String username = scanner.nextLine();
        System.out.println( "Senha:" );
        String senha = scanner.nextLine();
        boolean sucesso = isEstudante ? estudanteService.fazerLogin( username, senha )
                : empresaService.fazerLogin( username, senha );
        if ( sucesso ){
            System.out.println( "Autenticação performada com sucesso." );
            Main.imprimirLinha();
            if( isEstudante ){
                EstudanteView.home();
            }else {
                EmpresaView.home();
            }
        }else {
            System.out.println( "Falha na autenticação. Redirecionando-o à tela inicial." );
            Main.imprimirLinha();
        }
    }
}

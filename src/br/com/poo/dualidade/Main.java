package br.com.poo.dualidade;

import br.com.poo.dualidade.View.EmpresaView;
import br.com.poo.dualidade.View.EstudanteView;
import br.com.poo.dualidade.View.GenericUsuarioView;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        imprimirLinha();
        Scanner scanner = new Scanner( System.in );
        int seletor = 0;
        while ( seletor != 3 ){
            System.out.println( "INICIATIVA DUALIDADE" +
                    "\nSelecione uma das opções para continuar." +
                    "\n1. Sou um estudante.\n2. Sou uma empresa.\n3. Sair." );
            seletor = scanner.nextInt();
            if( seletor == 1 ) GenericUsuarioView.comecar( true );
            if( seletor == 2 ) GenericUsuarioView.comecar( false );
            if( seletor != 1 && seletor != 2 && seletor != 3 ) System.out.println( "Opção inválida. Tente novamente." );
        }
    }

    public static void imprimirLinha(){
        System.out.println( "-------------------------------------------" );
    }

}

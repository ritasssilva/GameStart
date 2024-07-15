import java.io.FileNotFoundException;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        // Imprimir logo e mensagem de boas-vindas
        Metodos.imprimirFicheiro("files/GameStart_Logo.txt");
        System.out.println("\n Bem-vind@ à nossa loja! Esperemos que goste da experiência e nos visite mais vezes!");

        // Chamar o MenuPrincipal para o utilizador escolher as opções
        MenuPrincipal.main(args);

        // Chamar o método de copyright ao final do programa
        Metodos.copyright();
    }
}
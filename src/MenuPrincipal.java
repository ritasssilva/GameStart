import java.io.FileNotFoundException;
import java.util.Scanner;

public class MenuPrincipal {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner input = new Scanner(System.in);
        int opcaoMenu;

        do {
            System.out.println("\n\n***** Menu Principal *****");
            System.out.println("1. Menu Cliente");
            System.out.println("2. Menu Admin");
            System.out.println("3. Sair");
            System.out.print("\nEscolha a opção: ");
            opcaoMenu = input.nextInt();
            input.nextLine();

            switch (opcaoMenu) {
                case 1:
                    MenuCliente.menuCliente(); // aceder menuCliente
                    break;

                case 2:
                    if (verificarCredenciais(input)) {
                        MenuAdmin.menuAdmin();
                    } else {
                        System.out.println("Utilizador não encontrado!");
                    }
                    break;

                case 3:
                    System.out.println("Sair");
                    break;

                default:
                    System.out.println("Opção Inválida!");
                    break;
            }
        } while (opcaoMenu != 3);
        input.close();
    }

    private static boolean verificarCredenciais(Scanner input) throws FileNotFoundException {
        System.out.print("Introduza o username: ");
        String username = input.nextLine();
        System.out.print("Introduza a password: ");
        String password = input.nextLine();

        return Metodos.acessosValidos(username, password);
    }
}

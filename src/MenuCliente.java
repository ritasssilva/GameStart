import java.io.FileNotFoundException;
import java.util.Scanner;

public class MenuCliente {

    /**
     * Método principal para o menu Cliente
     *
     * @throws FileNotFoundException se algum ficheiro não for encontrado
     */
    public static void menuCliente() throws FileNotFoundException {
        Scanner input = new Scanner(System.in);
        int opcaoMenu;

        do {
            System.out.println("\n\n***** Menu Cliente *****");
            System.out.println("1. Novo Registo");
            System.out.println("2. Procurar Estacionamento");
            System.out.println("3. Imprimir Catálogo");
            System.out.println("4. Imprimir Catálogos Gráficos");
            System.out.println("5. Imprimir Catálogo Editora");
            System.out.println("6. Imprimir Catálogo Categoria");
            System.out.println("7. Imprimir Jogo Mais Recente");
            System.out.println("8. Sair");
            System.out.print("\nEscolha a opção: ");
            opcaoMenu = input.nextInt();
            input.nextLine();

            switch (opcaoMenu) {
                case 1: // Novo Registo de cliente (apenas na consola)
                    novoRegisto(input);
                    Metodos.pressioneEnterParaContinuar(input);
                    break;
                case 2: // Procurar Estacionamento: lugares disponíveis são todos os números Triangulares Múltiplos de 5 até 121
                    imprimirTriangularesMultiplosDe5();
                    Metodos.pressioneEnterParaContinuar(input);
                    break;
                case 3: // Imprimir Catálogo
                    catalogoDeJogos();
                    Metodos.pressioneEnterParaContinuar(input);
                    break;
                case 4: // Imprimir Catálogos Gráficos
                    imprimirCatalogosGraficos(input);
                    Metodos.pressioneEnterParaContinuar(input);
                    break;
                case 5: // Imprimir Catálogo Editora
                    imprimirCatalogoEditora(input);
                    Metodos.pressioneEnterParaContinuar(input);
                    break;
                case 6: // Imprimir Catálogo Categoria
                    imprimirCatalogoCategoria(input);
                    Metodos.pressioneEnterParaContinuar(input);
                    break;
                case 7: // Imprimir Jogo Mais Recente
                    imprimirJogoMaisRecente();
                    Metodos.pressioneEnterParaContinuar(input);
                    break;
                case 8: // Sair
                    System.out.println("Sair");
                    break;
                default:
                    System.out.println("Opção Inválida!");
                    break;
            }
        } while (opcaoMenu != 8);
    }

    /**
     * Método que faz o registo de um novo cliente na consola
     *
     * @param input Scanner para ler a entrada do utilizador
     */
    private static void novoRegisto(Scanner input) {
        System.out.println("\n*** Inserir Cliente ***\n");
        System.out.print("Insira o Nome: ");
        String nomeCliente = input.nextLine();

        System.out.print("Insira Contacto: ");
        int contactoCliente = input.nextInt();
        input.nextLine();

        System.out.print("Insira Email: ");
        String emailCliente = input.nextLine();

        System.out.println("\nCliente Inserido com sucesso!\n" + nomeCliente + " | " + contactoCliente + " | " + emailCliente);
    }

    /**
     * Método que imprime os lugares de estacionamento disponíveis (correspondentes aos números triangulares múltiplos de 5 até 121)
     */
    private static void imprimirTriangularesMultiplosDe5() {
        System.out.println("\nOs lugares de estacionamento disponíveis são: ");

        // Itera sobre os números de 1 até 121
        for (int n = 1; n <= 121; n++) {
            // Verifica se o número é triangular e múltiplo de 5
            if (Metodos.triangular(n) && n % 5 == 0) {
                System.out.println(n);
            }
        }
        System.out.println("Estes números correspondem aos números triangulares múltiplos de 5 até 121.");
    }

    /**
     * Método que imprime o catálogo de jogos sem duplicados
     * @throws FileNotFoundException se o ficheiro não for encontrado
     */
    private static void catalogoDeJogos() throws FileNotFoundException {
        System.out.println("\n*** Catálogo de Jogos ***");
        String[][] matrizVendas = Metodos.lerCsvParaMatriz("files/GameStart_Vendas.csv");

        // Remover duplicados nas vendas
        String[][] jogosSemDuplicados = Metodos.obterPrimeiraReferencia(matrizVendas, 4);

        for (int i = 0; i < jogosSemDuplicados.length; i++) {
            System.out.println("- " + jogosSemDuplicados[i][4]);
        }
    }

    /**
     * Método que imprime os catálogos gráficos de jogos
     * @param input Scanner para ler a entrada do utilizador
     * @throws FileNotFoundException se o ficheiro não for encontrado
     */
    private static void imprimirCatalogosGraficos(Scanner input) throws FileNotFoundException {
        int graficoJogo;
        do {
            System.out.println("\n***** Catálogo Gráfico de Jogos *****");
            System.out.println("Indique o jogo que deseja pesquisar: ");
            System.out.println("1. Call of Duty");
            System.out.println("2. Fifa");
            System.out.println("3. Hollow Knight");
            System.out.println("4. Mortal Kombat");
            System.out.println("5. Overcooked");
            System.out.println("6. Witcher 3: Wild Hunt");
            System.out.println("7. Minecraft");
            System.out.println("8. Sair do Catálogo Gráfico de Jogos");
            graficoJogo = input.nextInt();
            input.nextLine();

            switch (graficoJogo) {
                case 1:
                    Metodos.imprimirFicheiro("files/CatalogoGrafico/callOfDuty.txt");
                    break;
                case 2:
                    Metodos.imprimirFicheiro("files/CatalogoGrafico/fifa.txt");
                    break;
                case 3:
                    Metodos.imprimirFicheiro("files/CatalogoGrafico/hollowKnight.txt");
                    break;
                case 4:
                    Metodos.imprimirFicheiro("files/CatalogoGrafico/mortalKombat.txt");
                    break;
                case 5:
                    Metodos.imprimirFicheiro("files/CatalogoGrafico/overcooked.txt");
                    break;
                case 6:
                    Metodos.imprimirFicheiro("files/CatalogoGrafico/witcher3.txt");
                    break;
                case 7:
                    Metodos.imprimirFicheiro("files/CatalogoGrafico/minecraft.txt");
                    break;
                case 8:
                    System.out.println("A sair do Catálogo Gráfico de Jogos...");
                    break;
                default:
                    System.out.println("Opção Inválida!");
                    break;
            }
        } while (graficoJogo != 8);
    }

    /**
     * Método que imprime o catálogo de jogos de uma editora específica
     * @param input Scanner para ler a entrada do utilizador
     * @throws FileNotFoundException se o ficheiro não for encontrado
     */
    private static void imprimirCatalogoEditora(Scanner input) throws FileNotFoundException {
        System.out.print("Insira o nome da Editora: ");
        String editora = input.nextLine();
        String[][] vendas = Metodos.lerCsvParaMatriz("files/GameStart_Vendas.csv");

        // Remover duplicados nas vendas
        vendas = Metodos.obterPrimeiraReferencia(vendas, 4);

        String[] categorias = new String[vendas.length];
        String[][] jogosPorCategoria = new String[vendas.length][vendas.length];
        int[] countJogosPorCategoria = new int[vendas.length];
        int numCategorias = 0;

        // Itera sobre as vendas para encontrar jogos por categoria
        for (int i = 0; i < vendas.length; i++) {
            if (vendas[i][2].equalsIgnoreCase(editora)) {
                String categoria = vendas[i][3];
                String jogo = vendas[i][4];
                int indexCategoria = -1;
                for (int j = 0; j < numCategorias; j++) {
                    if (categorias[j].equals(categoria)) {
                        indexCategoria = j;
                    }
                }
                if (indexCategoria == -1) {
                    categorias[numCategorias] = categoria;
                    indexCategoria = numCategorias;
                    numCategorias++;
                }
                boolean jogoExiste = false;
                for (int k = 0; k < countJogosPorCategoria[indexCategoria]; k++) {
                    if (jogosPorCategoria[indexCategoria][k].equals(jogo)) {
                        jogoExiste = true;
                    }
                }
                if (!jogoExiste) {
                    jogosPorCategoria[indexCategoria][countJogosPorCategoria[indexCategoria]] = jogo;
                    countJogosPorCategoria[indexCategoria]++;
                }
            }
        }

        // Imprimir resultados de uma forma "bonita" na consola
        System.out.println("\n*** " + editora + " ***");
        for (int i = 0; i < numCategorias; i++) {
            System.out.println("\n__" + categorias[i] + "__");
            for (int j = 0; j < countJogosPorCategoria[i]; j++) {
                System.out.println(jogosPorCategoria[i][j]);
            }
        }
    }

    /**
     * Método que imprime o catálogo de jogos de uma categoria específica
     * @param input Scanner para ler a entrada do utilizador
     * @throws FileNotFoundException se o ficheiro não for encontrado
     */
    private static void imprimirCatalogoCategoria(Scanner input) throws FileNotFoundException {
        System.out.print("Insira o nome da Categoria: ");
        String categoria = input.nextLine();
        String[][] vendas = Metodos.lerCsvParaMatriz("files/GameStart_Vendas.csv");

        // Remover duplicados nas vendas
        vendas = Metodos.obterPrimeiraReferencia(vendas, 4);

        String[] editoras = new String[vendas.length];
        String[][] jogosPorEditora = new String[vendas.length][vendas.length];
        int[] countJogosPorEditora = new int[vendas.length];
        int numEditoras = 0;

        // Itera sobre as vendas para encontrar jogos por editora
        for (int i = 0; i < vendas.length; i++) {
            if (vendas[i][3].equalsIgnoreCase(categoria)) {
                String editora = vendas[i][2];
                String jogo = vendas[i][4];
                int indexEditora = -1;
                for (int j = 0; j < numEditoras; j++) {
                    if (editoras[j].equals(editora)) {
                        indexEditora = j;
                    }
                }
                if (indexEditora == -1) {
                    editoras[numEditoras] = editora;
                    indexEditora = numEditoras;
                    numEditoras++;
                }
                boolean jogoExiste = false;
                for (int k = 0; k < countJogosPorEditora[indexEditora]; k++) {
                    if (jogosPorEditora[indexEditora][k].equals(jogo)) {
                        jogoExiste = true;
                    }
                }
                if (!jogoExiste) {
                    jogosPorEditora[indexEditora][countJogosPorEditora[indexEditora]] = jogo;
                    countJogosPorEditora[indexEditora]++;
                }
            }
        }

        // Imprimir resultados de forma "bonita" na consola
        System.out.println("\n*** " + categoria + " ***");
        for (int i = 0; i < numEditoras; i++) {
            System.out.println("\n__" + editoras[i] + "__");
            for (int j = 0; j < countJogosPorEditora[i]; j++) {
                System.out.println(jogosPorEditora[i][j]);
            }
        }
    }

    /**
     * Método que encontra e imprime o jogo mais recente (o que apareceu há menos vendas)
     * @throws FileNotFoundException se o ficheiro não for encontrado
     */
    private static void imprimirJogoMaisRecente() throws FileNotFoundException {
        String[][] vendas = Metodos.lerCsvParaMatriz("files/GameStart_Vendas.csv");

        // Variáveis para armazenar o jogo mais recente e a posição da venda mais recente
        String jogoMaisRecente = "";
        int vendaMaisRecente = vendas.length; // Começamos com o valor máximo de vendas

        // Processa todas as vendas para encontrar o jogo mais recente
        for (int i = 0; i < vendas.length; i++) {
            String jogoAtual = vendas[i][4];

            // Verifica se o jogo atual apareceu em uma venda anterior
            boolean jogoExistente = false;
            for (int j = 0; j < i; j++) {
                if (vendas[j][4].equals(jogoAtual)) {
                    jogoExistente = true;
                }
            }

            // Se o jogo atual não apareceu antes e a venda é mais recente, atualiza o jogo mais recente
            if (!jogoExistente && i < vendaMaisRecente) {
                jogoMaisRecente = jogoAtual;
                vendaMaisRecente = i;
            }
        }

        // Imprime o jogo mais recente
        System.out.println("\n*** Jogo Mais Recente ***");
        if (!jogoMaisRecente.isEmpty()) {
            System.out.println("O jogo mais recente é: " + jogoMaisRecente);
        } else {
            System.out.println("Nenhum jogo encontrado.");
        }
    }
}

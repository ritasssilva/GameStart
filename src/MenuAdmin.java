import java.io.FileNotFoundException;
import java.util.Scanner;

public class MenuAdmin {

    /**
     * Método principal para o menu Admin
     *
     * @throws FileNotFoundException se algum ficheiro não for encontrado
     */
    public static void menuAdmin() throws FileNotFoundException {
        Scanner input = new Scanner(System.in);
        int opcaoMenu;

        do {
            System.out.println("\n\n***** Menu Admin *****");
            System.out.println("1. Consulta de Ficheiros");
            System.out.println("2. Total de Vendas");
            System.out.println("3. Total de Lucro");
            System.out.println("4. Pesquisa de Cliente");
            System.out.println("5. Jogo mais Caro");
            System.out.println("6. Melhores Clientes");
            System.out.println("7. Melhor Categoria");
            System.out.println("8. Pesquisa Vendas");
            System.out.println("9. Top 5 Jogos");
            System.out.println("10. Bottom 5 Jogos");
            System.out.println("11. Sair");
            System.out.print("\nEscolha a opção: ");
            opcaoMenu = input.nextInt();
            input.nextLine();

            switch (opcaoMenu) {
                case 1: // Consulta de Ficheiros
                    consultaDeFicheiros(input);
                    break;
                case 2: // Total de Vendas
                    totalDeVendas();
                    Metodos.pressioneEnterParaContinuar(input);
                    break;
                case 3: // Total de Lucro
                    totalDeLucro();
                    Metodos.pressioneEnterParaContinuar(input);
                    break;
                case 4: // Pesquisa de Cliente
                    pesquisaDeCliente(input);
                    Metodos.pressioneEnterParaContinuar(input);
                    break;
                case 5: // Jogo Mais Caro
                    jogoMaisCaro();
                    Metodos.pressioneEnterParaContinuar(input);
                    break;
                case 6: // Melhores Clientes
                    melhoresClientes();
                    Metodos.pressioneEnterParaContinuar(input);
                    break;
                case 7: // Melhor Categoria
                    melhorCategoria();
                    Metodos.pressioneEnterParaContinuar(input);
                    break;
                case 8: // Pesquisa de Vendas
                    pesquisaVendas(input);
                    Metodos.pressioneEnterParaContinuar(input);
                    break;
                case 9: // Top 5 Jogos
                    top5JogosMaisLucro();
                    Metodos.pressioneEnterParaContinuar(input);
                    break;
                case 10: // Bottom 5 Jogos
                    bottom5JogosMenosLucro();
                    Metodos.pressioneEnterParaContinuar(input);
                    break;
                case 11:
                    System.out.println("Sair");
                    break;
                default:
                    System.out.println("Opção Inválida!");
                    break;
            }
        } while (opcaoMenu != 11);
    }

    /**
     * Método que consulta e imprime o conteúdo dos ficheiros
     *
     * @param input Scanner para ler a entrada do utilizador
     * @throws FileNotFoundException se algum ficheiro não for encontrado
     */
    private static void consultaDeFicheiros(Scanner input) throws FileNotFoundException {
        int ficheiro;
        do {
            System.out.println("\n***** Consulta de Ficheiros *****");
            System.out.println("1. Vendas");
            System.out.println("2. Clientes");
            System.out.println("3. Categorias");
            System.out.println("4. Sair");
            ficheiro = input.nextInt();
            input.nextLine();

            String[][] matriz;
            switch (ficheiro) {
                case 1:
                    System.out.println("*** Ficheiro Vendas ***\n");
                    matriz = Metodos.lerCsvParaMatriz("files/GameStart_Vendas.csv");
                    break;
                case 2:
                    System.out.println("*** Ficheiro Clientes ***\n");
                    matriz = Metodos.lerCsvParaMatriz("files/GameStart_Clientes.csv");
                    break;
                case 3:
                    System.out.println("*** Ficheiro Categorias ***\n");
                    matriz = Metodos.lerCsvParaMatriz("files/GameStart_Categorias.csv");
                    break;
                case 4:
                    System.out.println("A sair da consulta de ficheiros...");
                    return;
                default:
                    System.out.println("Opção Inválida!");
                    continue;
            }
            Metodos.imprimirMatrizFormatada(matriz);

        } while (ficheiro != 4);
    }

    /**
     * Método que calcula e imprime o total de vendas
     *
     * @throws FileNotFoundException se o ficheiro não for encontrado
     */
    private static void totalDeVendas() throws FileNotFoundException {
        System.out.println("\n*** Informação de Vendas ***");
        String[][] vendas = Metodos.lerCsvParaMatriz("files/GameStart_Vendas.csv");

        // Calcula o total de vendas somando os valores da coluna 5 (índice 4) da matriz e imprime resultado na consola
        int totalVendas = vendas.length;
        System.out.println("Total de Vendas: " + totalVendas);

        double valorTotalVendas = Metodos.somatorioColuna(vendas, 5);
        System.out.println("Valor do Total de Vendas: €" + valorTotalVendas);
    }

    /**
     * Método que calcula e imprime o total de lucro
     *
     * @throws FileNotFoundException se o ficheiro não for encontrado
     */
    private static void totalDeLucro() throws FileNotFoundException {
        System.out.println("\n*** Lucro ***");
        String[][] vendas = Metodos.lerCsvParaMatriz("files/GameStart_Vendas.csv");
        String[][] categorias = Metodos.lerCsvParaMatriz("files/GameStart_Categorias.csv");

        // Remover duplicados nas categorias
        categorias = Metodos.obterPrimeiraReferencia(categorias, 0);

        double totalLucro = 0;

        // Itera sobre todas as vendas
        for (int i = 0; i < vendas.length; i++) {
            String categoriaVenda = vendas[i][3];
            double valorVenda = Double.parseDouble(vendas[i][5]);
            double percentagem = 0.0;

            // Encontrar a percentagem de lucro para a categoria da venda
            for (int j = 0; j < categorias.length; j++) {
                if (categorias[j][0].equalsIgnoreCase(categoriaVenda)) {
                    percentagem = Double.parseDouble(categorias[j][1]);
                }
            }

            // Calcular lucro da venda e adicionar ao total
            double lucro = valorVenda * (percentagem / 100);
            totalLucro += lucro;
        }
        System.out.println("Total de Lucro: €" + totalLucro);
    }

    /**
     * Método que pesquisa e imprime informações de um cliente pelo seu ID
     *
     * @param input Scanner para ler a entrada do utilizador
     * @throws FileNotFoundException se o ficheiro não for encontrado
     */
    private static void pesquisaDeCliente(Scanner input) throws FileNotFoundException {
        String[][] clientes = Metodos.lerCsvParaMatriz("files/GameStart_Clientes.csv");

        // Remover duplicados nos clientes
        clientes = Metodos.obterPrimeiraReferencia(clientes, 0);

        System.out.print("Indique o ID do cliente que pretende pesquisar: ");
        int idCliente = input.nextInt();
        input.nextLine();
        boolean encontrado = false;

        // Itera sobre todos os clientes
        for (int i = 0; i < clientes.length; i++) {
            if (Integer.parseInt(clientes[i][0]) == idCliente) {
                System.out.println("\n*** Informação do Cliente ***");
                System.out.println("Nome: " + clientes[i][1] + " | Contacto: " + clientes[i][2] + " | Email: " + clientes[i][3]);
                encontrado = true;
            }
        }
        if (!encontrado) {
            System.out.println("Cliente não encontrado.");
        }
    }

    /**
     * Método que encontra e imprime o jogo mais caro e os clientes que o compraram
     *
     * @throws FileNotFoundException se o ficheiro não for encontrado
     */
    private static void jogoMaisCaro() throws FileNotFoundException {
        System.out.println("\n*** Jogo mais caro ***");
        String[][] vendas = Metodos.lerCsvParaMatriz("files/GameStart_Vendas.csv");
        String[][] clientes = Metodos.lerCsvParaMatriz("files/GameStart_Clientes.csv");

        // Remover duplicados nos clientes
        clientes = Metodos.obterPrimeiraReferencia(clientes, 0);

        double maiorValor = 0;
        String nomeJogoMaisCaro = "";

        // Itera sobre todas as vendas
        for (int i = 0; i < vendas.length; i++) {
            double valorVenda = Double.parseDouble(vendas[i][5]);
            if (valorVenda > maiorValor) {
                maiorValor = valorVenda;
                nomeJogoMaisCaro = vendas[i][4];
            }
        }
        System.out.println(nomeJogoMaisCaro + " | Valor: " + maiorValor);
        System.out.println("\nNome(s) do(s) Cliente(s): ");

        // Encontrar clientes que compraram o jogo mais caro
        for (int i = 0; i < vendas.length; i++) {
            double valorVenda = Double.parseDouble(vendas[i][5]);
            if (valorVenda == maiorValor) {
                int idCliente = Integer.parseInt(vendas[i][1]);
                for (int j = 0; j < clientes.length; j++) {
                    if (Integer.parseInt(clientes[j][0]) == idCliente) {
                        System.out.println("- " + clientes[j][1]);
                    }
                }
            }
        }
    }

    /**
     * Método que encontra e imprime os melhores clientes (os que mais gastaram)
     *
     * @throws FileNotFoundException se o ficheiro não for encontrado
     */
    private static void melhoresClientes() throws FileNotFoundException {
        System.out.println("\n*** Melhor(es) Cliente(s) ***");
        String[][] vendas = Metodos.lerCsvParaMatriz("files/GameStart_Vendas.csv");
        String[][] clientes = Metodos.lerCsvParaMatriz("files/GameStart_Clientes.csv");

        // Remover duplicados nos clientes
        clientes = Metodos.obterPrimeiraReferencia(clientes, 0);

        String[][] infoClientes = Metodos.calcularComprasClientes(vendas);
        int indiceMelhorCliente = 0;
        double maiorGasto = 0;

        // Encontrar o cliente que mais gastou
        for (int i = 0; i < infoClientes.length; i++) {
            double totalGasto = Double.parseDouble(infoClientes[i][1]);
            if (totalGasto > maiorGasto) {
                maiorGasto = totalGasto;
                indiceMelhorCliente = i;
            }
        }

        int idCliente = Integer.parseInt(infoClientes[indiceMelhorCliente][0]);
        double totalGasto = Double.parseDouble(infoClientes[indiceMelhorCliente][1]);
        String nomeCliente = "";
        String contatoCliente = "";
        String emailCliente = "";

        // Encontrar os detalhes do cliente
        for (int i = 0; i < clientes.length; i++) {
            if (Integer.parseInt(clientes[i][0]) == idCliente) {
                nomeCliente = clientes[i][1];
                contatoCliente = clientes[i][2];
                emailCliente = clientes[i][3];
            }
        }

        System.out.println("ID Cliente: " + idCliente);
        System.out.println("Nome: " + nomeCliente);
        System.out.println("Contato: " + contatoCliente);
        System.out.println("Email: " + emailCliente);
        System.out.println("Total Gasto: €" + totalGasto);
        System.out.print("Jogos Comprados: ");
        boolean primeiro = true;

        String[] jogos = infoClientes[indiceMelhorCliente][2].split(", ");
        for (int i = 0; i < jogos.length; i++) {
            if (!jogos[i].isEmpty()) {
                if (primeiro) {
                    System.out.print(jogos[i]);
                    primeiro = false;
                } else {
                    System.out.print(", " + jogos[i]);
                }
            }
        }
        System.out.println();
    }


    /**
     * Método que encontra e imprime a categoria que gerou mais lucro
     *
     * @throws FileNotFoundException se o ficheiro não for encontrado
     */
    private static void melhorCategoria() throws FileNotFoundException {
        System.out.println("\n*** Melhor Categoria ***");
        String[][] vendas = Metodos.lerCsvParaMatriz("files/GameStart_Vendas.csv");
        String[][] categorias = Metodos.lerCsvParaMatriz("files/GameStart_Categorias.csv");

        // Remover duplicados nas categorias
        categorias = Metodos.obterPrimeiraReferencia(categorias, 0);

        String[] nomesCategorias = new String[categorias.length];
        double[] lucrosCategorias = new double[categorias.length];

        for (int i = 0; i < categorias.length; i++) {
            nomesCategorias[i] = categorias[i][0];
            lucrosCategorias[i] = 0.0;
        }

        // Calcular lucros por categoria
        for (int i = 0; i < vendas.length; i++) {
            String categoriaVenda = vendas[i][3];
            double valorVenda = Double.parseDouble(vendas[i][5]);
            double percentagem = 0.0;

            for (int j = 0; j < categorias.length; j++) {
                if (categorias[j][0].equalsIgnoreCase(categoriaVenda)) {
                    percentagem = Double.parseDouble(categorias[j][1]);
                }
            }

            double lucro = valorVenda * (percentagem / 100);

            for (int k = 0; k < nomesCategorias.length; k++) {
                if (nomesCategorias[k].equalsIgnoreCase(categoriaVenda)) {
                    lucrosCategorias[k] += lucro;
                }
            }
        }

        double maiorLucro = 0.0;
        String melhorCategoria = "";

        // Encontrar a categoria com maior lucro
        for (int i = 0; i < nomesCategorias.length; i++) {
            if (lucrosCategorias[i] > maiorLucro) {
                maiorLucro = lucrosCategorias[i];
                melhorCategoria = nomesCategorias[i];
            }
        }
        System.out.println("\nMelhor Categoria: " + melhorCategoria);
        System.out.println("Lucro: €" + maiorLucro);
    }

    /**
     * Método que pesquisa e imprime as vendas de um jogo específico
     *
     * @param input Scanner para ler a entrada do utilizador
     * @throws FileNotFoundException se o ficheiro não for encontrado
     */
    private static void pesquisaVendas(Scanner input) throws FileNotFoundException {
        System.out.println("\n*** Pesquisa de Vendas ***");
        System.out.print("Insira o nome do jogo que pretende procurar: ");
        String nomeJogo = input.nextLine();
        String[][] vendas = Metodos.lerCsvParaMatriz("files/GameStart_Vendas.csv");
        String[][] clientes = Metodos.lerCsvParaMatriz("files/GameStart_Clientes.csv");

        // Remover duplicados nos clientes
        clientes = Metodos.obterPrimeiraReferencia(clientes, 0);

        boolean jogoEncontrado = false;

        // Iterar sobre todas as vendas
        for (int i = 0; i < vendas.length; i++) {
            if (vendas[i][4].equalsIgnoreCase(nomeJogo)) {
                int idCliente = Integer.parseInt(vendas[i][1]);
                for (int j = 0; j < clientes.length; j++) {
                    if (Integer.parseInt(clientes[j][0]) == idCliente) {
                        System.out.println("ID Cliente: " + clientes[j][0]);
                        System.out.println("Nome: " + clientes[j][1]);
                        System.out.println("Contato: " + clientes[j][2]);
                        System.out.println("Email: " + clientes[j][3]);
                        System.out.println("------------------------");
                        jogoEncontrado = true;
                    }
                }
            }
        }
        if (!jogoEncontrado) {
            System.out.println("Jogo não encontrado na lista de vendas.");
        }
    }

    /**
     * Método que encontra e imprime os 5 jogos que mais lucro geraram
     *
     * @throws FileNotFoundException se o ficheiro não for encontrado
     */
    private static void top5JogosMaisLucro() throws FileNotFoundException {
        String[][] vendas = Metodos.lerCsvParaMatriz("files/GameStart_Vendas.csv");
        String[][] categorias = Metodos.lerCsvParaMatriz("files/GameStart_Categorias.csv");

        // Remover duplicados nas vendas
        vendas = Metodos.obterPrimeiraReferencia(vendas, 4);

        String[] nomesJogos = new String[vendas.length];
        double[] lucrosJogos = new double[vendas.length];
        int countJogos = 0;

        // Calcular lucros por jogo
        for (int i = 0; i < vendas.length; i++) {
            String nomeJogo = vendas[i][4];
            double valorVenda = Double.parseDouble(vendas[i][5]);
            String categoriaVenda = vendas[i][3];
            double percentagem = 0;

            for (int j = 0; j < categorias.length; j++) {
                if (categorias[j][0].equals(categoriaVenda)) {
                    percentagem = Double.parseDouble(categorias[j][1]);
                }
            }

            double lucro = valorVenda * (percentagem / 100);
            boolean encontrado = false;
            for (int k = 0; k < countJogos; k++) {
                if (nomesJogos[k].equals(nomeJogo)) {
                    lucrosJogos[k] += lucro;
                    encontrado = true;
                }
            }

            if (!encontrado) {
                nomesJogos[countJogos] = nomeJogo;
                lucrosJogos[countJogos] = lucro;
                countJogos++;
            }
        }

        // Ordenar jogos por lucro em ordem decrescente
        for (int i = 0; i < countJogos - 1; i++) {
            for (int j = i + 1; j < countJogos; j++) {
                if (lucrosJogos[i] < lucrosJogos[j]) {
                    double tempLucro = lucrosJogos[i];
                    lucrosJogos[i] = lucrosJogos[j];
                    lucrosJogos[j] = tempLucro;

                    String tempNome = nomesJogos[i];
                    nomesJogos[i] = nomesJogos[j];
                    nomesJogos[j] = tempNome;
                }
            }
        }

        // Definir limite para top 5
        int limite;
        if (countJogos < 5) {
            limite = countJogos;
        } else {
            limite = 5;
        }

        // Imprimir top 5 jogos que mais lucro geraram
        System.out.println("\n*** Top 5 Jogos que mais lucro geraram ***");
        for (int i = 0; i < limite; i++) {
            System.out.println((i + 1) + ".º " + nomesJogos[i] + " | Lucro: €" + lucrosJogos[i]);
        }
    }

    /**
     * Método que encontra e imprime os 5 jogos que menos lucro geraram
     *
     * @throws FileNotFoundException se o ficheiro não for encontrado
     */
    private static void bottom5JogosMenosLucro() throws FileNotFoundException {
        String[][] vendas = Metodos.lerCsvParaMatriz("files/GameStart_Vendas.csv");
        String[][] categorias = Metodos.lerCsvParaMatriz("files/GameStart_Categorias.csv");

        // Remover duplicados nas vendas
        vendas = Metodos.obterPrimeiraReferencia(vendas, 4);

        String[] nomesJogos = new String[vendas.length];
        double[] lucrosJogos = new double[vendas.length];
        int countJogos = 0;

        // Calcular lucros por jogo
        for (int i = 0; i < vendas.length; i++) {
            String nomeJogo = vendas[i][4];
            double valorVenda = Double.parseDouble(vendas[i][5]);
            String categoriaVenda = vendas[i][3];
            double percentagem = 0;

            for (int j = 0; j < categorias.length; j++) {
                if (categorias[j][0].equals(categoriaVenda)) {
                    percentagem = Double.parseDouble(categorias[j][1]);
                }
            }

            double lucro = valorVenda * (percentagem / 100);
            boolean encontrado = false;
            for (int k = 0; k < countJogos; k++) {
                if (nomesJogos[k].equals(nomeJogo)) {
                    lucrosJogos[k] += lucro;
                    encontrado = true;
                }
            }

            if (!encontrado) {
                nomesJogos[countJogos] = nomeJogo;
                lucrosJogos[countJogos] = lucro;
                countJogos++;
            }
        }

        // Ordenar jogos por lucro em ordem crescente
        for (int i = 0; i < countJogos - 1; i++) {
            for (int j = i + 1; j < countJogos; j++) {
                if (lucrosJogos[i] > lucrosJogos[j]) {
                    double tempLucro = lucrosJogos[i];
                    lucrosJogos[i] = lucrosJogos[j];
                    lucrosJogos[j] = tempLucro;

                    String tempNome = nomesJogos[i];
                    nomesJogos[i] = nomesJogos[j];
                    nomesJogos[j] = tempNome;
                }
            }
        }

        // Definir limite para bottom 5
        int limite;
        if (countJogos < 5) {
            limite = countJogos;
        } else {
            limite = 5;
        }

        // Imprimir bottom 5 jogos que menos lucro geraram
        System.out.println("\n*** Bottom 5 Jogos que menos lucro geraram ***");
        for (int i = 0; i < limite; i++) {
            System.out.println((i + 1) + ".º " + nomesJogos[i] + " | Lucro: €" + lucrosJogos[i]);
        }
    }
}

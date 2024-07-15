import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Metodos {

    /**
     * Método que imprime o conteúdo de um ficheiro
     *
     * @param path Caminho do ficheiro
     * @throws FileNotFoundException se o ficheiro não for encontrado
     */

    public static void imprimirFicheiro(String path) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(path));
        while (scanner.hasNext()) {
            String linha = scanner.nextLine();
            System.out.println(linha);
        }
        scanner.close();
    }

    /**
     * Método que conta o número de linhas de um ficheiro
     *
     * @param path Caminho do ficheiro
     * @return Número de linhas no ficheiro
     * @throws FileNotFoundException se o ficheiro não for encontrado
     */

    public static int contarLinhas(String path) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(path));
        int linhas = 0;
        while (scanner.hasNextLine()) {
            linhas++;
            scanner.nextLine();
        }
        scanner.close();
        return linhas;
    }

    /**
     * Método que conta o número de colunas de um ficheiro
     *
     * @param path Caminho do ficheiro
     * @return Número de colunas no ficheiro
     * @throws FileNotFoundException se o ficheiro não for encontrado
     */

    public static int contarColunas(String path) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(path));
        if (scanner.hasNextLine()) {
            String linha = scanner.nextLine();
            scanner.close();
            return linha.split(";").length;
        }
        scanner.close();
        return 0;
    }

    /**
     * Método que lê um ficheiro CSV e armazena seu conteúdo numa matriz
     *
     * @param path Caminho do ficheiro CSV
     * @return Matriz com o conteúdo do ficheiro CSV
     * @throws FileNotFoundException se o ficheiro não for encontrado
     */

    public static String[][] lerCsvParaMatriz(String path) throws FileNotFoundException {
        int linhas = contarLinhas(path) - 1; // Conta as linhas do ficheiro e subtrai a linha de cabeçalho
        int colunas = contarColunas(path); // Conta as colunas do ficheiro
        String[][] matriz = new String[linhas][colunas]; // Cria a matriz com base no número de linhas e colunas

        Scanner scanner = new Scanner(new File(path)); // Inicializa o scanner para ler o ficheiro

        // Ignora a linha de cabeçalho (se existir)
        if (scanner.hasNextLine()) {
            scanner.nextLine(); // Ignora a linha de cabeçalho
        }

        int linhaIndex = 0;
        // Lê o ficheiro linha por linha e armazena os valores na matriz

        while (scanner.hasNextLine()) {
            String linha = scanner.nextLine();
            String[] valores = linha.split(";");
            System.arraycopy(valores, 0, matriz[linhaIndex], 0, valores.length);
            linhaIndex++;
        }

        return matriz; // Retorna a matriz contendo o conteúdo do ficheiro CSV
    }

    /**
     * Método que imprime uma matriz de forma formatada
     *
     * @param matriz Matriz de dados a ser impressa
     */

    public static void imprimirMatrizFormatada(String[][] matriz) {
        for (String[] linha : matriz) {
            for (String coluna : linha) {
                System.out.print(coluna + "\t");
            }
            System.out.println();
        }
    }

    /**
     * Método que verifica se as credenciais de acesso são válidas
     *
     * @param username Nome de utilizador
     * @param password Palavra-passe
     * @return true se as credenciais forem válidas, caso contrário false
     * @throws FileNotFoundException se o ficheiro não for encontrado
     */

    public static boolean acessosValidos(String username, String password) throws FileNotFoundException {
        String[][] admins = lerCsvParaMatriz("files/GameStart_Admins.csv");

        for (String[] credenciais : admins) {
            if (credenciais[0].equals(username) && credenciais[1].equals(password)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Método que calcula o somatório de uma coluna específica de uma matriz
     *
     * @param matriz Matriz de valores
     * @param coluna Índice da coluna
     * @return Somatório dos valores da coluna
     */

    public static double somatorioColuna(String[][] matriz, int coluna) {
        double soma = 0;
        for (int i = 0; i < matriz.length; i++) {
            soma += Double.parseDouble(matriz[i][coluna]);
        }
        return soma;
    }

    /**
     * Método que calcula o total gasto e os jogos comprados por cada cliente
     *
     * @param vendas Matriz de vendas
     * @return Matriz com o ID do cliente, total gasto e jogos comprados
     */
    public static String[][] calcularComprasClientes(String[][] vendas) {
        // Array temporário para armazenar as informações dos clientes
        String[][] infoClientes = new String[vendas.length][3];
        int count = 0;

        // Itera sobre todas as vendas usando um loop for tradicional
        for (int i = 0; i < vendas.length; i++) {
            String[] venda = vendas[i];
            int idCliente = Integer.parseInt(venda[1]);
            double valorVenda = Double.parseDouble(venda[5]);
            String nomeJogo = venda[4];

            boolean clienteExiste = false;

            // Verifica se o cliente já existe no array infoClientes
            for (int j = 0; j < count; j++) {
                if (Integer.parseInt(infoClientes[j][0]) == idCliente) {
                    clienteExiste = true;
                    // Atualiza o total gasto pelo cliente
                    double totalGasto = Double.parseDouble(infoClientes[j][1]) + valorVenda;
                    infoClientes[j][1] = Double.toString(totalGasto);
                    // Adiciona o novo jogo comprado à lista de jogos do cliente
                    infoClientes[j][2] += ", " + nomeJogo;
                    break;
                }
            }

            // Se o cliente não existir, cria uma nova entrada no array infoClientes
            if (!clienteExiste) {
                infoClientes[count][0] = Integer.toString(idCliente);
                infoClientes[count][1] = Double.toString(valorVenda);
                infoClientes[count][2] = nomeJogo;
                count++;
            }
        }

        // Cria um novo array ajustado ao número real de clientes
        String[][] resultado = new String[count][3];
        for (int i = 0; i < count; i++) {
            resultado[i][0] = infoClientes[i][0];
            resultado[i][1] = infoClientes[i][1];
            resultado[i][2] = infoClientes[i][2];
        }

        return resultado;
    }


    /**
     * Método para remover duplicados numa matriz baseado numa coluna específica
     *
     * @return novaMatriz sem duplicados
     */

    public static String[][] obterPrimeiraReferencia(String[][] matriz, int coluna) {
        int numColunas = matriz[0].length;

        String[] unicos = new String[matriz.length];
        int numUnicos = 0;
        boolean[] primeiroUnico = new boolean[matriz.length];

        for (int i = 0; i < matriz.length; i++) {

            boolean haRepetido = false;
            for (int j = 0; j < numUnicos; j++) {
                if (matriz[i][coluna].equals(unicos[j])) {
                    haRepetido = true;
                    break;
                }
            }

            if (!haRepetido) {
                unicos[numUnicos] = matriz[i][coluna];
                numUnicos++;
                primeiroUnico[i] = true;
            } else {
                primeiroUnico[i] = false;
            }

        }

        String[][] resultadoFinal = new String[numUnicos][numColunas];
        int posAtual = 0;

        for (int i = 0; i < matriz.length; ++i) {
            if (primeiroUnico[i]) {
                for (int j = 0; j < numColunas; ++j) {
                    resultadoFinal[posAtual][j] = matriz[i][j];
                }
                posAtual++;
            }
        }

        return resultadoFinal;
    }

    /**
     * Metodo para imprimir enter para continuar no menu
     */

    public static void pressioneEnterParaContinuar(Scanner input) {
        System.out.println("\nPressione Enter para voltar ao menu...");
        input.nextLine(); // Apenas uma chamada para nextLine() é necessária
    }

    /**
     * Método que retorna se um número é triangular ou não triangular
     *
     * @param num Número Inteiro
     * @return true se triangular, false se não triangular
     */

    public static boolean triangular(int num) {
        int cont = 0;
        for (int i = 1; cont < num; i++) {
            cont = cont + i;
            if (num == cont) {
                return true;
            }
        }
        return false;
    }

    /**
     * Método que imprime a mensagem de copyright
     */

    public static void copyright() {
        System.out.println("\uD83C\uDFAE ***** Desenvolvido por: Rita Silva ***** \uD83C\uDFAE");
    }
}

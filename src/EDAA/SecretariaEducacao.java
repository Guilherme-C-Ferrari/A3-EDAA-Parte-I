package EDAA;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;

public class SecretariaEducacao {
    private static final int maximo_creches = 11;
    private int[][] matriz_conexao;
    private double[][] matriz_distancia;

    private ListaSimples lista_creches;
    private int num_creches;

    public SecretariaEducacao() {
        this.matriz_conexao = new int[maximo_creches][maximo_creches];
        this.matriz_distancia = new double[maximo_creches][maximo_creches];
        this.lista_creches = new ListaSimples();
        this.num_creches = 0;
    }

    // Classe interna auxiliar
    private class ConexaoInfo {
        String creche;
        double distancia;
        public ConexaoInfo(String c, double d) {
            this.creche = c;
            this.distancia = d;
        }
    }

    // --- Funcionalidade 1: Ler os dados do grafo em um arquivo .txt ---
    public void lerGrafo(String nomeArquivo) {
        try (BufferedReader br = new BufferedReader(new FileReader(nomeArquivo))) {
            String linha_creches = br.readLine();
            if (linha_creches == null) return;

            String[] nomes = linha_creches.split(",");

            for (String nome : nomes) {
                String nome_trim = nome.trim();
                if (!nome_trim.isEmpty()) {
                    lista_creches.adicionar(nome_trim);
                }
            }
            num_creches = lista_creches.getTamanho();

            if (num_creches > maximo_creches) {
                System.err.println("Número de creches (" + num_creches + ") excede o limite estático (" + maximo_creches + ").");
                return;
            }

            String linha;
            while ((linha = br.readLine()) != null) {
                String[] partes = linha.split(",");
                if (partes.length == 3) {
                    String origem = partes[0].trim();
                    String destino = partes[1].trim();
                    double distancia = Double.parseDouble(partes[2].trim());

                    int i = lista_creches.getIndice(origem);
                    int j = lista_creches.getIndice(destino);

                    if (i != -1 && j != -1) {
                        matriz_conexao[i][j] = 1;
                        matriz_distancia[i][j] = distancia;
                        matriz_conexao[j][i] = 1;
                        matriz_distancia[j][i] = distancia;
                    }
                }
            }
            System.out.println("Dados do grafo lidos e estruturas inicializadas com " + num_creches + " creches.");

        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.err.println("Erro de formato: A distância deve ser um número válido. " + e.getMessage());
        }
    }

    // --- Funcionalidade 2: Informar o número de conexões partindo de cada creche ---
    public void informarNumConexoes() {
        System.out.println("\n## 2. Número de Conexões por Creche");
        for (int i = 0; i < num_creches; i++) {
            int conexoes = 0;
            for (int j = 0; j < num_creches; j++) {
                conexoes += matriz_conexao[i][j];
            }
            String nome_creche = lista_creches.getCreche(i);
            System.out.println(nome_creche + ": " + conexoes + " conexões diretas");
        }
    }

    // --- Funcionalidade 3: Listar conexões em ordem crescente de distância ---
    public void listarConexoesOrdenadas(String creche_origem) {
        int i = lista_creches.getIndice(creche_origem);

        if (i == -1) {
            System.out.println("\n## 3. Listar Conexões Ordenadas\n Creche '" + creche_origem + "' não encontrada.");
            return;
        }

        System.out.println("\n## 3. Conexões de " + creche_origem + " (em ordem crescente de distância)");

        ConexaoInfo[] conexoes = new ConexaoInfo[num_creches];
        int count = 0;

        for (int j = 0; j < num_creches; j++) {
            if (matriz_conexao[i][j] == 1) { // Se há conexão (1)
                String nome_destino = lista_creches.getCreche(j);
                double distancia = matriz_distancia[i][j];
                conexoes[count] = new ConexaoInfo(nome_destino, distancia);
                count++;
            }
        }

        if (count == 0) {
            System.out.println("Nenhuma conexão direta encontrada.");
            return;
        }

        ConexaoInfo[] conexoes_reais = Arrays.copyOf(conexoes, count);
        Arrays.sort(conexoes_reais, Comparator.comparingDouble(c -> c.distancia));

        for (ConexaoInfo conexao : conexoes_reais) {
            System.out.printf("%s -> %s (%.1f Km)\n", creche_origem, conexao.creche, conexao.distancia);
        }
    }


    // --- Funcionalidade 4: Informar a distância entre duas creches (se existir) ---
    public void informarDistancia(String creche_a, String creche_b) {
        int i = lista_creches.getIndice(creche_a);
        int j = lista_creches.getIndice(creche_b);

        System.out.println("\n## 4. Informar Distância Direta");

        if (i == -1 || j == -1) {
            System.out.println("Pelo menos uma das creches não foi encontrada.");
            return;
        }

        if (matriz_conexao[i][j] == 1) {
            double distancia = matriz_distancia[i][j];
            System.out.printf("A distância direta entre %s e %s é de %.1f Km.\n", creche_a, creche_b, distancia);
        } else {
            System.out.printf("Não existe uma conexão direta entre %s e %s.\n", creche_a, creche_b);
        }
    }


    // --- Funcionalidade 5: Incluir novas conexões entre creches ---
    public void incluirNovaConexao(String creche_a, String creche_b, double distancia) {
        int i = lista_creches.getIndice(creche_a);
        int j = lista_creches.getIndice(creche_b);

        System.out.println("\n## 5. Incluir Nova Conexão");

        if (i == -1 || j == -1) {
            System.out.println("Pelo menos uma das creches não foi encontrada. Conexão não incluída.");
            return;
        }

        if (i == j) {
            System.out.println("Não é possível conectar uma creche a si mesma.");
            return;
        }

        if (matriz_conexao[i][j] == 1) {
            System.out.printf("A conexão entre %s e %s já existe (%.1f Km). Distância atualizada para %.1f Km.\n", creche_a, creche_b, matriz_distancia[i][j], distancia);
        } else {
            System.out.printf("Nova conexão entre %s e %s incluída com %.1f Km.\n", creche_a, creche_b, distancia);
        }

        matriz_conexao[i][j] = 1;
        matriz_distancia[i][j] = distancia;
        matriz_conexao[j][i] = 1;
        matriz_distancia[j][i] = distancia;
    }
}
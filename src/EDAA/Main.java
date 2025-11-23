package EDAA;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        SecretariaEducacao secretaria = new SecretariaEducacao();
        String arquivo = "grafo.txt";
        Scanner scanner = new Scanner(System.in);
        String input;

        // 1. Ler os dados do grafo
        secretaria.lerGrafo(arquivo);
        System.out.println("\nDados do grafo lidos");
        do {
            mostrarOpcoes();
            input = scanner.nextLine();
            executarOpcao(secretaria, input, arquivo);
        } while (!input.equals("5"));
    }

    public static void mostrarOpcoes() {
        System.out.println("\nQual opção deseja acessar?");
        System.out.println("1 - Informar o número de conexões possíveis partindo de cada creche.");
        System.out.println("2 - Listar conexões ordenadas de uma creche.");
        System.out.println("3 - Informar a distância entre duas creches.");
        System.out.println("4 - Incluir novas conexões.");
        System.out.println("5 - Sair do programa.");
    }

    public static void executarOpcao(SecretariaEducacao secretaria, String input, String arquivo) {
        Scanner scanner = new Scanner(System.in);
        String creche1;
        String creche2;
        String aux;
        Float dist;

        switch (input) {
            case "1":
                secretaria.informarNumConexoes();
                break;
            case "2":
                System.out.println("Digite o nome da creche que deseja visualizar:");
                creche1 = scanner.nextLine();

                secretaria.listarConexoesOrdenadas(creche1);
                break;
            case "3":
                System.out.println("Digite o nome da primeira creche:");
                creche1 = scanner.nextLine();
                System.out.println("Digite o nome da segunda creche:");
                creche2 = scanner.nextLine();

                secretaria.informarDistancia(creche1, creche2);
                break;
            case "4":
                System.out.println("Digite o nome da primeira creche:");
                creche1 = scanner.nextLine();
                System.out.println("Digite o nome da segunda creche:");
                creche2 = scanner.nextLine();
                System.out.println("Digite a distância entre as duas:");
                aux = scanner.nextLine();

                try {
                    dist = Float.parseFloat(aux);
                    secretaria.incluirNovaConexao(creche1, creche2, dist);
                    System.out.println("\n-> Após inclusão da nova conexão:");
                    secretaria.informarDistancia(creche1, creche2);
                } catch (NumberFormatException e) {
                    System.err.println("Erro de formato: A distância deve ser um número válido. " + e.getMessage());
                }
                break;
            case "5":
                System.out.println("Programa encerrando.");
                break;
            default:
                System.out.println("Opção inválida.");
                break;
        }
    }
}
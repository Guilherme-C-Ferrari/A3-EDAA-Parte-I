package EDAA;

public class Main {
    // --- Metodo Principal para Teste ---
    public static void main(String[] args) {
        SecretariaEducacao se = new SecretariaEducacao();
        String arquivo = "grafo.txt";

        // 1. Ler os dados do grafo
        se.lerGrafo(arquivo);

        // 2. Informar o número de conexões possíveis partindo de cada creche
        se.informarNumConexoes();

        // 3. Listar conexões ordenadas
        se.listarConexoesOrdenadas("JoaoGama");

        // 4. Informar a distância entre duas creches
        se.informarDistancia("AnaReinaldo", "JoaoTimoteo");
        se.informarDistancia("HerculanoDeMelo", "JoaoTimoteo"); // Conexão inexistente

        // 5. Incluir novas conexões
        se.incluirNovaConexao("JoseVicente", "JoaoGama", 15.0);

        // 5. Testar a nova conexão e o número de conexões
        System.out.println("\n-> Após inclusão da nova conexão:");
        se.informarDistancia("JoseVicente", "JoaoGama");
        se.informarNumConexoes();
    }
}
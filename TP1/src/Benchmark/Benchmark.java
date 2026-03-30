package Benchmark;

import Estruturas.FullTarjan;
import Estruturas.NaiveDSU;
import Estruturas.UnionByRank;
import Estruturas.VarianteBase;
import Geradores.GeradorGrafo;
import Kruskal.Grafo;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.Locale;

import static Kruskal.Kruskal.executarKruskalDeVarianteParaGrafo;

public class Benchmark {

    private static final int[] N_ARESTAS = {10_000, 100_000, 500_000, 1_000_000};

    @SuppressWarnings("unchecked")
    private static final Class<? extends VarianteBase>[] VARIANTES = new Class[]{
            NaiveDSU.class,
            UnionByRank.class,
            FullTarjan.class
    };

    public static void executar() {
        System.out.println("Iniciando benchmark...\n");

        try (PrintWriter writer = new PrintWriter(new FileWriter("resultados.csv"))) {
            writer.println("n_arestas,tipo_grafo,variante,tempo_ns,tempo_ms");

            for (int n : N_ARESTAS) {
                int numVerticesAleatorio = (int) Math.ceil(Math.sqrt(2.0 * n)) + 1;
                int numVerticesPiorCaso = Math.max(n / 2, 2);

                Grafo grafoAleatorio = GeradorGrafo.gerarAleatorio(numVerticesAleatorio, n);
                Grafo grafoPiorCaso = GeradorGrafo.gerarPiorCaso(numVerticesPiorCaso);

                Collections.sort(grafoAleatorio.getArestas());
                Collections.sort(grafoPiorCaso.getArestas());

                System.out.printf("=== n = %,d arestas ===%n", n);

                medirERegistrar(writer, n, "aleatorio", grafoAleatorio);
                medirERegistrar(writer, n, "pior_caso", grafoPiorCaso);
            }

            System.out.println("\nResultados salvos em: resultados.csv");

        } catch (IOException e) {
            System.err.println("Erro ao escrever CSV: " + e.getMessage());
        }
    }

    private static void medirERegistrar(PrintWriter writer, int n, String tipoGrafo, Grafo grafo) {
        System.out.printf("  [%s]%n", tipoGrafo);

        // Aquecimento para evitar cold start
        for (int w = 0; w < 3; w++) {
            for (Class<? extends VarianteBase> variante : VARIANTES) {
                executarKruskalDeVarianteParaGrafo(variante, grafo);
            }
        }

        for (Class<? extends VarianteBase> variante : VARIANTES) {
            long inicio = System.nanoTime();
            executarKruskalDeVarianteParaGrafo(variante, grafo);
            long fim = System.nanoTime();

            long tempoNs = fim - inicio;
            double tempoMs = tempoNs / 1_000_000.0;

            System.out.printf("    %-15s %,12d ns  (%8.3f ms)%n",
                    variante.getSimpleName(), tempoNs, tempoMs);

            writer.printf(Locale.US, "%d,%s,%s,%d,%.3f\n",
                    n, tipoGrafo, variante.getSimpleName(), tempoNs, tempoMs);
        }
    }
}

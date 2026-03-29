import Estruturas.FullTarjan;
import Estruturas.NaiveDSU;
import Estruturas.UnionByRank;
import Geradores.GeradorGrafo;
import Kruskal.Aresta;
import Kruskal.Grafo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static Kruskal.Kruskal.executarKruskalDeVarianteParaGrafo;

public class Main {
    public static void main(String[] args) {
        Grafo grafoBase = GeradorGrafo.gerarAleatorio(10000, 50000);
        Grafo grafoPiorCaso = GeradorGrafo.gerarPiorCaso(10000);

        //Tb retirei a ordenação de dentro do Kruskal pq senão o naive ficaria prejudicado nos testes 
        Collections.sort(grafoBase.getArestas());
        Collections.sort(grafoPiorCaso.getArestas());

        List<Grafo> grafos = new ArrayList<>();
        grafos.add(grafoBase);
        grafos.add(grafoPiorCaso);

        for (Grafo grafo : grafos) {

            //Adicionei um aquecimento aqui para evitar "cold start" nas medições hehe
            for (int w = 0; w < 3; w++) {
                executarKruskalDeVarianteParaGrafo(new NaiveDSU(grafo.getNumVertices()), grafo);
                executarKruskalDeVarianteParaGrafo(new UnionByRank(grafo.getNumVertices()), grafo);
                executarKruskalDeVarianteParaGrafo(new FullTarjan(grafo.getNumVertices()), grafo);
            }

            long inicioNaive = System.nanoTime();
            List<Aresta> mst00 = executarKruskalDeVarianteParaGrafo(new NaiveDSU(grafo.getNumVertices()), grafo);
            long fimNaive = System.nanoTime();

            long inicioUbR = System.nanoTime();
            List<Aresta> mst01 = executarKruskalDeVarianteParaGrafo(new UnionByRank(grafo.getNumVertices()), grafo);
            long FimUbR = System.nanoTime();

            long inicioFullTarjan = System.nanoTime();
            List<Aresta> mst02 = executarKruskalDeVarianteParaGrafo(new FullTarjan(grafo.getNumVertices()), grafo);
            long fimFullTarjan = System.nanoTime();

            System.out.println("Naive: " + (fimNaive - inicioNaive) + " ns"
            + "\nUnionByRank: " + (FimUbR - inicioUbR) + " ns"
            + "\nFullTarjan: " + (fimFullTarjan - inicioFullTarjan) + " ns");
            }
    }
}
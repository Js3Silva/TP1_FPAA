package Kruskal;

import estruturas.NaiveDSU;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Kruskal {
    public static List<Aresta> executarKruskalNaive(int numVertices, List<Aresta> arestas) {
        List<Aresta> mst = new ArrayList<>();
        NaiveDSU dsu = new NaiveDSU(numVertices);

        Collections.sort(arestas);

        for (Aresta aresta : arestas) {
            int rootOrigem = dsu.find(aresta.origem);
            int rootDestino = dsu.find(aresta.destino);

            if (rootOrigem != rootDestino) {
                mst.add(aresta);
                dsu.union(rootOrigem, rootDestino);
            }
        }
        return mst;
    }
}

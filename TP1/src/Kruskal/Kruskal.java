package Kruskal;

import Estruturas.VarianteBase;

import java.util.ArrayList;
import java.util.List;

public class Kruskal {

    public static List<Aresta> executarKruskalDeVarianteParaGrafo(Class<? extends VarianteBase> clazz, Grafo grafo) {
        List<Aresta> mst = new ArrayList<>();

        VarianteBase variante;
        try {
            variante = clazz.getDeclaredConstructor(int.class).newInstance(grafo.getNumVertices());
        } catch (Exception e) {
            System.out.println("Incapaz de instanciar variante de DSU do tipo:" + clazz.getName());
            return mst;
        }

        int mstAlvo = grafo.getNumVertices() - 1;
        for (Aresta aresta : grafo.getArestas()) {
            if (mst.size() == mstAlvo) break;
            if (variante.find(aresta.origem) != variante.find(aresta.destino)) {
                mst.add(aresta);
                variante.union(aresta.origem, aresta.destino);
            }
        }

        return mst;
    }
}

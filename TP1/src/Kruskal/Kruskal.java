package Kruskal;

import Estruturas.VarianteBase;

import java.util.ArrayList;
import java.util.List;

public class Kruskal {

    public static VarianteBase executarKruskalDeVarianteParaGrafo(Class<? extends VarianteBase> clazz, Grafo grafo) {
        VarianteBase variante;
        try {
            variante = clazz.getDeclaredConstructor(int.class).newInstance(grafo.getNumVertices());
        } catch (Exception e) {
            System.out.println("Incapaz de instanciar variante de DSU do tipo:" + clazz.getName());
            return null;
        }

        int mstAlvo = grafo.getNumVertices() - 1;
        int mstCount = 0;
        for (Aresta aresta : grafo.getArestas()) {
            if (mstCount == mstAlvo) break;
            if (variante.find(aresta.origem) != variante.find(aresta.destino)) {
                variante.union(aresta.origem, aresta.destino);
                mstCount++;
            }
        }

        return variante;
    }
}

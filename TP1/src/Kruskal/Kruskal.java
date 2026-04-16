package Kruskal;

import Estruturas.VarianteBase;

import java.util.ArrayList;
import java.util.List;

public class Kruskal {
    private static final Map<Class<?>, Constructor<? extends VarianteBase>> constructorCache = new HashMap<>();

    public static VarianteBase executarKruskalDeVarianteParaGrafo(Class<? extends VarianteBase> clazz, Grafo grafo) {
        VarianteBase variante;
        var construtorClazz = getConstructor(clazz);
        try {
            variante = construtorClazz.newInstance(grafo.getNumVertices());
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

    @SuppressWarnings("unchecked")
    private static Constructor<? extends VarianteBase> getConstructor(Class<? extends VarianteBase> clazz) {
        return constructorCache.computeIfAbsent(clazz, c -> {
            try {
                Constructor<? extends VarianteBase> constructor =
                        (Constructor<? extends VarianteBase>) c.getDeclaredConstructor(int.class);
                constructor.setAccessible(true);
                return constructor;
            } catch (NoSuchMethodException e) {
                throw new RuntimeException("Construtor não encontrado para: " + c.getName(), e);
            }
        });
    }
}

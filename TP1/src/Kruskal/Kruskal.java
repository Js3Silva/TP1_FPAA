package kruskal;

import dsu.Dsu;
import java.util.Map;
import java.util.HashMap;
import java.lang.reflect.Constructor;

public class Kruskal {
    private static final Map<Class<?>, Constructor<? extends Dsu>> constructorCache = new HashMap<>();

    public static Dsu executarKruskalDeVarianteParaGrafo(Class<? extends Dsu> clazz, Grafo grafo) {
        Dsu variante;
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
    private static Constructor<? extends Dsu> getConstructor(Class<? extends Dsu> clazz) {
        return constructorCache.computeIfAbsent(clazz, c -> {
            try {
                Constructor<? extends Dsu> constructor =
                        (Constructor<? extends Dsu>) c.getDeclaredConstructor(int.class);
                constructor.setAccessible(true);
                return constructor;
            } catch (NoSuchMethodException e) {
                throw new RuntimeException("Construtor não encontrado para: " + c.getName(), e);
            }
        });
    }
}

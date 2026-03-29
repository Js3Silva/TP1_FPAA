import Kruskal.Aresta;
import java.util.ArrayList;
import java.util.List;
import static Kruskal.Kruskal.executarKruskalNaive;

public class Main {
    public static void main(String[] args) {
        int V = 4;
        List<Aresta> grafo = new ArrayList<>();
        grafo.add(new Aresta(0, 1, 10));
        grafo.add(new Aresta(0, 2, 6));
        grafo.add(new Aresta(0, 3, 5));
        grafo.add(new Aresta(1, 3, 15));
        grafo.add(new Aresta(2, 3, 4));

        long inicio = System.nanoTime();
        List<Aresta> mst = executarKruskalNaive(V, grafo);
        long fim = System.nanoTime();

        System.out.println("Naive: " + (fim - inicio) + " ns");
    }
}
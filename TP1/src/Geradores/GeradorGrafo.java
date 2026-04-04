package Geradores;

import Kruskal.Grafo;

import java.util.Random;

public class GeradorGrafo {

    public static Grafo gerarAleatorio(int numVertices, int numArestas) {
        Random rand = new Random();

        Grafo grafo = new Grafo(numVertices, numArestas);

        for (int i = 0; i < numArestas; i++) {
            int u = rand.nextInt(numVertices);
            int v = rand.nextInt(numVertices);

            while (u == v) {
                v = rand.nextInt(numVertices);
            }

            int peso = rand.nextInt(100) + 1;

            grafo.addAresta(u, v, peso);
        }

        return grafo;
    }

    public static Grafo gerarPiorCaso(int numVertices) {
        int numArestas = numVertices * (numVertices - 1) / 2;
        Grafo grafo = new Grafo(numVertices, numArestas);

        int peso = 1;

        for (int k = 1; k < numVertices; k++) {
            grafo.addAresta(k - 1, k, peso++);

            for (int i = 0; i < k - 1; i++) {
                grafo.addAresta(i, k, peso++);
            }
        }

        return grafo;
    }
}
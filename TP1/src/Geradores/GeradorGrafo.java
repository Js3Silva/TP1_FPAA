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
        int capacidade = numVertices - 1 + numVertices;
        Grafo grafo = new Grafo(numVertices, capacidade);

        for (int i = 0; i < numVertices - 1; i++) {
            grafo.addAresta(i, i + 1, i + 1);
        }
        int peso = numVertices + 1;

        for (int i = 0; i < numVertices / 2; i++) {
            grafo.addAresta(0, numVertices - 1 - i, peso++);
            grafo.addAresta(i, numVertices - 1, peso++);
        }

        return grafo;
    }
}
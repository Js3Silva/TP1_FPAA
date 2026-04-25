# Análise de Desempenho: Disjoint Set Union (DSU) e Algoritmo de Kruskal

Este repositório contém o Trabalho Prático 1 da disciplina de **Fundamentos de Projeto e Análise de Algoritmos (2026/1)** do curso de Engenharia de Software na **PUC Minas**.

O objetivo do projeto é implementar e comparar três variantes da estrutura de dados *Disjoint Set Union* (DSU) para resolver o problema da conectividade dinâmica, utilizando o Algoritmo de Kruskal para Árvores Geradoras Mínimas (MST) como cenário de teste.

## Estruturas Implementadas

Foram desenvolvidas três versões da estrutura Union-Find, com diferentes níveis de otimização:

1.  **Naive (Ingénua):** Implementação básica sem heurísticas. Sujeita à degeneração da árvore em listas encadeadas, com custo de pior caso $O(n)$.
2.  **Union by Rank:** Otimização na união que mantém a árvore equilibrada ao pendurar sempre a árvore menor na raiz da maior. Custo de pior caso $O(\log n)$.
3.  **Full Tarjan:** A versão mais eficiente, combinando *Union by Rank* com a **Compressão de Caminho** (*Path Compression*). Atinge uma complexidade amortizada de $O(\alpha(n))$, onde $\alpha$ é a inversa da função de Ackermann.

## 📊 Complexidade Teórica

| Variante | Pior Caso (Única Op.) | Custo Amortizado |
| :--- | :--- | :--- |
| **Naive** | $O(n)$ | $O(n)$ |
| **Union by Rank** | $O(\log n)$ | $O(\log n)$ |
| **Full Tarjan** | $O(\log n)$ | $O(\alpha(n))$ |

## 📁 Estrutura do Projeto

* `/src/dsu`: Contém as classes Java com as implementações das variantes do DSU.
* `/src/kruskal`: Implementação do Algoritmo de Kruskal.
* `/src/benchmark`: Gerador de grafos e teste desempenho.
* `/docs`: Artigo técnico formatado segundo o modelo da SBC e gráficos gerados.

## 🛠️ Como Executar

### Pré-requisitos
* Java JDK 17 ou superior.
* IDE de sua preferência (IntelliJ, VS Code, Eclipse).

### Execução
1. Clone o repositório:
   ```bash
   git clone https://github.com/JonathaDaSilva/TP1_FPAA.git
   ```
   
2. Compile o projeto:
```Bash
javac src/**/*.java
```

3. Execute os testes de desempenho:
```Bash
java src/Main.java
```

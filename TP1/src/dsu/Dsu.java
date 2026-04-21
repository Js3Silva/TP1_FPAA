package dsu;

public abstract class Dsu {
    int[] pai;
    protected long operacoes;

    Dsu(int n) {
        this.pai = new int[n];
        this.operacoes = 0;
    }

    public abstract int find(int i);

    public abstract void union(int a, int b);

    public long getOperacoes() {
        return operacoes;
    }
}

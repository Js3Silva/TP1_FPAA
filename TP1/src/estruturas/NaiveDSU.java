package estruturas;

public class NaiveDSU {

    int[] pai;

    public NaiveDSU(int n){
        pai = new int[n];
        for(int i = 0; i < n; i++){
            pai[i] = i;
        }
    }

    public int find(int i){
        if(pai[i] == i){
            return i;
        }
        return find(pai[i]);
    }

    public void union(int a, int b){
        int rootA = find(a);
        int rootB = find(b);
        if(rootA != rootB){
            pai[rootA] = rootB;
        }
    }
}

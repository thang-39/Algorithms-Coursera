public class QuickFindUF {
    private int[] id;

    public QuickFindUF(int N) {
        this.id = new int[N];
        for (int i = 0; i < N; i++)
            id[i] = i;
    }

    public boolean connected(int p, int q) {
        return id[p] == id[q];
    }

    public void union(int p, int q) {
        int pId = id[p];
        int qId = id[q];

        // change all entries with id[p] to id[q]
        // at most 2N + 2 array accesses
        for (int i = 0; i < id.length; i++) {
            if (id[i] == pId)
                id[i] = qId;
        }
    }


}

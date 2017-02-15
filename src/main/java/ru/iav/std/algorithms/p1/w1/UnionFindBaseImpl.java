package ru.iav.std.algorithms.p1.w1;

import static ru.iav.std.algorithms.util.Exceptions.notImplemented;

/**
 * Created by takoe on 12.02.17.
 */
class UnionFindBaseImpl extends AbstractUnionFind implements UnionFind {

    UnionFindBaseImpl(int itemsCount) {
        super(itemsCount);
    }

    @Override
    public void union(int p, int q) {
        int pid = id[p];
        int qid = id[q];
        int minId = Math.min(pid, qid);
        int maxId = Math.max(pid, qid);
        for (int i = 0; i < id.length; i++) {
            if (id[i] == maxId) {
                id[i] = minId;
            }
        }
    }

    @Override
    public boolean connected(int p, int q) {
        return id[p] == id[q];
    }

    @Override
    public int find(int p) {
        notImplemented();
        return 0;
    }

    @Override
    public int count() {
        return max(id);
    }

    private static int max(int[] array) {
        int result = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] > result) result = array[i];
        }
        return result;
    }
}

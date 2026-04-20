import java.io.*;
import java.util.*;

public class Main {
    static final long INF = Long.MAX_VALUE / 4;
    static final int NODE_BITS = 17;          // 2^17 = 131072 > 100000
    static final int EDGE_BITS = 20;          // 2^20 = 1048576 > 200000
    static final int EDGE_MASK = (1 << EDGE_BITS) - 1;

    static int N, M, K, Q;

    // original graph
    static int[] head, to, next, weight;
    static int edgePtr = 0;

    // undirected edges (for Kruskal)
    static int[] eu, ev, ew;

    // tree
    static int[] treeHead, treeTo, treeNext;
    static long[] treeW;
    static int treePtr = 0;

    static long[] dist;

    static int LOG;
    static int[][] parent;
    static long[][] minUp;
    static int[] depth;

    static void addEdge(int u, int v, int w) {
        to[edgePtr] = v;
        weight[edgePtr] = w;
        next[edgePtr] = head[u];
        head[u] = edgePtr++;
    }

    static void addTreeEdge(int u, int v, long w) {
        treeTo[treePtr] = v;
        treeW[treePtr] = w;
        treeNext[treePtr] = treeHead[u];
        treeHead[u] = treePtr++;
    }

    static class FastScanner {
        private final InputStream in = System.in;
        private final byte[] buffer = new byte[1 << 16];
        private int ptr = 0, len = 0;

        private int read() throws IOException {
            if (ptr >= len) {
                len = in.read(buffer);
                ptr = 0;
                if (len <= 0) return -1;
            }
            return buffer[ptr++];
        }

        int nextInt() throws IOException {
            int c;
            do {
                c = read();
            } while (c <= ' ');

            int sign = 1;
            if (c == '-') {
                sign = -1;
                c = read();
            }

            int val = 0;
            while (c > ' ') {
                val = val * 10 + (c - '0');
                c = read();
            }
            return val * sign;
        }
    }

    static class LongHeap {
        long[] heap;
        int size;

        LongHeap(int cap) {
            heap = new long[Math.max(4, cap + 1)];
        }

        boolean isEmpty() {
            return size == 0;
        }

        void add(long x) {
            if (size + 1 == heap.length) {
                heap = Arrays.copyOf(heap, heap.length << 1);
            }
            int i = ++size;
            while (i > 1 && heap[i >> 1] > x) {
                heap[i] = heap[i >> 1];
                i >>= 1;
            }
            heap[i] = x;
        }

        long poll() {
            long ret = heap[1];
            long x = heap[size--];

            int i = 1;
            while ((i << 1) <= size) {
                int c = i << 1;
                if (c + 1 <= size && heap[c + 1] < heap[c]) c++;
                if (heap[c] >= x) break;
                heap[i] = heap[c];
                i = c;
            }
            heap[i] = x;
            return ret;
        }
    }

    static class DSU {
        int[] parent, size;

        DSU(int n) {
            parent = new int[n + 1];
            size = new int[n + 1];
            for (int i = 1; i <= n; i++) {
                parent[i] = i;
                size[i] = 1;
            }
        }

        int find(int x) {
            while (parent[x] != x) {
                parent[x] = parent[parent[x]];
                x = parent[x];
            }
            return x;
        }

        boolean union(int a, int b) {
            a = find(a);
            b = find(b);
            if (a == b) return false;
            if (size[a] < size[b]) {
                int tmp = a;
                a = b;
                b = tmp;
            }
            parent[b] = a;
            size[a] += size[b];
            return true;
        }
    }

    static void dijkstra(int[] festivals) {
        dist = new long[N + 1];
        Arrays.fill(dist, INF);

        LongHeap pq = new LongHeap(M * 2 + K + 5);

        for (int city : festivals) {
            dist[city] = 0;
            pq.add(city); // (0 << NODE_BITS) | city == city
        }

        long nodeMask = (1L << NODE_BITS) - 1;

        while (!pq.isEmpty()) {
            long cur = pq.poll();
            long d = cur >>> NODE_BITS;
            int u = (int) (cur & nodeMask);

            if (d != dist[u]) continue;

            for (int e = head[u]; e != -1; e = next[e]) {
                int v = to[e];
                long nd = d + weight[e];
                if (nd < dist[v]) {
                    dist[v] = nd;
                    pq.add((nd << NODE_BITS) | v);
                }
            }
        }
    }

    static void buildLCA() {
        depth = new int[N + 1];
        parent = new int[LOG][N + 1];
        minUp = new long[LOG][N + 1];

        int[] stack = new int[N];
        int top = 0;
        boolean[] visited = new boolean[N + 1];

        stack[top++] = 1;
        visited[1] = true;
        minUp[0][1] = INF;

        while (top > 0) {
            int u = stack[--top];
            for (int e = treeHead[u]; e != -1; e = treeNext[e]) {
                int v = treeTo[e];
                if (visited[v]) continue;
                visited[v] = true;
                parent[0][v] = u;
                minUp[0][v] = treeW[e];
                depth[v] = depth[u] + 1;
                stack[top++] = v;
            }
        }

        for (int k = 1; k < LOG; k++) {
            for (int v = 1; v <= N; v++) {
                int mid = parent[k - 1][v];
                if (mid == 0) {
                    parent[k][v] = 0;
                    minUp[k][v] = minUp[k - 1][v];
                } else {
                    parent[k][v] = parent[k - 1][mid];
                    minUp[k][v] = Math.min(minUp[k - 1][v], minUp[k - 1][mid]);
                }
            }
        }
    }

    static long query(int a, int b) {
        long ans = INF;

        if (depth[a] < depth[b]) {
            int tmp = a;
            a = b;
            b = tmp;
        }

        int diff = depth[a] - depth[b];
        for (int k = 0; k < LOG; k++) {
            if (((diff >> k) & 1) != 0) {
                ans = Math.min(ans, minUp[k][a]);
                a = parent[k][a];
            }
        }

        if (a == b) return ans;

        for (int k = LOG - 1; k >= 0; k--) {
            if (parent[k][a] != parent[k][b]) {
                ans = Math.min(ans, minUp[k][a]);
                ans = Math.min(ans, minUp[k][b]);
                a = parent[k][a];
                b = parent[k][b];
            }
        }

        ans = Math.min(ans, minUp[0][a]);
        ans = Math.min(ans, minUp[0][b]);
        return ans;
    }

    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner();

        N = fs.nextInt();
        M = fs.nextInt();
        K = fs.nextInt();
        Q = fs.nextInt();

        head = new int[N + 1];
        Arrays.fill(head, -1);

        to = new int[2 * M];
        next = new int[2 * M];
        weight = new int[2 * M];

        eu = new int[M];
        ev = new int[M];
        ew = new int[M];

        for (int i = 0; i < M; i++) {
            int a = fs.nextInt();
            int b = fs.nextInt();
            int c = fs.nextInt();

            eu[i] = a;
            ev[i] = b;
            ew[i] = c;

            addEdge(a, b, c);
            addEdge(b, a, c);
        }

        int[] festivals = new int[K];
        for (int i = 0; i < K; i++) festivals[i] = fs.nextInt();

        dijkstra(festivals);

        treeHead = new int[N + 1];
        Arrays.fill(treeHead, -1);
        treeTo = new int[2 * (N - 1)];
        treeNext = new int[2 * (N - 1)];
        treeW = new long[2 * (N - 1)];

        long[] order = new long[M];
        for (int i = 0; i < M; i++) {
            long cap = Math.min(dist[eu[i]], dist[ev[i]]);
            order[i] = (cap << EDGE_BITS) | i;
        }
        Arrays.sort(order);

        DSU dsu = new DSU(N);
        int used = 0;
        for (int i = M - 1; i >= 0 && used < N - 1; i--) {
            int idx = (int) (order[i] & EDGE_MASK);
            long cap = order[i] >>> EDGE_BITS;
            int a = eu[idx];
            int b = ev[idx];
            if (dsu.union(a, b)) {
                addTreeEdge(a, b, cap);
                addTreeEdge(b, a, cap);
                used++;
            }
        }

        LOG = 1;
        while ((1 << LOG) <= N) LOG++;
        buildLCA();

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < Q; i++) {
            int s = fs.nextInt();
            int t = fs.nextInt();
            sb.append(query(s, t)).append('\n');
        }
        System.out.print(sb);
    }
}
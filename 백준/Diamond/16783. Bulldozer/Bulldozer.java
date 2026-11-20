import java.io.*;
import java.util.*;

public class Main {
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

        long nextLong() throws IOException {
            int c;
            do {
                c = read();
            } while (c <= ' ' && c != -1);

            int sign = 1;
            if (c == '-') {
                sign = -1;
                c = read();
            }

            long val = 0;
            while (c > ' ') {
                val = val * 10 + (c - '0');
                c = read();
            }
            return val * sign;
        }

        int nextInt() throws IOException {
            return (int) nextLong();
        }
    }

    static class Point {
        long x, y, w;
        Point(long x, long y, long w) {
            this.x = x;
            this.y = y;
            this.w = w;
        }
    }

    static class SegTree {
        int n;
        long[] sum, pref, suff, best;

        SegTree(long[] arr) {
            n = arr.length;
            sum = new long[n * 4];
            pref = new long[n * 4];
            suff = new long[n * 4];
            best = new long[n * 4];
            build(1, 0, n - 1, arr);
        }

        void build(int node, int l, int r, long[] arr) {
            if (l == r) {
                long v = arr[l];
                sum[node] = v;
                pref[node] = Math.max(0L, v);
                suff[node] = Math.max(0L, v);
                best[node] = Math.max(0L, v);
                return;
            }
            int mid = (l + r) >>> 1;
            build(node << 1, l, mid, arr);
            build(node << 1 | 1, mid + 1, r, arr);
            pull(node);
        }

        void update(int idx, long val) {
            update(1, 0, n - 1, idx, val);
        }

        void update(int node, int l, int r, int idx, long val) {
            if (l == r) {
                sum[node] = val;
                pref[node] = Math.max(0L, val);
                suff[node] = Math.max(0L, val);
                best[node] = Math.max(0L, val);
                return;
            }
            int mid = (l + r) >>> 1;
            if (idx <= mid) update(node << 1, l, mid, idx, val);
            else update(node << 1 | 1, mid + 1, r, idx, val);
            pull(node);
        }

        void pull(int node) {
            int left = node << 1;
            int right = left | 1;
            sum[node] = sum[left] + sum[right];
            pref[node] = Math.max(pref[left], sum[left] + pref[right]);
            suff[node] = Math.max(suff[right], sum[right] + suff[left]);
            best[node] = Math.max(Math.max(best[left], best[right]), suff[left] + pref[right]);
        }

        long answer() {
            return best[1];
        }
    }

    static long[] X, Y, W;
    static int[] eu, ev, edx, edy;
    static int M;

    static int cmpSlope(int dy1, int dx1, int dy2, int dx2) {
        long lhs = (long) dy1 * dx2;
        long rhs = (long) dy2 * dx1;
        return Long.compare(lhs, rhs);
    }

    static void swapEvent(int i, int j) {
        int t;
        t = eu[i]; eu[i] = eu[j]; eu[j] = t;
        t = ev[i]; ev[i] = ev[j]; ev[j] = t;
        t = edx[i]; edx[i] = edx[j]; edx[j] = t;
        t = edy[i]; edy[i] = edy[j]; edy[j] = t;
    }

    static void sortEvents(int l, int r) {
        while (l < r) {
            int m = (l + r) >>> 1;

            if (cmpSlope(edy[l], edx[l], edy[m], edx[m]) > 0) swapEvent(l, m);
            if (cmpSlope(edy[l], edx[l], edy[r], edx[r]) > 0) swapEvent(l, r);
            if (cmpSlope(edy[m], edx[m], edy[r], edx[r]) > 0) swapEvent(m, r);

            int pDy = edy[m];
            int pDx = edx[m];

            int lt = l, i = l, gt = r;
            while (i <= gt) {
                int c = cmpSlope(edy[i], edx[i], pDy, pDx);
                if (c < 0) {
                    swapEvent(lt++, i++);
                } else if (c > 0) {
                    swapEvent(i, gt--);
                } else {
                    i++;
                }
            }

            if (lt - l < r - gt) {
                sortEvents(l, lt - 1);
                l = gt + 1;
            } else {
                sortEvents(gt + 1, r);
                r = lt - 1;
            }
        }
    }

    static void sortByPos(int[] arr, int l, int r, int[] pos) {
        while (l < r) {
            int i = l, j = r;
            int pivot = pos[arr[(l + r) >>> 1]];

            while (i <= j) {
                while (pos[arr[i]] < pivot) i++;
                while (pos[arr[j]] > pivot) j--;
                if (i <= j) {
                    int t = arr[i];
                    arr[i] = arr[j];
                    arr[j] = t;
                    i++;
                    j--;
                }
            }

            if (j - l < r - i) {
                if (l < j) sortByPos(arr, l, j, pos);
                l = i;
            } else {
                if (i < r) sortByPos(arr, i, r, pos);
                r = j;
            }
        }
    }

    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner();
        int N = fs.nextInt();

        Point[] pts = new Point[N];
        for (int i = 0; i < N; i++) {
            long x = fs.nextLong();
            long y = fs.nextLong();
            long w = fs.nextLong();
            pts[i] = new Point(x, y, w);
        }

        Arrays.sort(pts, (a, b) -> {
            if (a.x != b.x) return Long.compare(a.x, b.x);
            return Long.compare(a.y, b.y);
        });

        X = new long[N];
        Y = new long[N];
        W = new long[N];
        for (int i = 0; i < N; i++) {
            X[i] = pts[i].x;
            Y[i] = pts[i].y;
            W[i] = pts[i].w;
        }

        int totalPairs = N * (N - 1) / 2;
        eu = new int[totalPairs];
        ev = new int[totalPairs];
        edx = new int[totalPairs];
        edy = new int[totalPairs];
        M = 0;

        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                long dx = X[j] - X[i];
                if (dx == 0) continue; // 무한 기울기(수직선)는 시작/끝 상태와 같아서 생략 가능
                long dy = Y[j] - Y[i];

                eu[M] = i;
                ev[M] = j;
                edx[M] = (int) dx;
                edy[M] = (int) dy;
                M++;
            }
        }

        if (M > 1) sortEvents(0, M - 1);

        int[] order = new int[N];
        int[] pos = new int[N];
        long[] init = new long[N];
        for (int i = 0; i < N; i++) {
            order[i] = i;
            pos[i] = i;
            init[i] = W[i];
        }

        SegTree seg = new SegTree(init);
        long ans = seg.answer();

        boolean[] marked = new boolean[N];
        int[] touched = new int[N];

        int i = 0;
        while (i < M) {
            int j = i + 1;
            while (j < M && (long) edy[i] * edx[j] == (long) edy[j] * edx[i]) {
                j++;
            }

            int t = 0;
            for (int k = i; k < j; k++) {
                int a = eu[k];
                int b = ev[k];
                if (!marked[a]) {
                    marked[a] = true;
                    touched[t++] = a;
                }
                if (!marked[b]) {
                    marked[b] = true;
                    touched[t++] = b;
                }
            }

            if (t > 1) {
                sortByPos(touched, 0, t - 1, pos);
            }

            int dx0 = edx[i];
            int dy0 = edy[i];

            int idx = 0;
            while (idx < t) {
                int firstId = touched[idx];
                long lineKey = Y[firstId] * dx0 - X[firstId] * dy0;

                int leftPos = pos[firstId];
                int rightPos = leftPos;
                idx++;

                while (idx < t) {
                    int id = touched[idx];
                    long key = Y[id] * dx0 - X[id] * dy0;
                    if (key != lineKey) break;
                    rightPos = pos[id];
                    idx++;
                }

                int l = leftPos;
                int r = rightPos;
                while (l < r) {
                    int idL = order[l];
                    int idR = order[r];

                    order[l] = idR;
                    order[r] = idL;

                    pos[idL] = r;
                    pos[idR] = l;

                    seg.update(l, W[idR]);
                    seg.update(r, W[idL]);

                    l++;
                    r--;
                }
            }

            for (int k = 0; k < t; k++) {
                marked[touched[k]] = false;
            }

            ans = Math.max(ans, seg.answer());
            i = j;
        }

        System.out.println(ans);
    }
}
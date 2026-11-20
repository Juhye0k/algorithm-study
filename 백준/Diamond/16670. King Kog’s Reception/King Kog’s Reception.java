import java.io.*;
import java.util.*;

public class Main {
    static final long NEG = -(1L << 60);

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

        char nextChar() throws IOException {
            int c;
            do {
                c = read();
            } while (c <= ' ');
            return (char) c;
        }
    }

    static class SegmentTree {
        int n, size;
        long[] sum;
        long[] max;
        int[] coord; // compressed index -> actual time

        SegmentTree(int[] coord) {
            this.coord = coord;
            this.n = coord.length;
            size = 1;
            while (size < n) size <<= 1;

            sum = new long[size << 1];
            max = new long[size << 1];

            Arrays.fill(max, NEG);

            for (int i = 0; i < n; i++) {
                max[size + i] = coord[i]; // no knight at this time -> waiting base = time itself
            }

            for (int i = size - 1; i >= 1; i--) pull(i);
        }

        void pull(int node) {
            sum[node] = sum[node << 1] + sum[node << 1 | 1];
            max[node] = Math.max(max[node << 1 | 1], max[node << 1] + sum[node << 1 | 1]);
        }

        void add(int idx, int delta) {
            int node = size + idx;
            sum[node] += delta;
            max[node] += delta; // leaf max = actual_time + current_duration
            node >>= 1;
            while (node >= 1) {
                pull(node);
                node >>= 1;
            }
        }

        long queryPrefixMax(int rightExclusive) {
            long leftSum = 0, leftMax = NEG;
            long rightSum = 0, rightMax = NEG;

            int l = size;
            int r = size + rightExclusive;

            while (l < r) {
                if ((l & 1) == 1) {
                    // leftRes = merge(leftRes, tree[l])
                    leftMax = Math.max(max[l], leftMax + sum[l]);
                    leftSum += sum[l];
                    l++;
                }
                if ((r & 1) == 1) {
                    --r;
                    // rightRes = merge(tree[r], rightRes)
                    rightMax = Math.max(rightMax, max[r] + rightSum);
                    rightSum += sum[r];
                }
                l >>= 1;
                r >>= 1;
            }

            // merge(leftRes, rightRes)
            return Math.max(rightMax, leftMax + rightSum);
        }
    }

    static int lowerBound(int[] arr, int target) {
        int l = 0, r = arr.length;
        while (l < r) {
            int m = (l + r) >>> 1;
            if (arr[m] < target) l = m + 1;
            else r = m;
        }
        return l;
    }

    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner();
        int q = fs.nextInt();

        char[] type = new char[q + 1];
        int[] a = new int[q + 1];
        int[] b = new int[q + 1];

        int[] joinTime = new int[q + 1];
        int[] joinDur = new int[q + 1];
        int[] joinCompressed = new int[q + 1];

        int[] times = new int[q * 2 + 5];
        int timeCnt = 0;

        for (int i = 1; i <= q; i++) {
            char op = fs.nextChar();
            type[i] = op;

            if (op == '+') {
                int t = fs.nextInt();
                int d = fs.nextInt();
                a[i] = t;
                b[i] = d;
                joinTime[i] = t;
                joinDur[i] = d;
                times[timeCnt++] = t;
            } else if (op == '-') {
                int idx = fs.nextInt();
                a[i] = idx;
            } else { // '?'
                int t = fs.nextInt();
                a[i] = t;
                times[timeCnt++] = t;
            }
        }

        Arrays.sort(times, 0, timeCnt);

        int uniqueCnt = 0;
        for (int i = 0; i < timeCnt; i++) {
            if (i == 0 || times[i] != times[i - 1]) {
                times[uniqueCnt++] = times[i];
            }
        }

        int[] coord = Arrays.copyOf(times, uniqueCnt);
        SegmentTree seg = new SegmentTree(coord);

        StringBuilder sb = new StringBuilder();

        for (int i = 1; i <= q; i++) {
            if (type[i] == '+') {
                int idx = lowerBound(coord, joinTime[i]);
                joinCompressed[i] = idx;
                seg.add(idx, joinDur[i]);
            } else if (type[i] == '-') {
                int joinIdx = a[i];
                seg.add(joinCompressed[joinIdx], -joinDur[joinIdx]);
            } else {
                int t = a[i];
                int idx = lowerBound(coord, t);
                long finish = seg.queryPrefixMax(idx + 1);
                sb.append(finish - t).append('\n');
            }
        }

        System.out.print(sb);
    }
}
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

        int nextInt() throws IOException {
            int c;
            do {
                c = read();
            } while (c <= ' ' && c != -1);

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

    static boolean isNonBoring(int[] a) {
        int n = a.length;

        int[] prev = new int[n];
        int[] next = new int[n];

        HashMap<Integer, Integer> map = new HashMap<>((int) (n / 0.75f) + 1);
        for (int i = 0; i < n; i++) {
            Integer p = map.put(a[i], i);
            prev[i] = (p == null) ? -1 : p;
        }

        // next 계산
        map.clear();
        for (int i = n - 1; i >= 0; i--) {
            Integer p = map.put(a[i], i);
            next[i] = (p == null) ? n : p;
        }

        int[] leftStack = new int[n];
        int[] rightStack = new int[n];
        int top = 0;
        leftStack[0] = 0;
        rightStack[0] = n - 1;

        while (top >= 0) {
            int l = leftStack[top];
            int r = rightStack[top];
            top--;

            if (l >= r) continue; 

            boolean found = false;
            for (int x = l, y = r; x <= y; x++, y--) {
                if (prev[x] < l && next[x] > r) {
                    if (l <= x - 1) {
                        leftStack[++top] = l;
                        rightStack[top] = x - 1;
                    }
                    if (x + 1 <= r) {
                        leftStack[++top] = x + 1;
                        rightStack[top] = r;
                    }
                    found = true;
                    break;
                }

                if (x != y && prev[y] < l && next[y] > r) {
                    if (l <= y - 1) {
                        leftStack[++top] = l;
                        rightStack[top] = y - 1;
                    }
                    if (y + 1 <= r) {
                        leftStack[++top] = y + 1;
                        rightStack[top] = r;
                    }
                    found = true;
                    break;
                }
            }

            if (!found) return false;
        }

        return true;
    }

    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner();
        StringBuilder sb = new StringBuilder();

        int T = fs.nextInt();
        while (T-- > 0) {
            int n = fs.nextInt();
            int[] a = new int[n];
            for (int i = 0; i < n; i++) {
                a[i] = fs.nextInt();
            }

            sb.append(isNonBoring(a) ? "non-boring" : "boring").append('\n');
        }

        System.out.print(sb);
    }
}
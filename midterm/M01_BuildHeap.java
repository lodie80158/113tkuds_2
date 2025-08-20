package midterm;
import java.io.*;

public class M01_BuildHeap {
    static class FastScanner {
        private final InputStream in;
        private final byte[] buffer = new byte[1 << 16];
        private int ptr = 0, len = 0;
        FastScanner(InputStream is) { in = is; }
        private int read() throws IOException {
            if (ptr >= len) {
                len = in.read(buffer);
                ptr = 0;
                if (len <= 0) return -1;
            }
            return buffer[ptr++];
        }
        String next() throws IOException {
            StringBuilder sb = new StringBuilder();
            int c;
            while ((c = read()) != -1 && c <= ' ') {}
            if (c == -1) return null;
            do {
                sb.append((char) c);
                c = read();
            } while (c != -1 && c > ' ');
            return sb.toString();
        }
        int nextInt() throws IOException { return Integer.parseInt(next()); }
    }

    static boolean better(int a, int b, boolean isMax) { return isMax ? a > b : a < b; }

    static void heapifyDown(int[] a, int i, int n, boolean isMax) {
        while (true) {
            int left = i * 2 + 1, right = i * 2 + 2, best = i;
            if (left < n && better(a[left], a[best], isMax)) best = left;
            if (right < n && better(a[right], a[best], isMax)) best = right;
            if (best == i) break;
            int tmp = a[i]; a[i] = a[best]; a[best] = tmp;
            i = best;
        }
    }

    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner(System.in);
        String type = fs.next();
        int n = Integer.parseInt(fs.next());
        int[] a = new int[n];
        for (int i = 0; i < n; i++) a[i] = fs.nextInt();

        boolean isMax = "max".equalsIgnoreCase(type);
        for (int i = n / 2 - 1; i >= 0; i--) heapifyDown(a, i, n, isMax);

        StringBuilder out = new StringBuilder();
        for (int i = 0; i < n; i++) {
            if (i > 0) out.append(' ');
            out.append(a[i]);
        }
        System.out.println(out);
    }
}

/*
 * Time Complexity: O(n)
 * 說明：自底向上自 (n/2)-1 迭代到 0 做 heapifyDown；深層節點多但每個可下沉高度小，總成本為 Σ (n/2^{h})·O(h) = O(n)。
 */

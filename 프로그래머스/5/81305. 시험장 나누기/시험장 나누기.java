class Solution {
    public int solution(int k, int[] num, int[][] links) {
        int n = num.length;

        // 1. 이분 탐색 범위와 루트 찾기
        int low = 0;
        int high = 0;
        boolean[] isChild = new boolean[n];

        for (int i = 0; i < n; i++) {
            low = Math.max(low, num[i]);
            high += num[i];

            for (int child : links[i]) {
                if (child != -1) {
                    isChild[child] = true;
                }
            }
        }

        int root = 0;
        for (int i = 0; i < n; i++) {
            if (!isChild[i]) {
                root = i;
                break;
            }
        }

        // 2. 부모가 자식보다 먼저 나오도록 노드 번호 기록
        int[] order = new int[n];
        order[0] = root;
        int size = 1;

        for (int i = 0; i < n; i++) {
            int node = order[i];

            for (int child : links[node]) {
                if (child != -1) {
                    order[size++] = child;
                }
            }
        }

        // remain[node]: 부모와 합칠 수 있도록 남겨 둔 그룹의 인원
        int[] remain = new int[n];

        // 3. 가능한 최대 그룹 인원의 최솟값 찾기
        while (low < high) {
            int mid = low + (high - low) / 2;

            if (canDivide(k, mid, num, links, order, remain)) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }

        return low;
    }

    private boolean canDivide(
            int k,
            int limit,
            int[] num,
            int[][] links,
            int[] order,
            int[] remain
    ) {
        int groups = 1;

        // 역순으로 읽으면 자식을 처리한 뒤 부모를 처리하게 됨
        for (int i = order.length - 1; i >= 0; i--) {
            int node = order[i];
            int left = links[node][0];
            int right = links[node][1];

            int w = num[node];
            int L = (left == -1) ? 0 : remain[left];
            int R = (right == -1) ? 0 : remain[right];

            if (w + L + R <= limit) {
                // 양쪽 모두 연결 유지
                remain[node] = w + L + R;

            } else if (w + Math.min(L, R) <= limit) {
                // 큰 쪽 연결을 끊고, 작은 쪽만 연결 유지
                groups++;
                remain[node] = w + Math.min(L, R);

            } else {
                // 양쪽 연결을 모두 끊음
                groups += 2;
                remain[node] = w;
            }

            if (groups > k) {
                return false;
            }
        }

        return true;
    }
}
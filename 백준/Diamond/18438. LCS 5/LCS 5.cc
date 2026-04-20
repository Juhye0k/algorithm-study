#include <cstdio>
#include <cstring>
#include <algorithm>

using namespace std;

const int MAXN = 7001;

char A[MAXN], B[MAXN];
int lenA, lenB;

short prevRow[MAXN], currRow[MAXN];
short scoreL[14][MAXN]; // 재귀 깊이 log2(7000) ≈ 13

char result[MAXN];
int resultLen = 0;

// A[aS..aE) 와 B[bS..bE) 의 LCS DP 마지막 행을 prevRow에 저장
// reverse=true 면 양쪽 문자열을 뒤에서부터 본 결과
void lcsRow(int aS, int aE, int bS, int bE, bool reverse) {
    int aLen = aE - aS;
    int bLen = bE - bS;

    for (int j = 0; j <= bLen; j++) prevRow[j] = 0;

    for (int i = 1; i <= aLen; i++) {
        char ac = reverse ? A[aE - i] : A[aS + i - 1];
        currRow[0] = 0;
        for (int j = 1; j <= bLen; j++) {
            char bc = reverse ? B[bE - j] : B[bS + j - 1];
            if (ac == bc) {
                currRow[j] = prevRow[j - 1] + 1;
            } else {
                currRow[j] = max(prevRow[j], currRow[j - 1]);
            }
        }
        // swap prev, curr
        for (int j = 0; j <= bLen; j++) prevRow[j] = currRow[j];
    }
}

void hirschberg(int aStart, int aEnd, int bStart, int bEnd, int level) {
    int aLen = aEnd - aStart;
    int bLen = bEnd - bStart;

    if (aLen == 0 || bLen == 0) return;

    if (aLen == 1) {
        char c = A[aStart];
        for (int i = bStart; i < bEnd; i++) {
            if (B[i] == c) {
                result[resultLen++] = c;
                return;
            }
        }
        return;
    }

    int aMid = aStart + aLen / 2;

    // 정방향 LCS
    lcsRow(aStart, aMid, bStart, bEnd, false);
    for (int k = 0; k <= bLen; k++) scoreL[level][k] = prevRow[k];

    // 역방향 LCS
    lcsRow(aMid, aEnd, bStart, bEnd, true);

    int bMid = bStart;
    int best = -1;
    for (int k = 0; k <= bLen; k++) {
        int sum = scoreL[level][k] + prevRow[bLen - k];
        if (sum > best) {
            best = sum;
            bMid = bStart + k;
        }
    }

    hirschberg(aStart, aMid, bStart, bMid, level + 1);
    hirschberg(aMid, aEnd, bMid, bEnd, level + 1);
}

int main() {
    scanf("%s", A);
    scanf("%s", B);
    lenA = strlen(A);
    lenB = strlen(B);

    hirschberg(0, lenA, 0, lenB, 0);

    result[resultLen] = '\0';
    printf("%d\n%s\n", resultLen, result);

    return 0;
}
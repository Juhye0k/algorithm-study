#include <string>
#include <algorithm>

using namespace std;

int solution(string name) {
    int answer = 0;
    int length = name.length();

    // 오른쪽으로만 이동하는 경우
    int minCursorMove = length - 1;

    for (int i = 0; i < length; i++) {
        // 1. 상하 이동 횟수
        int upMove = name[i] - 'A';
        int downMove = 'Z' - name[i] + 1;

        answer += min(upMove, downMove);

        // 2. 현재 위치 다음부터 연속된 A 구간 찾기
        int next = i + 1;

        while (next < length && name[next] == 'A') {
            next++;
        }

        // 오른쪽으로 갔다가 왼쪽으로 돌아가는 경우
        int moveRightThenLeft = i * 2 + (length - next);

        // 왼쪽으로 갔다가 오른쪽으로 돌아가는 경우
        int moveLeftThenRight = (length - next) * 2 + i;

        minCursorMove = min(
            minCursorMove,
            min(moveRightThenLeft, moveLeftThenRight)
        );
    }

    return answer + minCursorMove;
}
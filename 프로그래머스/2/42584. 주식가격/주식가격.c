#include <stdio.h>
#include <stdbool.h>
#include <stdlib.h>

// prices_len은 배열 prices의 길이입니다.
int* solution(int prices[], size_t prices_len) {
    int* answer = (int*)calloc(prices_len, sizeof(int));
    int* stack = (int*)malloc(sizeof(int) * prices_len);

    int top = -1;

    for (int current = 0; current < (int)prices_len; current++) {

        // 현재 가격이 이전 가격보다 낮아졌다면
        // 스택에 있는 이전 시점들의 유지 시간을 계산한다.
        while (top >= 0 && prices[stack[top]] > prices[current]) {
            int previous = stack[top--];

            answer[previous] = current - previous;
        }

        // 아직 가격이 떨어지지 않은 시점의 인덱스를 저장
        stack[++top] = current;
    }

    // 끝까지 가격이 떨어지지 않은 시점들 처리
    while (top >= 0) {
        int previous = stack[top--];

        answer[previous] = (int)prices_len - 1 - previous;
    }

    free(stack);

    return answer;
}
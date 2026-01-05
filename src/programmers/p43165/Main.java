package programmers.p43165;

import java.util.*;

/**
 * DFS 로 들어가면서 빠르게 나올 수 있는 로직이 있으면 좋겠다.
 * +,- 이므로 이진트리가 가능하다
 * 빠져나오는? 최대값, 최솟겂 더했을 때 target이 밖이면 끝낸다.
 *
 * DFS 에서 어떻게 재귀를 돌리는가? -> 다음 로직이 해야할 일을 생각하자
 *
 * Eearly return d을 넣어서 돌리니까 오히려 더 시간이 오래걸린다. -> calPossibleVal 에서 O(N)의 시간이 더 발생
 * 해당 부분을 O(1) 로 고정할 수 있는 방법이 존재
 */
public class Main {
    private int answer = 0;

    private int[] suffixSum;

    public int solution(int[] numbers, int target) {
        // Precompute suffix sums: O(n)
        suffixSum = new int[numbers.length + 1];
        for (int i = numbers.length - 1; i >= 0; i--) {
            suffixSum[i] = suffixSum[i + 1] + numbers[i];
        }

        dfs(numbers, 0, 0, target);
        return answer;
    }

    private void dfs(int[] numbers, int depth, int sum, int target) {
        if (depth == numbers.length) {
            if (sum == target) {
                answer++;
            }
            return;
        }
        if (Math.abs(target - sum) > suffixSum[depth]) {  // O(1) lookup
            return;
        }
        dfs(numbers, depth + 1, sum + numbers[depth], target);
        dfs(numbers, depth + 1, sum - numbers[depth], target);
    }

    public static void main(String[] args) {
        int solution1 = new Main().solution(new int[]{4, 1, 2, 1}, 2);
        System.out.println("solution1 = " + solution1);

    }
}

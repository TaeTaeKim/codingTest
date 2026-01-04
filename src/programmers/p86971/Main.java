package programmers.p86971;


import java.util.*;
/**
 * 송전망 나누기 문제
 *
 * 생각(틀림): 각 송전선에서 hub가 되는 애를 찾아야한다.
 * 첫번째 허브에서 두번째 허브를 잘라야 최대한 비슷해진다.
 *
 * 생각 2 : 반례를 발견 집합으로 풀어야한다. 여러 곳을 끝었을 때 부모 노드까지 개수들을 비교
 * 유니온 파인드 하면 좋을듯
 */
public class Main {

        Map<Integer, Set<Integer>> adj = new HashMap<>();
    public int solution(int n, int[][] wires) {
        int answer = n;

        Arrays.stream(wires).forEach(wire -> {
            // wire[0] 노드가 없으면 HashSet을 만들고 wire[1] 추가
            adj.computeIfAbsent(wire[0], k -> new HashSet<>()).add(wire[1]);
            // wire[1] 노드가 없으면 HashSet을 만들고 wire[0] 추가
            adj.computeIfAbsent(wire[1], k -> new HashSet<>()).add(wire[0]);
        });

        // 2. 모든 전선을 하나씩 끊어보며 테스트
        for (int[] wire : wires) {
            int v1 = wire[0];
            int v2 = wire[1];

            // [Backtracking] 전선 끊기
            adj.get(v1).remove(v2);
            adj.get(v2).remove(v1);

            // 한쪽 네트워크의 송전탑 개수 세기 (DFS)
            int count = dfs(v1, new boolean[n + 1]);

            // 두 전력망의 차이 계산 (|한쪽 - 다른쪽|)
            int diff = Math.abs(count - (n - count));
            answer = Math.min(answer, diff);

            // [Backtracking] 다음 테스트를 위해 전선 다시 연결
            adj.get(v1).add(v2);
            adj.get(v2).add(v1);
        }

        return answer;
    }

    // 현재 노드와 연결된 모든 노드를 탐색하며 개수를 반환
    private int dfs(int curr, boolean[] visited) {
        visited[curr] = true;
        int count = 1;

        // Map에서 현재 노드의 이웃(Set)을 가져와 탐색
        for (int next : adj.get(curr)) {
            if (!visited[next]) {
                count += dfs(next, visited);
            }
        }
        return count;
    }

    public static void main(String[] args) {
        int[][] test = {{1, 3}, {2, 3}, {3, 4}, {4, 5}, {4, 6}, {4, 7}, {7, 8}, {7, 9}};
        int[][] test2 = {{1,2},{2,3},{3,4}};
        int[][] test3 = {{1, 2}, {2, 7}, {3, 7}, {3, 4}, {4, 5}, {6, 7}};
        int solution1 = new Main().solution(9, test);
        int solution2= new Main().solution(4, test2);
        int solution3 = new Main().solution(7, test3);
        System.out.println("solution = " + solution1);
        System.out.println("solution = " + solution2);
        System.out.println("solution = " + solution3);


    }
}

package programmers.p43162;

import java.util.*;

/**
 * 네트워크 - 간선 정보가 주어질 때 독립된 네트워크의 개수 구하기
 *
 * 집합으로 풀면 쉬울 것으로 생각 -> 모든 컴퓨터를 돌면서 부모를 정하면 된다.
 *
 * 문제 : 유니온, 파인드 알고리즘에서 각각 잘못으로 인해 실제 테스트에서 값이 이상하게 나오는 문제
 * 유니온 : 재대로 합치지 않아서 몇 반례에서 재대로 트리가 구성이 안되는 현상
 * 파인드 : 재귀에서 idx를 잘 못 설정하여 stackOverflow 가 발생
 */
public class Main {

    public int solution(int n, int[][] computers) {
        int[] parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (computers[i][j] == 1) {
                    union(parent, i, j);
                }
            }
        }

        int count = 0;
        for (int i = 0; i < n; i++) {
            if (find(parent, i) == i) count++;
        }
        return count;
    }

    private int find(int[] parent, int x) {
        if (parent[x] != x) {
            parent[x] = find(parent, parent[x]);
        }
        return parent[x];
    }

    private void union(int[] parent, int x, int y) {
        int rootX = find(parent, x);
        int rootY = find(parent, y);
        if (rootX != rootY) {
            parent[rootX] = rootY;
        }
    }

    public static void main(String[] args) {
        int[][] matrix = {
                {1, 1, 0, 1},
                {1, 1, 1, 0},
                {0, 1, 1, 0},
                {1, 0, 0, 1}
        };
        int solution = new Main().solution(matrix.length, matrix);
        System.out.println(solution);

    }
}

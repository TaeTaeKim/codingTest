package programmers.p42839;

import java.util.*;

/**
 * 7 자리수 최대 -> 7P1 + 7P2 + 7P3 .. 7P7
 * 소수 : 1을 제외 및 나눌 수 있는 수가 1과 자기 자신 뿐일 때
 *
 * 생각 1 : 모든 수 조합을 어떻게 할 것인가? Tree로 구현? 이후 isprime 돌린다.
 * 배열에서 DFS하는 방법을 하는게 핵심 + backtracking 도 할 줄 알아야하는 문제
 */
public class Main {
    private Set<Integer> primeSet = new HashSet<>();
    boolean[] visited;

    public int solution(String numbers) {
        int answer = 0;
        // 이미 판별된 수인지를 체크하기 위한 자료구조
        visited = new boolean[numbers.length()];

        dfs(numbers, "", 0);
        return primeSet.size();

    }
    private void dfs(String numbers, String current, int depth) {

        if(!current.equals("")){
            int num = Integer.parseInt(current);
            if (isPrime(num)) {
                primeSet.add(num);
            }
        }
        if(depth == numbers.length()) {
            return;
        }

        for(int i = 0; i < numbers.length(); i++) {
            if(!visited[i]) { // 방문하지 않았다면
                visited[i] = true;
                dfs(numbers, current+numbers.charAt(i), depth + 1);
                visited[i] = false; // 백 트랙킹 (방문해제)
            }

        }

    }

    private boolean isPrime(int number) {
        if (number == 0 || number == 1) {
            return false;
        }else if(number == 2) {
            return true;
        }else{
            // sqrt 를 이용한 소수 판별
            for(int i =2; i <= Math.sqrt(number); i++) {
                if(number % i == 0) {
                    return false;
                }
            }
            return true;
        }
    }


    public static void main(String[] args) {



        int solution = new Main().solution("011");
        System.out.println("solution = " + solution);

    }
}

package programmers.p84512;

import java.util.List;

/**
 * 모음 사전 숫자 탐색
 *
 * 접근 : 규칙을 찾자 하나의 글자에 올 수 있는 경우의 수 781가지 1 1*5 1*5제곱 1*5세제곱 1*5 네제곱
 * 이후 내 앞에 올 수 있는 개수를 세면 나온다.
 *
 * 각 자릿수별 자기보다 위에 있는 숫자는 고정
 */
public class Main {
    // 자기 인덱스가 자기보다 위에 있는 숫자의 개수
    private static final List<Character> moeum = List.of('A', 'E', 'I', 'O', 'U');
    private static final int[] prevCount = {781, 156, 31, 6, 1};

    public int solution(String word) {

        int answer = 0;
        // 첫 자릿수부터 순회
        for (int location = 0; location < word.length(); location++) {
            int prevMoeum = moeum.indexOf(word.charAt(location));
            answer += prevMoeum*prevCount[location];
        }

        answer+=word.length();
        return answer;

    }

    public static void main(String[] args) {
        String testWord = "EIO";
        Main solution = new Main();
        System.out.println(solution.solution(testWord));

    }
}

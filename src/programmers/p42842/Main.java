package programmers.p42842;

import java.util.Arrays;

/**
 * 배수를 찾고 brown 이 덮을 수 있는 개수는 공식이 있어서 해당 방법으로 풀이
 * sqrt를 써서 배수를 빠르게 찾도록 설정
 */
public class Main {

    public int[] solution(int brown, int yellow) {
        // yellow 의 배수를 찾는다.
        // 배수를 기반으로 brown 과 매치되는 지 찾는다.
        int[] answer = new int[2];

        for (int i = 1; i <= Math.sqrt(yellow); i++) {
            // 배수일때
            if (yellow % i == 0) {
                int width = Math.max(i, yellow / i);
                int height = Math.min(i, yellow / i);
                if (calBrownSize(width, height) == brown) {
                    answer[0] = width + 2;
                    answer[1] = height + 2;
                }


            }
        }
        return answer;
    }
    private int calBrownSize(int width, int height) {
        return (width + 2) * 2 + 2 * height;
    }

    public static void main(String[] args) {
        Main main = new Main();
        int[] answer = main.solution(24,24);
        System.out.println(Arrays.toString(answer));

    }
}

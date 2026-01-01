
import java.util.*;
import java.util.stream.*;

/**
 * 첫 생각 : 각 자릿수 비교를 위한 재귀함수를 생각했다.
 * 매우 어렵고 반례가 있었다 54, 545 /// 56 565 이 케이스 생각하려면 힘들어졌는데
 *
 * 단순한 방법 두 수 합쳐봤을때 더 큰 수가 되면 됐던것
 *
 * 배운점 Comparator 에 대해 더 깊은 사용법을 확실히 알았다. -> 코틀린도 결국 BiFuntional이다.
 */
public class Main {
    public String solution(int[] numbers) {
        String collect = Arrays.stream(numbers)
                .mapToObj(String::valueOf)
                .sorted(this::comparePositionNumber)
                .collect(Collectors.joining());
        if (collect.startsWith("0")) {
            return "0";
        }
        return  collect;
    }

    private int comparePositionNumber(String s1, String s2) {
        if (s1.equals(s2)) {
            return 0;
        }else{
            int s1s2 = Integer.parseInt(s1+s2);
            int s2s1 = Integer.parseInt(s2+s1);
            return s2s1 - s1s2;
        }
    }

    public static void main(String[] args) throws Exception {
        int[] test = {0,0,0,0};
        String solution = new Main().solution(test);
        System.out.println("solution = " + solution);
    }

}
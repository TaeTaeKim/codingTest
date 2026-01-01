
import java.util.*;
import java.util.stream.*;

public class Main {
    public int solution(int[] scoville, int K) {

        // 우선순위 큐를 이용해 하나씪 꺼내고 넣으면서 연산
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int scov : scoville) {
            pq.add(scov);
        }

        int mixCount = 0;
        // 최소 스코빌이 K를 넘을때까지 반복
        while(pq.peek() < K) {
            // 한개가 남았어도 넘지 못했으면 못만드는 것
            if (pq.size() == 1) {
                return -1;
            }
            Integer minScov = pq.poll();
            Integer micSecScov = pq.poll();
            int mix = mix(minScov, micSecScov);
            pq.offer(mix);
            mixCount++;

        }
        return mixCount;
    }

    private int mix(int first, int second) {
        return first + (second*2);
    }

    public static void main(String[] args) throws Exception {

        int[] scoville = {1, 2, 3, 9, 10, 12};
        new Main().solution(scoville, 7);
    }

}
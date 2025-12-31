
import java.util.*;
import java.util.stream.*;

public class Main {
    public int[] solution(int[] progresses, int[] speeds) {
        Queue<Integer> daysQueue = new LinkedList<>();
        for (int i = 0; i < progresses.length; i++) {
            int daysNeeded = getLeftDay(progresses[i], speeds[i]);
            daysQueue.add(daysNeeded);
        }

        List<Integer> resultList = new ArrayList<>();
        

        while (!daysQueue.isEmpty()) {

            int deployDay = daysQueue.poll();
            int featuresDeployed = 1;


            while (!daysQueue.isEmpty() && daysQueue.peek() <= deployDay) {
                daysQueue.poll();
                featuresDeployed++;
            }
            resultList.add(featuresDeployed);
        }

        return resultList.stream().mapToInt(Integer::intValue).toArray();
    }

    private static int getLeftDay(int progress, int speed) {
        int remainingProgress = 100 - progress;
        return (remainingProgress + speed - 1) / speed;
    }

    public static void main(String[] args) throws Exception {
        int[] progresses = {93, 30, 55};
        int[] speeds = {1, 30, 5};
        int[] solution = new Main().solution(progresses, speeds);
        // Print the result to the console. Expected: [2, 1]
        System.out.println("Result: " + Arrays.toString(solution));

        // Example from problem description that would yield [1, 3, 2]
        int[] progresses2 = {95, 90, 99, 99, 80, 99};
        int[] speeds2 = {1, 1, 1, 1, 1, 1};
        int[] solution2 = new Main().solution(progresses2, speeds2);
        System.out.println("Result 2: " + Arrays.toString(solution2));
    }

}
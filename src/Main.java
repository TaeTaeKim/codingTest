
import java.util.*;
import java.util.stream.*;

public class Main {
    public int solution(int[][] jobs) {
        List<Job>  jobList = new ArrayList<>();
        for (int i = 0; i < jobs.length; i++) {
            jobList.add(new Job(i, jobs[i][0],  jobs[i][1]));
        }

        TreeMap<Integer, List<Job>> jobByRequestedTime = jobList.stream()
                .collect(Collectors.groupingBy(Job::getRequested,TreeMap::new, Collectors.toList()));

        // 소요시간, 요청시간, 아이디 오름차순 정렬된 프로세스 레디 큐
        PriorityQueue<Job> readyJobQueue = new PriorityQueue<>(
                Comparator.comparing(Job::getProcessTime)
                        .thenComparing(Job::getRequested)
                        .thenComparing(Job::getId)
        );

        // 각 job 이 응답된 소요시간
        List<Integer> responseTime = new ArrayList<>();
        // 현재시간
        int nowTime = 0; // 현재 시점
        int fromTime = 0; // 작업큐에 넣어야하는 시점
        while (responseTime.size() != jobList.size()) {
            for(int i = fromTime; i<=nowTime; i++){
                if(jobByRequestedTime.containsKey(i)){
                    readyJobQueue.addAll(jobByRequestedTime.get(i));
                }
            }
            fromTime = nowTime+1; // 다음 시점부터 저장이 되지 않는다.
            // 작업이 없을 수가 있다. -> 있는 시점으로 가야한다.
            if (readyJobQueue.isEmpty()) {
                int i = jobByRequestedTime.higherKey(nowTime).intValue();
                fromTime = i;
                nowTime = i;
                continue;
            }
            Job jobToRun = readyJobQueue.poll();
            // 현재 작업 시간을 더한다.
            nowTime += jobToRun.getProcessTime();
            responseTime.add(jobToRun.responseTime(nowTime));
        }


        return responseTime.stream().mapToInt(Integer::intValue).sum() / responseTime.size();
    }

    private static class Job {
        private final int id;
        private final int requested;
        private final int processTime;

        public int getId() {
            return id;
        }

        public int getRequested() {
            return requested;
        }

        public int getProcessTime() {
            return processTime;
        }

        private int finished;

        public Job(int id, int requested, int processTime) {
            this.id = id;
            this.requested = requested;
            this.processTime = processTime;
        }


        public int responseTime(int time) {
            return time - requested;
        }
    }

    public static void main(String[] args) throws Exception {
        int[][] jobs = {{0, 10}, {0, 1}, {100, 1}};
        int solution = new Main().solution(jobs);
        System.out.println("solution = " + solution);
    }

}
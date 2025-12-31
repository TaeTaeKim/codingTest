
import java.util.*;
import java.util.stream.*;

public class Main {
    public int solution(int[] priorities, int location) {
        ArrayDeque<Process> processes = new ArrayDeque<>();
        for(int i = 0; i < priorities.length; i++){
            processes.addLast(new Process(i, priorities[i]));
        }

        int runCount = 0;
        // location 과 일치하는 값이 나올때
        while(true){
            Process max = Collections.max(processes, Comparator.comparing(Process::getPriority));
            Process first = processes.getFirst();
            if(first.getPriority() >= max.getPriority()){
                processes.removeFirst();
                runCount++;
                if (first.getId() == location) {
                    break;
                }
            }else{
                Process poll = processes.poll();
                processes.addLast(poll);
            }
        }
        System.out.println("runCount = " + runCount);
        return runCount;
    }
    private static class Process{
        private int id;
        private int priority;

        public Process(int id, int priority) {
            this.id = id;
            this.priority = priority;
        }

        public int getId() {
            return id;
        }

        public int getPriority() {
            return priority;
        }
    }


    public static void main(String[] args) throws Exception {
        int[] processes = {1, 1, 9, 1, 1, 1};
        int location = 0;
        new Main().solution(processes, location);
    }

}
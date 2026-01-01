
import java.util.*;
import java.util.stream.*;

public class Main {

    private static int timeTook = 0;
    private static int truckIdx= 0;
    private static ArrayDeque<Truck> truckOnBridge =  new ArrayDeque<>();

    public int solution(int bridge_length, int weight, int[] truck_weights) {



        // 트럭이 다 지나가면 끝낸다.
        while (truckIdx < truck_weights.length || !truckOnBridge.isEmpty()) {
            timeTook ++ ;
            truckOnBridge.forEach(Truck::timeGone);
            if(!truckOnBridge.isEmpty()&&truckOnBridge.getFirst().onBridge > bridge_length){
                truckOnBridge.removeFirst();
            }
            if(truckIdx < truck_weights.length){
                // 올라갈 트럭
                Truck truck = new Truck(truck_weights[truckIdx]);
                if (canBoard(truck, bridge_length, weight)) {
                    truckOnBridge.addLast(truck);
                    truck.timeGone();
                    truckIdx++;
                }
            }
        }
        return timeTook;
    }

    private boolean canBoard(Truck truck, int bridgeLength, int bridgeWeight) {
        int onBridgeWeight = truckOnBridge.stream().mapToInt(Truck::getWeight).sum();
        if (onBridgeWeight + truck.weight > bridgeWeight) {
            return false;
        }
        if(truckOnBridge.size() + 1 > bridgeLength) {
            return false;
        }
        return true;
    }
    private static class Truck{
        private final int weight;
        private int onBridge;
        public Truck(int weight){
            this.weight = weight;
            this.onBridge = 0;
        }

        public void timeGone() {
            onBridge++;
        }

        public int getWeight() {
            return weight;
        }
    }


    public static void main(String[] args) throws Exception {
        int bridge_length = 100;
        int weight =100;
        int[] truck_weights = {10,10,10,10,10,10,10,10,10,10};
        int solution = new Main().solution(bridge_length, weight, truck_weights);
        System.out.println("solution = " + solution);
    }

}
import java.util.*;

public class HeapDemo {
    public static void main(String[] args) {
        Comparator<Integer> comparator = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                if(o1 < o2){
                    return -1;
                }
                else if(o1 > o2){
                    return 1;
                }
                return 0;
            }
        };
        Heap heap = new Heap<>(comparator);
        Random rand = new Random();
        for(int x = 0; x < 20; x++){    //adding 20 random integers
            int randomInt = rand.nextInt(100);
            System.out.print(randomInt + " ");
            heap.add(randomInt);
        }
        System.out.println("\n");
        for(int x=0; x<20;x++){
            System.out.print(heap.remove() + " ");
        }

    }
}

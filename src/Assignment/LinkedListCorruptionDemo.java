package Assignment;
import java.util.LinkedList;
public class LinkedListCorruptionDemo {
    public static void main(String[] args) {
        LinkedList<Integer> list = new LinkedList<>();

        Runnable addTask = () -> {
            for (int i = 0; i < 1000; i++) {
                list.add(i);
            }
        };

        Runnable removeTask = () -> {
            for (int i = 0; i < 1000; i++) {
                if (!list.isEmpty()) {
                    list.removeFirst();
                }
            }
        };

        Thread thread1 = new Thread(addTask);
        Thread thread2 = new Thread(removeTask);

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // At this point, the list may be corrupted due to concurrent access.
        System.out.println("List size: " + list.size());
    }
}

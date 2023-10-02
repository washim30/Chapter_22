import java.util.concurrent.ConcurrentLinkedDeque;

public class ConcurrentStack<T> {
    private ConcurrentLinkedDeque<T> stack = new ConcurrentLinkedDeque<>();

    // Push an item onto the stack.
    public void push(T item) {
        stack.push(item);
    }

    // Pop an item from the stack. Returns null if the stack is empty.
    public T pop() {
        return stack.poll();
    }

    // Check if the stack is empty.
    public boolean isEmpty() {
        return stack.isEmpty();
    }

    // Get the size of the stack.
    public int size() {
        return stack.size();
    }

    public static void main(String[] args) {
        ConcurrentStack<Integer> stack = new ConcurrentStack<>();

        // Push some elements onto the stack from multiple threads
        Runnable pushTask = () -> {
            for (int i = 1; i <= 10; i++) {
                stack.push(i);
                System.out.println("Pushed: " + i);
            }
        };

        // Pop elements from the stack from multiple threads
        Runnable popTask = () -> {
            while (!stack.isEmpty()) {
                Integer item = stack.pop();
                if (item != null) {
                    System.out.println("Popped: " + item);
                }
            }
        };

        Thread thread1 = new Thread(pushTask);
        Thread thread2 = new Thread(popTask);

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

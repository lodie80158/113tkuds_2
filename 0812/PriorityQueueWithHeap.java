import java.util.*;

public class PriorityQueueWithHeap {
    private static class Task {
        String name;
        int priority;
        long timestamp;
        Task(String name, int priority, long timestamp) {
            this.name = name;
            this.priority = priority;
            this.timestamp = timestamp;
        }
    }

    private PriorityQueue<Task> queue;
    private long counter = 0;

    public PriorityQueueWithHeap() {
        queue = new PriorityQueue<>((a, b) -> {
            if (b.priority != a.priority) return b.priority - a.priority;
            return Long.compare(a.timestamp, b.timestamp);
        });
    }

    public void addTask(String name, int priority) {
        queue.add(new Task(name, priority, counter++));
    }

    public String executeNext() {
        return queue.isEmpty() ? null : queue.poll().name;
    }

    public String peek() {
        return queue.isEmpty() ? null : queue.peek().name;
    }

    public void changePriority(String name, int newPriority) {
        List<Task> temp = new ArrayList<>();
        while (!queue.isEmpty()) {
            Task t = queue.poll();
            if (t.name.equals(name)) t.priority = newPriority;
            temp.add(t);
        }
        queue.addAll(temp);
    }

    public static void main(String[] args) {
        PriorityQueueWithHeap pq = new PriorityQueueWithHeap();
        pq.addTask("備份", 1);
        pq.addTask("緊急修復", 5);
        pq.addTask("更新", 3);
        System.out.println(pq.executeNext());
        System.out.println(pq.executeNext());
        System.out.println(pq.executeNext());
    }
}

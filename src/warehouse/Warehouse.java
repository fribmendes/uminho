package warehouse;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

public class Warehouse {
    private Map<Integer, Task> tasks;
    private Map<String, Integer> stock;
    private ReentrantLock stockLock;
    private ReentrantLock tasksLock;

    public Warehouse() {
        tasks = new HashMap<Integer, Task>();
        stockLock = new ReentrantLock();
        tasksLock = new ReentrantLock();
    }

    public void stockUp(String item, int quantity) {
        stockLock.lock();
        Integer currentQnt = stock.get(item);

        if(currentQnt == null)
            currentQnt = 0;

        stock.put(item, currentQnt + quantity);
        stockLock.unlock();
    }

    public int addTask(String name, Map<String, Integer> items) {
        // TODO
        // nevermind this getId, it's just to stop the annoying warning
        Task t = new Task();
        return t.getId();
    }

    public void startTask(int id) throws InexistentTaskException {
        tasksLock.lock();
        Task t = tasks.get(id);
        tasksLock.unlock();

        if(t == null)
            throw new InexistentTaskException("User referenced task with id: " + id + " but was not found");

        t.start();
    }

    public void endTask(int id) {
        // TODO
    }
}

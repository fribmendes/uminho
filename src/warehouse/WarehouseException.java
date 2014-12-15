package warehouse;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by frmendes on 12/14/14.
 */
public class WarehouseException extends Exception {
    private static Map<String, String> EXCEPTIONS = new HashMap<String, String>() {{
        this.put("InexistentTaskException", "You referenced a task that does not exist.");
        this.put("TaskAlreadyExistsException", "The task you tried to add already exists");
        this.put("NotBeingDoneException", "The task can't be stopped because it's not being done by any thread");
        this.put("NotSubscribingException", "That ID is not subscribed to that task");
        this.put("AlreadySubscribingException", "That ID is already subscribe");
    }};

    public WarehouseException() {
        super();
    }

    public WarehouseException(String className) {
        super(EXCEPTIONS.get(className));
    }

    public WarehouseException(String className, String message) {
        super(EXCEPTIONS.get(className));
        System.err.println(message);
    }
}

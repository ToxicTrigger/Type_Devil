package mine.typed.core.game;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EventPool {

    public final int MAX_EVENT;
    private ExecutorService exe;

    public EventPool(int maxEvent) {
	MAX_EVENT = maxEvent;

	exe = Executors.newFixedThreadPool(maxEvent);
    }

    public void submit(Thread event) {
	exe.submit(event);
    }

    public void upload(Thread... events) {
	for (Thread eve : events) {

	    exe.execute(eve);

	}
	exe.shutdown();
    }

}

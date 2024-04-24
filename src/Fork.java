import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicBoolean;

public class Fork {
    public final int id;
    private final AtomicBoolean isTaken;

    private final Semaphore put;
    private final Semaphore take;

    public Fork(int id) {
        this.id = id;
        this.isTaken = new AtomicBoolean(false);
        this.put = new Semaphore(1);
        this.take = new Semaphore(1);
    }

    public boolean isTaken() {
        return isTaken.get();
    }

    public void take() throws InterruptedException {
        take.acquire();
        synchronized (this) {
            isTaken.set(true);
        }
        put.release();
    }

    public void put() throws InterruptedException {
        put.acquire();
        synchronized (this) {
            isTaken.set(false);
        }
        take.release();
    }
}
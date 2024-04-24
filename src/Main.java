public class Main {
    public static void main(String[] args) {
        final int NUM_PHILOSOPHERS = 5;
        final Philosopher[] philosophers = new Philosopher[NUM_PHILOSOPHERS];
        final Fork[] forks = new Fork[NUM_PHILOSOPHERS];

        for (int i = 0; i < NUM_PHILOSOPHERS; i++) {
            forks[i] = new Fork(i);
        }

        for (int i = 0; i < NUM_PHILOSOPHERS; i++) {
            philosophers[i] = new Philosopher(i, forks[i], forks[(i + 1) % NUM_PHILOSOPHERS]);
            philosophers[i].start();
        }

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for (Philosopher philosopher : philosophers) {
            philosopher.interrupt();
        }
    }
}
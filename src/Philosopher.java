public class Philosopher extends Thread {
    private final int id;
    private final Fork leftFork;
    private final Fork rightFork;

    public Philosopher(int id, Fork leftFork, Fork rightFork) {
        this.id = id;
        this.leftFork = leftFork;
        this.rightFork = rightFork;
    }

    @Override
    public void run() {
        try {
            while (true) {
                test();
            }
        } catch (InterruptedException e) {
            synchronized (System.out) {
                System.out.println("Philosopher " + id + " has been interrupted and stopped.");
            }
        }
    }

    private void think() throws InterruptedException {
        synchronized (System.out) {
            System.out.println("Philosopher " + id + " is thinking");
        }
        Thread.sleep((long) (Math.random() * 1000));
    }

    private void eat() throws InterruptedException {
        synchronized (System.out) {
            System.out.println("Forks " + leftFork.id + " and " + rightFork.id + " have been taken by philosopher " + this.id);
            System.out.println("Philosopher " + id + " is eating");
        }
        Thread.sleep((long) (Math.random() * 1000));
        leftFork.put();
        rightFork.put();
        synchronized (System.out) {
            System.out.println("Forks " + leftFork.id + " and " + rightFork.id + " have been put by philosopher " + this.id);
        }
    }

    private void test() throws InterruptedException {
        if (!rightFork.isTaken()) {
            rightFork.take();
            if (leftFork.isTaken() && rightFork.isTaken()) {
                rightFork.put();
            } else {
                leftFork.take();
                eat();
            }
        } else {
            think();
        }
    }
}
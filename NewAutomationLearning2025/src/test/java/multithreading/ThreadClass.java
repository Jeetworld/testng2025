package multithreading;

public class ThreadClass extends Thread{

    public void run(){
        System.out.println("this is thread method");
        System.out.println("Thread details "+Thread.currentThread().getName());

    }

    public static void main(String[] args) {
        Thread t1 = new ThreadClass();
        Thread t2 = new ThreadClass();

        t1.start();
        t2.start();
    }
}

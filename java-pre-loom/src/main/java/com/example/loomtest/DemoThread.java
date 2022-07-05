package com.example.loomtest;

public class DemoThread {

    private static int NUM_THREADS = 1000; // Pass -Xss1M -Xm1G Jvm Args and make it higher to 10000

    private static void handleUserRequest() {
        System.out.println("Starting thread " + Thread.currentThread());

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Ending thread " + Thread.currentThread());

    }

    public static void main(String[] args) throws Exception {

        String numThreadsFromEnv = System.getenv("NUM_THREADS");
        if(numThreadsFromEnv != null && numThreadsFromEnv.length()>0){
            NUM_THREADS = Integer.parseInt(numThreadsFromEnv);
        }

        System.out.println("Starting main " + Thread.currentThread());

        for (int j= 0; j < NUM_THREADS; j++) {
            new Thread(() -> handleUserRequest()).start();
        }

        System.out.println("Ending main");
    }
}

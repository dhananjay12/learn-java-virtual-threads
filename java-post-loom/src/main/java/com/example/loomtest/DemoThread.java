package com.example.loomtest;

import java.util.ArrayList;

public class DemoThread {

    private static int NUM_THREADS = 1000000; // Pass -Xss1M -Xm1G Jvm Args accordingly and make it higher to 10000

    private static void handleUserRequest() {
        //System.out.println("Starting thread " + Thread.currentThread());

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //System.out.println("Ending thread " + Thread.currentThread());

    }

    public static void main(String[] args) throws Exception {

        String numThreadsFromEnv = System.getenv("NUM_THREADS");
        if(numThreadsFromEnv != null && numThreadsFromEnv.length()>0){
            NUM_THREADS = Integer.parseInt(numThreadsFromEnv);
        }

        System.out.println("Starting main " + Thread.currentThread());

        var threads = new ArrayList<Thread>();
        for (int j= 0; j < NUM_THREADS; j++) {
            threads.add(startThread());
        }
        // join on the threads
        for (Thread thread : threads) {
            thread.join();
        }
        System.out.println("Total virtual threads => " + threads.size());
        System.out.println("Ending main");
    }

    @SuppressWarnings("preview")
    private static Thread startThread() {
        return Thread.startVirtualThread(() -> handleUserRequest());
    }

//    private static void startThread() {
//        new Thread(() -> handleUserRequest()).start();
//    }
}

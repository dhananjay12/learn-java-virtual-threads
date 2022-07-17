package com.example.loomtest;

import java.lang.Thread.Builder.OfVirtual;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

public class DemoVirtualThreadCreate {

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

        System.out.println("Starting main ");

        // Replace this call as needed
        //playWithVirtualBuilder();
        // playWithFactory();
         //playWithVirtualExecutorService();
        playWithExecutorService();

        System.out.println("Ending main ");

    }

    /**
     *
     * Creates Virtual Threads using a Virtual Builder
     * @throws Exception
     */
    @SuppressWarnings({ "preview", "unused" })
    private static void playWithVirtualBuilder() throws Exception {

        // Create a Virtual Builder object with name and initial index
        OfVirtual vBuilder = Thread.ofVirtual().name("userthread", 0);

        // Start two virtual threads using the builder
        Thread vThread1 =
                vBuilder.start(DemoVirtualThreadCreate::handleUserRequest);
        Thread vThread2 =
                vBuilder.start(DemoVirtualThreadCreate::handleUserRequest);

        // Make sure the threads terminate
        vThread1.join();
        vThread2.join();

        // Control reaches here once the two virtual threads complete
    }

    /**
     *
     * Creates Virtual Threads using a Thread Factory
     * @throws Exception
     */
    @SuppressWarnings({ "preview", "unused" })
    private static void playWithFactory() throws Exception {

        // Create a Thread factory
        ThreadFactory factory
                = Thread.ofVirtual().name("userthread", 0).factory();

        // Start two virtual threads using the factory
        Thread vThread1
                = factory.newThread(DemoVirtualThreadCreate::handleUserRequest);
        vThread1.start();

        Thread vThread2
                = factory.newThread(DemoVirtualThreadCreate::handleUserRequest);
        vThread2.start();

        // Make sure the threads terminate
        vThread1.join();
        vThread2.join();

        // Control reaches here once the two virtual threads complete

    }


    /**
     *
     * Create a Virtual Thread using a Virtual Thread Executor
     */
    @SuppressWarnings({ "preview", "unused" })
    private static void playWithVirtualExecutorService() {


        // Create an Virtual Thread ExecutorService
        // Note the try with resource which will make sure all Virtual threads
        // are terminated
        try (ExecutorService srv = Executors.newVirtualThreadPerTaskExecutor()) {

            // Submit two tasks to the Executor service
            srv.submit(DemoVirtualThreadCreate::handleUserRequest);
            srv.submit(DemoVirtualThreadCreate::handleUserRequest);

        }

        // Control reaches here once the two virtual threads complete


    }


    /**
     *
     * Create a Virtual Thread using a "Thread Per Task" Executor Service
     */
    @SuppressWarnings({ "preview", "unused" })
    private static void playWithExecutorService() {

        // Create a Virtual Thread factory with custom name
        ThreadFactory factory
                = Thread.ofVirtual().name("userthread", 0).factory();

        // Create an ExecutorService for this factory
        // Note the try with resource which will make sure all Virtual threads
        // are terminated
        try (ExecutorService srv = Executors.newThreadPerTaskExecutor(factory)) {

            // Submit two tasks to the Executor service
            srv.submit(DemoVirtualThreadCreate::handleUserRequest);
            srv.submit(DemoVirtualThreadCreate::handleUserRequest);
        }

        // Control reaches here once the two virtual threads complete

    }

}

package com.example.loomtest.httpcall;

import java.util.Arrays;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

public class UserRequestHandler implements Callable<String> {
    @Override
    public String call() throws Exception {
        return sequentialCall();
    }

    //Using CompletableFuture
    private String concurrentCallCompletableFuture() {
        try (ExecutorService service = Executors.newVirtualThreadPerTaskExecutor()) {

            String output = CompletableFuture
                    .supplyAsync(this::dbCall, service)
                    .thenCombine(
                            CompletableFuture.supplyAsync(this::restCall, service)
                            , (result1, result2) -> {
                                return "[" + result1 + "," + result2 + "]";
                            }) //Combine these 2 calls
                    .thenApply(result -> {

                        // both dbCall and restCall have completed call a third one
                        String r = externalCall();
                        return "[" + result + "," + r + "]";

                    })
                    .join();

            System.out.println(output);
            return output;

        }
    }

    //Using Futures
    private String concurrentCallWithFutures() throws Exception {
        try (ExecutorService service = Executors.newVirtualThreadPerTaskExecutor()) {

            long start = System.currentTimeMillis();
            Future<String> dbFuture   = service.submit(this::dbCall);
            Future<String> restFuture = service.submit(this::restCall);

            String result = String.format("[%s,%s]", dbFuture.get(), restFuture.get()); //blocking calls using get

            long end = System.currentTimeMillis();
            System.out.println("time = " + (end - start));

            System.out.println(result);
            return result;

        }
    }

    private String sequentialCall() throws Exception {
        long start = System.currentTimeMillis();

        String result1 = dbCall(); // 2 secs
        String result2 = restCall();  // 5 secs

        String result = String.format("[%s,%s]", result1, result2);

        long end = System.currentTimeMillis();
        System.out.println("time = " + (end - start));

        System.out.println(result);
        return result;
    }

    private String dbCall() {
        try {
            NetworkCaller caller = new NetworkCaller("data");
            return caller.makeCall(2);
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private String restCall() {
        try {
            NetworkCaller caller = new NetworkCaller("rest");
            return caller.makeCall(5);
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    private String externalCall() {
        try {
            NetworkCaller caller = new NetworkCaller("extn");
            return caller.makeCall(4);
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }
}

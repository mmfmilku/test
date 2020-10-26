package design_mode.adapter;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class ObjectAdapter {

    public static void main(String[] args) {
        System.out.println(System.getProperties());
        Runnable runnable = () -> {
            System.out.println("run");
        };
        Callable<Object> callable = Executors.callable(runnable);
    }



}


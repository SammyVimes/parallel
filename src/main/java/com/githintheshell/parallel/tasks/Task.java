package com.githintheshell.parallel.tasks;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

public abstract class Task<T> {

    public final Future<T> run(final ExecutorService executorService) {
        return executorService.submit(new Callable<T>() {
            public T call() throws Exception {
                try {
                    return execute();
                } catch (final Throwable t) {
                    return null;
                }
            }
        });
    }

    public abstract T execute();

}

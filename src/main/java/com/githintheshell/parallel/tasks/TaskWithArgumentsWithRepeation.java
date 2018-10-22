package com.githintheshell.parallel.tasks;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public abstract class TaskWithArgumentsWithRepeation<T, A> extends TaskWithArguments<List<T>, A, Integer> {


    @Override
    public final List<T> execute() {
        return IntStream
                .range(0, getThreadCount())
                .mapToObj((v) -> {
                    CompletableFuture<T> future = new CompletableFuture<>();
                    new Thread(() -> {
                        final List<T> result = IntStream.range(0, getRepeatCount())
                                .mapToObj((a) -> execute(getArguments(v)))
                                .flatMap(List::stream)
                                .collect(Collectors.toList());
                        future.complete(result.get(result.size() - 1));
                    }).start();
                    return future;
                })
                .map(future -> {
                    try {
                        return future.get();
                    } catch (Throwable e) {
                        System.err.println("Error: " + e.getMessage());
                        return null;
                    }
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    public abstract int getRepeatCount();

    public abstract int getThreadCount();

}

package com.githintheshell.parallel.tasks;

import java.util.List;
import java.util.function.Function;

public class Task1 extends TaskWithArgumentsWithRepeation<Long, Function<Long, Long>> {
    
    private static final int REPEAT_COUNT = 5;
    private static final int THREAD_COUNT = 4;

    private Long value = 2L;

    public List<Long> execute(final Function<Long, Long> function) {
        value = function.apply(value);
        return List.of(value);
    }

    @Override
    public Function<Long, Long> getArguments(Integer param) {
        List<Function<Long, Long>> arr = List.of(v -> v + v, v -> v * v, v -> v - 5, v -> v / 4);
        return arr.get(param);
    }

    @Override
    public int getRepeatCount() {
        return REPEAT_COUNT;
    }

    @Override
    public int getThreadCount() {
        return THREAD_COUNT;
    }

}

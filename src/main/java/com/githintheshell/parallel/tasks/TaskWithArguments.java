package com.githintheshell.parallel.tasks;

public abstract class TaskWithArguments<T, A, AP> extends Task<T> {

    @Override
    public T execute() {
        return execute(getArguments((AP) null));
    }


    public abstract T execute(final A arguments);

    public abstract A getArguments(final AP param);

}

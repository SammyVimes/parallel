package com.githintheshell.parallel;

import com.githintheshell.parallel.tasks.Task;
import com.githintheshell.parallel.tasks.Task1;

import java.util.List;

public class MainTask {

    public static void main(String[] args) {
        var task1 = new Task1();
        final List<Long> execute = task1.execute();

        check(1,"[64, 4294967296, -23, 0]", execute);
    }

    private static void check(final int taskId, final Object expected, final Object real) {

        if (!expected.equals(real.toString())) {
            System.err.println("Check for task " + taskId + " failed");
            System.err.println("Expected is " + expected);
            System.err.println("Real is " + real);
            System.exit(1);
        }
    }

}

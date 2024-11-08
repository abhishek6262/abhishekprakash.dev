package dev.abhishekprakash.starterkit.Core.Support;

import dev.abhishekprakash.starterkit.Contracts.CheckedConsumer;

import java.util.ArrayList;
import java.util.List;

public class Tap<T> {

    private final T data;

    private final List<CheckedConsumer<T>> consumers = new ArrayList<>();

    public Tap(T data) {
        this.data = data;
    }

    public Tap<T> through(CheckedConsumer<T> consumer) {
        consumers.add(consumer);
        return this;
    }

    public void execute() throws Exception {
        for (CheckedConsumer<T> consumer : consumers) {
            consumer.accept(data);
        }
    }

}
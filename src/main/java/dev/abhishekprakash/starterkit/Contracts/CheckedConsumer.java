package dev.abhishekprakash.starterkit.Contracts;

@FunctionalInterface
public interface CheckedConsumer<T> {
    void accept(T t) throws Exception;
}
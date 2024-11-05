package dev.abhishekprakash.personalwebsite.Contracts;

public interface Renderable<T> {

    T render() throws Exception;

}
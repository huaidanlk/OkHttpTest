package com.example.okhttptest;

public class TClass<T> {
    private T a;

    public T getA() {
        return a;
    }

    public void setA(T a) {
        this.a = a;
    }

    public static class TClass1<E extends A> {
        private E a;

        public E getA() {
            return a;
        }

    }
}


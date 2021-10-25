package com.example.okhttptest.dataStructure.linkedList;

class MyLinkedList<E>{

    private static class Node<E>{
        E element;
        Node next;
    }


    private int size;
    private Node<E> firstNode;

}

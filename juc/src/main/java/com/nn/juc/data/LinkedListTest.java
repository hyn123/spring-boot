package com.nn.juc.data;

import java.util.LinkedList;

/**
 * 双向链表
 *
 * @Author: hyn
 * @Date: 2020-01-16 11:35
 */
public class LinkedListTest<T> {
    public class Node<T> {
        private T value;
        private Node<T> next;
        private Node<T> prev;

        public Node(T value, Node<T> next, Node<T> prev) {
            this.value = value;
            this.next = next;
            this.prev = prev;
        }
    }

    int size = 0;
    Node<T> first;
    Node<T> last;

    public void add(T t) {
        Node<T> l = last;
        Node<T> newL = new Node<T>(t, null, l);
        if (first == null) {
            first = newL;
        } else {
            if (last==null){
                last = newL;
                first.next = last;
            }else{
                last.next = newL;
            }
        }
        size++;
    }

}

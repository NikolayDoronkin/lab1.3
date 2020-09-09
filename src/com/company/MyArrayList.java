package com.company;

public class MyArrayList<E> {
    private int size = 0;
    public Node<E> head = new Node<E>(null);
    public Node<E> last;

    public MyArrayList() {
    }

    private static class Node<E> {
        E element;
        Node<E> next;

        Node(E element) {
            this.element = element;
        }
    }

    public void add(E element) {
        Node<E> temp = new Node<E>(element);
        if (size == 0) {
            head.next = temp;
        }
        else last.next = temp;
        last = temp;
        temp.next = head;
        size++;
    }

    public void removeAt(int index){
        Node<E> temp;
        Node<E> prev;
        if (index < 0 || index > size - 1)
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        if(index == 0){
            temp = node(index);
            head.next = temp.next;
        } else{
            temp = node(index);
            prev = node(index - 1);
            prev.next = temp.next;
        }
        temp.next = null;
        size--;
    }

    public void set(int index, E element){
        if (index < 0 || index > size)
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        Node<E> temp = node(index);
        temp.element = element;
    }

    private Node<E> node(int index) {
        //проверка на допустимость индекса
        if (index < 0 || index > size)
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        Node<E> e = head;
        //проход по списку до нужного элемента
        for (int i = 0; i <= index; i++)
            e = e.next;
        return e;
    }

    public E get(int index){
        return node(index).element;
    }

    public void print() {
        Node<E> temp = last;
        while (temp != null) {
            System.out.println(temp.element);
            temp = temp.next;
        }
    }

    //работает
    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void clear(){
        head.next = head;
        size = 0;
    }

}
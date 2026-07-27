package org.example;
public class Demo023_CustomLinkedList {
    Node head;
    static class Node {
        int data;
        Node next;

        Node(int d) {
            data = d;
            next = null;
        }
    }
    public void insert(int data) {
        Node newNode = new Node(data);
        if (this.head == null) {
            this.head = newNode;
        } else {
            Node last = this.head;
            while (last.next != null) {
                last = last.next;
            }
            last.next = newNode;
        }
    }
    public void printList() {
        Node currNode = this.head;
        System.out.print("LinkedList: ");
        while (currNode != null) {
            System.out.print(currNode.data + " -> ");
            currNode = currNode.next;
        }
        System.out.println("null");
    }

    public static void main(String[] args) {
        Demo023_CustomLinkedList list = new Demo023_CustomLinkedList();
        list.insert(10);
        list.insert(20);
        list.insert(30);
        list.printList();
    }
}
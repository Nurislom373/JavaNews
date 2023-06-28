package org.khasanof.leetcode.medium;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

/**
 * @author Nurislom
 * @see org.khasanof.leetcode.medium
 * @since 28.06.2023 5:58
 */
public class AddTwoNumbers {

    public static void main(String[] args) {
        ListNode listNodeOne = new ListNode(9);
        ListNode listNodeTwo = new ListNode(5, new ListNode(6, new ListNode(4, new ListNode(9,
                new ListNode(9, new ListNode(9, new ListNode(9, new ListNode(9, new ListNode(9,
                        new ListNode(9, new ListNode(9, new ListNode(9, new ListNode(9)))))))))))));
        ListNode listNode1 = addTwoNumbers(listNodeOne, listNodeTwo);
        List<Integer> integers = new ArrayList<>();
        addList(integers, listNode1);
        System.out.println("integers = " + integers);
    }

    static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        List<Integer> integersL1 = new ArrayList<>();
        List<Integer> integersL2 = new ArrayList<>();

        addList(integersL1, l1);
        addList(integersL2, l2);

        System.out.println("integersL1 = " + integersL1);
        System.out.println("integersL2 = " + integersL2);

        Collections.reverse(integersL1);
        Collections.reverse(integersL2);

        System.out.println("integersL1 = " + integersL1);
        System.out.println("integersL2 = " + integersL2);

        int sum = Stream.of(integersL1, integersL2)
                .map(list -> list.stream()
                        .map(String::valueOf)
                        .reduce(String::concat)
                        .get()
                ).mapToInt(Integer::parseInt)
                .sum();

        return strToListNode(new StringBuilder(String.valueOf(sum)).reverse().toString());
    }

    public static void addList(List<Integer> list, ListNode listNode) {
        list.add(listNode.val);
        if (Objects.nonNull(listNode.next))
            addList(list, listNode.next);
    }

    public static ListNode strToListNode(String var) {
        ListNode listNode = new ListNode(Integer.parseInt(Character.toString(var.charAt(0))));
        nextTo(listNode, var, 1);
        return listNode;
    }

    public static void nextTo(ListNode listNode, String var, int index) {
        if (var.length() > index) {
            listNode.next = new ListNode(Integer.parseInt(Character.toString(var.charAt(index))));
            nextTo(listNode.next, var, ++index);
        }
    }

}

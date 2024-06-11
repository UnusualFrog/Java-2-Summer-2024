package main.java.ca.nl.cna.java2.exercise.ex7_GenericCollections;

import java.util.Collection;
import java.util.LinkedList;

public class CollectionsEx {
    public static void main(String[] args) {
        CollectionsEx ex = new CollectionsEx();
        LinkedList<Integer> data = new LinkedList<>();
        ex.addValues(data);
        ex.showValues(data);
    }

    public void addValues(Collection<Integer> values) {
        values.add(1);
        values.add(2);
        values.add(3);
    }

    public void showValues(Collection<Integer> values) {
        for (Integer i : values) {
            System.out.println(i);
        }
    }
}

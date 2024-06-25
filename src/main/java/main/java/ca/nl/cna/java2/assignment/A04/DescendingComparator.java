package main.java.ca.nl.cna.java2.assignment.A04;

import java.util.Comparator;

public class DescendingComparator implements Comparator<Double> {

    @Override
    public int compare(Double o1, Double o2) {
        if (o1 > o2) {
            return -1;
        } else if (o1 < o2) {
            return 1;
        }
        return 0;
    }
}

package main.java.ca.nl.cna.java2.assignment.A01;

//#8 Exercise: Create a class “WeightConverter” which has two methods to perform
//conversions, “poundsToKilos” and “kilosToPounds”. Add assertions to these
//methods to prevent the conversion of weights less than or equal to 0. Create a main
//method which tests these two methods with correct values and incorrect values. ( 4
//marks)

public class WeightConverter {

    public WeightConverter() {
    }

    public double poundsToKilos(double initialPounds){
        assert(initialPounds > 0) : "lb. value must be greater than 0";
        return initialPounds/2.2046;
    }

    public double kilosToPounds(double initialKilos){
        assert(initialKilos > 0) : "kg. value must be greater than 0";
        return initialKilos * 2.2046;
    }

    public static void main(String[] args) {
        WeightConverter converter = new WeightConverter();

        // Correct conversions
        System.out.println(converter.poundsToKilos(2.2046));
        System.out.println(converter.kilosToPounds(1.0));

        // Incorrect zero cases
        try {
            System.out.println(converter.poundsToKilos(0));
        } catch(AssertionError e) {
            System.err.println(e);
        }

        try {
            System.out.println(converter.kilosToPounds(0));
        } catch(AssertionError e) {
            System.err.println(e);
        }

        // Incorrect negative cases
        try {
            System.out.println(converter.poundsToKilos(-10.2));

        } catch(AssertionError e) {
            System.err.println(e);
        }

        try {
            System.out.println(converter.kilosToPounds(-100.01));

        } catch(AssertionError e) {
            System.err.println(e);
        }
    }
}

package main.java.ca.nl.cna.java2.assignment.A01.ex11_16;

public class Main {

    public static void main(String[] args) {
        try{
            throw new ExceptionA("A");
        }catch (ExceptionA e){
            e.printStackTrace();
        }

        try{
            throw new ExceptionB("B");
        }catch (ExceptionA e){
            e.printStackTrace();
        }

        try{
            throw new ExceptionC("C");
        }catch (ExceptionA e){
            e.printStackTrace();
        }
    }
}

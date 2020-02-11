import java.util.Scanner;

public class CS2913Spring2020Intro {
    public static void main (String[] args) {
        /*
        255 chars from ASCII
        others all unicode
         */
        Scanner cin = new Scanner(System.in);
        System.out.println("What is your name?");
        String s = cin.nextLine();
        System.out.print("What is your age? ");
        int age = cin.nextInt();

        //auto box & auto unbox
        Integer i = age;

        

    }
}
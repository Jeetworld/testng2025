package Java11;

import java.util.stream.Collectors;

public class StringFeatures {

    public static void main(String[] args) {
        String s = "JavaWorld";
        String blankString = "";

        //isBlank()
        isStringBlankOrNot(s);
        isStringBlankOrNot(blankString);

        //lines() -- break the lines into string
        String s2= "Hello\nWorld\nJava";
        linesMethod(s2);

        //repeat()
        repeatMethod(s);

        //strip
        String s3 = " Hello Java ";
        strip(s3);

    }

    private static void isStringBlankOrNot(String s){
        System.out.println("Is this string blank or not :: "+s.isBlank());
    }

    private static void linesMethod(String s){
        System.out.println(s.lines().collect(Collectors.toList()));
    }

    private static void repeatMethod(String s){
        System.out.println("repeated strins are "+s.repeat(4));
    }

    private static void strip(String s){
        System.out.println("Striping the strings "+s.strip());
        System.out.println("Striping the strings "+s.stripLeading());
        System.out.println("Striping the strings "+s.stripTrailing());
    }
}

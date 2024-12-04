package programming;

import java.util.ArrayList;
import java.util.List;

public class FindString {


    public static void main(String[] args) {


        List<String> stringList = new ArrayList<>();
        // Populate the list with 10,000 strings

        String target1 = "string1";
        String target2 = "string2";

        boolean foundTarget1 = stringList.stream().anyMatch(s -> s.equals(target1));
        boolean foundTarget2 = stringList.stream().anyMatch(s -> s.equals(target2));

        System.out.println("Found target1: " + foundTarget1);
        System.out.println("Found target2: " + foundTarget2);
    }
}

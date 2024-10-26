package DivisionByThree;

import java.util.HashSet;
import java.util.Set;

public class DivisionByThree {
    public boolean divisionByThree(long num) {
        long totalNumber = 0;
        String numStr = Long.toString(num);
        for ( char number : numStr.toCharArray()) {
            totalNumber += number;
        }
        if(totalNumber >= 9999){
            totalNumber -= 9999;
        }
        else if(totalNumber >= 999) {
            totalNumber -= 999;
        } else if (totalNumber >= 99) {
            totalNumber -= 99;
        } else if (totalNumber >= 9) {
            totalNumber -= 9;
        }
        while (totalNumber >= 3) {
            totalNumber -= 3;
        }
        if(totalNumber == 0){
            return true;
        }
        else {
            return false;
        }
    }
    public static  void main(String[] args) {
        DivisionByThree checker = new DivisionByThree();
        Set<Long> sets = new HashSet<>();
        sets.add(1L);
        sets.add(99L);
        sets.add(5040004332321453L);
        sets.add(5040004332321456L);
        sets.forEach(set -> {
            boolean checkDivisonyThree = checker.divisionByThree(set);
            if(checkDivisonyThree == true){
                System.out.println("Number divisible by 3 : " + set);
            }
            else {
                System.out.println("Number not divisible by 3 : " + set);
            }
        });

    }
}

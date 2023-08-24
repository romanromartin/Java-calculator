import java.util.Arrays;
import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;

//X+X

public class Main {
    public static void main(String[] args) throws Exception {

        Scanner input = new Scanner(System.in);

        System.out.println( calc(input.nextLine()));
    }
    public static String calc(String input) throws Exception {
        String str = input.replaceAll (" ", "");
        if( str.length() < 3 ) {throw  new Exception( "строка не является математической операцией");}
        char[] op = "-+/*".toCharArray();
        int result = 0;
        String res_out = "";
        int count = 0;
        boolean oper = false;
        for( char c : op){
            if(str.contains(String.valueOf(c))){
                oper = true;
                int index_ = str.indexOf(c);
                String a = str.substring(0, index_);
                String b = str.substring(index_+1);
                String[] roman = {"I","II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"};
                String[] reverse = {"C", "XC", "L", "XL", "X", "IX", "VIII", "VII", "VI", "V", "IV", "III", "II", "I"};
                int[] reverse_int = {100, 90, 50, 40, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1};
                if (isNumeric(a) && isNumeric(b) ){
                    int int_a = Integer.parseInt(a);
                    int int_b = Integer.parseInt(b);
                    if(int_a > 0 && int_a < 11 && int_b > 0 && int_b < 11 ){
                        result = self_math(String.valueOf(c), int_a, int_b);
                        res_out = String.valueOf(result);
                    }
                }
                else if(Arrays.asList(roman).contains(a) && Arrays.asList(roman).contains(b) ){
                    int roman_a = Arrays.asList(roman).indexOf(a)+1;
                    int roman_b = Arrays.asList(roman).indexOf(b)+1;
                    result = self_math(String.valueOf(c), roman_a, roman_b);
                    if (result<1) res_out = "";
                    else {
                        int temp;
                        int count_rom = 0;
                        for (int i : reverse_int){
                            temp = result/i;
                            if(temp>0){
                                result -= temp*i;
                                for (int j = temp; j>0; j-- ){
                                    res_out += reverse[count_rom];
                                }
                            }
                            count_rom +=1;
                        }
                    }
                }
                else{throw  new Exception( "используются одновременно разные системы счисления");}
            }

        }
        if (!oper) throw  new Exception( "строка не является математической операцией");
        return res_out;
    }

    private static boolean isNumeric(String str) {
        return str != null && str.matches("[-+]?\\d*\\.?\\d+");
    }
    private static int self_math (String op, int a, int b){
      int result = 0;
        if (op.equals("-")) result = a-b;
        else if (op.equals("+")) result = a+b;
        else if (op.equals("*")) result = a*b;
        else if (op.equals("/")) result = a/b;
      return result;
    }
}
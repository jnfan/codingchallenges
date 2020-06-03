package affirm;

import java.util.Stack;

public class EvaluateExp {
    public EvaluateExp() {

    }

    public static void main(String[] args) {
        EvaluateExp ee = new EvaluateExp();
        int test = ee.parse("( mul ( mul 3 -2 5 ) -3 ( add 1 ( add 1 2 ) )");
        System.out.println(test);

    }
    class Pair {
        String str;
        int cnt;
        public Pair(String str, int cnt){
            this.str = str;
            this.cnt = cnt;
        }
    }
    public int parse(String exp) {
        Stack<Pair> operand = new Stack<>();
        Stack<Pair> num = new Stack<>();
        String[] arr = exp.split(" ");
        int curRes = 0;
        int count = 0;
        for(int i = 0; i < arr.length; i++) {
            if(arr[i].equals("(")) {
                if(i != 0) {
                    num.push(new Pair("" + curRes, count));
                }
                count++;
            }else if(arr[i].equals("add") || arr[i].equals("mul")){
                operand.push(new Pair(arr[i], count));
                if(arr[i].equals("add")) {
                    curRes = 0;
                }else {
                    curRes = 1;
                }
            }else if(isNumeric(arr[i])) {
                if(operand.peek().str.equals("add")) {
                    curRes += Integer.parseInt(arr[i]);
                }else {
                    curRes *= Integer.parseInt(arr[i]);
                }
            }else if(arr[i].equals(")")) {
                while(!num.isEmpty() && num.peek().cnt == count) {
                    if(operand.peek().cnt == count) {
                        if(operand.peek().str.equals("add")) {
                            curRes += Integer.parseInt(num.pop().str);
                        }else {
                            curRes *= Integer.parseInt(num.pop().str);
                        }
                    }
                }
                operand.pop();
                count--;
            }
        }
        return curRes;

    }
    public boolean isNumeric(String str) {
        if (str == null || str.length() == 0) {
            return false;
        }
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (i == 0 && c == '-') continue;
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }
}

import java.util.Stack;
import java.util.Queue;
import java.util.Scanner;
import java.util.LinkedList;

public class Main
{
    public static Object convert(Object x, String act){
        if(act.equals("to boolean") && x instanceof String){
            return Boolean.valueOf((String)x);
        }
        else if(act.equals("to string") && x instanceof Boolean){
            return Boolean.toString((Boolean)x); 
        }
        else{System.out.println("ERROR: funky input...\n"); return null;}
    }
    
    public static boolean isBinary(String x){
        switch(x){
            case "AND": return true;
            case "NAND": return true;
            case "OR": return true;
            case "NOR": return true;
            case "XOR": return true;
            case "COND": return true;
            case "BICOND": return true;
            default : return false;
        }
    }
    
    public static String postfixEvaluation(Queue<String> q){
        Stack <String> s = new Stack<String>();
        
        while(q.peek() != null){
        String token = "";
        //String newToken, newToken2;
        Boolean op1, op2;
        
            token = q.peek();
            if(token.equals("true") || token.equals("false")){s.push(token);}
            else if(token.equals("NOT")){
                if(s.size() < 1){return "error";}
                
                op1 = (Boolean)convert(s.pop(), "to boolean");
                op1 = !op1;
                s.push((String)convert(op1, "to string"));
            }
            else if(isBinary(token)){
                if(s.size() < 2){return "error";}
                
                op1 = (Boolean)convert(s.pop(), "to boolean");
                op2 = (Boolean)convert(s.pop(), "to boolean");
                
                if(token.equals("AND")){
                    op1 = op1 && op2;
                }
                else if(token.equals("NAND")){
                    op1 = !(op1 && op2);
                }
                else if(token.equals("OR")){
                    op1 = op1 || op2;
                }
                else if(token.equals("NOR")){
                    op1 = !(op1 || op2);
                }
                else if(token.equals("XOR")){
                    op1 = op1 != op2;
                }
                else if(token.equals("COND")){
                    op1 = !op1 || op2;
                }
                else if(token.equals("BICOND")){
                    op1 = op1 == op2;
                }
                
                s.push((String)convert(op1, "to string"));
            }
            q.poll();
        }
        if(s.size() > 1){return "error";}
        else{return s.pop();}
        
    }
    
	public static void main(String[] args) {
		Scanner enter = new Scanner(System.in);
		Queue <String> q = new LinkedList<String>(); 
		
/*Accept queue from user input/console input, not sure which, but I
just implemented a way to scan a single line at a time, as per the examples.*/
		
		    String currLine = enter.nextLine();
		    String[] tokens = currLine.split(" ");
		    for(String token: tokens){q.add(token);}
		
		System.out.println(postfixEvaluation(q));
	}
}

import java.util.*;

public class Pemdas 
{
    public static boolean isNumeric(String strNum) 
    {
        if (strNum == null) 
        {
            return false;
        }
        try 
        {
            double d = Double.parseDouble(strNum);
        } 
            catch (NumberFormatException nfe) 
        {
            return false;
        }
        return true;
    }

    static String calculate(String first, String operator, String second)
    {
        double num1 = Double.parseDouble(first); 
        double num2 = Double.parseDouble(second);
        double result = 0;

        switch (operator)
        {
            case "+":
                result += num1 + num2;
                break;

            case "-":
                result += num1 - num2;
                break;

            case "*":
                result += num1 * num2;
                break;

            case "/":
                result += num1 / num2;
                break;
        }

        return Double.toString(result);
    }

    public static void main(String[] args)
    {
        String equation;
        Scanner sc = new Scanner(System.in);

        do
        {
            System.out.print("Enter an expression (Press 0 to enter): ");
            equation = sc.nextLine();
    
            equation = equation.replaceAll(" ", ""); // remove spaces from the equation
            Queue <String> queue = new LinkedList<>();  
            Stack <Character> stck = new Stack<>();
            
            for (int a = 0; a < equation.length(); ++a)
            {   
                if (Character.isDigit(equation.charAt(a))) // if the current character is a number
                {
                    String temp = "";
                    while (a < equation.length() && Character.isDigit(equation.charAt(a)))
                    {
                        if (!Character.isDigit(equation.charAt(a)))
                            break;
    
                        temp += equation.charAt(a);
                        ++a;
                    }
                    --a;
                    queue.add(temp);
                }
    
                else // if its an operator 
                {
                    if (stck.isEmpty())
                        stck.push(equation.charAt(a));
    
                    else
                    {
                        switch (equation.charAt(a))
                        {
                            
                            case '(':
                                stck.push(equation.charAt(a));
                                break;
    
                            case ')':
                            
                                while (!stck.empty())
                                {
                                    if (stck.peek().equals('('))
                                    {
                                        stck.pop();
                                        break;                                    
                                    }
                                    else
                                        queue.add(String.valueOf(stck.pop()));
                                }
                                break;
                            
                            case '+':
                            case '-':
                                if (stck.peek().equals('*') || stck.peek().equals('/') || stck.peek().equals('+') || stck.peek().equals('-'))
                                {
                                    queue.add(String.valueOf(stck.pop()));
                                    stck.push(equation.charAt(a));
                                }
                                else
                                    stck.push(equation.charAt(a));
                                break; 
    
                            case '*':
                            case '/':
                                if (stck.peek().equals('*') || stck.peek().equals('/'))
                                {
                                    queue.add(String.valueOf(stck.pop()));
                                    stck.push(equation.charAt(a));
                                }
    
                                else
                                    stck.push(equation.charAt(a));
                        }
                    }
                }        
            }
    
            while (!stck.isEmpty())
                queue.add(String.valueOf(stck.pop()));
    
            Stack <String> stck1 = new Stack<>();
    
            for (String item : queue)
            {
                if (isNumeric(item))
                    stck1.push(item);
                    
                else
                {
                    String second = stck1.pop();
                    String first = stck1.pop();
                    String result = calculate(first, item, second);
                    stck1.push(result);
                }
            }
    
            if (!stck1.peek().equals("0"))
                System.out.printf("%s = %s\n", equation, stck1.peek());

        }while(!equation.equals("0"));
    }           
}   
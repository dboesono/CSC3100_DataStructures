// Derivative Calculator of a math expression with one variable
import java.io.*;
import java.util.*;

public class derivative {

  // Get derivative for each term
  static String derivativeTerm(String term) {
  
    // If there is no x variable then return nothing
    if (!term.contains("x")) {
      return "";
    }

    // If the term only contain x
    else if (term.equals("x") || term.equals("+x") || term.equals("x^1")) {
      return "1";
    }

    // if the term only contain -x
    else if (term.equals("-x") || term.equals("-x^1")) {
      return "-1";
    }

    // if the term has no coefficient
    else if (term.substring(0, 2).equals("x^")){
      // Retrieve the exponent of the term
      String StrPow = term.substring(2);
      int pow = Integer.parseInt(StrPow);
      String result = String.valueOf(pow) + "*x^" + String.valueOf(pow-1);
      if (result.substring(result.length()-1).equals("1")) {
        return result.replace("^1", "");
      }
      return result;
    }

    // if the term has no coefficient and in the middle of the expression
    else if (term.substring(0, 3).equals("+x^")){
      // Retrieve the exponent of the term
      String StrPow = term.substring(3);
      int pow = Integer.parseInt(StrPow);
      String result = String.valueOf(pow) + "*x^" + String.valueOf(pow-1);
      if (result.substring(result.length()-1).equals("1")) {
        return result.replace("^1", "");
      }
      return result;
    }

    // if the term has -1 as its coefficient
    else if (term.substring(0, 3).equals("-x^")){
      // Retrieve the exponent of the term
      String StrPow = term.substring(3);
      int pow = Integer.parseInt(StrPow);
      String result = "-" + String.valueOf(pow) + "*x^" + String.valueOf(pow-1);
      if (result.substring(result.length()-1).equals("1")) {
        return result.replace("^1", "");
      }
      return result;
    }

    // If term has x to the power of 1 then return their coefficient
    else if (term.substring(term.length()-1).equals("x") || term.substring(term.length()-1).equals("1")) {
      // If the term begins with + operator then exclude the + sign
      if (term.charAt(0) == '+') {
        return term.substring(1, term.indexOf("*"));
      }
      // Return only the coefficient 
      return term.substring(0, term.indexOf("*"));
    }

    // Otherwise if x is to the power of more than 1
    // Initialize an string that will store as the coefficient
    String coeffStr = "";

    // Initialize index i 
    int i;

    // Iterate through the index of the term
    for (i=0; term.charAt(i) != '*'; i++) {
      coeffStr += term.charAt(i);
    }

    // Initialize empty string that will store the power of x
    String powStr = "";

    // Get the power by skipping *, x, ^ characters
    for (i=i+3; i != term.length(); i++) {
      powStr += term.charAt(i);
    }
    
    // Convert coefficient and power to int types
    int coeff = Integer.parseInt(coeffStr);
    int power = Integer.parseInt(powStr);

    // For ax^n return a(n)x^(n-1) as the rule of differentiation
    String result = String.valueOf(coeff*power) + "*x^" + String.valueOf(power-1);
    if (result.substring(result.length()-1).equals("1")) {
      return result.replace("^1", "");
    }
    return result;
  }

  // Get derivative of the whole equation
  static String derivativeVal(String eq) {

    // If the expression have no x then output zero
    if (!eq.contains("x")) {
      return "0";
    }
    
    // Remove all whitespaces
    eq = eq.replaceAll("\\s", "");

    // Split each term into an array
    String[] eqSplit = eq.split("(?=[-+])");

    // Initialize empty string as answer
    String ans = "";

    // Derive each term using while loop
    int i = 0;
    while (i<eqSplit.length) {
      // Increment answer by the derivative of each term in expression
      if (i == eqSplit.length-1) {
        ans = ans + derivativeTerm(eqSplit[i]);
      }
      else if (eqSplit[i+1].charAt(0) == '-') {
        ans = ans + derivativeTerm(eqSplit[i]);
      }
      else {
        ans = ans + derivativeTerm(eqSplit[i]) + '+';
      }
      i++;
    }
    return ans;
  }

  // Main driver of the program
  public static void main(String[] args) {
    Scanner scan = new Scanner(System.in);
    // Derive math expression from user input
    String output = scan.nextLine();
    System.out.println(derivativeVal(output));
  }
}

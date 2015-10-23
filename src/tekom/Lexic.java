/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tekom;

import java.util.*;

/**
 *
 * @author RTP
 */
public class Lexic {

    private String expression;
    private Scanner sc = new Scanner(System.in);
    private ArrayList<Token> lexic = new ArrayList<>();
    private boolean status;
    private int i;
    private String token="";
    
    public void Read() {
        System.out.println("Masukan ekspresi: ");
        expression = sc.nextLine() + " ";
    }
    
    public void Analyze(int j) {
        //for (int i = 0; i < expression.length(); i++) {
            token = "";
            if (isInteger(expression.charAt(j))) {
                token+=expression.charAt(j);
                j++;
                while (isInteger(expression.charAt(j))) {                    
                    token+=expression.charAt(j);
                    j++;
                }
                if (!isInteger(expression.charAt(j))){
                    lexic.add(new Token(token,"Integer",3));
                    if (j != (expression.length()-1)){
                        Analyze(j);
                        j=expression.length()-1;
                    }
                }        
            }
            
            if (isVariable(expression.charAt(j))){
                token+=expression.charAt(j);
                j++;
                while (isVariable(expression.charAt(j)) || isInteger(expression.charAt(j))) {                    
                    token+=expression.charAt(j);
                    j++;
                }
                if (!isVariable(expression.charAt(j)) || !isInteger(expression.charAt(j))){
                    lexic.add(new Token(token,"Variable",1));
                    if (j < (expression.length()-1)){
                        Analyze(j);
                        j=expression.length()-1;
                    } 
                }
            } 
            if (isOperator(expression.charAt(j))){
                token+=expression.charAt(j);
                lexic.add(new Token(token,"Operator",6));
                j++;
                if (j != (expression.length()-1)){
                    Analyze(j);
                    j=expression.length()-1;
                } 
            }
    }

    public void Print() {
        for (Token x : lexic) {
            System.out.println(x.getItem()+", Lexic Type: "+x.getLexicType()+", Lexic Value: "+x.getLexicToken());
        }
    }

    public boolean isInteger(char Token) {
        status = false;
        if (('1' == Token) || ('2' == Token) || ('3' == Token) || ('4' == Token) || ('5' == Token)
                || ('6' == Token) || ('7' == Token) || ('8' == Token) || ('9' == Token) || ('0' == Token)) {
            status = true;
            return status;
        } else {
            return status;
        }
    }

    public boolean isVariable(char Token) {
        return Character.isAlphabetic(Token);
    }
    
    public boolean isOperator(char Token) {
        status = false;
        if (('+' == Token) || ('-' == Token) || ('*' == Token) || ('/' == Token)){
            status = true;
            return status;
        } else {
            return status;
        }
    }
}

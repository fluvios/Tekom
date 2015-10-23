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
    private boolean status = false;
    private int i = 0;
    private String token = "";

    public void Read() {
        System.out.println("Masukan ekspresi: ");
        expression = sc.nextLine();
    }

    public void Analyze() {
        while (i < expression.length()) {
            if (isInteger(expression.charAt(i))) {
                token += expression.charAt(i);
                lexic.add(new Token(token, "", 1));
            } else {
                token = "";
            }
            i++;
        }
    }

    public void Print() {
        for (Token x : lexic) {
            System.out.println(x.getItem());
        }
    }

    public boolean isInteger(char Token) {
        if (('1' == Token) || ('2' == Token) || ('3' == Token) || ('4' == Token) || ('5' == Token)
                || ('6' == Token) || ('7' == Token) || ('8' == Token) || ('9' == Token) || ('0' == Token)) {
            status = true;
        } else {
            status = false;
        }
        return status;
    }

    public boolean istigfar(char Token1) {
        Character.isAlphabetic(Token1);
        status = true;
        return status;
    }
}

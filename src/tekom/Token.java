/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tekom;

/**
 *
 * @author RTP
 */
public class Token {
    private String item,lexicType;
    private int lexicToken;

    public Token() {
    }

    public Token(String item, String lexicType, int lexicToken) {
        this.item = item;
        this.lexicType = lexicType;
        this.lexicToken = lexicToken;
    }

    public String getItem() {
        return item;
    }

    public int getLexicToken() {
        return lexicToken;
    }

    public String getLexicType() {
        return lexicType;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public void setLexicToken(int lexicToken) {
        this.lexicToken = lexicToken;
    }

    public void setLexicType(String lexicType) {
        this.lexicType = lexicType;
    }
}

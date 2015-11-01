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
    public ArrayList<Token> lexic = new ArrayList<>();
    private boolean status;
    private int i, countG, countEG;
    private String token = "";

    /**
     * Method untuk membaca inputan dari GUI I : String dari GUI text O :
     * Variabel expresi yang berisi string yang diakhirnya ditambahkan spasi
     */
    public void Read(String words) {
//        System.out.println("Masukan ekspresi: ");
        expression = words + " ";
    }

    /**
     * Method untuk menganalisa leksik setiap karakter pada indeks string dari
     * ekspersi I : variabel string O : string yang memenuhi kondisi suatu
     * leksik,tipe leksiknya dan nilai token leksik
     */
    public void Analyze(int j) {
        token = "";

        /**
         * Pembacaan bilangan integer
         */
        if (isInteger(expression.charAt(j))) {
            token += expression.charAt(j);
            j++;
            while (isInteger(expression.charAt(j))) {
                token += expression.charAt(j);
                j++;
            }

            /**
             * Pembacaan bilangan real
             */
            if (!isInteger(expression.charAt(j))) {
                if ((expression.charAt(j) == '.' || expression.charAt(j) == ',') && (isInteger(expression.charAt(j + 1)))) {
                    token += expression.charAt(j);
                    j++;
                    while (isInteger(expression.charAt(j))) {
                        token += expression.charAt(j);
                        j++;
                    }
                    if (!isInteger(expression.charAt(j))) {
                        if (expression.charAt(j) == 'e' || expression.charAt(j) == 'E') {
                            token += expression.charAt(j);
                            j++;
                            while (isInteger(expression.charAt(j))) {
                                token += expression.charAt(j);
                                j++;
                            }
                            if (!isInteger(expression.charAt(j))) {
                                if (isFloat(expression.charAt(j))) {
                                    token += expression.charAt(j);
                                    j++;
                                    while (isInteger(expression.charAt(j))) {
                                        token += expression.charAt(j);
                                        j++;
                                    }
                                    if (!isInteger(expression.charAt(j))) {
                                        lexic.add(new Token(token, "Real", 2));
                                        if (j != (expression.length() - 1)) {
                                            Analyze(j);
                                            j = expression.length() - 1;
                                        }
                                    }
                                } else {
                                    lexic.add(new Token(token, "Real", 2));
                                    if (j != (expression.length() - 1)) {
                                        Analyze(j);
                                        j = expression.length() - 1;
                                    }
                                }
                            }
                        } else {
                            lexic.add(new Token(token, "Real", 2));
                            if (j != (expression.length() - 1)) {
                                Analyze(j);
                                j = expression.length() - 1;
                            }
                        }
                    }
                    /**
                     * pembacaan real dengan aturan floating point
                     */
                } else if ((expression.charAt(j) == 'e' || expression.charAt(j) == 'E') && (expression.charAt(j + 1) != ' ')) {
                    token += expression.charAt(j);
                    j++;
                    while (isInteger(expression.charAt(j))) {
                        token += expression.charAt(j);
                        j++;
                    }
                    if (!isInteger(expression.charAt(j))) {
                        if (isFloat(expression.charAt(j))) {
                            token += expression.charAt(j);
                            j++;
                            while (isInteger(expression.charAt(j))) {
                                token += expression.charAt(j);
                                j++;
                            }
                            if (!isInteger(expression.charAt(j))) {
                                lexic.add(new Token(token, "Real", 2));
                                if (j != (expression.length() - 1)) {
                                    Analyze(j);
                                    j = expression.length() - 1;
                                }
                            }
                        } else {
                            lexic.add(new Token(token, "Real", 2));
                            if (j != (expression.length() - 1)) {
                                Analyze(j);
                                j = expression.length() - 1;
                            }
                        }
                    }
                } else if ((expression.charAt(j) == '.' || expression.charAt(j) == ',') && (!isInteger(expression.charAt(j + 1)))) {
                    j++;
                    if (j != (expression.length() - 1)) {
                        Analyze(j);
                        j = expression.length() - 1;
                    }
                } else if ((expression.charAt(j) == 'e' || expression.charAt(j) == 'E') && (expression.charAt(j + 1) == ' ')) {
                    j++;
                    if (j != (expression.length() - 1)) {
                        Analyze(j);
                        j = expression.length() - 1;
                    }
                } else {
                    lexic.add(new Token(token, "Integer", 3));
                    if (j != (expression.length() - 1)) {
                        Analyze(j);
                        j = expression.length() - 1;
                    }
                }
            }
        }

        /**
         * pembacaan variabel
         */
        if (isVariable(expression.charAt(j))) {
            token += expression.charAt(j);
            j++;
            while (isVariable(expression.charAt(j)) || isInteger(expression.charAt(j)) || expression.charAt(j) == '_') {
                token += expression.charAt(j);
                j++;
            }
            if (!isVariable(expression.charAt(j)) || !isInteger(expression.charAt(j))) {
                lexic.add(new Token(token, "Variable", 1));
                if (j < (expression.length() - 1)) {
                    Analyze(j);
                    j = expression.length() - 1;
                }
            }
        }

        /**
         * pembacaan simbol buka kurung
         */
        if (isGSymbol(expression.charAt(j))) {
            token += expression.charAt(j);
            lexic.add(new Token(token, "Grouping Symbol", 4));
            countG++;
            j++;
            if (j != (expression.length() - 1)) {
                Analyze(j);
                j = expression.length() - 1;
            }
        }

        /**
         * pembacaan simbol tutup kurung
         */
        if (isEGSymbol(expression.charAt(j))) {
            token += expression.charAt(j);
            lexic.add(new Token(token, "Grouping Symbol", 5));
            countEG++;
            j++;
            if (j != (expression.length() - 1)) {
                Analyze(j);
                j = expression.length() - 1;
            }
        }

        /**
         * pembacaan operator tambah
         */
        if (isPlus(expression.charAt(j))) {
            token += expression.charAt(j);
            lexic.add(new Token(token, "Operator", 6));
            j++;
            if (j != (expression.length() - 1)) {
                Analyze(j);
                j = expression.length() - 1;
            }
        }

        /**
         * pembacaan operator kurang
         */
        if (isMinus(expression.charAt(j))) {
            token += expression.charAt(j);
            lexic.add(new Token(token, "Operator", 7));
            j++;
            if (j != (expression.length() - 1)) {
                Analyze(j);
                j = expression.length() - 1;
            }
        }

        /**
         * pembacaan operator kali
         */
        if (isMultiple(expression.charAt(j))) {
            token += expression.charAt(j);
            lexic.add(new Token(token, "Operator", 8));
            j++;
            if (j != (expression.length() - 1)) {
                Analyze(j);
                j = expression.length() - 1;
            }
        }

        /**
         * pembacaan operator bagi
         */
        if (isDivide(expression.charAt(j))) {
            token += expression.charAt(j);
            lexic.add(new Token(token, "Operator", 9));
            j++;
            if (j != (expression.length() - 1)) {
                Analyze(j);
                j = expression.length() - 1;
            }
        }
    }

    /**
     * Method untuk menampilkan hasil dari method analyze I : - O : string yang
     * memenuhi kondisi suatu leksik,tipe leksiknya dan nilai token leksik
     */
    public void Print() {
        for (Token x : lexic) {
            System.out.println(x.getItem() + ", Lexic Type: " + x.getLexicType() + ", Lexic Value: " + x.getLexicToken());
        }
    }

    /**
     * Method untuk melakukan pengecekan karakter apakah bernilai integer I :
     * karakter dari string O : boolean
     */
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

    /**
     * Method untuk melakukan pengecekan karakter apakah bernilai variabel I :
     * karakter dari string O : boolean
     */
    public boolean isVariable(char Token) {
        return Character.isAlphabetic(Token);
    }

    /**
     * Method untuk melakukan pengecekan karakter apakah merupakan operator
     * tambah I : karakter dari string O : boolean
     */
    public boolean isPlus(char Token) {
        status = false;
        if (('+' == Token)) {
            status = true;
            return status;
        } else {
            return status;
        }
    }

    /**
     * Method untuk melakukan pengecekan karakter apakah merupakan operator
     * kurang I : karakter dari string O : boolean
     */
    public boolean isMinus(char Token) {
        status = false;
        if (('-' == Token)) {
            status = true;
            return status;
        } else {
            return status;
        }
    }

    /**
     * Method untuk melakukan pengecekan karakter apakah merupakan operator kali
     * I : karakter dari string O : boolean
     */
    public boolean isMultiple(char Token) {
        status = false;
        if (('*' == Token)) {
            status = true;
            return status;
        } else {
            return status;
        }
    }

    /**
     * Method untuk melakukan pengecekan karakter apakah merupakan operator bagi
     * I : karakter dari string O : boolean
     */
    public boolean isDivide(char Token) {
        status = false;
        if (('/' == Token)) {
            status = true;
            return status;
        } else {
            return status;
        }
    }

    /**
     * Method untuk melakukan pengecekan karakter apakah merupakan kurung buka I
     * : karakter dari string O : boolean
     */
    public boolean isGSymbol(char Token) {
        status = false;
        if ('(' == Token) {
            status = true;
            return status;
        } else {
            return status;
        }
    }

    /**
     * Method untuk melakukan pengecekan karakter apakah merupakan kurung tutup
     * I : karakter dari string O : boolean
     */
    public boolean isEGSymbol(char Token) {
        status = false;
        if (')' == Token) {
            status = true;
            return status;
        } else {
            return status;
        }
    }

    /**
     * Method untuk melakukan pengecekan karakter apakah merupakan eksponen dari
     * floating point I : karakter dari string O : boolean
     */
    public boolean isFloat(char Token) {
        status = false;
        if ('+' == Token || '-' == Token) {
            status = true;
            return status;
        } else {
            return status;
        }
    }
}

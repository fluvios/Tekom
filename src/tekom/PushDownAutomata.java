/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tekom;

import java.util.Stack;

/**
 *
 * @author RTP
 */
public class PushDownAutomata {

    private Stack s = new Stack();
    private boolean status;

    public void Validator(int i, Lexic l) {
        // Insialisasi Stack
        s.push("#");
        if (i < l.tokenPoints.size()) {
            // Cek kalau bilangan pertama bukan operand atau grouping symbol
            if (l.tokenPoints.get(i) != 1 || l.tokenPoints.get(i) != 2 || l.tokenPoints.get(i) != 3) {
                i += l.tokenPoints.size();
                Validator(i, l);
            }// else {
//                // Insialisasi Pola CFG
//                s.push("A");
//                s.push("O");
//                s.push("A");
//                i += 1;
//                // Lakukan pengecekan indeks ke satu
//                if (l.tokenPoints.get(i) != 1 || l.tokenPoints.get(i) != 2 || l.tokenPoints.get(i) != 3) {
//                    s.pop();
//                    i += 1;
//                    // Cek indeks selanjutnya 
//                    while (i < l.tokenPoints.size()) {
//                        // pengecekan indeks ke dua
//                        if (l.tokenPoints.get(i) == 6) {
//                            s.pop();
//                            i += 1;
//                            if (l.tokenPoints.get(+1) == 1 || l.tokenPoints.get(+1) == 2 || l.tokenPoints.get(+1) == 3) {
//                                s.pop();
//                                if (s.elementAt(i).equals("#")) {
//                                    s.pop();
//                                    status = true;
//                                }
//                            }
//                        }
//                    }
//                }
//            }
        }
        // Cek kondisi stack
        status = StackCondition();
    }

    public boolean StackCondition() {
        if (s.empty()) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        String stat;
        if (status == true) {
            stat = "Valid";
        } else {
            stat = "Tidak Valid";
        }
        return stat;
    }
}

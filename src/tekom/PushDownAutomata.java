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
        if (i == 0) {
            s.push("#");
        }
        if (i < l.tokenPoints.size()) {
            // Cek kalau bilangan pertama bukan operand atau grouping symbol
            if (l.tokenPoints.get(i) == 6 || l.tokenPoints.get(i) == 7 || l.tokenPoints.get(i) == 8 || l.tokenPoints.get(i) == 9) {
                s.push(l.tokenPoints.get(i));
                i += 1;
                Validator(i, l);
            } else if (l.tokenPoints.get(i) == 1 || l.tokenPoints.get(i) == 2 || l.tokenPoints.get(i) == 3) {
                s.pop();
                i += 1;
                Validator(i, l);
            } else if (l.tokenPoints.get(i) == 4) {
                if (StackCondition() == true) {
                    s.push(l.tokenPoints.get(i));
                    s.push("#");
                } else {
                    s.push(l.tokenPoints.get(i));
                }
                i += 1;
                Validator(i, l);
            } else if (l.tokenPoints.get(i) == 5) {
                s.pop();
                i += 1;
                Validator(i, l);
            }
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
        String stat = null;
        if (status == true) {
            stat = "Valid";
        } else if (status == false) {
            stat = "Tidak Valid";
        }
        return stat;
    }
}

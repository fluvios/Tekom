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
public class Tekom {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        boolean b;
        Lexic l = new Lexic();
        PushDownAutomata p = new PushDownAutomata();
        l.Read();
        l.Analyze(0);
        l.Generate();
        p.Validator(0,l);
        l.Print();
//        new View().setVisible(true);
    }
    
}

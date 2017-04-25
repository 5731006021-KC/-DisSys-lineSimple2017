/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lineclient;
import java.util.Random;
/**
 *
 * @author M4lZK
 */
public class LineClient {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Random rand = new Random();
        int  cid = rand.nextInt(999) + 100;
        ChatMain c = new ChatMain(cid);
        c.setVisible(true);
    }
    
}

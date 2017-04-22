/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lineclient;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author M4lZK
 */
public class ChatMain extends javax.swing.JFrame {

    /**
     * Creates new form ChatMain
     */
    String username; String address = "localhost";
    //ArrayList<Integer> friends = new ArrayList();
    int port = 2222;
    Boolean isConnected = false;
    String gid;
    ArrayList<String> groups = new ArrayList();
    GroupChat groupChat[] = new GroupChat[1001];
    
    Socket sock;
    BufferedReader reader;
    PrintWriter writer;
    
    public void createGroup(String groupID,ArrayList<String> cid){
        int length = cid.size();
        String participant = "";
        for(String x : cid){
            participant = participant + x + ":";
        }
        writer.println(username + ":" + groupID +":"+ "CG" + ":" + length + ":" + participant);
        writer.flush();
    }

    private ChatMain() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public void ListenThread() 
    {
         Thread IncomingReader = new Thread(new IncomingReader());
         IncomingReader.start();
    }
    
    
    public class IncomingReader implements Runnable
    {
        @Override
        public void run() 
        {
            String[] data;
            String stream, chat = "Chat";

            try 
            {
                while ((stream = reader.readLine()) != null) 
                {
                     data = stream.split(":");
             
                     if (data[1].equals("Invite")&&data[3].equals(username)){
          int reply = JOptionPane.showConfirmDialog(null, "You're invited to GroupID "+data[2], null, JOptionPane.YES_NO_OPTION);
        if (reply == JOptionPane.YES_OPTION) {
         
          if(groups.contains(data[2])) {
              groupChat[Integer.parseInt(data[2])].setVisible(true);
              System.out.print("aaaaaaaaaaa");
          }
          else{    GroupChat gc = new GroupChat(username,data[2]);
          gc.setVisible(true);
          groups.add(data[2]);
          groupChat[Integer.parseInt(data[2])]=gc;
          }
        }
                     }
       /* else if (data[2].equals("Block")) {
                        
                        tellEveryone(data[0]+"leave the group","",data[3]);
        } */
        else if (data[0].equals("ServerDown")){
          reconnectTo2();
        }
          
                }
           }catch(Exception ex) { }
        }
    }
    
    public void reconnectTo2(){
         try 
            {   sock.close();
                sock = new Socket(address, 1111);
                InputStreamReader streamreader = new InputStreamReader(sock.getInputStream());
                reader = new BufferedReader(streamreader);
                writer = new PrintWriter(sock.getOutputStream());
            } 
            catch (Exception ex) 
            {
             
            }
            
           ListenThread();
    }
    public ChatMain(int username) {
        this.username = Integer.toString(username);
        initComponents();
        if (isConnected == false) 
        {
           

            try 
            {
                sock = new Socket(address, port);
                InputStreamReader streamreader = new InputStreamReader(sock.getInputStream());
                reader = new BufferedReader(streamreader);
                writer = new PrintWriter(sock.getOutputStream());
                writer.println(username + ":has connected.:Connect");
                writer.flush();
                isConnected = true; 
            } 
            catch (Exception ex) 
            {
             
            }
            
           ListenThread();
        } 
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cID = new javax.swing.JLabel();
        getOnline_button = new javax.swing.JButton();
        exit_button = new javax.swing.JButton();
        Join_button = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jTextField1 = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        cID.setText("Username : ");
        cID.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                cIDPropertyChange(evt);
            }
        });

        getOnline_button.setText("Show Group");
        getOnline_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                getOnline_buttonActionPerformed(evt);
            }
        });

        exit_button.setText("Exit");
        exit_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exit_buttonActionPerformed(evt);
            }
        });

        Join_button.setText("Join");
        Join_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Join_buttonActionPerformed(evt);
            }
        });

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 44, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(getOnline_button, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(44, 44, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(exit_button, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(Join_button)))
                                .addGap(0, 16, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(cID)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(0, 3, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cID)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Join_button, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(36, 36, 36)
                        .addComponent(exit_button)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(getOnline_button))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void exit_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exit_buttonActionPerformed
         System.exit(0);// TODO add your handling code here:
    }//GEN-LAST:event_exit_buttonActionPerformed

    private void Join_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Join_buttonActionPerformed
    if(groups.contains(jTextField1.getText())) groupChat[Integer.parseInt(jTextField1.getText())].setVisible(true);
    else{    GroupChat gc = new GroupChat(username,jTextField1.getText());
          gc.setVisible(true);
          groups.add(jTextField1.getText());
          groupChat[Integer.parseInt(jTextField1.getText())]=gc;
    }
    // TODO add your handling code here:
    }//GEN-LAST:event_Join_buttonActionPerformed

    private void cIDPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_cIDPropertyChange
        cID.setText("cID : "+username);// TODO add your handling code here:
    }//GEN-LAST:event_cIDPropertyChange

    private void getOnline_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_getOnline_buttonActionPerformed
        if(groups.size()<=0){
            jTextArea1.append("Client didn't join anygroup \n");
        }
        for(int i=0;i<groups.size();i++){
            jTextArea1.append(groups.get(i)+" \n");
        }// TODO add your handling code here:
    }//GEN-LAST:event_getOnline_buttonActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ChatMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ChatMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ChatMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ChatMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ChatMain().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Join_button;
    private javax.swing.JLabel cID;
    private javax.swing.JButton exit_button;
    private javax.swing.JButton getOnline_button;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
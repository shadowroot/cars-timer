/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cars;

import com.sun.xml.internal.ws.api.ha.StickyFeature;
import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;


/**
 *
 * @author jonny
 */
public class mainFrame extends javax.swing.JFrame {

    private static int[] readyRacers=null;
    private static GroupLayout layout=null;
    private static JPanel pan=null;
    private static Graphics offscreen=null;
    private static JCheckBox[] chRacers=null;
    private static JCheckBox[] boxes = null;
    
    
    /**
     * Creates new form mainFrame
     */
    public mainFrame() {
        initComponents();
        paint_time();
        //</editor-fold>

        /*
         * Create and display the form
         */

        this.setVisible(true);
    }
    
    public javax.swing.JMenu getComMenu(){
        return jMenu4;
    }


    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        timeLabel = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButton1.setText("Zavod");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 372, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 313, Short.MAX_VALUE)
        );

        timeLabel.setText("jLabel1");

        jMenu1.setText("File");

        jMenuItem1.setText("Novy zavod");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuItem4.setText("Konec");
        jMenu1.add(jMenuItem4);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Vysledky");

        jMenuItem2.setText("Zobrazit");
        jMenu2.add(jMenuItem2);

        jMenuItem3.setText("Export");
        jMenu2.add(jMenuItem3);

        jMenuBar1.add(jMenu2);

        jMenu3.setText("Zavodnici");

        jMenuItem5.setText("Pridat zavodnika");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem5);

        jMenuItem6.setText("Import zavodniku");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem6);

        jMenuItem7.setText("Delete zavodniku");
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem7);

        jMenuBar1.add(jMenu3);

        jMenu4.setText("COM_PORT");
        jMenuBar1.add(jMenu4);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addContainerGap(512, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(timeLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(24, 24, 24))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(timeLabel)
                        .addGap(323, 323, 323)
                        .addComponent(jButton1)))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        new racers();
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
        JFileChooser ch = new JFileChooser();
        int ret= ch.showDialog(null, "Otevrit soubor");
        if(ret == JFileChooser.APPROVE_OPTION){
            File file = ch.getSelectedFile();
            try {
                Cars.readCSV(file.getAbsolutePath(),Cars.credentials);
                mainFrame.addRacers(Cars.credentials);
            } catch (IOException ex) {
                Logger.getLogger(mainFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
    }//GEN-LAST:event_jMenuItem6ActionPerformed

    public static void addRacers(String[][] cars){
        
        chRacers = new JCheckBox[cars.length];
        for(int i=0;i<cars.length;i++){
            
            
            chRacers[i] = new JCheckBox();
            chRacers[i].setVisible(true);
            chRacers[i].setText(i+"  "+cars[i][0]+"  "+cars[i][1]+"  "+cars[i][2]);
            chRacers[i].setName(""+i+"");
            chRacers[i].setSize(1000, 20);
            chRacers[i].setLocation(20, i*30);
            
            
            jPanel1.add(chRacers[i]);
            
            
       
        }
      
        
        
      jPanel1.repaint();
     
        

        
    }
    public static void paint_time(){
        Timer t = new Timer(100, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                Date d = new Date();
                timeLabel.setText(d.toString());
                mainFrame.getFrames()[0].repaint();
            }
        });
        t.start();
   }
    
    
    public static void addingRacers(java.awt.event.ActionEvent e){
        int[]tint = new int[readyRacers.length+1];
        tint = readyRacers;
        readyRacers=tint;
        readyRacers[readyRacers.length-1]=Integer.parseInt(e.getActionCommand().split("_")[1]);
    }
    
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        int[] selected=null;
        
        for(int i=0;i<chRacers.length;i++){
            if(chRacers[i].isSelected()){
                
                int[] tmp = new int[i+1];
                if(selected != null){
                    functions.cpyArrays(selected, tmp);
                }
                tmp[i]=i;
                selected=tmp;
            }
        }
        Cars.determineRacers(selected);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed
            JFrame del = new JFrame("Delete them");
            del.setSize(300, 300);
            JButton smazat = new JButton("Smazat");
            
            if(Cars.credentials != null){
                        boxes = new JCheckBox[Cars.credentials.length];
                        
                        for(int i=0;i<Cars.credentials.length;i++){
                            boxes[i] = new JCheckBox();
                            boxes[i].setText(i+"  "+Cars.credentials[i][0]+"  "+Cars.credentials[i][1]+"  "+Cars.credentials[i][2]);
                            boxes[i].setSize(1000, 20);
                            boxes[i].setLocation(10, i*30);
                            
                        }
            }
            smazat.setLocation(del.WIDTH-120, del.HEIGHT-20);
            smazat.setSize(20, 100);
            smazat.addActionListener(new java.awt.event.ActionListener() {
            @Override
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    int u=0;
                     String[][] tmpCred = new String[Cars.credentials.length][Cars.cols];
                        for(int i=0;i<Cars.credentials.length;i++){
                           
                            if(!boxes[i].isSelected()){
                                tmpCred[u] = Cars.credentials[i];
                                u++;
                            }
                            
                        }
                        Cars.credentials = tmpCred;
                try {
                    Cars.writeCSV(Cars.credentials);
                    mainFrame.addRacers(Cars.credentials);
                } catch (IOException ex) {
                    Logger.getLogger(mainFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
                        
                    }
            });
            del.add(smazat);
            del.setVisible(true);
    }//GEN-LAST:event_jMenuItem7ActionPerformed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private static javax.swing.JPanel jPanel1;
    private static javax.swing.JLabel timeLabel;
    // End of variables declaration//GEN-END:variables
    
}

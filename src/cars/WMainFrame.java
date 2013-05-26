/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cars;

import gnu.io.CommPortIdentifier;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.util.Date;
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;


/**
 *
 * @author jonny
 */
public class WMainFrame extends javax.swing.JFrame {

    private CCars cars;
    private static  boolean connected;
    private JCheckBoxMenuItem[] com_ports;
    private JCheckBox[] chRacers;
    private WRace wRace;
    
    
    /**
     * Creates new form mainFrame
     */
    public WMainFrame(CCars cars) {
        initComponents();
        paint_time();
        this.cars = cars;
        connected = false;
        //</editor-fold>

        /**
         * Create and display the form
         */
        addComm();
        this.setVisible(true);
        addRacers();
    }
    
    public javax.swing.JMenu getComMenu(){
        return jMenu4;
    }
    /**
     * 
     */
    public void init(){
        jComboBox1.addItem(EClass.F103);
        jComboBox1.addItem(EClass.C4X4);
        jComboBox1.addItem(EClass.Open);
        
    }
    
    private void addComm(){
        Enumeration e = CommPortIdentifier.getPortIdentifiers();
        
        int i=0;
         
         com_ports = new JCheckBoxMenuItem[1];
       
        while(e.hasMoreElements()){
            JCheckBoxMenuItem[] tmpChk = new JCheckBoxMenuItem[com_ports.length+1];
          CommPortIdentifier cpi = (CommPortIdentifier)e.nextElement(); 
          String name=cpi.getName();
          System.arraycopy(com_ports, 0, tmpChk, 0, com_ports.length);
          com_ports = tmpChk;
        com_ports[i] = new JCheckBoxMenuItem();
        com_ports[i].setSelected(false);
        com_ports[i].setText(name);
        com_ports[i].setActionCommand(name);
        
        
        com_ports[i].addActionListener(new java.awt.event.ActionListener() {
                private String cPort;
                @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cPort = evt.getActionCommand();
                for(int i=0;i<com_ports.length;i++){
                    if(com_ports[i] != null){
                        if(com_ports[i] != evt.getSource()){
                            com_ports[i].setSelected(false);
                        }
                    }
                }
                cars.serial = new CSerial(cars);
                try {
                    cars.serial.connect(cPort);
                } catch (Exception ex) {
                    Logger.getLogger(RunCars.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        i++;
        
        
        }
        
        for(int u=0; u < i; u++){
            jMenu4.add(com_ports[u]);
        }
        
    }


    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        timeLabel = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox();
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
                        .addGap(18, 18, 18)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(timeLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 143, Short.MAX_VALUE)
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
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton1)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        WRacers racers = new WRacers(cars);
        
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
        JFileChooser ch = new JFileChooser();
        int ret= ch.showDialog(null, "Otevrit soubor");
        if(ret == JFileChooser.APPROVE_OPTION){
            File file = ch.getSelectedFile();
            
        }
    }//GEN-LAST:event_jMenuItem6ActionPerformed
    /**
     * Adding to main pannel
     */
    public void addRacers(){
        jPanel1.removeAll();
        chRacers = new JCheckBox[cars.racers.size()];
        
        for(int i=0;i<cars.racers.size();i++){
            
            
            chRacers[i] = new JCheckBox();
            chRacers[i].setVisible(true);
            chRacers[i].setText(cars.racers.get(i).id + " "+cars.racers.get(i).Name());
            chRacers[i].setName(""+i+"");
            chRacers[i].setSize(1000, 20);
            chRacers[i].setLocation(20, i*30);
            
            
            jPanel1.add(chRacers[i]);
        }
      
        
        
      jPanel1.repaint();
    }
    public  void paint_time(){
        Timer t = new Timer(100, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                Date d = new Date();
                timeLabel.setText(d.toString());
                WMainFrame.getFrames()[0].repaint();
            }
        });
        t.start();
   }
    
    public void redrawRacersPanel(){
        
    }
    
   public void openRaceWindow(LinkedList<CRacer> racers_list, EClass category){
       wRace = new WRace(cars,category,racers_list);
   }
    
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        LinkedList<CRacer> list = new LinkedList<CRacer>();
        EClass model_class = (EClass) jComboBox1.getSelectedItem();
        for(int i=0;i<chRacers.length;i++){
            if(chRacers[i].isSelected() && cars.racers.get(i).classes.contains(model_class)){
                list.add(cars.racers.get(i));
            }
        }
        openRaceWindow(list,model_class);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed
          JFrame frame = new JFrame();
          
    }//GEN-LAST:event_jMenuItem7ActionPerformed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox jComboBox1;
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

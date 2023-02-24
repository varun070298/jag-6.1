/*   Copyright (C) 2003 Finalist IT Group
 *
 *   This file is part of JAG - the Java J2EE Application Generator
 *
 *   JAG is free software; you can redistribute it and/or modify
 *   it under the terms of the GNU General Public License as published by
 *   the Free Software Foundation; either version 2 of the License, or
 *   (at your option) any later version.
 *   JAG is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *   GNU General Public License for more details.
 *   You should have received a copy of the GNU General Public License
 *   along with JAG; if not, write to the Free Software
 *   Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 */
package com.finalist.jaggenerator;

import javax.swing.*;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author  hillie
 */
public class ConnectDialog extends javax.swing.JDialog {

   private JagGenerator parent;
   private static final String DRIVER = "driver";
   private String[] showTableTypes = null;
   private boolean offLineMode = false;


   /** Creates new form ConnectDialog */
   public ConnectDialog(JagGenerator parent) {
      super(parent, true);
      this.parent = parent;
      initComponents();
      this.setTitle("Connect to database");
      this.setLocation(50, 150);
      String url = parent.root.datasource.getJdbcUrl().toString();
      String user = parent.root.datasource.userNameText.getText();
      String password = parent.root.datasource.passwordText.getText();

      if (url == null)
         urlTextField.setText("jdbc:oracle:thin:@localhost:1521:orcl");
      else
         urlTextField.setText(url);
      if (user == null)
         user = "";
      usernameTextField.setText(user);
      if (password == null)
         password = "";
      passwordField.setText(password);
   }


   /** This method is called from within the constructor to
    * initialize the form.
    * WARNING: Do NOT modify this code. The content of this method is
    * always regenerated by the Form Editor.
    */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        urlLabel = new javax.swing.JLabel();
        usernameLabel = new javax.swing.JLabel();
        passwordLabel = new javax.swing.JLabel();
        urlTextField = new javax.swing.JTextField();
        usernameTextField = new javax.swing.JTextField();
        connectButton = new javax.swing.JButton();
        passwordField = new javax.swing.JPasswordField();
        passwordLabel1 = new javax.swing.JLabel();
        showTablesRadioButton = new javax.swing.JCheckBox();
        showViewsRadioButton = new javax.swing.JCheckBox();
        showSynonymsRadioButton = new javax.swing.JCheckBox();
        connectButton1 = new javax.swing.JButton();

        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                closeDialog(evt);
            }
        });

        urlLabel.setText("Database URL");
        getContentPane().add(urlLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 120, -1));

        usernameLabel.setText("Username");
        getContentPane().add(usernameLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, 100, -1));

        passwordLabel.setText("Password");
        getContentPane().add(passwordLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, -1, -1));

        getContentPane().add(urlTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 20, 370, -1));

        getContentPane().add(usernameTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 40, 170, -1));

        connectButton.setText("Connect");
        connectButton.setToolTipText("Connect to the database");
        connectButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                connectButtonActionPerformed(evt);
            }
        });

        getContentPane().add(connectButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 130, -1, -1));

        getContentPane().add(passwordField, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 60, 170, -1));

        passwordLabel1.setText("Show");
        getContentPane().add(passwordLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, -1, -1));

        showTablesRadioButton.setSelected(true);
        showTablesRadioButton.setLabel("tables");
        showTablesRadioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showTablesRadioButtonActionPerformed(evt);
            }
        });

        getContentPane().add(showTablesRadioButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 90, -1, -1));

        showViewsRadioButton.setText("views");
        getContentPane().add(showViewsRadioButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 90, -1, -1));

        showSynonymsRadioButton.setText("synonyms");
        getContentPane().add(showSynonymsRadioButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 90, -1, -1));

        connectButton1.setText("Offline");
        connectButton1.setToolTipText("Work in offline mode");
        connectButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                offlineButtonActionPerformed(evt);
            }
        });

        getContentPane().add(connectButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 130, -1, -1));

        pack();
    }
    // </editor-fold>//GEN-END:initComponents

    private void offlineButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_offlineButtonActionPerformed
// TODO add your handling code here:
        parent.setOfflineMode(true);
        this.dispose();
    }//GEN-LAST:event_offlineButtonActionPerformed

   private void showTablesRadioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showTablesRadioButtonActionPerformed
      // TODO add your handling code here:
   }//GEN-LAST:event_showTablesRadioButtonActionPerformed


   private void connectButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_connectButtonActionPerformed
      parent.setOfflineMode(false);
      String url = urlTextField.getText();
      String username = usernameTextField.getText();
      String password = new String(passwordField.getPassword());
      java.util.ArrayList theTypes = new java.util.ArrayList();
      if (this.showTablesRadioButton.isSelected()) {
         theTypes.add("TABLE");
      }
      if (this.showSynonymsRadioButton.isSelected()) {
         theTypes.add("SYNONYM");
      }
      if (this.showViewsRadioButton.isSelected()) {
         theTypes.add("VIEW");
      }
      showTableTypes = new String[theTypes.size()];
      for (int i = 0; i < theTypes.size(); i++) {
         showTableTypes[i] = (String) theTypes.get(i);
      }
      Connection con = null;

      String databaseType = parent.root.datasource.getDatabase().getDbName();
      parent.setConManager(
            new GenericJdbcManager(url, username, password, parent.root.datasource.getDatabase().getDriverClass(), showTableTypes));

      // Assume the connection will fail.
      String connectLabel = "Database Connection: failed";
      String connectToolTip = "Database Connection: failed to " + databaseType + " using: url=" + url + " username=" + username + " pasword=" + password;
      try {
         con = parent.getConManager().connect();
         JagGenerator.logToConsole("Connected to " + databaseType + " at " + url);
         connectLabel = "Database Connection: connected";
         connectToolTip = "Database Connection: connected to " + databaseType + " using: url=" + url + " username=" + username + " pasword=" + password;
         parent.databaseConnectionLabel.setText(connectLabel);
         parent.databaseConnectionLabel.setToolTipText(connectToolTip);

      } catch (ClassNotFoundException e) {
         requiresRestartWarning(e.getMessage(), databaseType, connectLabel, connectToolTip);

      } catch (SQLException e) {
         e.printStackTrace();
         if (e.getMessage().indexOf(DRIVER) != -1) {
            requiresRestartWarning(e.getMessage(), databaseType, connectLabel, connectToolTip);
         } else {
            genericConnectError(e, connectLabel, connectToolTip);
         }
      } catch (Exception e) {
         genericConnectError(e, connectLabel, connectToolTip);

      } finally {
         if (con != null) {
            try {
               con.close();
            } catch (Exception e) {
               JagGenerator.logToConsole("Connection couldn't be closed");
            }
         }
      }
      this.dispose();
   }//GEN-LAST:event_connectButtonActionPerformed

   private void genericConnectError(Exception e, String connectLabel, String connectToolTip) {
      e.printStackTrace();
      JagGenerator.logToConsole("Error while connecting! - " + e);
      parent.databaseConnectionLabel.setText(connectLabel);
      parent.databaseConnectionLabel.setToolTipText(connectToolTip);
      parent.setConManager(null);
   }

   private void requiresRestartWarning(String message, String databaseType, String connectLabel, String connectToolTip) {
      JagGenerator.logToConsole("Database driver problem!  Driver class: " + message);
      String msg = "The driver you are trying to use to connect to the database is not working.\n" +
            "Please check that you have chosen the correct 'Database Type' in the Datasource configuration screen.\n\n" +
            "NOTE: If you added the driver for " + databaseType + " databases during this session,\n" +
            "you'll  need to restart JAG first to make the necessary driver available.  Sorry!";
      JagGenerator.logToConsole("\n" + msg + "\n");
      JOptionPane.showMessageDialog(JagGenerator.jagGenerator,
            msg, "Driver problems!", JOptionPane.INFORMATION_MESSAGE);

      parent.databaseConnectionLabel.setText(connectLabel);
      parent.databaseConnectionLabel.setToolTipText(connectToolTip);
      parent.setConManager(null);
   }


   /** Closes the dialog */
   private void closeDialog(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_closeDialog

   }//GEN-LAST:event_closeDialog


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton connectButton;
    private javax.swing.JButton connectButton1;
    private javax.swing.JPasswordField passwordField;
    private javax.swing.JLabel passwordLabel;
    private javax.swing.JLabel passwordLabel1;
    private javax.swing.JCheckBox showSynonymsRadioButton;
    private javax.swing.JCheckBox showTablesRadioButton;
    private javax.swing.JCheckBox showViewsRadioButton;
    private javax.swing.JLabel urlLabel;
    private javax.swing.JTextField urlTextField;
    private javax.swing.JLabel usernameLabel;
    private javax.swing.JTextField usernameTextField;
    // End of variables declaration//GEN-END:variables

}
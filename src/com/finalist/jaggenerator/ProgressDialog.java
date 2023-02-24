// ProgressDialog
// $Id: ProgressDialog.java,v 1.1 2003/12/09 10:21:45 oconnor_m Exp $
//
// Copyright (C) 2002-2003 Axel Wernicke <axel.wernicke@gmx.de>
//
// This program is free software; you can redistribute it and/or modify
// it under the terms of the GNU General Public License as published by
// the Free Software Foundation; either version 2 of the License, or
// (at your option) any later version.
//
// This program is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
// GNU General Public License for more details.
//
// You should have received a copy of the GNU General Public License
// along with this program; if not, write to the Free Software
// Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA

package com.finalist.jaggenerator;


/**
 * ProgressDialog provides a progress bar within a dialog window and a cancel button to abort the action.
 *
 * @author  axel wernicke
 */
public class ProgressDialog extends javax.swing.JDialog {
   /** The myPod action worker thre3ad this dialog is attached to */
   private Thread worker = null;

   /**
    * Creates new form ProgressDialog.  The dialogue will not be visible untl {@link #startThread} is called.
    *
    * @param parent frame for the dialog
    * @param modal create dialog (a-) synchronously
    */
   public ProgressDialog(java.awt.Frame parent, boolean modal) {
      super(parent, modal);
   }

   /**
    * Starts up the thread that does the actual work being monitored.
    *
    * @param worker thread the dialog is attached to
    */
   public void startThread(final Thread worker) {
      this.worker = worker;
      initComponents();
      totalProgressBar.setIndeterminate(true);
      pack();
      setLocationRelativeTo(getParent());
      worker.start();
//      //create a waiter thread: waits until task is finished, then closes dialogue.
//      new Thread("ProgressDialog.startThread") {
//         public void run() {
//            try {
//               worker.join();
//            } catch (InterruptedException e) {
//               //no action
//            }
//            setVisible(false);
//            dispose();
//            JagGenerator.logToConsole("2222222222222222222222222");
//
//         }
//      }.start();

      setVisible(true);
      JagGenerator.logToConsole("33333333333333333333333");


   }

   /** This method is called from within the constructor to
    * initialize the form.
    * WARNING: Do NOT modify this code. The content of this method is
    * always regenerated by the Form Editor.
    */
   private void initComponents()//GEN-BEGIN:initComponents
   {
      java.awt.GridBagConstraints gridBagConstraints;

      statusLabel = new javax.swing.JLabel();
      statusContentLabel = new javax.swing.JLabel();
      totalProgressBar = new javax.swing.JProgressBar();
      clipLabel = new javax.swing.JLabel();
      clipContentLabel = new javax.swing.JLabel();
      canelButton = new javax.swing.JButton();

      getContentPane().setLayout(new java.awt.GridBagLayout());

      setTitle("progress...");
      setName("progressDialog");
      setResizable(false);
      addWindowListener(new java.awt.event.WindowAdapter() {
         public void windowClosing(java.awt.event.WindowEvent evt) {
            closeDialog(evt);
         }
      });

      statusLabel.setForeground(new java.awt.Color(102, 102, 153));
      statusLabel.setText("status:");
      gridBagConstraints = new java.awt.GridBagConstraints();
      gridBagConstraints.gridx = 0;
      gridBagConstraints.gridy = 0;
      gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
      gridBagConstraints.insets = new java.awt.Insets(12, 12, 0, 0);
      getContentPane().add(statusLabel, gridBagConstraints);

      statusContentLabel.setFont(new java.awt.Font("Dialog", 1, 10));
      statusContentLabel.setMaximumSize(new java.awt.Dimension(250, 14));
      statusContentLabel.setMinimumSize(new java.awt.Dimension(200, 14));
      statusContentLabel.setPreferredSize(new java.awt.Dimension(200, 14));
      gridBagConstraints = new java.awt.GridBagConstraints();
      gridBagConstraints.gridx = 1;
      gridBagConstraints.gridy = 0;
      gridBagConstraints.gridwidth = 2;
      gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
      gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
      gridBagConstraints.weightx = 0.1;
      gridBagConstraints.insets = new java.awt.Insets(12, 11, 0, 12);
      getContentPane().add(statusContentLabel, gridBagConstraints);

      gridBagConstraints = new java.awt.GridBagConstraints();
      gridBagConstraints.gridx = 0;
      gridBagConstraints.gridy = 1;
      gridBagConstraints.gridwidth = 3;
      gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
      gridBagConstraints.insets = new java.awt.Insets(11, 17, 0, 17);
      getContentPane().add(totalProgressBar, gridBagConstraints);

      clipLabel.setForeground(new java.awt.Color(102, 102, 153));
      clipLabel.setText("clip:");
      gridBagConstraints = new java.awt.GridBagConstraints();
      gridBagConstraints.gridx = 0;
      gridBagConstraints.gridy = 2;
      gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
      gridBagConstraints.insets = new java.awt.Insets(11, 12, 0, 0);
      getContentPane().add(clipLabel, gridBagConstraints);

      clipContentLabel.setFont(new java.awt.Font("Dialog", 1, 10));
      clipContentLabel.setMaximumSize(new java.awt.Dimension(250, 14));
      clipContentLabel.setMinimumSize(new java.awt.Dimension(250, 14));
      clipContentLabel.setPreferredSize(new java.awt.Dimension(250, 14));
      gridBagConstraints = new java.awt.GridBagConstraints();
      gridBagConstraints.gridx = 1;
      gridBagConstraints.gridy = 2;
      gridBagConstraints.gridwidth = 2;
      gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
      gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
      gridBagConstraints.insets = new java.awt.Insets(11, 11, 0, 12);
      getContentPane().add(clipContentLabel, gridBagConstraints);

      canelButton.setText("cancel");
      canelButton.addActionListener(new java.awt.event.ActionListener() {
         public void actionPerformed(java.awt.event.ActionEvent evt) {
            canelButtonActionPerformed(evt);
         }
      });

      gridBagConstraints = new java.awt.GridBagConstraints();
      gridBagConstraints.gridx = 0;
      gridBagConstraints.gridy = 6;
      gridBagConstraints.gridwidth = 3;
      gridBagConstraints.insets = new java.awt.Insets(17, 12, 12, 12);
      getContentPane().add(canelButton, gridBagConstraints);

      pack();
   }//GEN-END:initComponents

   /** Cancels the action and closes the dialog
    * @param evt that triggered the action
    */
   private void canelButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_canelButtonActionPerformed
   {//GEN-HEADEREND:event_canelButtonActionPerformed
      if (worker != null) {
         worker.interrupt();
      } else {
         setVisible(false);
         dispose();
      }

   }//GEN-LAST:event_canelButtonActionPerformed

   /** Cancels the action and closes the dialog
    * @param evt that triggered the action
    */
   private void closeDialog(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_closeDialog
      if (worker != null) {
         worker.interrupt();
      } else {
         setVisible(false);
         dispose();
      }

   }//GEN-LAST:event_closeDialog

   /** Sets the status text
    * @param text to set
    */
   public void setStatusText(String text) {
      if (statusContentLabel != null) {
         this.statusContentLabel.setText(text);
      }
   }

   /** Sets the clip text
    * @param text to set
    */
   public void setClipText(String text) {
      this.clipContentLabel.setText(text);
   }

   /** Sets the minimum value of the progress bar
    * @param value to set
    */
   public void setProgressMin(int value) {
      this.totalProgressBar.setMinimum(value);
   }

   /** Sets the progress max value
    * @param value to set
    */
   public void setProgressMax(int value) {
      this.totalProgressBar.setMaximum(value);
   }

   /** Sets the progress bounds
    * @param min value to set
    * @param max value to set
    */
   public void setProgressBounds(int min, int max) {
      this.totalProgressBar.setMinimum(min);
      this.totalProgressBar.setMaximum(max);
   }


   /** Sets the value of the progress bar
    * @param value to set
    */
   public void setProgressValue(int value) {
      this.totalProgressBar.setValue(value);
   }


   /** Gets the value of the progress bar
    * @return progress value
    */
   public int getProgressValue() {
      return this.totalProgressBar.getValue();
   }


   /** Sets the progress bars indeterminate status.
    * @param value to set
    */
   public void setProgressIndeterminate(boolean value) {
      this.totalProgressBar.setIndeterminate(value);
   }


   /** Interrupts the worker thread (action) that dialog is attached to */
   public void interrupt() {
      this.worker.interrupt();
   }

   // Variables declaration - do not modify//GEN-BEGIN:variables
   private javax.swing.JButton canelButton;
   private javax.swing.JLabel clipContentLabel;
   private javax.swing.JLabel clipLabel;
   private javax.swing.JLabel statusContentLabel;
   private javax.swing.JLabel statusLabel;
   private javax.swing.JProgressBar totalProgressBar;
   // End of variables declaration//GEN-END:variables

}

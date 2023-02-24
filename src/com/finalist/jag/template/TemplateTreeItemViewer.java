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

package com.finalist.jag.template;


import com.finalist.jag.util.*;

import java.io.*;

import java.util.*;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.tree.*;


/**
 * Class TemplateTreeItemViewer
 *
 *
 * @author Wendel D. de Witte
 * @version %I%, %G%
 */
public class TemplateTreeItemViewer {

   /**
    * Method show
    *
    *
    * @param dataObj
    *
    */
   public static void show(TemplateTreeItem dataObj) {
      show(dataObj, "TemplateTreeItemViewer");
   }


   /**
    * Method show
    *
    *
    * @param dataObj
    * @param title
    *
    */
   public static void show(TemplateTreeItem dataObj, String title) {

      javax.swing.JFrame frame = new javax.swing.JFrame();

      frame.getContentPane().add(createSwingTree(dataObj),
         java.awt.BorderLayout.CENTER);
      frame.setSize(400, 400);
      frame.setTitle(title);
      frame.setVisible(true);
   }


   /**
    * Method createSwingTree
    *
    *
    * @param dataObj
    *
    * @return
    *
    */
   private static JScrollPane createSwingTree(TemplateTreeItem dataObj) {

      DefaultMutableTreeNode root = new DefaultMutableTreeNode("Root");

      createSwingTree(root, dataObj);

      JTree tree = new JTree(root) {

         public java.awt.Insets getInsets() {
            return new java.awt.Insets(5, 5, 5, 5);
         }
      };

      return new JScrollPane(tree);
   }


   /**
    * Method createSwingTree
    *
    *
    * @param parent
    * @param dataObj
    *
    */
   private static void createSwingTree(DefaultMutableTreeNode parent,
      TemplateTreeItem dataObj) {

      TemplateTreeItem childItem =
         (TemplateTreeItem) dataObj.getFirstChild();

      while (childItem != null) {
         String sText = (childItem.getTag() == null)
            ? "[TextBlock] "
            : "[TextBlock][TAG] ";

         sText += childItem.getTextBlock().getText();

         DefaultMutableTreeNode child = new DefaultMutableTreeNode(sText);

         parent.add(child);
         createSwingTree(child, childItem);

         childItem = (TemplateTreeItem) childItem.getNextSibling();
      }
   }
}

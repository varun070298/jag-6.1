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

package com.finalist.jag.skelet;


import java.io.*;

import java.util.*;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.tree.*;


/**
 * Class JagSkeletViewer
 *
 *
 * @author Wendel D. de Witte
 * @version %I%, %G%
 */
public class JagSkeletViewer {

   /**
    * Method show
    *
    *
    * @param dataObj
    *
    */
   public static void show(ModuleData dataObj) {

      javax.swing.JFrame frame = new javax.swing.JFrame();

      frame.getContentPane().add(createSwingTree(dataObj),
         java.awt.BorderLayout.CENTER);
      frame.setSize(400, 400);
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
   private static JScrollPane createSwingTree(ModuleData dataObj) {

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
      ModuleData dataObj) {

      if (dataObj instanceof SkeletModule) {
         String label =
            ((SkeletModule) dataObj).getRefname();
         DefaultMutableTreeNode child =
            new DefaultMutableTreeNode("ref-name > " + label);

         parent.add(child);
      }

      if (dataObj.isValueCollection()) {
         Collection col = (Collection) dataObj.getValue();
         Iterator iterator = col.iterator();

         while (iterator.hasNext()) {
            ModuleData dataChild =
               (ModuleData) iterator.next();
            DefaultMutableTreeNode child =
               new DefaultMutableTreeNode(dataChild.getName());

            parent.add(child);
            createSwingTree(child, dataChild);
         }
      }
      else if (dataObj.isValueString()) {
         String name = dataObj.getName();
         String value = (String) dataObj.getValue();
         DefaultMutableTreeNode child = new DefaultMutableTreeNode(name
            + " > " + value);

         parent.add(child);
      }
   }
}

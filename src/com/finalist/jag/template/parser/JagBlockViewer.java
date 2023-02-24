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

package com.finalist.jag.template.parser;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.tree.*;
import javax.accessibility.*;
import java.awt.*;
import java.awt.event.*;
import java.beans.*;
import java.util.*;
import java.io.*;
import java.applet.*;
import java.net.*;

/**
 * Class JagBlockViewer
 *
 * Test class for viewing the JagBlockTree structure in a Swing component.
 *
 * @author Wendel D. de Witte
 * @version %I%, %G%
 */
public class JagBlockViewer extends javax.swing.JFrame {
   /** Field rootBlock           */
   JagBlock rootBlock = null;

   /** Field buffer           */
   StringBuffer buffer;


   /**
    * Constructor JagBlockTree
    *
    *
    * @param rootBlock
    *
    */
   public JagBlockViewer(JagBlock rootBlock) {
      buffer = new StringBuffer();
      this.rootBlock = rootBlock;

      getContentPane().add(createTree(), BorderLayout.CENTER);
      setSize(400, 400);
      setVisible(true);
   }


   /**
    * Method getTextString
    *
    *
    * @return
    *
    */
   public final String getTextString() {
      return buffer.toString();
   }


   /**
    * Method createTree
    *
    *
    * @return
    *
    */
   public JScrollPane createTree() {
      DefaultMutableTreeNode root = new DefaultMutableTreeNode("Root");
      createTree(root, rootBlock);

      JTree tree = new JTree(root) {
         public Insets getInsets() {
            return new Insets(5, 5, 5, 5);
         }
      };

      return new JScrollPane(tree);
   }


   /**
    * Method createTree
    *
    *
    * @param parent
    * @param block
    *
    */
   protected void createTree(DefaultMutableTreeNode parent, JagBlock block) {
      JagBlock blockChild = block.getFirstChild();

      while (blockChild != null) {
         DefaultMutableTreeNode child =
            new DefaultMutableTreeNode(blockChild.getText());

         if (blockChild.getType() == JagParserConstants.TAGSTART) {
            buffer.append("TAG");
         }

         if (blockChild.getType() == JagParserConstants.TEXT) {
            buffer.append(blockChild.getText());
         }

         createTree(child, blockChild);
         parent.add(child);

         blockChild = blockChild.getNextSibling();
      }
   }
}
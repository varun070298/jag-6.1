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

/*
 * MyTreeModelListener.java
 *
 * Created on 16 september 2002, 22:03
 */

package com.finalist.jaggenerator;

import com.finalist.jaggenerator.modules.*;

import javax.swing.event.*;
import javax.swing.tree.*;

/**
 *
 * @author  hillie
 */
public class JagTreeModelListener implements TreeModelListener {

   /** Creates a new instance of MyTreeModelListener */
   public JagTreeModelListener() {
   }


   /** <p>Invoked after a node (or a set of siblings) has changed in some
    * way. The node(s) have not changed locations in the tree or
    * altered their children arrays, but other attributes have
    * changed and may affect presentation. Example: the name of a
    * file has changed, but it is in the same location in the file
    * system.</p>
    * <p>To indicate the root has changed, childIndices and children
    * will be null. </p>
    *
    * <p>Use <code>e.getPath()</code>
    * to get the parent of the changed node(s).
    * <code>e.getChildIndices()</code>
    * returns the index(es) of the changed node(s).</p>
    *
    */
   public void treeNodesChanged(TreeModelEvent e) {
      DefaultMutableTreeNode node = (DefaultMutableTreeNode) (e.getTreePath().getLastPathComponent());
      /*
       * If the event lists children, then the changed
       * node is the child of the node we've already
       * gotten.  Otherwise, the changed node and the
       * specified node are the same.
       */
      try {
         int index = e.getChildIndices()[0];
         node = (DefaultMutableTreeNode) (node.getChildAt(index));
      }
      catch (NullPointerException exc) {
      }
   }


   /** <p>Invoked after nodes have been inserted into the tree.</p>
    *
    * <p>Use <code>e.getPath()</code>
    * to get the parent of the new node(s).
    * <code>e.getChildIndices()</code>
    * returns the index(es) of the new node(s)
    * in ascending order.</p>
    *
    */
   public void treeNodesInserted(TreeModelEvent e) {
   }


   /** <p>Invoked after nodes have been removed from the tree.  Note that
    * if a subtree is removed from the tree, this method may only be
    * invoked once for the root of the removed subtree, not once for
    * each individual set of siblings removed.</p>
    *
    * <p>Use <code>e.getPath()</code>
    * to get the former parent of the deleted node(s).
    * <code>e.getChildIndices()</code>
    * returns, in ascending order, the index(es)
    * the node(s) had before being deleted.</p>
    *
    */
   public void treeNodesRemoved(TreeModelEvent e) {
   }


   /** <p>Invoked after the tree has drastically changed structure from a
    * given node down.  If the path returned by e.getPath() is of length
    * one and the first element does not identify the current root node
    * the first element should become the new root of the tree.<p>
    *
    * <p>Use <code>e.getPath()</code>
    * to get the path to the node.
    * <code>e.getChildIndices()</code>
    * returns null.</p>
    *
    */
   public void treeStructureChanged(TreeModelEvent e) {
   }

}

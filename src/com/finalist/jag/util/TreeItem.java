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

package com.finalist.jag.util;


/**
 * Class TreeItem
 *
 *
 * @author Wendel D. de Witte
 * @version %I%, %G%
 *
 *
 *
 */
public class TreeItem {

   /** Field down */
   private TreeItem up = null;

   /** Field down */
   private TreeItem down = null;

   /** Field right */
   private TreeItem right = null;

   /** Field right */
   private TreeItem left = null;

   /** Field dataField           */
   private Object dataField = null;


   /**
    * Add a node to the end of the child list for this node
    *
    * @param node
    */
   public void addChild(TreeItem node) {

      if (node == null) {
         return;
      }

      TreeItem t = this.down;

      if (t != null) {
         // only the most left must point to the parent
         while (t.right != null) t = t.right;
         t.right = (TreeItem) node;
         node.left = t;
         node.up = null;
      }
      else {
         // don't reset the right pointer;
         this.down = (TreeItem) node;
         node.left = null;
         node.up = this;
      }
   }


   /**
    *  Get the first child of this node; null if not children
    *
    *  @return
    */
   public TreeItem getFirstChild() {
      return down;
   }


   /**
    * Get the next sibling in line after this one
    *
    * @return
    */
   public TreeItem getNextSibling() {
      return right;
   }


   /**
    * Get the next sibling in line after this one
    *
    * @return
    */
   public TreeItem getPrevSibling() {
      return left;
   }


   public TreeItem getLastChild() {
      TreeItem childItem = getFirstChild();
      while (childItem != null && childItem.getNextSibling() != null)
         childItem = childItem.getNextSibling();
      return childItem;
   }


   public TreeItem getParent() {
      TreeItem tmp = this;
      while (tmp.getPrevSibling() != null)
         tmp = tmp.getPrevSibling();
      return tmp.up;
   }


   public TreeItem getRoot() {
      TreeItem tmp = this;
      while (tmp.getParent() != null)
         tmp = tmp.getParent();
      return tmp;
   }


   /** Remove all children */
   public TreeItem disconnectChildren() {
      TreeItem treeitem = down;
      if (down != null) {
         treeitem.up = null;
         down = null;
      }
      return treeitem;
   }


   public TreeItem disconnectLastChild() {
      TreeItem child = this.getFirstChild();
      while (child != null) {
         if (child.getNextSibling() == null) {
            if (child.left == null)
               return disconnectChildren();

            return child.left.disconnectSiblings();
         }
         child = child.getNextSibling();
      }
      return null;
   }


   /** Remove all children */
   public TreeItem disconnectSiblings() {
      TreeItem treeitem = right;
      if (right != null) {
         right.left = null;
         right = null;
      }
      return treeitem;
   }


   /**
    * Method setFirstChild
    *
    *
    * @param c
    *
    */
   public TreeItem setFirstChild(TreeItem c) {
      TreeItem oldChild = disconnectChildren();
      down = (TreeItem) c;
      return oldChild;
   }


   /**
    * Method setNextSibling
    *
    * @param n
    */
   public TreeItem setNextSibling(TreeItem n) {
      TreeItem oldSibling = disconnectSiblings();
      right = n;
      n.left = this;
      return oldSibling;
   }


   /**
    * Method getDataItem
    *
    *
    * @return
    *
    */
   public Object getDataItem() {
      return dataField;
   }


   /**
    * Method setDataItem
    *
    *
    * @param dataField
    *
    */
   public void setDataItem(Object dataField) {
      this.dataField = dataField;
   }


   public String toString() {
      String buffer = new String();
      buffer += "up : ";
      buffer += (up != null) ? "1" : "null";
      buffer += " down : ";
      buffer += (down != null) ? "1" : "null";
      buffer += " right : ";
      buffer += (right != null) ? "1" : "null";
      buffer += " left : ";
      buffer += (left != null) ? "1" : "null";
      return buffer;
   }
}

;
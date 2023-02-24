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


import java.util.*;

import com.finalist.jag.util.*;


/**
 * Class TemplateStructure
 *
 *
 * @author Wendel D. de Witte
 * @version %I%, %G%
 */
public class TemplateStructure implements Cloneable {

   /** Field rootNode           */
   private TemplateTreeItem rootNode = null;


   /**
    * Constructor TemplateStructure
    *
    *
    */
   public TemplateStructure() {
   }


   /**
    * Constructor TemplateStructure
    *
    *
    * @param n
    *
    */
   public TemplateStructure(TemplateStructure n) {
      rootNode = new TemplateTreeItem(n.getRoot());
   }


   /**
    * Method setRoot
    *
    *
    * @param rootNode
    *
    */
   public void setRoot(TemplateTreeItem rootNode) {
      this.rootNode = rootNode;
   }


   /**
    * Method getRoot
    *
    *
    * @return
    *
    */
   public final TemplateTreeItem getRoot() {

      if (rootNode == null) {
         rootNode = new TemplateTreeItem();
      }

      return rootNode;
   }


   /**
    * Method getTextBlockList
    *
    *
    * @return
    *
    */
   public final TemplateTextBlockList getTextBlockList() {

      TemplateTextBlockList list = new TemplateTextBlockList();

      getTextBlockList(list, getRoot());

      return list;
   }


   /**
    * Method cut
    *
    * Requirement : both nodes must be in the same branche.
    *
    * @param first
    * @param last
    *
    * @return
    *
    */
   public TemplateStructure cut(TemplateTreeItem first,
      TemplateTreeItem last) {

      TemplateStructure returnValue = new TemplateStructure();
      TemplateTreeItem tmp = first;

      if (((first == null) || (last == null))
         || (first.getParent() != last.getParent())) {
         return returnValue;
      }

      // Disconnect first and last from the hive in order to
      // create their own collective.
      if (first.getPrevSibling() != null) {
         TreeItem prevItem = first.getPrevSibling();

         prevItem.setNextSibling(last.getNextSibling());
      }
      else if (first.getParent() != null) {
         TreeItem parentItem = first.getParent();

         parentItem.setFirstChild(last.getNextSibling());
      }

      last.disconnectSiblings();

      // return the new hive
      returnValue.getRoot().setFirstChild(first);

      return returnValue;
   }


   /**
    * Method getTextBlockList
    *
    *
    * @param list
    * @param item
    *
    */
   private void getTextBlockList(TemplateTextBlockList list,
      TemplateTreeItem item) {

      if (item.getTextBlock() != null) {
         list.add(item.getTextBlock());
      }

      TreeItem childItem = item.getFirstChild();

      while (childItem != null) {
         getTextBlockList(list, (TemplateTreeItem) childItem);

         childItem = childItem.getNextSibling();
      }
   }


   /**
    * Method getTemplateTreeItem
    *
    *
    * @param textBlock
    *
    * @return
    *
    */
   public TemplateTreeItem getTemplateTreeItem(TemplateTextBlock textBlock) {

      Iterator iterator = getCollection().iterator();

      while (iterator.hasNext()) {
         TemplateTreeItem item = (TemplateTreeItem) iterator.next();

         if (item.getTextBlock() == textBlock) {
            return item;
         }
      }

      return null;
   }


   /**
    * Method getCollection
    *
    *
    * @return
    *
    */
   public Collection getCollection() {

      LinkedList list = new LinkedList();

      getCollection(list, getRoot());

      return list;
   }


   /**
    * Method getCollection
    *
    *
    * @param list
    * @param item
    *
    */
   private void getCollection(LinkedList list, TemplateTreeItem item) {

      list.add(item);

      TreeItem childItem = item.getFirstChild();

      while (childItem != null) {
         getCollection(list, (TemplateTreeItem) childItem);

         childItem = childItem.getNextSibling();
      }
   }


   /**
    * Method clearBodyText
    *
    *
    *
    */
   public void clearBodyText() {
      clearBodyText(rootNode);
   }


   /**
    * Method clearBodyText
    *
    *
    *
    */
   private void clearBodyText(TemplateTreeItem parentItem) {

      if (parentItem == null) {
         return;
      }
      parentItem.getTextBlock().set();

      TemplateTreeItem templateItem =
         (TemplateTreeItem) parentItem.getFirstChild();

      while (templateItem != null) {
         clearBodyText(templateItem);
         templateItem = (TemplateTreeItem) templateItem.getNextSibling();
      }
   }
}

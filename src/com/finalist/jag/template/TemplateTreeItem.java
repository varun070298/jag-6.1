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


/**
 * Class TemplateTreeItem
 *
 *
 * @author Wendel D. de Witte
 * @version %I%, %G%
 */
public class TemplateTreeItem extends TreeItem {

   /** Field tag           */
   public TemplateTag tag = null;

   /** Field textBlock           */
   public TemplateTextBlock textBlock = null;


   /**
    * Constructor TemplateTreeItem
    *
    *
    */
   public TemplateTreeItem() {
      textBlock = new TemplateTextBlock("");
   }


   /**
    * Constructor TemplateTreeItem
    *
    *
    * @param root
    *
    */
   public TemplateTreeItem(TemplateTreeItem root) {

      // Copy the textBlocks
      textBlock = new TemplateTextBlock(root.textBlock);

      // Copy the Tag and set the textbuffers.
      if (root.tag != null) {
         tag = new TemplateTag(root.tag);

         tag.setTextBuffer(textBlock);
         tag.setClosingTextBuffer(null);
      }

      TreeItem childItem = root.getFirstChild();

      while (childItem != null) {
         addChild(new TemplateTreeItem((TemplateTreeItem) childItem));

         childItem = childItem.getNextSibling();
      }

      if ((tag != null) && (getLastChild() != null)) {
         TemplateTreeItem closingBlock =
            (TemplateTreeItem) getLastChild();
         TemplateTextBlock closingText = closingBlock.getTextBlock();

         tag.setClosingTextBuffer(closingText);
      }
   }


   /**
    * Method setTag
    *
    *
    * @param tag
    *
    */
   public void setTag(TemplateTag tag) {
      this.tag = tag;
   }


   /**
    * Method setTextBlock
    *
    *
    * @param textBlock
    *
    */
   public void setTextBlock(TemplateTextBlock textBlock) {
      this.textBlock = textBlock;
   }


   /**
    * Method getTag
    *
    *
    * @return
    *
    */
   public TemplateTag getTag() {
      return (this.tag);
   }


   /**
    * Method getTextBlock
    *
    *
    * @return
    *
    */
   public TemplateTextBlock getTextBlock() {
      return (this.textBlock);
   }
}

;
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


/**
 * Class TemplateTextBlockCollection
 *
 *
 * @author Wendel D. de Witte
 * @version %I%, %G%
 */
public class TemplateTextBlockList extends LinkedList {

   /**
    * Method setCollection
    *
    *
    *
    */
   public TemplateTextBlockList() {
   }


   /**
    * Method add
    *
    *
    * @param block
    *
    */
   public void add(TemplateTextBlock block) {
      this.addLast(block);
   }


   /**
    * Method add
    *
    *
    *
    * @param block1
    * @param block2
    *
    *
    * @return
    */
   public TemplateTextBlockList cut(TemplateTextBlock block1,
      TemplateTextBlock block2) {

      TemplateTextBlockList list = new TemplateTextBlockList();
      int nIndex1 = indexOf(block1);
      int nIndex2 = indexOf(block2);

      while ((nIndex1 != -1) && (nIndex1 <= nIndex2)) {
         list.add(list.get(nIndex1));
         remove(nIndex1++);
      }

      return list;
   }


   /**
    * Method getBefore
    *
    *
    * @param block
    *
    * @return
    *
    */
   public TemplateTextBlock getBefore(TemplateTextBlock block) {

      int nIndex = indexOf(block);

      if (nIndex == -1) {
         return null;
      }

      ListIterator iterator = listIterator(nIndex);

      return iterator.hasPrevious()
         ? (TemplateTextBlock) iterator.previous()
         : null;
   }


   /**
    * Method getAfter
    *
    *
    * @param block
    *
    * @return
    *
    */
   public TemplateTextBlock getAfter(TemplateTextBlock block) {

      int nIndex = indexOf(block);

      if (nIndex == -1) {
         return null;
      }

      ListIterator iterator = listIterator(nIndex);

      return iterator.hasNext()
         ? (TemplateTextBlock) iterator.next()
         : null;
   }


   /**
    * Method getStringBuffer
    *
    *
    * @return
    *
    */
   public StringBuffer getStringBuffer() {

      StringBuffer returnValue = new StringBuffer();
      ListIterator iterator = listIterator();

      while (iterator.hasNext()) {
         returnValue
            .append(((TemplateTextBlock) iterator.next()).getText());
      }

      return returnValue;
   }


   /**
    * Method toArray
    *
    *
    * @return
    *
    */
   public TemplateTextBlock[] toTextBlockArray() {
      return (TemplateTextBlock[]) super
         .toArray(new TemplateTextBlock[size()]);
   }
}
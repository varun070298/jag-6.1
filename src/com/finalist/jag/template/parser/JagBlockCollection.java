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


/**
 * Class JagBlockCollection
 *
 *
 * @author Wendel D. de Witte
 * @version %I%, %G%
 */
public class JagBlockCollection extends JagBlockImpl {

   /**
    * Method toString
    *
    *
    * @return
    *
    */
   public String toString() {
      return getTextBuffer(getFirstChild());
   }


   /**
    * Method getTextBuffer
    *
    *
    * @param childBlock
    *
    * @return
    *
    */
   private String getTextBuffer(JagBlock childBlock) {

      if (childBlock == null) {
         return "";
      }

      StringBuffer buffer = new StringBuffer();

      while (childBlock != null) {
         buffer.append(childBlock.getText());
         buffer.append(getTextBuffer(childBlock.getFirstChild()));

         childBlock = childBlock.getNextSibling();
      }

      return buffer.toString();
   }
}

;
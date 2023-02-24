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

/**
 * Class TemplateTextBlock
 *
 *
 * @author Wendel D. de Witte
 * @version %I%, %G%
 */
public class TemplateTextBlock {

   /** Field buffer           */
   private StringBuffer buffer;

   /** Field filePath           */
   private String filePath;


   /**
    * Constructor TemplateTextBlock
    *
    *
    * @param s
    *
    */
   public TemplateTextBlock(String s) {
      set(s);
   }

   /**
    * Constructor TemplateTextBlock
    *
    *
    * @param c
    *
    */
   public TemplateTextBlock(TemplateTextBlock c) {

      if ((c != null) && (c.buffer != null)) {
         this.buffer = new StringBuffer(new String(c.buffer));
      }
   }

   /**
    * Method set
    *
    *
    */
   public void set() {
      buffer = new StringBuffer();
   }

   /**
    * Method set
    *
    *
    * @param s
    *
    */
   public void set(String s) {
      buffer = new StringBuffer(s);
   }

   /**
    * Method append
    *
    *
    * @param s
    *
    */
   public void append(String s) {
      buffer.append(s);
   }

   /**
    * Method append
    *
    *
    * @param s
    *
    */
   public void append(StringBuffer s) {
      buffer.append(s.toString());
   }

   /**
    * Method getText
    *
    *
    * @return
    *
    */
   public String getText() {
      return new String(buffer);
   }

   /**
    * Method toString
    *
    *
    * @return
    *
    */
   public String toString() {
      return buffer.toString();
   }

   /**
    * Method isEmpty
    *
    *
    * @return
    *
    */
   public boolean isEmpty() {
      return buffer.length() < 1;
   }

   /**
    * Method setFile
    *
    *
    * @param path
    *
    */
   public void setFile(String path) {
      this.filePath = path;
   }

   /**
    * Method getFile
    *
    *
    * @return
    *
    */
   public String getFile() {
      return filePath;
   }

   /**
    * Method newFile
    *
    *
    * @return
    *
    */
   public boolean newFile() {
      return (filePath != null) && (filePath.length() > 0);
   }
}
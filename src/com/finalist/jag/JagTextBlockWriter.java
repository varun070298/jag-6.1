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

package com.finalist.jag;


import com.finalist.jag.template.*;


/**
 * Class JagTextBlockWriter
 *
 *
 * @author Wendel D. de Witte
 * @version %I%, %G%
 */
public class JagTextBlockWriter extends JagWriter {

   /** Field buffer */
   private TemplateTextBlock buffer = null;


   /**
    * Constructor JagTextBlockWriter
    *
    *
    */
   public JagTextBlockWriter() {
      buffer = new TemplateTextBlock("");
   }


   /**
    * Constructor JagTextBlockWriter
    *
    *
    * @param buffer
    *
    */
   public JagTextBlockWriter(TemplateTextBlock buffer) {
      this.buffer = buffer;
   }


   /**
    * Clear the contents of the buffer.
    */
   public void clear() {
      buffer.set();
   }


   /**
    * Write a line separator.
    */
   public void newLine() {
      buffer.append(java.io.File.separator);
   }


   /**
    * Print a boolean value.
    *
    * @param b
    */
   public void print(boolean b) {
      buffer.append(new StringBuffer().append(b));
   }


   /**
    * Print a character.
    *
    * @param c
    */
   public void print(char c) {
      buffer.append(new StringBuffer().append(c));
   }


   /**
    * Print an array of characters.
    *
    * @param s
    */
   public void print(char[] s) {
      buffer.append(new StringBuffer().append(s));
   }


   /**
    * Print a double-precision floating-point number.
    *
    * @param d
    */
   public void print(double d) {
      buffer.append(new StringBuffer().append(d));
   }


   /**
    * Print a floating-point number.
    *
    * @param f
    */
   public void print(float f) {
      buffer.append(new StringBuffer().append(f));
   }


   /**
    * Print an integer.
    *
    * @param i
    */
   public void print(int i) {
      buffer.append(new StringBuffer().append(i));
   }


   /**
    * Print a long integer.
    *
    * @param l
    */
   public void print(long l) {
      buffer.append(new StringBuffer().append(l));
   }


   /**
    * Print an object.
    *
    * @param obj
    */
   public void print(Object obj) {
      buffer.append(new StringBuffer().append(obj));
   }


   /**
    * Print a string.
    *
    * @param s
    */
   public void print(String s) {
      buffer.append(s);
   }


   /**
    * Terminate the current line by writing the line separator string.
    */
   public void println() {
      newLine();
   }


   /**
    * Print a boolean value and then terminate the line.
    *
    * @param x
    */
   public void println(boolean x) {
      print(x);
      newLine();
   }


   /**
    * Print a character and then terminate the line.
    *
    * @param x
    */
   public void println(char x) {
      print(x);
      newLine();
   }


   /**
    * Print an array of characters and then terminate the line.
    *
    * @param x
    */
   public void println(char[] x) {
      print(x);
      newLine();
   }


   /**
    * Print a double-precision floating-point number and then terminate the line.
    *
    * @param x
    */
   public void println(double x) {
      print(x);
      newLine();
   }


   /**
    * Print a floating-point number and then terminate the line.
    *
    * @param x
    */
   public void println(float x) {
      print(x);
      newLine();
   }


   /**
    * Print an integer and then terminate the line.
    *
    * @param x
    */
   public void println(int x) {
      print(x);
      newLine();
   }


   /**
    * Print a long integer and then terminate the line.
    *
    * @param x
    */
   public void println(long x) {
      print(x);
      newLine();
   }


   /**
    * Print an Object and then terminate the line.
    *
    * @param x
    */
   public void println(Object x) {
      print(x);
      newLine();
   }


   /**
    * Print a String and then terminate the line.
    *
    * @param x
    */
   public void println(String x) {
      print(x);
      newLine();
   }


   /**
    * Method createNewFile
    *
    *
    * @param path
    *
    */
   public void createNewFile(java.lang.String path) {
      buffer.setFile(path);
   }


   /**
    * Method createNewFile
    *
    *
    * @param path
    *
    */
   public void createNewFile(java.lang.StringBuffer path) {
      createNewFile(new String(path));
   }
}

;
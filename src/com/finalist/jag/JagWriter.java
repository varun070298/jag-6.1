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


/* Generated by Together */

/**
 * Class JagWriter
 *
 *
 * @author Wendel D. de Witte
 * @version %I%, %G%
 */
public abstract class JagWriter {

   /**
    * Constructor JagWriter
    *
    *
    */
   public JagWriter() {
   }


   /**
    * Clear the contents of the buffer.
    */
   public abstract void clear();


   /**
    * Write a line separator.
    */
   public abstract void newLine();


   /**
    * Print a boolean value.
    *
    * @param b
    */
   public abstract void print(boolean b);


   /**
    * Print a character.
    *
    * @param c
    */
   public abstract void print(char c);


   /**
    * Print an array of characters.
    *
    * @param s
    */
   public abstract void print(char[] s);


   /**
    * Print a double-precision floating-point number.
    *
    * @param d
    */
   public abstract void print(double d);


   /**
    * Print a floating-point number.
    *
    * @param f
    */
   public abstract void print(float f);


   /**
    * Print an integer.
    *
    * @param i
    */
   public abstract void print(int i);


   /**
    * Print a long integer.
    *
    * @param l
    */
   public abstract void print(long l);


   /**
    * Print an object.
    *
    * @param obj
    */
   public abstract void print(java.lang.Object obj);


   /**
    * Print a string.
    *
    * @param s
    */
   public abstract void print(java.lang.String s);


   /**
    * Terminate the current line by writing the line separator string.
    */
   public abstract void println();


   /**
    * Print a boolean value and then terminate the line.
    *
    * @param x
    */
   public abstract void println(boolean x);


   /**
    * Print a character and then terminate the line.
    *
    * @param x
    */
   public abstract void println(char x);


   /**
    * Print an array of characters and then terminate the line.
    *
    * @param x
    */
   public abstract void println(char[] x);


   /**
    * Print a double-precision floating-point number and then terminate the line.
    *
    * @param x
    */
   public abstract void println(double x);


   /**
    * Print a floating-point number and then terminate the line.
    *
    * @param x
    */
   public abstract void println(float x);


   /**
    * Print an integer and then terminate the line.
    *
    * @param x
    */
   public abstract void println(int x);


   /**
    * Print a long integer and then terminate the line.
    *
    * @param x
    */
   public abstract void println(long x);


   /**
    * Print an Object and then terminate the line.
    *
    * @param x
    */
   public abstract void println(java.lang.Object x);


   /**
    * Print a String and then terminate the line.
    *
    * @param x
    */
   public abstract void println(java.lang.String x);


   /**
    * Method createNewFile
    *
    *
    * @param path
    *
    */
   public abstract void createNewFile(java.lang.String path);


   /**
    * Method createNewFile
    *
    *
    * @param path
    *
    */
   public abstract void createNewFile(java.lang.StringBuffer path);
}

;
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
 * Class CharQueue
 *
 *
 * @author
 * @version %I%, %G%
 */
public class CharQueue {

   /** Field buffer           */
   protected char[] buffer;

   // buffer.length-1 for quick modulous

   /** Field sizeLessOne           */
   protected int sizeLessOne;

   // physical index of front token

   /** Field offset           */
   protected int offset;

   // number of characters in the queue

   /** Field nbrEntries           */
   protected int nbrEntries;


   /**
    * Constructor CharQueue
    *
    *
    * @param minSize
    *
    */
   public CharQueue(int minSize) {
      // Find first power of 2 >= to requested size
      int size;
      for (size = 2; size < minSize; size *= 2) ;
      init(size);
   }


   /**
    * Add token to end of the queue
    * @param tok The token to add
    */
   public final void append(char tok) {
      if (nbrEntries == buffer.length) {
         expand();
      }
      buffer[(offset + nbrEntries) & sizeLessOne] = tok;
      nbrEntries++;
   }


   /**
    * Fetch a token from the queue by index
    * @param idx The index of the token to fetch, where zero is the token at the front of the queue
    *
    * @return
    */
   public final char elementAt(int idx) {
      return buffer[(offset + idx) & sizeLessOne];
   }


   /** Expand the token buffer by doubling its capacity */
   private final void expand() {
      char[] newBuffer = new char[buffer.length * 2];

      // Copy the contents to the new buffer
      // Note that this will store the first logical item in the
      // first physical array element.
      for (int i = 0; i < buffer.length; i++) {
         newBuffer[i] = elementAt(i);
      }

      // Re-initialize with new contents, keep old nbrEntries
      buffer = newBuffer;
      sizeLessOne = buffer.length - 1;
      offset = 0;
   }


   /**
    * Initialize the queue.
    * @param size The initial size of the queue
    */
   private final void init(int size) {
      buffer = new char[size];
      sizeLessOne = size - 1;
      offset = 0;
      nbrEntries = 0;
   }


   /** Remove char from front of queue */
   public final void removeFirst() {
      offset = (offset + 1) & sizeLessOne;
      nbrEntries--;
   }
}
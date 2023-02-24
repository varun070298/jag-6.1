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
 * Class InputBuffer
 *
 *
 * @author
 * @version %I%, %G%
 */
public abstract class InputBuffer {

   // Number of active markers

   /** Field nMarkers           */
   protected int nMarkers = 0;

   // Additional offset used when markers are active

   /** Field markerOffset           */
   protected int markerOffset = 0;

   // Number of calls to consume() since last LA()

   /** Field numToConsume           */
   protected int numToConsume = 0;

   // Circular queue

   /** Field queue           */
   protected CharQueue queue;


   /** Create an input buffer */
   public InputBuffer() {
      queue = new CharQueue(1);
   }


   /**
    * This method updates the state of the input buffer so that
    *  the text matched since the most recent mark() is no longer
    *  held by the buffer.  So, you either do a mark/rewind for
    *  failed predicate or mark/commit to keep on parsing without
    *  rewinding the input.
    */
   public void commit() {
      nMarkers = (nMarkers > 0) ? nMarkers - 1 : 0;
   }


   /** Mark another character for deferred consumption */
   public void consume() {
      numToConsume++;
   }


   /**
    * Ensure that the input buffer is sufficiently full
    *
    * @param amount
    *
    * @throws CharStreamException
    */
   public abstract void fill(int amount) throws CharStreamException;


   /**
    * Method getLAChars
    *
    *
    * @return
    *
    */
   public String getLAChars() {
      StringBuffer la = new StringBuffer();
      for (int i = markerOffset; i < queue.nbrEntries; i++) {
         la.append(queue.elementAt(i));
      }

      return la.toString();
   }


   /**
    * Method getCharsFromMark
    *
    *
    * @param mark
    *
    * @return
    *
    */
   public String getCharsFromMark(int mark) {
      StringBuffer la = new StringBuffer();
      int i = mark;

      mark();
      for (; i < markerOffset; i++) {
         la.append(queue.elementAt(i));
      }
      commit();

      return la.toString();
   }


   /**
    * Method LAChars
    *
    *
    * @param n
    *
    * @return
    *
    * @throws CharStreamException
    *
    */
   public String LAChars(int n) throws CharStreamException {
      StringBuffer la = new StringBuffer();

      for (int i = 0; i < n; i++) {
         la.append(LA(i + 1));
      }

      return la.toString();
   }


   /**
    * Method getMarkedChars
    *
    *
    * @return
    *
    */
   public String getMarkedChars() {
      StringBuffer marked = new StringBuffer();

      for (int i = 0; i < markerOffset; i++) {
         marked.append(queue.elementAt(i));
      }

      return marked.toString();
   }


   /**
    * Method isMarked
    *
    *
    * @return
    *
    */
   public boolean isMarked() {
      return (nMarkers != 0);
   }


   /**
    * Get a lookahead character
    *
    * @param i
    *
    * @return
    *
    * @throws CharStreamException
    */
   public char LA(int i) throws CharStreamException {
      fill(i);
      return queue.elementAt(markerOffset + i - 1);
   }


   /**
    * Return an integer marker that can be used to rewind the buffer to
    * its current state.
    *
    * @return
    */
   public int mark() {
      syncConsume();
      nMarkers++;
      return markerOffset;
   }


   /**
    * Rewind the character buffer to a marker.
    * @param mark Marker returned previously from mark()
    */
   public void rewind(int mark) {
      syncConsume();
      markerOffset = mark;
      nMarkers--;
   }


   /** Sync up deferred consumption */
   protected void syncConsume() {
      while (numToConsume > 0) {
         if (nMarkers > 0) {
            // guess mode -- leave leading characters and bump offset.
            markerOffset++;
         }
         else {
            // normal mode -- remove first character
            queue.removeFirst();
         }
         numToConsume--;
      }
   }
}
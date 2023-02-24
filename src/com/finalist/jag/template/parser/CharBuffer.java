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

import java.io.*;


/**
 * Class CharBuffer
 *
 *
 * @author Wendel D. de Witte
 * @version %I%, %G%
 */
public class CharBuffer extends InputBuffer {

   // char source

   /** Field input           */
   private Reader input = null;

   /** Field inputString           */
   private String inputString = null;

   /** Field stringIndex           */
   private int stringIndex = 0;

   /** Field stringLength           */
   private int stringLength = 0;


   /**
    * Create a character buffer
    *
    * @param input
    */
   public CharBuffer(Reader input) {
      super();
      this.input = input;
   }


   /**
    * Create a character buffer
    *
    * @param input
    */
   public CharBuffer(String input) {
      super();
      this.inputString = input;
      this.stringLength = input.length();
   }


   /**
    * Ensure that the character buffer is sufficiently full
    *
    * @param amount
    *
    * @throws CharStreamException
    */
   public void fill(int amount) throws CharStreamException {
      try {
         syncConsume();
         // Fill the buffer sufficiently to hold needed characters
         while (queue.nbrEntries < amount + markerOffset) {
            queue.append(read());
         }
      }
      catch (IOException io) {
         throw new CharStreamIOException(io);
      }
   }


   /**
    * Method read
    *
    *
    * @return
    *
    * @throws IOException
    *
    */
   private char read() throws IOException {
      if (input != null) {
         return (char) input.read();
      }

      if ((inputString != null) && (stringIndex < stringLength)) {
         return (char) inputString.charAt(stringIndex++);
      }
      return (char) -1;
   }
}
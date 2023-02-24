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

package com.finalist.util;

import com.lowagie.text.html.HtmlEncoder;

/**
 * This class represents a line of text from a source file that conflicted during the diff process.
 *
 * @author Michael O'Connor - Finalist IT Group.
 */
public class DiffConflictLine {

   private String line;
   private int number;
   private boolean firstFile;

   /** A special case of DiffConflictLine, used to represent the last line in a file. */
   public static final DiffConflictLine EOF = new DiffConflictLine();


   /**
    * Constructs a DiffConflictLine.
    * @param firstFile <code>true</code> if this line comes from the 'first' file (a diff involves 2 files).
    * @param number the line number within the original file.
    * @param line the text of the line.
    */
   public DiffConflictLine(boolean firstFile, int number, String line) {
      this.number = number;
      this.firstFile = firstFile;
      this.line = line;
   }

   private DiffConflictLine() {
   }


   /**
    * Checks if the given line has the same text as this one (ignoring whitespace).
    * @param line2 the other line.
    * @return <code>true</code> if equal.
    */
   public boolean lineEquals(DiffConflictLine line2) {
      return line.trim().equals(line2.getLine().trim());
   }

   public String getLine() {
      return line;
   }

   public boolean isEof() {
      return (this == EOF);
   }

   public int getLineNumber() {
      return number;
   }

   public boolean isFirstFile() {
      return firstFile;
   }
   /**
    * By default this renders a HTML result.
    * @return
    */
   public String toString() {
      return "<font class='file" + (firstFile ? "1" : "2") + "-code'>" +
            (firstFile ? "&lt;" : "&gt;") + HtmlEncoder.encode(getLine()) + "</font><br>";
   }

   /**
    * Checks if a given conflict line precedes this one.
    *
    * @param next
    * @return
    */
   public boolean precedes(DiffConflictLine next) {
      return next != null &&
               (firstFile == next.firstFile) &&
               next.getLineNumber() == number + 1;
   }

}

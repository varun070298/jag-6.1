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

package com.finalist.jaggenerator;

import javax.swing.filechooser.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

/**
 * A FileFilter that accepts/rejects files depending on their extension (part of filename following the last 'dot').
 *
 * @author Michael O'Connor - Finalist IT Group
 */
public class ExtensionsFileFilter extends FileFilter {

   private static final char DOT = '.';
   private final ArrayList acceptFilter = new ArrayList();
   private String description;

   /**
    * Creates a filter with one extension.
    * @param acceptableExtension
    */
   public ExtensionsFileFilter(String acceptableExtension) {
      acceptFilter.add(acceptableExtension);
      description = "*." + acceptableExtension;
   }


   /**
    * Creates a filter with possibly more than one extension.
    * @param acceptableExtensions
    */
   public ExtensionsFileFilter(String[] acceptableExtensions) {
      acceptFilter.addAll(Arrays.asList(acceptableExtensions));

      StringBuffer sb = new StringBuffer();
      Iterator i = acceptFilter.iterator();
      while (i.hasNext()) {
         String extension = (String) i.next();
         sb.append("*.");
         sb.append(extension);
         if (i.hasNext()) {
            sb.append(", ");
         }
      }
      description = sb.toString();
   }

   /** @see {@link FileFilter#accept}. */
   public boolean accept(File file) {
      if (file.isDirectory()) return true;
      String filename = file.toString().toLowerCase();
      int lastDotPos = filename.lastIndexOf(DOT) + 1;
      if (lastDotPos != 0) {
         String extension = filename.substring(lastDotPos);
         return acceptFilter.contains(extension);
      }

      return false; //maybe this should be true..?  if a file has no extension, we don't know what it is...
   }

   /** @see {@link FileFilter#getDescription}. */
   public String getDescription() {
      return description;
   }
}
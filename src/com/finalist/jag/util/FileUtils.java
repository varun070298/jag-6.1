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

package com.finalist.jag.util;

import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import java.io.PrintWriter;

/**
 * A couple of file-related utilities.
 *
 * @author Michael O'Connor - Finalist IT Group
 */
public class FileUtils {

   /**
    * Just calling File.delete() only works sporadically on my system.  It's a strange problem,
    * but after a bit of trial and error, here's a solution that works for me..
    *
    * @param file the file to be deleted.
    */
   public static void deleteFile(File file) {
      if (file.exists() && !file.delete()) {
         System.gc();
         if (!file.delete()) {
            try {
               Thread.sleep(100);
            } catch (InterruptedException e) {
            }
            deleteFile(file);
         }
      }
   }

   /**
    * Creates a file and writes the specified content into it.
    *
    * @param file
    * @param content
    * @throws IOException
    */
   public static void createFile(File file, String content) throws IOException {
      file.getParentFile().mkdirs();
      FileWriter writer = new FileWriter(file);
      PrintWriter prwriter = new PrintWriter(writer);
      prwriter.print(content);
      prwriter.flush();
      prwriter.close();
      writer.close();
   }

}

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

import java.io.*;
import java.util.*;


/**
 * Class DirectoryIterator
 *
 *
 * @author Wendel D. de Witte
 * @version %I%, %G%
 */
public class DirectoryIterator {

   /** Field rootDirectoryFile           */
   protected File rootDirectoryFile = null;

   /** Field currentDirecory           */
   protected File currentDirectory = null;

   /** Field bRecursive           */
   protected boolean bRecursive = true;

   /** Field bRecursive           */
   protected boolean bIncludeDirs = false;

   /** Field currInterator           */
   protected DirectoryIterator currInterator = null;

   /** Field currentFiles           */
   protected File[] currentFiles = null;

   /** Field nCurrentFileIndex           */
   protected int nCurrentFileIndex = 0;


   /**
    * Constructor DirectoryIterator
    *
    *
    * @param sRootpath
    *
    */
   public DirectoryIterator(String sRootpath) {
      this(sRootpath, true);
   }


   /**
    * Constructor DirectoryIterator
    *
    *
    * @param sRootpath
    * @param bRecursive
    *
    */
   public DirectoryIterator(String sRootpath, boolean bRecursive) {
      this(sRootpath, true, false);
   }


   public DirectoryIterator(String sRootpath, boolean bRecursive, boolean bIncludeDirs) {
      rootDirectoryFile = new File(sRootpath);
      this.bRecursive = bRecursive;
      this.bIncludeDirs = bIncludeDirs;
   }


   /**
    * Method getNext
    *
    *
    * @return
    *
    */
   public File getNext() {

      if (currInterator != null) {
         File file = null;
         if ((file = currInterator.getNext()) != null) {
            return file;
         }
      }

      if ((currentFiles != null) && (nCurrentFileIndex < currentFiles.length)) {
         File file = currentFiles[nCurrentFileIndex];
         if (file.isDirectory() && currentDirectory != file) {
            currentDirectory = file;
            return file;
         }
         nCurrentFileIndex++;
         if (file.isDirectory()) {
            if (file.compareTo(rootDirectoryFile) != 0) {
               currInterator = new DirectoryIterator(file.getPath(), bRecursive);
            }
            return getNext();
         }
         return file;
      }
      else if (currentFiles != null) {
         return null;
      }
      currentFiles = rootDirectoryFile.listFiles();
      nCurrentFileIndex = 0;

      return (currentFiles != null) ? getNext() : null;
   }
}

;

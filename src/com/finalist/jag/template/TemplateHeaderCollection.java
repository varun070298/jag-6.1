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


import java.util.*;

import com.finalist.jag.*;


/**
 * Class TemplateHeaderCollection
 *
 *
 * @author Wendel D. de Witte
 * @version %I%, %G%
 */
public class TemplateHeaderCollection {

   /** Field headerLines           */
   private HeaderLine[] headerLines = null;


   /**
    * Constructor TemplateHeaderCollection
    *
    *
    * @param headerLines
    *
    */
   public TemplateHeaderCollection(HeaderLine[] headerLines) {
      this.headerLines = headerLines;
   }


   /**
    * Method getHeaderUrl
    *
    *
    * @param s
    *
    * @return
    *
    * @throws JagException
    *
    */
   public UrlHeaderLine getHeaderUrl(String s) throws JagException {

      UrlHeaderLine line = findHeaderUrl(s);

      if (line == null) {
         throw new JagException(
            "Missing header definition for the taglibrary " + s);
      }

      return line;
   }


   /**
    * Method findHeaderUrl
    *
    *
    * @param s
    *
    * @return
    *
    */
   public UrlHeaderLine findHeaderUrl(String s) {

      for (int i = 0; i < headerLines.length; i++) {
         HeaderLine headerLine = headerLines[i];

         if (!(headerLine instanceof UrlHeaderLine)) {
            continue;
         }

         UrlHeaderLine urlHeader = (UrlHeaderLine) headerLine;

         if (s.equals(urlHeader.getPrefix())) {
            return urlHeader;
         }
      }

      return null;
   }
}

;
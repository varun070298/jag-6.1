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

import com.finalist.jag.template.parser.JagParameter;

/**
 * Class UrlHeaderLine
 *
 *
 * @author Wendel D. de Witte
 * @version %I%, %G%
 */
public class UrlHeaderLine extends HeaderLine {

   /** Field url           */
   private String url = null;

   /** Field prefix           */
   private String prefix = null;


   /**
    * Constructor UrlHeaderLine
    *
    *
    * @param identArray
    * @param paramArray
    * @param url
    * @param prefix
    *
    */
   public UrlHeaderLine(String[] identArray, JagParameter[] paramArray,
      String url, String prefix) {

      super(identArray, paramArray);

      this.url = url;
      this.prefix = prefix;
   }


   /**
    * Method getUrl
    *
    *
    * @return
    *
    */
   public final String getUrl() {
      return url;
   }


   /**
    * Method getPrefix
    *
    *
    * @return
    *
    */
   public final String getPrefix() {
      return prefix;
   }
}
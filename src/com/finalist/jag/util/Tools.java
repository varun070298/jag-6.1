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

/*
 * @(#)Tools.java	1.0 17/Sept/2001
 *
 * @author : Wendel D. de Witte
 * @version 1.0
 */

import java.util.Collection;
import java.util.ArrayList;
import java.util.HashMap;
import java.awt.Point;
import java.io.*;

public class Tools {

   // Encoding maps for the decoding/encoding methods.
   private static HashMap decodeMap;
   private static HashMap encodeMap;


   static {
      encodeMap = new HashMap();
      encodeMap.put("&", "&amp;");
      encodeMap.put(" ", "&nbsp;");
      encodeMap.put("\"", "&quot;");
      encodeMap.put("'", "&apos;");
      encodeMap.put("<", "&lt;");
      encodeMap.put(">", "&gt;");
      encodeMap.put("�", "&copy;");
      encodeMap.put("�", "&reg;");
      encodeMap.put("�", "&acute;");
      encodeMap.put("�", "&laquo;");
      encodeMap.put("�", "&raquo;");
      encodeMap.put("�", "&iexcl;");
      encodeMap.put("�", "&iquest;");
      encodeMap.put("�", "&Agrave;");
      encodeMap.put("�", "&agrave;");
      encodeMap.put("�", "&Aacute;");
      encodeMap.put("�", "&aacute;");
      encodeMap.put("�", "&Acirc;");
      encodeMap.put("�", "&acirc;");
      encodeMap.put("�", "&Atilde;");
      encodeMap.put("�", "&atilde;");
      encodeMap.put("�", "&Auml;");
      encodeMap.put("�", "&auml;");
      encodeMap.put("�", "&Aring;");
      encodeMap.put("�", "&aring;");
      encodeMap.put("�", "&AElig;");
      encodeMap.put("�", "&aelig;");
      encodeMap.put("�", "&Ccedil;");
      encodeMap.put("�", "&ccedil;");
      encodeMap.put("�", "&ETH;");
      encodeMap.put("�", "&eth;");
      encodeMap.put("�", "&Egrave;");
      encodeMap.put("�", "&egrave;");
      encodeMap.put("�", "&Eacute;");
      encodeMap.put("�", "&eacute;");
      encodeMap.put("�", "&Ecirc;");
      encodeMap.put("�", "&ecirc;");
      encodeMap.put("�", "&Euml;");
      encodeMap.put("�", "&euml;");
      encodeMap.put("�", "&Igrave;");
      encodeMap.put("�", "&igrave;");
      encodeMap.put("�", "&Iacute;");
      encodeMap.put("�", "&iacute;");
      encodeMap.put("�", "&Icirc;");
      encodeMap.put("�", "&icirc;");
      encodeMap.put("�", "&Iuml;");
      encodeMap.put("�", "&iuml;");
      encodeMap.put("�", "&Ntilde;");
      encodeMap.put("�", "&ntilde;");
      encodeMap.put("�", "&Ograve;");
      encodeMap.put("�", "&ograve;");
      encodeMap.put("�", "&Oacute;");
      encodeMap.put("�", "&oacute;");
      encodeMap.put("�", "&Ocirc;");
      encodeMap.put("�", "&ocirc;");
      encodeMap.put("�", "&Otilde;");
      encodeMap.put("�", "&otilde;");
      encodeMap.put("�", "&Ouml;");
      encodeMap.put("�", "&ouml;");
      encodeMap.put("�", "&Oslash;");
      encodeMap.put("�", "&oslash;");
      encodeMap.put("�", "&Ugrave;");
      encodeMap.put("�", "&ugrave;");
      encodeMap.put("�", "&Uacute;");
      encodeMap.put("�", "&uacute;");
      encodeMap.put("�", "&Ucirc;");
      encodeMap.put("�", "&ucirc;");
      encodeMap.put("�", "&Uuml;");
      encodeMap.put("�", "&uuml;");
      encodeMap.put("�", "&Yacute;");
      encodeMap.put("�", "&yacute;");
      encodeMap.put("�", "&yuml;");
      encodeMap.put("�", "&THORN;");
      encodeMap.put("�", "&thorn;");
      encodeMap.put("�", "&szlig;");
      encodeMap.put("�", "&sect;");
      encodeMap.put("�", "&para;");
      encodeMap.put("�", "&micro;");
      encodeMap.put("�", "&brvbar;");
      encodeMap.put("�", "&plusmn;");
      encodeMap.put("�", "&middot;");
      encodeMap.put("�", "&uml;");
      encodeMap.put("�", "&cedil;");
      encodeMap.put("�", "&ordf;");
      encodeMap.put("�", "&ordm;");
      encodeMap.put("�", "&not;");
      encodeMap.put("�", "&shy;");
      encodeMap.put("�", "&macr;");
      encodeMap.put("�", "&deg;");
      encodeMap.put("�", "&sup1;");
      encodeMap.put("�", "&sup2;");
      encodeMap.put("�", "&sup3;");
      encodeMap.put("�", "&frac14;");
      encodeMap.put("�", "&frac12;");
      encodeMap.put("�", "&frac34;");
      encodeMap.put("�", "&times;");
      encodeMap.put("�", "&divide;");
      encodeMap.put("�", "&cent;");
      encodeMap.put("�", "&pound;");
      encodeMap.put("�", "&curren;");
      encodeMap.put("�", "&yen;");
      java.util.Set keys = encodeMap.keySet();
      java.util.Iterator iterator = keys.iterator();
      decodeMap = new HashMap();
      while (iterator.hasNext()) {
         String key = (String) iterator.next();
         String value = (String) encodeMap.get(key);
         decodeMap.put(value, key);
      }
   }


   /** General-purpose utility function for removing
    * characters from the front and back of string
    * @param src The string to process
    * @param head exact string to strip from head
    * @param tail exact string to strip from tail
    * @return The resulting string
    */
   public static String stripFrontBack(String src, String head, String tail) {
      int h = src.indexOf(head);
      int t = src.lastIndexOf(tail);
      if (h == -1 || t == -1) return src;
      return src.substring(h + 1, t);
   }


   /** General-purpose utility function for encoding the string
    * @param text The string to process
    * @return The resulting string
    */
   public static String encode(String text) {
      char c;
      StringBuffer n = new StringBuffer();
      for (int i = 0; i < text.length(); i++) {
         c = text.charAt(i);
         String code = (String) encodeMap.get("" + c);
         if (code == null)
            n.append(c);
         else
            n.append(code);
      }
      return new String(n);
   }


   /** General-purpose utility function for decoding the string!
    * @param text The string to process
    * @return The resulting string
    */
   public static String decode(String text) {
      StringBuffer n = new StringBuffer();
      for (int i = 0; i < text.length(); i++) {
         char c = text.charAt(i);
         if (c == '&') {
            StringBuffer code = new StringBuffer();
            int j = i;
            for (; j < text.length(); j++) {
               code.append(text.charAt(j));
               if (text.charAt(j) == ';')
                  break;
            }

            String ch = (String) decodeMap.get(new String(code));
            if (ch != null) {
               n.append(ch);
               i = j;
            }
         }
         else {
            n.append(c);
         }
      }
      return new String(n);
   }
}

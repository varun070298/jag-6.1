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
      encodeMap.put("©", "&copy;");
      encodeMap.put("®", "&reg;");
      encodeMap.put("´", "&acute;");
      encodeMap.put("«", "&laquo;");
      encodeMap.put("»", "&raquo;");
      encodeMap.put("¡", "&iexcl;");
      encodeMap.put("¿", "&iquest;");
      encodeMap.put("À", "&Agrave;");
      encodeMap.put("à", "&agrave;");
      encodeMap.put("Á", "&Aacute;");
      encodeMap.put("á", "&aacute;");
      encodeMap.put("Â", "&Acirc;");
      encodeMap.put("â", "&acirc;");
      encodeMap.put("Ã", "&Atilde;");
      encodeMap.put("ã", "&atilde;");
      encodeMap.put("Ä", "&Auml;");
      encodeMap.put("ä", "&auml;");
      encodeMap.put("Å", "&Aring;");
      encodeMap.put("å", "&aring;");
      encodeMap.put("Æ", "&AElig;");
      encodeMap.put("æ", "&aelig;");
      encodeMap.put("Ç", "&Ccedil;");
      encodeMap.put("ç", "&ccedil;");
      encodeMap.put("Ð", "&ETH;");
      encodeMap.put("ð", "&eth;");
      encodeMap.put("È", "&Egrave;");
      encodeMap.put("è", "&egrave;");
      encodeMap.put("É", "&Eacute;");
      encodeMap.put("é", "&eacute;");
      encodeMap.put("Ê", "&Ecirc;");
      encodeMap.put("ê", "&ecirc;");
      encodeMap.put("Ë", "&Euml;");
      encodeMap.put("ë", "&euml;");
      encodeMap.put("Ì", "&Igrave;");
      encodeMap.put("ì", "&igrave;");
      encodeMap.put("Í", "&Iacute;");
      encodeMap.put("í", "&iacute;");
      encodeMap.put("Î", "&Icirc;");
      encodeMap.put("î", "&icirc;");
      encodeMap.put("Ï", "&Iuml;");
      encodeMap.put("ï", "&iuml;");
      encodeMap.put("Ñ", "&Ntilde;");
      encodeMap.put("ñ", "&ntilde;");
      encodeMap.put("Ò", "&Ograve;");
      encodeMap.put("ò", "&ograve;");
      encodeMap.put("Ó", "&Oacute;");
      encodeMap.put("ó", "&oacute;");
      encodeMap.put("Ô", "&Ocirc;");
      encodeMap.put("ô", "&ocirc;");
      encodeMap.put("Õ", "&Otilde;");
      encodeMap.put("õ", "&otilde;");
      encodeMap.put("Ö", "&Ouml;");
      encodeMap.put("ö", "&ouml;");
      encodeMap.put("Ø", "&Oslash;");
      encodeMap.put("ø", "&oslash;");
      encodeMap.put("Ù", "&Ugrave;");
      encodeMap.put("ù", "&ugrave;");
      encodeMap.put("Ú", "&Uacute;");
      encodeMap.put("ú", "&uacute;");
      encodeMap.put("Û", "&Ucirc;");
      encodeMap.put("û", "&ucirc;");
      encodeMap.put("Ü", "&Uuml;");
      encodeMap.put("ü", "&uuml;");
      encodeMap.put("Ý", "&Yacute;");
      encodeMap.put("ý", "&yacute;");
      encodeMap.put("ÿ", "&yuml;");
      encodeMap.put("Þ", "&THORN;");
      encodeMap.put("þ", "&thorn;");
      encodeMap.put("ß", "&szlig;");
      encodeMap.put("§", "&sect;");
      encodeMap.put("¶", "&para;");
      encodeMap.put("µ", "&micro;");
      encodeMap.put("¦", "&brvbar;");
      encodeMap.put("±", "&plusmn;");
      encodeMap.put("·", "&middot;");
      encodeMap.put("¨", "&uml;");
      encodeMap.put("¸", "&cedil;");
      encodeMap.put("ª", "&ordf;");
      encodeMap.put("º", "&ordm;");
      encodeMap.put("¬", "&not;");
      encodeMap.put("­", "&shy;");
      encodeMap.put("¯", "&macr;");
      encodeMap.put("°", "&deg;");
      encodeMap.put("¹", "&sup1;");
      encodeMap.put("²", "&sup2;");
      encodeMap.put("³", "&sup3;");
      encodeMap.put("¼", "&frac14;");
      encodeMap.put("½", "&frac12;");
      encodeMap.put("¾", "&frac34;");
      encodeMap.put("×", "&times;");
      encodeMap.put("÷", "&divide;");
      encodeMap.put("¢", "&cent;");
      encodeMap.put("£", "&pound;");
      encodeMap.put("¤", "&curren;");
      encodeMap.put("¥", "&yen;");
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

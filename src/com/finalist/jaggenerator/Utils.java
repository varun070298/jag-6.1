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

import java.text.StringCharacterIterator;

/**
 *
 * @author  hillie
 */
public class Utils {

   public static String toClassName(String unformatted) {
      try {
         if (unformatted.startsWith("T_")) {
            unformatted = unformatted.substring(2);
         }
         String s = format(unformatted);
         s = initCap(s);
         return s;
      } catch (Exception e) {
         return unformatted;
      }
   }

   /**
    * Format the name by only allowing lowercase.
    */
   public static String formatLowercase(String unformatted) {
      try {
         if (unformatted == null) {
             return null;
         }
         StringBuffer sb = new StringBuffer(unformatted.toLowerCase());
         StringBuffer formattedString = new StringBuffer();
         //remove underscores
         for (int i = 0; i < sb.length(); i++) {
             char c = sb.charAt(i);
             if (c >= 'a' && c <= 'z') {
                 formattedString.append(c);
             }
         }        
         return formattedString.toString();
      } catch (Exception e) {
         return unformatted;
      }
   }

   /**
    * Format the name by only allowing lowercase and uppercase and 
    * always start with uppercase.
    */
   public static String formatLowerAndUpperCase(String unformatted) {
      try {
         if (unformatted == null) {
             return null;
         }
         StringBuffer sb = new StringBuffer(unformatted);
         StringBuffer formattedString = new StringBuffer();
         //remove underscores
         for (int i = 0; i < sb.length(); i++) {
             char c = sb.charAt(i);
             if ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z')){
                 formattedString.append(c);
             }
         }        
         return firstToUpperCase(formattedString.toString());
      } catch (Exception e) {
         return unformatted;
      }
   }
   
   public static String format(String unformatted) {
      try {
         StringBuffer sb = new StringBuffer(unformatted.toLowerCase());
         //remove underscore and capitalize nest character
         int i = sb.toString().indexOf("_");
         while (i >= 0) {
            if (i > -1) sb.replace(i, i + 2, sb.substring(i + 1, i + 2).toUpperCase());
            i = sb.toString().indexOf("_");
         }
         //remove dash and capitalize nest character
         i = sb.toString().indexOf("-");
         while (i >= 0) {
            if (i > -1) sb.replace(i, i + 2, sb.substring(i + 1, i + 2).toUpperCase());
            i = sb.toString().indexOf("-");
         }
         return sb.toString();
      } catch (Exception e) {
         return unformatted;
      }
   }

   /**
    * Undoes a format.  Everything is capitalised and any uppercase character
    * forces a '_' to be prefixed, e.g. "theColumnName" is unformatted to "THE_COLUMN_NAME", and
    * "theATrain" unformats to "THE_A_TRAIN".
    * @param formatted
    * @return the unformatted String.
    */
   public static String unformat(String formatted) {
      StringBuffer result = new StringBuffer();
      for (int i = 0; i < formatted.length(); i++) {
         if (Character.isUpperCase(formatted.charAt(i))) {
            result.append('_');
         }
         result.append(Character.toUpperCase(formatted.charAt(i)));
      }
      return result.toString();
   }

   public static String initCap(String unformatted) {
      StringBuffer sb = new StringBuffer(unformatted);
      //capitalize first character
      if ((unformatted != null) && (unformatted.length() > 0)) {
         sb.replace(0, 1, sb.substring(0, 1).toUpperCase());
         return sb.toString();
      } else
         return unformatted;
   }

   public static String formatFKName(String fkColumnName) {
      return format(fkColumnName) + "FK";
   }

   public static String firstToLowerCase(String text) {
      return (text == null || text.length() == 0) ? "" : Character.toLowerCase(text.charAt(0)) +
            (text.length() > 1 ? text.substring(1) : "");
   }
   
   public static String firstToUpperCase(String text) {
      return (text == null || text.length() == 0) ? "" : Character.toUpperCase(text.charAt(0)) +
            (text.length() > 1 ? text.substring(1) : "");
   }
   
}

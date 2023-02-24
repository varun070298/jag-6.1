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

/**
 * This class is used to add special formatting capabilities to a String, as needed by the
 * JAG Velocity templates.  For example, in a template you can access the 'crazy struts'
 * format of an entity bean field name with something like <code>${entity.Name.CrazyStruts}</code>.
 *
 * @author Michael O'Connor - Finalist IT Group
 */
public class TemplateString {
   private String string;

   /**
    * Creates a TemplateString with a given String.
    *
    * @param string
    */
   public TemplateString(String string) {
      this.string = string;
   }

   /**
    * Lower cases the entire string.
    *
    * @return
    */
   public String getLower() {
      return string.toLowerCase();
   }

   /**
    * Upper cases the entire string.
    *
    * @return
    */
   public String getUpper() {
      return string.toUpperCase();
   }

   /**
    * Upper cases the first letter of the string.
    *
    * @return
    */
   public TemplateString getSentensized() {
      if (string.length() > 1) {
         return new TemplateString(string.substring(0, 1).toUpperCase() + string.substring(1));
      }
      return new TemplateString(string.toUpperCase());
   }

   /**
    * Lower cases the first letter of the string.
    *
    * @return
    */
   public TemplateString getDesentensized() {
      if (string.length() > 1) {
         return new TemplateString(string.substring(0, 1).toLowerCase() + string.substring(1));
      }
      return new TemplateString(string.toLowerCase());
   }

   /**
    * Formats the string to the strange format used by Struts:
    *
    * <li>anElephant --> anElephant</li>
    * <li>aHorse --> AHorse</li>
    *
    * @return
    */
   public String getCrazyStruts() {
      if (string.length() > 1 &&
            Character.isLowerCase(string.charAt(0)) && Character.isUpperCase(string.charAt(1))) {
         return Character.toUpperCase(string.charAt(0)) + string.substring(1);
      }
      return string;
   }

   /**
    * Applies a 'database-table to Java-classname' formatting, for example:
    * both "the_table" and "THE_TABLE" becomes "TheTable", and
    * "table-name" becomes "TableName".
    *
    * @return
    */
   public String getClassNameFormat() {
      if (string == null || string.trim().equals("")) {
         return string;
      }
      String temp = Character.toUpperCase(string.charAt(0)) +
            (string.length() > 1 ? string.substring(1).toLowerCase() : "");
      try {
         StringBuffer sb = new StringBuffer(temp);
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
         return string;
      }
   }

   public String toString() {
      return string;
   }

   /**
    * <b>NOTE:</b> To enable simple String comparisons, a TemplateString is equal to a String with the same value.
    * <p>
    * For example, the following template snippet is a valid equality check:<br>
    * <code>#if (${datasource.Mapping.equals("Oracle 8")}) ...</code>
    *
    * @param o
    * @return
    */
   public boolean equals(Object o) {
      if (this == o) return true;
      if (!(o instanceof TemplateString || o instanceof String)) return false;
      final String other = o.toString();
      if (string != null ? !string.equals(other) : other != null) return false;
      return true;
   }

   public int hashCode() {
      return (string != null ? string.hashCode() : 0);
   }
}

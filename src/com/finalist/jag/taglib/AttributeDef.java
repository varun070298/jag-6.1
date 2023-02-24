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

package com.finalist.jag.taglib;


import java.util.Collection;


/**
 * Class AttributeDef
 *
 *
 * @author Wendel D. de Witte
 * @version %I%, %G%
 */
public class AttributeDef {

   /** Field name           */
   private String name = null;

   /** Field required           */
   private boolean required = false;


   /**
    * Constructor AttributeDef
    *
    *
    */
   public AttributeDef() {
   }


   /**
    * Constructor AttributeDef
    *
    *
    * @param n
    *
    */
   public AttributeDef(AttributeDef n) {
      this.name = n.name;
      this.required = n.required;
   }


   /**
    * Method setName
    *
    *
    * @param name
    *
    */
   public void setName(String name) {
      this.name = name;
   }


   /**
    * Method setRequired
    *
    *
    * @param required
    *
    */
   public void setRequired(boolean required) {
      this.required = required;
   }


   /**
    * Method setRequired
    *
    *
    * @param required
    *
    */
   public void setRequired(String required) {

      this.required = !((required == null)
         || required.equalsIgnoreCase("false")
         || required.equalsIgnoreCase("0"));
   }


   /**
    * Method getName
    *
    *
    * @return
    *
    */
   public String getName() {
      return (this.name);
   }


   /**
    * Method getRequired
    *
    *
    * @return
    *
    */
   public boolean getRequired() {
      return (this.required);
   }


   /**
    * Method toString
    *
    *
    * @return
    *
    */
   public String toString() {
      StringBuffer toString = new StringBuffer();
      toString.append("\name : ");
      toString.append(name);
      toString.append("\required : ");
      toString.append(required);

      return new String(toString);
   }
}
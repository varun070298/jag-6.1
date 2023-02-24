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

package com.finalist.jag.skelet;


import java.util.*;


/**
 * Class ModuleData
 *
 *
 * @author Wendel D. de Witte
 * @version %I%, %G%
 */
public class ModuleData {

   /** Field COLLECTION           */
   private static final short COLLECTION = 1;

   /** Field STRING           */
   private static final short STRING = 2;

   /** Field name           */
   private String name;

   /** Field value           */
   private Object value;

   /** Field valueType           */
   private short valueType;


   /**
    * Constructor ModuleData
    *
    *
    * @param name
    * @param value
    *
    */
   public ModuleData(String name, String value) {
      setName(name);
      setValue(value);
   }


   /**
    * Constructor ModuleData
    *
    *
    * @param name
    * @param value
    *
    */
   public ModuleData(String name, Collection value) {
      setName(name);
      setValue(value);
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
    * Method getValue
    *
    *
    * @return
    *
    */
   public Object getValue() {
      return (this.value);
   }


   /**
    * Method setValue
    *
    *
    * @param value
    *
    */
   public void setValue(String value) {
      this.valueType = STRING;
      this.value = value;
   }


   /**
    * Method setValue
    *
    *
    * @param value
    *
    */
   public void setValue(Collection value) {
      this.valueType = COLLECTION;
      this.value = value;
   }


   /**
    * Method isValueCollection
    *
    *
    * @return
    *
    */
   public boolean isValueCollection() {
      return this.valueType == COLLECTION;
   }


   /**
    * Method isValueString
    *
    *
    * @return
    *
    */
   public boolean isValueString() {
      return this.valueType == STRING;
   }
}
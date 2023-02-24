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
package com.finalist.jaggenerator.template;

import java.util.HashMap;


/**
 * This bean represents a single configurable parameter,
 * as specified in a template's "template.xml".
 * 
 * @author Michael O'Connor - Finalist IT Group
 */
public class TemplateConfigParameter {

   /**
    * The 'type' value for a parameter whose value is determined by entering text into an input field.
    */
   public static final Type TYPE_TEXT = new Type("text");
   /**
    * The 'type' value for a parameter whose value is determined by clicking an on/off checkbox.
    */
   public static final Type TYPE_CHECKBOX = new Type("checkbox");
   /**
    * The 'type' value for a parameter whose value is determined by selecting an item from a predefined list.
    */
   public static final Type TYPE_LIST = new Type("list");
   /**
    * The 'type' value for a parameter whose value is determined by either:
    * <li>selecting an item from a predefined list, or </li>
    * <li>typing in a 'free text' value</li>
    */
   public static final Type TYPE_EDITABLE_LIST = new Type("list-editable");

   private static final HashMap types = new HashMap();
   static {
      types.put(TYPE_TEXT.toString(), TYPE_TEXT);
      types.put(TYPE_CHECKBOX.toString(), TYPE_CHECKBOX);
      types.put(TYPE_LIST.toString(), TYPE_LIST);
      types.put(TYPE_EDITABLE_LIST.toString(), TYPE_EDITABLE_LIST);
   }

   private String id;
   private String name;
   private String description;
   private Type type;
   private String[] presetValues;
   private String value;


   /**
    * Gets the id - the unique identifier of this parameter used to access the parameter value
    * from the templates.
    *
    * @return id
    */
   public String getId() {
      return id;
   }

   /**
    * Sets the id - the unique identifier of this parameter used to access the parameter value
    * from the templates.  This value should be a String following the same naming conventions
    * as a Java bean attribute (e.g. no spaces, hyphens, etc.).
    *
    * @param id
    */
   public void setId(String id) {
      this.id = id;
   }

   /**
    * Gets the name - this is the human-readable short name used to represent this parameter in the GUI.
    *
    * @return
    */
   public String getName() {
      return name;
   }

   /**
    * Sets the name - this is the human-readable short name used to represent this parameter in the GUI.
    *
    * @param name
    */
   public void setName(String name) {
      this.name = name;
   }

   /**
    * Gets the description for this parameter - shows up in the GUI as a tooltip.
    *
    * @return
    */
   public String getDescription() {
      return description;
   }

   /**
    * Sets the description for this parameter - shows up in the GUI as a tooltip.
    *
    * @param description
    */
   public void setDescription(String description) {
      this.description = description;
   }

   /**
    * Gets the type of this configuration parameter.
    *
    * @return one of the TYPE_XXX constants defined in this class.
    */
   public TemplateConfigParameter.Type getType() {
      return type;
   }

   /**
    * Sets the type of this configuration parameter.
    *
    * @param type - Use one of the TYPE_XXX constants defined in this class.
    */
   public void setType(TemplateConfigParameter.Type type) {
      this.type = type;
   }

   /**
    * Gets the preset values for this parameter.
    * 
    * @return a String[] (may have length zero, never <code>null</code>).
    */
   public String[] getPresetValues() {
      return presetValues;
   }

   public void setPresetValues(String[] presetValues) {
      this.presetValues = presetValues;
   }

   public String getValue() {
      return value;
   }

   public void setValue(String value) {
      this.value = value;
   }

   /**
    * Translates a type's name into the corresponding Type object.
    *
    * @param name
    * @return <code>null</code> if the name is not a valid Type.
    */
   public static Type getTypeByName(String name) {
      return (Type) types.get(name);
   }


   /** Objects of this class are used to determine a TemplateConfigParamater's type */
   private final static class Type {
      private String type;

      private Type(String type) {
         this.type = type;
      }

      public String toString() {
         return type;
      }
   }

}

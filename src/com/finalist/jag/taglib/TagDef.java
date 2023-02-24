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
import java.util.*;


/**
 * Class TagDef
 *
 *
 * @author Wendel D. de Witte
 * @version %I%, %G%
 */
public class TagDef {

   /** Field name           */
   private String name = null;

   /** Field tagClass           */
   private String tagClass = null;

   /** Field bodyContent           */
   private String bodyContent = null;

   /** Field attrDefs           */
   private Collection attrDefs = null;


   /**
    * Constructor TagDef
    *
    *
    */
   public TagDef() {
   }


   /**
    * Constructor TagDef
    *
    *
    * @param n
    *
    */
   public TagDef(TagDef n) {

      this.name = n.name;
      this.tagClass = n.tagClass;
      this.bodyContent = n.bodyContent;
      attrDefs = new ArrayList();

      AttributeDef[] ar = this.getAttributeDefArray();

      for (int i = 0; i < ar.length; i++) {
         attrDefs.add(new AttributeDef(ar[i]));
      }
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
    * Method setTagClass
    *
    *
    * @param tagClass
    *
    */
   public void setTagClass(String tagClass) {
      this.tagClass = tagClass;
   }


   /**
    * Method setAttributeDefs
    *
    *
    * @param attrDefs
    *
    */
   public void setAttributeDefs(Collection attrDefs) {
      this.attrDefs = attrDefs;
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
    * Method getTagClass
    *
    *
    * @return
    *
    */
   public String getTagClass() {
      return (this.tagClass);
   }


   /**
    * Method getAttributeDefs
    *
    *
    * @return
    *
    */
   public Collection getAttributeDefs() {
      return (this.attrDefs);
   }


   /**
    * Method getAttributeDefArray
    *
    *
    * @return
    *
    */
   public AttributeDef[] getAttributeDefArray() {

      int size = attrDefs.size();

      return (AttributeDef[]) attrDefs.toArray(new AttributeDef[size]);
   }


   /**
    * Method setBodyContent
    *
    *
    * @param bodyContent
    *
    */
   public void setBodyContent(String bodyContent) {
      this.bodyContent = bodyContent;
   }


   /**
    * Method getBodyContent
    *
    *
    * @return
    *
    */
   public String getBodyContent() {
      return (this.bodyContent);
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
      toString.append("\nname : ");
      toString.append(name);
      toString.append("\ntagClass : ");
      toString.append(tagClass);
      toString.append("\nbodyContent : ");
      toString.append(bodyContent);
      toString.append("\nattrDefs : ");
      toString.append(attrDefs);

      return new String(toString);
   }
}
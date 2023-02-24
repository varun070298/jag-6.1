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

import com.finalist.jag.JagException;
import com.finalist.jag.template.*;

/**
 * Class TagLibrary
 *
 *
 * @author Wendel D. de Witte
 * @version %I%, %G%
 */
public class TagLibrary {
   /** Field libVersion           */
   private String libVersion = null;

   /** Field jagVersion           */
   private String jagVersion = null;

   /** Field shortName           */
   private String shortName = null;

   /** Field info           */
   private String info = null;

   /** Field tagDefs           */
   private Collection tagDefs = null;


   /**
    * Method setLibVersion
    *
    *
    * @param libVersion
    *
    */
   public void setLibVersion(String libVersion) {
      this.libVersion = libVersion;
   }


   /**
    * Method setJagVersion
    *
    * @param jagVersion
    *
    */
   public void setJagVersion(String jagVersion) {
      this.jagVersion = jagVersion;
   }


   /**
    * Method setShortName
    *
    *
    * @param shortName
    *
    */
   public void setShortName(String shortName) {
      this.shortName = shortName;
   }


   /**
    * Method setInfo
    *
    *
    * @param info
    *
    */
   public void setInfo(String info) {
      this.info = info;
   }


   /**
    * Method setTagDefs
    *
    *
    * @param tagDefs
    *
    */
   public void setTagDefs(Collection tagDefs) {
      this.tagDefs = tagDefs;
   }


   /**
    * Method getLibVersion
    *
    *
    * @return
    *
    */
   public String getLibVersion() {
      return (this.libVersion);
   }


   /**
    * Method getJagVersion
    *
    *
    * @return
    *
    */
   public String getJagVersion() {
      return (this.jagVersion);
   }


   /**
    * Method getShortName
    *
    *
    * @return
    *
    */
   public String getShortName() {
      return (this.shortName);
   }


   /**
    * Method getInfo
    *
    *
    * @return
    *
    */
   public String getInfo() {
      return (this.info);
   }


   /**
    * Method getTagDefs
    *
    *
    * @return
    *
    */
   public Collection getTagDefs() {
      if (tagDefs == null)
         tagDefs = new java.util.ArrayList();

      return (this.tagDefs);
   }


   /**
    * Method getTagDef
    *
    *
    * @param tagRef
    *
    * @return
    *
    * @throws JagException
    *
    */
   public TagDef getTagDef(TemplateTag tagRef) throws JagException {
      TagDef tagdef = findTagDef(tagRef);

      if (tagdef == null) {
         throw new JagException("" + tagRef.getTagName()
            + " doesn't exist in the tag library "
            + shortName);
      }
      return tagdef;
   }


   /**
    * Method findTagDef
    *
    *
    * @param tagRef
    *
    * @return
    *
    */
   public TagDef findTagDef(TemplateTag tagRef) {
      java.util.Iterator iterator = tagDefs.iterator();

      while (iterator.hasNext()) {
         TagDef tagDef = (TagDef) iterator.next();

         if (!(getShortName().equals(tagRef.getTagLib())))
            continue;    // should be impossible

         if (!(tagDef.getName().equals(tagRef.getTagName())))
            continue;

         return tagDef;
      }

      return null;
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
      toString.append("\nlibVersion : ");
      toString.append(libVersion);
      toString.append("\njagVersion : ");
      toString.append(jagVersion);
      toString.append("\nshortName : ");
      toString.append(shortName);
      toString.append("\ninfo : ");
      toString.append(info);
      toString.append("\ntagDefs : ");
      toString.append(tagDefs);

      Collection col = getTagDefs();
      java.util.Iterator iterator = col.iterator();

      while (iterator.hasNext()) {
         TagDef tagDef = (TagDef) iterator.next();
         toString.append("[TagDef]" + tagDef.toString());
      }
      return new String(toString);
   }
}


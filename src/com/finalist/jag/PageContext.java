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

package com.finalist.jag;


import com.finalist.jag.template.*;


/**
 * Class PageContext
 *
 *
 * @author Wendel D. de Witte
 * @version %I%, %G%
 */
public class PageContext {

   /** Field sessionContext */
   private SessionContext sessionContext = null;

   /** Field headerCollection */
   private TemplateHeaderCollection headerCollection = null;

   /** Field pageAttributes */
   private AttributeMap pageAttributes = new AttributeMap();

   /** Field templateData */
   private TemplateStructure templateData = null;


   /**
    * Constructor PageContext
    *
    *
    * @param sessionContext
    *
    */
   public PageContext(SessionContext sessionContext) {
      this.sessionContext = sessionContext;
   }


   /**
    * Method setHeaderCollection
    *
    *
    * @param headerCollection
    *
    */
   public void setHeaderCollection(
      TemplateHeaderCollection headerCollection) {
      this.headerCollection = headerCollection;
   }


   /**
    * Method setTemplateData
    *
    *
    * @param templateData
    *
    */
   public void setTemplateData(TemplateStructure templateData) {
      this.templateData = templateData;
   }


   /**
    * Method getTextCollection
    *
    *
    * @return
    *
    */
   public TemplateTextBlockList getTextCollection() {

      if (templateData == null) {
         return null;
      }

      return templateData.getTextBlockList();
   }


   /**
    * Method getHeaderCollection
    *
    *
    * @return
    *
    */
   public TemplateHeaderCollection getHeaderCollection() {
      return headerCollection;
   }


   /**
    * Method setAttribute
    *
    *
    * @param name
    * @param obj
    *
    */
   public void setAttribute(String name, Object obj) {
      pageAttributes.setAttribute(name, obj);
   }


   /**
    * Method removeAttribute
    *
    *
    * @param name
    *
    */
   public void removeAttribute(String name) {
      pageAttributes.removeAttribute(name);
   }


   /**
    * Method getSessionContext
    *
    *
    * @return
    *
    */
   public SessionContext getSessionContext() {
      return sessionContext;
   }


   /**
    * Method getTemplateData
    *
    *
    * @return
    *
    */
   public TemplateStructure getTemplateData() {
      return templateData;
   }


   /**
    * Method getAttribute
    *
    *
    * @param name
    *
    * @return
    *
    */
   public Object getAttribute(String name) {
      return pageAttributes.getAttribute(name);
   }
}

;

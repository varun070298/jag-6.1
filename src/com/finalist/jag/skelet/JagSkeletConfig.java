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
 * Class JagSkeletConfig
 *
 *
 * @author Wendel D. de Witte
 * @version %I%, %G%
 */
public class JagSkeletConfig {

   /** Field author           */
   private String author;

   /** Field version           */
   private String version;

   /** Field company           */
   private String company;

   /** Field templates           */
   private Collection templates = new HashSet();


   /**
    * Method setAuthor
    *
    *
    * @param author
    *
    */
   public void setAuthor(String author) {
      this.author = author;
   }


   /**
    * Method setVersion
    *
    *
    * @param version
    *
    */
   public void setVersion(String version) {
      this.version = version;
   }


   /**
    * Method setCompany
    *
    *
    * @param company
    *
    */
   public void setCompany(String company) {
      this.company = company;
   }


   /**
    * Method addTemplateUrl
    *
    *
    * @param template
    *
    */
   public void addTemplateUrl(String template) {
      this.templates.add(template);
   }


   /**
    * Method addTemplateUrl
    *
    *
    * @param templates
    *
    */
   public void addTemplateUrl(Collection templates) {

      if (templates == null) {
         return;
      }

      Iterator iterator = templates.iterator();

      while (iterator.hasNext()) {
         addTemplateUrl((String) iterator.next());
      }
   }


   /**
    * Method getAuthor
    *
    *
    * @return
    *
    */
   public String getAuthor() {
      return (this.author);
   }


   /**
    * Method getVersion
    *
    *
    * @return
    *
    */
   public String getVersion() {
      return (this.version);
   }


   /**
    * Method getCompany
    *
    *
    * @return
    *
    */
   public String getCompany() {
      return (this.company);
   }


   /**
    * Method getTemplatesUrls
    *
    *
    * @return
    *
    */
   public String[] getTemplatesUrls() {
      return (String[]) templates.toArray(new String[templates.size()]);
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
      toString.append("\nauthor : ");
      toString.append(author);
      toString.append("\nversion : ");
      toString.append(version);
      toString.append("\ncompany : ");
      toString.append(company);
      toString.append("\ncity : ");
      toString.append(templates);
      toString.append("\ntemplates : ");

      return new String(toString);
   }
}
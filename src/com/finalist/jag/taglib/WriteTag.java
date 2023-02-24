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


import com.finalist.jag.*;
import com.finalist.jag.taglib.util.RequestUtil;
import com.finalist.jaggenerator.Utils;

import java.util.*;


/**
 * Class WriteTag
 *
 *
 * @author Wendel D. de Witte
 * @version %I%, %G%
 */
public class WriteTag extends TagSupport {

   /** Field name           */
   private String name = null;

   /** Field property           */
   private String property = null;

   /** Field display           */
   private String display = null;

   /** Field pathEnable           */
   private String pathEnable = null;

   /////////////////////////////////////

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
    * Method setValue
    *
    *
    * @param property
    *
    */
   public void setProperty(String property) {
      this.property = property;
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
    * Method getValue
    *
    *
    * @return
    *
    */
   public String getProperty() {
      return (this.property);
   }

   /**
    * Method getSensitive
    *
    *
    * @return
    *
    */
   public String getDisplay() {
      return (this.display);
   }

   /**
    * Method setDisplay
    *
    *
    * @param display
    *
    */
   public void setDisplay(String display) {
      this.display = display;
   }

   /**
    * Method getPathenable
    *
    *
    * @return
    *
    */
   public String getPathenable() {
      return (this.pathEnable);
   }

   /**
    * Method setPathenable
    *
    *
    * @param pathEnable
    *
    */
   public void setPathenable(String pathEnable) {
      this.pathEnable = pathEnable;
   }

   /**
    * Method doStartTag
    *
    *
    * @return
    *
    * @throws JagException
    *
    */
   public int doStartTag() throws JagException {

      SessionContext session = getPageContext().getSessionContext();
      String value = RequestUtil.lookupString(getPageContext(),
            name, property);

      if (value == null) {
         throw new JagException("WriteTag: Invalid name field \"" + name
               + "\" (property=\"" + property + "\")");
      }

      if (display != null) {
         display = display.toLowerCase();

         if (display.equals("lower")) {
            value = value.toLowerCase();
         }
         else if (display.equals("upper")) {
            value = value.toUpperCase();
         }
         else if (display.equals("sentensize")) {
            if (value.length() > 1) {
               String tmp = value.substring(0, 1).toUpperCase();

               value = value.substring(1);
               value = tmp + value;
            } else {
               value = value.toUpperCase();
            }
         }
         else if (display.equals("desentensize")) {
            if (value.length() > 1) {
               String tmp = value.substring(0, 1).toLowerCase();

               value = value.substring(1);
               value = tmp + value;
            } else {
               value = value.toLowerCase();
            }
         }
         else if (display.equals("crazy_struts")) {
            //anElephant --> anElephant
            //aHorse --> AHorse
            if (value.length() > 1 &&
                  Character.isLowerCase(value.charAt(0)) && Character.isUpperCase(value.charAt(1))) {
               value = Character.toUpperCase(value.charAt(0)) + value.substring(1);
            }
         }
      }

      if ((pathEnable != null) && pathEnable.equals("true")) {
         value = value.replace('.', '/');
      }

      getWriter().print(value);

      return (EVAL_PAGE);
   }

}
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

import java.util.*;


/**
 * Class EqualTag
 *
 *
 * @author Wendel D. de Witte
 * @version %I%, %G%
 */
public class EqualTag extends TagBodySupport {

   /** Field name           */
   private String name = null;

   /** Field property           */
   private String property = null;

   /** Field parameter           */
   protected String parameter = null;

   /** Field sensitive           */
   private String sensitive = null;

   /** Field equal           */
   protected boolean equal = false;

   /** Field sensitive           */
   protected int counter = 0;

   protected String variable;

   /////////////////////////////////////

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
   public String getProperty() {
      return (this.property);
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
    * Method getParameter
    *
    *
    * @return
    *
    */
   public String getParameter() {
      return (this.parameter);
   }

   /**
    * Method setParameter
    *
    *
    * @param parameter
    *
    */
   public void setParameter(String parameter) {
      this.parameter = parameter;
   }


   public String getSensitive() {
      return (this.sensitive);
   }

   public void setSensitive(String sensitive) {
      this.sensitive = sensitive;
   }

   public String getVariable() {
      return variable;
   }

   public void setVariable(String variable) {
      this.variable = variable;
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

      String value = RequestUtil.lookupString(getPageContext(), name, property);
      if (value == null) {
         value = (String) getPageContext().getAttribute(name);
      }

      if (value == null) {
         value = "";
      }

      if (sensitive != null && sensitive.equals("true")) {
         value = value.toLowerCase();
         parameter = parameter.toLowerCase();
      }

      if (variable != null) {
         Object variableValue = getPageContext().getAttribute(variable);

        if (variableValue == null) {
            equal = false;
         } else {
            equal = (variableValue instanceof String) ?
                  value.equals(((String) variableValue)) :
                  ((Set) variableValue).contains(value);
         }

      } else {
         StringTokenizer tokens = new StringTokenizer(parameter, ", ");
         while (tokens.hasMoreTokens()) {
            String token = tokens.nextToken().trim();
            equal = token.equals(value);
            if (equal) {
               break;
            }
         }
      }
      return (EVAL_PAGE);
   }

   /**
    * Method doAfterBodyTag
    *
    *
    * @return
    *
    * @throws JagException
    *
    */
   public int doAfterBodyTag() throws JagException {
      return (equal && (counter++ < 1)) ? (EVAL_BODY_TAG) : (SKIP_BODY);
   }
}



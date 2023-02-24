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


/**
 * Class AttributeMap
 *
 *
 * @author Wendel D. de Witte
 * @version %I%, %G%
 */
public class AttributeMap extends java.util.HashMap {

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
      return get(name);
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

      if ((name == null) || (obj == null)) {
         return;
      }

      put(name, obj);
   }


   /**
    * Method removeAttribute
    *
    *
    * @param name
    *
    * @return
    *
    */
   public Object removeAttribute(String name) {

      if ((name == null) || isEmpty()) {
         return null;
      }

      return remove(name);
   }
}
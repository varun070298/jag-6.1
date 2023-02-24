/*   Copyright (C) 2005 Finalist IT Group
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

package com.finalist.jaggenerator.modules;

import java.util.Collection;

/**
 * Helper class for representing business arguments.
 *
 * @author Rudie Ekkelenkamp - Finalist IT Group
 * @version $Revision: 1.1 $, $Date: 2004/12/22 11:23:01 $
 */
public class BusinessArgument {
   private String type;
   private String name;

   /**
    * the return type of the argument.
    * @return
    */
   public String getType() {
      return type;
   }

   /**
    * Set the type of the argument.
    *
    * @param type
    */
   public void setType(String type) {
      this.type = type;
   }

   /**
    * get argument name.
    * @return the name.
    */
   public String getName() {
      return name;
   }

   /**
    * set the argument name.
    *
    * @param name
    */
   public void setName(String name) {
      this.name = name;
   }
}

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

/**
 * This tag is used in conjunction with the {@link VariableTag}, to clear the variable space of a
 * 'list' variable.
 *
 * @author Michael O'Connor - Finalist IT Group
 */
public class ClearVariableTag extends TagSupport {

   private String name = null;

   /**
    * Getter for name.
    *
    * @return the variable name to be cleared.
    */
   public String getName() {
      return name;
   }

   /**
    * Setter for name.
    *
    * @param name the variable name to be cleared.
    */
   public void setName(String name) {
      this.name = name;
   }

   /**
    * Method doStartTag
    *
    * @return a {@link TagConstant}.
    * @throws JagException if hell freezes over.
    */
   public int doStartTag() throws JagException {
      getPageContext().removeAttribute(name);
      return EVAL_PAGE;
   }
}

;
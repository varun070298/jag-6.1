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


import java.util.*;

import com.finalist.jag.JagException;


/**
 * Class TagBodySupport
 *
 *
 * @author Wendel D. de Witte
 * @version %I%, %G%
 */
public abstract class TagBodySupport extends TagSupport {

   /** Field bodyText */
   private String bodyText;


   /**
    * Constructor TagBodySupport
    *
    *
    */
   public TagBodySupport() {
      super();
   }


   /**
    * Method doInitBodyTag
    *
    *
    * @throws JagException
    *
    */
   public void doInitBodyTag() throws JagException {
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
   public abstract int doAfterBodyTag() throws JagException;


   /**
    * Method getBodyText
    *
    *
    * @return
    *
    */
   public String getBodyText() {
      return (this.bodyText);
   }


   /**
    * Method setBodyText
    *
    *
    * @param bodyText
    *
    */
   public void setBodyText(String bodyText) {
      this.bodyText = bodyText;
   }
}

;
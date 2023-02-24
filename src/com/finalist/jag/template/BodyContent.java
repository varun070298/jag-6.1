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

package com.finalist.jag.template;


import java.util.*;

import com.finalist.jag.*;


/**
 * Class BodyContent
 *
 *
 * @author Wendel D. de Witte
 * @version %I%, %G%
 */
public class BodyContent {

   /** Field bodyStructure           */
   private TemplateStructure bodyStructure = null;


   /**
    * Constructor BodyContent
    *
    *
    */
   public BodyContent() {
   }


   /**
    * Constructor BodyContent
    *
    *
    * @param n
    *
    */
   public BodyContent(BodyContent n) {
      bodyStructure = new TemplateStructure(n.bodyStructure);
   }


   /**
    * Method setBodyStructure
    *
    *
    * @param bodyStructure
    *
    */
   public void setBodyStructure(TemplateStructure bodyStructure) {
      this.bodyStructure = bodyStructure;
   }


   /**
    * Method getBodyStructure
    *
    *
    * @return
    *
    */
   public TemplateStructure getBodyStructure() {

      if (bodyStructure == null) {
         bodyStructure = new TemplateStructure();
      }

      return (this.bodyStructure);
   }
}
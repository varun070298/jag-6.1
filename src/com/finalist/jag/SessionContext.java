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

import com.finalist.jag.skelet.*;


/**
 * Class SessionContext
 *
 *
 * @author
 * @version %I%, %G%
 */
public class SessionContext extends AttributeMap {

   /** Field skelet           */
   private SkeletDataObj skelet = null;


   /**
    * Method getSkelet
    *
    *
    * @return
    *
    */
   public SkeletDataObj getSkelet() {
      return (this.skelet);
   }


   /**
    * Method setSkelet
    *
    *
    * @param skelet
    *
    */
   public void setSkelet(SkeletDataObj skelet) {
      this.skelet = skelet;
   }
}

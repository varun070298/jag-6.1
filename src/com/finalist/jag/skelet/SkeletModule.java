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
 * Class SkeletModule
 *
 *
 * @author Wendel D. de Witte
 * @version %I%, %G%
 */
public class SkeletModule extends ModuleData {

   /** Field refname */
   private String refname;

   /** Field refs */
   private Collection refs = null;


   /**
    * Constructor SkeletModule
    *
    *
    * @param name
    *
    */
   public SkeletModule(String name) {
      super(name, new ArrayList());
   }


   /**
    * Method getRefname
    *
    *
    * @return
    *
    */
   public String getRefname() {
      return (this.refname);
   }


   /**
    * Method setRefname
    *
    *
    * @param refname
    *
    */
   public void setRefname(String refname) {
      this.refname = refname;
   }


   /**
    * Method getRefs
    *
    *
    * @return
    *
    */
   public Collection getRefs() {

      if (refs == null) {
         refs = new ArrayList();
      }

      return (this.refs);
   }


   /**
    * Method setRefs
    *
    *
    * @param refs
    *
    */
   public void setRefs(Collection refs) {
      this.refs = refs;
   }

   public String toString() {
      return "<SkeletModule: refname=" + refname + " - refs=" + refs + '>';
   }
}
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
 * Class SkeletDataObj
 *
 *
 * @author Wendel D. de Witte
 * @version %I%, %G%
 */
public class SkeletDataObj extends ModuleData {

   /** Field config           */
   private JagSkeletConfig config = null;


   /**
    * Constructor SkeletDataObj
    *
    *
    * @param name
    *
    */
   public SkeletDataObj(String name) {
      super(name, new ArrayList());
   }


   /**
    * Method getConfig
    *
    *
    * @return
    *
    */
   public JagSkeletConfig getConfig() {
      return (this.config);
   }


   /**
    * Method setConfig
    *
    *
    * @param config
    *
    */
   public void setConfig(JagSkeletConfig config) {
      this.config = config;
   }


   /**
    * Method processReferences
    *
    *
    * @throws JagSkeletException
    *
    */
   public void processReferences() throws JagSkeletException {

      HashMap refNames = new HashMap();

      createRefMap(this, refNames);
      replaceReferences(this, refNames);
   }


   /**
    * Method createRefMap
    *
    *
    * @param root
    * @param refNames
    *
    * @throws JagSkeletException
    *
    */
   protected void createRefMap(ModuleData root, HashMap refNames)
      throws JagSkeletException {

      Collection modules = (Collection) getValue();
      Iterator iterator = modules.iterator();

      while (iterator.hasNext()) {
         SkeletModule module = (SkeletModule) iterator.next();

         if (!"".equals(module.getRefname()) && refNames.get(module.getRefname()) != null) {
            throw new JagSkeletException(
               "Found duplicate ref-name field : "
               + module.getRefname());
         }

         refNames.put(module.getRefname(), module);
      }
   }


   /**
    * Method replaceReferences
    *
    *
    * @param root
    * @param refNames
    *
    */
   protected void replaceReferences(ModuleData root, HashMap refNames) {

      Collection modules = (Collection) getValue();
      Iterator iterator = modules.iterator();

      while (iterator.hasNext()) {
         SkeletModule module = (SkeletModule) iterator.next();
         Collection refs = module.getRefs();
         Iterator iteratorRefs = refs.iterator();

         while (iteratorRefs.hasNext()) {
            SkeletModule refModule =
               (SkeletModule) refNames.get((String) iteratorRefs.next());

            ((Collection) module.getValue()).add(refModule);
         }
      }
   }
}
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

import com.finalist.jag.*;


/**
 * Class TagSupport
 *
 *
 * @author Wendel D. de Witte
 * @version %I%, %G%
 */
public abstract class TagSupport implements TagConstants {

   /** Field writer           */
   private JagWriter writer = null;

   /** Field pageContext           */
   private PageContext pageContext = null;


   /**
    * Constructor TagSupport
    *
    *
    */
   public TagSupport() {
      writer = new JagTextBlockWriter();
   }


   /**
    * Method setWriter
    *
    *
    * @param writer
    *
    */
   public void setWriter(JagWriter writer) {
      this.writer = writer;
   }


   /**
    * Method setPageContext
    *
    *
    * @param pageContext
    *
    */
   public void setPageContext(PageContext pageContext) {
      this.pageContext = pageContext;
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
   public abstract int doStartTag() throws JagException;


   /**
    * Method doEndTag
    *
    *
    * @return
    *
    * @throws JagException
    *
    */
   public int doEndTag() throws JagException {
      return (EVAL_PAGE);
   }


   /**
    * Method release
    *
    *
    */
   public void release() {
   }


   /**
    * Method getWriter
    *
    *
    * @return
    *
    */
   public JagWriter getWriter() {
      return writer;
   }


   /**
    * Method getPageContext
    *
    *
    * @return
    *
    */
   public PageContext getPageContext() {
      return pageContext;
   }
}
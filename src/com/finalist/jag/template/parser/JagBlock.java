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

package com.finalist.jag.template.parser;


import java.util.Collection;
import java.util.ArrayList;

import java.awt.Point;


/**
 * Interface JagBlock
 *
 *
 * @author Wendel D. de Witte
 * @version %I%, %G%
 */
public interface JagBlock {

   /**
    * Add a (rightmost) child to this node
    *
    * @param c
    */
   public void addChild(JagBlock c);


   /**
    * Get the first child of this node; null if no children
    *
    * @return
    */
   public JagBlock getFirstChild();


   /**
    * Get the next sibling in line after this one
    *
    * @return
    */
   public JagBlock getNextSibling();


   /**
    * Get the token text for this node
    *
    * @return
    */
   public String getText();


   /**
    * Get the token type for this node
    *
    * @return
    */
   public int getType();


   /**
    * Set the startposition of the token
    *
    * @return
    */
   public int getStartPos();


   /**
    * Set the endposition of the token
    *
    * @return
    */
   public int getEndPos();


   /**
    * Method equals
    *
    *
    * @param t
    *
    * @return
    *
    */
   public boolean equals(JagBlock t);


   /**
    * Method initialize
    *
    *
    * @param t
    * @param txt
    *
    */
   public void initialize(int t, String txt);


   /**
    * Method initialize
    *
    *
    * @param t
    *
    */
   public void initialize(JagBlock t);


   /**
    * Set the first child of a node.
    *
    * @param c
    */
   public void setFirstChild(JagBlock c);


   /**
    * Set the next sibling after this one.
    *
    * @param n
    */
   public void setNextSibling(JagBlock n);


   /**
    * Set the token text for this node
    *
    * @param text
    */
   public void setText(String text);


   /**
    * Set the     token type for this     node
    *
    * @param ttype
    */
   public void setType(int ttype);


   /**
    * Set the startposition of the token
    *
    * @param startPos
    */
   public void setStartPos(int startPos);


   /**
    * Set the endposition of the token
    *
    * @param endPos
    */
   public void setEndPos(int endPos);


   /**
    * Method toString
    *
    *
    * @return
    *
    */
   public String toString();
}
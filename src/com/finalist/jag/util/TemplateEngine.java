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

package com.finalist.jag.util;

import java.util.Collection;

/**
 * A template engine is a component that works from a template file, taking dynamic input from the
 * JagGenerator, to produce a set of output files.
 * <p>
 * <b>NOTE:</b> Implementations of this interface must have a no-args constructor.
 *
 * @author Michael O'Connor
 */
public interface TemplateEngine {

//this interface was introduced as a result of refactoring, so please excuse the poor API! ;)

   /**
    * This method instructs the template engine to process a collection of template files, and
    * as a result to create / update / delete files from the output directory.
    *
    * @param documents a Collection of File objects, of all the templates that you want to be processed.
    * @param outputDir the name of the directory where the destination files are.
    * @return the number of new files created by the process.
    * @throws InterruptedException The implementation should listen periodically for its Thread being
    * interrupted and thrown an InterruptedException accordingly.  This is the mechanism whereby the user
    * can halt the process.
    */
   int process(Collection documents, String outputDir) throws InterruptedException;

   /**
    * Allow specifying overwrite mode.
    * @param overwrite If set to True, the template engine will overwrite the existing files.
    */
   void setOverwrite(Boolean overwrite);
}

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
import com.finalist.jag.taglib.util.RequestUtil;


/**
 * Class FileTag
 *
 *
 * @author Wendel D. de Witte
 * @version %I%, %G%
 */
public class FileTag extends TagBodySupport {

    /** Field path */
    private String path = null;

    /** Field title */
    private String title = null;

    /** Field ext */
    private String ext = null;

    /** Field name           */
    private String name = null;

    /** Field property           */
    private String property = null;

    /** Field counter           */
    protected int counter = 0;


    /**
     * Method setName
     *
     *
     * @param name
     *
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Method setValue
     *
     *
     * @param property
     *
     */
    public void setProperty(String property) {
        this.property = property;
    }

    /**
     * Method getName
     *
     *
     * @return
     *
     */
    public String getName() {
        return (this.name);
    }

    /**
     * Method getValue
     *
     *
     * @return
     *
     */
    public String getProperty() {
        return (this.property);
    }

    /**
     * Method getPath
     *
     *
     * @return
     *
     */
    public String getPath() {
        return (this.path);
    }

    /**
     * Method setPath
     *
     *
     * @param path
     *
     */
    public void setPath(String path) {
        this.path = path;
    }

    /**
     * Method getTitle
     *
     *
     * @return
     *
     */
    public String getTitle() {
        return (this.title);
    }

    /**
     * Method setTitle
     *
     *
     * @param title
     *
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Method getExt
     *
     *
     * @return
     *
     */
    public String getExt() {
        return (this.ext);
    }

    /**
     * Method setExt
     *
     *
     * @param ext
     *
     */
    public void setExt(String ext) {
        this.ext = ext;
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
    public int doStartTag() throws JagException {
        return (EVAL_PAGE);
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
    public int doAfterBodyTag() throws JagException {

        counter++;
		// Retrieve the file title from the tag body.
        if ((title == null) || (title.length() < 1)) {
            if (counter == 1) {
                return (EVAL_BODY_TAG);
            }
            else {
                title = getBodyText();
                title = title.replace('\n',' ');
                title = title.replace('\r',' ');
                title = title.trim();
            }
        }
		// Construct the filepath
        StringBuffer filePath = new StringBuffer();

        if ((path != null) && (path.length() > 0)) {
            filePath.append(path);
        }
        else if(name != null && property != null)
        {
            String value = RequestUtil.lookupString(getPageContext(), name,
                                                    property);
            if (value == null) {
                throw new JagException("WriteTag: Invalid name field >"
                                       + name + "<");
            }

            filePath.append(value);
        }

        filePath.append(title);

        if ((ext != null) && (ext.length() > 0)) {
            filePath.append(".");
            filePath.append(ext);
        }
		// Create a new file entry.
        getWriter().createNewFile(filePath);
        return (SKIP_CLEAR_BODY);
    }
}
;
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
import com.finalist.jag.taglib.util.RequestUtil;

import java.util.*;


/**
 * Class CounterTag
 *
 *
 * @author Wendel D. de Witte
 * @version %I%, %G%
 */
public class CounterTag extends TagBodySupport {

    /** Field name */
    private String name = null;

    /** Field sensitive */
    private String odd = "false";

    /** Field output */
    private String output = null;

    /** Field equal */
    protected boolean body = false;

    /** Field counter */
    protected int counter = 0;

    /////////////////////////////////////

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
     * Method getOdd
     *
     *
     * @return
     *
     */
    public String getOdd() {
        return (this.odd);
    }

    /**
     * Method setOdd
     *
     *
     * @param odd
     *
     */
    public void setOdd(String odd) {
        this.odd = odd;
    }

    /**
     * Method getOutput
     *
     *
     * @return
     *
     */
    public String getOutput() {
        return (this.output);
    }

    /**
     * Method setOutput
     *
     *
     * @param name
     *
     */
    public void setOutput(String output) {
        this.output = output;
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

        SessionContext session = getPageContext().getSessionContext();

        try {
            String s = (String) getPageContext().getAttribute(name);
            int    n = new Integer(s).intValue();

            body = n % 2 == 1;

            if (odd != null && odd.equalsIgnoreCase("false")) {
                body = !body;
            }

            if (output != null && output.equalsIgnoreCase("true")) {
            	getWriter().print(s);
            }
        } catch (Exception e) {
            new JagException(e.getMessage());
        }

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

        return (body && (counter++ < 1))
               ? (EVAL_BODY_TAG)
               : (SKIP_BODY);
    }
}

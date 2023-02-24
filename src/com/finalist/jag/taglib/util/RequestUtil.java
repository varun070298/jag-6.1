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

package com.finalist.jag.taglib.util;

import com.finalist.jag.*;
import com.finalist.jag.skelet.*;

import java.util.*;


/**
 * Class RequestUtil
 *
 *
 * @author Wendel D. de Witte
 * @version %I%, %G%
 */
public class RequestUtil {

    /**
     * Method lookupString
     *
     *
     * @param pageContext
     * @param name
     * @param property
     *
     * @return
     *
     */
    public static String lookupString(PageContext pageContext, String name,
                                      String property)
    {
        Object object = pageContext.getAttribute(name);

        if (object == null) {
            object = pageContext.getSessionContext().getAttribute(name);
        }

        String returnValue = null;

        if ((object != null) && (object instanceof ModuleData))
        {
            ModuleData moduleData = (ModuleData) object;
            if (moduleData.isValueCollection())
            {
                Collection col      = (Collection) moduleData.getValue();
                Iterator   iterator = col.iterator();

                while (iterator.hasNext())
                {
                    moduleData = (ModuleData) iterator.next();
                    if (moduleData != null && moduleData.getName().equals(property)
                            && moduleData.isValueString())
                    {
                        returnValue = (String) moduleData.getValue();
                        break;
                    }
                }
            }
        }
        else if ((object != null) && (object instanceof String))
        {
            returnValue = (String) object;
        }
        else
        {
        	String colProperty = "";
        	String colName = "";
        	StringTokenizer tokens = new StringTokenizer(name, ".");
        	while(tokens.hasMoreTokens())
        	{
        		String token = tokens.nextToken();
        		if(!tokens.hasMoreTokens())
        		{
        			colProperty = token;
        		}
        		else
        		{
        			if(colName.length() > 0) colName += ".";
        			colName += token;
        		}
        	}
			Collection collection = lookupCollection(pageContext,colName,colProperty);
			if(collection != null)
			{
				Iterator iterator = collection.iterator();
				if(iterator.hasNext())
				{
					object = iterator.next();
					String hashcode = ""+object.hashCode();
					pageContext.setAttribute(hashcode, object);
					returnValue = lookupString(pageContext, hashcode, property);
					pageContext.removeAttribute(hashcode);
				}
			}
        }
        return returnValue;
    }

    /**
     * Method lookupCollection
     *
     *
     * @param pageContext
     * @param name
     * @param property
     *
     * @return
     *
     */
    public static Collection lookupCollection(PageContext pageContext,
                                              String name, String property)
    {
        SessionContext session = pageContext.getSessionContext();
        Object         object  = pageContext.getAttribute(name);

        if (object == null) {
            object = session.getAttribute(name);
        }

        Collection returnValue = null;

        if ((object != null) && (object instanceof Collection))
        {
            returnValue = (Collection) object;
        }
        else
        {
            Collection dataModules = null;
            ArrayList  tmpList     = new ArrayList();

            if ((object != null) && (object instanceof ModuleData))
            {
                ModuleData dataModule = (ModuleData) object;

                if (dataModule.isValueCollection()) {
                    dataModules = (Collection) dataModule.getValue();
                }
            }
            else
            {
                SkeletDataObj   skelet = session.getSkelet();
                StringTokenizer tokens = new StringTokenizer(name, ".");

                dataModules = (Collection) skelet.getValue();

                if (name.indexOf(skelet.getName()) == 0) {
                    tokens.nextToken();
                }

                while (tokens.hasMoreTokens())
                {
                    String   label    = tokens.nextToken();
                    Iterator iterator = dataModules.iterator();

                    while (iterator.hasNext())
                    {
                        ModuleData moduleData = (ModuleData) iterator.next();

                        if (moduleData.isValueCollection()
                                && moduleData.getName().equals(label))
                        {
                            if (tokens.hasMoreTokens())
                            {
                                dataModules =(Collection) moduleData.getValue();
                                break;
                            }
                        }
                    }
                    dataModules = null;
                    break;
                }
            }

            if (dataModules != null)
            {
                Iterator iterator = dataModules.iterator();
                while ((property != null) && iterator.hasNext())
                {
                    ModuleData moduleData = (ModuleData) iterator.next();

                    if (moduleData != null && moduleData.isValueCollection()
                            && moduleData.getName().equals(property)) {
                        tmpList.add(moduleData);
                    }
                }
                returnValue = tmpList;
            }
        }
        return returnValue;
    }
}

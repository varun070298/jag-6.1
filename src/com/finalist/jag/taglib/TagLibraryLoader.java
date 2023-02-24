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

import com.finalist.jag.util.Log;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Class TagLibraryLoader
 *
 *
 * @author Wendel D. de Witte
 * @version %I%, %G%
 */
public class TagLibraryLoader {

   /** Field parser           */
   private Document doc = null;

   /** Field exception           */
   private Exception exception = null;


   /**
    * Creates new ObjectModel
    *
    * @param config
    */
   public TagLibraryLoader(File config) {
      Log.log("loading: " + config.getPath());
      DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
      DocumentBuilder builder = null;
      try {
          builder = dbf.newDocumentBuilder();
          doc = builder.parse(config);
      } catch (Exception e) {
          e.printStackTrace();
      }
   }


   /**
    * Method getTagLibrary
    *
    *
    * @return
    *
    */
   public TagLibrary getTagLibrary() {

      if (doc == null)
         return null;
      Element root = doc.getDocumentElement();

      TagLibrary library = parseTagLibrary(root);
      return library;
   }


   /**
    * Method parseTagLibrary
    *
    *
    * @param root
    *
    * @return
    *
    */
   private TagLibrary parseTagLibrary(org.w3c.dom.Element root) {
      TagLibrary tagLibrary = new TagLibrary();

      tagLibrary.setShortName(getAttribute(root, "shortname"));
      tagLibrary.setLibVersion(getAttribute(root, "tlibversion"));
      tagLibrary.setJagVersion(getAttribute(root, "jagversion"));
      tagLibrary.setInfo(getAttribute(root, "info"));
      tagLibrary.setTagDefs(getTagDefs(root, "tag"));

      return tagLibrary;
   }


   /**
    * Method getTagDefs
    *
    *
    * @param root
    * @param label
    *
    * @return
    *
    */
   private Collection getTagDefs(org.w3c.dom.Element root, String label) {
      ArrayList defList = new ArrayList();
      NodeList list = root.getElementsByTagName(label);

      for (int i = 0; i < list.getLength(); i++) {
         org.w3c.dom.Element tagnode = (org.w3c.dom.Element) list.item(i);
         TagDef tagDef = new TagDef();

         tagDef.setName(getAttribute(tagnode, "name"));
         tagDef.setTagClass(getAttribute(tagnode, "tagclass"));
         tagDef.setBodyContent(getAttribute(tagnode, "bodycontent"));
         tagDef.setAttributeDefs(getAttributeDefs(tagnode, "attribute"));
         defList.add(tagDef);
      }

      return defList;
   }


   /**
    * Method getAttributeDefs
    *
    *
    * @param root
    * @param label
    *
    * @return
    *
    */
   private Collection getAttributeDefs(org.w3c.dom.Element root, String label) {
      ArrayList defList = new ArrayList();
      NodeList list = root.getElementsByTagName(label);

      for (int i = 0; i < list.getLength(); i++) {
         org.w3c.dom.Element tagnode = (org.w3c.dom.Element) list.item(i);
         AttributeDef attrDef = new AttributeDef();

         attrDef.setName(getAttribute(tagnode, "name"));
         attrDef.setRequired(getAttribute(tagnode, "required"));
         defList.add(attrDef);
      }

      return defList;
   }


   /**
    * Method getAttribute
    *
    *
    * @param root
    * @param label
    *
    * @return
    *
    */
   private String getAttribute(org.w3c.dom.Element root, String label) {
      String sAttribute = root.getAttribute(label);

      if ((sAttribute == null) || (sAttribute.length() < 1)) {
         NodeList list = root.getElementsByTagName(label);

         if (list.getLength() > 0) {
            org.w3c.dom.Element node = (org.w3c.dom.Element) list.item(0);
            if (node.getFirstChild() != null)
               sAttribute = node.getFirstChild().getNodeValue();
         }
      }
      return sAttribute;
   }


   /**
    * Method getException
    *
    *
    * @return
    *
    */
   public Exception getException() {
      return exception;
   }
}
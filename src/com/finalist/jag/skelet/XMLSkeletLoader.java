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


import com.finalist.jag.util.Log;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Class XMLSkeletLoader
 *
 *
 * @author Wendel D. de Witte
 * @version %I%, %G%
 */
public class XMLSkeletLoader implements SkeletLoader {

   /** Field parser           */
   private Document doc = null;

   /** Field exception           */
   private Exception exception = null;

   /** Field skeletObj           */
   private SkeletDataObj skeletObj = null;


   /**
    * Creates new ObjectModel
    *
    * @param config
    */
   public XMLSkeletLoader(File config) throws Exception {

      Log.log("loading: " + config.getPath());

      InputSource inputSource = new InputSource(
         new InputStreamReader(new FileInputStream(config)));


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
    *
    * @throws JagSkeletException
    */
   public SkeletDataObj getSkeletData() throws JagSkeletException {
      if ((skeletObj == null) && (doc != null)) {
         Element root = doc.getDocumentElement();
         skeletObj = parseSkelet(root);
         skeletObj.processReferences();
      }

      return skeletObj;
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
   private SkeletDataObj parseSkelet(org.w3c.dom.Element root) {
      SkeletDataObj obj = new SkeletDataObj(root.getNodeName());
      Collection rootObj = (Collection) obj.getValue();
      Node node = root.getFirstChild();
      JagSkeletConfig config = getConfig(root);

      while (node != null) {
         if ((node.getNodeType() == node.ELEMENT_NODE)
            && node.getNodeName().equals("module")) {
            String name = getAttribute((Element) node, "name");
            SkeletModule module = new SkeletModule(name);

            module.setRefname(getAttribute((Element) node, "ref-name"));
            module.setRefs(getAttributes((Element) node, "ref"));
            module.setValue(getModuleData((Element) node));
            rootObj.add(module);
         }
         node = node.getNextSibling();
      }
      obj.setConfig(config);
      return obj;
   }


   /**
    * Method getModuleData
    *
    *
    * @param parent
    *
    * @return
    *
    */
   private Collection getModuleData(org.w3c.dom.Element parent) {
      Node node = parent.getFirstChild();
      ArrayList dataModules = new ArrayList();

      while (node != null) {
         if ((node.getNodeType() == node.ELEMENT_NODE)
            && node.getNodeName().equals("module-data")) {
            String name = getAttribute((Element) node, "name");
            if (((Element) node).getElementsByTagName("module-data").getLength() > 0) {
               Collection value = getModuleData((Element) node);
               dataModules.add(new ModuleData(name, value));
            }
            else if (node.hasChildNodes()) {
               String value = node.getFirstChild().getNodeValue();
               dataModules.add(new ModuleData(name, value));
            }
            else {
               dataModules.add(new ModuleData(name, ""));
            }
         }
         node = node.getNextSibling();
      }
      return dataModules;
   }


   private JagSkeletConfig getConfig(org.w3c.dom.Element root) {
      JagSkeletConfig config = new JagSkeletConfig();
      NodeList list = root.getElementsByTagName("config");

      for (int i = 0; i < list.getLength(); i++) {
         org.w3c.dom.Element tagnode = (org.w3c.dom.Element) list.item(i);
         config.setAuthor(getAttribute(tagnode, "author"));
         config.setVersion(getAttribute(tagnode, "version"));
         config.setCompany(getAttribute(tagnode, "company"));
      }

      list = root.getElementsByTagName("templates");
      for (int i = 0; i < list.getLength(); i++) {
         org.w3c.dom.Element node = (org.w3c.dom.Element) list.item(i);
         Node childNode = node.getFirstChild();
         while (childNode != null) {
            if (childNode.getNodeName().equals("template-root") &&
               childNode.hasChildNodes()) {
               config.addTemplateUrl(childNode.getFirstChild().getNodeValue());
            }
            childNode = childNode.getNextSibling();
         }
      }
      return config;
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

            if (node.getFirstChild() != null) {
               sAttribute = node.getFirstChild().getNodeValue();
            }
         }
      }

      return sAttribute;
   }


   /**
    * Method getAttributes
    *
    *
    * @param root
    * @param label
    *
    * @return
    *
    */
   private Collection getAttributes(org.w3c.dom.Element root, String label) {

      ArrayList attrList = new ArrayList();
      NodeList l2 = root.getElementsByTagName(label);

      for (int i = 0; i < l2.getLength(); i++) {
         org.w3c.dom.Element element = (org.w3c.dom.Element) l2.item(i);

         if (element.getFirstChild() != null) {
            attrList.add(element.getFirstChild().getNodeValue());
         }
      }

      return attrList;
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
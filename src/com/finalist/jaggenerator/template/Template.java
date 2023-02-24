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
package com.finalist.jaggenerator.template;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * This class represents a JAG 'generation template'.  A generation template is in fact a directory
 * containing a collection of files and subdirectories.  The template directory must have a "template.xml"
 * configuration file, which contains information about the template and what configuration options are to be
 * presented to the user.
 * 
 * @author Michael O'Connor - Finalist IT Group
 */
public class Template {

   private static final String XMLTAG_JAG_TEMPLATE = "jag-template";
   private static final String TEMPLATE_XML = "template.xml";
   private static final String ID_ATTRIBUTE = "id";
   private static final String NAME_ATTRIBUTE = "name";
   private static final String[] STRING_ARRAY = new String[0];
   private static final TemplateConfigParameter[] TCP_ARRAY = new TemplateConfigParameter[0];
   private static final String XMLTAG_PARAM = "param";
   private static final String DESCRIPTION_ATTRIBUTE = "description";
   private static final String TYPE_ATTRIBUTE = "type";
   private static final String XMLTAG_VALUE = "value";
   private static final String ENGINE_ATTRIBUTE = "template-engine";
   /** The default template engine class. */
   public static final String DEFAULT_ENGINE_CLASS = "com.finalist.jag.util.VelocityTemplateEngine";

   private String name;
   private String description;
   private String engine;
   private File templateDir;
   private Document doc;
   private TemplateConfigParameter[] configParams;


   public Template(File templateDir) throws TemplateConfigException {
      this.templateDir = templateDir;
      load(new File(templateDir, TEMPLATE_XML));
   }

   /**
    * Gets the name of this template.
    * @return the name.
    */
   public String getName() {
      return name;
   }

   /**
    * Gets the base directory of this template.
    * @return the base directory.
    */
   public File getTemplateDir() {
      return templateDir;
   }

    /**
     * Sets the base directory of this template.
     */
    public void setTemplateDir(File  templateDir) {
       this.templateDir = templateDir;
    }

   public String getDescription() {
      return description;
   }

   public String getEngine() {
      return engine;
   }

   public String getEngineClass() {
      return DEFAULT_ENGINE_CLASS;
   }

   /**
    * Gets the configuration paramaters defined for this template.
    * @return the config paramseters.
    */
   public TemplateConfigParameter[] getConfigParams() {
      return configParams;
   }

   public String toString() {
      return name;
   }


   /**
    * Reads in the template information from the XML file.
    */
   private void load(File xmlFile) throws TemplateConfigException {
      DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
      DocumentBuilder builder = null;
      try {
         builder = dbf.newDocumentBuilder();
         doc = builder.parse(xmlFile);
         Element root = (Element) doc.getElementsByTagName(XMLTAG_JAG_TEMPLATE).item(0);
         if (root == null) {
            throw new SAXException("");
         }

         name = root.getAttribute(NAME_ATTRIBUTE);
         description = root.getAttribute(DESCRIPTION_ATTRIBUTE);
         engine = root.getAttribute(ENGINE_ATTRIBUTE);

         //read in all the config params defined in this template..
         ArrayList beans = new ArrayList();
         NodeList params = root.getElementsByTagName(XMLTAG_PARAM);
         for (int i = 0; i < params.getLength(); i++) {
            Element parameter = (Element) params.item(i);
            TemplateConfigParameter bean = new TemplateConfigParameter();
            bean.setId(parameter.getAttribute(ID_ATTRIBUTE));
            bean.setName(parameter.getAttribute(NAME_ATTRIBUTE));
            bean.setDescription(parameter.getAttribute(DESCRIPTION_ATTRIBUTE));
            bean.setType(TemplateConfigParameter.getTypeByName(parameter.getAttribute(TYPE_ATTRIBUTE)));
            ArrayList temp = new ArrayList();
            NodeList presetValues = parameter.getElementsByTagName(XMLTAG_VALUE);
            for (int j = 0; j < presetValues.getLength(); j++) {
               Element value = (Element) presetValues.item(j);
               temp.add(value.getFirstChild().getNodeValue());
            }
            bean.setPresetValues((String[]) temp.toArray(STRING_ARRAY));
            beans.add(bean);
         }
         configParams = (TemplateConfigParameter[]) beans.toArray(TCP_ARRAY);

      } catch (ParserConfigurationException e) {
         throw new TemplateConfigException("System error reading 'template.xml' : " + e);

      } catch (SAXException e) {
         throw new TemplateConfigException("The chosen template's 'template.xml' is invalid.");

      } catch (IOException e) {
         throw new TemplateConfigException("JAG can't locate the template's 'template.xml'.");
      }
   }

}

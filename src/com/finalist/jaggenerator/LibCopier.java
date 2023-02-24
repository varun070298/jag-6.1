/*   Copyright (C) 2004 Finalist IT Group
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


package com.finalist.jaggenerator;

import org.xml.sax.SAXException;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.NamedNodeMap;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.HashMap;
import java.net.URL;

/**
 * Helper class for copying jar files to the generated build file to prevent unnecessary downloads.
 *
 * User: Rudie Ekkelenkamp
 * Date: Apr 16, 2004
 * Time: 9:13:55 PM
 */
public class LibCopier {

    /**
     * Reads the libraries from the JAG generated libXmlFile and copy them if needed
     * from the local lib directory to the generated lib directory.
     * @throws javax.xml.parsers.ParserConfigurationException Indicates a serious configuration error.
     * @throws org.xml.sax.SAXException Encapsulate a general SAX error or warning.
     * @throws java.io.IOException Signals that an I/O exception of some sort has occurred
     * @return a Hashmap with filename/URL pairs as Strings that couldn't be copied .
     *
     */
    public static HashMap copyJars(String libXmlFile, String targetDir) throws ParserConfigurationException, SAXException, IOException {

       File target = new File(targetDir);
       HashMap failedCopies = new HashMap();
       if (!target.exists()) {
           System.out.println("Target directory to copy files to doesn't exist.");
           return null;
       }
       ArrayList libs = new ArrayList();
       DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
       dbf.setValidating(true);
       DocumentBuilder db = dbf.newDocumentBuilder();
       Document doc = db.parse(libXmlFile);
       NodeList nl = doc.getElementsByTagName("lib");
       for (int i = 0; i < nl.getLength(); i++) {
          Node node = nl.item(i);
          NamedNodeMap atts = node.getAttributes();
          String file;
          Node url = atts.getNamedItem("url");
          if (url != null) {
             file = url.getNodeValue();
             try {
                 URL theUrl = new URL(file);
                 String name = theUrl.getFile();
                 if (name.lastIndexOf("/") != -1) {
                     name = name.substring(name.lastIndexOf("/")+1);
                 }
                 libs.add(name);
             } catch (Exception e) {
                 e.printStackTrace();
             }
          }
       }

       for (Iterator iterator = libs.iterator(); iterator.hasNext();) {
           String s = (String) iterator.next();
           File sourceFile = new File(".."+File.separator+"lib"+File.separator+s);
           File targetFile = new File(targetDir+File.separator+s);
           if (sourceFile.exists()) {
              if (!targetFile.exists()) {
                 // Only copy if the target file doesn't exist yet.
                  copy(sourceFile, targetFile);
              } else {
              }
           } else {
               failedCopies.put(s, sourceFile.getCanonicalPath());
           }
       }
       return failedCopies;
    }

    // Copies src file to dst file.
    // If the dst file does not exist, it is created
    private static void copy(File src, File dst) throws IOException {
        InputStream in = new FileInputStream(src);
        OutputStream out = new FileOutputStream(dst);

        // Transfer bytes from in to out
        byte[] buf = new byte[1024];
        int len;
        while ((len = in.read(buf)) > 0) {
            out.write(buf, 0, len);
        }
        in.close();
        out.close();
    }

    public static void main(String[] args) {
        String lib = "lib.xml";
        try {
            copyJars(lib, "c:/temp");
        } catch (ParserConfigurationException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (SAXException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }
}

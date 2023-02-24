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

package com.finalist.jaggenerator.validation;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Represents Struts 1.1 validations for a particular database column, ultimately destined to be
 * included in the 'validation.xml' declarative validations configuration file.
 * <p>
 * This class represents a Struts validation as two parts: a 'depends' list and an XML fragment:
 * the 'depends list' is a comma-seperated list of validation methods (e.g. 'integer', 'date', 'mask'),
 * and the XML fragment contains any parameters needed by those validation methods.
 *
 * @author Michael O'Connor - Finalist IT Group
 */
public class StrutsValidation {

   private String dependsList = "";
   private String xml = "";


   /**
    * Creates a StrutsValidation without generating the validations.
    */
   public StrutsValidation() {
   }

   /**
    *
    * @param sqlType the column type (SQL data type, e.g. NUMBER(12)).
    * @param jdbcType the JDBC data type.
    * @param required whether or not the field is mandatory.
    */
   public StrutsValidation(String sqlType, String jdbcType, boolean required) {
      ArrayList validationsList = new ArrayList(3);
      StringBuffer validations = new StringBuffer();
      List parameters = getParams(sqlType);

      if (required) {
         validationsList.add("required");
      }
      if (sqlType.startsWith("NUMBER") || sqlType.equals("MEDIUMINT")) {
         if ("INTEGER".equals(jdbcType)) {
            validationsList.add("integer");
         } else {
            validationsList.add("float");

            if (parameters.size() == 2) {
               validationsList.add("decimal");
               generateDecimalMaskXML(Integer.parseInt((String) parameters.get(0)),
                     Integer.parseInt((String) parameters.get(1)));
            }
            else if (!parameters.isEmpty()) {
               validationsList.add("maxlength");
               generateMaxlengthXML(Integer.parseInt((String) parameters.get(0)));
            }
         }
      }
      if (sqlType.startsWith("INT")) {
         validationsList.add("integer");
         if (!parameters.isEmpty()) {
               validationsList.add("maxlength");
               generateMaxlengthXML(Integer.parseInt((String) parameters.get(0)));
         }
      }
      if (sqlType.indexOf("CHAR") != -1 && !parameters.isEmpty()) {
         validationsList.add("maxlength");
         generateMaxlengthXML(Integer.parseInt((String) parameters.get(0)));
      }
      if (sqlType.indexOf("DATE") != -1 ) {
         validationsList.add("date");
         generateDateXML();
      }
      if (sqlType.indexOf("BIGINT") != -1) {
         validationsList.add("long");
         if (!parameters.isEmpty()) {
               validationsList.add("maxlength");
               generateMaxlengthXML(Integer.parseInt((String) parameters.get(0)));
         }
      }
      if (sqlType.indexOf("SMALLINT") != -1) {
         validationsList.add("short");
         if (!parameters.isEmpty()) {
               validationsList.add("maxlength");
               generateMaxlengthXML(Integer.parseInt((String) parameters.get(0)));
         }
      }
      if (sqlType.indexOf("TINYINT") != -1) {
         validationsList.add("byte");
         if (!parameters.isEmpty()) {
               validationsList.add("maxlength");
               generateMaxlengthXML(Integer.parseInt((String) parameters.get(0)));
         }
      }
      if (sqlType.indexOf("FLOAT") != -1) {
         validationsList.add("float");
      }

      Iterator v = validationsList.iterator();
      while (v.hasNext()) {
         String item = (String) v.next();
         validations.append(item);
         if (v.hasNext()) {
            validations.append(", ");
         }
      }
      dependsList = validations.toString();

   }


   /** @see {@link #getDependsList()}. */
   public void setDependsList(String dependsList) {
      this.dependsList = dependsList;
   }

   /** @see {@link #getXml()}. */
   public void setXml(String xml) {
      this.xml = xml;
   }

   /**
    * Gets the comma-seperated list of Struts validators to be applied to this field.
    * @return the dependsList.
    */
   public String getDependsList() {
      return dependsList;
   }

   /**
    * Gets the corresponding XML fragment that complements the dependsList.
    * @return the xml fragment.
    */
   public String getXml() {
      return xml;
   }


   private void generateDateXML() {
      StringBuffer temp = new StringBuffer(xml);
      temp.append("<var>\n");
      temp.append("   <var-name>datePattern</var-name>\n");
      temp.append("   <var-value>${date_format}</var-value>\n");
      temp.append("</var>");
      setXml(temp.toString());
   }

   private void generateTimestampXML() {
      StringBuffer temp = new StringBuffer(xml);
      temp.append("<var>\n");
      temp.append("   <var-name>datePattern</var-name>\n");
      temp.append("   <var-value>${timestamp_format}</var-value>\n");
      temp.append("</var>");
      setXml(temp.toString());   }

   private void generateMaxlengthXML(int maxLength) {
      StringBuffer temp = new StringBuffer(xml);
      temp.append("<arg1 key=\"${var:maxlength}\" name=\"maxlength\" resource=\"false\"/>\n");
      temp.append("<var>\n");
      temp.append("   <var-name>maxlength</var-name>\n");
      temp.append("   <var-value>").append(maxLength).append("</var-value>\n");
      temp.append("</var>");
      setXml(temp.toString());
   }

   private void generateDecimalMaskXML(int length, int precision) {
      StringBuffer temp = new StringBuffer(xml);
      temp.append("<arg1 key=\"${var:decimalPrecision}\" name=\"decimal\" resource=\"false\"/>");
      temp.append("<arg2 key=\"${var:decimalLength}\" name=\"decimal\" resource=\"false\"/>");
      temp.append("<var>\n");
      temp.append("   <var-name>decimalLength</var-name>\n");
      temp.append("   <var-value>").append(length).append("</var-value>\n");
      temp.append("</var>");
      temp.append("<var>\n");
      temp.append("   <var-name>decimalPrecision</var-name>\n");
      temp.append("   <var-value>").append(precision).append("</var-value>\n");
      temp.append("</var>");
      setXml(temp.toString());
   }

   public static List getParams(String sqlType) {
      String trimmed = sqlType.trim();
      ArrayList params = new ArrayList();
      int openBracketPos = trimmed.indexOf('(');
      int closeBracketPos = trimmed.indexOf(')');
      if (openBracketPos != -1 && closeBracketPos == (trimmed.length() - 1)) {
         StringTokenizer tokie = new StringTokenizer(trimmed.substring(openBracketPos + 1, closeBracketPos), ",");
         while (tokie.hasMoreTokens()) {
            params.add(tokie.nextToken().trim());
         }
      }

      return params;
   }

}

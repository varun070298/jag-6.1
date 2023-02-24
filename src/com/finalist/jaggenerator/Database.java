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
package com.finalist.jaggenerator;

import com.finalist.jaggenerator.modules.DatabaseManagerFrame;


/**
 * A bean that models a database supported by JAG.
 *
 * @author Michael O'Connor - Finalist IT Group
 */
public class Database {

   public static final String ENTER_DB_NAME = "-- type a name here --";

   private String dbName = ENTER_DB_NAME;
   private String driverClass;
   private String typeMapping = DatabaseManagerFrame.SELECT;
   private String filename;


   /**
    * Gets the human-friendly name of the database.
    * @return
    */
   public String getDbName() {
      return dbName;
   }

   public void setDbName(String dbName) {
      this.dbName = dbName;
   }

   /**
    * Gets the fully-qualified class name of the JDBC driver class used to access this database.
    * @return
    */
   public String getDriverClass() {
      return driverClass;
   }

   public void setDriverClass(String driverClass) {
      this.driverClass = driverClass;
   }

   /**
    * Gets the name of the 'type-mapping' needed by the application server
    * to know how to map this database's proprietary SQL functions and datatypes to Java.
    * @return
    * @todo this property may be application-server specific -maybe better to have a Map of these..?
    */
   public String getTypeMapping() {
      return typeMapping;
   }

   public void setTypeMapping(String typeMapping) {
      this.typeMapping = typeMapping;
   }

   /**
    * Gets the name (not the full file location) of the file containing the JDBC driver for this database.
    * <b>NOTE:</b> all instances of the character '\' (backslash) will be replaced by a forward slash!
    * @return
    */
   public String getFilename() {
      return filename.replace('\\', '/');
   }

   public void setFilename(String filename) {
      this.filename = filename;
   }


   /**
    * Display just the database name (used to represent the database in the select drop down list).
    *
    * @return just the name.
    */
   public String toString() {
      if (dbName != null) {
         return dbName;
      } else {
         return driverClass;
      }
   }


}

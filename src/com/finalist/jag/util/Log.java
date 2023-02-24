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


import com.finalist.jaggenerator.ConsoleLogger;

import java.io.*;


/**
 * Class Log
 *
 * @author Wendel D. de Witte
 */
public class Log {

   /** Field out           */
   private static PrintStream out = System.out;

   private static ConsoleLogger console;

   /**
    * Method log
    *
    * @param s
    */
   public static void log(String s) {
      if (console == null) {
         out.println(s);
      } else {
         console.log(s);
      }
   }

   public static void setLogger(ConsoleLogger logger) {
      console = logger;
   }

   /**
    * Appends an red error log to the end of the console. 
    * 
    * @param message
    *            the log message.
    */
    public static void error(String message) {
        if (console == null) {
            out.println(message);
        } else {
            console.error(message);
        }
    }

    /**
     * Appends an yellow debug log to the end of the console. 
     * 
     * @param message
     *            the log message.
     */
     public static void debug(String message) {
         if (console == null) {
             out.println(message);
         } else {
             console.debug(message);
         }
     }
    
}
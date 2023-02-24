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

package com.finalist.jag.template.parser;

public interface JagParserConstants {
   static int IDENT = 0;
   static int STRING = 1;
   static int INTEGER = 2;
   static int FLOAT = 3;
   static int TEXT = 4;

   static int HEADERDEF_BEGIN = 5;
   static int HEADERDEF_END = 6;
   static int TAGDEF_BEGIN = 7;
   static int TAGDEF_END = 8;
   static int TAGDEF_CLOSE = 9;
   static int ASSIGN = 10;
   static int SEMICOLON = 11;
   static int COLON = 12;

   static int LANGUAGE = 13;
   static int TAGSTART = 14;
   static int TAGEND = 15;
   static int TAGLIB = 16;
   static int URI = 17;
   static int PREFIX = 18;
   static int DEFINE = 19;
   static int PARAMDEF = 20;

   static int TAGNAME = 21;
   static int TAGACTION = 22;
   static int SLIST = 23;

   static int COMMENT_BEGIN = 24;
   static int COMMENT_END = 25;

   static final char EOF_CHAR = (char) -1;

   static String[] tokennames =
      {
         "IDENT", //IDENT 			= 0
         "STRING", //STRING 			= 1
         "INTEGER", //INTEGER 			= 2
         "FLOAT", //FLOAT 			= 3
         "TEXT", //TEXT 				= 4

         "<#@", //HEADERDEF_BEGIN	= 5
         "#>", //HEADERDEF_END		= 6
         "<", //TAGDEF_BEGIN		= 7
         ">", //TAGDEF_END		= 8
         "/", //TAGDEF_CLOSE		= 9
         "=", //ASSIGN			= 10
         ";", //SEMICOLON			= 11
         ":", //COLON				= 12

         "language", //LANGUAGE			= 13
         "tagstart", //TAGSTART			= 14
         "tagend", //TAGEND			= 15
         "taglib", //TAGLIB			= 16
         "uri", //URI				= 17
         "prefix", //PREFIX 			= 18
         "define", //DEFINE 			= 19

         "PARAMDEF", //PARAMDEF			= 20

         "TAGNAME", //TAGNAME			= 21
         "TAGACTION", //TAGACTION			= 22
         "SLIST", //SLIST				= 23

         "!--", //COMMENT_BEGIN		= 24
         "--"		//COMMENT_END		= 25
      };
}

;

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

import javax.swing.*;
import java.util.prefs.Preferences;
import java.awt.*;

/**
 * The Settings class handles the loading and saving of user-settings like the current Window-size,
 * divider-locations, etc.
 *
 * @author Onno Scheffers
 */
public class Settings {
   // Retrieve current user's JAG-preferences
   private static final Preferences PREFS = Preferences.userNodeForPackage(JagGenerator.class);

   private static final String KEY_LAST_SELECTED_OUTPUT_DIR = "last_outputdir";

   // Store Divider positions
   private static final String KEY_VERTICAL_DIVIDER_POSITION = "vdiv";
   private static final int    DEFAULT_VERTICAL_DIVIDER_POSITION = 400;
   private static final String KEY_HORIZONTAL_DIVIDER_POSITION = "hdiv";
   private static final int    DEFAULT_HORIZONTAL_DIVIDER_POSITION = 444;
   // Store Window-position and size
   private static final String KEY_WINDOW_X = "winx";
   private static final int    DEFAULT_WINDOW_X = Integer.MIN_VALUE;
   private static final String KEY_WINDOW_Y = "winy";
   private static final int    DEFAULT_WINDOW_Y = Integer.MIN_VALUE;
   private static final String KEY_WINDOW_WIDTH = "winwidth";
   private static final int    DEFAULT_WINDOW_WIDTH = 1000;
   private static final String KEY_WINDOW_HEIGHT = "winheight";
   private static final int    DEFAULT_WINDOW_HEIGHT = 760;
   // Store window-maximized state
   private static final String KEY_MAXIMIZED = "winmaximized";
   private static final boolean DEFAULT_MAXIMIZED = false;
   // static properties holding the values to read and/or store
   private static int verticalDividerPosition;
   private static int horizontalDividerPosition;
   private static int windowX;
   private static int windowY;
   private static int windowWidth;
   private static int windowHeight;
   private static boolean isMaximized;


   public static String getLastSelectedOutputDir() {
      return lastSelectedOutputDir;
   }


   public static void setLastSelectedOutputDir(String lastSelectedOutputDir) {
      Settings.lastSelectedOutputDir = lastSelectedOutputDir;
   }


   private static String lastSelectedOutputDir;

   /**
    * Static method for reading the user-settings
    */
   public static void read() {
      Settings.verticalDividerPosition = PREFS.getInt(KEY_VERTICAL_DIVIDER_POSITION, DEFAULT_VERTICAL_DIVIDER_POSITION);
      Settings.horizontalDividerPosition = PREFS.getInt(KEY_HORIZONTAL_DIVIDER_POSITION, DEFAULT_HORIZONTAL_DIVIDER_POSITION);
      Settings.windowX = PREFS.getInt(KEY_WINDOW_X, DEFAULT_WINDOW_X);
      Settings.windowY = PREFS.getInt(KEY_WINDOW_Y, DEFAULT_WINDOW_Y);
      Settings.windowWidth = PREFS.getInt(KEY_WINDOW_WIDTH, DEFAULT_WINDOW_WIDTH);
      Settings.windowHeight = PREFS.getInt(KEY_WINDOW_HEIGHT, DEFAULT_WINDOW_HEIGHT);
      Settings.isMaximized = PREFS.getBoolean(KEY_MAXIMIZED, DEFAULT_MAXIMIZED);
      Settings.lastSelectedOutputDir = PREFS.get(KEY_LAST_SELECTED_OUTPUT_DIR, ".");
   }

   /**
    * Static method for writing the user-settings.
    */
   public static void write() {
      PREFS.putInt(KEY_VERTICAL_DIVIDER_POSITION, verticalDividerPosition);
      PREFS.putInt(KEY_HORIZONTAL_DIVIDER_POSITION, horizontalDividerPosition);
      PREFS.putInt(KEY_WINDOW_X, windowX);
      PREFS.putInt(KEY_WINDOW_Y, windowY);
      PREFS.putInt(KEY_WINDOW_WIDTH, windowWidth);
      PREFS.putInt(KEY_WINDOW_HEIGHT, windowHeight);
      PREFS.putBoolean(KEY_MAXIMIZED, isMaximized);
      PREFS.put(KEY_LAST_SELECTED_OUTPUT_DIR, lastSelectedOutputDir);
   }

   /**
    * Returns the last-known vertical divider position.
    *
    * @return the last-known vertical divider position.
    */
   public static int getVerticalDividerPosition() {
      return verticalDividerPosition;
   }

   /**
    * Sets a new value for the vertical divider position that will be stored
    * the next time you call <code>write()</code>.
    *
    * @param verticalDividerPosition The value to store for the vertical divider position.
    */
   public static void setVerticalDividerPosition(int verticalDividerPosition) {
      Settings.verticalDividerPosition = verticalDividerPosition;
   }

   /**
    * Returns the last-known horizontal divider position.
    *
    * @return the last-known horizontal divider position.
    */
   public static int getHorizontalDividerPosition() {
      return horizontalDividerPosition;
   }

   /**
    * Sets a new value for the horizontal divider position that will be stored
    * the next time you call <code>write()</code>.
    *
    * @param horizontalDividerPosition The value to store for the horizontal divider position.
    */
   public static void setHorizontalDividerPosition(int horizontalDividerPosition) {
      Settings.horizontalDividerPosition = horizontalDividerPosition;
   }

   /**
    * Returns the last-known maximized state.
    *
    * @return the last-known maximized state.
    */
   public static boolean isMaximized() {
      return isMaximized;
   }

   /**
    * Sets a new value for the maximized state that will be stored
    * the next time you call <code>write()</code>.
    *
    * @param maximized The value to store for the maximized state.
    */
   public static void setMaximized(boolean maximized) {
      isMaximized = maximized;
   }

   /**
    * Returns the last-known window bounds.
    *
    * @return the last-known window bounds.
    */
   public static Rectangle getUserWindowBounds(JFrame frame) {
      if(windowX == Integer.MIN_VALUE || windowY == Integer.MIN_VALUE) {
         // Center the current window
         Dimension screenSize = frame.getToolkit().getScreenSize();
         windowX = (int) (screenSize.getWidth() /2 - windowWidth /2);
         windowY = (int) (screenSize.getHeight()/2 - windowHeight/2);
      }
      return new Rectangle(windowX, windowY, windowWidth, windowHeight);
   }

   /**
    * Sets a new value for the window bounds that will be stored
    * the next time you call <code>write()</code>.
    *
    * @param bounds The value to store for the window bounds.
    */
   public static void setUserWindowBounds(Rectangle bounds) {
      windowX = (int) bounds.getX();
      windowY = (int) bounds.getY();
      windowWidth = (int) bounds.getWidth();
      windowHeight = (int) bounds.getHeight();
   }
}

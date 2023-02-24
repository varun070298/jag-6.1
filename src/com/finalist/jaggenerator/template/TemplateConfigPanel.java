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

import org.netbeans.lib.awtextra.AbsoluteConstraints;

import javax.swing.*;

import com.finalist.jaggenerator.JagGenerator;

import java.util.HashMap;
import java.util.Map;

/**
 * This class is a JPanel containing configuration settings derived from a particular
 * JAG application generation template.
 * 
 * @author Michael O'Connor - Finalist IT Group
 */
public class TemplateConfigPanel extends JPanel {

   private HashMap configComponents = new HashMap();


   public TemplateConfigPanel(TemplateConfigParameter[] params, String title) {
      super();
      setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

      if (title != null) {
         JLabel titleLabel = new JLabel();
         titleLabel.setText(title);
         add(titleLabel, new AbsoluteConstraints(0, 0, 350, -1));
         titleLabel.setBorder(new javax.swing.border.TitledBorder("Selected template:"));
      }

      for (int i = 0; i < params.length; i++) {
         int y = (i * 25) + 45;
         JLabel jLabel1 = new JLabel();
         jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
         jLabel1.setText(params[i].getName() + ':');
         String description = params[i].getDescription();
         if (description != null) {
            jLabel1.setToolTipText(description);
         }
         add(jLabel1, new AbsoluteConstraints(0, y, 150, -1));

         JComponent component = null;
         if (params[i].getType() == TemplateConfigParameter.TYPE_TEXT) {
            component = new JTextField();
            component.setName(params[i].getId());
         }
         else if (params[i].getType() == TemplateConfigParameter.TYPE_CHECKBOX) {
            component = new JCheckBox();
            component.setName(params[i].getId());

         }
         else if (params[i].getType() == TemplateConfigParameter.TYPE_LIST) {
            component = new JComboBox(params[i].getPresetValues());
            component.setName(params[i].getId());

         }
         else if (params[i].getType() == TemplateConfigParameter.TYPE_EDITABLE_LIST) {
            component = new JComboBox(params[i].getPresetValues());
            component.setName(params[i].getId());
            ((JComboBox) component).setEditable(true);
         }
         else {
            JagGenerator.logToConsole("ERROR: Template's config contains an unknown parameter type.");
            continue;
         }

         if (description != null) {
            component.setToolTipText(description);
         }

         add(component, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, y, 215, -1));
         configComponents.put(params[i].getId(), component);
      }
   }

   /**
    * Gets the mapping of (String) parameter id -> JComponent for all the configurable parameters.
    *
    * @return
    */
   public Map getConfigComponents() {
      return configComponents;
   }

}

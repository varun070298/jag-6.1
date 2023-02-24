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

import com.finalist.jaggenerator.modules.*;

import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.*;
import java.awt.*;

/**
 * A custom TreeCellRenderer that uses a different icons for the various JAG objects.
 *
 * @author Michael O'Connor - Finalist IT Group
 */
public class JagTreeCellRenderer extends DefaultTreeCellRenderer {
   ImageIcon relationIcon;
   ImageIcon entityIcon;
   ImageIcon associationEntityIcon;
   ImageIcon sessionIcon;
   ImageIcon configIcon;
   ImageIcon rootIcon;
   ImageIcon businessMethodIcon;
   ImageIcon fieldIcon;
   ImageIcon pkFieldIcon;

   public JagTreeCellRenderer() {
      relationIcon = new ImageIcon("../images/relation.png");
      entityIcon = new ImageIcon("../images/entity.png");
      associationEntityIcon = new ImageIcon("../images/associationEntity.png");
      sessionIcon = new ImageIcon("../images/session.png");
      configIcon = new ImageIcon("../images/config.png");
      rootIcon = new ImageIcon("../images/root.png");
      businessMethodIcon = new ImageIcon("../images/business.png");
      fieldIcon = new ImageIcon("../images/field.png");
      pkFieldIcon = new ImageIcon("../images/pkfield.png");
   }

   public Component getTreeCellRendererComponent(JTree tree,
                                                 Object value,
                                                 boolean sel,
                                                 boolean expanded,
                                                 boolean leaf,
                                                 int row,
                                                 boolean hasFocus) {
      super.getTreeCellRendererComponent(tree, value, sel,
                                         expanded, leaf, row,
                                         hasFocus);
      super.setIcon(rootIcon);

      if (leaf && value instanceof Relation) {
         setIcon(relationIcon);
      }
       if (leaf && value instanceof BusinessMethod) {
          setIcon(businessMethodIcon);
       }
      else if (value instanceof Entity) {
         if ("true".equals(((Entity) value).getIsAssociationEntity())) {
            setIcon(associationEntityIcon);
         } else {
            setIcon(entityIcon);
         }
      }
      else if (value instanceof Session) {
         setIcon(sessionIcon);
      }
      else if (leaf && value instanceof Config) {
         setIcon(configIcon);
      }
      else if (leaf && value instanceof App) {
         setIcon(configIcon);
      }
      else if (leaf && value instanceof Datasource) {
         setIcon(configIcon);
      }
      else if (leaf && value instanceof Paths) {
         setIcon(configIcon);
      }
      else if (leaf && value instanceof Field) {
         if ( ((Field) value).isPrimaryKey() ) {
            setIcon(pkFieldIcon);
         } else {
            setIcon(fieldIcon);
         }
      }
      return this;
   }

}

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

package com.finalist.jag;


import com.finalist.jag.template.*;
import com.finalist.jag.template.parser.*;
import com.finalist.jag.taglib.*;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.PropertyUtils;



/**
 * Class TagProcess
 *
 *
 * @author Wendel D. de Witte
 * @version %I%, %G%
 */
public class TagProcess implements TagConstants {

   /** Field tagInstance */
   private TagSupport tagInstance = null;

   /** Field tagRef */
   private TemplateTag tagRef = null;

   /** Field process */
   private int process = EVAL_PAGE;

   /** Field bodyText           */
   private String bodyText;


   /**
    * Constructor TagProcess
    *
    *
    * @param tagRef
    *
    */
   public TagProcess(TemplateTag tagRef) {
      this.tagRef = tagRef;
   }


   /**
    * Method process
    *
    *
    * @return
    *
    * @throws JagException
    *
    */
   public int process() throws JagException {

      if (tagInstance == null) {
         try {
            TagDef tagDef = tagRef.getTagDefinition();
            Object object =
               Class.forName(tagDef.getTagClass()).newInstance();

            tagInstance = (TagSupport) object;

            // set support properties
            tagInstance.setPageContext(tagRef.getPageContext());

            // Set tag properties.
            JagParameter[] params = tagRef.getParamArray();

            for (int i = 0; i < params.length; i++) {
               String sIdent = params[i].getIdent();
               String sValue = params[i].getValue();
               Object value = ConvertUtils.convert(sValue, String.class);
               PropertyUtils.setProperty(tagInstance, sIdent, value);
            }

            // Validate required properties.
            AttributeDef[] tagAttributes = tagDef.getAttributeDefArray();

            for (int i = 0; i < tagAttributes.length; i++) {
               if (!tagAttributes[i].getRequired()) {
                  continue;
               }

               String sPropertyName = tagAttributes[i].getName();
               Object value = PropertyUtils.getProperty(tagInstance, sPropertyName);

               if ((value == null) || (value.toString().length() < 1)) {
                  throw new JagException("ERROR: Can't find '" + sPropertyName + "' in " + tagInstance);
               }
            }
         }
         catch (Exception exc) {
            throw new JagException(exc.toString());
         }

         if (tagInstance == null) {
            return getProcess();
         }

         if (!tagRef.isProcessed()) {
            tagRef.setProcessed(true);
            tagRef.clearCloseTag();
            tagRef.clearTag();
         }
      }
      // Reset writer. TagEngine has the option to change the TextBuffer of the tag.
      JagTextBlockWriter writer = new JagTextBlockWriter(tagRef.getTextBuffer());
      tagInstance.setWriter(writer);

      process(tagInstance);

      return getProcess();
   }


   /**
    * Method process
    *
    *
    * @param tagInstance
    *
    * @throws JagException
    *
    */
   public void process(TagSupport tagInstance) throws JagException {

      if (process == EVAL_PAGE) {
         process = tagInstance.doStartTag();

         if (process == SKIP_PAGE) {
            return;
         }
      }

      if ((process != SKIP_BODY && process != SKIP_CLEAR_BODY)
         && (tagInstance instanceof TagBodySupport)) {
         TagBodySupport bodyTag = (TagBodySupport) tagInstance;

         bodyTag.setBodyText(bodyText);

         if (process == EVAL_PAGE) {
            bodyTag.doInitBodyTag();
         }

         process = bodyTag.doAfterBodyTag();

         if (process == EVAL_BODY_TAG) {
            return;
         }

         if (process == SKIP_BODY || process == SKIP_CLEAR_BODY) {
            return;
         }
      }

      process = tagInstance.doEndTag();

      tagInstance.release();
   }


   /**
    * Method getProcess
    *
    *
    * @return
    *
    */
   public int getProcess() {
      return process;
   }


   /**
    * Method setBodyText
    *
    *
    * @param bodyText
    *
    */
   public void setBodyText(String bodyText) {
      this.bodyText = bodyText;
   }
}

;
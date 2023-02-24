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

import java.util.*;

import com.finalist.jag.util.*;
import com.finalist.jag.JagException;
import com.finalist.jag.template.*;
import com.finalist.jag.taglib.*;

/**
 * Class TagEngine
 *
 *
 * @author Wendel D. de Witte
 * @version %I%, %G%
 */
public class TagEngine implements TagConstants {

   /** Field pageContext           */
   private PageContext pageContext = null;

   /** Field processMap           */
   private HashMap processMap = null;

   /** Field libraryMap           */
   private HashMap libraryMap = new HashMap();

   /** Field stackValidatorList           */
   private List stackValidatorList = new LinkedList();

   /** Field bodyContents           */
   private Stack bodyContents = new Stack();

   /** Field templateData           */
   private TemplateStructure templateData = null;

   /** Field currentBody           */
   private String currentBody;


   /**
    * Constructor TagEngine
    *
    *
    */
   public TagEngine() {
   }


   /**
    * Method processTags
    *
    *
    * @param pageContext
    * @param templateData
    *
    */
   public void processTags(PageContext pageContext) {
      this.processMap = new HashMap();
      this.pageContext = pageContext;
      this.templateData = pageContext.getTemplateData();

      processChildTags(templateData.getRoot());
   }


   /**
    * Method processChildTags
    *
    *
    * @param parentNode
    *
    */
   private void processChildTags(TemplateTreeItem parentNode) {
      TreeItem childNode = parentNode.getFirstChild();

      for (; childNode != null; childNode = childNode.getNextSibling()) {
         TemplateTag tagRef = ((TemplateTreeItem) childNode).getTag();
         if ((tagRef == null) || tagRef.isProcessed())
            continue;

         tagRef.setPageContext(pageContext);

         try {
            if (!processTag((TemplateTreeItem) childNode))
               return;
         }
         catch (JagException exc) {
            Log.log(exc.getMessage());
         }
      }
   }


   /**
    * Method processTag
    *
    *
    * @param tagItemNode
    *
    * @return
    *
    * @throws JagException
    *
    */
   public boolean processTag(TemplateTreeItem tagItemNode)
      throws JagException {
      TagProcess tagProcess = getTagProcess(tagItemNode);
      tagProcess.setBodyText(getBodyText(tagItemNode));

      switch (tagProcess.process()) {
         case EVAL_BODY_TAG:
            //  Continue evaluating the body of a tag.
            putBodyContent(tagItemNode);
            processChildTags(tagItemNode);
            processTag(tagItemNode);
            break;
         case SKIP_CLEAR_BODY:
            //  clear the clearBodyText.
            clearBodyText(tagItemNode);
         case SKIP_BODY:
            //  Continue evaluating the tag.
            popBodyContent(tagItemNode);
            processTag(tagItemNode);
            break;
         case EVAL_PAGE:
            //  Continue evaluating the page.
            break;
         case SKIP_PAGE:
            //  Skip the rest of the page.
            return false;
      }
      return true;
   }


   /**
    * Method getTagProcess
    *
    *
    * @param tagref
    *
    * @return
    *
    * @throws JagException
    *
    */
   protected TagProcess getTagProcess(TemplateTreeItem tagItemNode)
      throws JagException {
      TemplateTag tagref = tagItemNode.getTag();
      TagProcess tagProcess = (TagProcess) processMap.get(tagref);

      if (tagProcess == null) {
         PageContext pageContext = tagref.getPageContext();
         TemplateHeaderCollection headers = pageContext.getHeaderCollection();
         UrlHeaderLine urlHeader = headers.getHeaderUrl(tagref.getTagLib());
         TagLibrary tagLibrary = getLibrary(urlHeader.getUrl());
         TagDef tagdef = tagLibrary.getTagDef(tagref);

         tagref.setTagDefinition(tagdef);
         tagProcess = new TagProcess(tagref);
         processMap.put(tagref, tagProcess);
         pushBodyContent(tagItemNode);
      }
      return tagProcess;
   }


   /**
    * Method getBodyText
    *
    *
    * @param tagItemNode
    *
    */
   protected String getBodyText(TemplateTreeItem tagItemNode) {
      TemplateStructure bodyStructure = new TemplateStructure();
      bodyStructure.setRoot(tagItemNode);
      StringBuffer s = bodyStructure.getTextBlockList().getStringBuffer();
      return new String(s);
   }


   /**
    * Method clearBodyText
    *
    *
    * @return
    *
    */
   protected void clearBodyText(TemplateTreeItem parentItem) {
      TemplateStructure bodyStructure = new TemplateStructure();
      bodyStructure.setRoot(parentItem);
      bodyStructure.clearBodyText();
   }


   /**
    * Method getLibrary
    *
    *
    * @param url
    *
    * @return
    *
    * @throws JagException
    *
    */
   protected TagLibrary getLibrary(String url) throws JagException {
      TagLibrary library = (TagLibrary) libraryMap.get(url);
      if (library == null) {
         TagLibraryLoader libLoader = new TagLibraryLoader(new java.io.File(url));
         library = libLoader.getTagLibrary();
         Exception exc = libLoader.getException();

         if (exc != null)
            throw new JagException(exc.toString());
         libraryMap.put(url, library);
      }
      return library;
   }


   /**
    * Method pushBodyContent
    *
    *
    * @param tagItemNode
    *
    */
   protected void pushBodyContent(TemplateTreeItem tagItemNode) {
      TemplateTag tagRef = tagItemNode.getTag();
      TreeItem childNode = tagItemNode.getFirstChild();

      if (childNode == null)
         return;
      if (tagRef.getClosingTextBuffer() == null)
         return;

      TemplateTextBlock closingTextBlock = tagRef.getClosingTextBuffer();
      TemplateTreeItem firstBlock = (TemplateTreeItem) childNode;
      TemplateTreeItem closeBlock = templateData.getTemplateTreeItem(closingTextBlock);
      TemplateTreeItem lastBlock = (TemplateTreeItem) closeBlock.getPrevSibling();
      TemplateStructure bodyStructure = templateData.cut(firstBlock, lastBlock);

      BodyContent tagBody = new BodyContent();
      tagBody.setBodyStructure(bodyStructure);
      bodyContents.push(tagBody);
      stackValidatorList.add(tagItemNode);
   }


   /**
    * Method putBodyContent
    *
    *
    * @param parentItem
    *
    */
   protected void putBodyContent(TemplateTreeItem parentItem) {
      BodyContent bodyContent = peekBodyContent();
      TemplateStructure bodyData = bodyContent.getBodyStructure();
      TemplateTreeItem rootItem = bodyData.getRoot();
      TreeItem childItem = rootItem.getFirstChild();
      TreeItem closingItem = parentItem.disconnectLastChild();
      TemplateTreeItem textItem = new TemplateTreeItem();

      parentItem.addChild(childItem);
      parentItem.addChild(textItem);
      parentItem.addChild(closingItem);

      if (parentItem.getTag() != null)
         parentItem.getTag().setTextBuffer(textItem.getTextBlock());
   }


   /**
    * Method peekBodyContent
    *
    *
    * @return
    *
    */
   protected BodyContent peekBodyContent() {
      BodyContent copyBody = new BodyContent((BodyContent) bodyContents.peek());
      return copyBody;
   }


   /**
    * Method popBodyContent
    *
    *
    * @return
    *
    */
   protected BodyContent popBodyContent(TemplateTreeItem parentItem) {
      if (stackValidatorList.remove(parentItem)) {
         return (BodyContent) bodyContents.pop();
      }
      // prevention against nullpointers;
      return new BodyContent();
   }

}
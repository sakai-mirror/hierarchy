/******************************************************************************
 * HierarchyNode.java - created by aaronz@vt.edu
 * 
 * Copyright (c) 2007 Centre for Academic Research in Educational Technologies
 * Licensed under the Educational Community License version 1.0
 * 
 * A copy of the Educational Community License has been included in this 
 * distribution and is available at: http://www.opensource.org/licenses/ecl1.php
 * 
 *****************************************************************************/

package org.sakaiproject.hierarchy.model;

import java.util.Set;

/**
 * This pea represents a node in a hierarchy 
 * (in academics a department or college would probably be represented by a node)
 * 
 * @author Aaron Zeckoski (aaronz@vt.edu)
 */
public class HierarchyNode {

   /**
    * The unique id for this hierarchy node
    */
   public String id;
   /**
    * The assigned unique id for the hierarchy this node is in
    */
   public String hierarchyId;
   /**
    * the title of this node
    */
   public String title;
   /**
    * the description for this node 
    */
   public String description;
   /**
    * the permissions token for the associated node, 
    * can be looked up in the permissions token key generator service
    */
   public String permToken;
   /**
    * a set of all direct parents for this node,
    * the ids of parent nodes that touch this node directly
    */
   public Set<String> directParentNodeIds;
   /**
    * a set of all direct children for this node,
    * the ids of child nodes that touch this node directly
    */
   public Set<String> directChildNodeIds;
   /**
    * a set of all parents for this node
    */
   public Set<String> parentNodeIds;
   /**
    * a set of all children for this node
    */
   public Set<String> childNodeIds;



   /**
    * Empty constructor
    */
   public HierarchyNode() {}

   /**
    * Testing constructor
    * @param id
    * @param hierarchyId
    * @param title
    * @param permToken
    * @param directParentNodeIds
    * @param directChildNodeIds
    * @param parentNodeIds
    * @param childNodeIds
    */
   public HierarchyNode(String id, String hierarchyId, String title,
         String permToken, Set<String> directParentNodeIds,
         Set<String> parentNodeIds, Set<String> directChildNodeIds,
         Set<String> childNodeIds) {
      this.id = id;
      this.hierarchyId = hierarchyId;
      this.title = title;
      this.permToken = permToken;
      this.directParentNodeIds = directParentNodeIds;
      this.parentNodeIds = parentNodeIds;
      this.directChildNodeIds = directChildNodeIds;
      this.childNodeIds = childNodeIds;
   }


   /*
    * overrides for various internal methods
    */

   @Override
   public boolean equals(Object obj) {
      if (null == obj) return false;
      if (!(obj instanceof HierarchyNode)) return false;
      else {
         HierarchyNode castObj = (HierarchyNode) obj;
         if (null == this.id || null == castObj.id) return false;
         else return (
               this.id.equals(castObj.id) &&
               this.hierarchyId.equals(castObj.hierarchyId)
         );
      }
   }

   @Override
   public int hashCode() {
      if (null == this.id) return super.hashCode();
      String hashStr = this.getClass().getName() + ":" + this.id.hashCode() + ":" + this.hierarchyId.hashCode();
      return hashStr.hashCode();
   }

   @Override
   public String toString() {
      return "id:" + this.id + ";hierachyId:" + this.hierarchyId + ";parents:" + this.parentNodeIds.size() + ";children:" + this.childNodeIds.size();
   }

}

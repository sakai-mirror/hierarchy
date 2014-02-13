/******************************************************************************
 * HierarchyNodeManager.java - created by aaronz on Jul 1, 2007
 * 
 * Copyright (c) 2007 Centre for Academic Research in Educational Technologies
 * Licensed under the Educational Community License version 1.0
 * 
 * A copy of the Educational Community License has been included in this 
 * distribution and is available at: http://www.opensource.org/licenses/ecl1.php
 * 
 *****************************************************************************/

package org.sakaiproject.hierarchy;

import org.sakaiproject.hierarchy.model.HierarchyNode;

/**
 * Allows user to control nodes (create, update, remove)
 * 
 * @author Aaron Zeckoski (aaronz@vt.edu)
 */
public interface HierarchyNodeWriter {

   /**
    * Add a new node to a hierarchy
    * @param hierarchyId a unique id which defines the hierarchy
    * @param parentNodeId the unique id for the parent of this node, can be null if this is the root or a top level node
    * @return the object representing the newly added node
    */
   public HierarchyNode addNode(String hierarchyId, String parentNodeId);

   /**
    * Remove a node from the hierarchy if it is possible,
    * nodes can only be removed if they have no children associations,
    * root nodes can never be removed,
    * exception occurs if these rules are violated
    * @param nodeId a unique id for a hierarchy node
    * @return the object representing the parent of the removed node
    */
   public HierarchyNode removeNode(String nodeId);

   /**
    * Add parents to a node (creates the association),
    * only adds direct parents (directly connected to this node),
    * others are implicitly defined,<br/>
    * this will not create loops in the hierarchy
    * @param nodeId a unique id for a hierarchy node
    * @param parentNodeId a unique id for a hierarchy node which will be a parent of this node
    * @return the object representing the updated node
    */
   public HierarchyNode addParentRelation(String nodeId, String parentNodeId);

   /**
    * Add children to a node (creates the association),
    * only adds direct children (directly connected to this node),
    * others are implicitly defined,<br/>
    * this will not create loops in the hierarchy
    * @param nodeId a unique id for a hierarchy node
    * @param childNodeId a unique id for a hierarchy node which will be a child of this node
    * @return the object representing the updated node
    */
   public HierarchyNode addChildRelation(String nodeId, String childNodeId);

   /**
    * Remove a parent relation from a node,
    * this will not be allowed to orphan a node,
    * only extra parents may be removed, 
    * the last parent for a node cannot currently be removed
    * @param nodeId a unique id for a hierarchy node
    * @param parentNodeId a unique id for a hierarchy node which is a parent of this node
    * @return the object representing the updated node
    */
   public HierarchyNode removeParentRelation(String nodeId, String parentNodeId);

   /**
    * Remove a child relation from a node,
    * this will not be allowed to orphan a node
    * @param nodeId a unique id for a hierarchy node
    * @param childNodeId a unique id for a hierarchy node which is a child of this node
    * @return the object representing the updated node
    */
   public HierarchyNode removeChildRelation(String nodeId, String childNodeId);


   /**
    * Save meta data on a node, this is optional and nodes do not need meta data associated,
    * if the params are nulls then the values remain unchanged, if they are empty string
    * then the values are wiped out
    * @param nodeId a unique id for a hierarchy node
    * @param title the title of the node (optional)
    * @param description a description for this node (optional)
    * @param permKey the permission token key associated with this node (optional)
    * @return the object representing the updated node
    */
   public HierarchyNode saveNodeMetaData(String nodeId, String title, String description, String permKey);

}

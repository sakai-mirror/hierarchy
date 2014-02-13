/**
 * $Id: HierarchyPermissions.java 56046 2008-12-11 19:19:05Z aaronz@vt.edu $
 * $URL: https://source.sakaiproject.org/contrib/hierarchy/tags/hierarchy-1.2.2/api/src/java/org/sakaiproject/hierarchy/HierarchyPermissions.java $
 * HierarchyPermissions.java - hierarchy - Dec 11, 2008 11:03:17 AM - azeckoski
 **************************************************************************
 * Copyright (c) 2008 Aaron Zeckoski
 * Licensed under the Apache License, Version 2.0
 * 
 * A copy of the Apache License has been included in this 
 * distribution and is available at: http://www.apache.org/licenses/LICENSE-2.0.txt
 *
 * Aaron Zeckoski (azeckoski @ gmail.com) (aaronz @ vt.edu) (aaron @ caret.cam.ac.uk)
 */

package org.sakaiproject.hierarchy;

import java.util.Set;

import org.sakaiproject.hierarchy.model.HierarchyNode;

/**
 * This interface contains the methods for assigning and checking permissions in the hierarchy
 * 
 * @author Aaron Zeckoski (azeckoski @ gmail.com)
 */
public interface HierarchyPermissions {

    /**
     * Determine if a user has a specific hierarchy permission at a specific hierarchy node,
     * a permission key can be any string though it will most likely be from a relatively small set
     * 
     * @param userId the internal user id (not username)
     * @param nodeId a unique id for a hierarchy node
     * @param hierarchyPermission a string which indicates a permission key (e.g. delete.item)
     * @return true if the user has this permission, false otherwise
     */
    public boolean checkUserNodePerm(String userId, String nodeId, String hierarchyPermission);

    // ASSIGN

    /**
     * Assign the given permission to a user for the given hierarchy node,
     * can cascade the permission downward if desired
     * 
     * @param userId the internal user id (not username)
     * @param nodeId a unique id for a hierarchy node
     * @param hierarchyPermission a string which indicates a permission key (e.g. delete.item)
     * @param cascade if true then the permission is assigned to all nodes below this one as well,
     * if false it is only assigned to this node
     */
    public void assignUserNodePerm(String userId, String nodeId, String hierarchyPermission, boolean cascade);

    /**
     * Remove a permission for a user from the given hierarchy node,
     * can cascade the permission downward if desired
     * 
     * @param userId the internal user id (not username)
     * @param nodeId a unique id for a hierarchy node
     * @param hierarchyPermission a string which indicates a permission key (e.g. delete.item)
     * @param cascade if true then the permission is removed from all nodes below this one as well,
     * if false it is only removed from this node
     */
    public void removeUserNodePerm(String userId, String nodeId, String hierarchyPermission, boolean cascade);

    // NODES

    /**
     * Get all the userIds for users which have a specific permission in a set of
     * hierarchy nodes, this can be used to check one node or many nodes as needed
     * 
     * @param nodeIds an array of unique ids for hierarchy nodes
     * @param hierarchyPermission a string which indicates a permission key (e.g. delete.item)
     * @return a set of userIds (not username/eid)
     */
    public Set<String> getUserIdsForNodesPerm(String[] nodeIds, String hierarchyPermission);

    /**
     * Get the hierarchy nodes which a user has a specific permission in,
     * this is used to find a set of nodes which a user should be able to see and to build
     * the list of hierarchy nodes a user has a given permission in
     * 
     * @param userId the internal user id (not username)
     * @param hierarchyPermission a string which indicates a permission key (e.g. delete.item)
     * @return a Set of {@link HierarchyNode} objects
     */
    public Set<HierarchyNode> getNodesForUserPerm(String userId, String hierarchyPermission);

    /**
     * Get the set of all permissions which a user has on a node or group of nodes,
     * NOTE: this will get the set of ALL permissions inclusively for the given nodeIds
     * so nodes in the set which a user has no permissions on will not cause this to return no permissions,
     * example: for given user: nodeA(perm1, perm2), nodeB(perm1), nodeC(perm2), nodeD() : returns: (perm1, perm2)
     * 
     * @param userId the internal user id (not username)
     * @param nodeIds an array of unique ids for hierarchy nodes
     * @return the set of permission keys which exist on any of the given nodes
     */
    public Set<String> getPermsForUserNodes(String userId, String[] nodeIds);

}

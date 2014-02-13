/**
 * $Id: HierarchyPermissions.java 56224 2008-12-16 18:04:46Z aaronz@vt.edu $
 * $URL: https://source.sakaiproject.org/contrib/hierarchy/tags/hierarchy-1.2.3/api/src/java/org/sakaiproject/hierarchy/HierarchyPermissions.java $
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

import java.util.Map;
import java.util.Set;

/**
 * This interface contains the methods for assigning and checking permissions in the hierarchy
 * 
 * @author Aaron Zeckoski (azeckoski @ gmail.com)
 */
public interface HierarchyPermissions {

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

    /**
     * Get all the users and permissions currently assigned to nodes,
     * the returned map will always contain every passed in nodeId as a key
     * <br/>
     * This is not super efficient by itself so it should not used when other methods are sufficient,
     * however, it is actually much better than calling the other methods repeatedly so this is primarily
     * for use in administrative interfaces
     * 
     * @param nodeIds an array of unique ids for hierarchy nodes
     * @return the map of nodeId -> (map of userId -> Set(permission))
     */
    public Map<String, Map<String, Set<String>>> getUsersAndPermsForNodes(String... nodeIds);

    /**
     * Get all the nodeIds and permissions for the given userIds,
     * the returned map will always contain every userId that was passed in as a key
     * 
     * @param userIds an array of unique ids for users (internal id, not eid)
     * @return the map of userId -> (map of nodeId -> Set(permission))
     */
    public Map<String, Map<String, Set<String>>> getNodesAndPermsForUser(String... userIds);

}

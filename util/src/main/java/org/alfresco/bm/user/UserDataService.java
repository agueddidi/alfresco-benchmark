package org.alfresco.bm.user;

import java.util.Iterator;
import java.util.List;

/**
 * Service providing access to {@link UserData} storage. All {@link UserData} returned from and persisted
 * with this service will be testrun-specific. The testrun-identifier is set in the constructor.
 *
 * @author Frederik Heremans
 * @author Derek Hulley
 * @author steveglover
 * @since 1.1
 */
public interface UserDataService
{
    /**
     * The {@link UserData#getDomain() domain} given to users who belong to the default domain
     */
    public static final String DEFAULT_DOMAIN = "default";
    
    public interface UserCallback
    {
        boolean callback(UserData user);
    };
    
    public void createNewUser(UserData data);

    /**
     * Store an authentication token (ticket) against a username
     */
    public void setUserTicket(String username, String ticket);
    
    /**
     * Update a user's password
     */
    public void setUserPassword(String username, String password);

    /**
     * Store a node ID associated with the username
     */
    public void setUserNodeId(String username, String nodeId);

    /**
     * Change the 'created' state of the user i.e. whether the user exists on the server or not
     */
    public void setUserCreated(String username, boolean created);
    
    /**
     * @param created               <tt>true</tt> to only count users present in Alfresco
     */
    public long countUsers(boolean created);

    /**
     * @param domain                the domain to search
     * @param created               <tt>true</tt> to only count users present in Alfresco
     */
    public long countUsers(String domain, boolean created);

    /**
     * @return                      a count of all users in any state
     */
    public long countUsers();
    
    /**
     * Find a user by username
     * 
     * @return                          the {@link UserData} found otherwise <tt>null</tt>.
     */
    public UserData findUserByUsername(String username);
    
    /**
     * Find a user by email address
     * 
     * @return                          the {@link UserData} found otherwise <tt>null</tt>.
     */
    public UserData findUserByEmail(String email);
    
    /**
     * Get a list of usernames that are NOT created in alfresco with paging
     * 
     * @param startIndex index to start getting users from  
     * @param count number of users to fetch
     * @return      List of user data, which may be empty or less than the required count
     */
    public List<UserData> getUsersPendingCreation(int startIndex, int count);

    /**
     * Get a list of usernames that are created in alfresco with paging
     * 
     * @param startIndex    index to start getting users from  
     * @param count         number of users to fetch
     * @return              List of user data, which may be empty or less than the required count
     */
    public List<UserData> getCreatedUsers(int startIndex, int count);
    
    /**
     * Select a random, pre-created user.
     * <p/>
     * Note that this is useful only for large numbers of users.
     * 
     * @return      a random user or <tt>null</tt> if none are available
     */
    public UserData getRandomUser();

    /*
     * USER DOMAIN SERVICES
     */

    /**
     * Access users by their user domain using paging
     * 
     * @param domain                    the user domain
     * @param startIndex                the start index for paging
     * @param count                     the number of users to retrieve
     * 
     * @return a list of users in the user domain
     * 
     * @see #DEFAULT_DOMAIN
     */
    public List<UserData> getUsersInDomain(String domain, int startIndex, int count);

    /**
     * Return a maximum of "max" users in the network with id "networkId" and given created flag.
     * 
     * @param domain                    the user domain
     * @param startIndex                the start index for paging
     * @param count                     the number of users to retrieve
     * @param created                    is the user created or not?
     * 
     * @return a list of users in the domain
     * 
     * @see #DEFAULT_DOMAIN
     */
    public List<UserData> getUsersInDomain(String domain, int startIndex, int count, boolean created);
    
    /**
     * An iterator over networks in the users collection.
     * 
     * @return an iterator over networks
     * 
     * @see #DEFAULT_DOMAIN
     */
    public Iterator<String> getDomainsIterator();
    
    /**
     * Select a random, pre-created user.
     * <p/>
     * Note that this is useful only for large numbers of users.
     * 
     * @param       domain the user domain
     * @return      a random user or <tt>null</tt> if none are available
     * 
     * @see #DEFAULT_DOMAIN
     */
    public UserData getRandomUserFromDomain(String domain);

    /**
     * Select a random, pre-created user that is a member of one of the given domains.
     * <p/>
     * Note that this is useful only for large numbers of users.
     * 
     * @param       domain the user domain
     * @return      a random user or <tt>null</tt> if none are available
     * 
     * @see #DEFAULT_DOMAIN
     */
    UserData getRandomUserFromDomains(List<String> domains);
    
    /**
     * Iterate over users in the given network.
     * 
     * @param       domain the user domain
     * @return      an iterator over users in the given domain.
     * 
     * @see #DEFAULT_DOMAIN
     */
    public Iterator<UserData> getUsersByDomainIterator(String domain);
}
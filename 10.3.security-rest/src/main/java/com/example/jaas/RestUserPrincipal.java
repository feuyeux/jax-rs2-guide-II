package com.example.jaas;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.apache.catalina.Group;
import org.apache.catalina.Role;
import org.apache.catalina.User;
import org.apache.catalina.UserDatabase;

public class RestUserPrincipal implements User {
    private String userName;
    private final Set<Role> roles = new HashSet<>();

    public RestUserPrincipal(final String userName) {
        this.userName = userName;
    }

    @Override
    public String getName() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void addGroup(final Group arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void addRole(final Role role) {
        roles.add(role);
    }

    @Override
    public String getFullName() {
        return userName;
    }

    @Override
    public Iterator<Group> getGroups() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String getPassword() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Iterator<Role> getRoles() {
        return roles.iterator();
    }

    @Override
    public UserDatabase getUserDatabase() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public boolean isInGroup(final Group arg0) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean isInRole(final Role role) {
        if (!roles.isEmpty()) {
            for (final Role role0 : roles) {
                if (role0.getName() != null && role0.getName().equals(role.getName())) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public void removeGroup(final Group arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void removeGroups() {
        // TODO Auto-generated method stub

    }

    @Override
    public void removeRole(final Role arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void removeRoles() {
        roles.clear();
    }

    @Override
    public void setFullName(final String userName) {
        setUsername(userName);
    }

    @Override
    public void setPassword(final String arg0) {
        // TODO Auto-generated method stub
    }

    @Override
    public void setUsername(final String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return userName + "<" + roles.size();
    }
}

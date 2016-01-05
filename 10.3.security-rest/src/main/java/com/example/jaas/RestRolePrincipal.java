package com.example.jaas;

import java.io.Serializable;

import org.apache.catalina.Role;
import org.apache.catalina.UserDatabase;

public class RestRolePrincipal implements Role, Serializable {
    private static final long serialVersionUID = 1L;
    private String roleName;

    public RestRolePrincipal(final String roleName) {
        this.roleName = roleName;
    }

    @Override
    public String getName() {
        // TODO Auto-generated method stub
        return getRolename();
    }

    @Override
    public String getDescription() {
        // TODO Auto-generated method stub
        return " some role";
    }

    @Override
    public String getRolename() {
        // TODO Auto-generated method stub
        return roleName;
    }

    @Override
    public UserDatabase getUserDatabase() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void setDescription(final String arg0) {

    }

    @Override
    public void setRolename(final String roleName) {
        this.roleName = roleName;
    }
    
    @Override
    public String toString() {
        return roleName;
    }
}

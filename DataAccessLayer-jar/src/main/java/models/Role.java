/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Gabriel_Liberato
 */
@Entity
@Table(name = "ROLES")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "Role.findAll", query = "SELECT r FROM Role r")
    , @NamedQuery(name = "Role.findByRoleId", query = "SELECT r FROM Role r WHERE r.roleId = :roleId")
    , @NamedQuery(name = "Role.findByRoleName", query = "SELECT r FROM Role r WHERE r.roleName = :roleName")
    , @NamedQuery(name = "Role.findByRoleCreationDate", query = "SELECT r FROM Role r WHERE r.roleCreationDate = :roleCreationDate")
})
public class Role implements Serializable
{
    private static final long serialVersionUID = 1L;
    
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "ROLE_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int roleId;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 60)
    @Column(name = "ROLE_NAME", unique = true)
    private String roleName;
    
    @Column(name = "ROLE_CREATION_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date roleCreationDate;
    
    @Size(min = 1, max = 200)
    @Column(name = "ROLE_DESCRIPTION")
    private String roleDescription;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "role")
    private List<GroupRole> groupsRoles;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "role")
    private List<RolePermission> rolesPermissions;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "role")
    private List<UserRole> usersRoles;

    public Role()
    {
    }

    public Role(int roleId)
    {
        this.roleId = roleId;
    }

    public Role(int roleId, String roleName)
    {
        this.roleId = roleId;
        this.roleName = roleName;
    }

    public int getRoleId()
    {
        return roleId;
    }

    public void setRoleId(int roleId)
    {
        this.roleId = roleId;
    }

    public String getRoleName()
    {
        return roleName;
    }

    public void setRoleName(String roleName)
    {
        this.roleName = roleName.toUpperCase();
    }

    public Date getRoleCreationDate()
    {
        return roleCreationDate;
    }

    public void setRoleCreationDate(Date roleCreationDate)
    {
        this.roleCreationDate = roleCreationDate;
    }
    
    public String getRoleDescription()
    {
        return roleDescription;
    }

    public void setRoleDescription(String roleDescription)
    {
        this.roleDescription = roleDescription;
    }

    @XmlTransient
    public List<GroupRole> getGroupsRoles()
    {
        return groupsRoles;
    }

    public void setGroupsRoles(List<GroupRole> groupsRoles)
    {
        this.groupsRoles = groupsRoles;
    }
    
    @XmlTransient
    public List<RolePermission> getRolesPermissions()
    {
        return rolesPermissions;
    }

    public void setRolesPermissions(List<RolePermission> rolesPermissions)
    {
        this.rolesPermissions = rolesPermissions;
    }
    
    @XmlTransient
    public List<UserRole> getUsersRoles()
    {
        return usersRoles;
    }

    public void setUsersRoles(List<UserRole> usersRoles)
    {
        this.usersRoles = usersRoles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role = (Role) o;
        return roleId == role.roleId &&
                Objects.equals(roleName, role.roleName) &&
                Objects.equals(roleCreationDate, role.roleCreationDate) &&
                Objects.equals(roleDescription, role.roleDescription);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roleId, roleName, roleCreationDate, roleDescription);
    }

    @Override
    public String toString()
    {
        return "Role{" + "roleId=" + roleId + ", roleName=" + roleName + ", roleCreationDate=" + roleCreationDate + ", roleDescription=" + roleDescription + '}';
    }
}
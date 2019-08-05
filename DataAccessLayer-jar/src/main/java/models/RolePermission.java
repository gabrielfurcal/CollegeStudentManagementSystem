/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Gabriel_Liberato
 */
@Entity
@Table(name = "ROLES_PERMISSIONS")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "RolePermission.findAll", query = "SELECT r FROM RolePermission r")
    , @NamedQuery(name = "RolePermission.findByRolePermissionId", query = "SELECT r FROM RolePermission r WHERE r.rolePermissionId = :rolePermissionId")
})
public class RolePermission implements Serializable
{
    private static final long serialVersionUID = 1L;
    
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ROLE_PERMISSION_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int rolePermissionId;
    
    @JoinColumn(name = "PERMISSION_ID", referencedColumnName = "PERMISSION_ID")
    @ManyToOne(optional = false)
    private Permission permission;
    
    @JoinColumn(name = "ROLE_ID", referencedColumnName = "ROLE_ID")
    @ManyToOne(optional = false)
    private Role role;

    public RolePermission()
    {
    }

    public RolePermission(int rolePermissionId)
    {
        this.rolePermissionId = rolePermissionId;
    }

    public RolePermission(int rolePermissionId, String roleName, String permissionName)
    {
        this.rolePermissionId = rolePermissionId;
    }

    public int getRolePermissionId()
    {
        return rolePermissionId;
    }

    public void setRolePermissionId(int rolePermissionId)
    {
        this.rolePermissionId = rolePermissionId;
    }

    public Permission getPermission()
    {
        return permission;
    }

    public void setPermission(Permission permission)
    {
        this.permission = permission;
    }

    public Role getRole()
    {
        return role;
    }

    public void setRole(Role role)
    {
        this.role = role;
    }

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        RolePermission that = (RolePermission) o;
//        return rolePermissionId == that.rolePermissionId &&
//                Objects.equals(permission, that.permission) &&
//                Objects.equals(role, that.role);
//    }

    @Override
    public int hashCode() {
        return Objects.hash(rolePermissionId, permission, role);
    }

    @Override
    public String toString()
    {
        return "RolePermission{" + "rolePermissionId=" + rolePermissionId + "}";
    }
}
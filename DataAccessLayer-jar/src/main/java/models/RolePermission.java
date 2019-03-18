/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
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
    @SequenceGenerator(name="ROLES_PERMISSIONS_SEQUENCE", sequenceName = "ROLES_PERMISSIONS_SEQUENCE", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ROLES_PERMISSIONS_SEQUENCE")
    private BigDecimal rolePermissionId;
    
    @JoinColumn(name = "PERMISSION_ID", referencedColumnName = "PERMISSION_ID")
    @ManyToOne(optional = false)
    private Permission permission;
    
    @JoinColumn(name = "ROLE_ID", referencedColumnName = "ROLE_ID")
    @ManyToOne(optional = false)
    private Role role;

    public RolePermission()
    {
    }

    public RolePermission(BigDecimal rolePermissionId)
    {
        this.rolePermissionId = rolePermissionId;
    }

    public RolePermission(BigDecimal rolePermissionId, String roleName, String permissionName)
    {
        this.rolePermissionId = rolePermissionId;
    }

    public BigDecimal getRolePermissionId()
    {
        return rolePermissionId;
    }

    public void setRolePermissionId(BigDecimal rolePermissionId)
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
    
    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (rolePermissionId != null ? rolePermissionId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RolePermission))
        {
            return false;
        }
        RolePermission other = (RolePermission) object;
        if ((this.rolePermissionId == null && other.rolePermissionId != null) || (this.rolePermissionId != null && !this.rolePermissionId.equals(other.rolePermissionId)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "RolePermission{" + "rolePermissionId=" + rolePermissionId + ", permission=" + permission + ", role=" + role + '}';
    }
}
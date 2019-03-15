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
@Table(name = "PERMISSIONS")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "Permission.findAll", query = "SELECT p FROM Permission p")
    , @NamedQuery(name = "Permission.findByPermissionId", query = "SELECT p FROM Permission p WHERE p.permissionId = :permissionId")
    , @NamedQuery(name = "Permission.findByPermissionName", query = "SELECT p FROM Permission p WHERE p.permissionName = :permissionName")
    , @NamedQuery(name = "Permission.findByPermissionCreationDate", query = "SELECT p FROM Permission p WHERE p.permissionCreationDate = :permissionCreationDate")
})
public class Permission implements Serializable
{
    private static final long serialVersionUID = 1L;
    
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "PERMISSION_ID")
    @SequenceGenerator(name="PERMISSIONS_SEQUENCE", sequenceName = "PERMISSIONS_SEQUENCE", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PERMISSIONS_SEQUENCE")
    private BigDecimal permissionId;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 60)
    @Column(name = "PERMISSION_NAME")
    private String permissionName;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "PERMISSION_CREATION_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date permissionCreationDate;
    
    @Size(min = 1, max = 200)
    @Column(name = "PERMISSION_DESCRIPTION")
    private String permissionDescription;
    
    @Size(max = 1000)
    @Column(name = "PERMISSION_URL")
    private String permissionUrl;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "permission")
    private List<RolePermission> rolesPermissions;

    public Permission()
    {
    }

    public Permission(BigDecimal permissionId)
    {
        this.permissionId = permissionId;
    }

    public Permission(BigDecimal permissionId, String permissionName, Date permissionCreationDate)
    {
        this.permissionId = permissionId;
        this.permissionName = permissionName;
        this.permissionCreationDate = permissionCreationDate;
    }

    public BigDecimal getPermissionId()
    {
        return permissionId;
    }

    public void setPermissionId(BigDecimal permissionId)
    {
        this.permissionId = permissionId;
    }

    public String getPermissionName()
    {
        return permissionName;
    }

    public void setPermissionName(String permissionName)
    {
        this.permissionName = permissionName.toUpperCase();
    }

    public Date getPermissionCreationDate()
    {
        return permissionCreationDate;
    }

    public void setPermissionCreationDate(Date permissionCreationDate)
    {
        this.permissionCreationDate = permissionCreationDate;
    }
    
    public String getPermissionDescription()
    {
        return permissionDescription;
    }

    public void setPermissionDescription(String permissionDescription)
    {
        this.permissionDescription = permissionDescription;
    }

    public String getPermissionUrl()
    {
        return permissionUrl;
    }

    public void setPermissionUrl(String permissionUrl)
    {
        this.permissionUrl = permissionUrl;
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

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (permissionId != null ? permissionId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Permission))
        {
            return false;
        }
        Permission other = (Permission) object;
        if ((this.permissionId == null && other.permissionId != null) || (this.permissionId != null && !this.permissionId.equals(other.permissionId)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "Permission{" + "permissionId=" + permissionId + ", permissionName=" + permissionName + ", permissionCreationDate=" + permissionCreationDate + ", permissionDescription=" + permissionDescription + ", permissionUrl=" + permissionUrl + ", rolesPermissions=" + rolesPermissions + '}';
    }
}
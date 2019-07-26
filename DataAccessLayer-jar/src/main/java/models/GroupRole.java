/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;
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
@Table(name = "GROUPS_ROLES")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "GroupRole.findAll", query = "SELECT g FROM GroupRole g")
    , @NamedQuery(name = "GroupRole.findByGroupRoleId", query = "SELECT g FROM GroupRole g WHERE g.groupRoleId = :groupRoleId")
})
public class GroupRole implements Serializable
{
    private static final long serialVersionUID = 1L;
    
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "GROUP_ROLE_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int groupRoleId;
    
    @JoinColumn(name = "GROUP_ID", referencedColumnName = "GROUP_ID")
    @ManyToOne(optional = false)
    private Group group;
    
    @JoinColumn(name = "ROLE_ID", referencedColumnName = "ROLE_ID")
    @ManyToOne(optional = false)
    private Role role;
    
    public GroupRole()
    {
    }

    public GroupRole(int groupRoleId)
    {
        this.groupRoleId = groupRoleId;
    }

    public GroupRole(int groupRoleId, String groupName, String roleName)
    {
        this.groupRoleId = groupRoleId;
    }

    public int getGroupRoleId()
    {
        return groupRoleId;
    }

    public void setGroupRoleId(int groupRoleId)
    {
        this.groupRoleId = groupRoleId;
    }

    public Group getGroup()
    {
        return group;
    }

    public void setGroup(Group group)
    {
        this.group = group;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GroupRole groupRole = (GroupRole) o;
        return groupRoleId == groupRole.groupRoleId &&
                Objects.equals(group, groupRole.group) &&
                Objects.equals(role, groupRole.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(groupRoleId, group, role);
    }

    @Override
    public String toString()
    {
        return "GroupRole{" + "groupRoleId=" + groupRoleId + ", group=" + group + ", role=" + role + '}';
    }
}
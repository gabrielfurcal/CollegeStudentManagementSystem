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
@Table(name = "POSITIONS")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "Position.findAll", query = "SELECT p FROM Position p")
    , @NamedQuery(name = "Position.findByPositionId", query = "SELECT p FROM Position p WHERE p.positionId = :positionId")
    , @NamedQuery(name = "Position.findByPositionName", query = "SELECT p FROM Position p WHERE p.positionName = :positionName")
    , @NamedQuery(name = "Position.findByPositionDescription", query = "SELECT p FROM Position p WHERE p.positionDescription = :positionDescription")
    , @NamedQuery(name = "Position.findByPositionCreationDate", query = "SELECT p FROM Position p WHERE p.positionCreationDate = :positionCreationDate")
})
public class Position implements Serializable
{
    private static final long serialVersionUID = 1L;
    
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "POSITION_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int positionId;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 60)
    @Column(name = "POSITION_NAME")
    private String positionName;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "POSITION_DESCRIPTION")
    private String positionDescription;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "POSITION_CREATION_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date positionCreationDate;
    
    @OneToMany(mappedBy = "position")
    private List<User> users;

    public Position()
    {
    }

    public Position(int positionId)
    {
        this.positionId = positionId;
    }

    public Position(int positionId, String positionName, String positionDescription, Date positionCreationDate)
    {
        this.positionId = positionId;
        this.positionName = positionName;
        this.positionDescription = positionDescription;
        this.positionCreationDate = positionCreationDate;
    }

    public int getPositionId()
    {
        return positionId;
    }

    public void setPositionId(int positionId)
    {
        this.positionId = positionId;
    }

    public String getPositionName()
    {
        return positionName;
    }

    public void setPositionName(String positionName)
    {
        this.positionName = positionName.toUpperCase();
    }

    public String getPositionDescription()
    {
        return positionDescription;
    }

    public void setPositionDescription(String positionDescription)
    {
        this.positionDescription = positionDescription;
    }

    public Date getPositionCreationDate()
    {
        return positionCreationDate;
    }

    public void setPositionCreationDate(Date positionCreationDate)
    {
        this.positionCreationDate = positionCreationDate;
    }

    @XmlTransient
    public List<User> getUsers()
    {
        return users;
    }

    public void setUsers(List<User> users)
    {
        this.users = users;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return positionId == position.positionId &&
                Objects.equals(positionName, position.positionName) &&
                Objects.equals(positionDescription, position.positionDescription) &&
                Objects.equals(positionCreationDate, position.positionCreationDate) &&
                Objects.equals(users, position.users);
    }

    @Override
    public int hashCode() {
        return Objects.hash(positionId, positionName, positionDescription, positionCreationDate, users);
    }

    @Override
    public String toString()
    {
        return "Position{" + "positionId=" + positionId + ", positionName=" + positionName + ", positionDescription=" + positionDescription + ", positionCreationDate=" + positionCreationDate + ", users=" + users + '}';
    }
}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Gabriel_Liberato
 */
@Entity
@Table(name = "EMAILS")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "Email.findAll", query = "SELECT e FROM Email e")
    , @NamedQuery(name = "Email.findByEmailId", query = "SELECT e FROM Email e WHERE e.emailId = :emailId")
    , @NamedQuery(name = "Email.findByEmailText", query = "SELECT e FROM Email e WHERE e.emailText = :emailText")
    , @NamedQuery(name = "Email.findByEmailCreationDate", query = "SELECT e FROM Email e WHERE e.emailCreationDate = :emailCreationDate")
})
public class Email implements Serializable
{
    private static final long serialVersionUID = 1L;
    
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @SequenceGenerator(name="EMAILS_SEQUENCE", sequenceName = "EMAILS_SEQUENCE", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "EMAILS_SEQUENCE")
    @Column(name = "EMAIL_ID")
    private BigDecimal emailId;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 70)
    @Column(name = "EMAIL_TEXT")
    private String emailText;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "EMAIL_CREATION_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date emailCreationDate;
    
    @JoinColumn(name = "USER_ID", referencedColumnName = "USER_ID")
    @ManyToOne(optional = false)
    private User user;

    public Email()
    {
    }

    public Email(BigDecimal emailId)
    {
        this.emailId = emailId;
    }

    public Email(BigDecimal emailId, String emailText, Date emailCreationDate)
    {
        this.emailId = emailId;
        this.emailText = emailText;
        this.emailCreationDate = emailCreationDate;
    }

    public BigDecimal getEmailId()
    {
        return emailId;
    }

    public void setEmailId(BigDecimal emailId)
    {
        this.emailId = emailId;
    }

    public String getEmailText()
    {
        return emailText;
    }

    public void setEmailText(String emailText)
    {
        this.emailText = emailText;
    }

    public Date getEmailCreationDate()
    {
        return emailCreationDate;
    }

    public void setEmailCreationDate(Date emailCreationDate)
    {
        this.emailCreationDate = emailCreationDate;
    }

    public User getUser()
    {
        return user;
    }

    public void setUser(User user)
    {
        this.user = user;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (emailId != null ? emailId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Email))
        {
            return false;
        }
        Email other = (Email) object;
        if ((this.emailId == null && other.emailId != null) || (this.emailId != null && !this.emailId.equals(other.emailId)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "Email{" + "emailId=" + emailId + ", emailText=" + emailText + ", emailCreationDate=" + emailCreationDate + ", user=" + user + '}';
    }
}
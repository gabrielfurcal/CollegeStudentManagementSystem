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
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Gabriel_Liberato
 */
@Entity
@Table(name = "PERIODS")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "Period.findAll", query = "SELECT p FROM Period p")
    , @NamedQuery(name = "Period.findByPeriodYear", query = "SELECT p FROM Period p WHERE p.periodsPK.periodYear = :periodYear")
    , @NamedQuery(name = "Period.findByPeriodQuarter", query = "SELECT p FROM Period p WHERE p.periodsPK.periodQuarter = :periodQuarter")
    , @NamedQuery(name = "Period.findByPeriodCreationDate", query = "SELECT p FROM Period p WHERE p.periodCreationDate = :periodCreationDate")
})
public class Period implements Serializable
{
    private static final long serialVersionUID = 1L;
    
    @EmbeddedId
    protected PeriodPK periodsPK;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "PERIOD_CREATION_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date periodCreationDate;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "period")
    private List<CourseSectionHistorical> coursesSectionsHistorical;

    public Period()
    {
    }

    public Period(PeriodPK periodsPK)
    {
        this.periodsPK = periodsPK;
    }

    public Period(PeriodPK periodsPK, Date periodCreationDate)
    {
        this.periodsPK = periodsPK;
        this.periodCreationDate = periodCreationDate;
    }

    public Period(int periodYear, int periodMonth)
    {
        this.periodsPK = new PeriodPK(periodYear, periodMonth);
    }

    public PeriodPK getPeriodsPK()
    {
        return periodsPK;
    }

    public void setPeriodsPK(PeriodPK periodsPK)
    {
        this.periodsPK = periodsPK;
    }

    public Date getPeriodCreationDate()
    {
        return periodCreationDate;
    }

    public void setPeriodCreationDate(Date periodCreationDate)
    {
        this.periodCreationDate = periodCreationDate;
    }

    @XmlTransient
    public List<CourseSectionHistorical> getCoursesSectionsHistorical()
    {
        return coursesSectionsHistorical;
    }

    public void setCoursesSectionsHistoricalList(List<CourseSectionHistorical> coursesSectionsHistorical)
    {
        this.coursesSectionsHistorical = coursesSectionsHistorical;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (periodsPK != null ? periodsPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Period))
        {
            return false;
        }
        Period other = (Period) object;
        if ((this.periodsPK == null && other.periodsPK != null) || (this.periodsPK != null && !this.periodsPK.equals(other.periodsPK)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "Period{" + "periodsPK=" + periodsPK + ", periodCreationDate=" + periodCreationDate + ", coursesSectionsHistorical=" + coursesSectionsHistorical + '}';
    }
}
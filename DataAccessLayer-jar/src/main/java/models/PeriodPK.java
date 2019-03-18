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
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Gabriel_Liberato
 */
@Embeddable
public class PeriodPK implements Serializable
{
    @Basic(optional = false)
    @NotNull
    @Column(name = "PERIOD_YEAR")
    private BigDecimal periodYear;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "PERIOD_QUARTER")
    private BigDecimal periodQuarter;

    public PeriodPK()
    {
    }

    public PeriodPK(BigDecimal periodYear, BigDecimal periodQuarter)
    {
        this.periodYear = periodYear;
        this.periodQuarter = periodQuarter;
    }

    public BigDecimal getPeriodYear()
    {
        return periodYear;
    }

    public void setPeriodYear(BigDecimal periodYear)
    {
        this.periodYear = periodYear;
    }

    public BigDecimal getPeriodQuarter()
    {
        return periodQuarter;
    }

    public void setPeriodQuarter(BigDecimal periodQuarter)
    {
        this.periodQuarter = periodQuarter;
    }
    
    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (periodYear != null ? periodYear.hashCode() : 0);
        hash += (periodQuarter != null ? periodQuarter.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PeriodPK))
        {
            return false;
        }
        PeriodPK other = (PeriodPK) object;
        if ((this.periodYear == null && other.periodYear != null) || (this.periodYear != null && !this.periodYear.equals(other.periodYear)))
        {
            return false;
        }
        if ((this.periodQuarter == null && other.periodQuarter != null) || (this.periodQuarter != null && !this.periodQuarter.equals(other.periodQuarter)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "PeriodPK{" + "periodYear=" + periodYear + ", periodQuarter=" + periodQuarter + '}';
    }
}
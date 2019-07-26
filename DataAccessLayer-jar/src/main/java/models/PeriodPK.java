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
    private int periodYear;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "PERIOD_QUARTER")
    private int periodQuarter;

    public PeriodPK()
    {
    }

    public PeriodPK(int periodYear, int periodQuarter)
    {
        this.periodYear = periodYear;
        this.periodQuarter = periodQuarter;
    }

    public int getPeriodYear()
    {
        return periodYear;
    }

    public void setPeriodYear(int periodYear)
    {
        this.periodYear = periodYear;
    }

    public int getPeriodQuarter()
    {
        return periodQuarter;
    }

    public void setPeriodQuarter(int periodQuarter)
    {
        this.periodQuarter = periodQuarter;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PeriodPK periodPK = (PeriodPK) o;
        return periodYear == periodPK.periodYear &&
                periodQuarter == periodPK.periodQuarter;
    }

    @Override
    public int hashCode() {
        return Objects.hash(periodYear, periodQuarter);
    }

    @Override
    public String toString()
    {
        return "PeriodPK{" + "periodYear=" + periodYear + ", periodQuarter=" + periodQuarter + '}';
    }
}
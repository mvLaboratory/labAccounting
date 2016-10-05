package com.mvLab.account.reports;

import com.mvLab.account.catalogs.reagents.ReagentCatalog;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

@Entity
public class ReagentUsageReport implements Serializable, Report {
    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "date")
    private Date date;

    @ManyToOne
    @JoinColumn(name="reagent")
    private ReagentCatalog reagent;

    @Column(name = "usage")
    private double usage;

    public ReagentUsageReport() {
    }

    public ReagentUsageReport(Date date, ReagentCatalog reagent, double usage) {
        this.date = date;
        this.reagent = reagent;
        this.usage = usage;
    }

    public ReagentCatalog getReagent() {
        return reagent;
    }

    public void setReagent(ReagentCatalog reagent) {
        this.reagent = reagent;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getUsage() {
        return usage;
    }

    public void setUsage(double usage) {
        this.usage = usage;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Map<ReagentCatalog, List<ReagentUsageReport>> getUsageTree(List<ReagentUsageReport> reportData) {
        Map<ReagentCatalog, List<ReagentUsageReport>>treeMap = new HashMap<>();
        Set<ReagentCatalog> reagentsSet = reportData.stream().map(ReagentUsageReport::getReagent).collect(Collectors.toSet());

        for (ReagentCatalog reagent : reagentsSet) {
            for (ReagentUsageReport reportDataRecord : reportData) {
                if (reagent.equals(reportDataRecord.getReagent())) {
                    if (!treeMap.containsKey(reagent)) {
                        List<ReagentUsageReport> reportList = new ArrayList<>();
                        reportList.add(reportDataRecord);
                        treeMap.put(reagent, reportList);
                    }
                    else
                    {
                        List<ReagentUsageReport> reportList = treeMap.get(reagent);
                        reportList.add(reportDataRecord);
                        treeMap.put(reagent, reportList);
                    }
                }
            }
        }

        return treeMap;
    }

    public String getQueryString() {
        String queryString = "Select";
        queryString = queryString + " max(recordID) as id, usage.date as date, usage.reagent as reagent, Sum(usage.quantity) as usage";
        queryString = queryString + " from REAGENT_USAGE usage";
        queryString = queryString + " group by usage.reagent, usage.date";
        queryString = queryString + " ORDER by usage.reagent, usage.date";

        return queryString;
    }
}
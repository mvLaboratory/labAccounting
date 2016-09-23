package com.mvLab.lab.account.register;

import com.mvLab.lab.account.DB_Manager;
import com.mvLab.lab.account.documents.Document;

import java.util.HashSet;
import java.util.Set;

public class RecordSet {
    Set<Register> recordSet = new HashSet<>();
    Document registrator;

    public RecordSet(Register ... recordSet) {

        for (Register record : recordSet)
            this.recordSet.add(record);
    }

    public RecordSet(Document registrator, Register ... recordSet) {
        this.registrator = registrator;
        for (Register record : recordSet)
            this.recordSet.add(record);
    }

    public void add(Register record) {
        recordSet.add(record);
    }

    public void remove(Register record) {
        recordSet.remove(record);
    }

    public Set<Register> getRecordSet() {
        return recordSet;
    }

    public void setRecordSet(Set<Register> recordSet) {
        this.recordSet = recordSet;
    }

    public Document getRegistrator() {
        return registrator;
    }

    public void setRegistrator(Document registrator) {
        this.registrator = registrator;
    }

    public void save() {
        DB_Manager.getInstance().deleteDocPosts(registrator);
        DB_Manager.getInstance().saveRecordSet(this);
    }
}
package com.mvLab.lab.accaunt.documents;

import java.util.Date;
import java.util.HashMap;
import java.util.UUID;

public class Document {
    private String number;
    private Date date;
    private UUID uuid;

    public static HashMap getElements() {
        return new HashMap();
    }
}

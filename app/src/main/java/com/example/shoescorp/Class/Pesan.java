package com.example.shoescorp.Class;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

public class Pesan {
    private String NamePesan;
    private String Size;

    public Pesan(String namePesan, String size){
        this.NamePesan = namePesan;
        this.Size = size;
    }

    public String getNamePesan() {
        return NamePesan;
    }

    public void setNamePesan(String namePesan) {
        NamePesan = namePesan;
    }

    public String getSize() {
        return Size;
    }

    public void setSize(String size) {
        Size = size;
    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("Name", NamePesan);
        result.put("Size", Size);
        return result;
    }
}

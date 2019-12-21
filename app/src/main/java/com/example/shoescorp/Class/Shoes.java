package com.example.shoescorp.Class;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

public class Shoes {

    public String Name;
    public String Image;
    public String Price;


    public Shoes(){

    }

    public Shoes(String name, String image, String price) {
        this.Name = name;
        this.Image = image;
        this.Price = price;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("Name", Name);
        result.put("Image", Image);
        result.put("Price", Price);
        return result;
    }
}

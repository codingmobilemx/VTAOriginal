package com.vta.codingmobile.vtamovil.Model.Clases;

import android.media.Image;

import java.util.Date;
import java.util.UUID;

public class Product {
    private UUID id;
    private String title;
    //private Date created;
    private String price;
    private String description;
    private Images images;

    public Images getImages() {
        return images;
    }

    public void setImages(Images images) {
        this.images = images;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    /*   public Date getCreated() {
           return created;
       }

       public void setCreated(Date created) {
           this.created = created;
       }
   */
    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}

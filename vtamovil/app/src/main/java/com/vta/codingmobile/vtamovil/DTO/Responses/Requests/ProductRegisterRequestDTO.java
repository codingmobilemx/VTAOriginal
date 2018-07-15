package com.vta.codingmobile.vtamovil.DTO.Responses.Requests;

import com.vta.codingmobile.vtamovil.Model.Clases.Product;

public class ProductRegisterRequestDTO extends Product {
    private Byte[] imageArray;

    public Byte[] getImageArray() {
        return imageArray;
    }

    public void setImageArray(Byte[] imageArray) {
        this.imageArray = imageArray;
    }
}

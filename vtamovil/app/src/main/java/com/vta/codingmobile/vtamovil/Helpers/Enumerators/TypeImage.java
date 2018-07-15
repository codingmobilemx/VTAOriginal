package com.vta.codingmobile.vtamovil.Helpers.Enumerators;

public enum TypeImage {

    BANNER(1, "Banner"),
    SMALL(2, "Small");
    private int id;
    private String name;

    TypeImage(int id, String name) {
        this.id = id;
        this.name = name;
    }


    public String getName() {
        return name;
    }


    public int getId() {
        return id;
    }
}

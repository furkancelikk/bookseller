package com.furkan.celik.bookseller.enums;

/**
 * @author furkancelik
 **/
public enum Gender {

    WOMAN("Woman"),
    MAN("Man");
    private final String description;

    Gender(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}

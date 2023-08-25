package com.maxim.coupons.enums;

public enum CategoryType {
    CELLPHONES("CELLPHONES"),
    CLOTHING("CLOTHING"),
    ELECTRONICS("ELECTRONICS"),
    JEWELRY("JEWELRY"),
    WATCHES("WATCHES"),
    HOME("HOME"),
    TOYS("TOYS"),
    SPORTS("SPORTS"),
    HEALTH("HEALTH"),
    FOOD("FOOD"),
    TOOLS("TOOLS");

    private String categoryName;

    private CategoryType(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryName() {
        return this.categoryName;
    }
}



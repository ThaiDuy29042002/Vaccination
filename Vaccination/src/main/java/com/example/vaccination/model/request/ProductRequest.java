package com.example.vaccination.model.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
public class ProductRequest implements Serializable {

    // product
    private int productId;

    @NotEmpty(message = "Name is required!")
    private String name;

    private String image;

    private String description;

    @PositiveOrZero(message = "Price is required!")
    private double price;

    private boolean status;

    // category
    @Positive(message = "Category is required!")
    private int categoryId;

}

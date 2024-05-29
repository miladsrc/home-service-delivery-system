package domain;


import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;


@Embeddable
public class Address {
    @Column(name = "street_address")
    private String streetAddress;

    @Column(name = "house_number")
    private String houseNumber;

    @Column(name = "zip_code")
    private String zipCode;
}
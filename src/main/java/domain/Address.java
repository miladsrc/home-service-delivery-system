package domain;


import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;


@Embeddable
public class Address {
    @Column
    private String streetAddress;

    @Column
    private String houseNumber;

    @Column
    private String zipCode;
}
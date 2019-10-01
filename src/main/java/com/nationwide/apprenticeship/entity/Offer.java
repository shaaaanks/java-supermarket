package com.nationwide.apprenticeship.entity;

import javax.persistence.*;

@Entity
@Table(name = "offers")
public class Offer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long offerId;
    private String offerDescription;
    private String shortDescription;
    private boolean bogof;
    private boolean tftpot;
    private Integer tftpotGroup;
    private boolean discount;
    private Integer discountPercentage;

    public Offer() {}

    public Offer(long offerId, String offerDescription, String shortDescription, boolean bogof, boolean tftpot, int tftpotGroup, boolean discount, int discountPercentage) {
        this.offerId = offerId;
        this.offerDescription = offerDescription;
        this.shortDescription = shortDescription;
        this.bogof = bogof;
        this.tftpot = tftpot;
        this.tftpotGroup = tftpotGroup;
        this.discount = discount;
        this.discountPercentage = discountPercentage;
    }

    public long getOfferId() {
        return offerId;
    }

    public String getOfferDescription() {
        return offerDescription;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public boolean isBogof() {
        return bogof;
    }

    public boolean isTftpot() {
        return tftpot;
    }

    public int getTftpotGroup() {
        return tftpotGroup;
    }

    public boolean isDiscount() {
        return discount;
    }

    public int getDiscountPercentage() {
        return discountPercentage;
    }
}

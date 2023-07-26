package com.example.zbiletemreplika.app.bt.buyTickets;

import java.util.ArrayList;


import java.util.ArrayList;

public class TicketsModel {

    private String ticketId;
    private String headline;
    private Double price;
    private String discountType;
    private String ticketTypeInformation;
    private ArrayList<String> applicableLines;

    // Konstruktor bezargumentowy, wymagany przez Firebase Realtime Database
    public TicketsModel() {
        // Pamiętaj, żeby nie pozostawiać go pustym, jeśli masz jakieś pola, które wymagają inicjalizacji.
    }

    public TicketsModel(String ticketId, String headline, Double price, String discountType, String ticketTypeInformation, ArrayList<String> applicableLines) {
        this.ticketId = ticketId;
        this.headline = headline;
        this.price = price;
        this.discountType = discountType;
        this.ticketTypeInformation = ticketTypeInformation;
        this.applicableLines = applicableLines;
    }

    // Gettery i settery dla pól
    public String getTicketId() {
        return ticketId;
    }

    public void setTicketId(String ticketId) {
        this.ticketId = ticketId;
    }

    public String getHeadline() {
        return headline;
    }

    public void setHeadline(String headline) {
        this.headline = headline;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDiscountType() {
        return discountType;
    }

    public void setDiscountType(String discountType) {
        this.discountType = discountType;
    }

    public String getTicketTypeInformation() {
        return ticketTypeInformation;
    }

    public void setTicketTypeInformation(String ticketTypeInformation) {
        this.ticketTypeInformation = ticketTypeInformation;
    }

    public ArrayList<String> getApplicableLines() {
        return applicableLines;
    }

    public void setApplicableLines(ArrayList<String> applicableLines) {
        this.applicableLines = applicableLines;
    }
}

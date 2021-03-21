package com.example.eventish.model;

public class Event {
    String id;
    String name;
    String link;
    String image;
    String date;
    String time;
    int price;
    String venue;
    String city;
    String gerne;
    String subgerne;

    public String getGerne() {
        return gerne;
    }

    public void setGerne(String gerne) {
        this.gerne = gerne;
    }

    public String getSubgerne() {
        return subgerne;
    }

    public void setSubgerne(String subgerne) {
        this.subgerne = subgerne;
    }

    public Event(){}

    public Event(String name, String link, String image, String date, String time, int price) {
        this.name = name;
        this.link = link;
        this.image = image;
        this.date = date;
        this.time = time;
        this.price = price;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String description) {
        this.link = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}

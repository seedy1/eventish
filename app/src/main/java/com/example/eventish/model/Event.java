package com.example.eventish.model;

public class Event {
    String id;
    String name;
    String description;
    String image;
    String date;
    String time;
    int price;

    public Event(){}

    public Event(String name, String description, String image, String date, String time, int price) {
        this.name = name;
        this.description = description;
        this.image = image;
        this.date = date;
        this.time = time;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

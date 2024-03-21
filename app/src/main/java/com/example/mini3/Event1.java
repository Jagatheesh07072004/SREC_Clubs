package com.example.mini3;



public class Event1
{
    private String eventName;
    private String clubName;
    private String eventDate;
    private String eventTime;
    private String eventLocation;
    private String eventDescription;
    private String regLink;
    private String resLink;
    private String imageUrl;

    public Event1() {
        // Default constructor required for Firebase
    }

    public Event1(String eventName, String clubName, String eventDate, String eventTime, String eventLocation, String eventDescription, String regLink, String resLink, String imageUrl) {
        this.eventName = eventName;
        this.clubName = clubName;
        this.eventDate = eventDate;
        this.eventTime = eventTime;
        this.eventLocation = eventLocation;
        this.eventDescription = eventDescription;
        this.regLink = regLink;
        this.resLink = resLink;
        this.imageUrl = imageUrl;
    }

    public String getEventName() {
        return eventName;
    }

    public String getClubName() {
        return clubName;
    }

    public String getEventDate() {
        return eventDate;
    }

    public String getEventTime() {
        return eventTime;
    }

    public String getEventLocation() {
        return eventLocation;
    }

    public String getEventDescription() {
        return eventDescription;
    }

    public String getRegLink() {
        return regLink;
    }

    public String getResLink() {
        return resLink;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}


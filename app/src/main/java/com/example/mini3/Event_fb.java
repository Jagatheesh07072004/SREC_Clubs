package com.example.mini3;

public class Event_fb
{
    public String id;
    public String eventName;
    public String clubName;
    public String eventDate;
    public String eventTime;
    public String eventLocation;
    public String eventDescription;
    public String regLink;
    public String resLink;
    public String imageUrl;

    public Event_fb() {
        // Default constructor required for calls to DataSnapshot.getValue(Event.class)
    }

    public Event_fb(String id, String eventName, String clubName, String eventDate, String eventTime, String eventLocation, String eventDescription, String regLink, String resLink, String imageUrl) {
        this.id = id;
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
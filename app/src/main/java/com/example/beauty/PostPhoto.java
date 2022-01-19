package com.example.beauty;

public class PostPhoto {

    public String description;
    public String uriPhoto;

    public PostPhoto(String Description, String UriPhoto) {
        description = Description;
        uriPhoto = UriPhoto;
    }

    public PostPhoto() {
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUriPhoto() {
        return uriPhoto;
    }

    public void setUriPhoto(String uriPhoto) {
        this.uriPhoto = uriPhoto;
    }

}

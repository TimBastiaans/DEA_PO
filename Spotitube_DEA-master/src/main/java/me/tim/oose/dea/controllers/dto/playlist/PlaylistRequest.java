package me.tim.oose.dea.controllers.dto.playlist;

import me.tim.oose.dea.controllers.dto.Dto;

public class PlaylistRequest implements Dto {

    private int id;
    private String name;
    private boolean owner;
    private int[] tracks;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isOwner(boolean owner) {
        return this.owner;
    }

    public void setOwner(boolean owner) {
        this.owner = owner;
    }

    public int[] getTracks() {
        return tracks;
    }

    public void setTracks(int[] tracks) {
        this.tracks = tracks;
    }

}

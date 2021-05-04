package me.tim.oose.dea.mo;



public class Playlist {

    private int id;
    private String name;
    private boolean owner;
    private int[] tracks;
    private int length;

    public Playlist(){

    }

    public Playlist(int id, String name,int length , boolean owner ) {
        this.id = id;
        this.name = name;
        this.owner = owner;
        this.length = length;
        this.tracks = null;
    }

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

    public boolean isOwner() {
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

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }
}

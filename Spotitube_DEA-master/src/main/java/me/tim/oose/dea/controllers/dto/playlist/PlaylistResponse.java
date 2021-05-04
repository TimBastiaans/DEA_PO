package me.tim.oose.dea.controllers.dto.playlist;

import me.tim.oose.dea.mo.Playlist;
import me.tim.oose.dea.controllers.dto.Dto;

import java.util.List;

public class PlaylistResponse implements Dto {

    private List<Playlist> playlists;
    private int length;

    public PlaylistResponse(List<Playlist> playlists, int length) {
        this.playlists = playlists;
        this.length = length;
    }

    public List<Playlist> getPlaylists() {
        return playlists;
    }
    public int getLength() { return length; }
    public void setPlaylists(List<Playlist> playlists) {
        this.playlists = playlists;
    }
}

package me.tim.oose.dea.controllers.dto.track;

import me.tim.oose.dea.mo.Track;
import me.tim.oose.dea.controllers.dto.Dto;

import java.util.List;

public class TrackResponse implements Dto {

    private List<Track> tracks;

    public TrackResponse(List<Track> tracks) {
        this.tracks = tracks;
    }

    public List<Track> getTracks() {
        return tracks;
    }

}

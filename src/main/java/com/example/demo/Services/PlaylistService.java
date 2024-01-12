package com.example.demo.Services;

import java.util.List;

import com.example.demo.entities.Playlist;

public interface PlaylistService {

	public void addPlaylist(Playlist playlist);

	public List<Playlist> fetchAllPlaylists();

}
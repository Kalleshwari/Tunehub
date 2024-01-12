package com.example.demo.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Repository.PlaylistRepository;
import com.example.demo.entities.Playlist;

@Service
public class PlaylistServiceImplementation implements PlaylistService{
	
	@Autowired
	PlaylistRepository repo;

	@Override
	public void addPlaylist(Playlist playlist) {
		repo.save(playlist);
		
	}

	@Override
	public List<Playlist> fetchAllPlaylists() {
			return repo.findAll();
	}

}

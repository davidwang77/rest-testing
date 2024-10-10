package com.dwang.app.rest.service;

import com.dwang.app.rest.models.Song;
import com.dwang.app.rest.repo.SongRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SongService {
    @Autowired
    private SongRepo songRepo;

    public List<Song> getSongs(){
        return songRepo.findAll();
    }

    public Song saveSong(Song song){
        return songRepo.save(song);
    }
}

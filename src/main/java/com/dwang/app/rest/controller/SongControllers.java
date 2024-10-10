package com.dwang.app.rest.controller;

import com.dwang.app.rest.models.Song;
import com.dwang.app.rest.models.User;
import com.dwang.app.rest.service.SongService;
import com.dwang.app.rest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SongControllers {
    @Autowired
    private SongService songService;

    @GetMapping("song/users")
    public List<Song> getSongs(){
        return songService.getSongs();
    }

    @PostMapping("song/save")
    public ResponseEntity<Song> saveSong(@RequestBody Song song){
        Song newSong = songService.saveSong(song);
        return new ResponseEntity<>(newSong, HttpStatus.CREATED);
    }
}

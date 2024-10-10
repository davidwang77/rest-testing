package com.dwang.app.rest.repo;

import com.dwang.app.rest.models.Song;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SongRepo extends JpaRepository<Song, Long> {
}

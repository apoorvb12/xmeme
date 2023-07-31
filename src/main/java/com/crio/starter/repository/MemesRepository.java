package com.crio.starter.repository;

import java.util.List;
import com.crio.starter.data.MemesEntity;
import com.crio.starter.exchange.MemesResponseDto;
import org.springframework.http.ResponseEntity;

public interface MemesRepository {
    
    List<MemesEntity> getMemes();

    ResponseEntity<MemesEntity> getMeme(long id);

    ResponseEntity<MemesResponseDto> saveMeme(MemesEntity meme);
}

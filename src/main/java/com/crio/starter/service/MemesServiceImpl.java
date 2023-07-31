package com.crio.starter.service;

import java.util.List;
import com.crio.starter.data.MemesEntity;
import com.crio.starter.exchange.MemesResponseDto;
import com.crio.starter.repository.MemesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class MemesServiceImpl implements MemesService {

    @Autowired
    private MemesRepository memeRepository;

    @Override
    public List<MemesEntity> getMemes() {
        // TODO Auto-generated method stub
        return memeRepository.getMemes();
    }

    @Override
    public ResponseEntity<MemesEntity> getMeme(long id) {
        // TODO Auto-generated method stub
        return memeRepository.getMeme(id);
    }

    @Override
    public ResponseEntity<MemesResponseDto> saveMeme(MemesEntity meme) {
        // TODO Auto-generated method stub
        return memeRepository.saveMeme(meme);
    }
    
}

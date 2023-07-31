package com.crio.starter.controller;

import java.util.ArrayList;
import java.util.List;
import com.crio.starter.data.MemesEntity;
import com.crio.starter.exchange.MemesResponseDto;
import com.crio.starter.service.MemesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping
@RestController
public class MemesController {
       
    @Autowired
    private MemesService memeService;

    @GetMapping("/memes")
    public List<MemesEntity> getMemes(){
        return memeService.getMemes();
    }

    @GetMapping("/memes/{id}")
    public ResponseEntity<MemesEntity> getMeme(@PathVariable("id") long id) {
        return memeService.getMeme(id);
    }

    @PostMapping("/memes")
    public ResponseEntity<MemesResponseDto> saveMeme(@RequestBody MemesEntity memeEntity){
        return memeService.saveMeme(memeEntity);
    }
}

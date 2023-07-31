package com.crio.starter.repository;

import lombok.extern.log4j.Log4j2;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import com.crio.starter.data.MemesEntity;
import com.crio.starter.exchange.MemesResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

@Repository
public class MemesRepositoryImpl implements MemesRepository{

    private static long count = 0;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public List<MemesEntity> getMemes() {
        // TODO Auto-generated method stub
        
        List<MemesEntity> list = mongoTemplate.findAll(MemesEntity.class, "memes");
        Collections.reverse(list);
        if(list.size()>100){
            list = list.stream().limit(100).collect(Collectors.toList());
        }
        return list;
    }

    @Override
    public ResponseEntity<MemesEntity> getMeme(long id) {
        // TODO Auto-generated method stub
        Query query = new Query(Criteria.where("id").is(id));
        MemesEntity meme = mongoTemplate.findOne(query, MemesEntity.class, "memes");
        if(meme==null){
            return ResponseEntity.badRequest().body(meme);
        }
        return ResponseEntity.ok().body(meme);
    }

    @Override
    public ResponseEntity<MemesResponseDto> saveMeme(MemesEntity meme){
        // TODO Auto-generated method stub
        MemesResponseDto memesResponseDto = new MemesResponseDto();
        if(!validateMeme(meme)){
            return ResponseEntity.badRequest().body(memesResponseDto);
        }
        if(isDuplicateMeme(meme)){
            return ResponseEntity.badRequest().body(memesResponseDto);
        }
        count++;
        System.out.println(count);
        meme.setId(count);
        MemesEntity savedMeme = mongoTemplate.save(meme, "memes");
        memesResponseDto.setId(savedMeme.getId());
        return ResponseEntity.ok().body(memesResponseDto);
    }

    private boolean validateMeme(MemesEntity meme){
        if(meme.getName()==null || meme.getUrl()==null || meme.getCaption()==null){
            return false;
        }
        if(meme.getName().isBlank() || meme.getUrl().isBlank() || meme.getCaption().isBlank()){
            return false;
        }
        return true;
    }

    private boolean isDuplicateMeme(MemesEntity meme){
        String name = meme.getName();
        String url = meme.getUrl();
        String caption = meme.getCaption();
        Query query = new Query(Criteria.where("name").is(name).and("url").is(url).and("caption").is(caption));
        List<MemesEntity> list = mongoTemplate.find(query, MemesEntity.class, "memes");
        if(list.size()>0){
            return true;
        }
        return false;
    }

}

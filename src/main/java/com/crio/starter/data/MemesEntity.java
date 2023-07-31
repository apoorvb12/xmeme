package com.crio.starter.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "memes")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemesEntity{

    @Id
    private long id;

    @NonNull
    private String name;

    @NonNull
    private String url;

    @NonNull
    private String caption;
    
}

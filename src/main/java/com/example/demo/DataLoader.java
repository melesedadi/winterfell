package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
public class DataLoader implements CommandLineRunner {
    @Autowired
    AlbumRepository albumRepository ;



    @Override

    public void run(String... strings) throws Exception {
        Album album;

        album= new Album("Traditional",  "Marvin Gaye","Quiet");
        albumRepository.save(album);

        album= new Album("Pop",  "Russell Baker","Dark");
        albumRepository.save(album);
        album= new Album("Space",  "Han Shan","Sun");
        albumRepository.save(album);



    }


}

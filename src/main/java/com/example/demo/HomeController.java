package com.example.demo;

import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.Map;

@Controller
public class HomeController {
    @Autowired
    AlbumRepository albumRepository;

    @Autowired
    SongRepository songRepository;

    @Autowired
    CloudinaryConfig cloudc;

    @RequestMapping("/")
    public String home(Model model){
        model.addAttribute("albums", albumRepository.findAll());
        model.addAttribute("songs", songRepository.findAll());

        return "index";
    }



    @GetMapping("/addalbum")

    public String albumForm(Model model){
        model.addAttribute("album", new Album());
        return "albumform";
    }

/*
    @PostMapping("/process")
    public String processalbumForm(@Valid Album album, BindingResult result){

        if (result.hasErrors()){
            return "albumform"; }

        albumRepository.save(album);

        return "redirect:/albumlist";
    }*/


    @RequestMapping("/albumlist")

    public String albumList(Model model){

        model.addAttribute("albums", albumRepository.findAll());

        return "albumlist";
    }


    @GetMapping("/addsong")

    public String songForm(Model model){

        model.addAttribute("albums", albumRepository.findAll());

        model.addAttribute("song", new Song());

        return "songform";
    }


    @PostMapping("/process")

    public String processAlbumForm(@RequestParam(value = "file", required = true) MultipartFile file, @Valid Album album,Model model, BindingResult result){
       //If the one of the field is empty, the model will know. Otherwise it won't has the validation function.
        //model.addAttribute("employee",employee);

        if (result.hasErrors()){

            return "redirect:/addalbum";
        }
       if (file.isEmpty()){
            return "redirect:/addalbum";
        }
        try {
            Map uploadResult =cloudc.upload(file.getBytes(),
                    ObjectUtils.asMap("resourcetype", "auto"));
            album.setHeadshot(uploadResult.get("url").toString());
            albumRepository.save(album);
        } catch (IOException e){
            e.printStackTrace();
            return "redirect:/addalbum";
        }


        albumRepository.save(album);

        return "redirect:/albumlist";
    }


    @RequestMapping("/songlist")

    public String songList(Model model){

        model.addAttribute("songs", songRepository.findAll());

        return "songlist";
    }


    @RequestMapping("/detail_album/{id}")

    public String showAlbum(@PathVariable("id") long id, Model model){

        model.addAttribute("album", albumRepository.findById(id).get());
        return "showalbum";
    }


    @RequestMapping("/update_album/{id}")

    public String updateAlbum(@PathVariable("id") long id, Model model){

        model.addAttribute("album", albumRepository.findById(id).get());

        return "albumform";
    }


    @RequestMapping("/delete_album/{id}")

    public String delDepartment(@PathVariable("id") long id){

        albumRepository.deleteById(id);
        return "redirect:/";
    }


    @PostMapping("/processform")

    public String processSongForm(@RequestParam(value = "file", required = true) MultipartFile file, @Valid Song song,Model model, BindingResult result){
        //If the one of the field is empty, the model will know. Otherwise it won't has the validation function.
        //model.addAttribute("employee",employee);

        if (result.hasErrors()){

            return "redirect:/addsong";
        }
        if (file.isEmpty()){
            return "redirect:/addsong";
        }
        try {
            Map uploadResult =cloudc.upload(file.getBytes(),
                    ObjectUtils.asMap("resourcetype", "auto"));
            song.setHeadshot(uploadResult.get("url").toString());
            songRepository.save(song);
        } catch (IOException e){
            e.printStackTrace();
            return "redirect:/addalbum";
        }


        songRepository.save(song);

        return "redirect:/albumlist";
    }

    @RequestMapping("/detail_song/{id}")

    public String showEmployee(@PathVariable("id") long id, Model model){

        model.addAttribute("song", songRepository.findById(id).get());

        return "showsong";

    }


    @RequestMapping("/update_song/{id}")

    public String updateEmployee(@PathVariable("id") long id, Model model){

        model.addAttribute("song", songRepository.findById(id).get());

        model.addAttribute("albums",albumRepository.findAll());

        return "songform";

    }



    @RequestMapping("/delete_song/{id}")

    public String delSong(@PathVariable("id") long id){

        songRepository.deleteById(id);

        return "redirect:/";

    }
}

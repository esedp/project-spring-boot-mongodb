package org.example.projectmongo.resources;

import org.example.projectmongo.domain.Post;
import org.example.projectmongo.resources.util.URL;
import org.example.projectmongo.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(value="/posts")
public class PostResource {

    @Autowired
    private PostService service;

    @GetMapping(value="/{id}")
    public ResponseEntity<Post> findById(@PathVariable String id) {
        Post obj = service.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @GetMapping(value="/titlesearch")
    public ResponseEntity<List<Post>> findByTitle(@RequestParam(value="text",defaultValue="") String text) {

        text = URL.decodeParam(text);
        List<Post> list = service.findByTitle(text);

        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value="/fullsearch")
    public ResponseEntity<List<Post>> fullSearch(
            @RequestParam(value="text",defaultValue="") String text,
            @RequestParam(value="minDate",defaultValue="") String minDate,
            @RequestParam(value="maxDate",defaultValue="") String maxDate) {

        text = URL.decodeParam(text);
        LocalDate min = URL.convertDate(minDate, LocalDate.of(1970,1,1));
        LocalDate max = URL.convertDate(maxDate, LocalDate.now());

        List<Post> list = service.fullSearch(text, min, max);

        return ResponseEntity.ok().body(list);
    }

}

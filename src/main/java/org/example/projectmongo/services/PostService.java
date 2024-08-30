package org.example.projectmongo.services;

import org.example.projectmongo.domain.Post;
import org.example.projectmongo.repository.PostRepository;
import org.example.projectmongo.services.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    public Post findById(String id) {
        Optional<Post> obj = postRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Object not found"));

    }

    public List<Post> findByTitle(String text) {

        //Consulta caracteristica do MongoDB na forma de texto JSON
        return postRepository.searchTitle(text);

        // Consulta gerada pelo Spring Data automaticamente com metodo especial
        //return postRepository.findByTitleContainingIgnoreCase(text);
    }

    public List<Post> fullSearch(String text, LocalDate minDate, LocalDate maxDate) {

        return postRepository.fullSearch(text, minDate, maxDate);
    }
}

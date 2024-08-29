package org.example.projectmongo.repository;

import org.example.projectmongo.domain.Post;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends MongoRepository<Post, String> {

    //Consulta caracteristica do MongoDB na forma de texto JSON
    @Query("{ 'title' : { $regex:  ?0, $options: 'i' } }")
    List<Post> searchTitle(String text);

    //Consulta gerada pelo Spring Data automaticamente com metodo especial
    List<Post> findByTitleContainingIgnoreCase(String text);

}

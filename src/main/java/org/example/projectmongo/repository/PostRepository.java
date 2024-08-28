package org.example.projectmongo.repository;

import org.example.projectmongo.domain.Post;
import org.example.projectmongo.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends MongoRepository<Post, String> {

}

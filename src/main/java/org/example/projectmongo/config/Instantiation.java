package org.example.projectmongo.config;

import org.example.projectmongo.domain.Post;
import org.example.projectmongo.domain.User;
import org.example.projectmongo.dto.AuthorDTO;
import org.example.projectmongo.dto.CommentDTO;
import org.example.projectmongo.repository.PostRepository;
import org.example.projectmongo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

@Configuration
public class Instantiation implements CommandLineRunner {

    DateTimeFormatter fmt1 = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @Override
    public void run(String... args) throws Exception {

        userRepository.deleteAll();
        postRepository.deleteAll();

        User maria = new User(null, "Maria Brown", "maria@gmail.com");
        User alex = new User(null, "Alex Green", "alex@gmail.com");
        User bob = new User(null, "Bob Grey", "bob@gmail.com");

        userRepository.saveAll(Arrays.asList(maria, alex, bob));

        Post post1 = new Post(null, LocalDate.parse("21/03/2024",fmt1), "Partiu viagem", "Vou viajar para Sao Paulo. Abracos!", new AuthorDTO(maria));
        Post post2 = new Post(null, LocalDate.parse("23/03/2024",fmt1), "Bom dia", "Acordei feliz da vida hoje!", new AuthorDTO(maria));

        CommentDTO c1 = new CommentDTO("Boa viagem mano!", LocalDate.parse("21/03/2024", fmt1), new AuthorDTO(alex));
        CommentDTO c2 = new CommentDTO("Aproveite!", LocalDate.parse("22/03/2024", fmt1), new AuthorDTO(bob));
        CommentDTO c3 = new CommentDTO("Tenha um otimo dia!", LocalDate.parse("23/03/2024", fmt1), new AuthorDTO(alex));

        post1.getComments().addAll(Arrays.asList(c1, c2));
        post2.getComments().addAll(Arrays.asList(c3));

        postRepository.saveAll(Arrays.asList(post1, post2));

        maria.getPosts().addAll(Arrays.asList(post1, post2));
        userRepository.save(maria);
    }
}

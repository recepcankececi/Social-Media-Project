package com.workintech.twitter.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user", schema = "twitter")

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(of = "id")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    @NotNull
    @NotBlank
    @Size(max = 50)
    private String username;

    @NotNull
    @Size(max = 100)
    private String email;

    @NotNull
    @Size(max = 255)
    private String password;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Tweet> tweets = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "user_role", schema = "twitter",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<Role> roles = new ArrayList<>();

    public void addTweet(Tweet tweet){
        tweets.add(tweet);
    }

    public void removeTweet(Tweet tweet){
        tweets.remove(tweet);
    }
}

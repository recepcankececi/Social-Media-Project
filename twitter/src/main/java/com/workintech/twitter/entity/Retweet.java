package com.workintech.twitter.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "retweet", schema = "twitter")

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(of = "id")

public class Retweet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "tweet_id")
    private Tweet tweet;
}

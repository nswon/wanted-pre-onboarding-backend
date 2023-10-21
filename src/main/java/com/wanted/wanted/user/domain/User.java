package com.wanted.wanted.user.domain;

import com.wanted.wanted.application.domain.ApplicationHistory;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private final List<ApplicationHistory> applicationHistories = new ArrayList<>();

    public static User createUser() {
        return new User();
    }

    public void apply(ApplicationHistory applicationHistory) {
        applicationHistories.add(applicationHistory);
    }
}

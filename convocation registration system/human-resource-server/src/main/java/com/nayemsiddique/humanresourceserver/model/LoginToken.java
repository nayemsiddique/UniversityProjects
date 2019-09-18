package com.nayemsiddique.humanresourceserver.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class LoginToken {
    @Id
    private String id;
    @Embedded
    private Name name;
    @Enumerated(EnumType.STRING)
    private Role role;

}

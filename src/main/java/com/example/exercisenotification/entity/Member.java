package com.example.exercisenotification.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@NoArgsConstructor
@Entity
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String username;
    private String password;

    @Builder
    public Member(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @ElementCollection(fetch = FetchType.LAZY)
    private Set<MemberRole> roleSet = new HashSet<>();

    public void addMemberRole(MemberRole memberRole){
        roleSet.add(memberRole);
    }

    @Builder
    public Member(Long id, String username, String password, Set<MemberRole> roleSet) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.roleSet = roleSet;
    }
}

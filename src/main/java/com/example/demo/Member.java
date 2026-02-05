package com.example.demo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity // 「これはデータベースの表になるクラスやで！」っていう合図
public class Member {
    @Id // これが主役（ID）やで！
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 番号は自動で振ってな
    private Long id;
    private String name;

    // 5歳児でもわかる：空っぽのコンストラクタが必要なんや
    public Member() {}

    public Member(String name) {
        this.name = name;
    }

    // GetterとSetter（右クリックの「Source Action...」から作ると楽やで！）
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
}

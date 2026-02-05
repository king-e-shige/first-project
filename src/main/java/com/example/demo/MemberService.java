package com.example.demo;

import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class MemberService {
    private final MemberRepository repository;

    public MemberService(MemberRepository repository) {
        this.repository = repository;
    }

    // 名前を保存する魔法
    public void saveMember(String name) {
        repository.save(new Member(name));
    }

    // 全員分の一覧を持ってくる魔法
    public List<Member> getAllMembers() {
        return repository.findAll();
    }
}

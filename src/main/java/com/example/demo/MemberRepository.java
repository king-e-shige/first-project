package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;

// JpaRepositoryを継承するだけで、保存や削除の魔法が全部使えるようになるんや！
public interface MemberRepository extends JpaRepository<Member, Long> {
}

package com.zerobase.fastlms.member.repository;

import com.zerobase.fastlms.admin.entity.LoginHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LoginHistoryRepository extends JpaRepository<LoginHistory, Long> {
    List<LoginHistory> findAllByUserId(String userId);

    Optional<LoginHistory> findTopByUserIdOrderByLoginDtDesc(String userId);
}

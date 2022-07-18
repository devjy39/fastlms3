package com.zerobase.fastlms.member.service.impl;

import com.zerobase.fastlms.admin.dto.LoginHistoryDto;
import com.zerobase.fastlms.admin.entity.LoginHistory;
import com.zerobase.fastlms.member.repository.LoginHistoryRepository;
import com.zerobase.fastlms.member.service.LoginHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class LoginHistoryServiceImpl implements LoginHistoryService {
    private final LoginHistoryRepository loginHistoryRepository;

    @Override
    public void saveLoginHistory(String userId, String ip, String userAgent) {
        loginHistoryRepository.save(LoginHistory.builder()
                .userId(userId)
                .ip(ip)
                .userAgent(userAgent)
                .loginDt(LocalDateTime.now())
                .build());
    }

    @Override
    public List<LoginHistoryDto> getUserLoginHistory(String userId) {
        return loginHistoryRepository.findAllByUserId(userId)
                .stream().map(LoginHistoryDto::of)
                .collect(Collectors.toList());
    }

}
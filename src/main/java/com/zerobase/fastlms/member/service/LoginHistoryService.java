package com.zerobase.fastlms.member.service;


import com.zerobase.fastlms.admin.dto.LoginHistoryDto;

import java.util.List;

public interface LoginHistoryService {
    void saveLoginHistory(String userId, String ip, String userAgent);

    List<LoginHistoryDto>  getUserLoginHistory(String userId);
}

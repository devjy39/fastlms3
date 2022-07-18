package com.zerobase.fastlms.admin.dto;


import com.zerobase.fastlms.admin.entity.LoginHistory;
import com.zerobase.fastlms.util.DateTimeFormatter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class LoginHistoryDto extends DateTimeFormatter{
    Long id;
    
    String userId;
    String ip;
    String userAgent;
    LocalDateTime loginDt;

    public String getLoginDtText() {
        return dateTimeToString(loginDt);
    }

    public static LoginHistoryDto of(LoginHistory loginHistory) {
        return LoginHistoryDto.builder()
                .id(loginHistory.getId())
                .userId(loginHistory.getUserId())
                .ip(loginHistory.getIp())
                .userAgent(loginHistory.getUserAgent())
                .loginDt(loginHistory.getLoginDt())
                .build();
    }
}

package com.zerobase.fastlms.admin.dto;

import com.zerobase.fastlms.member.entity.Member;
import com.zerobase.fastlms.util.DateTimeFormatter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class MemberDto extends DateTimeFormatter{
    
    String userId;
    String userName;
    String phone;
    String password;
    LocalDateTime regDt;
    LocalDateTime udtDt;
    
    boolean emailAuthYn;
    LocalDateTime emailAuthDt;
    String emailAuthKey;
    
    String resetPasswordKey;
    LocalDateTime resetPasswordLimitDt;
    
    boolean adminYn;
    String userStatus;
    
    private String zipcode;
    private String addr;
    private String addrDetail;
    
    //추가컬럼
    long totalCount;
    long seq;

    public String getRegDtText() {
        return dateTimeToString(regDt);
    }

    public String getUdtDtText() {
        return dateTimeToString(udtDt);
    }
    
    
    public static MemberDto of(Member member) {

        return MemberDto.builder()
                .userId(member.getUserId())
                .userName(member.getUserName())
                .phone(member.getPhone())
                //.password(member.getPassword())
                .regDt(member.getRegDt())
                .udtDt(member.getUdtDt())
                .emailAuthYn(member.isEmailAuthYn())
                .emailAuthDt(member.getEmailAuthDt())
                .emailAuthKey(member.getEmailAuthKey())
                .resetPasswordKey(member.getResetPasswordKey())
                .resetPasswordLimitDt(member.getResetPasswordLimitDt())
                .adminYn(member.isAdminYn())
                .userStatus(member.getUserStatus())
                .zipcode(member.getZipcode())
                .addr(member.getAddr())
                .addrDetail(member.getAddrDetail())
                .build();
    }
    
}

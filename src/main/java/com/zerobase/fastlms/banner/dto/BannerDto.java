package com.zerobase.fastlms.banner.dto;

import com.zerobase.fastlms.banner.entity.Banner;
import com.zerobase.fastlms.util.DateTimeFormatter;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder
@Data
public class BannerDto extends DateTimeFormatter {
    private Long id;
    private String title;
    private String fileName;
    private String urlFilename;

    private String content;
    private String moveUrl;

    private int sortValue;

    private boolean newWindowYn;
    private boolean usingYn;

    private LocalDateTime regDt;

    public String getRegDtText() {
        return dateTimeToString(regDt);
    }

    public static BannerDto of(Banner banner) {
        return BannerDto.builder()
                .id(banner.getId())
                .title(banner.getTitle())
                .fileName(banner.getFileName())
                .urlFilename(banner.getUrlFilename())
                .content(banner.getContent())
                .moveUrl(banner.getMoveUrl())
                .newWindowYn(banner.isNewWindowYn())
                .sortValue(banner.getSortValue())
                .usingYn(banner.isUsingYn())
                .regDt(banner.getRegDt())
                .build();
    }
}

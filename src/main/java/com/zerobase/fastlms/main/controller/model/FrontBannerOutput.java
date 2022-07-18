package com.zerobase.fastlms.main.controller.model;

import com.zerobase.fastlms.banner.dto.BannerDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class FrontBannerOutput {
    private Long id;
    private String title;

    private String content;
    private String moveUrl;

    private String target;

    public static List<FrontBannerOutput> getFrontBannerList(List<BannerDto> bannerDtoList) {
        return bannerDtoList.stream()
                .filter(BannerDto::isUsingYn)
                .sorted((x, y) -> y.getSortValue() - x.getSortValue())
                .map(FrontBannerOutput::fromDto)
                .collect(Collectors.toList());
    }

    public static FrontBannerOutput fromDto(BannerDto bannerDto) {
        return FrontBannerOutput.builder()
                .id(bannerDto.getId())
                .title(bannerDto.getTitle())
                .content(bannerDto.getContent())
                .moveUrl(bannerDto.getMoveUrl())
                .target(bannerDto.isNewWindowYn() ? "_blank" : "_self")
                .build();
    }
}

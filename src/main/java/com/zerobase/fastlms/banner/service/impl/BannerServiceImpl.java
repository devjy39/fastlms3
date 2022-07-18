package com.zerobase.fastlms.banner.service.impl;

import com.zerobase.fastlms.banner.dto.BannerDto;
import com.zerobase.fastlms.banner.entity.Banner;
import com.zerobase.fastlms.banner.model.BannerInput;
import com.zerobase.fastlms.banner.repository.BannerRepository;
import com.zerobase.fastlms.banner.service.BannerService;
import com.zerobase.fastlms.exception.InvalidAccessException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class BannerServiceImpl implements BannerService {
    private final BannerRepository bannerRepository;

    @Override
    public List<BannerDto> list() {
        return bannerRepository.findAll().stream()
                .map(BannerDto::of).collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        bannerRepository.delete(getBanner(id));
    }

    private Banner getBanner(Long id) {
        return bannerRepository.findById(id)
                .orElseThrow(() -> new InvalidAccessException("배너가 존재하지 않습니다."));
    }

    @Override
    public void add(BannerInput parameter) {
        bannerRepository.save(Banner.builder()
                .title(parameter.getTitle())
                .fileName(parameter.getFileName())
                .moveUrl(parameter.getMoveUrl())
                .sortValue(parameter.getSortValue())
                .urlFilename(parameter.getUrlFilename())
                .usingYn(parameter.isUsingYn())
                .content(parameter.getContent())
                .newWindowYn(parameter.isNewWindowYn())
                .regDt(LocalDateTime.now())
                .build());
    }

    @Override
    public BannerDto getBannerById(Long bannerId) {
        return BannerDto.of(getBanner(bannerId));
    }

    @Override
    public void modify(Long bannerId, BannerInput parameter) {
        Banner banner = getBanner(bannerId);

        banner.setTitle(parameter.getTitle());
        banner.setContent(parameter.getContent());
        banner.setMoveUrl(parameter.getMoveUrl());
        banner.setNewWindowYn(parameter.isNewWindowYn());
        banner.setUsingYn(parameter.isUsingYn());
        banner.setSortValue(parameter.getSortValue());

        if (parameter.getFileName() != null && parameter.getFileName().isEmpty()) {
            banner.setFileName(parameter.getFileName());
            banner.setUrlFilename(parameter.getUrlFilename());
        }

        bannerRepository.save(banner);
    }
}

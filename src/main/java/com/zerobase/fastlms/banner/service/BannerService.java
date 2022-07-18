package com.zerobase.fastlms.banner.service;

import com.zerobase.fastlms.banner.dto.BannerDto;
import com.zerobase.fastlms.banner.model.BannerInput;

import java.util.List;

public interface BannerService {

    List<BannerDto> list();

    void delete(Long id);

    void add(BannerInput parameter);

    BannerDto getBannerById(Long bannerId);

    void modify(Long bannerId, BannerInput parameter);
}

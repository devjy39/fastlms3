package com.zerobase.fastlms.banner.model;

import lombok.Data;

@Data
public class BannerInput {
    private String title;
    private String fileName;
    private String urlFilename;

    private String content;
    private String moveUrl;

    private int sortValue;

    private boolean newWindowYn;
    private boolean usingYn;
}

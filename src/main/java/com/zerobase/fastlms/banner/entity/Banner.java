package com.zerobase.fastlms.banner.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Banner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    /*
    배너 이미지 파일, ?
    배너 Alter텍스트(대체 텍스트),
    배너 링크 했을때 이동하는 URL정보,
    클릭시Target정보(새창인지, 현재창 이동인지),
    정렬순서,
    프론트 표시 여부 정보
     */
}

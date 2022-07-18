package com.zerobase.fastlms.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PageUtilTest {

    @Test
    void pageUtilTest() {
        PageUtil pageUtil = new PageUtil(151, 10, 3, "");
        String htmlPager = pageUtil.pager();

        System.out.println(htmlPager);
    }

}
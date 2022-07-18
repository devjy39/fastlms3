package com.zerobase.fastlms.main.controller;


import com.zerobase.fastlms.banner.service.BannerService;
import com.zerobase.fastlms.main.controller.model.FrontBannerOutput;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@RequiredArgsConstructor
@Controller
public class MainController {
    private final BannerService bannerService;

    @RequestMapping("/")
    public String index(Model model) {
        model.addAttribute("banners",
                FrontBannerOutput.getFrontBannerList(bannerService.list()));
        return "index";
    }
    
    @RequestMapping("/error/denied")
    public String errorDenied() {
        log.error("Unauthorized access error occurred!");
        return "error/denied";
    }
    
}

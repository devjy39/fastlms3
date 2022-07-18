package com.zerobase.fastlms.banner.controller;

import com.zerobase.fastlms.banner.dto.BannerDto;
import com.zerobase.fastlms.banner.model.BannerInput;
import com.zerobase.fastlms.banner.service.BannerService;
import com.zerobase.fastlms.course.dto.CourseDto;
import com.zerobase.fastlms.util.FilePathUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.net.MalformedURLException;

@RequiredArgsConstructor
@Controller
public class AdminBannerController {
    private final BannerService bannerService;

    @GetMapping("/admin/banner/list.do")
    public String list(Model model) {
        model.addAttribute("list", bannerService.list());
        return "admin/banner/list";
    }

    @GetMapping("/admin/banner/add.do")
    public String add(Model model) {
        return "admin/banner/add";
    }

    @PostMapping("/admin/banner/add.do")
    public String addSubmit(Model model, MultipartFile file, BannerInput parameter) {
        String saveFilename = "";
        String urlFilename = "";

        if (file != null) {
            String originalFilename = file.getOriginalFilename();
            String[] arrFilename = FilePathUtil.getNewSaveFile(originalFilename);
            saveFilename = arrFilename[0];
            urlFilename = arrFilename[1];

            FilePathUtil.saveFile(file, saveFilename);
        }

        parameter.setFileName(saveFilename);
        parameter.setUrlFilename(urlFilename);

        bannerService.add(parameter);

        return "redirect:/admin/banner/list.do";
    }

    @GetMapping("/admin/banner/edit.do")
    public String edit(Model model, Long bannerId) {
        BannerDto bannerDto = bannerService.getBannerById(bannerId);
        model.addAttribute("detail", bannerDto);

        return "admin/banner/edit";
    }

    @PostMapping("/admin/banner/edit.do")
    public String editSubmit(Model model, Long bannerId, MultipartFile file, BannerInput parameter) {
        if (file != null && !file.isEmpty()) {
            String originalFilename = file.getOriginalFilename();
            System.out.println(originalFilename);

            String[] arrFilename = FilePathUtil.getNewSaveFile(originalFilename);
            FilePathUtil.saveFile(file, arrFilename[0]);

            parameter.setFileName(arrFilename[0]);
            parameter.setUrlFilename(arrFilename[1]);
        }

        bannerService.modify(bannerId, parameter);

        return "redirect:/admin/banner/edit.do?bannerId=" + bannerId;
    }

    @PostMapping("/admin/banner/delete.do")
    public String del(Model model, Long id) {
        bannerService.delete(id);

        return "redirect:/admin/banner/list.do";
    }

    @ResponseBody
    @GetMapping("/banner/image/{bannerId}")
    public Resource courseImage(@PathVariable long bannerId) throws MalformedURLException {
        BannerDto bannerDto = bannerService.getBannerById(bannerId);
        if (bannerDto.getUrlFilename().isEmpty()) {
            return null;
        }
        return new UrlResource("file:" + "." + bannerDto.getUrlFilename());// . 상대경로
    }
}

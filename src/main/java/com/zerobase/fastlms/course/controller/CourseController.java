package com.zerobase.fastlms.course.controller;


import com.zerobase.fastlms.admin.dto.CategoryDto;
import com.zerobase.fastlms.admin.service.CategoryService;
import com.zerobase.fastlms.course.dto.CourseDto;
import com.zerobase.fastlms.course.model.CourseInput;
import com.zerobase.fastlms.course.model.CourseParam;
import com.zerobase.fastlms.course.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.jdbc.Null;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.net.MalformedURLException;
import java.security.Principal;
import java.util.List;

@RequiredArgsConstructor
@Controller
public class CourseController extends BaseController {
    
    private final CourseService courseService;
    private final CategoryService categoryService;
    
    @GetMapping("/course")
    public String course(Model model
            , CourseParam parameter) {
        
        List<CourseDto> list = courseService.frontList(parameter);
        model.addAttribute("list", list);
        
        int courseTotalCount = 0;
        List<CategoryDto> categoryList = categoryService.frontList(CategoryDto.builder().build());
        if (categoryList != null) {
            for(CategoryDto x : categoryList) {
                courseTotalCount += x.getCourseCount();
            }
        }
        
        model.addAttribute("categoryList", categoryList);
        model.addAttribute("courseTotalCount", courseTotalCount);

        return "course/index";
    }
    
    @GetMapping("/course/{id}")
    public String courseDetail(Model model
            , CourseParam parameter) throws MalformedURLException {
        
        CourseDto detail = courseService.frontDetail(parameter.getId());
        model.addAttribute("detail", detail);

        return "course/detail";
    }

    @ResponseBody
    @GetMapping("/course/image/{courseId}")
    public Resource courseImage(@PathVariable long courseId) throws MalformedURLException {
        CourseDto courseDto = courseService.getById(courseId);
        System.out.println("호출 이미지: "+courseDto.getUrlFilename()); // . 상대경로
        if (courseDto.getUrlFilename().isEmpty()) {
            return null;
        }
        return new UrlResource("file:" + "." + courseDto.getUrlFilename());
    }
    
}

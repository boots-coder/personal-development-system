package com.coder.study.controller.backstage;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.coder.study.pojo.Category;
import com.coder.study.service.CateService;
import com.coder.study.service.FatherCateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/backstage/category")
public class CateController {
    @Autowired
    private CateService cateService;

    @Autowired
    private FatherCateService fatherCateService;
    @RequestMapping("/all")

    //全部子类
   // @PreAuthorize("hasAnyAuthority('/admin/all')")
    public ModelAndView allCate(@RequestParam(defaultValue = "1") int page,
                            @RequestParam(defaultValue = "10") int size){
        ModelAndView modelAndView = new ModelAndView();
        Page<Category> catePage = cateService.findCatePage(page, size);
        modelAndView.addObject("catePage",catePage);
        modelAndView.setViewName("/backstage/cate_all");
        return modelAndView;
    }



    //子类添加
    @RequestMapping("/sonadd")
    public String addson(Category category){
        cateService.add(category);
        return "redirect:/backstage/category/all";
    }


    //子类删除
    @RequestMapping("/delete")
    public String delete(Integer cid){
        cateService.delete(cid);
        return "redirect:/backstage/category/all";
    }
}

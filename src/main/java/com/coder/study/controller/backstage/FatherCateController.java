package com.coder.study.controller.backstage;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.coder.study.pojo.Catefather;
import com.coder.study.bean.CategoryWithStatus;
import com.coder.study.service.CateService;
import com.coder.study.service.FatherCateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

//import com.itbaizhan.study.bean.RoleWithStatus;
//import org.springframework.security.access.prepost.PreAuthorize;

@Controller
@RequestMapping("/backstage/fcategory")
public class FatherCateController {
    @Autowired
    private CateService cateService;

    @Autowired
    private FatherCateService fatherCateService;
    @RequestMapping("/fatherall")
    //全部父类
    // @PreAuthorize("hasAnyAuthority('/admin/all')")
    public ModelAndView allfCate(@RequestParam(defaultValue = "1") int page,
                                @RequestParam(defaultValue = "10") int size){
        ModelAndView modelAndView = new ModelAndView();
        Page<Catefather> fcatePage = fatherCateService.findFatherCatePage(page, size);
        modelAndView.addObject("fcatePage",fcatePage);
        modelAndView.setViewName("/backstage/fathercate_all");
        return modelAndView;
    }
//父类添加
    @RequestMapping("/fatheradd")
    public String addFather(Catefather catefather){
        fatherCateService.add(catefather);
        return "redirect:/backstage/fcategory/fatherall";
    }
    //父类删除
    @RequestMapping("/fatherdelete")
    public String deleteFather(Integer fcid){
        fatherCateService.delete(fcid);
        return "redirect:/backstage/fcategory/fatherall";
    }
    // 查询父亲的儿子情况
    @RequestMapping("/findson")
    public ModelAndView findFather(Integer fcid) {
        List<CategoryWithStatus> categories = fatherCateService.findSon(fcid);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("categories", categories);
        modelAndView.addObject("fcid", fcid);
        modelAndView.setViewName("/backstage/catefather_category");
        return modelAndView;
    }
    @RequestMapping("/updateson")
    public String updateSon(Integer fcid, Integer[] ids) {
        fatherCateService.updateSons(fcid, ids);
        return "redirect:/backstage/fcategory/fatherall";
    }
}

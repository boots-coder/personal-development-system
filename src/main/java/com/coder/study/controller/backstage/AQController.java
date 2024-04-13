package com.coder.study.controller.backstage;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.coder.study.pojo.Answerquestion;
import com.coder.study.service.AQService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;


@Controller
@RequestMapping("/backstage/answerquestion")
public class AQController {
    @Autowired
    private AQService aqService;

    @RequestMapping("/all")
    public ModelAndView allAq(@RequestParam(defaultValue = "1") int page,
                            @RequestParam(defaultValue = "10") int size){
        ModelAndView modelAndView = new ModelAndView();
        Page<Answerquestion> answerquestionPage = aqService.findAQPage(page, size);
        modelAndView.addObject("aqPage",answerquestionPage);
        modelAndView.setViewName("/backstage/aq_all");
        return modelAndView;
    }

    /**
     * 添加优秀问答到常见问答
     * @param ansid 回答的id
     * @return
     */
    @RequestMapping("/addTo")
    public String addTo(Integer ansid){

    Answerquestion aq =aqService.findById(ansid);
    aqService.addTo(aq);
    return "redirect:/backstage/commquestion/all";
    }

    /**
     * 查看详情
     * @param ansid 回答的id
     * @return
     */
    @RequestMapping("/check")
    public ModelAndView desc(Integer ansid){
        Answerquestion answerquestion = aqService.findDesc(ansid);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("aq", answerquestion);
        modelAndView.setViewName("/backstage/aq_desc");
        return modelAndView;
}



}

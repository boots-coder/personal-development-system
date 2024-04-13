package com.coder.study.controller.frontdesk;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.coder.study.pojo.Answerquestion;
import com.coder.study.service.AQService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;




@Controller
@RequestMapping("/frontdesk/tea_answerquestion")
public class TeaAQController {
    @Autowired
    private AQService aqService;

    @RequestMapping("/all")
    public ModelAndView allAq(@RequestParam(defaultValue = "1") int page,
                            @RequestParam(defaultValue = "10") int size){
        ModelAndView modelAndView = new ModelAndView();
        Page<Answerquestion> answerquestionPage = aqService.findAQPage(page, size);
        modelAndView.addObject("aqPage",answerquestionPage);
        modelAndView.setViewName("/frontdesk/tea_aq_all");
        return modelAndView;
    }

    /**
     * //添加优秀问答到常见问答
     * @param ansid
     * @return
     */
    @RequestMapping("/addTo")
    public String addTo(Integer ansid){

    Answerquestion aq =aqService.findById(ansid);
    aqService.addTo(aq);
    return "redirect:/frontdesk/tea_commquestion/all";
    }


    //查看详情
    @RequestMapping("/check")
    public ModelAndView desc(Integer ansid) {
        Answerquestion answerquestion = aqService.findDesc(ansid);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("aq", answerquestion);
        modelAndView.setViewName("/frontdesk/tea_aq_desc");
        return modelAndView;
    }



    // 查询教师，跳转到修改页面
    @RequestMapping("/answer")
    public ModelAndView edit(Integer ansid) {
        Answerquestion answerquestion = aqService.findById(ansid);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("aq", answerquestion);
        modelAndView.setViewName("/frontdesk/tea_aq_edit");
        return modelAndView;
    }

    // 修改教师
    @RequestMapping("/update")
    public String update(Answerquestion answerquestion) {
        aqService.update(answerquestion);
        return "redirect:/frontdesk/tea_answerquestion/all";
    }

}

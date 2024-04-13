package com.coder.study.controller.frontdesk;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.coder.study.bean.WangEditorResult;
import com.coder.study.pojo.Answerquestion;
import com.coder.study.pojo.Catefather;
import com.coder.study.pojo.Category;
import com.coder.study.service.AQService;
import com.coder.study.service.CateService;
import com.coder.study.service.FatherCateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.List;
import java.util.UUID;

@Controller

@RequestMapping("/frontdesk/stu_answerquestion")
public class StuAQController {
    @Autowired
    private FatherCateService fatherCateService;
    @Autowired
    private CateService cateService;
    @Autowired
    private AQService aqService;

    List<Category> city ;
   String name;
//查询我的人工答疑
    @RequestMapping("/all")
   // @PreAuthorize("hasAnyAuthority('/admin/all')")
    public ModelAndView allAq(@RequestParam(defaultValue = "1") int page,
                            @RequestParam(defaultValue = "10") int size,String stuName){
        ModelAndView modelAndView = new ModelAndView();
name=stuName;
        Page<Answerquestion>  answerquestionPage = aqService.findMyAQPage(page, size,stuName);
        modelAndView.addObject("aqPage",answerquestionPage);
        modelAndView.setViewName("/frontdesk/stu_aq_all");
        return modelAndView;
    }
    //查询我的人工答疑
    @RequestMapping("/partall")
    //分页查询
    public ModelAndView partAllAq(@RequestParam(defaultValue = "1") int page,
                                  @RequestParam(defaultValue = "10") int size){
        ModelAndView modelAndView = new ModelAndView();
        Page<Answerquestion>  answerquestionPage = aqService.findMyAQPage(page, size,name);
        modelAndView.addObject("aqPage",answerquestionPage);
        modelAndView.setViewName("/frontdesk/stu_aq_all");
        return modelAndView;
    }

    @ResponseBody
    // 获取所有的市
    @PostMapping("/provinceList")
    public List<Catefather> provinceList(){
        return fatherCateService.findAllFather();
    }
    @ResponseBody
    // 获取有选择的市
    @PostMapping("/cities")
    public List<Category> citiesList(){

        return city;
    }
    // 通过REST风格根据省id获取对应所有的市
    @ResponseBody
    @PostMapping("/city/{id}")
    public List<Category> city1(@PathVariable("id") Integer id){
        List<Catefather> allFather = fatherCateService.findAllFather();
        Catefather catefather = allFather.get(id - 1);
        Integer fcid = catefather.getFcid();
        city = fatherCateService.findDesc1(fcid).getCates();
       return city;
    }

    /**
     * 获取所有的市
     * @return city
     */
    @ResponseBody
    @PostMapping("/cityList")
    public List<Category> cityList(){
        return cateService.findAllSon();
    }

    /**
     * 奇奇怪怪的
     * @return
     */
    @RequestMapping("/ask_0")
    public String askCate(){
        ModelAndView modelAndView = new ModelAndView();
        List<Catefather> fcates = fatherCateService.findAllFather();
        return "redirect:/frontdesk/stu_aq_ask";
    }


    @RequestMapping("/check")
    public ModelAndView desc(Integer ansid){
        Answerquestion answerquestion = aqService.findDesc(ansid);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("aq", answerquestion);
        modelAndView.setViewName("/frontdesk/stu_aq_desc");
        return modelAndView;
    }


    // 评分（更新）
    @RequestMapping("/marks")
    public String update(Answerquestion aq) {
        if(aq.getAnswer().length()==0){
            return "redirect:/frontdesk/stu_marksfail";
        }else {
        aqService.update(aq);
        return "redirect:/frontdesk/stu_index";}
    }
    //提出问题
@RequestMapping("/ask")

public String ask(Answerquestion answerquestion) {
    aqService.add(answerquestion);
    return "redirect:/frontdesk/stu_index";
}
    // 查询常见，跳转到修改页面
    @RequestMapping("/continueAsk")
    public ModelAndView edit(Integer ansid) {
        Answerquestion answerquestion =aqService .findById(ansid);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("aq", answerquestion);
        modelAndView.setViewName("/frontdesk/stu_aq_edit");
        return modelAndView;
    }
    @RequestMapping("/continue_ask_update")
    public String updatecontinue(Answerquestion answerquestion) {
        aqService.update(answerquestion);
        return "redirect:/frontdesk/stu_index";
    }

    //编写上传控制器
    @RequestMapping(value = "/upload")
    @ResponseBody
    public WangEditorResult upload(HttpServletRequest request, MultipartFile file) throws Exception {
        // 创建文件夹，存放上传文件。
        //1.设置上传文件夹的真实路径
        String realPath = ResourceUtils.getURL("classpath:").getPath()+"/static/upload/";
        //2.判断该文件夹是否存在，如果不存在，新建文件夹
        File dir = new File(realPath);
        if (!dir.exists()){
            dir.mkdirs();
        }
        // 拿到上传文件名
        String filename = file.getOriginalFilename();
        filename = UUID.randomUUID()+filename;
        // 创建空文件
        File newFile = new File(dir, filename);
        // 将上传的文件写到空文件中
        file.transferTo(newFile);

        WangEditorResult wangEditorResult = new WangEditorResult();
        String[] data = {"/upload/"+filename};
        wangEditorResult.setErrno(0);
        wangEditorResult.setData(data);
        return wangEditorResult;
    }


}

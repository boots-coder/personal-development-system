package com.itbaizhan.study;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.coder.study.mapper.AQMapper;
import com.coder.study.mapper.CQMapper;
import com.coder.study.service.CQService;
import com.coder.study.service.StudentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class StudyApplicationTests {
@Autowired
private CQService cqService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private AQMapper aqMapper;
    @Autowired
    private CQMapper cqMapper;
    @Test
    void contextLoads() {
/*//学生信息的分页查询,desc和describe都是关键字
    Page<Commonquestion> page =cqService.findCQPage(1,5);
        System.out.println(page);*/
       /* Page<StudentInfo> page1 =studentService.findStuPage(1,5);
        System.out.println(page1);*/


     /*  Answerquestion desc=aqMapper.findDesc(1);
        System.out.println(desc);
*/



    }
}




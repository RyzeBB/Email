package com.cwca.customer.course.controller;

import com.cwca.customer.common.web.JsonResult;
import com.cwca.customer.course.entity.Course;
import com.cwca.customer.course.service.CourseService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Map;
@RequestMapping("/course/")
@Controller
public class CourseController {

    @Resource
    private CourseService courseService;

    @RequestMapping("listUI.do")
    public String listUI() {
        return "course/course_list";
    }

    @RequestMapping("editUI.do")
    public String editUI() {
        return "course/course_edit";
    }

    @RequestMapping("doFindObjects.do")
    @ResponseBody
    public JsonResult doFindObjects(
            String name,
            Integer valid,
            int pageCurrent) {
        Map<String, Object> map =
                courseService.findObjects(
                        name, valid, pageCurrent);
        return new JsonResult(map);
    }

    @RequestMapping("doValidById.do")
    @ResponseBody
    public JsonResult doValidById(
            String checkedIds,
            Integer valid) {
        courseService.validById(
                checkedIds,
                valid);
        return new JsonResult();
    }

    @RequestMapping("doSaveObject.do")
    @ResponseBody
    public JsonResult doSaveObject(Course entity
            ) {
        courseService.saveObject(entity);
        return new JsonResult();
    }

    @RequestMapping("doFindObjectById.do")
    @ResponseBody
    public JsonResult doFindObjectById(
            Integer id) {
        Course project =
                courseService.findObjectById(id);
        return new JsonResult(project);
    }

    @RequestMapping("doUpdateObject.do")
    @ResponseBody
    public JsonResult doUpdateObject(
            Course entity) {
        System.out.println(entity);

        //假设有用户登录,可以从session中获得用户信息
		/*User user=(User)
		request.getSession()
		.getAttribute("user");
		entity.setModifiedUser(user.getUsername());
		*/
        courseService.updateObject(entity);
        return new JsonResult();
    }
}

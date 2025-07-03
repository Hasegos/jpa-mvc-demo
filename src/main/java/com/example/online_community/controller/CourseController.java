package com.example.online_community.controller;

import com.example.online_community.model.Course;
import com.example.online_community.model.User;
import com.example.online_community.service.CommentService;
import com.example.online_community.service.CourseService;
import com.example.online_community.service.RatingService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
@RequestMapping("/courses")
public class CourseController {

    @Autowired CourseService courseService;
    @Autowired CommentService commentService;
    @Autowired RatingService ratingService;

    private final int PAGE_SIZE = 5;

    @GetMapping
    public String courses(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "keyword", defaultValue = "") String keyword,
            @RequestParam(name = "category", defaultValue = "") String category,
            Model model,
            HttpSession session){

        model.addAttribute("user",session.getAttribute("user"));

        List<Course> list = courseService.searchCourse(keyword, category);

        Pageable pageable = PageRequest.of(page, PAGE_SIZE, Sort.by("id").descending());

        Page<Course> coursePage = createPage(list,pageable);

        model.addAttribute("page",coursePage);
        model.addAttribute("keyword", keyword);
        model.addAttribute("category", category);

        return "courses";
    }
    @GetMapping("/new")
    public String courseForm(Model model, HttpSession session){
        model.addAttribute("user",session.getAttribute("user"));
        return "course_form";
    }

    @GetMapping("/{id}")
    public String courseDetail(
            @PathVariable Long id,
            Model model,
            HttpSession session){

        model.addAttribute("user",session.getAttribute("user"));

        Course course = courseService.getCourse(id)
                .orElseThrow(() -> new NoSuchElementException("해당 요소는 존재하지않습니다."));

        model.addAttribute("course",course);
        return "course_detail";
    }

    /* 댓글 */
    @PostMapping("/{id}/comments")
    public String comment(
            @PathVariable Long id,
            @RequestParam("content") String content,
            HttpSession session){

        User user = (User) session.getAttribute("user");
        if(user != null && !content.isBlank()){
            commentService.addComment(id,user,content);
        }
        return "redirect:/courses/" + id;
    }

    /* 평점 */
    @PostMapping("/{id}/rate")
    public String rate(
            @PathVariable Long id,
            @RequestParam("score") int score,
            HttpSession session){

        System.out.println("▶▶▶ rate() 호출, id=" + id + ", user=" + session.getAttribute("user"));
        User user = (User) session.getAttribute("user");
        if(user != null && score >= 1 && score <= 5){
            ratingService.addRating(id,user,score);
        }
        return "redirect:/courses/" + id;
    }

    /* 좋아요 */
    @PostMapping("/{id}/like")
    public String like(
            @PathVariable Long id,
            HttpSession session){
        User user = (User) session.getAttribute("user");
        if(user != null){
            courseService.toggleLike(id,user);
        }

        return "redirect:/courses/" + id;
    }

    @PostMapping
    public String createCourse(
            @RequestParam String title,
            @RequestParam String description,
            @RequestParam(required = false) String url,
            @RequestParam String category,
            HttpSession session,
            Model model){

        User user = (User)session.getAttribute("user");
        if(user == null){
            model.addAttribute("error","로그인을 먼저해주세요");
            return "login";
        }

        if(title.isBlank() || description.isBlank() || category.isBlank()){
            model.addAttribute("error", "모든 필수 항목(제목, 설명, 카테고리) 을 입력해주세요.");

            model.addAttribute("title",title);
            model.addAttribute("description",description);
            model.addAttribute("url",url);
            model.addAttribute("category",category);
            model.addAttribute("user",user);

            return "course_form";
        }

        Course course = new Course();
        course.setTitle(title);
        course.setDescription(description);
        course.setUrl(url);
        course.setCategory(category);
        course.setAuthor(user);
        courseService.saveCourse(course);

        return "redirect:/courses";
    }

    private Page<Course> createPage(List<Course> list, Pageable pageable){

        int start = (int)pageable.getOffset(); /* 첫번째 화면에 보여줄 데이터 */
        int end = Math.min(start + pageable.getPageSize(), list.size()); /* 마지막 번째까지 보여줄지*/

        List<Course> content = (start >= list.size())
                ? Collections.emptyList()
                : list.subList(start,end);

        return new PageImpl<>(content,pageable,list.size());
    }
}
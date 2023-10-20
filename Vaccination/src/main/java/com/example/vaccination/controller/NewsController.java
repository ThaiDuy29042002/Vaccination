package com.example.vaccination.controller;

import com.example.vaccination.model.entity.News;
import com.example.vaccination.service.NewsServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import java.util.List;
@Controller
@RequestMapping
public class NewsController {
    @Autowired
    private final NewsServices NewsServices;

    public NewsController(NewsServices newsServices) {
        NewsServices = newsServices;
    }

    @GetMapping(value = "/")
    public String home() {
        return "index";
    }

//    @GetMapping(value = "/1")
//    public String test(){
//        return "index2";
//    }

    //Print list on screen
    @GetMapping(value = "/newslist")
    public String findAll(Model model) {
        List<News> newsList = NewsServices.findAllByOrderByPostdateDesc();
        model.addAttribute("newsList", newsList);
        return "newslist";
    }

    //Create News
    @GetMapping(value = "/createnews")
    public String createnews(Model model) {
        model.addAttribute("create", new News());
        return "createnews";
    }

    @PostMapping(value = "/createnews")
    public String saveNews(Model model, @ModelAttribute("news") News news) {
        model.addAttribute("create", news);
        NewsServices.createNews(news);
        return "redirect:/newslist";
    }

    //Update news
    @GetMapping(value = "/update")
    public String updateNews(@RequestParam Integer newsId, Model model) {
        News existingNews = NewsServices.findbyId(newsId);
        model.addAttribute("update", existingNews);
        return "updatenews";
    }

    @PostMapping(value = "/update")
    public String updateNews(@RequestParam Integer newsId, @ModelAttribute("update") News news, Model model) {
        news.setNewsId(newsId); // Set the newsId from the URL path
        model.addAttribute("update", news);
        NewsServices.updateNews(news);
        return "redirect:/newslist";
    }

    //checkbox delete

    @GetMapping(value = "/delete/{ids}")
    public String deleteNews(@PathVariable("ids") String ids, Model model) {
        String[] idArray = ids.split(",");

        for (String idStr : idArray) {
            int id = Integer.parseInt(idStr);  // Convert each ID string to an integer
            NewsServices.deleteNews(id);  // Delete the news item with the given ID
        }
        return "redirect:/newslist";
    }
//    @GetMapping(value = "/report")
//    public String reportcustomer(){
////        NewsServices.findbyId(id);
////        List<News> reportlist = NewsServices.findAllByOrderByPostdateDesc();
////        model.addAttribute("reportlist", reportlist);
//        return "report";
//    }

    @GetMapping(value = "/report")
    public String reportresult(Model model) {
        List<News> newsList = NewsServices.findAllByOrderByPostdateDesc();
        model.addAttribute("newsList", newsList);
        return "reportInjectionResult";
    }

    @GetMapping(value = "/reportcustomer")
    public String reportcustomer(Model model) {
        List<News> newsList = NewsServices.findAllByOrderByPostdateDesc();
        model.addAttribute("newsList", newsList);
        return "reportCustomer";
    }

    @GetMapping(value = "/reportvaccine")
    public String reportvaccine(Model model) {
        List<News> newsList = NewsServices.findAllByOrderByPostdateDesc();
        model.addAttribute("newsList", newsList);
        return "reportVaccine";
    }

    @GetMapping(value = "/chart")
    public String chart(Model model) {

        return "/chart";
    }
}
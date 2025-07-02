package hello.hello_spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {
    // localhost:8080/hello 요청, TomCat 서버가 Spring Container에 요청
    @GetMapping("hello") // http url mapping
    //
    public String hello(Model model){
        model.addAttribute("data", "hello!!"); // mapping된 html 안에 data라는 key 존재
        return "hello"; // templates의 hello.html을 처리하고 반환해라, viewResolver가 해결
        // hello가 view name
    }

    @GetMapping("hello-mvc") // for mvc
    public String helloMvc(@RequestParam(value="name", required = false) String name, Model model){
        model.addAttribute("name", name);
        return "hello-template";
    }

    @GetMapping("hello-string") // for api
    @ResponseBody
    public String helloString(@RequestParam(value="name", required=false) String name){
        return "hello " + name;
    }

    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam(value="name", required = false) String name){
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
    }

    static class Hello{
        private String name;

        public String getName(){
            return name;
        }

        public void setName(String name){
            this.name = name;
        }

    }
}

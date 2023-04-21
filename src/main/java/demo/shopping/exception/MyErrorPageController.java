package demo.shopping.exception;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
public class MyErrorPageController {

    @RequestMapping("error-404")
    public String toPage404(){
        return "error/error-404";
    }
    @RequestMapping("error-400")
    public String toPage400(){
        return "error/error-400";
    }
    @RequestMapping("error-500")
    public String toPage500(){
        return "error/error-500";
    }

}

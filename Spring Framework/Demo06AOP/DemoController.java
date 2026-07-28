package com.SpringFw.App.Controller;
import com.SpringFw.App.Service.DemoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {
    private final DemoService demoService;

    public DemoController(DemoService demoService) {
        this.demoService = demoService;
    }

    @GetMapping("/demo")
    public String demo() {
        return demoService.greet();
    }
}

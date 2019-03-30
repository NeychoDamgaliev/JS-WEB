package residentevil.web.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Neycho Damgaliev on 3/16/2019.
 */
@Controller
public class HomeController extends BaseController {

    @GetMapping("/")
    public ModelAndView home() {
        return super.view("index");
    }
}

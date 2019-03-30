package residentevil.web.controllers;

import com.fasterxml.jackson.databind.ser.Serializers;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Neycho Damgaliev on 3/24/2019.
 */
@Controller
public class ErrorsController extends BaseController {

    @GetMapping("/unauthorized")
    public ModelAndView unauthorized() {
        return super.view("/error/unauthorized");
    }

}

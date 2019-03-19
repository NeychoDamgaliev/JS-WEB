package residentevil.web.controllers;

import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Neycho Damgaliev on 3/16/2019.
 */

public abstract class BaseController {

    protected ModelAndView view(String view, ModelAndView modelAndView) {
        modelAndView.setViewName(view);
        return modelAndView;
    }

    protected ModelAndView view (String view) {
        return this.view(view, new ModelAndView());
    }

    protected ModelAndView redirect(String url) {
        return this.view("redirect:"+url);
    }
}

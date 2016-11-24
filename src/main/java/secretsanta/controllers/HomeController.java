package secretsanta.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Landing page controller.
 *
 * Created by allan.moso on 11/14/2016.
 */
@Controller
@RequestMapping("/")
public class HomeController {

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView view() {
        final ModelAndView mav = new ModelAndView("home");
        mav.addObject("message", "This is home.");
        return mav;
    }
}

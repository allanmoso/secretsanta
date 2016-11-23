package secretsanta.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/user")
public class UserController {

    @RequestMapping("/{userId}")
    public ModelAndView editUser(@PathVariable("userId") String userId) {
        final ModelAndView mav = new ModelAndView("edit-user");
        mav.addObject("userId", userId);
        return mav;
    }
}

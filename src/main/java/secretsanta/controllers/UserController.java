package secretsanta.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import secretsanta.dto.UserEditDto;
import secretsanta.model.User;
import secretsanta.services.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    public UserService userService;

    @RequestMapping("/{userId}")
    public ModelAndView editUser(@PathVariable("userId") String userId) {
        final ModelAndView mav = new ModelAndView("edit-user");
        mav.addObject("userId", userId);
        return mav;
    }

    @RequestMapping("/{userId}/recipient")
    public ModelAndView recipient(@PathVariable("userId") String userId) {
        final ModelAndView mav = new ModelAndView("recipient");
        final User user = userService.getUser(userId);
        final UserEditDto dto = new UserEditDto(user);
        mav.addObject("user", dto);
        return mav;
    }
}

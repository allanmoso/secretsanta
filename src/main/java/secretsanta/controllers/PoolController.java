package secretsanta.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Page Controller for viewing a pool.
 * Created by allan.moso on 11/15/2016.
 */
@Controller
@RequestMapping("/pool")
public class PoolController {

    @RequestMapping(path = "/{poolId}", method = RequestMethod.GET)
    public ModelAndView view(@PathVariable("poolId") String poolId) {
        final ModelAndView mav = new ModelAndView("pool");
        mav.addObject("poolId", poolId);
        return mav;
    }
}

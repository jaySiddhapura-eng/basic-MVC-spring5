package CGI.Application.JokeApp.Controllers;

import CGI.Application.JokeApp.Services.JokeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class JokeController {

    private JokeService jokeService;

    @Autowired
    public JokeController(JokeService jokeService) {
        this.jokeService = jokeService;
    }

    @RequestMapping({"/",""})
    public String getJoke(Model model){

        // adding model attribute name and data
        // this model will directly be accessed in the view template
        model.addAttribute("joke",jokeService.getJoke());

        // returning the name of the joke
        return "chucknorris";
    }
}

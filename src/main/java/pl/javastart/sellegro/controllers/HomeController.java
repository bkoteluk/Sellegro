package pl.javastart.sellegro.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.javastart.sellegro.auction.AuctionService;
import pl.javastart.sellegro.repository.AuctionRepository;

@Controller
public class HomeController {

    private AuctionService auctionService;

    public HomeController(AuctionService auctionService) {
        this.auctionService = auctionService;
    }

    @GetMapping("/")
    public String home(Model model) {
        auctionService.getData();
        model.addAttribute("cars", auctionService.find4MostExpensive());
        return "home";
    }
}

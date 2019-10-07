package pl.javastart.sellegro.controllers;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.javastart.sellegro.auction.AuctionService;
import pl.javastart.sellegro.entity.Auction;
import pl.javastart.sellegro.auction.AuctionFilters;
import pl.javastart.sellegro.repository.AuctionRepository;

import java.util.List;

@Controller
public class AuctionController {

    private AuctionRepository auctionRepository;

    public AuctionController(AuctionRepository auctionRepository) {
        this.auctionRepository = auctionRepository;
    }

    @GetMapping("/auctions")
    public String auctions(Model model, @RequestParam(required = false) String sort,
                           AuctionFilters auctionFilters) {
        List<Auction> auctions;

        if(sort != null) {
            auctions = auctionRepository.findAll(Sort.by(Sort.Direction.ASC, sort));

        } else {
            auctions = auctionRepository.findAll();
        }
        model.addAttribute("cars", AuctionService.findAllForFilters(auctionFilters, auctions));
        model.addAttribute("filters", auctionFilters);
        return "pageauctions";
    }


}

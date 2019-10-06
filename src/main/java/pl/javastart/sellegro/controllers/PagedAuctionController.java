package pl.javastart.sellegro.controllers;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.javastart.sellegro.auction.AuctionFilters;
import pl.javastart.sellegro.repository.AuctionRepository;

@Controller
public class PagedAuctionController {


        private AuctionRepository auctionRepository;

        public PagedAuctionController(AuctionRepository auctionRepository) {
            this.auctionRepository = auctionRepository;
        }

        @GetMapping("/pages")
        public String pagesAuctions(Model model, @RequestParam(defaultValue = "0") int page,
                                    @RequestParam(required = false) String sort,
                                    AuctionFilters auctionFilters) {

            model.addAttribute("cars", auctionRepository.findAll("title", PageRequest.of(page, 50, Sort.by("title").descending())));

            model.addAttribute("filters", auctionFilters);
            return "pageauctions";
        }
}

package pl.javastart.sellegro.controllers;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.javastart.sellegro.auction.AuctionFilters;
import pl.javastart.sellegro.entity.Auction;
import pl.javastart.sellegro.repository.AuctionRepository;

@Controller
public class PagedAuctionController {


        private AuctionRepository auctionRepository;

        public PagedAuctionController(AuctionRepository auctionRepository) {
            this.auctionRepository = auctionRepository;
        }

        @GetMapping("/pages")
        public String pagesAuctions(Model model, @RequestParam(defaultValue = "0") int page,
                                    @RequestParam(required = false, defaultValue = "title") String sort,
                                    AuctionFilters auctionFilters) {
            Page<Auction> auctionsPage = auctionRepository.findAll(sort, PageRequest.of(page, 50, Sort.by(sort).ascending()));
            model.addAttribute("cars", auctionsPage);
            model.addAttribute("currentPage", page);
            model.addAttribute("filters", auctionFilters);
            return "pageauctions";
        }
}

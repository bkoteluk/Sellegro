package pl.javastart.sellegro.auction;

import org.springframework.stereotype.Service;
import pl.javastart.sellegro.entity.Auction;
import pl.javastart.sellegro.repository.AuctionRepository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class AuctionService {

    private AuctionRepository auctionRepository;

    public AuctionService(AuctionRepository auctionRepository) {
        this.auctionRepository = auctionRepository;
        getData();
    }

    private List<Auction> auctions;



    private static final String[] ADJECTIVES = {"Niesamowity", "Jedyny taki", "IGŁA", "HIT", "Jak nowy",
            "Perełka", "OKAZJA", "Wyjątkowy"};


    public void getData() {
        auctions = auctionRepository.findAll();
        Random random = new Random();
        for (int i = 0; i < auctions.size(); i++) {
            String randomAdjective = ADJECTIVES[random.nextInt(ADJECTIVES.length)];
            String title = randomAdjective + " " + auctions.get(i).getCarMake() + " " + auctions.get(i).getCarModel();
            auctions.get(i).setTitle(title);
        }
        auctionRepository.saveAll(auctions);
    }

    public List<Auction> find4MostExpensive() {
        return auctions.stream()
                .sorted(Comparator.comparing(Auction::getPrice).reversed())
                .limit(4)
                .collect(Collectors.toList());
    }

    public static List<Auction> findAllForFilters(AuctionFilters auctionFilters, List<Auction> auctionList) {
        return auctionList.stream()
                .filter(auction -> auctionFilters.getTitle() == null || auction.getTitle().toUpperCase().contains(auctionFilters.getTitle().toUpperCase()))
                .filter(auction -> auctionFilters.getCarMaker() == null || auction.getCarMake().toUpperCase().contains(auctionFilters.getCarMaker().toUpperCase()))
                .filter(auction -> auctionFilters.getCarModel() == null || auction.getCarModel().toUpperCase().contains(auctionFilters.getCarModel().toUpperCase()))
                .filter(auction -> auctionFilters.getColor() == null || auction.getColor().toUpperCase().contains(auctionFilters.getColor().toUpperCase()))
                .collect(Collectors.toList());
    }



    public List<Auction> findAllSorted(String sort) {
        Comparator<Auction> comparator = Comparator.comparing(Auction::getTitle);
        if(sort.equals("title")) {
            comparator = Comparator.comparing(Auction::getTitle);
        } else if(sort.equals("price")) {
            comparator = Comparator.comparing(Auction::getPrice);
        } else if(sort.equals("carMake")) {
            comparator = Comparator.comparing(Auction::getCarMake);
        } else if(sort.equals("color")) {
            comparator = Comparator.comparing(Auction::getColor);
        } else if(sort.equals("endDate")) {
            comparator = Comparator.comparing(Auction::getEndDate);
        }

        return auctions.stream()
                .sorted(comparator)
                .collect(Collectors.toList());
    }


}

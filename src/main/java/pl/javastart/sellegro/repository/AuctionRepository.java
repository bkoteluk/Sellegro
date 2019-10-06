package pl.javastart.sellegro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.javastart.sellegro.entity.Auction;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface AuctionRepository extends JpaRepository<Auction, Long> {




    @Query("SELECT a FROM Auction a ORDER BY a.price asc ")
    List<Auction> find4MostExpensive();

    @Query("SELECT a FROM Auction a ORDER BY a.price asc ")
    List<Auction> findAllSorted(String sort);

    @Transactional
    @Modifying
    @Query("UPDATE Auction a SET a.title = :title WHERE a.id = :id")
    void updateAuctionsTitles(@Param("title") String title, @Param("id") Long id);


}

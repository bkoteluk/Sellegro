package pl.javastart.sellegro.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    @Query(value="SELECT * FROM AUCTIONS  ORDER BY :sort", nativeQuery = true)
    List<Auction> findAllSorted(@Param("sort") String sort);

    @Query("SELECT a FROM Auction a ORDER BY a.title ")
    List<Auction> findAllForFilters(@Param("s") String sort);



    @Query("SELECT a FROM Auction a ORDER BY a.price asc ")
    Page<Auction> findAllSorted(String sort, Pageable pageable);

    @Query("SELECT a FROM Auction a ")
    Page<Auction> findAll(String sort, Pageable pageable);

    @Transactional
    @Modifying
    @Query("UPDATE Auction a SET a.title = :title WHERE a.id = :id")
    void updateAuctionsTitles(@Param("title") String title, @Param("id") Long id);


}

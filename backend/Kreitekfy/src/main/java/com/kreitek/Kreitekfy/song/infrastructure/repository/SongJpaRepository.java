package com.kreitek.Kreitekfy.song.infrastructure.repository;


import com.kreitek.Kreitekfy.song.domain.entity.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SongJpaRepository extends JpaRepository<Song, Long>, JpaSpecificationExecutor<Song> {

    @Query(value = "SELECT * FROM songs ORDER BY inclusion_date DESC limit 5" , nativeQuery = true)
    List<Song> getAllSongsByOrderByUploadDateDesc();

    List<Song> findAllByCategory_Name(String categoryName);

    @Query("SELECT um.song " +
            "FROM UserMusic um " +
            "JOIN um.song s " +
            "JOIN s.category c " +
            "WHERE c.id IN (:categoryIds) " +
            "GROUP BY s " +
            "HAVING AVG(  um.personalValorations ) > 3 " +
            "ORDER BY SUM(um.personalPlays) DESC LIMIT 5")
    List<Song> findUserRecommendation(List<Long> categoryIds);


//    @Query("SELECT um.song, SUM(um.personalPlays) AS totalReproductions, SUM(um.personalValorations) AS totalRating " +
//            "FROM UserMusic um " +
//            "JOIN um.song s " +
//            "JOIN s.category c " +
//            "WHERE c.categoryId IN (:categoryIds) " +
//            "GROUP BY um.song " +
//            "HAVING AVG(totalRating) > 3 " +
//            "ORDER BY totalReproductions DESC")
//    List<Object[]> findTop5ByCategoriesAndRatingGreaterThan3OrderByReproductionsDesc(List<Long> categoryIds);


    List<Song>  findTop5ByCategory_NameOrderByUploadDateDesc(String categoryName);







}

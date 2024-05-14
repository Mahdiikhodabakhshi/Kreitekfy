package com.kreitek.Kreitekfy.userMusic.infrastructure.persistenceJpa;

import com.kreitek.Kreitekfy.category.application.dto.CategoryDto;
import com.kreitek.Kreitekfy.category.domain.entity.Category;
import com.kreitek.Kreitekfy.userMusic.domain.entity.UserMusic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserMusicPersistenceJpa extends JpaRepository<UserMusic, Long> {
    Boolean existsBySong_idAndUser_username(Long songId , String username ) ;
    Optional<UserMusic> findBySong_idAndUser_username(Long songId , String username ) ;
    List<UserMusic> findByUser_username(String userId) ;
    List<UserMusic> findBySong_id(Long id) ;



    @Query("SELECT s.category " +
            "FROM UserMusic um " +
            "JOIN um.song s " +
            "WHERE um.user.username = :username " +
            "GROUP BY s.category " +
            "ORDER BY  SUM(um.personalPlays)  DESC LIMIT 2")
    List<Category> getMostListenedStylesByUser(String username);

//
//    @Query("SELECT s, SUM(um.personalPlays) AS totalReproductions, SUM(um.personalValorations) AS totalRating " +
//            "FROM Song s " +
//            "JOIN UserMusic um " +
//            "JOIN s.category c " +
//            "WHERE c.id IN (:categoryIds) " +
//            "GROUP BY s " +
//            "HAVING AVG(totalRating) > 3 " +
//            "ORDER BY totalReproductions DESC")
//    List<Object[]> findTop5ByCategoriesAndRatingGreaterThan3OrderByReproductionsDesc(List<Long> categoryIds);




   // List<UserMusic> findTop2BySong_categoryByUser_username(String userId) ;

}

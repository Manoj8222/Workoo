package com.android.workoo.dao;

import com.android.workoo.entity.Tasker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

@Repository
public interface TaskerRepository extends JpaRepository<Tasker,Long> {

    @Query(value = "SELECT phone_number FROM tasker WHERE phone_number = :phone_number",nativeQuery = true)
    Long checkTaskerPhone(@Param("phone_number") BigInteger phone_number);

    @Query(value = "SELECT password FROM tasker WHERE phone_number = :phone_number",nativeQuery = true)
    String checkTaskerPassword(@Param("phone_number")BigInteger phone_number);

    @Query(value = "SELECT * FROM tasker WHERE phone_number = :phone_number",nativeQuery = true)
    Tasker getTaskerDetailsByPhone(@Param("phone_number")BigInteger phone_number);

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO tasker(tasker_name,phone_number,password,img,skill,description,fair,location,rating,review,total_project) VALUES (:tasker_name, :phone_number, :password, :img, :skill, :description, :fair, :location, :rating, :review, :total_project)",nativeQuery = true)
    int registerNewTasker(@Param("tasker_name")String tasker_name,
                          @Param("phone_number")BigInteger phone_number,
                          @Param("password")String password,
                          @Param("img")byte[] img,
                          @Param("skill")String skill,
                          @Param("description")String description,
                          @Param("fair")Long fair,
                          @Param("location")String location,
                          @Param("rating") BigDecimal rating,
                          @Param("review")String review,
                          @Param("total_project")Long total_project);


    //Search Tasker with filters
    @Query("SELECT t FROM Tasker t " +
            "WHERE (:skill IS NULL OR t.skill LIKE %:skill%) " +
            "AND (:city IS NULL OR t.city = :city) " +
            "AND (:location IS NULL OR t.location = :location) " +
            "AND (:minFair IS NULL OR t.fair >= :minFair) " +
            "AND (:maxFair IS NULL OR t.fair <= :maxFair)")
    List<Tasker> findTaskersWithFilters(
            @Param("skill") String skill,
            @Param("city") String city,
            @Param("location") String location,
            @Param("minFair") Integer minFair,
            @Param("maxFair") Integer maxFair
    );
    @Query("SELECT t FROM Tasker t LEFT JOIN Favorite f ON t.id = f.taskerId AND f.userId = :userId")
    List<Tasker> findAllWithFavoriteStatus(@Param("userId") Long userId);
    Optional<Tasker> findById(Long taskerId);
}

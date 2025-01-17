package com.android.workoo.service;

import com.android.workoo.dao.FavoriteRepository;
import com.android.workoo.dao.TaskerRepository;
import com.android.workoo.entity.Favorite;
import com.android.workoo.entity.Tasker;
import com.android.workoo.model.TaskerDTO;
import com.android.workoo.model.TaskerFavoriteDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TaskerServiceImpl implements TaskerService{
    @Autowired
    TaskerRepository taskerRepository;
    @Autowired
    private FavoriteRepository favoriteRepository;


    @Override
    public TaskerDTO getTaskerById(Long taskerId) {
        return taskerRepository.findById(taskerId)
                .map(this::convertToDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Tasker not found"));
    }
    @Override
    public Long checkTasker(BigInteger phone_number) {
        return taskerRepository.checkTaskerPhone(phone_number);
    }

    @Override
    public String checkPassword(BigInteger phone_number) {
        return taskerRepository.checkTaskerPassword(phone_number);
    }

    @Override
    public Tasker getTaskerDetailsByPhone(BigInteger phone_number) {
        return taskerRepository.getTaskerDetailsByPhone(phone_number);
    }

    @Override
    public int registerNewTasker(String taskerName, BigInteger phoneNumber, String password, Optional<byte[]> img, String skill, String description, Optional<Long> fair, String location, Optional<BigDecimal> rating, Optional<String> review, Optional<Long> totalProject) {
        return taskerRepository.registerNewTasker(taskerName,phoneNumber,password, img.orElse(null),skill,description,fair.orElse(null),location,rating.orElse(null),review.orElse(null), totalProject.orElse(null) );
    }

    @Override
    public List<Tasker> searchTaskers(String skill, String city, String location, Integer minFair, Integer maxFair) {
        return taskerRepository.findTaskersWithFilters(skill, city, location, minFair, maxFair);
    }
//    @Override
//    public List<TaskerDTO> getTaskersWithFavoriteStatus(Long userId) {
//        List<Tasker> taskers = taskerRepository.findAllWithFavoriteStatus(userId);
//        return taskers.stream()
//                .map(tasker -> convertToDTO(tasker, userId))
//                .collect(Collectors.toList());
//    }

    @Override
    public void addToFavorites(Long taskerId, Long userId) {
        // Check if already favorited
        if (!favoriteRepository.existsByTaskerIdAndUserId(taskerId, userId)) {
            Favorite favorite = new Favorite();
            favorite.setTaskerId(taskerId);
            favorite.setUserId(userId);
            favoriteRepository.save(favorite);
        }
    }

    @Override
    public void removeFromFavorites(Long taskerId, Long userId) {
        favoriteRepository.deleteByTaskerIdAndUserId(taskerId, userId);
    }

    @Override
    public boolean checkFavoriteStatus(Long taskerId, Long userId) {
        return favoriteRepository.existsByTaskerIdAndUserId(taskerId, userId);
    }

//    @Override
//    public List<TaskerDTO> getTaskersWithFavoriteStatus(Long userId) {
//        List<Tasker> taskers = taskerRepository.findAll();
//        return taskers.stream()
//                .map(tasker -> {
//                    TaskerDTO dto = new TaskerDTO();
//                    dto.setId(tasker.getId());
//                    dto.setTaskerName(tasker.getTaskerName());
//                    dto.setRating(tasker.getRating());
//                    dto.setTotalProject(tasker.getTotalProject());
//                    dto.setImg(Arrays.toString(tasker.getImg()));
//                    dto.setFavorite(favoriteRepository.existsByTaskerIdAndUserId(tasker.getId(), userId));
//                    return dto;
//                })
//                .collect(Collectors.toList());
//    }
    private TaskerFavoriteDTO convertToDTO(Tasker tasker, Long userId) {
        TaskerFavoriteDTO dto = new TaskerFavoriteDTO();
        dto.setTaskerName(tasker.getTaskerName());
        dto.setRating(tasker.getRating());
        dto.setTotalProject(tasker.getTotalProject());
        dto.setImg(Arrays.toString(tasker.getImg()));
        dto.setFavorite(favoriteRepository.existsByTaskerIdAndUserId(tasker.getId(), userId));
        return dto;
    }
    @Override
    public List<TaskerFavoriteDTO> getFavoriteTaskers(Long userId) {
        // Get all favorites for the user
        List<Favorite> favorites = favoriteRepository.findByUserId(userId);

        // Get tasker details for each favorite
        return favorites.stream()
                .map(favorite -> {
                    Tasker tasker = taskerRepository.findById(favorite.getTaskerId())
                            .orElse(null);
                    if (tasker != null) {
                        return convertToTaskerDTO(tasker, true);
                    }
                    return null;
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    private TaskerFavoriteDTO convertToTaskerDTO(Tasker tasker, boolean isFavorite) {
        TaskerFavoriteDTO dto = new TaskerFavoriteDTO();
        dto.setId(tasker.getId());
        dto.setTaskerName(tasker.getTaskerName());
        dto.setRating(tasker.getRating());
        dto.setTotalProject(tasker.getTotalProject());
        dto.setImg(Arrays.toString(tasker.getImg()));
        dto.setFavorite(isFavorite);
        return dto;
    }

    private TaskerDTO convertToDTO(Tasker tasker) {
        TaskerDTO dto = new TaskerDTO();
        dto.setId(tasker.getId());
        dto.setTaskerName(tasker.getTaskerName());
        dto.setPhoneNumber(tasker.getPhoneNumber());
        dto.setSkill(tasker.getSkill());
        dto.setDescription(tasker.getDescription());
        dto.setFar(Math.toIntExact(tasker.getFair()));
        dto.setCity(tasker.getCity());
        dto.setLocation(tasker.getLocation());
        dto.setRating(tasker.getRating());
        dto.setReview(tasker.getReview());
        dto.setTotalProject(Math.toIntExact(tasker.getTotalProject()));
        dto.setImg(tasker.getImg());
        dto.setFavorite(true); // Set to true for favorite taskers
        return dto;
    }
}

package com.fitness.activityservice.dto;

import com.fitness.activityservice.model.ActivityType;
import jakarta.persistence.Column;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.Map;
@Data
public class ActivityResponse {
    private  String id ;
    private String userId;
    private ActivityType type;
    private Integer duration;
    private Integer caloriesBurned;
    private LocalDateTime startTime;
    private Map<String ,Object> additionalMetrics;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

//    public void setAdditionalMetrics(Map<String, Object> additionalMetrics) {
//    }
}

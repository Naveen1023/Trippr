package com.cosmos.trippr.service.pojos;

import com.cosmos.trippr.dto.ShowSeatsDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetShowDetailsByIdResponse {
    private long id;
    private double rateMultiplier;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private List<ShowSeatsDTO> showSeats;
}

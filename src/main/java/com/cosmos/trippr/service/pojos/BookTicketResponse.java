package com.cosmos.trippr.service.pojos;

import com.cosmos.trippr.entity.BookingDetailsEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookTicketResponse {
    private long id;
    private String bookingNo;
    private String bookingStatus;
    private int numberOfSeats;
    private long showId;
    private long userId;
    private List<String> seatNumbers;


    public static BookTicketResponse toResponse(BookingDetailsEntity bookingDetails) {
        return BookTicketResponse.builder()
                .id(bookingDetails.getId())
                .bookingNo(bookingDetails.getBookingNo())
                .bookingStatus(bookingDetails.getBookingStatus())
                .numberOfSeats(bookingDetails.getNumberOfSeats())
                .showId(bookingDetails.getShow().getId())
                .userId(bookingDetails.getUser().getId())
                .build();
    }
}

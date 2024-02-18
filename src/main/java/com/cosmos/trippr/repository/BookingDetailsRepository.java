package com.cosmos.trippr.repository;

import com.cosmos.trippr.entity.BookingDetailsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.awt.print.Book;

public interface BookingDetailsRepository extends JpaRepository<BookingDetailsEntity, Long> {
}

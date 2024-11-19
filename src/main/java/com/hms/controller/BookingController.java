package com.hms.controller;

import com.hms.entity.Booking;
import com.hms.entity.Property;
import com.hms.service.BookingService;
import com.hms.service.PDFService;
import com.hms.service.PropertyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/booking")
public class BookingController {
    
    private BookingService bookingService;


    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }
    @PostMapping("/create")
    public ResponseEntity<?> createBooking(
            @RequestParam Long propertyId,
            @RequestBody Booking booking,
            @RequestParam String type
            ){

       String bookingCreated = bookingService.createBooking(propertyId, booking, type);

        return new ResponseEntity<>(bookingCreated, HttpStatus.OK);

    }
}

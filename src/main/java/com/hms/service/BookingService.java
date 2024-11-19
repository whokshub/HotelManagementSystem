package com.hms.service;

import com.hms.entity.Booking;
import com.hms.entity.Property;
import com.hms.entity.Room;
import com.hms.repository.BookingRepository;
import com.hms.repository.RoomRepository;
import org.springframework.stereotype.Service;

@Service
public class BookingService {

    private BookingRepository bookingRepository;
    private PropertyService propertyService;
    private PDFService pdfService;
    private  RoomRepository roomRepository;

    public BookingService(BookingRepository bookingRepository, PropertyService propertyService, PDFService pdfService, RoomRepository roomRepository) {
        this.bookingRepository = bookingRepository;
        this.propertyService = propertyService;
        this.pdfService = pdfService;
        this.roomRepository = roomRepository;
    }


    public String createBooking(Long propertyId, Booking booking, String type) {

        Property property = propertyService.getProperty(propertyId);
        Room room = roomRepository.findByTypeAndProperty(type, property);
        if(room.getCount() ==0){
            return  "no rooms are available";
        }

        Booking save = bookingRepository.save(booking);

        if(save!=null){
            room.setCount(room.getCount() - 1);
            roomRepository.save(room);
        }

        pdfService.generateBookingPDF("E:\\Bookings\\bookings"+booking.getId()+".pdf",property);

        return "Booking generated";

    }
}

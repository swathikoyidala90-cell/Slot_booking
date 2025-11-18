package com.myslot.booking;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/booking")
public class mybookingController {

    private final mybookingRepo bookingRepo;
    private final AddRoomRepo roomRepo;

    public mybookingController(mybookingRepo bookingRepo, AddRoomRepo roomRepo) {
        this.bookingRepo = bookingRepo;
        this.roomRepo = roomRepo;
    }

    // Load booking page
    @GetMapping
    public String showBookingPage(Model model) {
        model.addAttribute("rooms", roomRepo.findAll());
        return "my_slot_booking";
    }

    // Create booking
    @PostMapping
    public String addBooking(
            @RequestParam String name,
            @RequestParam Long roomNo,
            @RequestParam String startDate,
            @RequestParam String startTimeOnly,
            @RequestParam int hours,
            Model model
    ) {

        AddRoom room = roomRepo.findByRoomNo(roomNo)
                .orElseThrow(() -> new RuntimeException("Room not found"));

        LocalDateTime start = LocalDateTime.parse(startDate + "T" + startTimeOnly);
        LocalDateTime end = start.plusHours(hours);

        List<my_slot_booking> clash = bookingRepo
                .findByRoomNoAndEndTimeAfterAndStartTimeBefore(roomNo, start, end);

        if (!clash.isEmpty()) {
            model.addAttribute("error", "❌ Room already booked during this time!");
            model.addAttribute("rooms", roomRepo.findAll());
            return "my_slot_booking";
        }

        my_slot_booking booking = new my_slot_booking();
        booking.setRegNo(UUID.randomUUID().toString().substring(0, 6));
        booking.setName(name);
        booking.setRoomNo(roomNo);
        booking.setStartTime(start);
        booking.setEndTime(end);
        booking.setTotalPay(room.getPerHour() * hours);

        bookingRepo.save(booking);

        model.addAttribute("message",
                "Booking successful! Register No: " + booking.getRegNo());
        model.addAttribute("rooms", roomRepo.findAll());

        return "my_slot_booking";
    }

    // Show cancellation page
    @GetMapping("/cancel")
    public String showCancellationPage() {
        return "cancel";  // matches cancel.html
    }

    // Handle cancellation
    @PostMapping("/cancel")
    public String cancelBooking(
            @RequestParam String regNo,
            @RequestParam String name,
            Model model
    ) {

        my_slot_booking booking = bookingRepo.findById(regNo).orElse(null);

        if (booking == null) {
            model.addAttribute("error", "Booking not found!");
            return "cancel";
        }

        if (!booking.getName().equalsIgnoreCase(name)) {
            model.addAttribute("error", "Name does not match booking!");
            return "cancel";
        }

        if (booking.getStartTime().minusHours(2).isBefore(LocalDateTime.now())) {
            model.addAttribute("error",
                    "❌ Too late to cancel — must cancel at least 2 hours before start.");
            return "cancel";
        }

        bookingRepo.delete(booking);
        model.addAttribute("message", "Booking cancelled successfully!");

        return "cancel";
    }
}

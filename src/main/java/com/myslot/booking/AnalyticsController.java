package com.myslot.booking;
import com.myslot.booking.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/analytics")
public class AnalyticsController {

    private final AddRoomRepo roomRepo;
    private final mybookingRepo bookingRepo;

    public AnalyticsController(AddRoomRepo roomRepo, mybookingRepo bookingRepo){
        this.roomRepo = roomRepo;
        this.bookingRepo = bookingRepo;
    }

    @GetMapping
    public String showAnalytics(Model model){
        List<AddRoom> rooms = roomRepo.findAll();
        List<my_slot_booking> bookings = bookingRepo.findAll();
        int revenue = bookings.stream().mapToInt(my_slot_booking::getTotalPay).sum();

        LocalDateTime now = LocalDateTime.now();
        List<AddRoom> available = rooms.stream().filter(r ->
            bookings.stream().noneMatch(b ->
                b.getRoomNo().equals(r.getRoomNo()) &&
                b.getStartTime().isBefore(now) && b.getEndTime().isAfter(now)
            )
        ).toList();

        model.addAttribute("totalRooms", rooms.size());
        model.addAttribute("totalBookings", bookings.size());
        model.addAttribute("totalRevenue", revenue);
        model.addAttribute("availableRooms", available);

        return "analytics"; // Thymeleaf template: analytics.html
    }
}

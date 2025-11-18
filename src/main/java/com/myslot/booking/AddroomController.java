package com.myslot.booking;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

@Controller
@RequestMapping("/Addroom")
public class AddroomController {

    private final AddRoomRepo roomRepo;

    public AddroomController(AddRoomRepo roomRepo){
        this.roomRepo = roomRepo;
    }

    // Serve the Addroom HTML page
    @GetMapping
    public String showAddRoomPage(){
        return "Addroom";  // must exist as src/main/resources/templates/Addroom.html
    }

    // Handle form submission
    @PostMapping
    public String addRoom(@ModelAttribute AddRoom room, Model model){
        roomRepo.save(room);           // save room to DB
        model.addAttribute("message", "Room added successfully!");
        return "Addroom";              // reload page with success message
    }
}

package com.csc3402.hotelhub1.controller;

import com.csc3402.hotelhub1.model.Room;
import com.csc3402.hotelhub1.model.RoomType;
import com.csc3402.hotelhub1.repository.RoomRepository;
import com.csc3402.hotelhub1.repository.RoomTypeRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/staff/rooms")
public class StaffRoomController {

    private final RoomRepository roomRepo;
    private final RoomTypeRepository roomTypeRepo;

    @Autowired
    public StaffRoomController(RoomRepository roomRepo, RoomTypeRepository roomTypeRepo) {
        this.roomRepo = roomRepo;
        this.roomTypeRepo = roomTypeRepo;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("rooms", roomRepo.findAll());
        return "rooms";
    }

    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("room", new Room());
        model.addAttribute("statuses", List.of("AVAILABLE", "OCCUPIED", "MAINTENANCE"));
        model.addAttribute("types", roomTypeRepo.findAll());
        model.addAttribute("formTitle", "Add New Room");
        return "room_form";
    }

    @PostMapping("/new")
    public String create(@Valid @ModelAttribute Room room, BindingResult br, Model model) {
        if (roomRepo.existsByRoomNumber(room.getRoomNumber())) {
            br.rejectValue("roomNumber", "error.roomNumber", "Room number already exists.");
        }

        if (br.hasErrors()) {
            model.addAttribute("statuses", List.of("AVAILABLE", "OCCUPIED", "MAINTENANCE"));
            model.addAttribute("types", roomTypeRepo.findAll());
            model.addAttribute("formTitle", "Add New Room");
            return "room_form";
        }

        RoomType type = roomTypeRepo.findById(room.getRoomType().getRoomTypeId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid Room Type"));
        room.setRoomType(type);

        roomRepo.save(room);
        return "redirect:/staff/rooms";
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        Room room = roomRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid room Id:" + id));
        model.addAttribute("room", room);
        model.addAttribute("statuses", List.of("AVAILABLE", "OCCUPIED", "MAINTENANCE"));
        model.addAttribute("types", roomTypeRepo.findAll());
        model.addAttribute("formTitle", "Edit Room");
        return "room_form";
    }

    @PostMapping("/{id}/edit")
    public String update(@PathVariable Long id,
                         @Valid @ModelAttribute Room room,
                         BindingResult br,
                         Model model) {
        if (roomRepo.existsByRoomNumberAndRoomIdNot(room.getRoomNumber(), id)) {
            br.rejectValue("roomNumber", "error.roomNumber", "Room number already exists.");
        }

        if (br.hasErrors()) {
            model.addAttribute("statuses", List.of("AVAILABLE", "OCCUPIED", "MAINTENANCE"));
            model.addAttribute("types", roomTypeRepo.findAll());
            model.addAttribute("formTitle", "Edit Room");
            return "room_form";
        }

        RoomType type = roomTypeRepo.findById(room.getRoomType().getRoomTypeId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid Room Type"));
        room.setRoomType(type);
        room.setRoomId(id);

        roomRepo.save(room);
        return "redirect:/staff/rooms";
    }

    @GetMapping("/{id}")
    public String view(@PathVariable Long id, Model model) {
        Room room = roomRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid room Id:" + id));
        model.addAttribute("room", room);
        return "room_view";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        roomRepo.deleteById(id);
        return "redirect:/staff/rooms";
    }
}

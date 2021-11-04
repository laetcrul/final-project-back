package be.digitalcity.laetitia.finalproject.controllers;

import be.digitalcity.laetitia.finalproject.models.dtos.AlertDTO;
import be.digitalcity.laetitia.finalproject.models.forms.AlertResponseForm;
import be.digitalcity.laetitia.finalproject.services.impl.AlertService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/alert")
public class AlertController {
    private final AlertService service;

    public AlertController(AlertService service) {
        this.service = service;
    }

    @GetMapping("")
    public ResponseEntity<List<AlertDTO>> findAll() {
        return ResponseEntity.ok(service.findAllAlerts());
    }

    @GetMapping("/created_by/{id}")
    public ResponseEntity<List<AlertDTO>> findByCreator(@PathVariable Long id) {
        return ResponseEntity.ok(this.service.findAllByCreator(id));
    }

    @PutMapping("/response")
    public ResponseEntity<String> respond(@RequestBody AlertResponseForm form) {
        this.service.respondToAlert(form);
        return ResponseEntity.ok("Response sent");
    }

    //add more for all services//

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgument(
            IllegalArgumentException e
    ){

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(e.getMessage());
    }
}

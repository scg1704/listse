package co.edu.umanizales.tads.controller;

import co.edu.umanizales.tads.controller.dto.ResponseDTO;
import co.edu.umanizales.tads.service.VetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="/vet")
public class VetController {
    @Autowired
    private VetService vetService;
    @GetMapping
    public ResponseEntity<ResponseDTO> getAllLVets(){
        return new ResponseEntity<>(new ResponseDTO(200, vetService.getVets(), null), HttpStatus.OK);
    }

    @GetMapping(path="/petvets")
    public ResponseEntity<ResponseDTO> getPetVets(){
        return new ResponseEntity<>(new ResponseDTO(200, vetService.getVetsByCodeSize(3), null), HttpStatus.OK);
    }

    @GetMapping(path="/petsales")
    public ResponseEntity<ResponseDTO> getPetSalesPlaces(){
        return new ResponseEntity<>(new ResponseDTO(200, vetService.getVetsByCodeSize(4), null), HttpStatus.OK);
    }

    @GetMapping(path="/petstuff")
    public ResponseEntity<ResponseDTO> getPetStuffPlaces(){
        return new ResponseEntity<>(new ResponseDTO(200, vetService.getVetsByCodeSize(5), null), HttpStatus.OK);
    }
}

package co.edu.umanizales.tads.controller;

import co.edu.umanizales.tads.controller.dto.ResponseDTO;
import co.edu.umanizales.tads.model.Pet;
import co.edu.umanizales.tads.service.ListDECirclesService;
import co.edu.umanizales.tads.service.ListDEService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="/circular")
public class ListDECirclesController {
    @Autowired
    private ListDECirclesService listDECirclesService;

    @GetMapping
    public ResponseEntity<ResponseDTO> getPets(){
        return new ResponseEntity<>(new ResponseDTO(200,listDECirclesService.getPets().getHead(),
                null), HttpStatus.OK);
    }

    @GetMapping(path="/add")
    public ResponseEntity<ResponseDTO> addPet(@RequestBody Pet pet){
        listDECirclesService.getPets().addPet(pet);
        return new ResponseEntity<>(new ResponseDTO(200, "the pet has been added", null),
                HttpStatus.OK);
    }

    @GetMapping(path="/addstart")
    public ResponseEntity<ResponseDTO> addPetStart(@RequestBody Pet pet){
        listDECirclesService.getPets().addPetStart(pet);
        return new ResponseEntity<>(new ResponseDTO(200, "the pet has been added", null),
                HttpStatus.OK);
    }

    @GetMapping(path="/addposition")
    public ResponseEntity<ResponseDTO> addPetPosition(@PathVariable int position, @RequestBody Pet pet){
        listDECirclesService.getPets().addPetByPosition(pet, position);
        return new ResponseEntity<>(new ResponseDTO(200, "The pet has been added", null),
                HttpStatus.OK);
    }

    @GetMapping(path="/showerpet")
    public ResponseEntity<ResponseDTO> showerPet(@PathVariable int position){
        listDECirclesService.getPets().showerPet(position);
        return new ResponseEntity<>(new ResponseDTO(200, "The pet is clean", null),
                HttpStatus.OK);
    }
}

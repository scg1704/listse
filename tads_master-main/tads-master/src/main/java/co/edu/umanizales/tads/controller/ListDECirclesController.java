package co.edu.umanizales.tads.controller;

import co.edu.umanizales.tads.controller.dto.ReportSpecieShowerDTO;
import co.edu.umanizales.tads.controller.dto.ResponseDTO;
import co.edu.umanizales.tads.model.Pet;
import co.edu.umanizales.tads.service.ListDECirclesService;
import co.edu.umanizales.tads.service.SpecieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="/circular")
public class ListDECirclesController {
    @Autowired
    private ListDECirclesService listDECirclesService;

    @Autowired
    private SpecieService specieService;

    @GetMapping
    public ResponseEntity<ResponseDTO> getPets(){
        return new ResponseEntity<>(new ResponseDTO(200,listDECirclesService.getPets().print(),
                null), HttpStatus.OK);
    }

    @PostMapping(path="/add")
    public ResponseEntity<ResponseDTO> addPet(@RequestBody Pet pet){
        listDECirclesService.getPets().addPet(pet);
        return new ResponseEntity<>(new ResponseDTO(200, "the pet has been added", null),
                HttpStatus.OK);
    }

    @PostMapping(path="/addstart")
    public ResponseEntity<ResponseDTO> addPetStart(@RequestBody Pet pet){
        listDECirclesService.getPets().addPetStart(pet);
        return new ResponseEntity<>(new ResponseDTO(200, "the pet has been added", null),
                HttpStatus.OK);
    }

    @PostMapping(path="/addposition/{position}")
    public ResponseEntity<ResponseDTO> addPetPosition(@PathVariable int position, @RequestBody Pet pet){
        listDECirclesService.getPets().addPetByPosition(pet, position);
        return new ResponseEntity<>(new ResponseDTO(200, "The pet has been added", null),
                HttpStatus.OK);
    }

    @GetMapping(path="/showerpetposition/{position}")
    public ResponseEntity<ResponseDTO> showerPetPosition(@PathVariable int position){
        listDECirclesService.getPets().showerByPosition(position);
        return new ResponseEntity<>(new ResponseDTO(200, "The pet is clean", null),
                HttpStatus.OK);
    }

    @GetMapping(path="/takeshower/{direction}")
    public ResponseEntity<ResponseDTO> takeShower(@PathVariable String direction){
        listDECirclesService.getPets().takeShower(direction);
        return new ResponseEntity<>(new ResponseDTO(200, "A random pet is clean", null),
                HttpStatus.OK);
    }

    @GetMapping(path="/petsshowerbyspecie")
    public ResponseEntity<ResponseDTO> getReportPetsBySpecieShower(){
        ReportSpecieShowerDTO report = listDECirclesService.getPets().getReportPetsBySpecieShower();
        return new ResponseEntity<>(new ResponseDTO(200, report, null), HttpStatus.OK);
    }
}

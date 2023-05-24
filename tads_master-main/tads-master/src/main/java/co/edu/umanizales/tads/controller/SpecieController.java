package co.edu.umanizales.tads.controller;

import co.edu.umanizales.tads.controller.dto.ResponseDTO;
import co.edu.umanizales.tads.service.SpecieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="/specie")
public class SpecieController {
    @Autowired
    private SpecieService specieService;

    @GetMapping
    public ResponseEntity<ResponseDTO> getAllSpecies(){
        return new ResponseEntity<>(new ResponseDTO(200, specieService.getSpecies(), null), HttpStatus.OK);
    }

    @GetMapping(path="speciebycode/{code}")
    public ResponseEntity<ResponseDTO> getSpecieByCode(@PathVariable String code){
        return new ResponseEntity<>(new ResponseDTO(200, specieService.getSpecieByCode(code), null), HttpStatus.OK);
    }
}

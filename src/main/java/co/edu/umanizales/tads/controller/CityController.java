package co.edu.umanizales.tads.controller;

import co.edu.umanizales.tads.controller.dto.ResponseDTO;
import co.edu.umanizales.tads.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="/location")
public class CityController {

    @Autowired
    private CityService cityService;
    @GetMapping
    public ResponseEntity<ResponseDTO> getAllLocations(){
        return new ResponseEntity<>(new ResponseDTO(200, cityService.getCities(), null), HttpStatus.OK);
    }

    @GetMapping(path="/countries")
    public ResponseEntity<ResponseDTO> getCountries(){
        return new ResponseEntity<>(new ResponseDTO(200, cityService.getCitiesByCodeSize(3), null), HttpStatus.OK);
    }

    @GetMapping(path="/departments")
    public ResponseEntity<ResponseDTO> getDepartments(){
        return new ResponseEntity<>(new ResponseDTO(200, cityService.getCitiesByCodeSize(5), null), HttpStatus.OK);
    }
}

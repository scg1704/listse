package co.edu.umanizales.tads.controller;

import co.edu.umanizales.tads.controller.dto.*;
import co.edu.umanizales.tads.exception.ListSEException;
import co.edu.umanizales.tads.model.AgeRange;
import co.edu.umanizales.tads.model.City;
import co.edu.umanizales.tads.model.Kid;
import co.edu.umanizales.tads.service.AgeRangeService;
import co.edu.umanizales.tads.service.CityService;
import co.edu.umanizales.tads.service.ListSEService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/listse")
public class ListSEController {
    @Autowired
    private ListSEService listSEService;
    @Autowired
    private CityService cityService;
    @Autowired
    private AgeRangeService ageRangeService;

    @GetMapping
    public ResponseEntity<ResponseDTO> getKids(){
        return new ResponseEntity<>(new ResponseDTO(
                200,listSEService.getKids(),null), HttpStatus.OK);
    }

    //Se insertan las excepciones únicamente donde vayamos a añadir niños

    @PostMapping(path="/add")
    public ResponseEntity<ResponseDTO> add(@RequestBody @Valid Kid kid) throws ListSEException{
        try {
            if (kid == null){
                throw new ListSEException("Kid doesn't have any data");
            }
            listSEService.getKids().add(kid);
            return new ResponseEntity<>(new ResponseDTO(200,
                    "The kid has been added", null), HttpStatus.OK);
        }
        catch (ListSEException e){
            return new ResponseEntity<>(new ResponseDTO(400, "Error adding the kid", null),HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(path="/addstart")
    public ResponseEntity<ResponseDTO> addToStart(@RequestBody @Valid Kid kid) {
        try{
            if (kid == null){
                throw new ListSEException("Kid doesn't have any data");
            }
            listSEService.getKids().addToStart(kid);
            return new ResponseEntity<>(new ResponseDTO(200,
                    "The kid has been added to start", null), HttpStatus.OK);
        }
        catch (ListSEException e){
            return new ResponseEntity<>(new ResponseDTO(400, "Error adding the kid", null), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(path="/addposition/{position}")
    public ResponseEntity<ResponseDTO> addByPosition(@PathVariable int position, @RequestBody @Valid Kid kid) {
        try{
            if (kid == null){
                throw new ListSEException("Kid doesn't have any data");
            }
            listSEService.getKids().addByPosition(kid, position);
            return new ResponseEntity<>(new ResponseDTO(200,
                    "The kid has been added in the position", null), HttpStatus.OK);
        }
        catch (ListSEException e){
            return new ResponseEntity<>(new ResponseDTO(400, "Error adding the kid", null), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(path = "/invert")
    public ResponseEntity<ResponseDTO> invert() {
        try{
            listSEService.getKids().invert();
            return new ResponseEntity<>(new ResponseDTO(200,
                    "The list has been inverted", null), HttpStatus.OK);
        }
        catch (ListSEException e){
            return new ResponseEntity<>(new ResponseDTO(400, "Error inverting the list", null), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(path="/delete/{identification}")
    public ResponseEntity<ResponseDTO> deleteByIdentification(@PathVariable String identification) {
        try{
            listSEService.getKids().deleteByIdentification(identification);
            return new ResponseEntity<>(new ResponseDTO(200,
                    "The kid has been deleted", null), HttpStatus.OK);
        }
        catch (ListSEException e){
            return new ResponseEntity<>(new ResponseDTO(400, "Error deleting the kid", null), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(path="/averageage")
    public ResponseEntity<ResponseDTO> averageAge() {
        try{
            return new ResponseEntity<>(new ResponseDTO(200,
                    listSEService.getKids().averageAge(), null), HttpStatus.OK);
        }
        catch (ListSEException e){
            return new ResponseEntity<>(new ResponseDTO(400, "Error", null), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(path="/changeextremes")
    public ResponseEntity<ResponseDTO> changeExtremes() {
        try{
            listSEService.getKids().changeExtremes();
            return new ResponseEntity<>(new ResponseDTO(200, "Extremes have been changed",
                    null), HttpStatus.OK);
        }
        catch (ListSEException e){
            return new ResponseEntity<>(new ResponseDTO(400, "Error changing extremes", null), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping
    public ResponseEntity<ResponseDTO> addKid(@RequestBody @Valid KidDTO kidDTO) {
        City city = cityService.getCityByCode(kidDTO.getCodeCity());
        if (city == null){
            return new ResponseEntity<>(new ResponseDTO(404, "Location doesn't exist", null), HttpStatus.OK);
        }
        try{
            listSEService.getKids().addKidDone(new Kid(kidDTO.getIdentification(), kidDTO.getName(), kidDTO.getAge(), kidDTO.getGender(), city));
        }
        catch (ListSEException e){
            return new ResponseEntity<>(new ResponseDTO(400, "Already exists", null), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(new ResponseDTO(200, "Kid has been added", null), HttpStatus.OK);
    }

    @GetMapping(path = "/kidsbylocations")
    public ResponseEntity<ResponseDTO> getKidsByCity() {
        try{
            List<KidsCityDTO> kidsCityDTOList = new ArrayList<>();
            for(City city: cityService.getCities()){
                int count = listSEService.getKids().getCountKidsByCityCode(city.getCode());
                if(count > 0){
                    kidsCityDTOList.add(new KidsCityDTO(city, count));
                }
            }
            return new ResponseEntity<>(new ResponseDTO(200, kidsCityDTOList, null), HttpStatus.OK);
        }
        catch (ListSEException e){
            return new ResponseEntity<>(new ResponseDTO(400, "Error", null), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(path = "/kidsbydept")
    public ResponseEntity<ResponseDTO> getKidsByDeptCode() {
        try{
            List<KidsCityDTO> kidsCityDTOList=new ArrayList<>();
            for(City city: cityService.getCities()){
                int count = listSEService.getKids().getCountKidsByDeptCode(city.getCode());
                if(count > 0){
                    kidsCityDTOList.add(new KidsCityDTO(city, count));
                }
            }
            return new ResponseEntity<>(new ResponseDTO(200, kidsCityDTOList, null), HttpStatus.OK);
        }
        catch (ListSEException e){
            return new ResponseEntity<>(new ResponseDTO(400, "Error", null), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(path = "/deletebyage/{age}")
    public ResponseEntity<ResponseDTO> deleteByAge(@PathVariable int age) {
        try{
            listSEService.getKids().deleteByAge(age);
            return new ResponseEntity<>(new ResponseDTO(200, "Kids were deleted",
                    null), HttpStatus.OK);
        }
        catch (ListSEException e){
            return new ResponseEntity<>(new ResponseDTO(400, "Error", null), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(path="/sendbottom/{initial}")
    public ResponseEntity<ResponseDTO> sendBottomByLetter(@PathVariable char initial) {
        try{
            listSEService.getKids().sendBottomByLetter(Character.toUpperCase(initial));
            return new ResponseEntity<>(new ResponseDTO(200, "Kids with that letter at start have been sent to the end",
                    null), HttpStatus.OK);
        }
        catch (ListSEException e){
            return new ResponseEntity<>(new ResponseDTO(400, "Error", null), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(path="/boysfirstgirlslast")
    public ResponseEntity<ResponseDTO> boyStartGirlsLast() {
        try{
            listSEService.getKids().boyStartGirlsLast();
            return new ResponseEntity<>(new ResponseDTO(200, "Boys are at the start, girls are at last",
                    null), HttpStatus.OK);
        }
        catch (ListSEException e){
            return new ResponseEntity<>(new ResponseDTO(400, "Error", null), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(path="/boythengirl")
    public ResponseEntity<ResponseDTO> boyThenGirl() {
        try{
            listSEService.getKids().boyThenGirl();
            return new ResponseEntity<>(new ResponseDTO(200, "Kids have been altered by them gender",
                    null), HttpStatus.OK);
        }
        catch (ListSEException e){
            return new ResponseEntity<>(new ResponseDTO(400, "Error", null), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(path="/rangeage")
    public ResponseEntity<ResponseDTO> getAgeRange() {
        try{
            List<AgeRangeKidsDTO> rangeKidsDTOList = new ArrayList<>();
            for (AgeRange i : ageRangeService.getRanges()){
                int quantity = listSEService.getKids().getAgeRange(i.getFrom(), i.getTo());
                rangeKidsDTOList.add(new AgeRangeKidsDTO(i, quantity));
            }
            return new ResponseEntity<>(new ResponseDTO(200, rangeKidsDTOList, null), HttpStatus.OK);
        }
        catch (ListSEException e){
            return new ResponseEntity<>(new ResponseDTO(400, "Error", null), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(path="/kidsbylocationgenders/{age}")
    public ResponseEntity<ResponseDTO> getReportKidsByLocationGendersByAge(@PathVariable int age) {
        ReportKidsLocationGenderDTO report = new ReportKidsLocationGenderDTO(cityService.getCitiesByCodeSize(8));
        try{
            listSEService.getKids().getReportKidsByLocationGendersByAge(age, report);
            return new ResponseEntity<>(new ResponseDTO(200, report, null), HttpStatus.OK);
        }
        catch (ListSEException e){
            return new ResponseEntity<>(new ResponseDTO(400, "Error", null), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(path="/forwardpositions/{identification}/{positions}")
    public ResponseEntity<ResponseDTO> forwardPositions(@PathVariable String identification, @PathVariable int positions) {
        try{
            listSEService.getKids().forwardPositions(identification, positions, listSEService.getKids());
            return new ResponseEntity<>(new ResponseDTO(200, "The kid has been forwarded to the position", null), HttpStatus.OK);
        }
        catch (ListSEException e){
            return new ResponseEntity<>(new ResponseDTO(400, "Error", null), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(path="/afterwardspositions/{identification}/{positions}")
    public ResponseEntity<ResponseDTO> afterwardsPositions(@PathVariable String identification, @PathVariable int positions)  {
        try{
            listSEService.getKids().afterwardsPositions(identification, positions);
            return new ResponseEntity<>(new ResponseDTO(200, "The kid has lost positions", null), HttpStatus.OK);
        }
        catch (ListSEException e){
            return new ResponseEntity<>(new ResponseDTO(400, "Error", null), HttpStatus.BAD_REQUEST);
        }
    }

}

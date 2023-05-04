package co.edu.umanizales.tads.controller;

import co.edu.umanizales.tads.controller.dto.*;
import co.edu.umanizales.tads.exception.ListSEException;
import co.edu.umanizales.tads.model.AgeRange;
import co.edu.umanizales.tads.model.City;
import co.edu.umanizales.tads.model.Kid;
import co.edu.umanizales.tads.service.AgeRangeService;
import co.edu.umanizales.tads.service.CityService;
import co.edu.umanizales.tads.service.ListSEService;
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
                200,listSEService.getKids().getHead(),null), HttpStatus.OK);
    }

    @GetMapping(path="/add")
    public ResponseEntity<ResponseDTO> add(@RequestBody Kid kid) throws ListSEException{
        listSEService.getKids().add(kid);
        return new ResponseEntity<>(new ResponseDTO(200,
                "The kid has been added", null), HttpStatus.OK);
    }

    @GetMapping(path="/addstart")
    public ResponseEntity<ResponseDTO> addToStart(@RequestBody Kid kid) throws ListSEException{
        listSEService.getKids().addToStart(kid);
        return new ResponseEntity<>(new ResponseDTO(200,
                "The kid has been added to start", null), HttpStatus.OK);
    }

    @GetMapping(path="/addposition")
    public ResponseEntity<ResponseDTO> addByPosition(@PathVariable int position, @RequestBody Kid kid) throws ListSEException{
        listSEService.getKids().addByPosition(kid, position);
        return new ResponseEntity<>(new ResponseDTO(200,
                "The kid has been added in the position", null), HttpStatus.OK);
    }

    @GetMapping(path = "/invert")
    public ResponseEntity<ResponseDTO> invert() throws ListSEException{
        listSEService.getKids().invert();
        return new ResponseEntity<>(new ResponseDTO(200,
                "The list has been inverted", null), HttpStatus.OK);
    }

    @GetMapping(path="/delete")
    public ResponseEntity<ResponseDTO> deleteByIdentification(@PathVariable String identification) throws ListSEException{
        listSEService.getKids().deleteByIdentification(identification);
        return new ResponseEntity<>(new ResponseDTO(200,
                "The kid has been deleted", null), HttpStatus.OK);
    }

    @GetMapping(path="/averageage")
    public ResponseEntity<ResponseDTO> averageAge() throws ListSEException{
        return new ResponseEntity<>(new ResponseDTO(200,
                listSEService.getKids().averageAge(), null), HttpStatus.OK);
    }

    @GetMapping(path="/changeextremes")
    public ResponseEntity<ResponseDTO> changeExtremes() throws ListSEException{
        listSEService.getKids().changeExtremes();
        return new ResponseEntity<>(new ResponseDTO(200, "Extremes have been changed",
                null), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ResponseDTO> addKid(@RequestBody KidDTO kidDTO) throws ListSEException{
        City city = cityService.getCityByCode(kidDTO.getCodeCity());
        Boolean addKidDone = listSEService.getKids().addKidDone(new Kid(kidDTO.getIdentification(),
                kidDTO.getName(), kidDTO.getAge(), kidDTO.getGender(), city));
        if (city == null){
            return new ResponseEntity<>(new ResponseDTO(404, "Location doesn't exist", null), HttpStatus.OK);
        }
        else if(addKidDone.equals(false)){
            return new ResponseEntity<>(new ResponseDTO(400, "Already exists", null), HttpStatus.OK);
        }
        else{
            listSEService.getKids().add(new Kid(kidDTO.getIdentification(), kidDTO.getName(), kidDTO.getAge(), kidDTO.getGender(), city));
            return new ResponseEntity<>(new ResponseDTO(200, "Kid has been added", null), HttpStatus.OK);
        }
    }

    @GetMapping(path = "/kidsbylocations")
    public ResponseEntity<ResponseDTO> getKidsByCity() throws ListSEException{
        List<KidsCityDTO> kidsCityDTOList = new ArrayList<>();
        for(City city: cityService.getCities()){
            int count = listSEService.getKids().getCountKidsByCityCode(city.getCode());
            if(count > 0){
                kidsCityDTOList.add(new KidsCityDTO(city, count));
            }
        }
        return new ResponseEntity<>(new ResponseDTO(200, kidsCityDTOList, null), HttpStatus.OK);
    }

    @GetMapping(path = "/kidsbydept")
    public ResponseEntity<ResponseDTO> getKidsByDeptCode() throws ListSEException{
        List<KidsCityDTO> kidsCityDTOList=new ArrayList<>();
        for(City city: cityService.getCities()){
            int count = listSEService.getKids().getCountKidsByDeptCode(city.getCode());
            if(count > 0){
                kidsCityDTOList.add(new KidsCityDTO(city, count));
            }
        }
        return new ResponseEntity<>(new ResponseDTO(200, kidsCityDTOList, null), HttpStatus.OK);
    }

    @GetMapping(path = "/kidsbymunicipal")
    public ResponseEntity<ResponseDTO> getKidsByMunicipalCode() throws ListSEException{
        List<KidsCityDTO> kidsCityDTOList=new ArrayList<>();
        for(City city: cityService.getCities()){
            int count = listSEService.getKids().getCountKidsByMunicipalCode(city.getCode());
            if(count > 0){
                kidsCityDTOList.add(new KidsCityDTO(city, count));
            }
        }
        return new ResponseEntity<>(new ResponseDTO(200, kidsCityDTOList, null), HttpStatus.OK);
    }

    @GetMapping(path = "/deletebyage/{age}")
    public ResponseEntity<ResponseDTO> deleteByAge(@PathVariable int age) throws ListSEException{
        listSEService.getKids().deleteByAge(age);
        return new ResponseEntity<>(new ResponseDTO(200, "Kids were deleted",
                null), HttpStatus.OK);
    }

    @GetMapping(path="/sendbottom/{initial}")
    public ResponseEntity<ResponseDTO> sendBottomByLetter(@PathVariable char initial) throws ListSEException{
        listSEService.getKids().sendBottomByLetter(Character.toUpperCase(initial));
        return new ResponseEntity<>(new ResponseDTO(200, "Kids with that letter at start have been sent to the end",
                null), HttpStatus.OK);
    }

    @GetMapping(path="/boysfirstgirlslast")
    public ResponseEntity<ResponseDTO> boyStartGirlsLast() throws ListSEException{
        listSEService.getKids().boyStartGirlsLast();
        return new ResponseEntity<>(new ResponseDTO(200, "Boys are at the start, girls are at last",
                null), HttpStatus.OK);
    }

    @GetMapping(path="/boythengirl")
    public ResponseEntity<ResponseDTO> boyThenGirl() throws ListSEException{
        listSEService.getKids().boyThenGirl();
        return new ResponseEntity<>(new ResponseDTO(200, "Kids have been altered by them gender",
                null), HttpStatus.OK);
    }

    @GetMapping(path="/rangeage")
    public ResponseEntity<ResponseDTO> getAgeRange() throws ListSEException{
        List<AgeRangeKidsDTO> rangeKidsDTOList = new ArrayList<>();

        for (AgeRange i : ageRangeService.getRanges()){
            int quantity = listSEService.getKids().getAgeRange(i.getFrom(), i.getTo());
            rangeKidsDTOList.add(new AgeRangeKidsDTO(i, quantity));
        }
        return new ResponseEntity<>(new ResponseDTO(200, rangeKidsDTOList, null), HttpStatus.OK);
    }

    @GetMapping(path="/kidsbylocationgenders/{age}")
    public ResponseEntity<ResponseDTO> getReportKidsByLocationGendersByAge(@PathVariable int age) throws ListSEException{
        ReportKidsLocationGenderDTO report = new ReportKidsLocationGenderDTO(cityService.getCitiesByCodeSize(8));
        listSEService.getKids().getReportKidsByLocationGendersByAge(age, report);
        return new ResponseEntity<>(new ResponseDTO(200, report, null), HttpStatus.OK);
    }

    @GetMapping(path="/forwardpositions")
    public ResponseEntity<ResponseDTO> forwardPositions(@PathVariable String identification, int positions) throws ListSEException{
        listSEService.getKids().forwardPositions(identification, positions);
        return new ResponseEntity<>(new ResponseDTO(200, "The kid has been moved to the position", null), HttpStatus.OK);
    }

    @GetMapping(path="/afterwardspositions")
    public ResponseEntity<ResponseDTO> afterwardsPositions(@PathVariable String identification, int positions) throws ListSEException{
        listSEService.getKids().afterwardsPositions(identification, positions);
        return new ResponseEntity<>(new ResponseDTO(200, "The kid has been moved to the position", null), HttpStatus.OK);
    }

}

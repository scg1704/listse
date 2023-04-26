package co.edu.umanizales.tads.service;

import co.edu.umanizales.tads.model.AgeRange;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Data
public class AgeRangeService {
    private List<AgeRange> ranges;

    public AgeRangeService(){
        ranges=new ArrayList<>();
        ranges.add(new AgeRange(1, 3));
        ranges.add(new AgeRange(4, 6));
        ranges.add(new AgeRange(7, 9));
        ranges.add(new AgeRange(10, 12));
        ranges.add(new AgeRange(13, 15));
    }
}

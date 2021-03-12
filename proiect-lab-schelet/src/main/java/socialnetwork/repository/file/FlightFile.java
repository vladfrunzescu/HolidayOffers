package socialnetwork.repository.file;

import socialnetwork.domain.Flight;
import socialnetwork.domain.Flight;
import socialnetwork.domain.validators.Validator;
import socialnetwork.utils.Constants;
import socialnetwork.utils.My_Enum;

import java.time.LocalDateTime;
import java.util.List;

public class FlightFile extends AbstractFileRepository<Long, Flight>{

    public FlightFile(String fileName, Validator<Flight> validator) {
        super(fileName, validator);
    }

    @Override
    public Flight extractEntity(List<String> attributes) {
        Long ID = Long.parseLong(attributes.get(0));
        String from = attributes.get(1);
        String to = attributes.get(2);
        LocalDateTime departureTime = LocalDateTime.parse(attributes.get(3), Constants.DATE_TIME_FORMATTER);
        LocalDateTime landingTime  = LocalDateTime.parse(attributes.get(4), Constants.DATE_TIME_FORMATTER);
        Integer seats = Integer.parseInt(attributes.get(5));

        Flight entity = new Flight(ID, from, to , departureTime, landingTime, seats);
        return entity;
    }

    @Override
    protected String createEntityAsString(Flight entity) {
        return entity.getId()+";"+entity.getFrom()+";"+entity.getTo()+";"+entity.getDepartureTime()+";"+entity.getLandingTime()+";"+entity.getSeats();
    }
}

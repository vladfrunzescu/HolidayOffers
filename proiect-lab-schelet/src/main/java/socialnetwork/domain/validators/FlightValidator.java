package socialnetwork.domain.validators;

import socialnetwork.domain.Flight;
import socialnetwork.domain.Unu;

public class FlightValidator implements Validator<Flight> {

    @Override
    public void validate(Flight entity) throws ValidationException {
        String errors = "";

        if(entity.getId() == null || entity.getId() < 0){
            errors += "Id invalid!\n";
        }

        if(entity.getTo() == null || entity.getTo().equals("")){
            errors += "Destinatie invalida!\n";
        }

        if(entity.getFrom() == null || entity.getFrom().equals("")){
            errors += "Plecare invalida!\n";
        }

        if(entity.getSeats() == null || entity.getSeats() < 0){
            errors += "Nr locuri invalid!\n";
        }

        if(entity.getDepartureTime().isAfter(entity.getLandingTime())){
            errors += "Data plecare invalida!\n";
        }

        if(entity.getLandingTime().isBefore(entity.getDepartureTime())){
            errors += "Data sosire invalida!\n";
        }

        if(!errors.equals("")){
            throw new ValidationException(errors);
        }
    }
}

package socialnetwork.domain.validators;

import socialnetwork.domain.Client;
import socialnetwork.domain.Flight;
import socialnetwork.domain.Ticket;
import socialnetwork.repository.Repository;

public class TicketValidator implements Validator<Ticket> {
    private Repository<String, Client> repoClient;
    private Repository<Long, Flight> repoFlight;

    public TicketValidator(Repository<String, Client> repoClient, Repository<Long, Flight> repoFlight) {
        this.repoClient = repoClient;
        this.repoFlight = repoFlight;
    }

    @Override
    public void validate(Ticket entity) throws ValidationException {
        String errors = "";

        if(entity.getId() == null || entity.getId() < 0){
            errors += "Id invalid!\n";
        }

        if(entity.getUsername() == null || entity.getUsername().equals("")){
            errors += "Username invalid!\n";
        }

        if(repoClient.findOne(entity.getUsername()) == null){
            errors += "Nu exista clientul cu acest username!\n";
        }

        if(entity.getPurchaseTime() == null){
            errors += "Data cumpararii invalida!\n";
        }

        if(entity.getFlightID() == null || entity.getFlightID() < 0){
            errors += "Flight ID invalid!\n";
        }

        if(repoFlight.findOne(entity.getFlightID()) == null){
            errors += "Nu exista zborul cu acest id!\n";
        }

        if(!errors.equals("")){
            throw new ValidationException(errors);
        }
    }
}

package socialnetwork.repository.file;

import socialnetwork.domain.Ticket;
import socialnetwork.domain.Ticket;
import socialnetwork.domain.validators.Validator;
import socialnetwork.utils.Constants;

import java.time.LocalDateTime;
import java.util.List;

public class TicketFile extends AbstractFileRepository<Long, Ticket>{

    public TicketFile(String fileName, Validator<Ticket> validator) {
        super(fileName, validator);
    }

    @Override
    public Ticket extractEntity(List<String> attributes) {
        Long ID = Long.parseLong(attributes.get(0));
        String username = attributes.get(1);
        Long flightId = Long.parseLong(attributes.get(2));
        LocalDateTime purchaseTime = LocalDateTime.parse(attributes.get(3), Constants.DATE_TIME_FORMATTER);


        Ticket entity = new Ticket(ID, username, flightId, purchaseTime);
        return entity;
    }

    @Override
    protected String createEntityAsString(Ticket entity) {
        return entity.getId()+";"+entity.getUsername()+";"+entity.getFlightID()+";"+entity.getPurchaseTime().format(Constants.DATE_TIME_FORMATTER);
    }
}

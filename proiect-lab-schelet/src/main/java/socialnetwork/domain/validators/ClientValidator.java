package socialnetwork.domain.validators;

import socialnetwork.domain.Client;
import socialnetwork.domain.Unu;

public class ClientValidator implements Validator<Client>{

    @Override
    public void validate(Client entity) throws ValidationException {
        String errors = "";

        if(entity.getId() == null || entity.getId().equals("")){
            errors += "Username invalid!\n";
        }

        if(entity.getName() == null || entity.getName().equals("")){
            errors += "Nume invalid!\n";
        }

        if(!errors.equals("")){
            throw new ValidationException(errors);
        }
    }
}

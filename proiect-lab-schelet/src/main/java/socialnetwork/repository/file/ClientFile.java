package socialnetwork.repository.file;

import socialnetwork.domain.Client;
import socialnetwork.domain.Client;
import socialnetwork.domain.validators.Validator;
import socialnetwork.utils.Constants;
import socialnetwork.utils.My_Enum;

import java.time.LocalDateTime;
import java.util.List;

public class ClientFile  extends AbstractFileRepository<String, Client>{

    public ClientFile(String fileName, Validator<Client> validator) {
        super(fileName, validator);
    }

    @Override
    public Client extractEntity(List<String> attributes) {
        String ID = attributes.get(0);
        String name = attributes.get(1);
        Client entity = new Client(ID, name);
        return entity;
    }

    @Override
    protected String createEntityAsString(Client entity) {
        return entity.getId()+";"+entity.getName();
    }
    
}

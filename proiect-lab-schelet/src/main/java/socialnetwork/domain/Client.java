package socialnetwork.domain;

import java.util.Objects;

public class Client extends Entity<String>{
    private String name;

    public Client(String username, String name) {
        this.setId(username);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return Objects.equals(name, client.name) && Objects.equals(client.getId(), this.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getId(), name);
    }


}

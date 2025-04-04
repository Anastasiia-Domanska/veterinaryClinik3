package app.repository;

import app.domain.Owner;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OwnerRepository {

    private final File database;

    private final ObjectMapper mapper;

    private int maxId;

    public OwnerRepository() throws IOException {
        database = new File("database/owner.txt");
        mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);

        List<Owner> owners = findAll();

        if (!owners.isEmpty()) {
            Owner lastOwner = owners.get(owners.size() - 1);
            maxId = lastOwner.getId();
        }
    }

    public Owner save(Owner owner) throws IOException {
        owner.setId(++maxId);
        List<Owner> owners = findAll();
        owners.add(owner);
        mapper.writeValue(database, owners);
        return owner;
    }

    public List<Owner> findAll() throws IOException {
        try {
            Owner[] owners = mapper.readValue(database, Owner[].class);
            return new ArrayList<>(Arrays.asList(owners));
        } catch (MismatchedInputException e) {
            return new ArrayList<>();
        }
    }

    public Owner findById(int id) throws IOException {
        return findAll()
                .stream()
                .filter(x -> x.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public void update(Owner owner) throws IOException {
        int id = owner.getId();
        String newName = owner.getName();
        boolean active = owner.isActive();
        String phone = owner.getPhone();
        String mail = owner.getMail();

        List<Owner> owners = findAll();
        owners
                .stream()
                .filter(x -> x.getId() == id)
                .forEach(x -> {
                    x.setActive(active);
                    x.setPhone(phone);
                    x.setMail(mail);

                });
        mapper.writeValue(database, owners);
    }

    public void deleteById(int id) throws IOException {
        List<Owner> owners = findAll();
        owners.removeIf(x -> x.getId() == id);
        mapper.writeValue(database, owners);
    }


}


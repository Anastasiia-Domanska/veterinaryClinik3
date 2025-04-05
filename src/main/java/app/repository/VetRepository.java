package app.repository;

import app.domain.Vet;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class VetRepository {

    private final File database;

    private final ObjectMapper mapper;

    private int maxId;

    public VetRepository() throws IOException {
        database = new File("database/vet.txt");
        mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);

        List<Vet> vets = findAll();

        if (!vets.isEmpty()) {
            Vet lastVets = vets.get(vets.size() - 1);
            maxId = lastVets.getId();
        }
    }

    public Vet save(Vet vet) throws IOException {
        vet.setId(++maxId);
        List<Vet> vets = findAll();
        vets.add(vet);
        mapper.writeValue(database, vets);
        return vet;
    }

    public List<Vet> findAll() throws IOException {
        try {
            Vet[] vets = mapper.readValue(database, Vet[].class);
            return new ArrayList<>(Arrays.asList(vets));
        } catch (MismatchedInputException e) {
            return new ArrayList<>();
        }
    }

    public Vet findById(int id) throws IOException {
        return findAll()
                .stream()
                .filter(x -> x.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public void update(Vet vet) throws IOException {
        int id = vet.getId();
        String newName = vet.getName();
        String role = vet.getRole();
        boolean active = vet.isActive();

        List<Vet> vets = findAll();
        vets
                .stream()
                .filter(x -> x.getId() == id)
                .forEach(x -> {
                    x.setName(newName);
                    x.setActive(active);
                });

        mapper.writeValue(database, vets);
    }

    public void deleteById(int id) throws IOException {
        List<Vet> vets = findAll();
        vets.removeIf(x -> x.getId() == id);
        mapper.writeValue(database, vets);
    }
}

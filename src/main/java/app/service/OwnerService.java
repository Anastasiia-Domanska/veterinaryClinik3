package app.service;

import app.domain.Owner;
import app.domain.Pet;
import app.exception.ownerExceptions.OwnerNotFoundException;
import app.exception.ownerExceptions.OwnerSaveException;
import app.exception.ownerExceptions.OwnerUpdateException;
import app.exception.petExceptions.PetNotFoundException;
import app.repository.OwnerRepository;

import java.io.IOException;
import java.util.List;

public class OwnerService {

    private PetService petService;


    private final OwnerRepository repository;


    public OwnerService() throws IOException, OwnerNotFoundException, PetNotFoundException {
        repository = new OwnerRepository();
    }

    public void setPetService(PetService petService) {
        this.petService = petService;
    }

//
//1. Сохранить владельца в базе данных

    public Owner save(Owner owner) throws OwnerSaveException, IOException {
        if (owner == null){
            throw new OwnerSaveException("Владелец не может быть null!");
        }

        String name = owner.getName();

        if (name == null || name.trim().isEmpty()) {
            throw new OwnerSaveException("Имя владельца не может быть null!");
        }

        String mail = owner.getMail();

        if (mail == null || mail.trim().isEmpty()) {
            throw new OwnerSaveException("Почта владельца не может быть null!");
        }

        String phone = owner.getPhone();

        if (phone == null || phone.trim().isEmpty()) {
            throw new OwnerSaveException("Телефон владельца не может быть null!");
        }


        owner.setActive(true);
        return repository.save(owner);
    }

//2. Вернуть всех (активных) владельцев из базы данных

    public List<Owner> getAllActiveOwners() throws IOException {
        return repository.findAll()
                .stream()
                .filter(Owner::isActive)
                .toList();
    }


//3. Вернуть одного владельца по его id (если он активен)

    public Owner getActiveOwnerById(int id) throws OwnerNotFoundException, IOException {
        Owner owner = repository.findById(id);

        if (owner == null || !owner.isActive()){
            throw new OwnerNotFoundException(id);
        }
        return owner;
    }

//4. Изменить одного владельца в базе данных по его id

    public void update(Owner owner) throws OwnerUpdateException, OwnerSaveException, IOException {
        if (owner == null){
            throw new OwnerUpdateException("Владелец не может быть null!");
        }

        String mail = owner.getMail();

        if (mail == null || mail.trim().isEmpty()) {
            throw new OwnerSaveException("Почта владельца не может быть null!");
        }

        String phone = owner.getPhone();

        if (phone == null || phone.trim().isEmpty()) {
            throw new OwnerSaveException("Телефон владельца не может быть null!");
        }

        owner.setActive(true);
        repository.update(owner);
    }

//5. Удалить владельца из базы данных по его id

    public void deleteById(int id) throws IOException, OwnerNotFoundException {
        Owner owner = getActiveOwnerById(id);
        owner.setActive(false);
        repository.update(owner);
    }


//6. Удалить владельца из базы данных по его имени

    public void deleteByName(String name) throws IOException, OwnerNotFoundException {
        Owner owner = getAllActiveOwners()
                .stream()
                .filter(x -> x.getName().equals(name))
                .peek(x -> x.setActive(false))
                .findFirst()
                .orElseThrow( () -> new OwnerNotFoundException(name));
        repository.update(owner);
    }


//7. Восстановить удаленного владельца по его id

    public void restoreById(int id) throws IOException, OwnerNotFoundException {
        Owner owner = repository.findById(id);

        if (owner != null) {
            owner.setActive(true);
            repository.update(owner);
        } else {
            throw new OwnerNotFoundException(id);
        }
    }
//8. Вернуть общее количество владельцев в базе данных(активных)

    public int getActiveOwnersNumber() throws IOException {
        return getAllActiveOwners().size();
    }
//9. Вернуть общее количество животных владельца по id владельца

    public int getTotalPetsByOwnerId(int id) throws IOException, OwnerNotFoundException {
        return getActiveOwnerById(id).getPets().size();
    }
//10. Добавить новое животное в список животных владельца по их идентификаторам(если оба активны)

    public void addNewPetToOwner(int ownerId, int petId) throws IOException, OwnerNotFoundException, PetNotFoundException {
        Owner owner = getActiveOwnerById(ownerId);
        Pet pet = petService.getActivePetById(petId);
        owner.getPets().add(pet);
        repository.update(owner);
    }

//11. Удалить животное владельца по их идентификаторам
    public void removePetFromOwnersList(int ownerId, int petId) throws IOException, OwnerNotFoundException, PetNotFoundException {
        Owner owner = getActiveOwnerById(ownerId);
        Pet pet = petService.getActivePetById(petId);
        owner.getPets().remove(pet);
        repository.update(owner);
    }
//12. Полностью удалить список животных владельца по его идентификатору (если он активен)

   public void clearOwnersPetList(int ownerId) throws IOException, OwnerNotFoundException {
       Owner owner = getActiveOwnerById(ownerId);
       owner.getPets().clear();
       repository.update(owner);
   }
}

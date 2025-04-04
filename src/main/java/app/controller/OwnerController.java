package app.controller;

import app.domain.Owner;
import app.domain.Pet;
import app.exception.ownerExceptions.OwnerNotFoundException;
import app.exception.ownerExceptions.OwnerSaveException;
import app.exception.ownerExceptions.OwnerUpdateException;
import app.exception.petExceptions.PetNotFoundException;
import app.service.OwnerService;

import java.io.IOException;
import java.util.List;

public class OwnerController {

    private final OwnerService service;

    public OwnerController() throws IOException, OwnerNotFoundException, PetNotFoundException {
        service = new OwnerService();
    }

    //1. Сохранить владельца в базе данных

    public Owner save(String name, String mail, String phone) throws OwnerSaveException, IOException {
        Owner owner = new Owner(name, phone, mail);
        return service.save(owner);
    }

//2. Вернуть всех (активных) владельцев из базы данных

    public List<Owner> getAllActiveOwners() throws IOException {
        return service.getAllActiveOwners();
    }


//3. Вернуть одного владельца по его id (если он активен)

    public Owner getActiveOwnerById(int id) throws IOException, OwnerNotFoundException {
        return service.getActiveOwnerById(id);
    }

//4. Изменить одного владельца в базе данных по его id

    public void update(int id, String mail, String phone) throws OwnerUpdateException, IOException, OwnerSaveException {

        Owner owner = new Owner(id, mail, phone);
        service.update(owner);
    }


//5. Удалить владельца из базы данных по его id

    public void deleteById(int id) throws IOException, OwnerNotFoundException {
        service.deleteById(id);
    }


//6. Удалить владельца из базы данных по его имени

    public void deleteByName(String name) throws IOException, OwnerNotFoundException {

        service.deleteByName(name);
    }


//7. Восстановить удаленного владельца по его id

    public void restoreById(int id) throws IOException, OwnerNotFoundException {
        service.restoreById(id);
    }

//8. Вернуть общее количество владельцев в базе данных(активных)

    public int getActiveOwnersNumber() throws IOException {
        return service.getActiveOwnersNumber();
    }
//9. Вернуть общее количество животных владельца по id владельца

    public int getTotalPetsByOwnerId(int id) throws IOException, OwnerNotFoundException {
        return service.getTotalPetsByOwnerId(id);
    }
//10. Добавить новое животное в список животных владельца по их идентификаторам(если оба активны)

    public void addNewPetToOwner(int ownerId, int petId) throws IOException, OwnerNotFoundException, PetNotFoundException {
        service.addNewPetToOwner(ownerId, petId);
    }

    //11. Удалить животное владельца по их идентификаторам
    public void removePetFromOwnersList(int ownerId, int petId) throws IOException, OwnerNotFoundException, PetNotFoundException {
        service.removePetFromOwnersList(ownerId, petId);
    }
//12. Полностью удалить список животных владельца по его идентификатору (если он активен)

    public void clearOwnersPetList(int ownerId) throws IOException, OwnerNotFoundException {
        service.clearOwnersPetList(ownerId);
    }
}

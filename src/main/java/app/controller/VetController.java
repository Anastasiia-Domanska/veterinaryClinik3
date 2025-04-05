package app.controller;

import app.domain.Owner;
import app.domain.Vet;
import app.exception.ownerExceptions.OwnerNotFoundException;
import app.exception.ownerExceptions.OwnerSaveException;
import app.exception.ownerExceptions.OwnerUpdateException;
import app.service.PetService;

import java.io.IOException;
import java.util.List;

public class VetController {

    private PetService service;

    public VetController() {
        service = new VetService();
    }

    //    1.сохранить нового ветеринара в базе

    public Vet vet(String name, String role) {
        Vet vet = new Vet(name, role);
        return service.save(vet);
    }

//    2.вернуть всех активных ветеринаров

    public List<Vet> getAllActiveVets(){
        return service.getAllActiveVets();
    }

//    3.вернуть ветеринара по id (если он активен)

    public Vet getActiveVetById(int id){
        return service.getActiveVetById(id);
    }

//    4.Изменить данные ветеринара в базе данных по его id

    public void update(int id, String name, String role) {

        Vet vet = new Owner(id, name, role);
        service.update(vet);
    }

//    5.Удалить ветеринара из базы данных по его id

    public void deleteById(int id) {
        service.deleteById(id);
    }

//    6.Удалить ветеринара из базы данных по его имени

    public void deleteByName(String name){
        service.deleteByName(name);
    }

//    7.Восстановить удаленного ветеринара по его id

    public void restoreById(int id)  {
        service.restoreById(id);
    }

//    8.Вернуть общее количество ветеринаров в базе данных (только активных)

    public int getActiveVetsNumber(){
        return service.getActiveVetsNumber();
    }

//    9.Вернуть общее количество ветеринаров в базе данных (только не активных)

    public int getTotalVetsById(int id){
        return service.getTotalVetsById(id);
    }

//    10.Вернуть всех питомцев, которых лечил конкретный ветеринар по id ветеринара

    public int getTotalPetsByVetId(int id) {
        return service.getTotalPetsByVetId(id);
    }

//    11.Найти веринаров по конкретной должности (передаем String role)

    public int getVetsByRole(String role){
        return service.getVetsByRole(role);
    }

//    12.вернуть все визиты, которые проводил конкретный ветеринар

    // может это прописать в визитах?

//    13.проверить активность ветеринара по его id, может ли он принимать или уволен

    // за каким параметром это проверять, мне кажеться можем этот метод удалить

}

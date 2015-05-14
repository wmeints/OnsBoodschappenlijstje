package boodschappenlijst.controllers;

import boodschappenlijst.entity.BoodschappenlijstItem;
import boodschappenlijst.entity.BoodschappenlijstItemRepository;
import boodschappenlijst.entity.CreateItemData;
import boodschappenlijst.entity.UpdateItemData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.ws.Response;
import java.util.List;

@RestController
public class BoodschappenController  {
    @Autowired
    private BoodschappenlijstItemRepository boodschappenlijstItemRepository;

    @RequestMapping(value = "/item", method = RequestMethod.GET)
    public Iterable<BoodschappenlijstItem> getBoodschappenLijst() {
        return boodschappenlijstItemRepository.findAll();
    }

    @RequestMapping(value = "/item", method = RequestMethod.POST)
    public BoodschappenlijstItem createItem(CreateItemData data) {
        BoodschappenlijstItem insertedItem = boodschappenlijstItemRepository.save(new BoodschappenlijstItem(false, data.getItem(), data.getWinkel()));
        return insertedItem;
    }

    @RequestMapping(value = "/item/{id}", method = RequestMethod.PUT)
    public BoodschappenlijstItem updateItem(@PathVariable Long id, UpdateItemData data){
        BoodschappenlijstItem foundItem = boodschappenlijstItemRepository.findByID(id);

        foundItem.setDone(data.getDone());
        foundItem.setItem(data.getItem());
        foundItem.setWinkel(data.getWinkel());

        BoodschappenlijstItem savedItem = boodschappenlijstItemRepository.save(foundItem);

        return savedItem;
    }

    @RequestMapping(value = "/item/{id}", method = RequestMethod.DELETE)
    public void deleteItem(@PathVariable Long id){
        BoodschappenlijstItem foundItem = boodschappenlijstItemRepository.findByID(id);
        boodschappenlijstItemRepository.delete(foundItem);
    }


}

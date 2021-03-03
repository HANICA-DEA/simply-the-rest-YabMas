package nl.han.dea.services;

import nl.han.dea.services.dto.ItemDTO;
import nl.han.dea.services.exceptions.IdAlreadyInUseException;
import nl.han.dea.services.exceptions.ItemNotAvailableException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;

class ItemServiceTest {

    private final ItemService sut = new ItemService();

    @Test
    public void returnsEmptyArrayWhenNoItemsAvailable() {
        var expected = new ArrayList<ItemDTO>();

        Assertions.assertEquals(expected, sut.getAll());
    }

    @Test
    public void returnsAllItems() {
        var expected = new ArrayList<ItemDTO>();
        expected.add(new ItemDTO(1, "Bread", new String[]{"Breakfast, Lunch"}, "Delicious!"));
        expected.add(new ItemDTO(2, "Butter", new String[]{"Breakfast, Lunch"}, "Use it with bread"));
        expected.add(new ItemDTO(3, "Honey", new String[]{"Breakfast, Lunch"}, "Use it with bread"));

        sut.items = expected;

        Assertions.assertEquals(expected, sut.getAll());
    }

    @Test
    public void addsItem() {
        var newItem = new ItemDTO(1, "Bread", new String[]{"Breakfast, Lunch"}, "Delicious!");

        sut.addItem(newItem);

        Assertions.assertEquals(newItem, sut.items.get(0));
    }

    @Test
    void throwsIdAlreadyInUseExceptionWhenAddedItemsIdAlreadyInUse() {
        sut.items.add(new ItemDTO(1, "Bread", new String[]{"Breakfast, Lunch"}, "Delicious!"));

        Assertions.assertThrows(IdAlreadyInUseException.class, () -> {
            sut.addItem(new ItemDTO(1, "Butter", new String[]{"Breakfast, Lunch"}, "Use it with bread"));
        });
    }

    @Test
    void getsItem() {
        var item = new ItemDTO(1, "Bread", new String[]{"Breakfast, Lunch"}, "Delicious!");
        sut.items.add(item);
        Assertions.assertEquals(item, sut.getItem(1));
    }

    @Test
    void throwsItemNotAvailableExceptionWhenAskedForItemWithUnknownId() {
        var item = new ItemDTO(1, "Bread", new String[]{"Breakfast, Lunch"}, "Delicious!");
        sut.items.add(item);
        Assertions.assertThrows(ItemNotAvailableException.class, () -> {
            sut.getItem(2);
        });
    }

    @Test
    void deletesItem() {
        var item = new ItemDTO(1, "Bread", new String[]{"Breakfast, Lunch"}, "Delicious!");
        sut.items.add(item);
        sut.deleteItem(1);
        Assertions.assertEquals(Collections.emptyList(), sut.items);
    }

    @Test
    void throwsItemNotAvailableExceptionWhenAskedToDeleteItemWithUnknownId() {
        sut.items.add(new ItemDTO(1, "Bread", new String[]{"Breakfast, Lunch"}, "Delicious!"));

        Assertions.assertThrows(ItemNotAvailableException.class, () -> {
            sut.deleteItem(2);
        });
    }
}
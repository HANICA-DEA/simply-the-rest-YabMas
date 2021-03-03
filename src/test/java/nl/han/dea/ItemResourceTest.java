package nl.han.dea;

import nl.han.dea.services.ItemService;
import nl.han.dea.services.dto.ItemDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.ws.rs.core.Response;
import java.util.ArrayList;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class ItemResourceTest {

    @Mock
    ItemService itemServiceMock = new ItemService();
    private final ItemResource sut = new ItemResource();

    @BeforeEach
    public void setup() {
        //sut = new ItemResource();
        sut.itemservice = itemServiceMock;
    }

    @Test
    public void returnsItemsFromItemService() {

        var expected = new ArrayList<ItemDTO>();
        expected.add(new ItemDTO());
        expected.add(new ItemDTO());
        expected.add(new ItemDTO());

        when(itemServiceMock.getAll()).thenReturn(expected);

        var actual = sut.getAllItemsAsJson().getEntity();

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void getAllItemsReturnsStatusMessage200() {
        Assertions.assertEquals(sut.getAllItemsAsJson().getStatus(), Response.Status.OK.getStatusCode());
    }

    @Test
    public void returnsItemFromItemService() {
        var expected = new ItemDTO();

        when(itemServiceMock.getItem(1)).thenReturn(expected);

        var actual = sut.getSpecificItemAsJson(1).getEntity();

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void getItemReturnsStatusMessage200() {
        Assertions.assertEquals(sut.getSpecificItemAsJson(1).getStatus(), Response.Status.OK.getStatusCode());
    }

    @Test
    public void callsItemservicesAddItemMethod() {
        var item = new ItemDTO();
        sut.addItem(item);
        verify(itemServiceMock).addItem(item);
    }

    @Test
    public void callsItemservicesDeleteItemMethod() {
        sut.deleteItem(1);
        verify(itemServiceMock).deleteItem(1);
    }

}
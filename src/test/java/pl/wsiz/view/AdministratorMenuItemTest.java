package pl.wsiz.view;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AdministratorMenuItemTest {

    @Test
    void testUserListItem() {
        AdministratorMenuItem item = AdministratorMenuItem.USER_LIST;
        assertEquals(1, item.getNumber());
        assertEquals("lista użytkowników", item.getDescriptionPl());
    }

    @Test
    void testExitItem() {
        AdministratorMenuItem item = AdministratorMenuItem.EXIT;
        assertEquals(2, item.getNumber());
        assertEquals("wyjście z programu", item.getDescriptionPl());
    }
}

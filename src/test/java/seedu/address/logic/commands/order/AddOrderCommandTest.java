package seedu.address.logic.commands.order;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;

import org.junit.jupiter.api.Test;

public class AddOrderCommandTest {
    @Test
    public void equals_sameType_returnsTrue() {
        assertTrue(new AddOrderCommand(1, new HashMap<>()).equals(
                new AddOrderCommand(1, new HashMap<>())));
    }
}

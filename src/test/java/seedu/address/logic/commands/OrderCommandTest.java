package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.order.AddOrderCommand;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.order.OrderMap;
import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;

public class OrderCommandTest {

    @Test
    public void constructor_nullOrder_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddOrderCommand(1, null));
    }

    @Test
    public void execute_orderAcceptedByModel_addSuccessful() {
        ModelStubAcceptingOrderAdded modelStub = new ModelStubAcceptingOrderAdded();

        Map<Integer, Integer> orderMap = new HashMap<>();
        orderMap.put(1, 2);

        AddOrderCommand orderCommand = new AddOrderCommand(1, orderMap);

        CommandResult commandResult = orderCommand.execute(modelStub);

        OrderMap expectedOrder = new OrderMap(modelStub.getFilteredPersonList().get(1), orderMap);

        assertEquals(
                String.format(AddOrderCommand.MESSAGE_SUCCESS, Messages.format(expectedOrder)),
                commandResult.getFeedbackToUser()
        );

        assertEquals(Arrays.asList(expectedOrder), modelStub.ordersAdded);
    }

    @Test
    public void equals() {
        Map<Integer, Integer> order1 = new HashMap<>();
        order1.put(1, 2);

        Map<Integer, Integer> order2 = new HashMap<>();
        order2.put(2, 3);

        AddOrderCommand command1 = new AddOrderCommand(1, order1);
        AddOrderCommand command2 = new AddOrderCommand(2, order2);

        // same object
        assertTrue(command1.equals(command1));

        // same values
        AddOrderCommand command1Copy = new AddOrderCommand(1, order1);
        assertTrue(command1.equals(command1Copy));

        // different types
        assertFalse(command1.equals(1));

        // null
        assertFalse(command1.equals(null));

        // different index
        assertFalse(command1.equals(command2));
    }
    /**
     * A default model stub that have all of the methods failing.
     */
    private class ModelStub implements Model {
        @Override
        public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyUserPrefs getUserPrefs() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public GuiSettings getGuiSettings() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setGuiSettings(GuiSettings guiSettings) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getAddressBookFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAddressBookFilePath(Path addressBookFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addPerson(Person person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addOrder(OrderMap order) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAddressBook(ReadOnlyAddressBook newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasPerson(Person person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasOrder(OrderMap order) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deletePerson(Person target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteOrder(OrderMap target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setPerson(Person target, Person editedPerson) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setOrder(OrderMap target, OrderMap editedPerson) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Person> getFilteredPersonList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<OrderMap> getFilteredOrderList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredPersonList(Predicate<Person> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        public void updateFilteredOrderList(Predicate<OrderMap> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean canUndoAddressBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean canRedoAddressBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void undoAddressBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void redoAddressBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void commitAddressBook() {
            throw new AssertionError("This method should not be called.");
        }
    }

    /**
     * A Model stub that always accepts orders.
     */
    private class ModelStubAcceptingOrderAdded extends ModelStub {
        final ArrayList<OrderMap> ordersAdded = new ArrayList<>();
        private final Person defaultPerson = new PersonBuilder().build();

        @Override
        public void addOrder(OrderMap order) {
            requireNonNull(order);
            ordersAdded.add(order);
        }
    }
}
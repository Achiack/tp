package seedu.address.logic.commands.order;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.order.OrderMap;

/**
 * Lists all persons in the address book to the user.
 */
public class ListOrderCommand extends Command {

    public static final String COMMAND_WORD = "listorders";

    public static final String MESSAGE_SUCCESS = "Listed all orders";

    public static final String MESSAGE_NO_CONTACTS = "No orders found";

    public ListOrderCommand() {}

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        ObservableList<OrderMap> orderList = model.getAddressBook().getOrderList();
        if (orderList.isEmpty()) {
            return new CommandResult(MESSAGE_NO_CONTACTS);
        }

        // Placeholder print statements
        System.out.println("Initial data:");
        List<OrderMap> list = model.getAddressBook().getOrderList();
        for (int i = 0; i < list.size(); i++) {
            System.out.println((i + 1) + ". " + Messages.format(list.get(i)));
        }

        return new CommandResult(MESSAGE_SUCCESS);
    }
}

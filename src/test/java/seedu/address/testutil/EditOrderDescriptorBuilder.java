package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.logic.commands.order.EditOrderCommand.EditOrderDescriptor;
import seedu.address.model.order.OrderMap;
import seedu.address.model.order.ProductQuantityPair;

/**
 * A utility class to help with building EditOrderDescriptor objects.
 */
public class EditOrderDescriptorBuilder {

    private final EditOrderDescriptor descriptor;

    public EditOrderDescriptorBuilder() {
        descriptor = new EditOrderDescriptor();
    }

    public EditOrderDescriptorBuilder(EditOrderDescriptor descriptor) {
        this.descriptor = new EditOrderDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditOrderDescriptor} with fields containing {@code order}'s details
     */
    public EditOrderDescriptorBuilder(OrderMap order) {
        descriptor = new EditOrderDescriptor();
        descriptor.setProductQuantityPairs(order.getProductQuantityPairs());
    }

    /**
     * Sets the {@code orderMap} of the {@code EditOrderDescriptor} that we are building.
     */
    public EditOrderDescriptorBuilder withOrderMap(String... orders) {
        Set<ProductQuantityPair> map = new HashSet<>();
        for (String order : orders) {
            map.add(new ProductQuantityPair(order));
        }
        descriptor.setProductQuantityPairs(map);
        return this;
    }

    public EditOrderDescriptor build() {
        return descriptor;
    }
}

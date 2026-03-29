package seedu.address.model.order;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;

public class OrderMapTest {

    private final Person person = new PersonBuilder().build();
    private final Set<ProductQuantityPair> itemSet = new HashSet<>();

    public OrderMapTest() {
        itemSet.add(new ProductQuantityPair("1 2"));
    }

    @Test
    void constructor_andGetters_success() {
        OrderMap order = new OrderMap(person, itemSet);
        assertEquals(person, order.getPerson());
        assertEquals(itemSet, order.getProductQuantityPairs());
    }

    @Test
    void isSameOrder_sameObject_returnsTrue() {
        OrderMap order = new OrderMap(person, itemSet);
        assertTrue(order.isSameOrder(order));
    }

    @Test
    void isSameOrder_differentId_returnsFalse() {
        OrderMap order1 = new OrderMap(person, itemSet);
        OrderMap order2 = new OrderMap(person, itemSet);
        assertFalse(order1.isSameOrder(order2));
    }

    @Test
    void isSameOrder_null_returnsFalse() {
        OrderMap order = new OrderMap(person, itemSet);
        assertFalse(order.isSameOrder(null));
    }

    @Test
    void equals_sameValue_returnsTrue() {
        OrderMap order = new OrderMap(person, itemSet);
        assertEquals(order, order);
        assertEquals(order.hashCode(), order.hashCode());
        LocalDateTime now = LocalDateTime.now();
        OrderMap order1 = new OrderMap(1, person, itemSet, OrderStatus.PENDING, new OrderDateTime(now));
        OrderMap order2 = new OrderMap(1, person, itemSet, OrderStatus.PENDING, new OrderDateTime(now));
        assertEquals(order1, order2);
        assertEquals(order1.hashCode(), order2.hashCode());
    }

    @Test
    void equals_differentValue_returnsFalse() {
        Set<ProductQuantityPair> otherMap = new HashSet<>();
        otherMap.add(new ProductQuantityPair("2 1"));
        OrderMap order1 = new OrderMap(person, itemSet);
        OrderMap order2 = new OrderMap(person, otherMap);
        assertNotEquals(order1, order2);
    }

    @Test
    void equals_otherObject_returnsFalse() {
        OrderMap order = new OrderMap(person, itemSet);
        assertNotEquals(null, order);
        assertNotEquals("not an order", order);
    }

    @Test
    void toString_containsAllFields() {
        OrderMap order = new OrderMap(person, itemSet);
        String str = order.toString();
        assertTrue(str.contains("orderId"));
        assertTrue(str.contains("person"));
        assertTrue(str.contains("status"));
        assertTrue(str.contains("orderDatetime"));
    }
}

package seedu.address.storage;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.order.OrderDateTime;
import seedu.address.model.order.OrderMap;
import seedu.address.model.order.OrderStatus;
import seedu.address.model.order.ProductQuantityPair;
import seedu.address.model.person.Person;

/**
 * Jackson-friendly version of {@link OrderMap}.
 */
public class JsonAdaptedOrder {

    private final String orderId;
    private final String personName;
    private final String status;
    private final String orderDatetime;
    private final List<String> itemList = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedOrder} with the given order details.
     */
    @JsonCreator
    public JsonAdaptedOrder(
            @JsonProperty("orderId") String orderId,
            @JsonProperty("personName") String personName,
            @JsonProperty("status") String status,
            @JsonProperty("orderDatetime") String orderDatetime,
            @JsonProperty("itemList") List<String> itemList) {

        this.orderId = orderId;
        this.personName = personName;
        this.status = status;
        this.orderDatetime = orderDatetime;
        this.itemList.addAll(itemList);
    }

    /**
     * Converts a given {@code Order} into this class for Jackson use.
     */
    public JsonAdaptedOrder(OrderMap source) {
        this.orderId = Integer.toString(source.getOrderId());
        this.personName = source.getPerson().getName().toString();
        this.status = source.getStatus().toString();
        this.itemList.addAll(source.getProductQuantityPairs().stream()
                .map(ProductQuantityPair::toString)
                .toList());
        this.orderDatetime = source.getOrderDatetime().toString();
    }

    public String getPersonName() {
        return personName;
    }

    /**
     * Converts this Jackson-friendly adapted order object into the model's {@code Order} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted order.
     */
    public OrderMap toModelType(Person person) throws IllegalValueException {
        final Set<ProductQuantityPair> itemList = new HashSet<>();
        for (String item : this.itemList) {
            ProductQuantityPair productQuantityPair = new ProductQuantityPair(item);
            itemList.add(productQuantityPair);
        }

        LocalDateTime parsedDateTime;
        try {
            parsedDateTime = LocalDateTime.parse(orderDatetime);
        } catch (java.time.format.DateTimeParseException e) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            parsedDateTime = LocalDateTime.parse(orderDatetime, formatter);
        }
        return new OrderMap(
                Integer.parseInt(orderId),
                person,
                itemList,
                OrderStatus.valueOf(status),
                new OrderDateTime(parsedDateTime));
    }
}

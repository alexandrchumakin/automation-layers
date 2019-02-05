package service.model;

import lombok.Getter;

@Getter
public enum PizzaTopping {
    BACON("bacon"),
    EXTRA_CHEESE("cheese"),
    ONION("onion"),
    MUSHROOM("mushroom");

    private final String text;

    PizzaTopping(final String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}

package service.model;

import lombok.Getter;

@Getter
public enum PizzaSize {
    SMALL("small"),
    MEDIUM("medium"),
    LARGE("large");

    private final String text;

    PizzaSize(final String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}

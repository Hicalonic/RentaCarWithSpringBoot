package academy.mindswap.rentacar.model;

public enum Role {
    USER("USER"),
    ADMIN("ADMIN");

    public final String value;

     Role(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}

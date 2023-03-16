package academy.mindswap.rentacar.model;

public enum Role {
    EMPLOYEE("EMPLOYEE"),
    CLIENT("CLIENT"),
    ADMIN("ADMIN");

    public final String value;

     Role(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}

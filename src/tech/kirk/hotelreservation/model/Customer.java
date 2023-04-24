package tech.kirk.hotelreservation.model;

import java.util.regex.Pattern;

public class Customer {
    private String firstName;
    private String lastName;
    private String email;
    private final String emailRegex = "^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
    private final Pattern pattern = Pattern.compile(emailRegex);

    public Customer(String firstName, String lastName, String email) {
        if(!pattern.matcher(email).matches()) {
            throw new IllegalArgumentException("Error, invalid email address");
        }
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString(){
        return String.format("Name: %s %s \nEmail: %s", getFirstName(), getLastName(), getEmail());
    }
}

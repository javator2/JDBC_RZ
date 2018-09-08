package model;

import com.google.common.base.Preconditions;

public class Student {
    private int id;
    private String firstname;
    private String lastname;

    public Student(int id, String firstname, String lastname) {
        Preconditions.checkNotNull(firstname);
        Preconditions.checkNotNull(lastname);
        Preconditions.checkArgument(id >= 0);

        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public int getId() {
        return id;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }
}

import java.io.Serializable;

public class User implements Serializable {
    private String ID;
    private String firstName;
    private String lastName;
    private Occupation occupation;
    private int age;

    public User(String ID, String firstName, String lastName, Occupation occupation, int age) {
        try {
            this.ID = ID;
        } catch (Exception var7) {
            System.out.println("Invalid ID.");
        }

        this.firstName = firstName;
        this.lastName = lastName;
        this.occupation = occupation;
        this.age = age;
    }

    public String getID() {
        return this.ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Occupation getOccupation() {
        return this.occupation;
    }

    public void setOccupation(Occupation occupation) {
        this.occupation = occupation;
    }

    public int getAge() {
        return this.age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String toString() {
        return "User{ID=" + this.ID + ", firstName='" + this.firstName + "', lastName='" + this.lastName + "', occupation=" + this.occupation + ", age=" + this.age + "}";
    }
}


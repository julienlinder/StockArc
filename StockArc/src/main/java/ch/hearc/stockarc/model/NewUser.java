package ch.hearc.stockarc.model;

/**
 * Interface that represent a NewUser
 * 
 * @author Alexandre Bianchi
 */

public interface NewUser {

    /**
     * Get the email of the new user.
     * 
     * @return The day
     */
    String getEmail();

    void setEmail(String email);

    /**
     * Get if the user in an admin.
     * 
     * @return Boolean
     */
    Boolean getIsAdmin();

    void setIsAdmin(Boolean isAdmin);

    /**
     * Get the existing person.
     * 
     * @return String <code>"Yes"</code> if the new user is based on a person;
     *         <code>"No"</code> otherwise.
     */
    String getExistingPerson();

    void setExistingPerson(String existingPerson);

    /**
     * Get the existing person.
     * 
     * @return The person
     */
    Person getPerson();

    void setPerson(Person person);
}

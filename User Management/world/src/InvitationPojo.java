public class InvitationPojo {
    private String username;
    private String invitation;
    private String designation; // Add this field for designation

    // Getters and Setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getInvitation() {
        return invitation;
    }

    public void setInvitation(String invitation) {
        this.invitation = invitation;
    }

    public String getDesignation() {
        return designation; // Getter for designation
    }

    public void setDesignation(String designation) {
        this.designation = designation; // Setter for designation
    }
}

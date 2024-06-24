package inventoryServlet;

public class SignUpPojo {
    private String username;
    private String email;
    private String password;

    // Default constructor
    public SignUpPojo() {
        
    }

    // Getters and setters for username, email, and password
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

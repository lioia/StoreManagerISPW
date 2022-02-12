package com.shoppingpoint.model.user;

public abstract class User {
    private String username;
    private String email;
    private String password;

    public String getUsername() { return username; }
    public String getEmail() { return email; }
    public String getPassword() { return password; }

//  protected per Information Hiding (altrimenti si potrebbe cambiare direttamente il valore senza passare dal DB)
    protected void setUsername(String user) { username = user; }
    protected void setEmail(String mail) { email = mail; }
    protected void setPassword(String pass) { password = pass; }
}

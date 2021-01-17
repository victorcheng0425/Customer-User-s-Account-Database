package model;

//This Password Class is used for password encryption.
//When user set the password for their account, our application will do encryption before storing into database
//This Class also contain getPassword method(Call decryption method), to let customer object verify user password
public class Password {
    private String password;

    //EFFECTS: Transfer the password to encryption function
    public Password(String beforeE) {
        passwordEncryption(beforeE);
    }

    //EFFECTS: Don't encrypt the password because it's been encrypted, just store it.
    //Whatever parameter just for overriding function
    public Password(String password, boolean whatever) {
        this.password = password;
    }

    //MODIFIES: this
    //Encrypt the password and save it
    private void passwordEncryption(String beforeE) {
        char[] turnChar = beforeE.toCharArray();
        for (int i = 0; i < beforeE.length(); i++) {
            turnChar[i] += i;
        }
        password = String.valueOf(turnChar);
    }

    //EFFECTS: Decrypt the password and return it as a String
    private String passwordDecryption() {
        char[] turnChar = password.toCharArray();
        for (int i = 0; i < password.length(); i++) {
            turnChar[i] -= i;
        }
        return String.valueOf(turnChar);
    }

    //EFFECTS: Get password from decrypt function as a String and return it
    public String getPassword() {
        return passwordDecryption();
    }

    //EFFECTS: Get Encrypted password
    public String getPasswordEncrypted() {
        return password;
    }

    //MODIFIES: this
    //EFFECTS: Reset password and do encryption
    public void resetPassword(String password) {
        passwordEncryption(password);
    }
}

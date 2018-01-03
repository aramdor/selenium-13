package tests.user.registration;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class EmailObject {
    String emailName;
    String emailDomain;
    int increment;
    String emailWithIncrement;
    String password;

    public String getEmailName() {
        return emailName;
    }

    public int getIncrement() {
        return increment;
    }

    public String getEmailWithIncrement() {
        return emailWithIncrement;
    }

    public void setEmailName(String emailName) {
        this.emailName = emailName;
    }

    public void setIncrement(int increment) {
        this.increment = increment;
    }

    public void setEmailWithIncrement() {
        this.emailWithIncrement = getEmailName() + getIncrement() + getEmailDomain();
    }

    public String getEmailDomain() {
        return emailDomain;
    }

    public void setEmailDomain(String emailDomain) {
        this.emailDomain = emailDomain;
    }

    public void setDefaultEmail(int increment) {
        this.emailName = "agator";
        this.increment = increment;
        this.emailDomain = "@gmail.com";
        setEmailWithIncrement();
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }
}

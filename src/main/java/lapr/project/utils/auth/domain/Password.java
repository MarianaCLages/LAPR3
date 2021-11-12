package lapr.project.utils.auth.domain;



import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

public class Password {

    private final String password;

    public Password(String password) {
        if (!validate(password))
            throw new IllegalArgumentException("Invalid Password");
        this.password = password;
    }

    private boolean validate(String password) {
        return !StringUtils.isBlank(password);
        // Check for other invalid criteria here

        //
    }


    public boolean checkPassword(String pwd) {
        return !StringUtils.isBlank(pwd);
    }


    @Override
    public int hashCode() {
        int hash = 7;
        hash = 7 * hash + this.password.hashCode();
        return hash;
    }

    @Override
    public boolean equals(Object o) {
        // Inspired in https://www.sitepoint.com/implement-javas-equals-method-correctly/

        // self check
        if (this == o)
            return true;
        // null check
        if (o == null)
            return false;
        // type check and cast
        if (getClass() != o.getClass())
            return false;
        // field comparison
        Password obj = (Password) o;
        return Objects.equals(this.password, obj.password);
    }


}

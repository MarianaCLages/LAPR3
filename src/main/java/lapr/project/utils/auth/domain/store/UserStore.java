package lapr.project.utils.auth.domain.store;

import lapr.project.utils.auth.domain.Email;
import lapr.project.utils.auth.domain.Password;
import lapr.project.utils.auth.domain.User;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Optional;
import java.util.Set;

public class UserStore {
    private Set<User> store = new HashSet();

    public UserStore() {
    }

    public User create(String name, String email, String password) {
        return new User(new Email(email), new Password(password), name);
    }

    public boolean add(User user) {
        return user != null && !this.exists(user) ? this.store.add(user) : false;
    }

    public boolean remove(User user) {
        return user != null ? this.store.remove(user) : false;
    }

    public Optional<User> getById(String email) {
        return this.getById(new Email(email));
    }

    public Optional<User> getById(Email email) {
        Iterator var2 = this.store.iterator();

        User user;
        do {
            if (!var2.hasNext()) {
                return Optional.empty();
            }

            user = (User) var2.next();
        } while (!user.hasId(email));

        return Optional.of(user);
    }

    public boolean exists(String email) {
        Optional<User> result = this.getById(email);
        return result.isPresent();
    }

    public boolean exists(Email email) {
        Optional<User> result = this.getById(email);
        return result.isPresent();
    }

    public boolean exists(User user) {
        return this.store.contains(user);
    }
}
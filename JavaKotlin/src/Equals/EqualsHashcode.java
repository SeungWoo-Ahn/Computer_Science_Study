package Equals;

import java.util.Objects;

class User {
    public String name;

    public User(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof User user)) return false;
        return Objects.equals(name, user.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}

class Main {
    public static void main(String[] args) {
        User user1 = new User("SeungWoo");
        User user2 = new User("SeungWoo");
        System.out.println(user1.hashCode()); // 1522281946
        System.out.println(user2.hashCode()); // 1522281946
        System.out.println(user1.equals(user2)); // true
    }
}

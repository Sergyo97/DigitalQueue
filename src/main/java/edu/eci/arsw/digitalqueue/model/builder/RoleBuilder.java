package edu.eci.arsw.digitalqueue.model.builder;

import edu.eci.arsw.digitalqueue.model.Role;

public final class RoleBuilder {
    private Long id;
    private String name;

    private RoleBuilder() {
    }

    public static RoleBuilder aRole() {
        return new RoleBuilder();
    }

    public RoleBuilder withId(Long id) {
        this.id = id;
        return this;
    }

    public RoleBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public Role build() {
        Role role = new Role();
        role.setName(name);
        return role;
    }
}

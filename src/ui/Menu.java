package ui;

import service.UserSystem;

public abstract class Menu {
    protected final UserSystem userSystem;

    protected Menu(UserSystem userSystem) {
        this.userSystem = userSystem;
    }

    public abstract boolean show();
}
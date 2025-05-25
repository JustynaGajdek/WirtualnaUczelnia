package pl.wsiz.view;

public enum AdministratorMenuItem {
    USER_LIST(1, "lista użytkowników"),
    ADD_USER(2, "Dodaj nowego studenta"),
    ADD_TEACHER(3, "Dodaj nowego nauczyciela"),
    ADD_ADMIN(4, "Dodaj nowego administratora"),
    EXIT(5, "wyjście z programu");

    private int number;
    private String descriptionPl;

    AdministratorMenuItem(int number, String descriptionPl) {
        this.number = number;
        this.descriptionPl = descriptionPl;
    }

    public int getNumber() {
        return number;
    }

    public String getDescriptionPl() {
        return descriptionPl;
    }

}

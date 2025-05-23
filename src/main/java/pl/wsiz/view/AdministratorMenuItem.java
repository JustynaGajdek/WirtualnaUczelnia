package pl.wsiz.view;

public enum AdministratorMenuItem {
    USER_LIST(1, "lista użytkowników"),
    EXIT(2, "wyjście z programu");

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

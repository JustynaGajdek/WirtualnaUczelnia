# Wirtualna Uczelnia – Projekt zaliczeniowy

Repozytorium zawiera projekt rozwijany w ramach przedmiotu **Języki i metody programowania – cz. 2**.

## Opis

Celem projektu jest symulacja prostego systemu zarządzania użytkownikami uczelni. Aplikacja pozwala na przechowywanie i przetwarzanie informacji o użytkownikach różnych typów:

- `Administrator`
- `Teacher`
- `Student`

Dane użytkowników zapisywane są w pliku JSON. Projekt rozwijany jest etapami podczas kolejnych laboratoriów.

## Technologie

- Java 23
- JUnit 5
- Maven
- JSON (do przechowywania danych użytkowników)

## Uruchamianie testów

Uruchomienie testów możliwe jest na dwa sposoby:

### 1. Z poziomu terminala (Maven)

```
mvn test
```
### 2. Z poziomu IntelliJ IDEA
- Kliknij prawym przyciskiem na katalog `src/test/java/pl/wsiz`
- Wybierz opcję `Run 'All Tests'` lub `Run Tests`


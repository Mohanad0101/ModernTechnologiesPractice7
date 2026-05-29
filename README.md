main: https://aliebraheem-fun.github.io/Modern-Programming-Technologies/#/practice7

# Практическая работа №7 Spring Framework и Spring Boot

Учебный репозиторий для выполнения практической работы по Spring Framework и Spring Boot.

В проекте уже есть рабочее приложение. Основные учебные фрагменты кода выделены специальными маркерами:

```java
// ▼ ВАШ КОД ЗДЕСЬ ▼
// учебный фрагмент кода
// ▲ КОНЕЦ ВАШЕГО КОДА ▲
```

Для HTML/Thymeleaf используются такие же маркеры:

```html
<!-- ▼ ВАШ КОД ЗДЕСЬ ▼ -->
<!-- учебный фрагмент шаблона -->
<!-- ▲ КОНЕЦ ВАШЕГО КОДА ▲ -->
```

Задача студента — найти эти блоки, изучить код, запустить приложение, собрать результаты работы и ответить на вопросы.

---

## Темы работы

- Spring Boot project structure
- Maven и `pom.xml`
- Inversion of Control и Dependency Injection
- REST API
- Spring Data JPA и H2 database
- Thymeleaf web interface
- Spring Security
- AOP logging
- DTO, validation и global exception handling
- Transactions
- Unit / Web MVC tests

---

## Требования

- Java 21
- Maven 3.9+ или Maven Tool Window в IntelliJ IDEA
- Доступ в интернет при первом запуске для загрузки Maven-зависимостей

---

## Запуск в Windows PowerShell

Откройте терминал в корне проекта — в папке, где находятся `pom.xml` и `mvnw.cmd`.

Проверка папки:

```powershell
dir pom.xml, mvnw.cmd
```

Запуск тестов:

```powershell
.\mvnw.cmd clean test
```

Запуск приложения:

```powershell
.\mvnw.cmd spring-boot:run
```

Если Maven установлен глобально, можно использовать:

```powershell
mvn clean test
mvn spring-boot:run
```

---



## Запуск через IntelliJ IDEA Community Edition

1. Откройте папку проекта в IntelliJ IDEA.
2. Убедитесь, что IntelliJ распознал файл `pom.xml`.
3. Откройте Maven Tool Window.
4. Запустите `clean`, затем `test`.
5. Для запуска приложения используйте Maven goal:

```text
spring-boot:run
```

---

## Адреса приложения

После запуска приложение доступно по адресу:

```text
http://localhost:8080
```

Основные URL:

```text
GET http://localhost:8080/api/greet/World
GET http://localhost:8080/api/students
GET http://localhost:8080/api/students/1
GET http://localhost:8080/api/students/search?q=sash&by=name
POST http://localhost:8080/api/students
PUT http://localhost:8080/api/students/1
DELETE http://localhost:8080/api/students/1
```

Web UI:

```text
http://localhost:8080/students
```

H2 console:

```text
http://localhost:8080/h2-console
```

---

## Логины

```text
user / password
admin / password
```

Пользователь `user` может просматривать данные.

Пользователь `admin` может создавать, изменять и удалять данные.

---

## Демонстрационные студенты

При первом запуске база данных H2 заполняется такими студентами:

| Имя | Фамилия |
|---|---|
| Ekatrena | Wilson |

---

## Команды для проверки

### 1. Greeting endpoint

```bash
curl http://localhost:8080/api/greet/World
```

Ожидаемый результат:

```text
Hello, World!
```

### 2. Получить список студентов

```bash
curl -u user:password http://localhost:8080/api/students
```

### 3. Создать студента

```bash
curl -u admin:password -X POST http://localhost:8080/api/students ^
  -H "Content-Type: application/json" ^
  -d "{\"name\":\"Sash\",\"surname\":\"Brown\"}"
```

### 4. Проверить запрет удаления для обычного пользователя

```bash
curl -i -u user:password -X DELETE http://localhost:8080/api/students/1
```

Ожидаемый результат: `HTTP 403 Forbidden`.

### 5. Проверить validation

```bash
curl -i -u admin:password -X POST http://localhost:8080/api/students ^
  -H "Content-Type: application/json" ^
  -d "{\"name\":\"Sash\",\"surname\":\"Brown\"}"
```

Ожидаемый результат: `HTTP 400` и JSON с `fieldErrors`.

---

## Что нужно сдать

1. Исходный код проекта.
2. Заполненные файлы `answers/*.md`.
3. Заполненные файлы `results/*.txt`.
4. Скриншот страницы `/students`.
5. Скриншот успешного запуска тестов.

---


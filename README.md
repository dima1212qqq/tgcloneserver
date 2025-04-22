# 📡 TgServer (Spring Boot WebSocket)

**TgServer** — это серверная часть для десктопного мессенджера на Java, реализованная на базе Spring Boot. Сервер предоставляет WebSocket-канал для обмена сообщениями между клиентами, а также REST API для сервисных запросов. Для хранения данных используется встраиваемая база H2 и JPA.

---

## 📦 Технологии и библиотеки

- 🌱 [Spring Boot](https://spring.io/projects/spring-boot) — фреймворк для быстрой разработки серверных приложений.
- 📡 [Spring WebSocket](https://spring.io/guides/gs/messaging-stomp-websocket/) — поддержка WebSocket-соединений.
- 📝 [Spring Data JPA](https://spring.io/projects/spring-data-jpa) — работа с базами данных через JPA.
- 🛠️ [H2 Database](https://www.h2database.com/) — встраиваемая база данных для разработки и тестирования.
- 🔧 [Lombok](https://projectlombok.org/) — сокращение шаблонного кода.
- 📝 [org.json](https://github.com/stleary/JSON-java) — работа с JSON.
- 📦 [Spring Boot DevTools](https://docs.spring.io/spring-boot/docs/current/reference/html/using.html#using.devtools) — горячая перезагрузка и инструменты для разработки.
- 🧪 [Spring Boot Test](https://spring.io/projects/spring-boot) — библиотека для модульного тестирования.

---

## 📜 Структура Maven-проекта

```text
src/
 └── main/
      └── java/
          └── ru/
              └── dovakun/
                  └── TgServerApplication.java
pom.xml

```

Как собрать проект
mvn clean package

Как запустить сервер
java -jar target/TgServer-0.0.1-SNAPSHOT.jar


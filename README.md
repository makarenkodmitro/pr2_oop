# Система управління рестораном (MVC)

Імітаційний прототип системи управління рестораном з веб-інтерфейсом, що демонструє архітектуру **MVC (Model-View-Controller)** та взаємодію між відвідувачами, офіціантами та шеф-кухарем. Проект розроблений на Java з використанням Maven та фреймворку Javalin для веб-сервера.

## Призначення проєкту


# Система управління рестораном (MVC)

Імітаційний прототип системи управління рестораном з веб-інтерфейсом. Проєкт демонструє архітектуру **MVC (Model-View-Controller)**, Dependency Injection з Guice та простий REST API на Javalin.

## Проєкт — коротко

Демонструє повний робочий цикл: створення замовлення (Customer → Order → OrderItem), підтвердження офіціантом, приготування шеф-кухарем та оплата. Дані зберігаються та читаються через `OrderService` і доступні через REST API, яке обслуговує `RestaurantWebView`.

## Структура (коротко)

```
restaurant/
├── src/main/java/com/restaurant/
│   ├── model/        # Order, OrderItem, Table, ServiceParticipant
│   ├── service/      # OrderService, PaymentService
│   ├── controller/   # RestaurantController
│   ├── view/         # RestaurantWebView, webserver/*
│   └── Restaurant.java (точка входу)
├── src/main/resources/ (index.html, script.js, style.css)
├── docs/             # UML-діаграми
└── pom.xml
```

## Компіляція та запуск

1) Скомпілювати проект:

```bash
cd restaurant
mvn clean compile
```

2) Запустити програму (консольна демонстрація + веб-сервер):

```bash
mvn exec:java -Dexec.mainClass="com.restaurant.Restaurant"
```

Після запуску:
- Консоль: демонстрація взаємодії між акторами
- Веб-сервер: `http://localhost:8080`
- REST API: `GET /api/orderitems` повертає JSON список позицій замовлення

## Тестування REST API

```bash
# Отримати всі позиції замовлення
curl http://localhost:8080/api/orderitems

# Приклад відповіді:
#[{"dish":"Борщ","quantity":1},{"dish":"Вареники","quantity":2}]
```

## Веб-інтерфейс

Веб-сторінка знаходиться в `src/main/resources/index.html`. Для локального перегляду відкрийте її через Live Server у VS Code або будь-який статичний сервер. Сторінка робить AJAX-запит до `GET /api/orderitems` і відображає `dish` та `quantity`.

## Основні класи та ролі

- `Order`, `OrderItem` — модель даних
- `OrderService` — доступ до БД (save/get)
- `RestaurantController` — контролер між View і Service
- `RestaurantWebView` — REST-інтерфейс + налаштування маршрутів
- `WebServer`/`JavalinWebServer` — інфраструктура HTTP

## Залежності (деталі в `pom.xml`)

- Google Guice — Dependency Injection
- Javalin — веб-фреймворк для REST API
- SQLite JDBC — приклад драйвера БД
- SLF4J — логування
- JUnit — тести

## Архітектурні патерни та зв'язки

- MVC: Model ← Controller ← View
- DI: Guice (використання `@Inject` і `RestaurantModule`)
- Абстракції: `WebServer`, `HttpContext` для відділення фреймворку
- Композиція: `Order *-- OrderItem`
- Агрегація: `Order o-- OrderService`

## UML-діаграми

Файли діаграм лежать у `docs/` (наприклад, `mvc.class.puml`). Вони показують класи, зв'язки (композиція, агрегація, реалізація) і REST-потік.

---

Якщо хочете, я додам у README приклади POST/PUT для створення позицій, приклади JSON-схем або інструкцію для Docker-контейнера. 
- [`class.puml`](docs/class.puml) - діаграма класів
- [`sequence.puml`](docs/sequence.puml) - діаграма послідовності
- [`state.puml`](docs/state.puml) - діаграма станів
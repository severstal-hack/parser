# Модуль парсинга

Программный модуль реализует парсинг со страницы извещения тендера и из документации
приложенной к тендеру. Проект умеет определять все продуктовые позиции (наименование 
, кол-во, ед. изм., цена, адрес места поставки).

Поступление данных в данный модуль реализовано как из модуля 1 - [Модуль происка тендеров](github.com/severstal-hack/searcher), 
так и из други сторонних API. 

## Требования системы

### Переменные среды

- `CLICKHOUSE_USERNAME` - Имя пользователя для доступа к Clickhouse
- `CLICKHOUSE_PASSWORD` - Имя пароль для доступа к Clickhouse
- `RMQ_USERNAME` - Имя пользователя для доступа к RabbitMQ
- `RMQ_PASSWORD` - Имя пароль для доступа к RabbitMQ

### Зависимости

- PlayWright
- ClickHouse
- RabbitMQ

## Запуск



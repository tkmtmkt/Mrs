Mrs
===

### データベースを起動する

```sh
$ docker-compose up
```


### データベースのスキーマを作成する

```sh
$ docker-compose exec db /bin/bash

$ psql -U mrs -f /Mrs/src/main/resources/schema.sql
$ psql -U mrs -f /Mrs/src/main/resources/data.sql
```


### アプリケーションを実行する

```sh
$ ./gradlew clean
$ ./gradlew bootRun
```

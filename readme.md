## Дипломный проект содержит набор автотестов на сайт https://lenta.com/


## Содержание:

- [Технологии и инструменты](#tools)
- [Тестовое покрытие](#cases)
- [Локальный запуск тестов](#localrun)
- [Запуск тестов в Jenkins](#jenkinsrun)
- [Как запускать](#howrun)
- [Запуск тестов из Allure, отчеты и уведомления отчёт](#report)


<a id="tools"></a>
### Технологии и инструменты:
<div align="center">
<a href="https://www.jetbrains.com/idea/"><img alt="InteliJ IDEA" height="45" src="images/logo/IntelliJ_IDEA.png" width="45"/></a>
<a href="https://www.android.com/"> <img src="https://cdn.jsdelivr.net/gh/devicons/devicon@latest/icons/android/android-original.svg" title="Android" alt="Android" width="45" height="45"/> </a> 
<a href="https://developer.android.com/studio"> <img src="https://cdn.jsdelivr.net/gh/devicons/devicon@latest/icons/androidstudio/androidstudio-original.svg" title="Android Studio" alt="Android Studio" width="45" height="45"/> </a> 
<a href="https://github.com/"><img alt="GitHub" height="45" src="images/logo/GitHub.png" width="45"/></a>  
<a href="https://www.java.com/"><img alt="Java" height="45" src="images/logo/Java_logo.png" width="45"/></a>
<a href="https://gradle.org/"><img alt="Gradle" height="45" src="images/logo/Gradle.png" width="45"/></a>  
<a href="https://junit.org/junit5/"><img alt="JUnit 5" height="45" src="images/logo/JUnit5.png" width="45"/></a>
<a href="https://selenide.org/"><img alt="Selenide" height="45" src="images/logo/Selenide.png" width="45"/></a>
<a href="https://www.browserstack.com/"> <img src="https://cdn.jsdelivr.net/gh/devicons/devicon@latest/icons/browserstack/browserstack-original.svg" title="Browserstack" alt="Browserstack" width="45" height="45"/> </a>
<a href="https://appium.io/"> <img src="images/logo/appium.png" title="Appium" alt="Appium" width="40" height="40"/> </a>
<a href="https://aerokube.com/selenoid/"><img alt="Selenoid" height="45" src="images/logo/Selenoid.png" width="45"/></a>
<a href="https://rest-assured.io/"><img alt="RestAssured" height="45" src="images/logo/RestAssured.png" width="45"/></a>
<a href="https://www.jenkins.io/"><img alt="Jenkins" height="45" src="images/logo/Jenkins.png" width="45"/></a>
<a href="https://github.com/allure-framework/"><img alt="Allure Report" height="45" src="images/logo/AllureReports.png" width="45"/></a>
<a href="https://qameta.io/"><img alt="Allure TestOps" height="45" src="images/logo/AllureTestOps.svg" width="45"/></a>
<a href="https://telegram.org/"><img alt="Telegram" height="45" src="images/logo/Telegram.png" width="45"/></a>
</div>

- #### Язык программирования Java
- #### Системы сборки и управления зависимостями Gradle
- #### Интегрированные среды разработки
  - IntelliJ IDEA
  - Android Studio
- #### Тестирование
  - JUnit 5 — фреймворк для модульного тестирования Java-приложений
  - Selenide — фреймворк для автоматизации тестирования веб-приложений. Упрощает работу с Selenium
  - Appium — инструмент для автоматизации тестирования мобильных приложений (iOS и Android)
  - RestAssured — библиотека для тестирования REST API
  - Selenoid — инструмент для запуска тестов в контейнерах Docker
- #### Инфраструктура и CI/CD
  - Jenkins — сервер для непрерывной интеграции и доставки (CI/CD)
  - BrowserStack — облачная платформа для тестирования веб- и мобильных приложений на реальных устройствах и браузерах.
- #### Отчеты и информирование
  - Allure Report — фреймворк для создания визуальных и детализированных отчетов о результатах тестирования.
  - Allure TestOps — платформа для управления тестами, интеграции с CI/CD и анализа результатов тестирования.
  - Telegram — мессенджер, в который направляются уведомления.
- #### Версионный контроль GitHub


<a id="cases"></a>
### Тестовое покрытие
- #### WEB
  - ##### Пользователь может работать с каталогом товаров
    - Успешный переход в каталог 1-го уровня
    - Успешный переход в каталог 2-го уровня
  - ##### Пользователь может авторизоваться через кнопку 'Войти'
    - Кнопка 'Получить код' не активна до ввода номера телефона
    - "Кнопка 'Получить код' активна после ввода номера телефона"
    - "Открыто окно 'Введите код'"
  - ##### Пользователь может найти товар
    - Успешный поиск товара через строку поиска
- #### API
  - ##### Пользователь не может авторизоваться если в запросе некорректные данные
    - Запрашиваемый media-type не поддерживается
    - Ошибка 403: Нет аккаунта с указанным номером телефона
  - ##### Неавторизованный пользователь может получить информацию о товаре
    - Успешное получение данных товара по SKU
    - Успешный поиск товара в каталоге
  - ##### Неавторизованный пользователь может получить информацию о магазинах
    - Успешное получение данных о магазине номер
    - Успешное получение данных о всех магазинах
    - Успешное получение информации о доставке
- #### MOBILE (Android)
  - ##### Пользователь может получить информацию о магазине и товарах в нем
    - Успешный выбор магазин через строку поиска
    - Успешный поиск товара через строку поиск
    - Успешный выбор товара через строку поиска

<a id="jenkinsrun"></a>
### Запуск тестов в Jenkins

```
https://jenkins.autotests.cloud/job/QaLenta
https://jenkins.autotests.cloud/job/QaLentaMobile
```

Запуск может выполняться как локально, так и удаленно на Selenoid сервере или через browserstack


<a id="howrun"></a>
### Как запускать
#### Локально Web
```
gradle clean ui -Dplatform=web
gradle clean ui -DenvironmentType=local -Dplatform=web
```
#### Удаленно Web
```
gradle clean ui -DenvironmentType=remote -Dplatform=web
```
#### Запуск API локально
```
gradle clean api -DenvironmentType=local -Dplatform=web
gradle clean api -DenvironmentType=local -Dplatform=remote
```
#### Реальная Мобилка
```
gradle clean android -DdeviceHost=real -DmobileOS=android
gradle clean android -Dplatform=mobile -DdeviceHost=real -DmobileOS=android -Ddevice=redmi9A
```

#### Эмулятор Мобилка
```
gradle clean android -Dplatform=mobile -DdeviceHost=emulation -DmobileOS=android -Ddevice=pixel4
```
#### BrowserStack Мобилка
```
gradle clean android -Dplatform=mobile -DdeviceHost=browserstack -DmobileOS=android -Ddevice=pixel6Pro
```

<a id="report"></a>
### Запуск тестов из Allure, отчеты и уведомления

### Allure TestOps
```
https://allure.autotests.cloud/project/4630/test-cases?treeId=9052
```
![allure1.png](images/screenshot/allure1.png)
![allure2.png](images/screenshot/allure2.png)


### Telegram
![allure3.png](images/screenshot/allure3.png)
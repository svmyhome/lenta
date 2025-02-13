## Дипломный проект содержит набор автотестов на сайт https://lenta.com/
  - ### WEB
  - ### API
  - ### MOBILE (Android) 

Запуск может выполняться ка локально, так и удаленно на selenoid сервере или через browserstack

Язык: Java
Для формирования отчетов используется Allure TestOps|Allure Report

Используемые технологии
Java, JUNIT5, Gradle, Senenide, RestAssured, Selenoid, Appium, Allure TestOps|Allure Report , Appium Inspector, SCRPY, Inte;jIdea, Android Studio


<img src="img.png" alt="Alt Text" style="width:20%; height:auto;">
<img src="img_1.png" alt="Alt Text" style="width:20%; height:auto;">
<img src="img_2.png" alt="Alt Text" style="width:20%; height:auto;">
<img src="img_3.png" alt="Alt Text" style="width:20%; height:auto;">
<img src="img_4.png" alt="Alt Text" style="width:20%; height:auto;">
<img src="img_5.png" alt="Alt Text" style="width:20%; height:auto;">
<img src="img_6.png" alt="Alt Text" style="width:20%; height:auto;">
<img src="img_7.png" alt="Alt Text" style="width:20%; height:auto;">
<img src="img_8.png" alt="Alt Text" style="width:20%; height:auto;">
<img src="img_10.png" alt="Alt Text" style="width:20%; height:auto;">


### Jenkins Job

```
https://jenkins.autotests.cloud/job/QaLenta/
```


### Запуск

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

<!---Пример кода-->
[![Typing SVG](https://readme-typing-svg.herokuapp.com?color=%2336BCF7&lines=Computer+science+student)](https://git.io/typing-svg)


## TODO

- [X] Запуск в Jenkins
  - [ ] переделать на paip
- [X] Подключить аллюр
- [X] Подключить telegramm
- [X] Дописать автотесты по UI
- [X] Дописать автотетсы по АПИ
- [X] Дописать автотесты по Mobile
- [ ] Сделать скриншоты и описание

## TODO

- [X] Запуск в Jenkins
    - [ ] переделать на paip
- [X] Подключить аллюр
- [X] Подключить telegramm
- [X] Дописать автотесты по UI
- [X] Дописать автотетсы по АПИ
- [X] Дописать автотесты по Mobile
- [ ] Сделать скриншоты и описание

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
#### Запуск Апи локально
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

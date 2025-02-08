
## TODO

- [X] Запуск в Jenkins
    - [ ] переделать на paip
- [X] Подключить аллюр
- [ ] Подключить telegramm
- [ ] Дописать автотесты по UI
- [ ] Дописать автотетсы по АПИ
- [ ] Дописать автотесты по Mobile
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
gradle clean android -DdeviceHost=real -DmobileOS=android -Ddevice=redmi9A
```
#### Эмулятор Мобилка
```
gradle clean android -DdeviceHost=emulation -DmobileOS=android -Ddevice=pixel4
```
#### BrowserStack Мобилка
```
gradle clean android -DdeviceHost=browserstack -DmobileOS=android -Ddevice=pixel6Pro
```

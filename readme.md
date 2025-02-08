
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
gradle android -DdeviceHost=real -DmobileOS=android
gradle android -DdeviceHost=real -DmobileOS=android -Ddevice=redmi9A
```
#### Эмулятор Мобилка
```
gradle android -DdeviceHost=emulation -DmobileOS=android -Ddevice=pixel4
```
#### BrowserStack Мобилка
```
gradle android -DdeviceHost=browserstack -DmobileOS=android -Ddevice=pixel6Pro
```

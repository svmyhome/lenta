
### Запуск

#### Локально Web
```
gradle clean ui
gradle clean ui -DenvironmentType=local
```
#### Удаленно Web
```
gradle clean ui -DenvironmentType=remote
```



#### Реальная Мобилка
```
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
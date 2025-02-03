
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



#### Локально Мобилка
```
gradle android -DdeviceHost=real -DmobileOS=android -Ddevice=redmi9A
```
#### Удаленно Мобилка
```
gradle android -DdeviceHost=emulation -DmobileOS=android -Ddevice=pixel4
```
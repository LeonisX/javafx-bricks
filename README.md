# javafx-bricks

Building bricks for JavaFX applicationt.

Don't read tons of manuals. Just copy necessary bricks and build your RIA application.

Все проекты рассчитаны на Java 8 (лямбды)

Если всё-таки очень хочется запустить проект в Java 7, то замените лямбду метода `btn.setOnAction` на этот код:
```java
        btn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                System.out.println("Hello World!");
            }
        });
```

Так же придётся импортировать ещё два файла:

```java
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
```
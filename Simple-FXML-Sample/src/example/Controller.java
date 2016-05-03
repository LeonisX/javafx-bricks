package example;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;

public class Controller {

    @FXML
    private Button button;

    @FXML
    public void handleButtonAction(ActionEvent actionEvent) {
        System.out.println("Clicked!");
        button.setText("Hello World!");
    }
}

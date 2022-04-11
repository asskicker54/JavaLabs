package viewmodel;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import model.Model;

public class ViewModel {
    @FXML
    TextField t1;

    @FXML
    TextField t2;

    @FXML
    Button button;

    private boolean swapped = false;

    public void onClick() {
        String[] text = Model.swap(t1.getText(), t2.getText());
        t1.setText(text[0]);
        t2.setText(text[1]);

        if (swapped) {
            button.setText(">");
        } else {
            button.setText("<");
        }
        swapped = !swapped;
    }
}
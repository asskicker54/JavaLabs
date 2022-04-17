package viewmodel;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;


public class ViewModel {
    @FXML
    CheckBox cb1, cb2, cb3;

    @FXML
    Hyperlink hl;

    @FXML
    PasswordField pwf;

    @FXML
    ProgressIndicator pi;


    public void onClick(MouseEvent event) {
        if (event.getSource() == cb1) {
            hl.setVisible(cb1.isSelected());
        } else if (event.getSource() == cb2) {
            pwf.setVisible(cb2.isSelected());
        } else if (event.getSource() == cb3) {
            pi.setVisible(cb3.isSelected());
        }
    }

}
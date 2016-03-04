package accaunt.lab.mvLab.com.Catalogs.Reagents;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

public class ReagentCatalogListFormActionHandler implements EventHandler<ActionEvent> {

    @Override
    public void handle(ActionEvent event) {
        if (event.getSource() instanceof Button) {
            Button source = (Button) event.getSource();
            String buttonID = source.idProperty().getValue();
            if (buttonID == "AddReagent") {
                //ReagentCatalogListForm.getLabDB().addReagentCatalogElement(new ReagentCatalog(3, "new2", "brend new"));
                ReagentCatalogElementForm.display();
                ReagentCatalogListForm.update();
            }
        }
    }
}

package com.mvLab.lab.accaunt.documents;

import com.mvLab.lab.accaunt.catalogs.Reagents.ReagentCatalog;
import com.mvLab.lab.accaunt.windows.MV_Window;
import javafx.scene.control.TableView;

public class DocumentListForm extends MV_Window {
    public DocumentListForm(String title, int windowWidth, int windowHeight) {
        super(title, windowWidth, windowHeight);
        fillElements();
    }

    private final void fillElements() {
        TableView<ReagentCatalog> docsTable = new TableView<ReagentCatalog>();
//        reagentTable.setItems(getCatalogData());
//        reagentTable.getColumns().addAll(idColumn, nameColumn, descColumn, uuidColumn);

//        reagentTable.setRowFactory(new ReagentCatalogDblClickListener());
//        reagentTable.autosize();
//
//        addCenterElement(reagentTable, 0, 0);
    }
}

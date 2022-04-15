package ru.buba.demoex.UI;

import ru.buba.demoex.entity.ProductEntity;
import ru.buba.demoex.manager.ProductManager;
import ru.buba.demoex.util.BaseForm;
import ru.buba.demoex.util.DialogUtil;

import javax.swing.*;
import javax.xml.crypto.Data;
import java.sql.DriverManager;
import java.sql.SQLException;

public class CreateForm extends BaseForm {
    private JTextField titleField;
    private JTextField productTypeField;
    private JTextField articleNumberField;
    private JTextField imageField;
    private JSpinner productPersonCountSpinner;
    private JSpinner productionWorkshopNumberSpinner;
    private JSpinner minCostForAgentSpinner;
    private JButton addButton;
    private JButton backButton;
    private JPanel mainPanel;

    public CreateForm() {
        super(800, 600);
        setContentPane(mainPanel);

        InitButtons();

        setVisible(true);

    }

    private void InitButtons () {

        addButton.addActionListener(e -> {

            String title = titleField.getText();
            if (title.isEmpty() || title.length() > 50) {
                DialogUtil.showError(this, "Error");
            }

            String productType = productTypeField.getText();
            if (productType.isEmpty() || productType.length() > 50) {
                DialogUtil.showError(this, "Error");
            }

            String articleNumber = articleNumberField.getText();
            if (articleNumber.isEmpty() || articleNumber.length() > 50) {
                DialogUtil.showError(this, "Error");
            }

            String image = imageField.getText();
            if (image.isEmpty() || image.length() > 50) {
                DialogUtil.showError(this, "Error");
            }

            int productPersonCount = (int) productPersonCountSpinner.getValue();
            if(productPersonCount <= 0) {
                DialogUtil.showError(this, "Error");
            }

            int productionWorkshopNumber = (int) productionWorkshopNumberSpinner.getValue();
            if(productionWorkshopNumber <= 0) {
                DialogUtil.showError(this, "Error");
            }

            int minCostForAgent = (int) minCostForAgentSpinner.getValue();
            if(minCostForAgent <= 0) {
                DialogUtil.showError(this, "Error");
            }

            ProductEntity entity = new ProductEntity(title, productType, articleNumber, image, productPersonCount,
                    productionWorkshopNumber, minCostForAgent
            );

            try {
                ProductManager.insert(entity);
            } catch (SQLException ex) {
                DialogUtil.showError(this, "error");
                ex.printStackTrace();
                return;
            }

            DialogUtil.showInfo(this, "Success");
            dispose();
            new MainForm();

        });

        backButton.addActionListener(e -> {
            dispose();
            new MainForm();
        });

    }
}

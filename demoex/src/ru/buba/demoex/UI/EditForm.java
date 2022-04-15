package ru.buba.demoex.UI;

import ru.buba.demoex.UI.MainForm;
import ru.buba.demoex.entity.ProductEntity;
import ru.buba.demoex.manager.ProductManager;
import ru.buba.demoex.util.BaseForm;
import ru.buba.demoex.util.DialogUtil;

import javax.swing.*;
import java.sql.SQLException;

public class EditForm extends BaseForm {
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
    private JTextField idField;
    private JButton deleteButton;

    private ProductEntity entity;

    public EditForm(ProductEntity entity) {
        super(800, 600);
        this.entity = entity;

        setContentPane(mainPanel);

        InitFields();
        InitButtons();

        setVisible(true);
    }

    private void InitFields() {
        idField.setEditable(false);
        idField.setText(String.valueOf(entity.getId()));
        titleField.setText(entity.getTitle());
        productTypeField.setText(entity.getProductType());
        articleNumberField.setText(entity.getArticleNumber());
        imageField.setText(entity.getImage());
        productPersonCountSpinner.setValue(entity.getProductPersonCount());
        productionWorkshopNumberSpinner.setValue(entity.getProductionWorkshopNumber());
        minCostForAgentSpinner.setValue(entity.getMinCostForAgent());
    }

    private void InitButtons () {

        addButton.addActionListener(e -> {

            String title = titleField.getText();
            if (title.isEmpty() || title.length() > 50) {
                DialogUtil.showError(this, "Error");
                return;
            }

            String productType = productTypeField.getText();
            if (productType.isEmpty() || productType.length() > 50) {
                DialogUtil.showError(this, "Error");
                return;
            }

            String articleNumber = articleNumberField.getText();
            if (articleNumber.isEmpty() || articleNumber.length() > 50) {
                DialogUtil.showError(this, "Error");
                return;
            }

            String image = imageField.getText();
            if (image.isEmpty() || image.length() > 50) {
                DialogUtil.showError(this, "Error");
                return;
            }

            int productPersonCount = (int) productPersonCountSpinner.getValue();
            if(productPersonCount <= 0) {
                DialogUtil.showError(this, "Error");
                return;
            }

            int productionWorkshopNumber = (int) productionWorkshopNumberSpinner.getValue();
            if(productionWorkshopNumber <= 0) {
                DialogUtil.showError(this, "Error");
                return;
            }

            int minCostForAgent = (int) minCostForAgentSpinner.getValue();
            if(minCostForAgent <= 0) {
                DialogUtil.showError(this, "Error");
                return;
            }

            entity.setTitle(title);
            entity.setProductType(productType);
            entity.setArticleNumber(articleNumber);
            entity.setImage(image);
            entity.setProductPersonCount(productPersonCount);
            entity.setProductionWorkshopNumber(productionWorkshopNumber);
            entity.setMinCostForAgent(minCostForAgent);

            try {
                ProductManager.update(entity);
                DialogUtil.showInfo(this, "Success");
                dispose();
                new MainForm();
            } catch (SQLException ex) {
                DialogUtil.showError(this, "error");
                ex.printStackTrace();
            }

        });

        backButton.addActionListener(e -> {
            dispose();
            new MainForm();
        });

        deleteButton.addActionListener(e -> {
            if(JOptionPane.showConfirmDialog(
                    this,
                    "Вы уверены?",
                    "Подтверждение",
                    JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                try {
                    ProductManager.delete(entity.getId());
                    DialogUtil.showInfo(this, "Success");
                    dispose();
                    new MainForm();
                } catch (Exception ex) {
                    ex.printStackTrace();
                    DialogUtil.showError(this, "Error");
                }
            }
        });

    }
}

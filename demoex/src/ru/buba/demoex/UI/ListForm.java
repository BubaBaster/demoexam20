package ru.buba.demoex.UI;

import ru.buba.demoex.entity.ProductEntity;
import ru.buba.demoex.manager.ProductManager;
import ru.buba.demoex.util.BaseForm;
import ru.buba.demoex.util.DialogUtil;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class ListForm extends BaseForm {
    private JTextArea textArea1;
    private JButton backButton;
    private JPanel mainPanel;

    public ListForm() {
        super(1000, 1000);

        setContentPane(mainPanel);

        InitText();
        InitButtons();

        setVisible(true);
    }

    public void InitText() {

        textArea1.setEditable(false);

        try {
            List<ProductEntity> list = ProductManager.selectAll();

            String s = "";
            for (ProductEntity entity : list) {
                s += entity;
                s += '\n';
            }

            textArea1.setText(s);

        } catch (Exception ex) {
            DialogUtil.showError(this, "Error");
            ex.printStackTrace();
        }
    }

    public void InitButtons() {
        backButton.addActionListener(e -> {
            dispose();
            new MainForm();
        });
    }
}

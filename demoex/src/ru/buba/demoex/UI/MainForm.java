package ru.buba.demoex.UI;

import ru.buba.demoex.entity.ProductEntity;
import ru.buba.demoex.manager.ProductManager;
import ru.buba.demoex.util.BaseForm;
import ru.buba.demoex.util.DialogUtil;

import javax.swing.*;
import javax.xml.crypto.Data;
import java.sql.SQLException;

public class MainForm extends BaseForm {
    private JButton listButton;
    private JButton addButton;
    private JButton editButton;
    private JPanel mainPanel;

    public MainForm() {
        super(600, 600);

        setContentPane(mainPanel);

        InitButtons();

        setVisible(true);
    }

    public void InitButtons(){

        listButton.addActionListener(e -> {
            dispose();
            new ListForm();
        });

        addButton.addActionListener(e -> {
            dispose();
            new CreateForm();
        });

        editButton.addActionListener(e -> {

            String s = JOptionPane.showInputDialog(this, "Введите id продукта", "Ввод", JOptionPane.QUESTION_MESSAGE);
            if (s == null) {
                return;
            }

            int id = -1;
            try {
                id = Integer.parseInt(s);
            } catch (Exception ex) {
                DialogUtil.showError(this,"Error");
                return;
            }

            ProductEntity product = null;

            try {
                product = ProductManager.selectById(id);
            } catch (SQLException ex) {
                ex.printStackTrace();
                DialogUtil.showError(this, "error");
                return;
            }

            if (product == null) {
                DialogUtil.showError(this, "error");
            }

            dispose();
            new EditForm(product);


        });

    }
}

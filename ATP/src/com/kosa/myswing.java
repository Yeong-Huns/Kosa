/*
 * Created by JFormDesigner on Tue Apr 30 16:54:20 KST 2024
 */

package com.kosa;

import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

/**
 * @author KOSA
 */
public class myswing extends JPanel {
    private JPanel panel1;
    private JLabel pwdLabel;
    private JLabel idLabel;
    private JTextField idTbox;
    private JTextField pwdTbox;

    public myswing() {
        this.initComponents();
    }

    private void initComponents() {
        this.panel1 = new JPanel();
        this.pwdLabel = new JLabel();
        this.idLabel = new JLabel();
        this.idTbox = new JTextField();
        this.pwdTbox = new JTextField();
        this.setBorder(new CompoundBorder(new TitledBorder(new EmptyBorder(0, 0, 0, 0), "JFormDesigner Evaluation", 2, 5, new Font("Dialog", 1, 12), Color.red), this.getBorder()));
        this.addPropertyChangeListener(new PropertyChangeListener() {
            public void propertyChange(PropertyChangeEvent e) {
                if ("border".equals(e.getPropertyName())) {
                    throw new RuntimeException();
                }
            }
        });
        this.setLayout((LayoutManager)null);
        this.panel1.setLayout((LayoutManager)null);
        this.pwdLabel.setText("ID");
        this.panel1.add(this.pwdLabel);
        this.pwdLabel.setBounds(0, 0, 75, 45);
        this.idLabel.setText("PASSWORD");
        this.panel1.add(this.idLabel);
        this.idLabel.setBounds(0, 50, 75, 45);
        this.panel1.add(this.idTbox);
        this.idTbox.setBounds(new Rectangle(new Point(80, 10), this.idTbox.getPreferredSize()));
        this.panel1.add(this.pwdTbox);
        this.pwdTbox.setBounds(80, 55, 49, 36);
        Dimension preferredSize = new Dimension();

        int i;
        Rectangle bounds;
        for(i = 0; i < this.panel1.getComponentCount(); ++i) {
            bounds = this.panel1.getComponent(i).getBounds();
            preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
            preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
        }

        Insets insets = this.panel1.getInsets();
        preferredSize.width += insets.right;
        preferredSize.height += insets.bottom;
        this.panel1.setMinimumSize(preferredSize);
        this.panel1.setPreferredSize(preferredSize);
        this.add(this.panel1);
        this.panel1.setBounds(60, 5, 515, 280);
        preferredSize = new Dimension();

        for(i = 0; i < this.getComponentCount(); ++i) {
            bounds = this.getComponent(i).getBounds();
            preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
            preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
        }
        setVisible(true);

        insets = this.getInsets();
        preferredSize.width += insets.right;
        preferredSize.height += insets.bottom;
        this.setMinimumSize(preferredSize);
        this.setPreferredSize(preferredSize);
    }}

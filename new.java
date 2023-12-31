/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.studentmanagement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author ADMIN
 */
public class STATISTICS extends javax.swing.JFrame {

    Connection cn;
    PreparedStatement pst;
    ResultSet rs;
    
    /**
     * Creates new form STATISTICS
     */
    public STATISTICS() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButton1.setText("COURSE");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("REPORT");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel1.setText("STATISTICS");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton2)
                    .addComponent(jButton1))
                .addGap(33, 33, 33))
            .addGroup(layout.createSequentialGroup()
                .addGap(257, 257, 257)
                .addComponent(jLabel1)
                .addContainerGap(279, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(38, 38, 38)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 164, Short.MAX_VALUE)
                .addComponent(jButton2)
                .addGap(72, 72, 72))
        );

        pack();
    }// </editor-fold>                        

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {                                         
        // TODO add your handling code here:
        JFreeChart chart = createStudentChart();
        ChartPanel chartPanel = new ChartPanel(chart);
        javax.swing.JFrame chartFrame = new javax.swing.JFrame("Student Enrollment Chart");
        chartFrame.setDefaultCloseOperation(javax.swing.JFrame.DISPOSE_ON_CLOSE);
        chartFrame.add(chartPanel);
        chartFrame.pack();
        chartFrame.setVisible(true);
    }                                        

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {                                         
        // TODO add your handling code here:
            try {
           cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/studentmgt", "root", null);
           pst = cn.prepareStatement("SELECT COURSE, COUNT(*) AS STUDENT_COUNT FROM student GROUP BY COURSE");

           ResultSet resultSet = pst.executeQuery();

           StringBuilder report = new StringBuilder();
           report.append("Course\tNumber of Students\n");

           while (resultSet.next()) {
               String course = resultSet.getString("COURSE");
               int studentCount = resultSet.getInt("STUDENT_COUNT");

               report.append(course).append("\t").append(studentCount).append("\n");
           }

           // Close the resources (ResultSet, PreparedStatement, and Connection)
           resultSet.close();
           pst.close();
           cn.close();

           // Display the report using JOptionPane
           JOptionPane.showMessageDialog(this, report.toString(), "Student Report", JOptionPane.INFORMATION_MESSAGE);

       } catch (SQLException e) {
           e.printStackTrace();
       }
    }                                        
    
    private JFreeChart createStudentChart() {
    JFreeChart barChart = ChartFactory.createBarChart(
            "Number of Students Enrolled in Each Course",
            "Course",
            "Number of Students",
            createDataset(),
            PlotOrientation.VERTICAL,
            true, true, false);

    return barChart;
}
    
    private CategoryDataset createDataset() {
    DefaultCategoryDataset dataset = new DefaultCategoryDataset();

    try {
        cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/studentmgt", "root", null);
        pst = cn.prepareStatement("SELECT GPA, COUNT(*) AS STUDENT_COUNT FROM student GROUP BY GPA");

        ResultSet resultSet = pst.executeQuery();

        while (resultSet.next()) {
            String courseCode = resultSet.getString("GPA");
            int studentCount = resultSet.getInt("STUDENT_COUNT");

            dataset.addValue(studentCount, "GPA", courseCode);
        }

        // Close the resources (ResultSet, PreparedStatement, and Connection)
        resultSet.close();
        pst.close();
        cn.close();
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return dataset;
    }
    
      
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(STATISTICS.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(STATISTICS.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(STATISTICS.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(STATISTICS.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new STATISTICS().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify                     
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration                   
}
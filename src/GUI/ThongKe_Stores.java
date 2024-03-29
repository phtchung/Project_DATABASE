/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import com.toedter.calendar.JDateChooser;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
//import static jdk.nashorn.internal.objects.NativeArray.map;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 *
 * @author MyPC
 */
public class ThongKe_Stores extends javax.swing.JPanel {

    /**
     * Creates new form ThongKe_Stores
     */
    Map<String, Double> map = new HashMap<>();

    public ThongKe_Stores() {
        initComponents();
        createMap();
        initFrame();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        date1 = new com.toedter.calendar.JDateChooser();
        date2 = new com.toedter.calendar.JDateChooser();
        jcontent = new javax.swing.JTabbedPane();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setPreferredSize(new java.awt.Dimension(1920, 1030));

        jPanel1.setLayout(null);

        jButton1.setContentAreaFilled(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1);
        jButton1.setBounds(610, 248, 150, 70);

        date1.setDate(new java.util.Date(1578101857000L));
        jPanel1.add(date1);
        date1.setBounds(640, 136, 190, 50);
        jPanel1.add(date2);
        date2.setBounds(1120, 136, 200, 50);
        jPanel1.add(jcontent);
        jcontent.setBounds(390, 360, 1150, 460);

        jButton2.setContentAreaFilled(false);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2);
        jButton2.setBounds(1101, 248, 150, 70);

        jButton3.setContentAreaFilled(false);
        jPanel1.add(jButton3);
        jButton3.setBounds(0, 690, 370, 120);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/THỐNG KÊ cửa hàng (1).png"))); // NOI18N
        jLabel1.setText("jLabel1");
        jPanel1.add(jLabel1);
        jLabel1.setBounds(0, -60, 1550, 940);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1552, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 828, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:

        createMap();
        initFrame();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        createMap2();
        initFrame();
    }//GEN-LAST:event_jButton2ActionPerformed

    public void createMap() {
        map = new HashMap<>();
        Date tdate1 = date1.getDate();
        Date tdate2 = date2.getDate();
        Date tdate = new Date();
        if (tdate1.after(tdate2)) {
            JOptionPane.showMessageDialog(this, "Ngày không hợp lệ");
            return;
        } else if (tdate1.after(tdate)) {
            JOptionPane.showMessageDialog(this, "Ngày không hợp lệ");
            return;
        }
        try {
            Connect a = new Connect();
            Connection conn = a.getConnectDB();
            String sql_doanhthu = "select sstr.name as Ten, sum(so.price) as Tien from sales.stores sstr\n"
                    + "left join sales.stocks sstck on sstr.store_id = sstck.store_id\n"
                    + "left join sales.order_items soits on sstck.product_id = soits.product_id\n"
                    + "left join sales.orders so on soits.order_id = so.order_id\n"
                    + "where so.created_date between (?) and (?) or sstr.store_id not in (select store_id from sales.order_items)\n"
                    + "group by sstr.name";
            PreparedStatement ps;
            ps = conn.prepareStatement(sql_doanhthu);
            java.sql.Date jdate1 = new java.sql.Date(tdate1.getTime());
            java.sql.Date jdate2 = new java.sql.Date(tdate2.getTime());
            ps.setDate(1, jdate1);
            ps.setDate(2, jdate2);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String name = rs.getString("Ten");
                Double tien = rs.getDouble("Tien");
                map.put(name, tien);
            }

        } catch (Exception ex) {
            System.out.println("Thong ke cua hang " + ex.toString());
        }
    }

    public void createMap2() {
        map = new HashMap<>();
        Date tdate1 = date1.getDate();
        Date tdate2 = date2.getDate();
        Date tdate = new Date();
        if (tdate1.after(tdate2)) {
            JOptionPane.showMessageDialog(this, "Ngày không hợp lệ");
            return;
        } else if (tdate1.after(tdate)) {
            JOptionPane.showMessageDialog(this, "Ngày không hợp lệ");
            return;
        }
        try {
            Connect a = new Connect();
            Connection conn = a.getConnectDB();
            String sql_doanhthu = "select sstr.name as Ten, sum(soits.price*soits.quantity*(1- soits.discount)-pp.price*soits.quantity) as Tien from sales.stores sstr\n"
                    + "left join sales.stocks sstck on sstr.store_id = sstck.store_id\n"
                    + "left join sales.order_items soits on sstck.product_id = soits.product_id\n"
                    + "left join sales.orders so on soits.order_id = so.order_id\n"
                    + "left join production.products pp on pp.product_id = soits.product_id\n"
                    + "where so.created_date between  (?) and (?) or sstr.store_id not in (select store_id from sales.order_items)\n"
                    + "group by sstr.name";
            PreparedStatement ps;
            ps = conn.prepareStatement(sql_doanhthu);
            java.sql.Date jdate1 = new java.sql.Date(tdate1.getTime());
            java.sql.Date jdate2 = new java.sql.Date(tdate2.getTime());
            ps.setDate(1, jdate1);
            ps.setDate(2, jdate2);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String name = rs.getString("Ten");
                Double tien = rs.getDouble("Tien");
                map.put(name, tien);
            }

        } catch (Exception ex) {
            System.out.println("Thong ke cua hang " + ex.toString());
        }
    }

    public JFreeChart createChart() {
        JFreeChart barChart = ChartFactory.createBarChart(
                "Thống kê tổng doanh thu của cửa hàng",
                "Tên cửa hàng", "Số tiền",
                createDataset(), PlotOrientation.VERTICAL, false, false, false);
        return barChart;
    }

    private CategoryDataset createDataset() {
        final DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (Map.Entry<String, Double> entry : map.entrySet()) {
            String key = entry.getKey();
            Double value = entry.getValue();
            dataset.addValue(value, "Số tiền", key);
        }
        return dataset;
    }

    public void initFrame() {

        ChartPanel chartPanel = new ChartPanel(createChart());
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 100));
        jcontent.removeAll();
        jcontent.add(chartPanel);
        jcontent.setVisible(true);

    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.toedter.calendar.JDateChooser date1;
    private com.toedter.calendar.JDateChooser date2;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTabbedPane jcontent;
    // End of variables declaration//GEN-END:variables
}

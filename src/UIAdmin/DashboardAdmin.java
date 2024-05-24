package UIAdmin;


import MODEL.Account;
import MODEL.Course;
import MODEL.CourseSchedule;
import java.awt.Color;
import java.awt.Component;
import java.awt.Image;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.swing.ImageIcon;
import java.util.*;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;



/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

/**
 *
 * @author pc
 */
public class DashboardAdmin extends javax.swing.JFrame {

    /**
     * Creates new form Dashboard
     */
       Account account = new Account();
        Course course = new Course();
    public DashboardAdmin() {
        initComponents();
        showLogo();
        showDashboard();
    }
   
    
    public void showLogo()
    {
        ImageIcon imageIcon = new ImageIcon("image/logohuit.jpg");
        Image image = imageIcon.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH);
        lbLogo.setIcon(new ImageIcon(image));
    }
    
    public void showIconAdmin()
    {
        ImageIcon imageIcon = new ImageIcon("image/iconAdmin.png");
        Image image = imageIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        lbIconAdmin.setIcon(new ImageIcon(image));
    }
    
    public void showTableCourseDashboard(){
     
         DefaultTableModel model = new DefaultTableModel();
            model.addColumn("Course");
            model.addColumn("Slot");
            model.addColumn("Slot Now");
            model.addColumn("Start Date");
            model.addColumn("End Date");
            model.addColumn("Teacher");
            
            JTableHeader header = tblCourseDashboard.getTableHeader();
            header.setDefaultRenderer(new DefaultTableCellRenderer() {
                @Override
                public Component getTableCellRendererComponent(JTable table, Object value,
                                                               boolean isSelected, boolean hasFocus,
                                                               int row, int column) {
                    JLabel label = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                    label.setHorizontalAlignment(SwingConstants.CENTER); 
                    label.setBackground(Color.GRAY);
                    label.setForeground(Color.WHITE); 
                    return label;
                }
            });
            
            
            CourseSchedule s = new CourseSchedule();
            ArrayList<CourseSchedule> acs = s.getCourseFromTime(new Date());
            for (CourseSchedule st : acs) {
                model.addRow(new Object[]{course.getNameCourseFromID(st.getIdCourse()),st.getSlot(),st.getSlotNow(),st.getStartDay(),st.getEndDay(),account.getNameAccountFromID(st.getIdTeacher())});
            }
            tblCourseDashboard.setModel(model);
    }
    
    public void showTableStudent()
    {
         DefaultTableModel model = new DefaultTableModel();
            model.addColumn("ID");
            model.addColumn("Name");
            model.addColumn("BirthDay");
            model.addColumn("Phone");
            model.addColumn("Address");
            model.addColumn("CCCD");
            model.addColumn("Gender");
            
              JTableHeader header = tblStudent.getTableHeader();
            header.setDefaultRenderer(new DefaultTableCellRenderer() {
                @Override
                public Component getTableCellRendererComponent(JTable table, Object value,
                                                               boolean isSelected, boolean hasFocus,
                                                               int row, int column) {
                    JLabel label = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                    label.setHorizontalAlignment(SwingConstants.CENTER); 
                    label.setBackground(Color.GRAY);
                    label.setForeground(Color.WHITE); 
                    return label;
                }
            });
            
            
            ArrayList<Account> acs = account.getAllStudent();
            for (Account ac : acs) {
                String gt = "Nu";
                if(ac.isGender())
                {
                    gt = "Nam";
                }
                model.addRow(new Object[]{ac.getId(),ac.getName(),ac.getDob(),ac.getPhone(),ac.getAddress(),ac.getIdcard(),gt});
            }
            tblStudent.setModel(model);
    }
    
    public void showfrmStudent()
    {
        rboGroupGender.add(rdoFemaleStudent);
        rboGroupGender.add(rdoMaleStudent);
        showTableStudent();
    }
   
    
    public void showfrmDashboard()
    {
        lbCountStudent.setText(String.valueOf(account.getCountStudent()));
        lbCountTeacher.setText(String.valueOf(account.getCountTeacher()));
        lbCountCourse.setText(String.valueOf(course.getCountCouses()));
        showIconAdmin();
        showTableCourseDashboard();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel17 = new javax.swing.JLabel();
        rboGroupGender = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        itemLogo = new javax.swing.JPanel();
        lbLogo = new javax.swing.JLabel();
        itemDashboard = new javax.swing.JPanel();
        lbDashboard = new javax.swing.JLabel();
        itemStudent = new javax.swing.JPanel();
        lbStudent = new javax.swing.JLabel();
        itemTeacher = new javax.swing.JPanel();
        lbTeacher = new javax.swing.JLabel();
        itemClass = new javax.swing.JPanel();
        lbClass = new javax.swing.JLabel();
        itemLogout = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        itemMail = new javax.swing.JPanel();
        lbMail = new javax.swing.JLabel();
        itemReport = new javax.swing.JPanel();
        lbReport = new javax.swing.JLabel();
        layoutadmin = new javax.swing.JPanel();
        itemfrmReportAdmin = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        itemfrmMailAdmin = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        itemfrmClassAdmin = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        itemfrmDashboardAdmin = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        lbCountCourse = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        lbCountStudent = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        lbCountTeacher = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        lbIconAdmin = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblCourseDashboard = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        itemfrmStudentAdmin = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblStudent = new javax.swing.JTable();
        jPanel6 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        txtNameStudent = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtDateStudent = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        txtPhoneStudent = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        txtCCCDStudent = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtAdressStudent = new javax.swing.JTextArea();
        rdoMaleStudent = new javax.swing.JRadioButton();
        rdoFemaleStudent = new javax.swing.JRadioButton();
        btnEditStudent = new javax.swing.JButton();
        btnDeleteStudent = new javax.swing.JButton();
        txtSearchStudent = new javax.swing.JTextField();
        btnFind = new javax.swing.JButton();
        btnCreateStudent = new javax.swing.JButton();
        itemfrmTeacherAdmin = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();

        jLabel17.setText("jLabel17");

        jPanel1.setBackground(new java.awt.Color(51, 51, 255));

        itemLogo.setBackground(new java.awt.Color(255, 255, 255));
        itemLogo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                itemLogoMouseClicked(evt);
            }
        });

        lbLogo.setAlignmentX(1.0F);

        javax.swing.GroupLayout itemLogoLayout = new javax.swing.GroupLayout(itemLogo);
        itemLogo.setLayout(itemLogoLayout);
        itemLogoLayout.setHorizontalGroup(
            itemLogoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, itemLogoLayout.createSequentialGroup()
                .addGap(0, 22, Short.MAX_VALUE)
                .addComponent(lbLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        itemLogoLayout.setVerticalGroup(
            itemLogoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbLogo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        itemDashboard.setBackground(new java.awt.Color(51, 51, 255));
        itemDashboard.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                itemDashboardMouseClicked(evt);
            }
        });

        lbDashboard.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbDashboard.setForeground(new java.awt.Color(255, 255, 255));
        lbDashboard.setText("Dashboard");

        javax.swing.GroupLayout itemDashboardLayout = new javax.swing.GroupLayout(itemDashboard);
        itemDashboard.setLayout(itemDashboardLayout);
        itemDashboardLayout.setHorizontalGroup(
            itemDashboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbDashboard, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 75, Short.MAX_VALUE)
        );
        itemDashboardLayout.setVerticalGroup(
            itemDashboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(itemDashboardLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(lbDashboard)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        itemStudent.setBackground(new java.awt.Color(51, 51, 255));
        itemStudent.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                itemStudentMouseClicked(evt);
            }
        });

        lbStudent.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbStudent.setForeground(new java.awt.Color(255, 255, 255));
        lbStudent.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbStudent.setText("Student");
        lbStudent.setToolTipText("");

        javax.swing.GroupLayout itemStudentLayout = new javax.swing.GroupLayout(itemStudent);
        itemStudent.setLayout(itemStudentLayout);
        itemStudentLayout.setHorizontalGroup(
            itemStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbStudent, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 75, Short.MAX_VALUE)
        );
        itemStudentLayout.setVerticalGroup(
            itemStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(itemStudentLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(lbStudent)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        itemTeacher.setBackground(new java.awt.Color(51, 51, 255));
        itemTeacher.setPreferredSize(new java.awt.Dimension(75, 45));
        itemTeacher.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                itemTeacherMouseClicked(evt);
            }
        });

        lbTeacher.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbTeacher.setForeground(new java.awt.Color(255, 255, 255));
        lbTeacher.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbTeacher.setText("Teacher");

        javax.swing.GroupLayout itemTeacherLayout = new javax.swing.GroupLayout(itemTeacher);
        itemTeacher.setLayout(itemTeacherLayout);
        itemTeacherLayout.setHorizontalGroup(
            itemTeacherLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbTeacher, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 75, Short.MAX_VALUE)
        );
        itemTeacherLayout.setVerticalGroup(
            itemTeacherLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(itemTeacherLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(lbTeacher)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        itemClass.setBackground(new java.awt.Color(51, 51, 255));
        itemClass.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                itemClassMouseClicked(evt);
            }
        });

        lbClass.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbClass.setForeground(new java.awt.Color(255, 255, 255));
        lbClass.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbClass.setText("Class");

        javax.swing.GroupLayout itemClassLayout = new javax.swing.GroupLayout(itemClass);
        itemClass.setLayout(itemClassLayout);
        itemClassLayout.setHorizontalGroup(
            itemClassLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbClass, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 75, Short.MAX_VALUE)
        );
        itemClassLayout.setVerticalGroup(
            itemClassLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(itemClassLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(lbClass)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        itemLogout.setBackground(new java.awt.Color(51, 51, 255));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 51, 51));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("LOGOUT");

        javax.swing.GroupLayout itemLogoutLayout = new javax.swing.GroupLayout(itemLogout);
        itemLogout.setLayout(itemLogoutLayout);
        itemLogoutLayout.setHorizontalGroup(
            itemLogoutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(itemLogoutLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel5)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        itemLogoutLayout.setVerticalGroup(
            itemLogoutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(itemLogoutLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel5)
                .addContainerGap(28, Short.MAX_VALUE))
        );

        itemMail.setBackground(new java.awt.Color(51, 51, 255));
        itemMail.setPreferredSize(new java.awt.Dimension(75, 45));
        itemMail.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                itemMailMouseClicked(evt);
            }
        });

        lbMail.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbMail.setForeground(new java.awt.Color(255, 255, 255));
        lbMail.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbMail.setText("Mail");

        javax.swing.GroupLayout itemMailLayout = new javax.swing.GroupLayout(itemMail);
        itemMail.setLayout(itemMailLayout);
        itemMailLayout.setHorizontalGroup(
            itemMailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbMail, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 75, Short.MAX_VALUE)
        );
        itemMailLayout.setVerticalGroup(
            itemMailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(itemMailLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(lbMail)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        itemReport.setBackground(new java.awt.Color(51, 51, 255));
        itemReport.setPreferredSize(new java.awt.Dimension(75, 45));
        itemReport.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                itemReportMouseClicked(evt);
            }
        });

        lbReport.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbReport.setForeground(new java.awt.Color(255, 255, 255));
        lbReport.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbReport.setText("Report");

        javax.swing.GroupLayout itemReportLayout = new javax.swing.GroupLayout(itemReport);
        itemReport.setLayout(itemReportLayout);
        itemReportLayout.setHorizontalGroup(
            itemReportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbReport, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 75, Short.MAX_VALUE)
        );
        itemReportLayout.setVerticalGroup(
            itemReportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(itemReportLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(lbReport)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(itemLogo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(itemDashboard, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(itemStudent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(itemTeacher, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(itemClass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(itemMail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(itemReport, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 206, Short.MAX_VALUE)
                .addComponent(itemLogout, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(itemLogo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(itemStudent, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(itemTeacher, javax.swing.GroupLayout.DEFAULT_SIZE, 67, Short.MAX_VALUE)
            .addComponent(itemClass, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(itemDashboard, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(itemLogout, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addComponent(itemMail, javax.swing.GroupLayout.DEFAULT_SIZE, 67, Short.MAX_VALUE)
            .addComponent(itemReport, javax.swing.GroupLayout.DEFAULT_SIZE, 67, Short.MAX_VALUE)
        );

        jLabel13.setText("Report");

        javax.swing.GroupLayout itemfrmReportAdminLayout = new javax.swing.GroupLayout(itemfrmReportAdmin);
        itemfrmReportAdmin.setLayout(itemfrmReportAdminLayout);
        itemfrmReportAdminLayout.setHorizontalGroup(
            itemfrmReportAdminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(itemfrmReportAdminLayout.createSequentialGroup()
                .addGap(277, 277, 277)
                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(529, Short.MAX_VALUE))
        );
        itemfrmReportAdminLayout.setVerticalGroup(
            itemfrmReportAdminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(itemfrmReportAdminLayout.createSequentialGroup()
                .addGap(175, 175, 175)
                .addComponent(jLabel13)
                .addContainerGap(258, Short.MAX_VALUE))
        );

        jLabel12.setText("MAIL");

        javax.swing.GroupLayout itemfrmMailAdminLayout = new javax.swing.GroupLayout(itemfrmMailAdmin);
        itemfrmMailAdmin.setLayout(itemfrmMailAdminLayout);
        itemfrmMailAdminLayout.setHorizontalGroup(
            itemfrmMailAdminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(itemfrmMailAdminLayout.createSequentialGroup()
                .addGap(276, 276, 276)
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(498, Short.MAX_VALUE))
        );
        itemfrmMailAdminLayout.setVerticalGroup(
            itemfrmMailAdminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(itemfrmMailAdminLayout.createSequentialGroup()
                .addGap(156, 156, 156)
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(259, Short.MAX_VALUE))
        );

        jLabel11.setText("CLASS");

        javax.swing.GroupLayout itemfrmClassAdminLayout = new javax.swing.GroupLayout(itemfrmClassAdmin);
        itemfrmClassAdmin.setLayout(itemfrmClassAdminLayout);
        itemfrmClassAdminLayout.setHorizontalGroup(
            itemfrmClassAdminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(itemfrmClassAdminLayout.createSequentialGroup()
                .addGap(269, 269, 269)
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(468, Short.MAX_VALUE))
        );
        itemfrmClassAdminLayout.setVerticalGroup(
            itemfrmClassAdminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(itemfrmClassAdminLayout.createSequentialGroup()
                .addGap(166, 166, 166)
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(217, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(102, 102, 255));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel1.setText("Tổng các môn học hiện tại");

        lbCountCourse.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        lbCountCourse.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addComponent(lbCountCourse, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbCountCourse, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(25, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(102, 255, 102));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setText("Số lượng học sinh tại trung tâm");

        lbCountStudent.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        lbCountStudent.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(56, 56, 56)
                .addComponent(lbCountStudent, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbCountStudent, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(25, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(102, 255, 255));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setText("Số lượng giáo viên tại trung tâm");

        lbCountTeacher.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        lbCountTeacher.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lbCountTeacher, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(63, 63, 63))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbCountTeacher, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(25, Short.MAX_VALUE))
        );

        jPanel5.setBackground(new java.awt.Color(102, 0, 255));

        jLabel4.setFont(new java.awt.Font("Segoe UI Emoji", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("HELLO ADMIN");

        lbIconAdmin.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(65, 65, 65)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(52, 52, 52)
                        .addComponent(lbIconAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(57, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addGap(100, 100, 100)
                .addComponent(lbIconAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addGap(177, 177, 177))
        );

        tblCourseDashboard.setBackground(new java.awt.Color(255, 255, 255));
        tblCourseDashboard.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblCourseDashboard.setGridColor(new java.awt.Color(255, 255, 255));
        tblCourseDashboard.setShowGrid(false);
        jScrollPane1.setViewportView(tblCourseDashboard);

        jLabel6.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(51, 51, 255));
        jLabel6.setText("TOP CÁC MÔN HỌC CÒN NIÊN HẠN");

        javax.swing.GroupLayout itemfrmDashboardAdminLayout = new javax.swing.GroupLayout(itemfrmDashboardAdmin);
        itemfrmDashboardAdmin.setLayout(itemfrmDashboardAdminLayout);
        itemfrmDashboardAdminLayout.setHorizontalGroup(
            itemfrmDashboardAdminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(itemfrmDashboardAdminLayout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(itemfrmDashboardAdminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(itemfrmDashboardAdminLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(itemfrmDashboardAdminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(itemfrmDashboardAdminLayout.createSequentialGroup()
                                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 580, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(itemfrmDashboardAdminLayout.createSequentialGroup()
                        .addGap(190, 190, 190)
                        .addComponent(jLabel6)))
                .addContainerGap())
        );
        itemfrmDashboardAdminLayout.setVerticalGroup(
            itemfrmDashboardAdminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(itemfrmDashboardAdminLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(itemfrmDashboardAdminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        tblStudent.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblStudent.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblStudentMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblStudent);

        jPanel6.setBackground(new java.awt.Color(153, 153, 153));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel7.setText("Name:");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel10.setText("Birthday");

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel14.setText("Phone:");

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel15.setText("Address");

        jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel16.setText("CCCD:");

        jLabel18.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel18.setText("Gender:");

        txtAdressStudent.setColumns(20);
        txtAdressStudent.setRows(5);
        jScrollPane3.setViewportView(txtAdressStudent);

        rdoMaleStudent.setText("Nam");

        rdoFemaleStudent.setText("Nu");

        btnEditStudent.setBackground(new java.awt.Color(204, 204, 0));
        btnEditStudent.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnEditStudent.setForeground(new java.awt.Color(255, 255, 255));
        btnEditStudent.setText("EDIT");
        btnEditStudent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditStudentActionPerformed(evt);
            }
        });

        btnDeleteStudent.setBackground(new java.awt.Color(255, 0, 51));
        btnDeleteStudent.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnDeleteStudent.setForeground(new java.awt.Color(255, 255, 255));
        btnDeleteStudent.setText("DELETE");
        btnDeleteStudent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteStudentActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(jPanel6Layout.createSequentialGroup()
                                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(0, 51, Short.MAX_VALUE)))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(txtPhoneStudent, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtDateStudent, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtNameStudent, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel6Layout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addComponent(rdoMaleStudent, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(rdoFemaleStudent, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(7, 7, 7))
                                    .addGroup(jPanel6Layout.createSequentialGroup()
                                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel18, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel6Layout.createSequentialGroup()
                                                .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(0, 0, Short.MAX_VALUE)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtCCCDStudent, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(1, 1, 1)))
                        .addGap(16, 16, 16))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(57, 57, 57)
                        .addComponent(btnEditStudent, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(60, 60, 60)
                        .addComponent(btnDeleteStudent, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtNameStudent, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(txtDateStudent, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(txtPhoneStudent, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel15)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(txtCCCDStudent, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(rdoMaleStudent)
                    .addComponent(rdoFemaleStudent))
                .addGap(29, 29, 29)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnEditStudent, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDeleteStudent, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(47, 47, 47))
        );

        btnFind.setBackground(new java.awt.Color(0, 153, 51));
        btnFind.setForeground(new java.awt.Color(255, 255, 255));
        btnFind.setText("FIND");
        btnFind.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFindActionPerformed(evt);
            }
        });

        btnCreateStudent.setBackground(new java.awt.Color(51, 51, 255));
        btnCreateStudent.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnCreateStudent.setForeground(new java.awt.Color(255, 255, 255));
        btnCreateStudent.setText("CREATE");
        btnCreateStudent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCreateStudentActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout itemfrmStudentAdminLayout = new javax.swing.GroupLayout(itemfrmStudentAdmin);
        itemfrmStudentAdmin.setLayout(itemfrmStudentAdminLayout);
        itemfrmStudentAdminLayout.setHorizontalGroup(
            itemfrmStudentAdminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(itemfrmStudentAdminLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(itemfrmStudentAdminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(itemfrmStudentAdminLayout.createSequentialGroup()
                        .addComponent(txtSearchStudent, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnFind, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnCreateStudent, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        itemfrmStudentAdminLayout.setVerticalGroup(
            itemfrmStudentAdminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(itemfrmStudentAdminLayout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addGroup(itemfrmStudentAdminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnFind)
                    .addComponent(btnCreateStudent, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSearchStudent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jLabel8.setText("TEacher");

        javax.swing.GroupLayout itemfrmTeacherAdminLayout = new javax.swing.GroupLayout(itemfrmTeacherAdmin);
        itemfrmTeacherAdmin.setLayout(itemfrmTeacherAdminLayout);
        itemfrmTeacherAdminLayout.setHorizontalGroup(
            itemfrmTeacherAdminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(itemfrmTeacherAdminLayout.createSequentialGroup()
                .addGap(310, 310, 310)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        itemfrmTeacherAdminLayout.setVerticalGroup(
            itemfrmTeacherAdminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(itemfrmTeacherAdminLayout.createSequentialGroup()
                .addGap(124, 124, 124)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(273, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layoutadminLayout = new javax.swing.GroupLayout(layoutadmin);
        layoutadmin.setLayout(layoutadminLayout);
        layoutadminLayout.setHorizontalGroup(
            layoutadminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layoutadminLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(itemfrmTeacherAdmin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layoutadminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layoutadminLayout.createSequentialGroup()
                    .addGap(9, 9, 9)
                    .addComponent(itemfrmStudentAdmin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGap(9, 9, 9)))
            .addGroup(layoutadminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layoutadminLayout.createSequentialGroup()
                    .addGap(12, 12, 12)
                    .addComponent(itemfrmDashboardAdmin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGap(12, 12, 12)))
            .addGroup(layoutadminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layoutadminLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(itemfrmClassAdmin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
            .addGroup(layoutadminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layoutadminLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(itemfrmMailAdmin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
            .addGroup(layoutadminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layoutadminLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(itemfrmReportAdmin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        layoutadminLayout.setVerticalGroup(
            layoutadminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layoutadminLayout.createSequentialGroup()
                .addComponent(itemfrmTeacherAdmin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layoutadminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layoutadminLayout.createSequentialGroup()
                    .addGap(3, 3, 3)
                    .addComponent(itemfrmStudentAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(9, Short.MAX_VALUE)))
            .addGroup(layoutadminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layoutadminLayout.createSequentialGroup()
                    .addGap(3, 3, 3)
                    .addComponent(itemfrmDashboardAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
            .addGroup(layoutadminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layoutadminLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(itemfrmClassAdmin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
            .addGroup(layoutadminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layoutadminLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(itemfrmMailAdmin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
            .addGroup(layoutadminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layoutadminLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(itemfrmReportAdmin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(layoutadmin, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(layoutadmin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void itemDashboardMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_itemDashboardMouseClicked
      showDashboard();
    }//GEN-LAST:event_itemDashboardMouseClicked

    private void itemStudentMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_itemStudentMouseClicked
        showStudent();
    }//GEN-LAST:event_itemStudentMouseClicked

    private void itemTeacherMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_itemTeacherMouseClicked
        showTeacher();
    }//GEN-LAST:event_itemTeacherMouseClicked

    private void itemLogoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_itemLogoMouseClicked
        showDashboard();
    }//GEN-LAST:event_itemLogoMouseClicked

    private void itemClassMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_itemClassMouseClicked
       showClass();
    }//GEN-LAST:event_itemClassMouseClicked

    private void itemMailMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_itemMailMouseClicked
        showMail();
    }//GEN-LAST:event_itemMailMouseClicked

    private void itemReportMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_itemReportMouseClicked
        showReport();
    }//GEN-LAST:event_itemReportMouseClicked

    private void tblStudentMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblStudentMouseClicked
       int selectedRow = tblStudent.getSelectedRow();
                if (selectedRow != -1) {
                    int id = Integer.parseInt(tblStudent.getValueAt(selectedRow, 0).toString());
                    Account ac = account.getAcountFromID(id);
                    txtNameStudent.setText(ac.getName());
                    txtDateStudent.setText(ac.getDob().toString());
                    txtPhoneStudent.setText(ac.getPhone());
                    txtAdressStudent.setText(ac.getAddress());
                    txtCCCDStudent.setText(ac.getIdcard());
                    if(ac.isGender())
                    {
                        rdoMaleStudent.setSelected(true);
                    }else{
                        rdoFemaleStudent.setSelected(true);
                    }
                }
    }//GEN-LAST:event_tblStudentMouseClicked

    private void btnFindActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFindActionPerformed
        String search = txtSearchStudent.getText().toString().trim();
         DefaultTableModel model = new DefaultTableModel();
            model.addColumn("ID");
            model.addColumn("Name");
            model.addColumn("BirthDay");
            model.addColumn("Phone");
            model.addColumn("Address");
            model.addColumn("CCCD");
            model.addColumn("Gender");
            
              JTableHeader header = tblStudent.getTableHeader();
            header.setDefaultRenderer(new DefaultTableCellRenderer() {
                @Override
                public Component getTableCellRendererComponent(JTable table, Object value,
                                                               boolean isSelected, boolean hasFocus,
                                                               int row, int column) {
                    JLabel label = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                    label.setHorizontalAlignment(SwingConstants.CENTER); 
                    label.setBackground(Color.GRAY);
                    label.setForeground(Color.WHITE); 
                    return label;
                }
            });
            
            
            ArrayList<Account> acs = account.getStudentFromName(search);
            for (Account ac : acs) {
                String gt = "Nu";
                if(ac.isGender())
                {
                    gt = "Nam";
                }
                model.addRow(new Object[]{ac.getId(),ac.getName(),ac.getDob(),ac.getPhone(),ac.getAddress(),ac.getIdcard(),gt});
            }
            tblStudent.setModel(model);
    }//GEN-LAST:event_btnFindActionPerformed

    private void btnCreateStudentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCreateStudentActionPerformed
        CreateStudent frm = new CreateStudent();
        frm.setVisible(true);
    }//GEN-LAST:event_btnCreateStudentActionPerformed

    private void btnEditStudentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditStudentActionPerformed
       SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        int selectedRow = tblStudent.getSelectedRow();
        
        if (selectedRow != -1) {
            try {
                int id = Integer.parseInt(tblStudent.getValueAt(selectedRow, 0).toString());
                String name = txtNameStudent.getText().trim();
                java.util.Date utilDate = dateFormat.parse(txtDateStudent.getText().trim());
                String phone = txtPhoneStudent.getText().toString().trim();
                String address = txtAdressStudent.getText().toString();
                String cccd = txtCCCDStudent.getText().toString();
                boolean gender = true;
                if(rdoFemaleStudent.isSelected())
                {
                    gender = false;
                }

                boolean success = account.editAccount(id, name, utilDate, phone, address, cccd, gender);

                if (success) {
                    JOptionPane.showMessageDialog(null, "Cập nhật thông tin học viên thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                    showfrmStudent();
                } else {
                    JOptionPane.showMessageDialog(null, "Cập nhật thông tin học viên thất bại!", "Thông báo", JOptionPane.ERROR_MESSAGE);
                }

            } catch (ParseException e) {
                JOptionPane.showMessageDialog(null, "Định dạng ngày tháng không hợp lệ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Định dạng số không hợp lệ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Bạn chưa chọn học viên để chỉnh sửa", "Thông báo", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnEditStudentActionPerformed

    private void btnDeleteStudentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteStudentActionPerformed
       int selectedRow = tblStudent.getSelectedRow();
        
        if (selectedRow != -1) {
                int id = Integer.parseInt(tblStudent.getValueAt(selectedRow, 0).toString());
                
                boolean success = account.deleteAccount(id);

                if (success) {
                    JOptionPane.showMessageDialog(null, "Xóa học viên thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                    showfrmStudent();
                } else {
                    JOptionPane.showMessageDialog(null, "Xóa học viên thất bại!", "Thông báo", JOptionPane.ERROR_MESSAGE);
                }

        } else {
            JOptionPane.showMessageDialog(null, "Bạn chưa chọn học viên để xóa!", "Thông báo", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnDeleteStudentActionPerformed

    public void showDashboard()
    {
         itemfrmDashboardAdmin.setVisible(true);
         itemfrmStudentAdmin.setVisible(false);
         itemfrmTeacherAdmin.setVisible(false);
         itemfrmClassAdmin.setVisible(false);
         itemfrmMailAdmin.setVisible(false);
         itemfrmReportAdmin.setVisible(false);
         lbDashboard.setForeground(Color.GRAY);
         lbMail.setForeground(Color.white);
         lbReport.setForeground(Color.white);
         lbStudent.setForeground(Color.white);
         lbClass.setForeground(Color.white);
         lbTeacher.setForeground(Color.white);
        showfrmDashboard();
    }
    
    public void showTeacher()
    {
         itemfrmDashboardAdmin.setVisible(false);
         itemfrmStudentAdmin.setVisible(false);
         itemfrmTeacherAdmin.setVisible(true);
         itemfrmClassAdmin.setVisible(false);
         itemfrmMailAdmin.setVisible(false);
         itemfrmReportAdmin.setVisible(false);
         lbDashboard.setForeground(Color.white);
         lbMail.setForeground(Color.white);
         lbReport.setForeground(Color.white);
         lbStudent.setForeground(Color.white);
         lbClass.setForeground(Color.white);
         lbTeacher.setForeground(Color.GRAY);
    }
    
    public void showStudent()
    {
         itemfrmDashboardAdmin.setVisible(false);
         itemfrmStudentAdmin.setVisible(true);
         itemfrmTeacherAdmin.setVisible(false);
         itemfrmClassAdmin.setVisible(false);
         itemfrmMailAdmin.setVisible(false);
         itemfrmReportAdmin.setVisible(false);
         lbDashboard.setForeground(Color.white);
         lbMail.setForeground(Color.white);
         lbReport.setForeground(Color.white);
         lbStudent.setForeground(Color.GRAY);
         lbClass.setForeground(Color.white);
         lbTeacher.setForeground(Color.white);
        showfrmStudent();

    }
    
    public void showClass()
    {
        itemfrmDashboardAdmin.setVisible(false);
         itemfrmStudentAdmin.setVisible(false);
         itemfrmTeacherAdmin.setVisible(false);
         itemfrmClassAdmin.setVisible(true);
         itemfrmMailAdmin.setVisible(false);
         itemfrmReportAdmin.setVisible(false);
         lbDashboard.setForeground(Color.white);
         lbMail.setForeground(Color.white);
         lbReport.setForeground(Color.white);
         lbStudent.setForeground(Color.white);
         lbClass.setForeground(Color.GRAY);
         lbTeacher.setForeground(Color.white);
    }
    
    public void showMail()
    {
        itemfrmDashboardAdmin.setVisible(false);
         itemfrmStudentAdmin.setVisible(false);
         itemfrmTeacherAdmin.setVisible(false);
         itemfrmClassAdmin.setVisible(false);
         itemfrmMailAdmin.setVisible(true);
         itemfrmReportAdmin.setVisible(false);
         lbDashboard.setForeground(Color.white);
         lbMail.setForeground(Color.GRAY);
         lbReport.setForeground(Color.white);
         lbStudent.setForeground(Color.white);
         lbClass.setForeground(Color.white);
         lbTeacher.setForeground(Color.white);
    }
    
    public void showReport()
    {
        itemfrmDashboardAdmin.setVisible(false);
         itemfrmStudentAdmin.setVisible(false);
         itemfrmTeacherAdmin.setVisible(false);
         itemfrmClassAdmin.setVisible(false);
         itemfrmMailAdmin.setVisible(false);
         itemfrmReportAdmin.setVisible(true);
           lbDashboard.setForeground(Color.white);
         lbMail.setForeground(Color.white);
         lbReport.setForeground(Color.GRAY);
         lbStudent.setForeground(Color.white);
         lbClass.setForeground(Color.white);
         lbTeacher.setForeground(Color.white);
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
            java.util.logging.Logger.getLogger(DashboardAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DashboardAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DashboardAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DashboardAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DashboardAdmin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCreateStudent;
    private javax.swing.JButton btnDeleteStudent;
    private javax.swing.JButton btnEditStudent;
    private javax.swing.JButton btnFind;
    private javax.swing.JPanel itemClass;
    private javax.swing.JPanel itemDashboard;
    private javax.swing.JPanel itemLogo;
    private javax.swing.JPanel itemLogout;
    private javax.swing.JPanel itemMail;
    private javax.swing.JPanel itemReport;
    private javax.swing.JPanel itemStudent;
    private javax.swing.JPanel itemTeacher;
    private javax.swing.JPanel itemfrmClassAdmin;
    private javax.swing.JPanel itemfrmDashboardAdmin;
    private javax.swing.JPanel itemfrmMailAdmin;
    private javax.swing.JPanel itemfrmReportAdmin;
    private javax.swing.JPanel itemfrmStudentAdmin;
    private javax.swing.JPanel itemfrmTeacherAdmin;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JPanel layoutadmin;
    private javax.swing.JLabel lbClass;
    private javax.swing.JLabel lbCountCourse;
    private javax.swing.JLabel lbCountStudent;
    private javax.swing.JLabel lbCountTeacher;
    private javax.swing.JLabel lbDashboard;
    private javax.swing.JLabel lbIconAdmin;
    private javax.swing.JLabel lbLogo;
    private javax.swing.JLabel lbMail;
    private javax.swing.JLabel lbReport;
    private javax.swing.JLabel lbStudent;
    private javax.swing.JLabel lbTeacher;
    private javax.swing.ButtonGroup rboGroupGender;
    private javax.swing.JRadioButton rdoFemaleStudent;
    private javax.swing.JRadioButton rdoMaleStudent;
    private javax.swing.JTable tblCourseDashboard;
    private javax.swing.JTable tblStudent;
    private javax.swing.JTextArea txtAdressStudent;
    private javax.swing.JTextField txtCCCDStudent;
    private javax.swing.JTextField txtDateStudent;
    private javax.swing.JTextField txtNameStudent;
    private javax.swing.JTextField txtPhoneStudent;
    private javax.swing.JTextField txtSearchStudent;
    // End of variables declaration//GEN-END:variables
}

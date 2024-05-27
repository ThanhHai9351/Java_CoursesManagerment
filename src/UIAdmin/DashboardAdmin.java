package UIAdmin;


import UI.Login;
import MODEL.Account;
import MODEL.Course;
import MODEL.CourseSchedule;
import MODEL.Mail;
import MODEL.Receipt;
import MODEL.Schedule;
import MODEL.Teacher;
import java.awt.Color;
import java.awt.Component;
import java.awt.Image;
import java.sql.SQLException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import UI.Login;


/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

/**
 *
 * @author pc
 */
public class DashboardAdmin extends javax.swing.JFrame {
 boolean checkLoadColumnTeacher=false,checkLoadColumnClass=false,checkLoadColumnReceipt=false,checkLoadColumnMAil=false;
    // Teacher
    DefaultTableModel modelTeacher=new DefaultTableModel();
    Teacher teacher=new Teacher();
    List<Teacher> listTeacher=new ArrayList<>();
    // Class
    DefaultTableModel modelClass=new DefaultTableModel();
    CourseSchedule daoClass=new CourseSchedule();
    List<CourseSchedule> listClass=new ArrayList<>();
    //Course
    Course daoCourse=new Course();
    List<Course> listCourse=new ArrayList<>();
    //Receipt
    Receipt receipt=new Receipt();
    List<Receipt> listReceipt=new ArrayList<>();
    DefaultTableModel modelReceipt=new DefaultTableModel();
    //Mail
    Mail mail=new Mail();
    List<Mail> listMail=new ArrayList<>();
    DefaultTableModel modelMail=new DefaultTableModel();
    //Schedule 
    Schedule schedule=new Schedule();
    /**
     * Creates new form Dashboard
     */
       Account account = new Account();
        Course course = new Course();
    public DashboardAdmin() {
        initComponents();
        
        try {
            showLogo();
            showDashboard();
        } catch (Exception e) {
        }
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
    public boolean checkDate(String s){
        String pattern="^\\d{4}-\\d{2}-\\d{2}$";
        if(s.matches(pattern))
            return true;
        return false;
    }
    public boolean checkNumber(String s){
        String pattern="^\\d+$";
        if(s.matches(pattern))
            return true;
        return false;
    }
    public boolean checkTime(String s){
        String regex = "^([01]\\d|2[0-3]):([0-5]\\d)$";
        return s.matches(regex);
    }
    public static boolean checkInt(String s) {
        try {
            Integer.parseInt(s);
            return true; // Chuỗi s là dạng số int
        } catch (NumberFormatException e) {
            return false; // Chuỗi s không phải là dạng số int
        }
    }
    
    public void loadTableTeacher() throws SQLException{
        
        String gender;
        listTeacher=teacher.getAllTeacher();
        modelTeacher=(DefaultTableModel) tblTeacher.getModel();
        modelTeacher.setRowCount(0);
        if(checkLoadColumnTeacher==false){
            modelTeacher.addColumn("ID");
            modelTeacher.addColumn("Name");
            modelTeacher.addColumn("Birthday");
            modelTeacher.addColumn("Phone");
            modelTeacher.addColumn("ADdress");
            modelTeacher.addColumn("ID Card");
            modelTeacher.addColumn("Gender");   
            checkLoadColumnTeacher=true;
        }
        
        for(Teacher t: listTeacher){
            if(t.isGender())
                gender="Nam";
            else
                gender="Nu";
            Object []row=new Object[]{t.getId(),t.getName(),t.getDob(),t.getPhone(),t.getAddress(),t.getIdCard(),gender};
            modelTeacher.addRow(row);
        }
        tblTeacher.setModel(modelTeacher);
    }
    public void loadTableClass() throws SQLException{
        listClass=daoClass.getAllCouseSchedules();
        modelClass=(DefaultTableModel) tblClass.getModel();
        modelClass.setRowCount(0);
        if(checkLoadColumnClass==false){
            modelClass.addColumn("ID");
            modelClass.addColumn("ID Course");
            modelClass.addColumn("Slot");
            modelClass.addColumn("Slot now");
            modelClass.addColumn("Start Day");
            modelClass.addColumn("End Day");
            modelClass.addColumn("ID Teacher");
            loadComboboxCourseClass();
            loadCombobocTeacherClass();
            checkLoadColumnClass=true;
        }
        for(CourseSchedule course: listClass){
            Object []row=new Object[]{course.getId(), course.getIdCourse(),course.getSlot(),course.getSlotNow(),course.getStartDay(),course.getEndDay(),course.getIdTeacher()};
            modelClass.addRow(row);
        }
        tblClass.setModel(modelTeacher);
    }
    
    void loadComboboxCourseClass(){
        listCourse=daoCourse.getAllCourses();
        for(int i=0;i<listCourse.size();i++){
            cboCourseClass.addItem(listCourse.get(i).getName());
        }
    }
    void loadCombobocTeacherClass() throws SQLException{
        listTeacher=teacher.getAllTeacher();
        for(int i=0;i<listTeacher.size();i++){
            cboTeacherClass.addItem(listTeacher.get(i).getName());
        }
    }
    int getIDCourse(String name){
        listCourse=daoCourse.getAllCourses();
        for(int i=0;i<listCourse.size();i++){
            if(listCourse.get(i).getName().equals(name)){
                return listCourse.get(i).getId();
            }
        }
        return -1;
    }
    int getIDTeacher(String name) throws SQLException{
        listTeacher=teacher.getAllTeacher();
        for(int i=0;i<listTeacher.size();i++){
            if(listTeacher.get(i).getName().equals(name)){
                return listTeacher.get(i).getId();
            }
        }
        return -1;
    }
    String getNameTeacher(int id) throws SQLException{
        listTeacher=teacher.getAllTeacher();
        for(int i=0;i<listTeacher.size();i++){
            if(listTeacher.get(i).getId()==id){
                return listTeacher.get(i).getName();
            }
        }
        return null;
    }
    int checkDayStart(String dateStart){
        LocalDate date=LocalDate.parse(dateStart);
        if(date.getDayOfWeek().getValue()==1 )
            return 1;
        if(date.getDayOfWeek().getValue()==2)
            return 2;
        return 0;
    }
    String getEndDate(String dateStart){
         LocalDate startDate = LocalDate.parse(dateStart, DateTimeFormatter.ISO_LOCAL_DATE);
        
        // Tính ngày kết thúc học
        LocalDate endDate = startDate.plusWeeks(4).minusDays(3);

        return endDate.toString();
    }
    public void loadTableReceipt(){
        listReceipt=receipt.getAllReceipt();
        modelReceipt=(DefaultTableModel) tblReceipt.getModel();
        modelReceipt.setRowCount(0);
        if(checkLoadColumnReceipt==false){
            modelReceipt.addColumn("Name");
            modelReceipt.addColumn("Name Course");
            modelReceipt.addColumn("Price");
            modelReceipt.addColumn("Start Day");
            checkLoadColumnReceipt=true;
        }
        float total=0;
        for(Receipt receipt: listReceipt){
            Object []row=new Object[]{receipt.getName(),receipt.getNameCourse(),receipt.getPrice(),receipt.getStartDay()};
            modelReceipt.addRow(row);
            total+=receipt.getPrice();
        }
        lblTotalReceipt.setText(total+" $");
        tblReceipt.setModel(modelReceipt);
    }
    public void loadTableMail(){
        listMail= mail.getAllMail();
        modelMail=(DefaultTableModel) tblMail.getModel();
        modelMail.setRowCount(0);
        if(checkLoadColumnMAil==false){
            modelMail.addColumn("ID");
            modelMail.addColumn("ID Account");
            modelMail.addColumn("Name");
            modelMail.addColumn("Title");
            modelMail.addColumn("Is Read");
            checkLoadColumnMAil=true;
        }
        for(Mail mail : listMail){
            Object []row=new Object[]{mail.getId(),mail.getIdAccount(),mail.getName(),mail.getTitle(),mail.isIsRead()};
            modelMail.addRow(row);
        }
        tblMail.setModel(modelMail);
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
            ArrayList<CourseSchedule> acs = s.getCourseFromTime(new java.util.Date());
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
        itemfrmReportAdmin1 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblReceipt = new javax.swing.JTable();
        jPanel7 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        btnFindReceipt = new javax.swing.JButton();
        txtFindNameReceipt = new javax.swing.JTextField();
        txtFindPriceReceipt = new javax.swing.JTextField();
        btnFindReceipt1 = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        lblTotalReceipt = new javax.swing.JLabel();
        itemfrmMailAdmin = new javax.swing.JPanel();
        itemfrmMailAdmin1 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblMail = new javax.swing.JTable();
        jPanel9 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        txtNameAccountMail = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        txtTitleMail = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        txtMessageMail = new javax.swing.JTextArea();
        rdbIsReadMail = new javax.swing.JRadioButton();
        btnDeleteMail = new javax.swing.JButton();
        lblIDMail = new javax.swing.JLabel();
        btnReadMail1 = new javax.swing.JButton();
        jPanel10 = new javax.swing.JPanel();
        cboFilterMail = new javax.swing.JComboBox<>();
        jLabel12 = new javax.swing.JLabel();
        itemfrmClassAdmin = new javax.swing.JPanel();
        itemfrmClassAdmin1 = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        lblNameClass = new javax.swing.JLabel();
        txtSearchClass = new javax.swing.JTextField();
        txtFindClass = new javax.swing.JButton();
        jScrollPane7 = new javax.swing.JScrollPane();
        tblClass = new javax.swing.JTable();
        jPanel12 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        cboCourseClass = new javax.swing.JComboBox<>();
        jLabel23 = new javax.swing.JLabel();
        txtSlotClass = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        txtStartDayClass = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        cboTeacherClass = new javax.swing.JComboBox<>();
        btnUpdateClass = new javax.swing.JButton();
        btnCreateClass = new javax.swing.JButton();
        btnDeleteClass = new javax.swing.JButton();
        jLabel26 = new javax.swing.JLabel();
        txtTimeClass = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        txtSlotNowClass = new javax.swing.JTextField();
        lblIDClass = new javax.swing.JLabel();
        btnUpdateSchedule = new javax.swing.JButton();
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
        itemfrmTeacherAdmin1 = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        lblSearchTeacher = new javax.swing.JLabel();
        txtSearchTeacher = new javax.swing.JTextField();
        btnFindTeacher = new javax.swing.JButton();
        jScrollPane8 = new javax.swing.JScrollPane();
        tblTeacher = new javax.swing.JTable();
        jPanel14 = new javax.swing.JPanel();
        lblNameTeacher = new javax.swing.JLabel();
        txtNameTeacher = new javax.swing.JTextField();
        lblBirthdayTeacher = new javax.swing.JLabel();
        txtDoBTeacher = new javax.swing.JTextField();
        lblPhoneTeacher = new javax.swing.JLabel();
        txtPhoneTeacher = new javax.swing.JTextField();
        lblIDCardTeacher = new javax.swing.JLabel();
        txtIDCardTeacher = new javax.swing.JTextField();
        lblAddressTeacher = new javax.swing.JLabel();
        jScrollPane9 = new javax.swing.JScrollPane();
        txtAddressTeacher = new javax.swing.JTextArea();
        lblGenderTeacher = new javax.swing.JLabel();
        rdbMaleTeacher = new javax.swing.JRadioButton();
        rdbFemaleTeacher = new javax.swing.JRadioButton();
        btnUpdateTeacher = new javax.swing.JButton();
        btnDeleteTeacher = new javax.swing.JButton();
        btnCreateTeacher = new javax.swing.JButton();

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
        itemLogout.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                itemLogoutMouseClicked(evt);
            }
        });

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

        tblReceipt.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane4.setViewportView(tblReceipt);

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder("Search"));

        jLabel9.setText("Name:");

        jLabel19.setText("Price: ");

        btnFindReceipt.setBackground(new java.awt.Color(255, 51, 51));
        btnFindReceipt.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnFindReceipt.setForeground(new java.awt.Color(255, 255, 255));
        btnFindReceipt.setText("Find");
        btnFindReceipt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFindReceiptActionPerformed(evt);
            }
        });

        btnFindReceipt1.setBackground(new java.awt.Color(102, 102, 255));
        btnFindReceipt1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnFindReceipt1.setForeground(new java.awt.Color(255, 255, 255));
        btnFindReceipt1.setText("Reload");
        btnFindReceipt1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFindReceipt1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addComponent(jLabel19))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtFindNameReceipt)
                            .addComponent(txtFindPriceReceipt, javax.swing.GroupLayout.DEFAULT_SIZE, 163, Short.MAX_VALUE)))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(btnFindReceipt, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
                        .addComponent(btnFindReceipt1, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txtFindNameReceipt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(txtFindPriceReceipt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 59, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnFindReceipt, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnFindReceipt1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder("Receipt"));

        jLabel20.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel20.setText("Total receipt:");

        lblTotalReceipt.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lblTotalReceipt.setForeground(new java.awt.Color(51, 0, 255));
        lblTotalReceipt.setText("jLabel9");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel20)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblTotalReceipt)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(lblTotalReceipt))
                .addContainerGap(51, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout itemfrmReportAdmin1Layout = new javax.swing.GroupLayout(itemfrmReportAdmin1);
        itemfrmReportAdmin1.setLayout(itemfrmReportAdmin1Layout);
        itemfrmReportAdmin1Layout.setHorizontalGroup(
            itemfrmReportAdmin1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(itemfrmReportAdmin1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 580, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(itemfrmReportAdmin1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        itemfrmReportAdmin1Layout.setVerticalGroup(
            itemfrmReportAdmin1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(itemfrmReportAdmin1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(itemfrmReportAdmin1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, itemfrmReportAdmin1Layout.createSequentialGroup()
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout itemfrmReportAdminLayout = new javax.swing.GroupLayout(itemfrmReportAdmin);
        itemfrmReportAdmin.setLayout(itemfrmReportAdminLayout);
        itemfrmReportAdminLayout.setHorizontalGroup(
            itemfrmReportAdminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 849, Short.MAX_VALUE)
            .addGroup(itemfrmReportAdminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(itemfrmReportAdminLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(itemfrmReportAdmin1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        itemfrmReportAdminLayout.setVerticalGroup(
            itemfrmReportAdminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 449, Short.MAX_VALUE)
            .addGroup(itemfrmReportAdminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(itemfrmReportAdminLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(itemfrmReportAdmin1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        tblMail.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tblMail.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblMailMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(tblMail);

        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder("Infomation Email"));

        jLabel13.setText("Name:");

        txtNameAccountMail.setEditable(false);

        jLabel21.setText("Title");

        txtTitleMail.setEditable(false);

        jLabel22.setText("Message");

        txtMessageMail.setEditable(false);
        txtMessageMail.setColumns(20);
        txtMessageMail.setRows(5);
        jScrollPane6.setViewportView(txtMessageMail);

        rdbIsReadMail.setText("Is Read");

        btnDeleteMail.setBackground(new java.awt.Color(255, 51, 51));
        btnDeleteMail.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnDeleteMail.setForeground(new java.awt.Color(255, 255, 255));
        btnDeleteMail.setText("Delete");
        btnDeleteMail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteMailActionPerformed(evt);
            }
        });

        lblIDMail.setText("ID");

        btnReadMail1.setBackground(new java.awt.Color(255, 51, 51));
        btnReadMail1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnReadMail1.setForeground(new java.awt.Color(255, 255, 255));
        btnReadMail1.setText("Read");
        btnReadMail1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReadMail1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel13)
                            .addComponent(jLabel21))
                        .addGap(57, 57, 57)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtTitleMail)
                            .addComponent(txtNameAccountMail)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel22)
                            .addComponent(lblIDMail))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addComponent(btnReadMail1, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(57, 57, 57)
                                .addComponent(btnDeleteMail, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(rdbIsReadMail))))
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(txtNameAccountMail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21)
                    .addComponent(txtTitleMail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel22)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rdbIsReadMail)
                    .addComponent(lblIDMail))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnDeleteMail, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnReadMail1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel10.setBorder(javax.swing.BorderFactory.createTitledBorder("Filter"));

        cboFilterMail.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        cboFilterMail.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "All", "Read", "Unread" }));
        cboFilterMail.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cboFilterMailMouseClicked(evt);
            }
        });
        cboFilterMail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboFilterMailActionPerformed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel12.setText("Is read");

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addComponent(jLabel12)
                .addGap(27, 27, 27)
                .addComponent(cboFilterMail, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cboFilterMail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12))
                .addContainerGap(25, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout itemfrmMailAdmin1Layout = new javax.swing.GroupLayout(itemfrmMailAdmin1);
        itemfrmMailAdmin1.setLayout(itemfrmMailAdmin1Layout);
        itemfrmMailAdmin1Layout.setHorizontalGroup(
            itemfrmMailAdmin1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(itemfrmMailAdmin1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(itemfrmMailAdmin1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        itemfrmMailAdmin1Layout.setVerticalGroup(
            itemfrmMailAdmin1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(itemfrmMailAdmin1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(itemfrmMailAdmin1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(itemfrmMailAdmin1Layout.createSequentialGroup()
                        .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );

        javax.swing.GroupLayout itemfrmMailAdminLayout = new javax.swing.GroupLayout(itemfrmMailAdmin);
        itemfrmMailAdmin.setLayout(itemfrmMailAdminLayout);
        itemfrmMailAdminLayout.setHorizontalGroup(
            itemfrmMailAdminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 849, Short.MAX_VALUE)
            .addGroup(itemfrmMailAdminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(itemfrmMailAdminLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(itemfrmMailAdmin1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        itemfrmMailAdminLayout.setVerticalGroup(
            itemfrmMailAdminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 449, Short.MAX_VALUE)
            .addGroup(itemfrmMailAdminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(itemfrmMailAdminLayout.createSequentialGroup()
                    .addGap(20, 20, 20)
                    .addComponent(itemfrmMailAdmin1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGap(21, 21, 21)))
        );

        jPanel11.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Search", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 14))); // NOI18N

        lblNameClass.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblNameClass.setText("Name: ");

        txtSearchClass.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        txtFindClass.setBackground(new java.awt.Color(255, 51, 51));
        txtFindClass.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txtFindClass.setForeground(new java.awt.Color(255, 255, 255));
        txtFindClass.setText("Find");
        txtFindClass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFindClassActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(lblNameClass)
                .addGap(18, 18, 18)
                .addComponent(txtSearchClass)
                .addGap(18, 18, 18)
                .addComponent(txtFindClass, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNameClass)
                    .addComponent(txtSearchClass, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtFindClass, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        tblClass.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tblClass.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblClassMouseClicked(evt);
            }
        });
        jScrollPane7.setViewportView(tblClass);

        jPanel12.setBackground(new java.awt.Color(204, 204, 204));

        jLabel11.setText("Course");

        jLabel23.setText("Slot");

        jLabel24.setText("Start day");

        jLabel25.setText("Teacher");

        cboTeacherClass.setToolTipText("");

        btnUpdateClass.setBackground(new java.awt.Color(51, 51, 255));
        btnUpdateClass.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnUpdateClass.setForeground(new java.awt.Color(255, 255, 255));
        btnUpdateClass.setText("UPDATE");
        btnUpdateClass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateClassActionPerformed(evt);
            }
        });

        btnCreateClass.setBackground(new java.awt.Color(51, 51, 255));
        btnCreateClass.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnCreateClass.setForeground(new java.awt.Color(255, 255, 255));
        btnCreateClass.setText("CREATE");
        btnCreateClass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCreateClassActionPerformed(evt);
            }
        });

        btnDeleteClass.setBackground(new java.awt.Color(51, 51, 255));
        btnDeleteClass.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnDeleteClass.setForeground(new java.awt.Color(255, 255, 255));
        btnDeleteClass.setText("DELETE");
        btnDeleteClass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteClassActionPerformed(evt);
            }
        });

        jLabel26.setText("Time");

        jLabel27.setText("Slot now");

        lblIDClass.setText("ID");

        btnUpdateSchedule.setBackground(new java.awt.Color(51, 51, 255));
        btnUpdateSchedule.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnUpdateSchedule.setForeground(new java.awt.Color(255, 255, 255));
        btnUpdateSchedule.setText("UPDATE SCHEDULE");
        btnUpdateSchedule.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateScheduleActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11)
                            .addComponent(jLabel23)
                            .addComponent(jLabel27))
                        .addGap(70, 70, 70)
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtSlotClass)
                            .addComponent(txtSlotNowClass)
                            .addComponent(cboCourseClass, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel24)
                            .addComponent(jLabel25)
                            .addComponent(jLabel26)
                            .addComponent(lblIDClass))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtStartDayClass)
                            .addComponent(cboTeacherClass, 0, 213, Short.MAX_VALUE)
                            .addComponent(txtTimeClass))))
                .addGap(26, 26, 26))
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnCreateClass, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnUpdateClass, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(14, 14, 14)
                        .addComponent(btnDeleteClass, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGap(82, 82, 82)
                        .addComponent(btnUpdateSchedule, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(cboCourseClass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23)
                    .addComponent(txtSlotClass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel27)
                    .addComponent(txtSlotNowClass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel24)
                    .addComponent(txtStartDayClass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel25)
                    .addComponent(cboTeacherClass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel26)
                    .addComponent(txtTimeClass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(lblIDClass)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnUpdateSchedule, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnUpdateClass, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDeleteClass, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCreateClass, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout itemfrmClassAdmin1Layout = new javax.swing.GroupLayout(itemfrmClassAdmin1);
        itemfrmClassAdmin1.setLayout(itemfrmClassAdmin1Layout);
        itemfrmClassAdmin1Layout.setHorizontalGroup(
            itemfrmClassAdmin1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(itemfrmClassAdmin1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(itemfrmClassAdmin1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 466, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        itemfrmClassAdmin1Layout.setVerticalGroup(
            itemfrmClassAdmin1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(itemfrmClassAdmin1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(itemfrmClassAdmin1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(itemfrmClassAdmin1Layout.createSequentialGroup()
                        .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        javax.swing.GroupLayout itemfrmClassAdminLayout = new javax.swing.GroupLayout(itemfrmClassAdmin);
        itemfrmClassAdmin.setLayout(itemfrmClassAdminLayout);
        itemfrmClassAdminLayout.setHorizontalGroup(
            itemfrmClassAdminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 861, Short.MAX_VALUE)
            .addGroup(itemfrmClassAdminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(itemfrmClassAdminLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(itemfrmClassAdmin1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        itemfrmClassAdminLayout.setVerticalGroup(
            itemfrmClassAdminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 459, Short.MAX_VALUE)
            .addGroup(itemfrmClassAdminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(itemfrmClassAdminLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(itemfrmClassAdmin1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
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

        jPanel13.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Search", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 14))); // NOI18N

        lblSearchTeacher.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblSearchTeacher.setText("Name: ");

        txtSearchTeacher.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        btnFindTeacher.setBackground(new java.awt.Color(255, 51, 51));
        btnFindTeacher.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnFindTeacher.setForeground(new java.awt.Color(255, 255, 255));
        btnFindTeacher.setText("Find");
        btnFindTeacher.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFindTeacherActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(lblSearchTeacher)
                .addGap(18, 18, 18)
                .addComponent(txtSearchTeacher)
                .addGap(18, 18, 18)
                .addComponent(btnFindTeacher, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSearchTeacher)
                    .addComponent(txtSearchTeacher, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnFindTeacher, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        tblTeacher.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tblTeacher.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblTeacherMouseClicked(evt);
            }
        });
        jScrollPane8.setViewportView(tblTeacher);

        jPanel14.setBackground(new java.awt.Color(204, 204, 204));
        jPanel14.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        lblNameTeacher.setText("Name:");

        lblBirthdayTeacher.setText("Birthday:");

        lblPhoneTeacher.setText("Phone:");

        lblIDCardTeacher.setText("ID Card:");

        lblAddressTeacher.setText("Address:");

        txtAddressTeacher.setColumns(20);
        txtAddressTeacher.setRows(5);
        jScrollPane9.setViewportView(txtAddressTeacher);

        lblGenderTeacher.setText("Gender:");

        rdbMaleTeacher.setSelected(true);
        rdbMaleTeacher.setText("Male");

        rdbFemaleTeacher.setText("Female");

        btnUpdateTeacher.setBackground(new java.awt.Color(51, 51, 255));
        btnUpdateTeacher.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnUpdateTeacher.setForeground(new java.awt.Color(255, 255, 255));
        btnUpdateTeacher.setText("UPDATE");
        btnUpdateTeacher.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateTeacherActionPerformed(evt);
            }
        });

        btnDeleteTeacher.setBackground(new java.awt.Color(51, 51, 255));
        btnDeleteTeacher.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnDeleteTeacher.setForeground(new java.awt.Color(255, 255, 255));
        btnDeleteTeacher.setText("DELETE");
        btnDeleteTeacher.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteTeacherActionPerformed(evt);
            }
        });

        btnCreateTeacher.setBackground(new java.awt.Color(51, 51, 255));
        btnCreateTeacher.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnCreateTeacher.setForeground(new java.awt.Color(255, 255, 255));
        btnCreateTeacher.setText("CREATE");
        btnCreateTeacher.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCreateTeacherActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel14Layout.createSequentialGroup()
                        .addComponent(lblPhoneTeacher)
                        .addGap(48, 48, 48)
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtDoBTeacher)
                            .addComponent(txtPhoneTeacher)
                            .addComponent(txtNameTeacher)))
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblAddressTeacher)
                            .addComponent(lblBirthdayTeacher)
                            .addComponent(lblNameTeacher)
                            .addComponent(lblIDCardTeacher)
                            .addComponent(lblGenderTeacher))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel14Layout.createSequentialGroup()
                                .addComponent(rdbMaleTeacher)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(rdbFemaleTeacher)
                                .addGap(58, 58, 58))
                            .addComponent(txtIDCardTeacher)
                            .addComponent(jScrollPane9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 238, Short.MAX_VALUE))))
                .addGap(27, 27, 27))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createSequentialGroup()
                .addContainerGap(12, Short.MAX_VALUE)
                .addComponent(btnCreateTeacher, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnUpdateTeacher, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addComponent(btnDeleteTeacher, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNameTeacher)
                    .addComponent(txtNameTeacher, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblBirthdayTeacher)
                    .addComponent(txtDoBTeacher, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPhoneTeacher)
                    .addComponent(txtPhoneTeacher, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblAddressTeacher)
                    .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblIDCardTeacher)
                    .addComponent(txtIDCardTeacher, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblGenderTeacher)
                    .addComponent(rdbMaleTeacher)
                    .addComponent(rdbFemaleTeacher))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 58, Short.MAX_VALUE)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnUpdateTeacher, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDeleteTeacher, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCreateTeacher, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16))
        );

        javax.swing.GroupLayout itemfrmTeacherAdmin1Layout = new javax.swing.GroupLayout(itemfrmTeacherAdmin1);
        itemfrmTeacherAdmin1.setLayout(itemfrmTeacherAdmin1Layout);
        itemfrmTeacherAdmin1Layout.setHorizontalGroup(
            itemfrmTeacherAdmin1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(itemfrmTeacherAdmin1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(itemfrmTeacherAdmin1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane8)
                    .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        itemfrmTeacherAdmin1Layout.setVerticalGroup(
            itemfrmTeacherAdmin1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(itemfrmTeacherAdmin1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(itemfrmTeacherAdmin1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(itemfrmTeacherAdmin1Layout.createSequentialGroup()
                        .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        javax.swing.GroupLayout itemfrmTeacherAdminLayout = new javax.swing.GroupLayout(itemfrmTeacherAdmin);
        itemfrmTeacherAdmin.setLayout(itemfrmTeacherAdminLayout);
        itemfrmTeacherAdminLayout.setHorizontalGroup(
            itemfrmTeacherAdminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 845, Short.MAX_VALUE)
            .addGroup(itemfrmTeacherAdminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(itemfrmTeacherAdminLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(itemfrmTeacherAdmin1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        itemfrmTeacherAdminLayout.setVerticalGroup(
            itemfrmTeacherAdminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 465, Short.MAX_VALUE)
            .addGroup(itemfrmTeacherAdminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(itemfrmTeacherAdminLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(itemfrmTeacherAdmin1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
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
     try {
         loadTableTeacher();
     } catch (SQLException ex) {
         java.util.logging.Logger.getLogger(DashboardAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
     }
    }//GEN-LAST:event_itemTeacherMouseClicked

    private void itemLogoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_itemLogoMouseClicked
        showDashboard();
    }//GEN-LAST:event_itemLogoMouseClicked

    private void itemClassMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_itemClassMouseClicked
       showClass();
     try {
         loadTableClass();
     } catch (SQLException ex) {
         java.util.logging.Logger.getLogger(DashboardAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
     }
    }//GEN-LAST:event_itemClassMouseClicked

    private void itemMailMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_itemMailMouseClicked
        showMail();
        loadTableMail();
    }//GEN-LAST:event_itemMailMouseClicked

    private void itemReportMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_itemReportMouseClicked
        showReport();
        loadTableReceipt();
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

    private void btnFindReceiptActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFindReceiptActionPerformed
        listReceipt=receipt.getAllReceipt();
        modelReceipt=(DefaultTableModel) tblReceipt.getModel();
        modelReceipt.setRowCount(0);
        float total=0;
        if(txtFindNameReceipt.getText().isEmpty()&& txtFindPriceReceipt.getText().isEmpty()){
            JOptionPane.showMessageDialog(rootPane, "Vui lòng nhập tên học viên hoặc giá khóa học");
            return;
        }
        for(Receipt receipt: listReceipt){
            if(!txtFindNameReceipt.getText().isEmpty()&& txtFindPriceReceipt.getText().isEmpty()){
                if(receipt.getName().equals(txtFindNameReceipt.getText())){
                    Object []row=new Object[]{receipt.getName(),receipt.getNameCourse(),receipt.getPrice(),receipt.getStartDay()};
                    modelReceipt.addRow(row);
                    total+=receipt.getPrice();
                }
            }else if(txtFindNameReceipt.getText().isEmpty()&& !txtFindPriceReceipt.getText().isEmpty()){
                if(receipt.getPrice()==Float.parseFloat(txtFindPriceReceipt.getText())){
                    Object []row=new Object[]{receipt.getName(),receipt.getNameCourse(),receipt.getPrice(),receipt.getStartDay()};
                    modelReceipt.addRow(row);
                    total+=receipt.getPrice();
                }
            }else if(!txtFindNameReceipt.getText().isEmpty()&& !txtFindPriceReceipt.getText().isEmpty()){
                if(receipt.getPrice()==Float.parseFloat(txtFindPriceReceipt.getText()) && receipt.getName().equals(txtFindNameReceipt.getText())){
                    Object []row=new Object[]{receipt.getName(),receipt.getNameCourse(),receipt.getPrice(),receipt.getStartDay()};
                    modelReceipt.addRow(row);
                    total+=receipt.getPrice();
                }
            }
        }

        lblTotalReceipt.setText(total+" $");
        tblReceipt.setModel(modelReceipt);
    }//GEN-LAST:event_btnFindReceiptActionPerformed

    private void btnFindReceipt1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFindReceipt1ActionPerformed
        loadTableReceipt();
    }//GEN-LAST:event_btnFindReceipt1ActionPerformed

    private void tblMailMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblMailMouseClicked
        int index=tblMail.getSelectedRow();
        Mail mail=listMail.get(index);
        lblIDMail.setText(""+mail.getId());
        txtNameAccountMail.setText(mail.getName());
        txtTitleMail.setText(mail.getTitle());
        txtMessageMail.setText(mail.getMessage());
        if(mail.isIsRead())
        rdbIsReadMail.setSelected(true);
    }//GEN-LAST:event_tblMailMouseClicked

    private void btnDeleteMailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteMailActionPerformed
        if(!txtNameAccountMail.getText().isEmpty()){
            mail.deleteMail(Integer.parseInt(lblIDMail.getText()));
            loadTableMail();
            JOptionPane.showMessageDialog(rootPane,"Xóa email thành công");
        }else{
            JOptionPane.showMessageDialog(rootPane,"Vui lòng chọn email");
        }
    }//GEN-LAST:event_btnDeleteMailActionPerformed

    private void btnReadMail1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReadMail1ActionPerformed
        if(!txtNameAccountMail.getText().isEmpty()){
            try {
                mail.updateMail(Integer.parseInt(lblIDMail.getText()));
                loadTableMail();
                JOptionPane.showMessageDialog(rootPane,"Đã đọc email");

            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }else{
            JOptionPane.showMessageDialog(rootPane,"Vui lòng chọn email");
        }
    }//GEN-LAST:event_btnReadMail1ActionPerformed

    private void cboFilterMailMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cboFilterMailMouseClicked

    }//GEN-LAST:event_cboFilterMailMouseClicked

    private void cboFilterMailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboFilterMailActionPerformed
        if(cboFilterMail.getSelectedItem().toString().equals("All")){
            loadTableMail();
        }else if(cboFilterMail.getSelectedItem().toString().equals("Read")){
            listMail=mail.getAllMail();
            modelMail=(DefaultTableModel) tblMail.getModel();
            modelMail.setRowCount(0);
            if(checkLoadColumnMAil==false){
                modelMail.addColumn("ID");
                modelMail.addColumn("ID Account");
                modelMail.addColumn("Name");
                modelMail.addColumn("Title");
                modelMail.addColumn("Is Read");
                checkLoadColumnMAil=true;
            }
            for(Mail mail : listMail){
                if(mail.isIsRead()){
                    Object []row=new Object[]{mail.getId(),mail.getIdAccount(),mail.getName(),mail.getTitle(),mail.isIsRead()};
                    modelMail.addRow(row);
                }
            }
            tblMail.setModel(modelMail);
        }else{
            listMail=mail.getAllMail();
            modelMail=(DefaultTableModel) tblMail.getModel();
            modelMail.setRowCount(0);
            if(checkLoadColumnMAil==false){
                modelMail.addColumn("ID");
                modelMail.addColumn("ID Account");
                modelMail.addColumn("Name");
                modelMail.addColumn("Title");
                modelMail.addColumn("Is Read");
                checkLoadColumnMAil=true;
            }
            for(Mail mail : listMail){
                if(!mail.isIsRead()){
                    Object []row=new Object[]{mail.getId(),mail.getIdAccount(),mail.getName(),mail.getTitle(),mail.isIsRead()};
                    modelMail.addRow(row);
                }
            }
            tblMail.setModel(modelMail);
        }
    }//GEN-LAST:event_cboFilterMailActionPerformed

    private void txtFindClassActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFindClassActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFindClassActionPerformed

    private void tblClassMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblClassMouseClicked
        int index=tblClass.getSelectedRow();
        CourseSchedule course=listClass.get(index);
        lblIDClass.setVisible(false);
        lblIDClass.setText(""+course.getId());
        cboCourseClass.setSelectedIndex(course.getIdCourse()-1);
        txtSlotClass.setText(""+course.getSlot());
        txtSlotNowClass.setText(""+course.getSlotNow());
        txtStartDayClass.setText(course.getStartDay().toString());
        try {
            cboTeacherClass.setSelectedItem(getNameTeacher(course.getIdTeacher()));
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_tblClassMouseClicked

    private void btnUpdateClassActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateClassActionPerformed
        try {
            int idCourse=getIDCourse(cboCourseClass.getSelectedItem().toString());
            int idTeacher=getIDTeacher(cboTeacherClass.getSelectedItem().toString());
            if(txtSlotClass.getText().toString().isEmpty() || txtStartDayClass.getText().toString().isEmpty()){
                JOptionPane.showMessageDialog(rootPane, "Vui lòng đầy đủ thông tin");
            }else if(!checkNumber(txtSlotClass.getText().toString())){
                JOptionPane.showMessageDialog(rootPane, "Vui lòng nhập số lượng học viên");
            }else if(!checkDate(txtStartDayClass.getText().toString())){
                JOptionPane.showMessageDialog(rootPane, "Vui lòng nhập ngày bắt đầu(yyyy-mm-dd)");
            }else if(checkDayStart(txtStartDayClass.getText())==0){
                JOptionPane.showMessageDialog(rootPane, "Vui lòng nhập ngày bắt đầu là thứ 2 hoặc 3");
            }else if(!checkTime(txtTimeClass.getText().toString())){
                JOptionPane.showMessageDialog(rootPane, "Vui lòng nhập giờ học");
            }else{
                String endDate=getEndDate(txtStartDayClass.getText().toString());
                System.out.println(idCourse +" "+ txtSlotClass.getText().toString()+" "+idTeacher);
                daoClass.updateCourseSchedule(Integer.parseInt(lblIDClass.getText()),idCourse, Integer.parseInt(txtSlotClass.getText().toString()), Integer.parseInt(txtSlotNowClass.getText().toString()), Date.valueOf(txtStartDayClass.getText().toString()), Date.valueOf(endDate), idTeacher);
                int idCourseSchedule=daoClass.getIDCourseSchedule(idCourse, Date.valueOf(txtStartDayClass.getText().toString()), idTeacher);
                JOptionPane.showConfirmDialog(rootPane, "Sửa lịch thành công");
                loadTableClass();
            }
        } catch (SQLException ex) {
                        ex.printStackTrace();
        }
    }//GEN-LAST:event_btnUpdateClassActionPerformed

    private void btnCreateClassActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCreateClassActionPerformed
        try {
            int idCourse=getIDCourse(cboCourseClass.getSelectedItem().toString());
            int idTeacher=getIDTeacher(cboTeacherClass.getSelectedItem().toString());
            if(txtSlotClass.getText().toString().isEmpty() || txtStartDayClass.getText().toString().isEmpty()){
                JOptionPane.showMessageDialog(rootPane, "Vui lòng đầy đủ thông tin");
            }else if(!checkNumber(txtSlotClass.getText().toString())){
                JOptionPane.showMessageDialog(rootPane, "Vui lòng nhập số lượng học viên");
            }else if(!checkNumber(txtSlotNowClass.getText().toString())){
                JOptionPane.showMessageDialog(rootPane, "Vui lòng nhập số lượng học viên hiện tại");
            }else if(!checkDate(txtStartDayClass.getText().toString())){
                JOptionPane.showMessageDialog(rootPane, "Vui lòng nhập ngày bắt đầu(yyyy-mm-dd)");
            }else if(checkDayStart(txtStartDayClass.getText())==0){
                JOptionPane.showMessageDialog(rootPane, "Vui lòng nhập ngày bắt đầu là thứ 2 hoặc 3");
            }else if(!checkTime(txtTimeClass.getText().toString())){
                JOptionPane.showMessageDialog(rootPane, "Vui lòng nhập giờ học");
            }else{
                String endDate=getEndDate(txtStartDayClass.getText().toString());
                System.out.println(idCourse +" "+ txtSlotClass.getText().toString()+" "+idTeacher);
                daoClass.insertCourseSchedule(idCourse, Integer.parseInt(txtSlotClass.getText().toString()), Integer.parseInt(txtSlotNowClass.getText().toString()), Date.valueOf(txtStartDayClass.getText().toString()), Date.valueOf(endDate), idTeacher);
                int idCourseSchedule=daoClass.getIDCourseSchedule(idCourse, Date.valueOf(txtStartDayClass.getText().toString()), idTeacher);
                // insert Schedule
                Date currentDate = Date.valueOf(txtStartDayClass.getText().toString());

                while (currentDate.compareTo(Date.valueOf(endDate)) <= 0) {
                    schedule.insertSchedule(currentDate, txtTimeClass.getText().trim(), 2, idCourseSchedule);
                    // Tăng ngày lên 1 (date.getTime() + 24 * 60 * 60 * 1000)
                    currentDate = new Date(currentDate.getTime() + 24 * 60 * 60 * 1000*2);
                    schedule.insertSchedule(currentDate, txtTimeClass.getText().trim(), 2, idCourseSchedule);
                    currentDate = new Date(currentDate.getTime() + 24 * 60 * 60 * 1000*2);
                    schedule.insertSchedule(currentDate, txtTimeClass.getText().trim(), 2, idCourseSchedule);
                    currentDate = new Date(currentDate.getTime() + 24 * 60 * 60 * 1000*3);
                }
                JOptionPane.showConfirmDialog(rootPane, "Tạo lịch thành công");
                loadTableClass();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_btnCreateClassActionPerformed

    private void btnDeleteClassActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteClassActionPerformed
        if(lblIDClass.getText().isEmpty()){
            JOptionPane.showConfirmDialog(rootPane, "Vui lòng chọn dòng cần xóa");
        }
        else{
            try{
                schedule.deleteSchedule(Integer.parseInt(lblIDClass.getText().toString()));
                daoClass.deleteCourseSchedule(Integer.parseInt(lblIDClass.getText().toString()));
                JOptionPane.showConfirmDialog(rootPane, "Xóa thành công");
                loadTableClass();
            }catch(Exception e){
                e.printStackTrace();
            }

        }
    }//GEN-LAST:event_btnDeleteClassActionPerformed

    private void btnUpdateScheduleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateScheduleActionPerformed
        if(checkInt(lblIDClass.getText())){
            ScheduleAdmin frm=new ScheduleAdmin(Integer.parseInt(lblIDClass.getText()));
            frm.setVisible(true);
        }else{
            JOptionPane.showMessageDialog(rootPane,"Vui lòng chọn lịch học");
        }
    }//GEN-LAST:event_btnUpdateScheduleActionPerformed

    private void btnFindTeacherActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFindTeacherActionPerformed
        if(txtSearchTeacher.getText().toString().isEmpty()){
            JOptionPane.showConfirmDialog(rootPane, "Vui lòng nhập tên giảng viên");
        }else{
            int check=0;
            String gender="";
            modelTeacher.setRowCount(0);
            for(Teacher t: listTeacher){
                if(txtSearchTeacher.getText().toString().equals(t.getName())){
                    if(t.isGender())
                    gender="Nam";
                    else
                    gender="Nu";
                    Object []row=new Object[]{t.getId(),t.getName(),t.getDob(),t.getPhone(),t.getAddress(),t.getIdCard(),gender};
                    modelTeacher.addRow(row);
                }

            }
        }
    }//GEN-LAST:event_btnFindTeacherActionPerformed

    private void tblTeacherMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblTeacherMouseClicked
        int index=tblTeacher.getSelectedRow();
        Teacher teacher=listTeacher.get(index);
        txtNameTeacher.setText(teacher.getName());
        txtDoBTeacher.setText(teacher.getDob().toString());
        txtPhoneTeacher.setText(teacher.getPhone());
        txtAddressTeacher.setText(teacher.getAddress());
        txtIDCardTeacher.setText(""+teacher.getIdCard());
        if(teacher.isGender())
        rdbMaleTeacher.setSelected(true);
        else
        rdbFemaleTeacher.setSelected(true);
    }//GEN-LAST:event_tblTeacherMouseClicked

    private void btnUpdateTeacherActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateTeacherActionPerformed
        if(txtNameTeacher.getText().isEmpty() || txtDoBTeacher.getText().isEmpty() || txtPhoneTeacher.getText().isEmpty() || txtAddressTeacher.getText().isEmpty() || txtIDCardTeacher.getText().isEmpty()){
            JOptionPane.showConfirmDialog(rootPane,"Vui lòng nhập đầy đủ thông tin");
        }else if(!checkDate(txtDoBTeacher.getText().trim())){
            JOptionPane.showConfirmDialog(rootPane, "Vui lòng nhập đúng định dạng yyyy/mm/dd");
        }else if(!checkNumber(txtPhoneTeacher.getText().trim())){
            JOptionPane.showConfirmDialog(rootPane, "Vui lòng nhập số điện thoại");
        }else if(!checkNumber(txtIDCardTeacher.getText().trim())){
            JOptionPane.showConfirmDialog(rootPane, "Vui lòng nhập số căn cước");
        }else{
            boolean gender=true;
            if(rdbFemaleTeacher.isSelected())
            gender=false;
            try {
                teacher.updateTeacher(txtNameTeacher.getText().trim(), Date.valueOf(txtDoBTeacher.getText()), gender, txtAddressTeacher.getText().trim(), txtIDCardTeacher.getText().trim(), txtPhoneTeacher.getText().trim());
                JOptionPane.showConfirmDialog(rootPane, "Sửa giáo viên mới thành công");
                loadTableTeacher();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }//GEN-LAST:event_btnUpdateTeacherActionPerformed

    private void btnDeleteTeacherActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteTeacherActionPerformed
        if(txtIDCardTeacher.getText().trim().isEmpty()){
            JOptionPane.showConfirmDialog(rootPane,"Vui lòng nhập");
        }else if(!checkNumber(txtIDCardTeacher.getText().trim())){
            JOptionPane.showConfirmDialog(rootPane, "Vui lòng nhập số căn cước");
        }else{
            try {
                teacher.deleteTeacher(txtIDCardTeacher.getText());
                loadTableTeacher();
                JOptionPane.showConfirmDialog(rootPane, "Xóa giáo viên mới thành công");
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }//GEN-LAST:event_btnDeleteTeacherActionPerformed

    private void btnCreateTeacherActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCreateTeacherActionPerformed
        if(txtNameTeacher.getText().isEmpty() || txtDoBTeacher.getText().isEmpty() || txtPhoneTeacher.getText().isEmpty() || txtAddressTeacher.getText().isEmpty() || txtIDCardTeacher.getText().isEmpty()){
            JOptionPane.showConfirmDialog(rootPane,"Vui lòng nhập đầy đủ thông tin");
        }else if(!checkDate(txtDoBTeacher.getText().trim())){
            JOptionPane.showConfirmDialog(rootPane, "Vui lòng nhập đúng định dạng yyyy/mm/dd");
        }else if(!checkNumber(txtPhoneTeacher.getText().trim())){
            JOptionPane.showConfirmDialog(rootPane, "Vui lòng nhập số điện thoại");
        }else if(!checkNumber(txtIDCardTeacher.getText().trim())){
            JOptionPane.showConfirmDialog(rootPane, "Vui lòng nhập số căn cước");
        }else{
            boolean gender=true;
            if(rdbFemaleTeacher.isSelected())
            gender=false;
            try {
                teacher.insertTeacher(txtNameTeacher.getText().trim(), Date.valueOf(txtDoBTeacher.getText()), gender, txtAddressTeacher.getText().trim(), txtIDCardTeacher.getText().trim(), txtPhoneTeacher.getText().trim());
                JOptionPane.showConfirmDialog(rootPane, "Thêm giáo viên mới thành công");
                loadTableTeacher();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }//GEN-LAST:event_btnCreateTeacherActionPerformed

    private void itemLogoutMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_itemLogoutMouseClicked
        int response = JOptionPane.showConfirmDialog(this,
                "Are you sure you want to exit?",
                "Confirm Exit",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);

        if (response == JOptionPane.YES_OPTION) {
            Login frm = new Login();
            frm.setVisible(true);
            this.setVisible(false);
        }
    }//GEN-LAST:event_itemLogoutMouseClicked

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
    private javax.swing.JButton btnCreateClass;
    private javax.swing.JButton btnCreateStudent;
    private javax.swing.JButton btnCreateTeacher;
    private javax.swing.JButton btnDeleteClass;
    private javax.swing.JButton btnDeleteMail;
    private javax.swing.JButton btnDeleteStudent;
    private javax.swing.JButton btnDeleteTeacher;
    private javax.swing.JButton btnEditStudent;
    private javax.swing.JButton btnFind;
    private javax.swing.JButton btnFindReceipt;
    private javax.swing.JButton btnFindReceipt1;
    private javax.swing.JButton btnFindTeacher;
    private javax.swing.JButton btnReadMail1;
    private javax.swing.JButton btnUpdateClass;
    private javax.swing.JButton btnUpdateSchedule;
    private javax.swing.JButton btnUpdateTeacher;
    private javax.swing.JComboBox<String> cboCourseClass;
    private javax.swing.JComboBox<String> cboFilterMail;
    private javax.swing.JComboBox<String> cboTeacherClass;
    private javax.swing.JPanel itemClass;
    private javax.swing.JPanel itemDashboard;
    private javax.swing.JPanel itemLogo;
    private javax.swing.JPanel itemLogout;
    private javax.swing.JPanel itemMail;
    private javax.swing.JPanel itemReport;
    private javax.swing.JPanel itemStudent;
    private javax.swing.JPanel itemTeacher;
    private javax.swing.JPanel itemfrmClassAdmin;
    private javax.swing.JPanel itemfrmClassAdmin1;
    private javax.swing.JPanel itemfrmDashboardAdmin;
    private javax.swing.JPanel itemfrmMailAdmin;
    private javax.swing.JPanel itemfrmMailAdmin1;
    private javax.swing.JPanel itemfrmReportAdmin;
    private javax.swing.JPanel itemfrmReportAdmin1;
    private javax.swing.JPanel itemfrmStudentAdmin;
    private javax.swing.JPanel itemfrmTeacherAdmin;
    private javax.swing.JPanel itemfrmTeacherAdmin1;
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
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
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
    private javax.swing.JLabel lblAddressTeacher;
    private javax.swing.JLabel lblBirthdayTeacher;
    private javax.swing.JLabel lblGenderTeacher;
    private javax.swing.JLabel lblIDCardTeacher;
    private javax.swing.JLabel lblIDClass;
    private javax.swing.JLabel lblIDMail;
    private javax.swing.JLabel lblNameClass;
    private javax.swing.JLabel lblNameTeacher;
    private javax.swing.JLabel lblPhoneTeacher;
    private javax.swing.JLabel lblSearchTeacher;
    private javax.swing.JLabel lblTotalReceipt;
    private javax.swing.ButtonGroup rboGroupGender;
    private javax.swing.JRadioButton rdbFemaleTeacher;
    private javax.swing.JRadioButton rdbIsReadMail;
    private javax.swing.JRadioButton rdbMaleTeacher;
    private javax.swing.JRadioButton rdoFemaleStudent;
    private javax.swing.JRadioButton rdoMaleStudent;
    private javax.swing.JTable tblClass;
    private javax.swing.JTable tblCourseDashboard;
    private javax.swing.JTable tblMail;
    private javax.swing.JTable tblReceipt;
    private javax.swing.JTable tblStudent;
    private javax.swing.JTable tblTeacher;
    private javax.swing.JTextArea txtAddressTeacher;
    private javax.swing.JTextArea txtAdressStudent;
    private javax.swing.JTextField txtCCCDStudent;
    private javax.swing.JTextField txtDateStudent;
    private javax.swing.JTextField txtDoBTeacher;
    private javax.swing.JButton txtFindClass;
    private javax.swing.JTextField txtFindNameReceipt;
    private javax.swing.JTextField txtFindPriceReceipt;
    private javax.swing.JTextField txtIDCardTeacher;
    private javax.swing.JTextArea txtMessageMail;
    private javax.swing.JTextField txtNameAccountMail;
    private javax.swing.JTextField txtNameStudent;
    private javax.swing.JTextField txtNameTeacher;
    private javax.swing.JTextField txtPhoneStudent;
    private javax.swing.JTextField txtPhoneTeacher;
    private javax.swing.JTextField txtSearchClass;
    private javax.swing.JTextField txtSearchStudent;
    private javax.swing.JTextField txtSearchTeacher;
    private javax.swing.JTextField txtSlotClass;
    private javax.swing.JTextField txtSlotNowClass;
    private javax.swing.JTextField txtStartDayClass;
    private javax.swing.JTextField txtTimeClass;
    private javax.swing.JTextField txtTitleMail;
    // End of variables declaration//GEN-END:variables
}

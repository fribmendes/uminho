/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package habitat;

import controllers.ContactsController;
import controllers.ControllerFactory;
import controllers.FamiliesController;
import controllers.MembersController;
import controllers.RepresentativesController;
import data.DataException;
import data.RepositoryFactory;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import models.Activity;
import models.Application;
import models.Contact;
import models.Family;
import models.Representative;
import models.SimpleMember;

/**
 *
 * @author tiago
 */
public class MainWindow extends javax.swing.JFrame {
    
    private Family currentFamily;
    private Representative currentRepresentative;
    private List<SimpleMember> currentMembers;
    private List<Contact> representativeContacts;
    private Application currentApplication;
    /**
     * Creates new form GUI
     */
    public MainWindow() {
        this.setIconImage((new ImageIcon("etc/logo.png")).getImage());
        initComponents();
        listFamilies();
        
        familyList.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
        public void valueChanged(ListSelectionEvent event) {
            String code = familyList.getValueAt(familyList.getSelectedRow(), 0).toString();
            int i = Integer.parseInt(code);
            try {
                currentFamily = ControllerFactory.getFamiliesController().find(i);
                currentRepresentative = ControllerFactory.getRepresentativesController().findBy(
                    new HashMap<String, Object>() {{ put("familyID", currentFamily.getId());}}
            ).get(0);
                currentMembers = ControllerFactory.getMembersController().findBy(new HashMap<String, Object>() {{
                put("familyID", currentFamily.getId());
                
                representativeContacts = ControllerFactory.getContactsController().findBy(new HashMap<String, Object>() {{
                    put("OwnerType", "Representante");
                    put("Owner", currentRepresentative.getId());
                }});
                
                currentApplication = ControllerFactory.getApplicationsController().findBy(new HashMap<String, Object>() {{
                    put("familyId", currentFamily.getId());
                }}).get(0);
            }});
                setCurrentFamily();
                setFamilyMembers();
            } catch(DataException e) { }
        }
        });
    }

    
    public void listFamilies() {
        List<Family> families  = new ArrayList<>();
        try {
            families = ControllerFactory.getFamiliesController().all();
            for(final Family f : families) {
                ((DefaultTableModel)familyList.getModel()).addRow(new Object[]{f.getId(), f.getName(), ControllerFactory.getRepresentativesController().findBy(
                    new HashMap<String, Object>() {{ put("familyID", f.getId());}}
                ).get(0).getName(), f.getAddress()});
            }
        } catch (DataException e) {
            JOptionPane.showMessageDialog(this, "Erro a ler dados");
        }
    }
    
    public void setCurrentFamily() {
        Family f = currentFamily;
        familyName.setText(f.getName());
        familyAddress.setText(f.getAddress());
        familyIncome.setText(Float.toString(f.getIncome()));
        familyApproved.setSelected(f.getApproved());
        familyNotes.setText(f.getObservations());        
        Representative r = currentRepresentative;
        
        familyRep.setText(r.getName());
        repBirthDate.setText(Util.dateToStr(r.getBirthDate()));
        repMaritalStatus.setSelectedItem(r.getMaritalStatus());
        repEducation.setText(r.getEducation());
        repNif.setText(r.getNif());
        repNib.setText(r.getNib());
        mainWindowRepProf.setSelectedIndex(0);
        repBirthPlace.setText(r.getBirthPlace());
        repNationality.setSelectedItem(r.getNationality());
        
        ((DefaultTableModel)repContacts.getModel()).setRowCount(0);
            
        for(Contact c : representativeContacts)
            ((DefaultTableModel)repContacts.getModel()).addRow(new Object[]{c.getType(), c.getValue()});
    }
    
    public void setFamilyMembers() {
        final Family f = currentFamily;
        List<SimpleMember> members = currentMembers;
            
        ((DefaultTableModel)memberList.getModel()).setRowCount(0);
            
        for(final SimpleMember m : members)
            ((DefaultTableModel)memberList.getModel()).addRow(new Object[]{ m.getName(), Util.dateToStr(m.getBirthDate()), m.getKinship() });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel4 = new javax.swing.JPanel();
        jDialog1 = new javax.swing.JDialog();
        jFormattedTextField1 = new javax.swing.JFormattedTextField();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel33 = new javax.swing.JPanel();
        familySubTabbedPane = new javax.swing.JTabbedPane();
        jPanel34 = new javax.swing.JPanel();
        jLabel58 = new javax.swing.JLabel();
        jLabel59 = new javax.swing.JLabel();
        jLabel60 = new javax.swing.JLabel();
        jLabel61 = new javax.swing.JLabel();
        jLabel62 = new javax.swing.JLabel();
        jSeparator15 = new javax.swing.JSeparator();
        jLabel63 = new javax.swing.JLabel();
        jLabel64 = new javax.swing.JLabel();
        jLabel65 = new javax.swing.JLabel();
        jLabel66 = new javax.swing.JLabel();
        jLabel88 = new javax.swing.JLabel();
        editFamily = new javax.swing.JButton();
        deleteFamily = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jScrollPane14 = new javax.swing.JScrollPane();
        familyNotes = new javax.swing.JTextArea();
        familyName = new javax.swing.JTextField();
        familyAddress = new javax.swing.JTextField();
        familyIncome = new javax.swing.JFormattedTextField();
        familyApproved = new javax.swing.JCheckBox();
        jScrollPane15 = new javax.swing.JScrollPane();
        repContacts = new javax.swing.JTable();
        familyRep = new javax.swing.JTextField();
        repBirthDate = new javax.swing.JFormattedTextField();
        repEducation = new javax.swing.JTextField();
        repNif = new javax.swing.JTextField();
        repNib = new javax.swing.JTextField();
        jLabel67 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        deleteRepContact = new javax.swing.JButton();
        addRepContact = new javax.swing.JButton();
        jLabel28 = new javax.swing.JLabel();
        cancelEditFamily = new javax.swing.JButton();
        submitEditFamily = new javax.swing.JButton();
        repMaritalStatus = new javax.swing.JComboBox();
        jLabel19 = new javax.swing.JLabel();
        repBirthPlace = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        repNationality = new javax.swing.JComboBox();
        mainWindowRepProf = new javax.swing.JComboBox();
        jPanel35 = new javax.swing.JPanel();
        addApplication = new javax.swing.JButton();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        previousApplication = new javax.swing.JButton();
        nextApplication = new javax.swing.JButton();
        deleteApplication = new javax.swing.JButton();
        editApplication = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        applicationQuestionnaire = new javax.swing.JTable();
        applicationDate = new javax.swing.JFormattedTextField();
        applicationLocation = new javax.swing.JTextField();
        applicationApprovalDate = new javax.swing.JFormattedTextField();
        applicationId = new javax.swing.JTextField();
        jScrollPane9 = new javax.swing.JScrollPane();
        applicationNotes = new javax.swing.JTextArea();
        editQuestionnaire = new javax.swing.JButton();
        applicationPriority = new javax.swing.JComboBox();
        applicationApproved = new javax.swing.JCheckBox();
        jPanel36 = new javax.swing.JPanel();
        editMember = new javax.swing.JButton();
        removeMember = new javax.swing.JButton();
        addMember = new javax.swing.JButton();
        jScrollPane18 = new javax.swing.JScrollPane();
        memberList = new javax.swing.JTable();
        jLabel90 = new javax.swing.JLabel();
        familySearch = new javax.swing.JTextField();
        addFamily = new javax.swing.JButton();
        jScrollPane6 = new javax.swing.JScrollPane();
        familyList = new javax.swing.JTable();
        searchFamiliesButton = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jPanel45 = new javax.swing.JPanel();
        jTabbedPane15 = new javax.swing.JTabbedPane();
        jPanel46 = new javax.swing.JPanel();
        jLabel121 = new javax.swing.JLabel();
        jLabel122 = new javax.swing.JLabel();
        jLabel123 = new javax.swing.JLabel();
        jLabel124 = new javax.swing.JLabel();
        jScrollPane13 = new javax.swing.JScrollPane();
        projectNotes = new javax.swing.JTextArea();
        jLabel17 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        editProject = new javax.swing.JButton();
        deleteProject = new javax.swing.JButton();
        jLabel33 = new javax.swing.JLabel();
        jtfPDEC = new javax.swing.JFormattedTextField();
        projectSignDate = new javax.swing.JFormattedTextField();
        jtfPDF = new javax.swing.JFormattedTextField();
        projectEta = new javax.swing.JFormattedTextField();
        projectStartDate = new javax.swing.JFormattedTextField();
        cancelEditProject = new javax.swing.JButton();
        submitEditProject = new javax.swing.JButton();
        projectBudget = new javax.swing.JFormattedTextField();
        projectFinalCost = new javax.swing.JFormattedTextField();
        jtfPVP = new javax.swing.JFormattedTextField();
        jLabel46 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        projectName = new javax.swing.JTextField();
        jPanel10 = new javax.swing.JPanel();
        jLabel32 = new javax.swing.JLabel();
        jScrollPane11 = new javax.swing.JScrollPane();
        paymentPlanNotes = new javax.swing.JTextArea();
        addPayment = new javax.swing.JButton();
        deletePayment = new javax.swing.JButton();
        editPaymentPlan = new javax.swing.JButton();
        jScrollPane12 = new javax.swing.JScrollPane();
        paymentPlan = new javax.swing.JTable();
        jLabel31 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        addTask = new javax.swing.JButton();
        removeTask = new javax.swing.JButton();
        jScrollPane22 = new javax.swing.JScrollPane();
        taskList = new javax.swing.JTable();
        taskViewDetails = new javax.swing.JButton();
        jLabel38 = new javax.swing.JLabel();
        searchProjectsField = new javax.swing.JTextField();
        addProject = new javax.swing.JButton();
        searchProjectsButton = new javax.swing.JButton();
        jScrollPane16 = new javax.swing.JScrollPane();
        projectList = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jPanel24 = new javax.swing.JPanel();
        jTabbedPane12 = new javax.swing.JTabbedPane();
        jPanel25 = new javax.swing.JPanel();
        jLabel110 = new javax.swing.JLabel();
        jLabel111 = new javax.swing.JLabel();
        jLabel112 = new javax.swing.JLabel();
        jLabel114 = new javax.swing.JLabel();
        jScrollPane8 = new javax.swing.JScrollPane();
        volunteerNotes = new javax.swing.JTextArea();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        editVolunteer = new javax.swing.JButton();
        deleteVolunteer = new javax.swing.JButton();
        volunteerName = new javax.swing.JTextField();
        volunteerAddress = new javax.swing.JTextField();
        volunteerTeam = new javax.swing.JComboBox();
        addVolunteerTeam = new javax.swing.JButton();
        volunteerBirthDate = new javax.swing.JFormattedTextField();
        volunteerEducation = new javax.swing.JTextField();
        volunteerBirthPlace = new javax.swing.JTextField();
        volunteerMaritalStatus = new javax.swing.JComboBox();
        volunteerNationality = new javax.swing.JComboBox();
        volunteerNib = new javax.swing.JTextField();
        volunteerNif = new javax.swing.JTextField();
        cancelEditVolunteer = new javax.swing.JButton();
        submitEditVolunteer = new javax.swing.JButton();
        jPanel11 = new javax.swing.JPanel();
        volunteerViewEventDetails = new javax.swing.JButton();
        jScrollPane19 = new javax.swing.JScrollPane();
        volunteerEventList = new javax.swing.JTable();
        jPanel12 = new javax.swing.JPanel();
        volunteerViewProjectDetails = new javax.swing.JButton();
        jScrollPane20 = new javax.swing.JScrollPane();
        jTable8 = new javax.swing.JTable();
        addVolunteer = new javax.swing.JButton();
        jScrollPane10 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jLabel91 = new javax.swing.JLabel();
        searchVolunteersField = new javax.swing.JTextField();
        searchVolunteersButton = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jPanel43 = new javax.swing.JPanel();
        jTabbedPane14 = new javax.swing.JTabbedPane();
        jPanel44 = new javax.swing.JPanel();
        jLabel115 = new javax.swing.JLabel();
        jLabel130 = new javax.swing.JLabel();
        jLabel131 = new javax.swing.JLabel();
        jLabel133 = new javax.swing.JLabel();
        jScrollPane32 = new javax.swing.JScrollPane();
        eventObservation = new javax.swing.JTextArea();
        editEventButton = new javax.swing.JButton();
        removeEventButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        eventDate = new javax.swing.JFormattedTextField();
        eventAddress = new javax.swing.JTextField();
        eventRaisedValue = new javax.swing.JFormattedTextField();
        cancelEditEventButton = new javax.swing.JButton();
        submitEditEventButton = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        eventParticipantNmb = new javax.swing.JSpinner();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane17 = new javax.swing.JScrollPane();
        participantsTable = new javax.swing.JTable();
        editParticipantsButton = new javax.swing.JButton();
        jLabel139 = new javax.swing.JLabel();
        eventSearch = new javax.swing.JTextField();
        addEventButton = new javax.swing.JButton();
        jScrollPane21 = new javax.swing.JScrollPane();
        eventTable = new javax.swing.JTable();
        searchEventsButton = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        jPanel47 = new javax.swing.JPanel();
        jTabbedPane16 = new javax.swing.JTabbedPane();
        jPanel48 = new javax.swing.JPanel();
        jLabel125 = new javax.swing.JLabel();
        jLabel126 = new javax.swing.JLabel();
        jLabel127 = new javax.swing.JLabel();
        jLabel128 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        editDonorButton = new javax.swing.JButton();
        removeDonorButton = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        donorObservation = new javax.swing.JTextArea();
        donorName = new javax.swing.JTextField();
        donorAddress = new javax.swing.JTextField();
        donorNIF = new javax.swing.JTextField();
        donorActivity = new javax.swing.JTextField();
        donorType = new javax.swing.JTextField();
        jScrollPane23 = new javax.swing.JScrollPane();
        donorContacts = new javax.swing.JTable();
        addDonorContact = new javax.swing.JButton();
        deleteDonorContact = new javax.swing.JButton();
        cancelDonorEdit = new javax.swing.JButton();
        submitDonorEdit = new javax.swing.JButton();
        jPanel9 = new javax.swing.JPanel();
        jScrollPane24 = new javax.swing.JScrollPane();
        addDonationTable = new javax.swing.JTable();
        removeDonationButton = new javax.swing.JButton();
        addDonationButton = new javax.swing.JButton();
        confirmRemoveDonationButton = new javax.swing.JButton();
        cancelRemoveDonationButton = new javax.swing.JButton();
        jLabel53 = new javax.swing.JLabel();
        addDonorButton = new javax.swing.JButton();
        jButton40 = new javax.swing.JButton();
        jTextField10 = new javax.swing.JTextField();
        jScrollPane25 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        searchDonorsButton = new javax.swing.JButton();
        jPanel13 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jDialog1Layout = new javax.swing.GroupLayout(jDialog1.getContentPane());
        jDialog1.getContentPane().setLayout(jDialog1Layout);
        jDialog1Layout.setHorizontalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jDialog1Layout.setVerticalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        jFormattedTextField1.setText("jFormattedTextField1");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Habitat");
        setBackground(new java.awt.Color(54, 79, 194));
        setResizable(false);

        jTabbedPane1.setBackground(new java.awt.Color(22, 113, 204));
        jTabbedPane1.setFont(new java.awt.Font("Calibri Light", 0, 18)); // NOI18N
        jTabbedPane1.setMinimumSize(new java.awt.Dimension(500, 245));
        jTabbedPane1.setOpaque(true);
        jTabbedPane1.setPreferredSize(new java.awt.Dimension(312, 463));

        familySubTabbedPane.setBackground(new java.awt.Color(255, 255, 255));
        familySubTabbedPane.setTabPlacement(javax.swing.JTabbedPane.LEFT);
        familySubTabbedPane.setFont(new java.awt.Font("Calibri Light", 0, 14)); // NOI18N

        jPanel34.setBackground(new java.awt.Color(255, 255, 255));

        jLabel58.setText("Nome:");

        jLabel59.setText("Morada:");

        jLabel60.setText("Candidatura Aprovada");

        jLabel61.setText("Rendimento:");

        jLabel62.setText("Observações:");

        jLabel63.setFont(new java.awt.Font("Calibri Light", 0, 24)); // NOI18N
        jLabel63.setText("Família");

        jLabel64.setText("Nome:");

        jLabel65.setText("Data de Nasc.:");

        jLabel66.setText("Escolaridade:");

        jLabel88.setText("Profissão:");

        editFamily.setText("Editar Familia");
        editFamily.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editFamilyActionPerformed(evt);
            }
        });

        deleteFamily.setText("Remover Familia");
        deleteFamily.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteFamilyActionPerformed(evt);
            }
        });

        jLabel8.setText("Estado Civil:");

        jLabel16.setText("NIF:");

        jLabel20.setText("NIB:");

        jScrollPane14.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        familyNotes.setEditable(false);
        familyNotes.setColumns(20);
        familyNotes.setLineWrap(true);
        familyNotes.setRows(5);
        familyNotes.setToolTipText("");
        familyNotes.setWrapStyleWord(true);
        jScrollPane14.setViewportView(familyNotes);
        familyNotes.setDocument(new JTextAreaLimit(500));

        familyName.setEditable(false);

        familyAddress.setEditable(false);

        familyIncome.setEditable(false);
        familyIncome.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00; #0.00"))));

        familyApproved.setBackground(new java.awt.Color(255, 255, 255));
        familyApproved.setBorder(null);
        familyApproved.setEnabled(false);

        repContacts.setAutoCreateRowSorter(true);
        repContacts.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Tipo", "Contacto"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        repContacts.setEnabled(false);
        repContacts.getTableHeader().setReorderingAllowed(false);
        jScrollPane15.setViewportView(repContacts);
        if (repContacts.getColumnModel().getColumnCount() > 0) {
            repContacts.getColumnModel().getColumn(0).setCellEditor(new DefaultCellEditor(
                new javax.swing.JComboBox(
                    new javax.swing.DefaultComboBoxModel(
                        new String[] { "Telefone", "Telemóvel", "Email", "Fax" }))));
    }

    familyRep.setEditable(false);

    repBirthDate.setEditable(false);
    repBirthDate.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(new java.text.SimpleDateFormat("dd/MM/yyyy"))));
    repBirthDate.setToolTipText("dd/MM/aaaa");
    repBirthDate.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            repBirthDateActionPerformed(evt);
        }
    });

    repEducation.setEditable(false);

    repNif.setEditable(false);

    repNib.setEditable(false);

    jLabel67.setFont(new java.awt.Font("Calibri Light", 0, 24)); // NOI18N
    jLabel67.setText("Representante");

    jLabel44.setText("€");

    deleteRepContact.setText("-");
    deleteRepContact.setMaximumSize(new java.awt.Dimension(41, 23));
    deleteRepContact.setMinimumSize(new java.awt.Dimension(41, 23));
    deleteRepContact.setVisible(false);
    deleteRepContact.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            deleteRepContactActionPerformed(evt);
        }
    });

    addRepContact.setText("+");
    addRepContact.setVisible(false);
    addRepContact.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            addRepContactActionPerformed(evt);
        }
    });

    jLabel28.setText("Contactos:");

    cancelEditFamily.setText("Cancelar");
    cancelEditFamily.setVisible(false);
    cancelEditFamily.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            cancelEditFamilyActionPerformed(evt);
        }
    });

    submitEditFamily.setText("Submeter");
    submitEditFamily.setVisible(false);
    submitEditFamily.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            submitEditFamilyActionPerformed(evt);
        }
    });

    repMaritalStatus.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Solteiro(a)", "Casado(a)", "Divorciado(a)", "Viúvo(a)" }));
    repMaritalStatus.setEnabled(false);

    jLabel19.setText("Naturalidade:");

    repBirthPlace.setEditable(false);

    jLabel22.setText("Nacionalidade:");

    repNationality.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Afeganistão", "África do Sul", "Albânia", "Alemanha", "Andorra", "Angola", "Arábia Saudita", "Argélia", "Argentina", "Armênia", "Austrália", "Áustria", "Azerbaijão", "Bahamas", "Bahrein", "Bangladesh", "Barbados", "Bélgica", "Belize", "Benin", "Bielorrússia", "Bolívia", "Bósnia", "Botsuana", "Brasil", "Brunei", "Bulgária", "Burkina-Fasso", "Burundi", "Butão", "Cabo Verde", "Camarões", "Camboja", "Canadá", "Catar", "Cazaquistão", "Chade", "Chile", "China", "Chipre", "Cingapura", "Colômbia", "Comores", "Congo", "Coréia do Norte", "Coréia do Sul", "Costado Marfim", "Costa Rica", "Croácia", "Cuba", "Dinamarca", "Djibuti", "Dominica", "Egito", "El Salvador", "Emirados Árabes Unidos", "Equador", "Eritreia", "Escócia", "Eslováquia", "Eslovênia", "Espanha", "Estados Unidos", "Estônia", "Etiópia", "Federação Russa", "Fiji", "Filipinas", "Finlândia", "Formosa Taiwan", "França", "Gabão", "Gâmbia", "Gana", "Geórgia", "Grã-Bretanha", "Granada", "Grécia", "Groenlândia", "Guatemala", "Guiana", "Guiana Francesa", "Guiné", "Guiné Bissau", "Guiné Equatorial", "Haiti", "Holanda", "Honduras", "Hungria", "Iêmen", "Ilhas Marshall", "Ilhas Salomão", "Índia", "Indonésia", "Irão", "Iraque", "Irlanda", "Irlanda do Norte", "Islândia", "Israel", "Itália", "Jamaica", "Japão", "Jordânia", "Kiribati", "Kuwait", "Laos", "Lesoto", "Letônia", "Líbano", "Libéria", "Líbia", "Liechtenstein", "Lituânia", "Luxemburgo", "Macedônia", "Madagascar", "Malásia", "Malauí", "Maldivas", "Mali", "Malta", "Marrocos", "Maurício", "Mauritânia", "México", "Mianmar", "Micronésia", "Moçambique", "Moldávia", "Mônaco", "Mongólia", "Namíbia", "Nauru", "Nepal", "Nicarágua", "Níger", "Nigéria", "Noruega", "Nova Zelândia", "Omã", "Palau", "Panamá", "Papua Nova Guiné", "Paquistão", "Paraguai", "Peru", "Polônia", "Porto Rico", "Portugal", "Quênia", "Quirguistão", "Reino Unido", "Rep.Centro-Africana", "Rep.Dominicana", "República Tcheca", "Romênia", "Ruanda", "Samoa", "SanMarino", "Santa Lúcia", "São Cristóvão e Névis", "São Tomé e Príncipe", "São Vicente e Granadinas", "Seicheles", "Senegal", "Serra Leoa", "Sérvia e Montenegro", "Síria", "Somália", "Sri-Lanka", "Suazilândia", "Sudão", "Suécia", "Suáça", "Suriname", "Tadjiquistão", "Tailândia", "Tanzânia", "Togo", "Tonga", "Trinidade Tobago", "Tunísia", "Turcomenistão", "Turquia", "Tuvalu", "Ucrânia", "Uganda", "Uruguai", "Uzbequistão", "Vanuatu", "Vaticano", "Venezuela", "Vietnã", "Zaire", "Zâmbia", "Zimbábue " }));
    repNationality.setSelectedIndex(149);
    repNationality.setEnabled(false);
    repNationality.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            repNationalityActionPerformed(evt);
        }
    });

    javax.swing.GroupLayout jPanel34Layout = new javax.swing.GroupLayout(jPanel34);
    jPanel34.setLayout(jPanel34Layout);
    jPanel34Layout.setHorizontalGroup(
        jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel34Layout.createSequentialGroup()
            .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel34Layout.createSequentialGroup()
                    .addGap(22, 22, 22)
                    .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel66)
                        .addComponent(jLabel65)
                        .addComponent(jLabel88)
                        .addComponent(jLabel64)))
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel34Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel28, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel19, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel22, javax.swing.GroupLayout.Alignment.TRAILING))))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel34Layout.createSequentialGroup()
                    .addComponent(jLabel67)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(jPanel34Layout.createSequentialGroup()
                    .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel34Layout.createSequentialGroup()
                            .addComponent(repNationality, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(addRepContact, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(deleteRepContact, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(familyRep)
                        .addComponent(jScrollPane15)
                        .addGroup(jPanel34Layout.createSequentialGroup()
                            .addComponent(cancelEditFamily)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(submitEditFamily)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(editFamily)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(deleteFamily))
                        .addGroup(jPanel34Layout.createSequentialGroup()
                            .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(jPanel34Layout.createSequentialGroup()
                                    .addComponent(repBirthDate, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel8))
                                .addGroup(jPanel34Layout.createSequentialGroup()
                                    .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(repEducation, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(repBirthPlace, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(mainWindowRepProf, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(29, 29, 29)
                                    .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel20)
                                        .addComponent(jLabel16))
                                    .addGap(0, 0, Short.MAX_VALUE)))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(repNib, javax.swing.GroupLayout.DEFAULT_SIZE, 199, Short.MAX_VALUE)
                                .addComponent(repMaritalStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(repNif))))
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        .addGroup(jPanel34Layout.createSequentialGroup()
            .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel34Layout.createSequentialGroup()
                    .addGap(19, 19, 19)
                    .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel34Layout.createSequentialGroup()
                            .addComponent(jLabel62)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jScrollPane14, javax.swing.GroupLayout.PREFERRED_SIZE, 489, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel34Layout.createSequentialGroup()
                            .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel61)
                                .addComponent(jLabel59)
                                .addComponent(jLabel58))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(familyName, javax.swing.GroupLayout.DEFAULT_SIZE, 484, Short.MAX_VALUE)
                                .addComponent(familyAddress)
                                .addGroup(jPanel34Layout.createSequentialGroup()
                                    .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel63)
                                        .addGroup(jPanel34Layout.createSequentialGroup()
                                            .addComponent(familyApproved)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(jLabel60))
                                        .addGroup(jPanel34Layout.createSequentialGroup()
                                            .addComponent(familyIncome, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(jLabel44)))
                                    .addGap(103, 103, 103))))))
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel34Layout.createSequentialGroup()
                    .addGap(0, 21, Short.MAX_VALUE)
                    .addComponent(jSeparator15, javax.swing.GroupLayout.PREFERRED_SIZE, 615, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addContainerGap())
    );
    jPanel34Layout.setVerticalGroup(
        jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel34Layout.createSequentialGroup()
            .addGap(17, 17, 17)
            .addComponent(jLabel63)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel58)
                .addComponent(familyName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGap(6, 6, 6)
            .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel59)
                .addComponent(familyAddress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel61)
                .addComponent(familyIncome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel44))
            .addGap(9, 9, 9)
            .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                .addComponent(familyApproved)
                .addComponent(jLabel60))
            .addGap(30, 30, 30)
            .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jScrollPane14, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel62))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jSeparator15, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jLabel67)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel64)
                .addComponent(familyRep, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel65)
                .addComponent(jLabel8)
                .addComponent(repBirthDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(repMaritalStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel66)
                .addComponent(jLabel16)
                .addComponent(repEducation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(repNif, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel88)
                .addComponent(jLabel20)
                .addComponent(repNib, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(mainWindowRepProf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel19)
                .addComponent(repBirthPlace, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(addRepContact)
                .addComponent(deleteRepContact, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel22)
                .addComponent(repNationality, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGap(5, 5, 5)
            .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jScrollPane15, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel28))
            .addGap(21, 21, 21)
            .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(submitEditFamily)
                .addComponent(cancelEditFamily)
                .addComponent(editFamily)
                .addComponent(deleteFamily))
            .addContainerGap())
    );

    familySubTabbedPane.addTab("Informações", jPanel34);

    jPanel35.setBackground(new java.awt.Color(255, 255, 255));

    addApplication.setText("Nova Candidatura...");
    addApplication.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            addApplicationActionPerformed(evt);
        }
    });

    jLabel23.setText("Código:");

    jLabel24.setText("Aprovada:");

    jLabel25.setText("Terreno:");

    jLabel26.setText("Data de Aplicação:");

    jLabel27.setText("Data de Aprovação:");

    previousApplication.setText("<");

    nextApplication.setText(">");

    deleteApplication.setText("Remover");

    editApplication.setText("Editar");

    jLabel5.setText("Prioridade:");

    jLabel21.setText("Observações:");

    jLabel29.setText("Questionário");

    jLabel43.setText("1/3");

    applicationQuestionnaire.setAutoCreateRowSorter(true);
    applicationQuestionnaire.setModel(new javax.swing.table.DefaultTableModel(
        new Object [][] {
            {null, null},
            {null, null},
            {null, null},
            {null, null}
        },
        new String [] {
            "Pergunta", "Resposta"
        }
    ) {
        Class[] types = new Class [] {
            java.lang.String.class, java.lang.String.class
        };
        boolean[] canEdit = new boolean [] {
            true, false
        };

        public Class getColumnClass(int columnIndex) {
            return types [columnIndex];
        }

        public boolean isCellEditable(int rowIndex, int columnIndex) {
            return canEdit [columnIndex];
        }
    });
    applicationQuestionnaire.getTableHeader().setReorderingAllowed(false);
    jScrollPane7.setViewportView(applicationQuestionnaire);

    applicationDate.setEditable(false);
    applicationDate.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(new java.text.SimpleDateFormat("dd/MM/yyyy"))));
    applicationDate.setToolTipText("dd/MM/aaaa");

    applicationLocation.setEditable(false);

    applicationApprovalDate.setEditable(false);
    applicationApprovalDate.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(new java.text.SimpleDateFormat("dd/MM/yyyy"))));
    applicationApprovalDate.setToolTipText("dd/MM/aaaa");

    applicationId.setEditable(false);

    applicationNotes.setEditable(false);
    applicationNotes.setColumns(20);
    applicationNotes.setLineWrap(true);
    applicationNotes.setRows(5);
    applicationNotes.setToolTipText("");
    applicationNotes.setWrapStyleWord(true);
    jScrollPane9.setViewportView(applicationNotes);
    familyNotes.setDocument(new JTextAreaLimit(500));

    editQuestionnaire.setText("Editar Respostas");
    editQuestionnaire.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            editQuestionnaireActionPerformed(evt);
        }
    });

    applicationPriority.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Baixa", "Normal", "Alta" }));
    applicationPriority.setSelectedIndex(-1);

    applicationApproved.setBorder(null);

    javax.swing.GroupLayout jPanel35Layout = new javax.swing.GroupLayout(jPanel35);
    jPanel35.setLayout(jPanel35Layout);
    jPanel35Layout.setHorizontalGroup(
        jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel35Layout.createSequentialGroup()
            .addGroup(jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                .addGroup(jPanel35Layout.createSequentialGroup()
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(previousApplication)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(nextApplication))
                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel35Layout.createSequentialGroup()
                    .addGap(55, 55, 55)
                    .addGroup(jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 541, Short.MAX_VALUE)
                        .addGroup(jPanel35Layout.createSequentialGroup()
                            .addGroup(jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel29)
                                .addGroup(jPanel35Layout.createSequentialGroup()
                                    .addGroup(jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel21)
                                        .addComponent(jLabel5)
                                        .addComponent(jLabel27)
                                        .addComponent(jLabel25)
                                        .addComponent(jLabel24)
                                        .addComponent(jLabel23)
                                        .addComponent(jLabel26))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(applicationDate, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                                            .addComponent(applicationLocation)
                                            .addComponent(applicationApprovalDate, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(applicationId, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jScrollPane9, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                                            .addComponent(applicationPriority, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(applicationApproved))))
                            .addGap(0, 0, Short.MAX_VALUE))
                        .addGroup(jPanel35Layout.createSequentialGroup()
                            .addComponent(addApplication)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(editQuestionnaire, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(deleteApplication, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(editApplication, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)))))
            .addGap(50, 50, 50))
        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel35Layout.createSequentialGroup()
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel43)
            .addGap(85, 85, 85))
    );

    jPanel35Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {addApplication, editApplication});

    jPanel35Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {nextApplication, previousApplication});

    jPanel35Layout.setVerticalGroup(
        jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel35Layout.createSequentialGroup()
            .addGap(29, 29, 29)
            .addComponent(jLabel43)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(previousApplication)
                .addComponent(nextApplication))
            .addGap(1, 1, 1)
            .addGroup(jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel26)
                .addComponent(applicationDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                .addComponent(applicationApproved)
                .addGroup(jPanel35Layout.createSequentialGroup()
                    .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(1, 1, 1)))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel5)
                .addComponent(applicationPriority, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel25)
                .addComponent(applicationLocation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel27)
                .addComponent(applicationApprovalDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel23)
                .addComponent(applicationId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jLabel21)
                .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 39, Short.MAX_VALUE)
            .addComponent(jLabel29)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addComponent(editQuestionnaire)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addGroup(jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(addApplication)
                .addComponent(deleteApplication)
                .addComponent(editApplication))
            .addGap(106, 106, 106))
    );

    jPanel35Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {addApplication, deleteApplication, editApplication});

    familySubTabbedPane.addTab("Candidaturas", jPanel35);

    jPanel36.setBackground(new java.awt.Color(255, 255, 255));

    editMember.setText("Editar Membro");

    removeMember.setText("Remover Membro");

    addMember.setText("Adicionar Membro");
    addMember.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            addMemberActionPerformed(evt);
        }
    });

    memberList.setAutoCreateRowSorter(true);
    memberList.setModel(new javax.swing.table.DefaultTableModel(
        new Object [][] {
            {null, null, null},
            {null, null, null},
            {null, null, null},
            {null, null, null}
        },
        new String [] {
            "Nome", "Data de Nascimento", "Grau de Parentesco"
        }
    ) {
        Class[] types = new Class [] {
            java.lang.String.class, java.lang.String.class, java.lang.String.class
        };
        boolean[] canEdit = new boolean [] {
            false, false, false
        };

        public Class getColumnClass(int columnIndex) {
            return types [columnIndex];
        }

        public boolean isCellEditable(int rowIndex, int columnIndex) {
            return canEdit [columnIndex];
        }
    });
    memberList.getTableHeader().setReorderingAllowed(false);
    jScrollPane18.setViewportView(memberList);

    javax.swing.GroupLayout jPanel36Layout = new javax.swing.GroupLayout(jPanel36);
    jPanel36.setLayout(jPanel36Layout);
    jPanel36Layout.setHorizontalGroup(
        jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel36Layout.createSequentialGroup()
            .addContainerGap()
            .addGroup(jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jScrollPane18)
                .addGroup(jPanel36Layout.createSequentialGroup()
                    .addComponent(addMember)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 146, Short.MAX_VALUE)
                    .addComponent(editMember)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 145, Short.MAX_VALUE)
                    .addComponent(removeMember)))
            .addContainerGap())
    );
    jPanel36Layout.setVerticalGroup(
        jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel36Layout.createSequentialGroup()
            .addContainerGap(40, Short.MAX_VALUE)
            .addComponent(jScrollPane18, javax.swing.GroupLayout.PREFERRED_SIZE, 378, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(40, 40, 40)
            .addGroup(jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(editMember)
                .addComponent(removeMember)
                .addComponent(addMember))
            .addGap(238, 238, 238))
    );

    familySubTabbedPane.addTab("Membros", jPanel36);

    jLabel90.setFont(new java.awt.Font("Calibri Light", 0, 24)); // NOI18N
    jLabel90.setText("Pesquisa");

    addFamily.setText("Adicionar Familia");
    addFamily.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            addFamilyActionPerformed(evt);
        }
    });

    familyList.setAutoCreateRowSorter(true);
    familyList.setModel(new javax.swing.table.DefaultTableModel(
        new Object [][] {

        },
        new String [] {
            "Código", "Nome", "Representante", "Morada"
        }
    ) {
        Class[] types = new Class [] {
            java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
        };
        boolean[] canEdit = new boolean [] {
            false, false, false, false
        };

        public Class getColumnClass(int columnIndex) {
            return types [columnIndex];
        }

        public boolean isCellEditable(int rowIndex, int columnIndex) {
            return canEdit [columnIndex];
        }
    });
    familyList.getTableHeader().setReorderingAllowed(false);
    jScrollPane6.setViewportView(familyList);

    searchFamiliesButton.setText("Pesquisar");

    javax.swing.GroupLayout jPanel33Layout = new javax.swing.GroupLayout(jPanel33);
    jPanel33.setLayout(jPanel33Layout);
    jPanel33Layout.setHorizontalGroup(
        jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel33Layout.createSequentialGroup()
            .addComponent(familySubTabbedPane, javax.swing.GroupLayout.PREFERRED_SIZE, 742, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 393, Short.MAX_VALUE)
                .addGroup(jPanel33Layout.createSequentialGroup()
                    .addComponent(addFamily)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(searchFamiliesButton))
                .addComponent(familySearch)
                .addGroup(jPanel33Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel90)
                    .addGap(0, 0, Short.MAX_VALUE)))
            .addContainerGap())
    );
    jPanel33Layout.setVerticalGroup(
        jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel33Layout.createSequentialGroup()
            .addGap(6, 6, 6)
            .addComponent(jLabel90)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(familySearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(addFamily)
                .addComponent(searchFamiliesButton))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jScrollPane6)
            .addContainerGap())
        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel33Layout.createSequentialGroup()
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(familySubTabbedPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );

    javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
    jPanel1.setLayout(jPanel1Layout);
    jPanel1Layout.setHorizontalGroup(
        jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addComponent(jPanel33, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
    );
    jPanel1Layout.setVerticalGroup(
        jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel1Layout.createSequentialGroup()
            .addComponent(jPanel33, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addContainerGap())
    );

    jTabbedPane1.addTab("Famílias", jPanel1);

    jTabbedPane15.setTabPlacement(javax.swing.JTabbedPane.LEFT);
    jTabbedPane15.setFont(new java.awt.Font("Calibri Light", 0, 14)); // NOI18N

    jPanel46.setBackground(new java.awt.Color(255, 255, 255));

    jLabel121.setText("Nome de Projeto:");

    jLabel122.setText("Data Inicio:");

    jLabel123.setText("Data Final:");

    jLabel124.setText("Observações:");

    projectNotes.setColumns(20);
    projectNotes.setLineWrap(true);
    projectNotes.setRows(5);
    projectNotes.setWrapStyleWord(true);
    jScrollPane13.setViewportView(projectNotes);

    jLabel17.setText("Data Entrega Chaves:");

    jLabel35.setText("Data Assinatura Contrato:");

    jLabel36.setText("Orçamento:");

    jLabel37.setText("Custo Final:");

    jLabel3.setText("Data Final Prevista:");

    editProject.setText("Editar Projeto");
    editProject.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            editProjectActionPerformed(evt);
        }
    });

    deleteProject.setText("Remover Projeto");

    jLabel33.setText("Próxima Prestação:");

    jtfPDEC.setEditable(false);
    jtfPDEC.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(new java.text.SimpleDateFormat("dd/MM/yyyy"))));
    jtfPDEC.setToolTipText("dd/MM/aaaa");

    projectSignDate.setEditable(false);
    projectSignDate.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(new java.text.SimpleDateFormat("dd/MM/yyyy"))));
    projectSignDate.setToolTipText("dd/MM/aaaa");

    jtfPDF.setEditable(false);
    jtfPDF.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(new java.text.SimpleDateFormat("dd/MM/yyyy"))));
    jtfPDF.setToolTipText("dd/MM/aaaa");

    projectEta.setEditable(false);
    projectEta.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(new java.text.SimpleDateFormat("dd/MM/yyyy"))));
    projectEta.setToolTipText("dd/MM/aaaa");

    projectStartDate.setEditable(false);
    projectStartDate.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(new java.text.SimpleDateFormat("dd/MM/yyyy"))));
    projectStartDate.setToolTipText("dd/MM/aaaa");

    cancelEditProject.setText("Cancelar");
    cancelEditProject.setVisible(false);
    cancelEditProject.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            cancelEditProjectActionPerformed(evt);
        }
    });

    submitEditProject.setText("Submeter");
    submitEditProject.setVisible(false);

    projectBudget.setEditable(false);
    projectBudget.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00; #0.00"))));

    projectFinalCost.setEditable(false);
    projectFinalCost.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00; #0.00"))));

    jtfPVP.setEditable(false);
    jtfPVP.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00; #0.00"))));

    jLabel46.setText("€");

    jLabel47.setText("€");

    jLabel48.setText("€");

    projectName.setEditable(false);
    projectName.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            projectNameActionPerformed(evt);
        }
    });

    javax.swing.GroupLayout jPanel46Layout = new javax.swing.GroupLayout(jPanel46);
    jPanel46.setLayout(jPanel46Layout);
    jPanel46Layout.setHorizontalGroup(
        jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel46Layout.createSequentialGroup()
            .addGroup(jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                .addComponent(jLabel124)
                .addGroup(jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel46Layout.createSequentialGroup()
                        .addGap(61, 61, 61)
                        .addComponent(jLabel121))
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel123, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel17, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel122, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel35, javax.swing.GroupLayout.Alignment.TRAILING)))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel46Layout.createSequentialGroup()
                    .addGroup(jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(projectSignDate, javax.swing.GroupLayout.DEFAULT_SIZE, 105, Short.MAX_VALUE)
                        .addComponent(jtfPDEC, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(projectStartDate, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(projectEta, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jtfPDF, javax.swing.GroupLayout.Alignment.LEADING))
                    .addGap(18, 91, Short.MAX_VALUE)
                    .addGroup(jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel33)
                        .addComponent(jLabel37)
                        .addComponent(jLabel36))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel46Layout.createSequentialGroup()
                            .addComponent(projectFinalCost, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabel47, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel46Layout.createSequentialGroup()
                            .addComponent(jtfPVP, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabel48, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel46Layout.createSequentialGroup()
                            .addComponent(projectBudget, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabel46, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addContainerGap(136, Short.MAX_VALUE))
                .addGroup(jPanel46Layout.createSequentialGroup()
                    .addGroup(jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(projectName, javax.swing.GroupLayout.PREFERRED_SIZE, 369, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jScrollPane13, javax.swing.GroupLayout.PREFERRED_SIZE, 369, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(0, 0, Short.MAX_VALUE))))
        .addGroup(jPanel46Layout.createSequentialGroup()
            .addGap(44, 44, 44)
            .addComponent(cancelEditProject)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(submitEditProject)
            .addGap(109, 109, 109)
            .addComponent(editProject, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(deleteProject)
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );
    jPanel46Layout.setVerticalGroup(
        jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel46Layout.createSequentialGroup()
            .addGap(20, 20, 20)
            .addGroup(jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel121)
                .addComponent(projectName, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel37)
                    .addComponent(projectFinalCost, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel47))
                .addGroup(jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel122)
                    .addComponent(projectStartDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addGap(4, 4, 4)
            .addGroup(jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel3)
                .addComponent(projectEta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel33)
                .addComponent(jtfPVP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel48))
            .addGap(1, 1, 1)
            .addGroup(jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel123)
                .addComponent(jtfPDF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(projectBudget, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel46))
            .addGap(4, 4, 4)
            .addGroup(jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jtfPDEC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel46Layout.createSequentialGroup()
                    .addComponent(jLabel17)
                    .addGap(18, 18, 18)
                    .addGroup(jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel35)
                        .addComponent(projectSignDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
            .addGap(18, 18, 18)
            .addGroup(jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jLabel124)
                .addComponent(jScrollPane13, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGap(29, 29, 29)
            .addGroup(jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(cancelEditProject)
                .addComponent(submitEditProject)
                .addComponent(editProject)
                .addComponent(deleteProject))
            .addGap(351, 351, 351))
    );

    jTabbedPane15.addTab("Informações", jPanel46);

    jPanel10.setBackground(new java.awt.Color(255, 255, 255));

    jLabel32.setText("Observações:");

    paymentPlanNotes.setColumns(20);
    paymentPlanNotes.setRows(5);
    jScrollPane11.setViewportView(paymentPlanNotes);

    addPayment.setText("+");
    addPayment.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            addPaymentActionPerformed(evt);
        }
    });

    deletePayment.setText("-");
    deletePayment.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            deletePaymentActionPerformed(evt);
        }
    });

    editPaymentPlan.setText("Editar");

    paymentPlan.setAutoCreateRowSorter(true);
    paymentPlan.setModel(new javax.swing.table.DefaultTableModel(
        new Object [][] {

        },
        new String [] {
            "Valor", "Data Limite", "Paga"
        }
    ) {
        Class[] types = new Class [] {
            java.lang.String.class, java.lang.String.class, java.lang.Object.class
        };

        public Class getColumnClass(int columnIndex) {
            return types [columnIndex];
        }
    });
    paymentPlan.getTableHeader().setReorderingAllowed(false);
    jScrollPane12.setViewportView(paymentPlan);

    jLabel31.setFont(new java.awt.Font("Calibri Light", 0, 24)); // NOI18N
    jLabel31.setText("Plano de Pagamentos");

    javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
    jPanel10.setLayout(jPanel10Layout);
    jPanel10Layout.setHorizontalGroup(
        jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel10Layout.createSequentialGroup()
            .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel10Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane12, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 683, Short.MAX_VALUE)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                            .addGap(0, 0, Short.MAX_VALUE)
                            .addComponent(editPaymentPlan)
                            .addGap(0, 0, Short.MAX_VALUE))))
                .addGroup(jPanel10Layout.createSequentialGroup()
                    .addGap(21, 21, 21)
                    .addComponent(jLabel31)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(addPayment, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(deletePayment, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel10Layout.createSequentialGroup()
                    .addGap(45, 45, 45)
                    .addComponent(jLabel32)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 436, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
            .addContainerGap())
    );
    jPanel10Layout.setVerticalGroup(
        jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel10Layout.createSequentialGroup()
            .addGap(17, 17, 17)
            .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel31)
                .addComponent(addPayment)
                .addComponent(deletePayment))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(editPaymentPlan)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jLabel32)
                .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addContainerGap())
    );

    jTabbedPane15.addTab("Prestações", jPanel10);

    jPanel6.setBackground(new java.awt.Color(255, 255, 255));

    addTask.setText("+");
    addTask.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            addTaskActionPerformed(evt);
        }
    });

    removeTask.setText("-");
    removeTask.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            removeTaskActionPerformed(evt);
        }
    });

    taskList.setAutoCreateRowSorter(true);
    taskList.setModel(new javax.swing.table.DefaultTableModel(
        new Object [][] {

        },
        new String [] {
            "Nome", "Data Início", "Estado"
        }
    ) {
        Class[] types = new Class [] {
            java.lang.String.class, java.lang.String.class, java.lang.String.class
        };

        public Class getColumnClass(int columnIndex) {
            return types [columnIndex];
        }
    });
    taskList.getTableHeader().setReorderingAllowed(false);
    jScrollPane22.setViewportView(taskList);

    taskViewDetails.setText("Detalhes");

    javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
    jPanel6.setLayout(jPanel6Layout);
    jPanel6Layout.setHorizontalGroup(
        jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel6Layout.createSequentialGroup()
            .addContainerGap()
            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jScrollPane22, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 631, Short.MAX_VALUE)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(addTask, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(removeTask, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel6Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(taskViewDetails)
                    .addGap(0, 0, Short.MAX_VALUE)))
            .addContainerGap())
    );
    jPanel6Layout.setVerticalGroup(
        jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
            .addGap(36, 36, 36)
            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(addTask)
                .addComponent(removeTask))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jScrollPane22, javax.swing.GroupLayout.DEFAULT_SIZE, 610, Short.MAX_VALUE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(taskViewDetails)
            .addContainerGap())
    );

    jTabbedPane15.addTab("Tarefas", jPanel6);

    jLabel38.setFont(new java.awt.Font("Calibri Light", 0, 24)); // NOI18N
    jLabel38.setText("Pesquisa");

    addProject.setText("Adicionar projeto");
    addProject.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            addProjectActionPerformed(evt);
        }
    });

    searchProjectsButton.setText("Pesquisar");

    projectList.setAutoCreateRowSorter(true);
    projectList.setModel(new javax.swing.table.DefaultTableModel(
        new Object [][] {
            {null, null, null},
            {null, null, null},
            {null, null, null}
        },
        new String [] {
            "Código", "Nome", "Orçamento"
        }
    ) {
        Class[] types = new Class [] {
            java.lang.Integer.class, java.lang.String.class, java.lang.String.class
        };
        boolean[] canEdit = new boolean [] {
            false, false, false
        };

        public Class getColumnClass(int columnIndex) {
            return types [columnIndex];
        }

        public boolean isCellEditable(int rowIndex, int columnIndex) {
            return canEdit [columnIndex];
        }
    });
    projectList.getTableHeader().setReorderingAllowed(false);
    jScrollPane16.setViewportView(projectList);

    javax.swing.GroupLayout jPanel45Layout = new javax.swing.GroupLayout(jPanel45);
    jPanel45.setLayout(jPanel45Layout);
    jPanel45Layout.setHorizontalGroup(
        jPanel45Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel45Layout.createSequentialGroup()
            .addComponent(jTabbedPane15, javax.swing.GroupLayout.PREFERRED_SIZE, 741, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(jPanel45Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jScrollPane16, javax.swing.GroupLayout.DEFAULT_SIZE, 394, Short.MAX_VALUE)
                .addGroup(jPanel45Layout.createSequentialGroup()
                    .addComponent(addProject)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(searchProjectsButton))
                .addComponent(searchProjectsField)
                .addGroup(jPanel45Layout.createSequentialGroup()
                    .addGap(155, 155, 155)
                    .addComponent(jLabel38)
                    .addGap(0, 0, Short.MAX_VALUE)))
            .addContainerGap())
    );
    jPanel45Layout.setVerticalGroup(
        jPanel45Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel45Layout.createSequentialGroup()
            .addGroup(jPanel45Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel45Layout.createSequentialGroup()
                    .addComponent(jLabel38)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(searchProjectsField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(jPanel45Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(addProject)
                        .addComponent(searchProjectsButton))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jScrollPane16, javax.swing.GroupLayout.DEFAULT_SIZE, 646, Short.MAX_VALUE))
                .addGroup(jPanel45Layout.createSequentialGroup()
                    .addGap(8, 8, 8)
                    .addComponent(jTabbedPane15, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
            .addContainerGap())
    );

    javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
    jPanel2.setLayout(jPanel2Layout);
    jPanel2Layout.setHorizontalGroup(
        jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGap(0, 1151, Short.MAX_VALUE)
        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel45, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );
    jPanel2Layout.setVerticalGroup(
        jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGap(0, 763, Short.MAX_VALUE)
        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel45, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap()))
    );

    jTabbedPane1.addTab("Projetos", jPanel2);

    jTabbedPane12.setBackground(new java.awt.Color(255, 255, 255));
    jTabbedPane12.setTabPlacement(javax.swing.JTabbedPane.LEFT);
    jTabbedPane12.setFont(new java.awt.Font("Calibri Light", 0, 14)); // NOI18N

    jPanel25.setBackground(new java.awt.Color(255, 255, 255));

    jLabel110.setText("Nome:");

    jLabel111.setText("Morada:");

    jLabel112.setText("Equipa:");

    jLabel114.setText("Observações:");

    volunteerNotes.setEditable(false);
    volunteerNotes.setColumns(20);
    volunteerNotes.setLineWrap(true);
    volunteerNotes.setRows(5);
    volunteerNotes.setWrapStyleWord(true);
    jScrollPane8.setViewportView(volunteerNotes);

    jLabel9.setText("Data Nasc:");

    jLabel10.setText("Escolaridade:");

    jLabel11.setText("Estado Civil:");

    jLabel12.setText("NIF:");

    jLabel13.setText("NIB:");

    jLabel14.setText("Naturalidade:");

    jLabel15.setText("Nacionalidade:");

    editVolunteer.setText("Editar Voluntário");
    editVolunteer.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            editVolunteerActionPerformed(evt);
        }
    });

    deleteVolunteer.setText("Remover Voluntário");

    volunteerName.setEditable(false);

    volunteerAddress.setEditable(false);

    volunteerTeam.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " " }));
    volunteerTeam.setEnabled(false);

    addVolunteerTeam.setText("Nova Equipa");
    addVolunteerTeam.setVisible(false);

    volunteerBirthDate.setEditable(false);
    volunteerBirthDate.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(new java.text.SimpleDateFormat("dd/MM/yyyy"))));
    volunteerBirthDate.setToolTipText("dd/MM/aaaa");
    volunteerBirthDate.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            volunteerBirthDateActionPerformed(evt);
        }
    });

    volunteerEducation.setEditable(false);

    volunteerBirthPlace.setEditable(false);

    volunteerMaritalStatus.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Solteiro(a)", "Casado(a)", "Divorciado(a)", "Viúvo(a)" }));
    volunteerMaritalStatus.setEnabled(false);

    volunteerNationality.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Afeganistão", "África do Sul", "Albânia", "Alemanha", "Andorra", "Angola", "Arábia Saudita", "Argélia", "Argentina", "Armênia", "Austrália", "Áustria", "Azerbaijão", "Bahamas", "Bahrein", "Bangladesh", "Barbados", "Bélgica", "Belize", "Benin", "Bielorrússia", "Bolívia", "Bósnia", "Botsuana", "Brasil", "Brunei", "Bulgária", "Burkina-Fasso", "Burundi", "Butão", "Cabo Verde", "Camarões", "Camboja", "Canadá", "Catar", "Cazaquistão", "Chade", "Chile", "China", "Chipre", "Cingapura", "Colômbia", "Comores", "Congo", "Coréia do Norte", "Coréia do Sul", "Costado Marfim", "Costa Rica", "Croácia", "Cuba", "Dinamarca", "Djibuti", "Dominica", "Egito", "El Salvador", "Emirados Árabes Unidos", "Equador", "Eritreia", "Escócia", "Eslováquia", "Eslovênia", "Espanha", "Estados Unidos", "Estônia", "Etiópia", "Federação Russa", "Fiji", "Filipinas", "Finlândia", "Formosa Taiwan", "França", "Gabão", "Gâmbia", "Gana", "Geórgia", "Grã-Bretanha", "Granada", "Grécia", "Groenlândia", "Guatemala", "Guiana", "Guiana Francesa", "Guiné", "Guiné Bissau", "Guiné Equatorial", "Haiti", "Holanda", "Honduras", "Hungria", "Iêmen", "Ilhas Marshall", "Ilhas Salomão", "Índia", "Indonésia", "Irão", "Iraque", "Irlanda", "Irlanda do Norte", "Islândia", "Israel", "Itália", "Jamaica", "Japão", "Jordânia", "Kiribati", "Kuwait", "Laos", "Lesoto", "Letônia", "Líbano", "Libéria", "Líbia", "Liechtenstein", "Lituânia", "Luxemburgo", "Macedônia", "Madagascar", "Malásia", "Malauí", "Maldivas", "Mali", "Malta", "Marrocos", "Maurício", "Mauritânia", "México", "Mianmar", "Micronésia", "Moçambique", "Moldávia", "Mônaco", "Mongólia", "Namíbia", "Nauru", "Nepal", "Nicarágua", "Níger", "Nigéria", "Noruega", "Nova Zelândia", "Omã", "Palau", "Panamá", "Papua Nova Guiné", "Paquistão", "Paraguai", "Peru", "Polônia", "Porto Rico", "Portugal", "Quênia", "Quirguistão", "Reino Unido", "Rep.Centro-Africana", "Rep.Dominicana", "República Tcheca", "Romênia", "Ruanda", "Samoa", "SanMarino", "Santa Lúcia", "São Cristóvão e Névis", "São Tomé e Príncipe", "São Vicente e Granadinas", "Seicheles", "Senegal", "Serra Leoa", "Sérvia e Montenegro", "Síria", "Somália", "Sri-Lanka", "Suazilândia", "Sudão", "Suécia", "Suáça", "Suriname", "Tadjiquistão", "Tailândia", "Tanzânia", "Togo", "Tonga", "Trinidade Tobago", "Tunísia", "Turcomenistão", "Turquia", "Tuvalu", "Ucrânia", "Uganda", "Uruguai", "Uzbequistão", "Vanuatu", "Vaticano", "Venezuela", "Vietnã", "Zaire", "Zâmbia", "Zimbábue " }));
    volunteerNationality.setSelectedIndex(149);
    volunteerNationality.setEnabled(false);
    volunteerNationality.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            volunteerNationalityActionPerformed(evt);
        }
    });

    volunteerNib.setEditable(false);

    volunteerNif.setEditable(false);

    cancelEditVolunteer.setText("Cancelar");
    cancelEditVolunteer.setVisible(false);
    cancelEditVolunteer.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            cancelEditVolunteerActionPerformed(evt);
        }
    });

    submitEditVolunteer.setText("Submeter");
    submitEditVolunteer.setVisible(false);
    submitEditVolunteer.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            submitEditVolunteerActionPerformed(evt);
        }
    });

    javax.swing.GroupLayout jPanel25Layout = new javax.swing.GroupLayout(jPanel25);
    jPanel25.setLayout(jPanel25Layout);
    jPanel25Layout.setHorizontalGroup(
        jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel25Layout.createSequentialGroup()
            .addGap(38, 38, 38)
            .addComponent(jLabel114)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                .addGroup(jPanel25Layout.createSequentialGroup()
                    .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel25Layout.createSequentialGroup()
                            .addGap(85, 85, 85)
                            .addComponent(submitEditVolunteer))
                        .addComponent(cancelEditVolunteer))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(editVolunteer)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(deleteVolunteer))
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(volunteerAddress, javax.swing.GroupLayout.DEFAULT_SIZE, 470, Short.MAX_VALUE)
                    .addComponent(volunteerName))
                .addComponent(volunteerBirthDate, javax.swing.GroupLayout.PREFERRED_SIZE, 376, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(volunteerMaritalStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(volunteerEducation, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(volunteerBirthPlace, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(volunteerNationality, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 475, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel25Layout.createSequentialGroup()
                    .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel25Layout.createSequentialGroup()
                            .addGap(64, 64, 64)
                            .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel112)
                                .addComponent(jLabel111)
                                .addComponent(jLabel110)))
                        .addComponent(volunteerTeam, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(addVolunteerTeam)))
            .addContainerGap(61, Short.MAX_VALUE))
        .addGroup(jPanel25Layout.createSequentialGroup()
            .addGap(34, 34, 34)
            .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                .addComponent(jLabel11)
                .addComponent(jLabel9)
                .addComponent(jLabel10)
                .addComponent(jLabel14)
                .addComponent(jLabel15))
            .addGap(246, 246, 246)
            .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                .addComponent(jLabel13)
                .addComponent(jLabel12))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(volunteerNib, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(volunteerNif, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGap(0, 0, Short.MAX_VALUE))
    );
    jPanel25Layout.setVerticalGroup(
        jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel25Layout.createSequentialGroup()
            .addGap(20, 20, 20)
            .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel110)
                .addComponent(volunteerName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGap(6, 6, 6)
            .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel111)
                .addComponent(volunteerAddress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel112)
                .addComponent(volunteerTeam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(addVolunteerTeam))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel9)
                .addComponent(volunteerBirthDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel11)
                .addComponent(volunteerMaritalStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel10)
                .addComponent(volunteerEducation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel13)
                .addComponent(volunteerNib, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel14)
                .addComponent(volunteerBirthPlace, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel12)
                .addComponent(volunteerNif, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel15)
                .addComponent(volunteerNationality, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jLabel114)
                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGap(40, 40, 40)
            .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(deleteVolunteer)
                .addComponent(editVolunteer)
                .addComponent(submitEditVolunteer)
                .addComponent(cancelEditVolunteer))
            .addContainerGap(347, Short.MAX_VALUE))
    );

    jTabbedPane12.addTab("Informações", jPanel25);

    jPanel11.setBackground(new java.awt.Color(255, 255, 255));

    volunteerViewEventDetails.setText("Detalhes");

    volunteerEventList.setModel(new javax.swing.table.DefaultTableModel(
        new Object [][] {

        },
        new String [] {
            "Data", "Local", "Valor Obtido", "Nr. Participantes"
        }
    ) {
        Class[] types = new Class [] {
            java.lang.String.class, java.lang.String.class, java.lang.Double.class, java.lang.Integer.class
        };
        boolean[] canEdit = new boolean [] {
            false, false, false, false
        };

        public Class getColumnClass(int columnIndex) {
            return types [columnIndex];
        }

        public boolean isCellEditable(int rowIndex, int columnIndex) {
            return canEdit [columnIndex];
        }
    });
    volunteerEventList.getTableHeader().setReorderingAllowed(false);
    jScrollPane19.setViewportView(volunteerEventList);

    javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
    jPanel11.setLayout(jPanel11Layout);
    jPanel11Layout.setHorizontalGroup(
        jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel11Layout.createSequentialGroup()
            .addGap(268, 268, 268)
            .addComponent(volunteerViewEventDetails)
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        .addGroup(jPanel11Layout.createSequentialGroup()
            .addContainerGap()
            .addComponent(jScrollPane19, javax.swing.GroupLayout.DEFAULT_SIZE, 631, Short.MAX_VALUE)
            .addContainerGap())
    );
    jPanel11Layout.setVerticalGroup(
        jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
            .addContainerGap()
            .addComponent(jScrollPane19, javax.swing.GroupLayout.DEFAULT_SIZE, 669, Short.MAX_VALUE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(volunteerViewEventDetails)
            .addGap(12, 12, 12))
    );

    jTabbedPane12.addTab("Eventos", jPanel11);

    jPanel12.setBackground(new java.awt.Color(255, 255, 255));

    volunteerViewProjectDetails.setText("Detalhes");

    jTable8.setModel(new javax.swing.table.DefaultTableModel(
        new Object [][] {

        },
        new String [] {
            "Nome", "Data de Início", "Orçamento/Custo Final", "Terminado"
        }
    ) {
        Class[] types = new Class [] {
            java.lang.String.class, java.lang.String.class, java.lang.Double.class, java.lang.Boolean.class
        };
        boolean[] canEdit = new boolean [] {
            false, false, false, false
        };

        public Class getColumnClass(int columnIndex) {
            return types [columnIndex];
        }

        public boolean isCellEditable(int rowIndex, int columnIndex) {
            return canEdit [columnIndex];
        }
    });
    jTable8.getTableHeader().setReorderingAllowed(false);
    jScrollPane20.setViewportView(jTable8);
    if (jTable8.getColumnModel().getColumnCount() > 0) {
        jTable8.getColumnModel().getColumn(3).setCellEditor(new DefaultCellEditor( new JCheckBox() ));
    }

    javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
    jPanel12.setLayout(jPanel12Layout);
    jPanel12Layout.setHorizontalGroup(
        jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel12Layout.createSequentialGroup()
            .addGap(268, 268, 268)
            .addComponent(volunteerViewProjectDetails)
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        .addGroup(jPanel12Layout.createSequentialGroup()
            .addContainerGap()
            .addComponent(jScrollPane20, javax.swing.GroupLayout.DEFAULT_SIZE, 631, Short.MAX_VALUE)
            .addContainerGap())
    );
    jPanel12Layout.setVerticalGroup(
        jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
            .addContainerGap()
            .addComponent(jScrollPane20, javax.swing.GroupLayout.DEFAULT_SIZE, 669, Short.MAX_VALUE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(volunteerViewProjectDetails)
            .addGap(12, 12, 12))
    );

    jTabbedPane12.addTab("Projetos", jPanel12);

    addVolunteer.setText("Adicionar Voluntário");
    addVolunteer.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            addVolunteerMouseClicked(evt);
        }
    });
    addVolunteer.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            addVolunteerActionPerformed(evt);
        }
    });

    jTable3.setAutoCreateRowSorter(true);
    jTable3.setModel(new javax.swing.table.DefaultTableModel(
        new Object [][] {

        },
        new String [] {
            "Código", "Nome", "Equipa"
        }
    ) {
        boolean[] canEdit = new boolean [] {
            false, false, true
        };

        public boolean isCellEditable(int rowIndex, int columnIndex) {
            return canEdit [columnIndex];
        }
    });
    jTable3.getTableHeader().setReorderingAllowed(false);
    jScrollPane10.setViewportView(jTable3);

    jLabel91.setFont(new java.awt.Font("Calibri Light", 0, 24)); // NOI18N
    jLabel91.setText("Pesquisa");

    searchVolunteersButton.setText("Pesquisar");

    javax.swing.GroupLayout jPanel24Layout = new javax.swing.GroupLayout(jPanel24);
    jPanel24.setLayout(jPanel24Layout);
    jPanel24Layout.setHorizontalGroup(
        jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel24Layout.createSequentialGroup()
            .addComponent(jTabbedPane12, javax.swing.GroupLayout.PREFERRED_SIZE, 741, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jScrollPane10, javax.swing.GroupLayout.DEFAULT_SIZE, 394, Short.MAX_VALUE)
                .addGroup(jPanel24Layout.createSequentialGroup()
                    .addComponent(addVolunteer)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(searchVolunteersButton))
                .addComponent(searchVolunteersField)
                .addGroup(jPanel24Layout.createSequentialGroup()
                    .addGap(155, 155, 155)
                    .addComponent(jLabel91)
                    .addGap(0, 0, Short.MAX_VALUE)))
            .addContainerGap())
    );
    jPanel24Layout.setVerticalGroup(
        jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel24Layout.createSequentialGroup()
            .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel24Layout.createSequentialGroup()
                    .addComponent(jLabel91)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(searchVolunteersField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(addVolunteer)
                        .addComponent(searchVolunteersButton))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jScrollPane10))
                .addGroup(jPanel24Layout.createSequentialGroup()
                    .addGap(8, 8, 8)
                    .addComponent(jTabbedPane12)))
            .addContainerGap())
    );

    javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
    jPanel3.setLayout(jPanel3Layout);
    jPanel3Layout.setHorizontalGroup(
        jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGap(0, 1151, Short.MAX_VALUE)
        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );
    jPanel3Layout.setVerticalGroup(
        jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGap(0, 763, Short.MAX_VALUE)
        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jPanel24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap()))
    );

    jTabbedPane1.addTab("Voluntários", jPanel3);

    jTabbedPane14.setBackground(new java.awt.Color(255, 255, 255));
    jTabbedPane14.setTabPlacement(javax.swing.JTabbedPane.LEFT);
    jTabbedPane14.setFont(new java.awt.Font("Calibri Light", 0, 14)); // NOI18N

    jPanel44.setBackground(new java.awt.Color(255, 255, 255));

    jLabel115.setText("Local:");

    jLabel130.setText("Data:");

    jLabel131.setText("Valor angariado:");

    jLabel133.setText("Observações:");

    eventObservation.setEditable(false);

    eventObservation.setLineWrap(true);

    eventObservation.setToolTipText("");

    eventObservation.setWrapStyleWord(true);
    eventObservation.setColumns(20);
    eventObservation.setRows(5);
    jScrollPane32.setViewportView(eventObservation);
    eventObservation.setDocument(new JTextAreaLimit(500));

    editEventButton.setText("Editar Evento");
    editEventButton.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            editEventButtonActionPerformed(evt);
        }
    });

    removeEventButton.setText("Remover Evento");
    removeEventButton.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            removeEventButtonActionPerformed(evt);
        }
    });

    jLabel1.setText("Nº participantes:");

    eventDate.setEditable(false);
    eventDate.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(new java.text.SimpleDateFormat("dd/MM/yyyy"))));
    eventDate.setToolTipText("dd/MM/aaaa");
    eventDate.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            eventDateActionPerformed(evt);
        }
    });

    eventAddress.setEditable(false);
    eventAddress.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            eventAddressActionPerformed(evt);
        }
    });

    eventRaisedValue.setEditable(false);
    eventRaisedValue.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00; #0.00"))));
    eventRaisedValue.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            eventRaisedValueActionPerformed(evt);
        }
    });

    cancelEditEventButton.setVisible(false);
    cancelEditEventButton.setText("Cancelar");
    cancelEditEventButton.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            cancelEditEventButtonActionPerformed(evt);
        }
    });

    submitEditEventButton.setVisible(false);
    submitEditEventButton.setText("Submeter");

    jLabel4.setText("€");

    eventParticipantNmb.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(0), Integer.valueOf(0), null, Integer.valueOf(1)));

    javax.swing.GroupLayout jPanel44Layout = new javax.swing.GroupLayout(jPanel44);
    jPanel44.setLayout(jPanel44Layout);
    jPanel44Layout.setHorizontalGroup(
        jPanel44Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel44Layout.createSequentialGroup()
            .addGroup(jPanel44Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel44Layout.createSequentialGroup()
                    .addGroup(jPanel44Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel44Layout.createSequentialGroup()
                            .addGap(19, 19, 19)
                            .addGroup(jPanel44Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel133)
                                .addComponent(jLabel131)
                                .addComponent(jLabel115)
                                .addComponent(jLabel1))
                            .addGap(5, 5, 5))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel44Layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(jLabel130)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                    .addGroup(jPanel44Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jScrollPane32, javax.swing.GroupLayout.DEFAULT_SIZE, 434, Short.MAX_VALUE)
                        .addComponent(eventAddress)
                        .addComponent(eventParticipantNmb, javax.swing.GroupLayout.DEFAULT_SIZE, 434, Short.MAX_VALUE)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel44Layout.createSequentialGroup()
                            .addGroup(jPanel44Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(eventRaisedValue, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(eventDate, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 101, Short.MAX_VALUE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabel4)))
                    .addGap(0, 0, Short.MAX_VALUE))
                .addGroup(jPanel44Layout.createSequentialGroup()
                    .addGroup(jPanel44Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel44Layout.createSequentialGroup()
                            .addGap(39, 39, 39)
                            .addComponent(cancelEditEventButton)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(jPanel44Layout.createSequentialGroup()
                            .addContainerGap(177, Short.MAX_VALUE)
                            .addComponent(submitEditEventButton)
                            .addGap(117, 117, 117)))
                    .addComponent(editEventButton)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(removeEventButton)))
            .addGap(22, 22, 22))
    );
    jPanel44Layout.setVerticalGroup(
        jPanel44Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel44Layout.createSequentialGroup()
            .addGap(21, 21, 21)
            .addGroup(jPanel44Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel115)
                .addComponent(eventAddress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGap(5, 5, 5)
            .addGroup(jPanel44Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel130)
                .addComponent(eventDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(jPanel44Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel131)
                .addComponent(eventRaisedValue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel4))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addGroup(jPanel44Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel1)
                .addComponent(eventParticipantNmb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGap(12, 12, 12)
            .addGroup(jPanel44Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jLabel133)
                .addComponent(jScrollPane32, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(jPanel44Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(removeEventButton)
                .addComponent(editEventButton)
                .addComponent(cancelEditEventButton)
                .addComponent(submitEditEventButton))
            .addGap(47, 47, 47))
    );

    jTabbedPane14.addTab("Informações", jPanel44);

    jPanel7.setBackground(new java.awt.Color(255, 255, 255));

    participantsTable.setAutoCreateRowSorter(true);
    participantsTable.setModel(new javax.swing.table.DefaultTableModel(
        new Object [][] {

        },
        new String [] {
            "Nome", "Contacto"
        }
    ) {
        Class[] types = new Class [] {
            java.lang.String.class, java.lang.String.class
        };
        boolean[] canEdit = new boolean [] {
            false, false
        };

        public Class getColumnClass(int columnIndex) {
            return types [columnIndex];
        }

        public boolean isCellEditable(int rowIndex, int columnIndex) {
            return canEdit [columnIndex];
        }
    });
    participantsTable.getTableHeader().setReorderingAllowed(false);
    jScrollPane17.setViewportView(participantsTable);

    editParticipantsButton.setText("Editar participantes");
    editParticipantsButton.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            editParticipantsButtonActionPerformed(evt);
        }
    });

    javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
    jPanel7.setLayout(jPanel7Layout);
    jPanel7Layout.setHorizontalGroup(
        jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel7Layout.createSequentialGroup()
            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel7Layout.createSequentialGroup()
                    .addGap(58, 58, 58)
                    .addComponent(jScrollPane17, javax.swing.GroupLayout.PREFERRED_SIZE, 521, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel7Layout.createSequentialGroup()
                    .addGap(235, 235, 235)
                    .addComponent(editParticipantsButton)))
            .addContainerGap(67, Short.MAX_VALUE))
    );
    jPanel7Layout.setVerticalGroup(
        jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel7Layout.createSequentialGroup()
            .addGap(28, 28, 28)
            .addComponent(jScrollPane17, javax.swing.GroupLayout.PREFERRED_SIZE, 499, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(editParticipantsButton)
            .addContainerGap(174, Short.MAX_VALUE))
    );

    jTabbedPane14.addTab("Participantes", jPanel7);

    jLabel139.setFont(new java.awt.Font("Calibri Light", 0, 24)); // NOI18N
    jLabel139.setText("Pesquisa");

    addEventButton.setText("Adicionar Evento");
    addEventButton.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            addEventButtonMouseClicked(evt);
        }
    });
    addEventButton.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            addEventButtonActionPerformed(evt);
        }
    });

    eventTable.setAutoCreateRowSorter(true);
    eventTable.setModel(new javax.swing.table.DefaultTableModel(
        new Object [][] {

        },
        new String [] {
            "Local", "Data", "Nºparticipantes"
        }
    ) {
        Class[] types = new Class [] {
            java.lang.String.class, java.lang.String.class, java.lang.String.class
        };
        boolean[] canEdit = new boolean [] {
            false, false, false
        };

        public Class getColumnClass(int columnIndex) {
            return types [columnIndex];
        }

        public boolean isCellEditable(int rowIndex, int columnIndex) {
            return canEdit [columnIndex];
        }
    });
    eventTable.getTableHeader().setReorderingAllowed(false);
    jScrollPane21.setViewportView(eventTable);

    searchEventsButton.setText("Pesquisar");

    javax.swing.GroupLayout jPanel43Layout = new javax.swing.GroupLayout(jPanel43);
    jPanel43.setLayout(jPanel43Layout);
    jPanel43Layout.setHorizontalGroup(
        jPanel43Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel43Layout.createSequentialGroup()
            .addComponent(jTabbedPane14, javax.swing.GroupLayout.PREFERRED_SIZE, 741, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(jPanel43Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jScrollPane21, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 394, Short.MAX_VALUE)
                .addGroup(jPanel43Layout.createSequentialGroup()
                    .addComponent(addEventButton)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(searchEventsButton))
                .addComponent(eventSearch)
                .addGroup(jPanel43Layout.createSequentialGroup()
                    .addGap(155, 155, 155)
                    .addComponent(jLabel139)
                    .addGap(0, 0, Short.MAX_VALUE)))
            .addContainerGap())
    );
    jPanel43Layout.setVerticalGroup(
        jPanel43Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel43Layout.createSequentialGroup()
            .addGap(6, 6, 6)
            .addComponent(jLabel139)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(eventSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(jPanel43Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(addEventButton)
                .addComponent(searchEventsButton))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jScrollPane21))
        .addComponent(jTabbedPane14)
    );

    javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
    jPanel5.setLayout(jPanel5Layout);
    jPanel5Layout.setHorizontalGroup(
        jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGap(0, 1151, Short.MAX_VALUE)
        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel43, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );
    jPanel5Layout.setVerticalGroup(
        jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGap(0, 763, Short.MAX_VALUE)
        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jPanel43, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap()))
    );

    jTabbedPane1.addTab("Eventos", jPanel5);

    jTabbedPane16.setTabPlacement(javax.swing.JTabbedPane.LEFT);
    jTabbedPane16.setFont(new java.awt.Font("Calibri Light", 0, 14)); // NOI18N

    jPanel48.setBackground(new java.awt.Color(255, 255, 255));

    jLabel125.setText("Nome:");

    jLabel126.setText("Morada:");

    jLabel127.setText("NIF:");

    jLabel128.setText("Observações:");

    jLabel18.setText("Atividade:");

    jLabel42.setText("Tipo:");

    editDonorButton.setText("Editar doador");
    editDonorButton.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            editDonorButtonActionPerformed(evt);
        }
    });

    removeDonorButton.setText("Remover doador");

    jLabel2.setText("Contactos:");

    donorObservation.setEditable(false);

    donorObservation.setLineWrap(true);

    donorObservation.setToolTipText("");

    donorObservation.setWrapStyleWord(true);

    donorObservation.setDocument(new JTextAreaLimit(500));
    donorObservation.setColumns(20);
    donorObservation.setRows(5);
    jScrollPane3.setViewportView(donorObservation);

    donorName.setEditable(false);

    donorAddress.setEditable(false);

    donorNIF.setEditable(false);

    donorActivity.setEditable(false);

    donorType.setEditable(false);

    donorContacts.setAutoCreateRowSorter(true);
    donorContacts.setModel(new javax.swing.table.DefaultTableModel(
        new Object [][] {

        },
        new String [] {
            "Tipo", "Contacto"
        }
    ) {
        Class[] types = new Class [] {
            java.lang.String.class, java.lang.String.class
        };

        public Class getColumnClass(int columnIndex) {
            return types [columnIndex];
        }
    });
    donorContacts.setEnabled(false);
    donorContacts.getTableHeader().setReorderingAllowed(false);
    jScrollPane23.setViewportView(donorContacts);
    if (donorContacts.getColumnModel().getColumnCount() > 0) {
        donorContacts.getColumnModel().getColumn(0).setCellEditor(new DefaultCellEditor(
            new javax.swing.JComboBox(
                new javax.swing.DefaultComboBoxModel(
                    new String[] { "Telefone", "Telemóvel", "Email", "Fax" }))) );
    }

    addDonorContact.setText("+");
    addDonorContact.setVisible(false);
    addDonorContact.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            addDonorContactActionPerformed(evt);
        }
    });

    deleteDonorContact.setText("-");
    deleteDonorContact.setMaximumSize(new java.awt.Dimension(41, 23));
    deleteDonorContact.setMinimumSize(new java.awt.Dimension(41, 23));
    deleteDonorContact.setVisible(false);
    deleteDonorContact.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            deleteDonorContactActionPerformed(evt);
        }
    });

    cancelDonorEdit.setVisible(false);
    cancelDonorEdit.setText("Cancelar");
    cancelDonorEdit.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            cancelDonorEditActionPerformed(evt);
        }
    });

    submitDonorEdit.setVisible(false);
    submitDonorEdit.setText("Submeter");

    javax.swing.GroupLayout jPanel48Layout = new javax.swing.GroupLayout(jPanel48);
    jPanel48.setLayout(jPanel48Layout);
    jPanel48Layout.setHorizontalGroup(
        jPanel48Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel48Layout.createSequentialGroup()
            .addGap(62, 62, 62)
            .addComponent(cancelDonorEdit)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(submitDonorEdit)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 157, Short.MAX_VALUE)
            .addComponent(editDonorButton)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(removeDonorButton)
            .addGap(55, 55, 55))
        .addGroup(jPanel48Layout.createSequentialGroup()
            .addGap(15, 15, 15)
            .addGroup(jPanel48Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                .addComponent(jLabel127)
                .addComponent(jLabel126)
                .addComponent(jLabel125)
                .addComponent(jLabel18)
                .addComponent(jLabel42)
                .addComponent(jLabel2)
                .addComponent(jLabel128))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(jPanel48Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                .addComponent(donorName, javax.swing.GroupLayout.DEFAULT_SIZE, 461, Short.MAX_VALUE)
                .addComponent(donorAddress, javax.swing.GroupLayout.DEFAULT_SIZE, 461, Short.MAX_VALUE)
                .addComponent(donorNIF, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(donorActivity, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(donorType, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jScrollPane23)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel48Layout.createSequentialGroup()
                    .addComponent(addDonorContact, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(deleteDonorContact, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addComponent(jScrollPane3))
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );
    jPanel48Layout.setVerticalGroup(
        jPanel48Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel48Layout.createSequentialGroup()
            .addGap(20, 20, 20)
            .addGroup(jPanel48Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(donorName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel125))
            .addGap(5, 5, 5)
            .addGroup(jPanel48Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel126)
                .addComponent(donorAddress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(jPanel48Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel127)
                .addComponent(donorNIF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addGroup(jPanel48Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel18)
                .addComponent(donorActivity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addGroup(jPanel48Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel42, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(donorType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGap(18, 18, 18)
            .addGroup(jPanel48Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(deleteDonorContact, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(addDonorContact))
            .addGap(2, 2, 2)
            .addGroup(jPanel48Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jScrollPane23, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel2))
            .addGap(18, 18, 18)
            .addGroup(jPanel48Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel128))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(jPanel48Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(editDonorButton)
                .addComponent(removeDonorButton)
                .addComponent(cancelDonorEdit)
                .addComponent(submitDonorEdit))
            .addGap(27, 27, 27))
    );

    jTabbedPane16.addTab("Informações", jPanel48);

    jPanel9.setBackground(new java.awt.Color(255, 255, 255));

    donorContacts.setEnabled(false);
    addDonationTable.setAutoCreateRowSorter(true);
    addDonationTable.setModel(new javax.swing.table.DefaultTableModel(
        new Object [][] {

        },
        new String [] {
            "Data", "Tipo", "Valor"
        }
    ) {
        Class[] types = new Class [] {
            java.lang.String.class, java.lang.String.class, java.lang.String.class
        };

        public Class getColumnClass(int columnIndex) {
            return types [columnIndex];
        }
    });
    jScrollPane24.setViewportView(addDonationTable);

    removeDonationButton.setText("Remover donativo");
    removeDonationButton.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            removeDonationButtonActionPerformed(evt);
        }
    });

    addDonationButton.setText("Adicionar donativo");
    addDonationButton.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            addDonationButtonActionPerformed(evt);
        }
    });

    confirmRemoveDonationButton.setVisible(false);
    confirmRemoveDonationButton.setText("Confirmar");
    confirmRemoveDonationButton.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            confirmRemoveDonationButtonActionPerformed(evt);
        }
    });

    cancelRemoveDonationButton.setVisible(false);
    cancelRemoveDonationButton.setText("Cancelar");
    cancelRemoveDonationButton.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            cancelRemoveDonationButtonActionPerformed(evt);
        }
    });

    javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
    jPanel9.setLayout(jPanel9Layout);
    jPanel9Layout.setHorizontalGroup(
        jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel9Layout.createSequentialGroup()
            .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                .addGroup(jPanel9Layout.createSequentialGroup()
                    .addGap(45, 45, 45)
                    .addComponent(cancelRemoveDonationButton, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(confirmRemoveDonationButton, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(removeDonationButton)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(addDonationButton))
                .addGroup(jPanel9Layout.createSequentialGroup()
                    .addGap(54, 54, 54)
                    .addComponent(jScrollPane24, javax.swing.GroupLayout.PREFERRED_SIZE, 581, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addContainerGap())
    );
    jPanel9Layout.setVerticalGroup(
        jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel9Layout.createSequentialGroup()
            .addGap(40, 40, 40)
            .addComponent(jScrollPane24, javax.swing.GroupLayout.DEFAULT_SIZE, 626, Short.MAX_VALUE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(addDonationButton)
                .addComponent(confirmRemoveDonationButton)
                .addComponent(removeDonationButton)
                .addComponent(cancelRemoveDonationButton))
            .addGap(35, 35, 35))
    );

    jTabbedPane16.addTab("Donativos", jPanel9);

    jLabel53.setFont(new java.awt.Font("Calibri Light", 0, 24)); // NOI18N
    jLabel53.setText("Pesquisa");

    addDonorButton.setText("Adicionar doador");
    addDonorButton.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            addDonorButtonActionPerformed(evt);
        }
    });

    jButton40.setText("Anónimos");

    jTable2.setAutoCreateRowSorter(true);
    jTable2.setModel(new javax.swing.table.DefaultTableModel(
        new Object [][] {
            {null, null, null},
            {null, null, null},
            {null, null, null}
        },
        new String [] {
            "Nome", "Tipo", "Atividade"
        }
    ) {
        Class[] types = new Class [] {
            java.lang.String.class, java.lang.String.class, java.lang.String.class
        };
        boolean[] canEdit = new boolean [] {
            false, false, false
        };

        public Class getColumnClass(int columnIndex) {
            return types [columnIndex];
        }

        public boolean isCellEditable(int rowIndex, int columnIndex) {
            return canEdit [columnIndex];
        }
    });
    jTable2.getTableHeader().setReorderingAllowed(false);
    jScrollPane25.setViewportView(jTable2);

    searchDonorsButton.setText("Pesquisar");

    javax.swing.GroupLayout jPanel47Layout = new javax.swing.GroupLayout(jPanel47);
    jPanel47.setLayout(jPanel47Layout);
    jPanel47Layout.setHorizontalGroup(
        jPanel47Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel47Layout.createSequentialGroup()
            .addComponent(jTabbedPane16, javax.swing.GroupLayout.PREFERRED_SIZE, 740, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(jPanel47Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel47Layout.createSequentialGroup()
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(jPanel47Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel47Layout.createSequentialGroup()
                            .addComponent(jScrollPane25, javax.swing.GroupLayout.PREFERRED_SIZE, 380, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(jPanel47Layout.createSequentialGroup()
                            .addGroup(jPanel47Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jTextField10, javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel47Layout.createSequentialGroup()
                                    .addComponent(addDonorButton)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jButton40)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(searchDonorsButton)))
                            .addGap(12, 12, 12))))
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel47Layout.createSequentialGroup()
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel53)
                    .addGap(147, 147, 147))))
    );
    jPanel47Layout.setVerticalGroup(
        jPanel47Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel47Layout.createSequentialGroup()
            .addGap(6, 6, 6)
            .addComponent(jLabel53)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(jPanel47Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(addDonorButton, javax.swing.GroupLayout.Alignment.TRAILING)
                .addGroup(jPanel47Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(searchDonorsButton)
                    .addComponent(jButton40)))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addComponent(jScrollPane25))
        .addComponent(jTabbedPane16)
    );

    javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
    jPanel8.setLayout(jPanel8Layout);
    jPanel8Layout.setHorizontalGroup(
        jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGap(0, 1140, Short.MAX_VALUE)
        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel47, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );
    jPanel8Layout.setVerticalGroup(
        jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGap(0, 763, Short.MAX_VALUE)
        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addComponent(jPanel47, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap()))
    );

    jTabbedPane1.addTab("Doadores", jPanel8);

    jPanel13.setBackground(new java.awt.Color(22, 113, 204));

    jLabel7.setFont(new java.awt.Font("Calibri Light", 0, 24)); // NOI18N
    jLabel7.setForeground(new java.awt.Color(255, 255, 255));
    jLabel7.setText("Habitat For Humanity");

    javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
    jPanel13.setLayout(jPanel13Layout);
    jPanel13Layout.setHorizontalGroup(
        jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel13Layout.createSequentialGroup()
            .addContainerGap()
            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 587, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );
    jPanel13Layout.setVerticalGroup(
        jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel13Layout.createSequentialGroup()
            .addGap(0, 0, 0)
            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGap(0, 0, 0))
    );

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1156, Short.MAX_VALUE)
        .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
    );
    layout.setVerticalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(layout.createSequentialGroup()
            .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(0, 0, 0)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 809, Short.MAX_VALUE))
    );

    pack();
    }// </editor-fold>//GEN-END:initComponents

    private void addDonorButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addDonorButtonActionPerformed
        (new AdicionarDoador(this, true)).setVisible(true);
    }//GEN-LAST:event_addDonorButtonActionPerformed

    private void cancelRemoveDonationButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelRemoveDonationButtonActionPerformed
        donorContacts.setEnabled(false);
        confirmRemoveDonationButton.setVisible(false);
        cancelRemoveDonationButton.setVisible(false);
        removeDonationButton.setVisible(true);
        addDonationButton.setVisible(true);
    }//GEN-LAST:event_cancelRemoveDonationButtonActionPerformed

    private void confirmRemoveDonationButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_confirmRemoveDonationButtonActionPerformed
        int rowID;
        if(( rowID = addDonationTable.getSelectedRow()) >= 0)
        ((DefaultTableModel)addDonationTable.getModel()).removeRow(rowID);

        confirmRemoveDonationButton.setVisible(false);
        cancelRemoveDonationButton.setVisible(false);
        removeDonationButton.setVisible(true);
        addDonationButton.setVisible(true);
    }//GEN-LAST:event_confirmRemoveDonationButtonActionPerformed

    private void addDonationButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addDonationButtonActionPerformed
        (new AdicionarDonativoaDoador(this, true)).setVisible(true);
    }//GEN-LAST:event_addDonationButtonActionPerformed

    private void removeDonationButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeDonationButtonActionPerformed
        donorContacts.setEnabled(true);
        removeDonationButton.setVisible(false);
        addDonationButton.setVisible(false);
        confirmRemoveDonationButton.setVisible(true);
        cancelRemoveDonationButton.setVisible(true);
    }//GEN-LAST:event_removeDonationButtonActionPerformed

    private void cancelDonorEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelDonorEditActionPerformed
        donorName.setEditable(false);
        donorAddress.setEditable(false);
        donorNIF.setEditable(false);
        donorType.setEditable(false);
        donorActivity.setEditable(false);
        donorObservation.setEditable(false);
        donorContacts.setEnabled(false);
        cancelDonorEdit.setVisible(false);
        submitDonorEdit.setVisible(false);
        addDonorContact.setVisible(false);
        deleteDonorContact.setVisible(false);
    }//GEN-LAST:event_cancelDonorEditActionPerformed

    private void deleteDonorContactActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteDonorContactActionPerformed
        int rowID;
        if(( rowID = donorContacts.getSelectedRow()) >= 0)
        ((DefaultTableModel)donorContacts.getModel()).removeRow(rowID);
    }//GEN-LAST:event_deleteDonorContactActionPerformed

    private void addDonorContactActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addDonorContactActionPerformed
        ((DefaultTableModel)donorContacts.getModel()).addRow(new Object[]{"", "", ""});
    }//GEN-LAST:event_addDonorContactActionPerformed

    private void editDonorButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editDonorButtonActionPerformed
        donorName.setEditable(true);
        donorAddress.setEditable(true);
        donorNIF.setEditable(true);
        donorType.setEditable(true);
        donorActivity.setEditable(true);
        donorObservation.setEditable(true);
        donorContacts.setEnabled(true);
        cancelDonorEdit.setVisible(true);
        submitDonorEdit.setVisible(true);
        addDonorContact.setVisible(true);
        deleteDonorContact.setVisible(true);
    }//GEN-LAST:event_editDonorButtonActionPerformed

    private void addEventButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addEventButtonActionPerformed
        (new AdicionarEvento(this, true)).setVisible(true);
    }//GEN-LAST:event_addEventButtonActionPerformed

    private void addEventButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addEventButtonMouseClicked
        (new AdicionarEvento(this, true)).setVisible(true);
    }//GEN-LAST:event_addEventButtonMouseClicked

    private void editParticipantsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editParticipantsButtonActionPerformed
        (new EditarParticipantes(this, true)).setVisible(true);
    }//GEN-LAST:event_editParticipantsButtonActionPerformed

    private void cancelEditEventButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelEditEventButtonActionPerformed
        eventAddress.setEditable(false);
        eventDate.setEditable(false);
        eventRaisedValue.setEditable(false);
        eventParticipantNmb.setEnabled(false);
        eventObservation.setEditable(false);
        cancelEditEventButton.setVisible(false);
        submitEditEventButton.setVisible(false);
    }//GEN-LAST:event_cancelEditEventButtonActionPerformed

    private void eventRaisedValueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eventRaisedValueActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_eventRaisedValueActionPerformed

    private void eventAddressActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eventAddressActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_eventAddressActionPerformed

    private void eventDateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eventDateActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_eventDateActionPerformed

    private void removeEventButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeEventButtonActionPerformed

    }//GEN-LAST:event_removeEventButtonActionPerformed

    private void editEventButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editEventButtonActionPerformed
        eventAddress.setEditable(true);
        eventDate.setEditable(true);
        eventRaisedValue.setEditable(true);
        eventParticipantNmb.setEnabled(true);
        eventObservation.setEditable(true);
        submitEditEventButton.setVisible(true);
        cancelEditEventButton.setVisible(true);
    }//GEN-LAST:event_editEventButtonActionPerformed

    private void addVolunteerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addVolunteerActionPerformed
        AdicionarVoluntario window = new AdicionarVoluntario(this, true);
        try {
            window.setActivities();
            window.setVisible(true);
        } catch (DataException ex) {
            JOptionPane.showMessageDialog(this, "Ocorreu um erro ao obter os dados");
        }

    }//GEN-LAST:event_addVolunteerActionPerformed

    private void addVolunteerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addVolunteerMouseClicked
        (new AdicionarVoluntario(this, true)).setVisible(true);
    }//GEN-LAST:event_addVolunteerMouseClicked

    private void submitEditVolunteerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submitEditVolunteerActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_submitEditVolunteerActionPerformed

    private void cancelEditVolunteerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelEditVolunteerActionPerformed
        volunteerName.setEditable(false);
        volunteerAddress.setEditable(false);
        volunteerNotes.setEditable(false);
        volunteerBirthDate.setEditable(false);
        volunteerEducation.setEditable(false);
        volunteerMaritalStatus.setEnabled(false);
        volunteerNif.setEditable(false);
        volunteerNib.setEditable(false);
        cancelEditVolunteer.setVisible(false);
        submitEditVolunteer.setVisible(false);
        addVolunteerTeam.setVisible(false);
        volunteerNationality.setEnabled(false);
        volunteerBirthPlace.setEditable(false);
        volunteerTeam.setEnabled(false);
    }//GEN-LAST:event_cancelEditVolunteerActionPerformed

    private void volunteerNationalityActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_volunteerNationalityActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_volunteerNationalityActionPerformed

    private void volunteerBirthDateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_volunteerBirthDateActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_volunteerBirthDateActionPerformed

    private void editVolunteerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editVolunteerActionPerformed
        volunteerName.setEditable(true);
        volunteerAddress.setEditable(true);
        volunteerNotes.setEditable(true);
        volunteerBirthDate.setEditable(true);
        volunteerEducation.setEditable(true);
        volunteerMaritalStatus.setEnabled(true);
        volunteerNationality.setEnabled(true);
        volunteerBirthPlace.setEditable(true);
        volunteerTeam.setEnabled(true);
        volunteerNif.setEditable(true);
        volunteerNib.setEditable(true);
        cancelEditVolunteer.setVisible(true);
        submitEditVolunteer.setVisible(true);
        addVolunteerTeam.setVisible(true);
    }//GEN-LAST:event_editVolunteerActionPerformed

    private void addProjectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addProjectActionPerformed
        (new AdicionarProjeto(this, true)).setVisible(true);
    }//GEN-LAST:event_addProjectActionPerformed

    private void removeTaskActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeTaskActionPerformed
        Integer rowID;
        if(( rowID = taskList.getSelectedRow()) >= 0)
        ((DefaultTableModel)taskList.getModel()).removeRow(rowID);
    }//GEN-LAST:event_removeTaskActionPerformed

    private void addTaskActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addTaskActionPerformed
        ((DefaultTableModel)taskList.getModel()).addRow(new Object[]{"", "", ""});
    }//GEN-LAST:event_addTaskActionPerformed

    private void deletePaymentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deletePaymentActionPerformed
        Integer rowID;
        if(( rowID = paymentPlan.getSelectedRow()) >= 0)
        ((DefaultTableModel)paymentPlan.getModel()).removeRow(rowID);
    }//GEN-LAST:event_deletePaymentActionPerformed

    private void addPaymentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addPaymentActionPerformed
        ((DefaultTableModel)paymentPlan.getModel()).addRow(new Object[]{"", "", ""});
    }//GEN-LAST:event_addPaymentActionPerformed

    private void projectNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_projectNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_projectNameActionPerformed

    private void cancelEditProjectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelEditProjectActionPerformed
        projectName.setEditable(false);
        projectStartDate.setEditable(false);
        projectEta.setEditable(false);
        jtfPDF.setEditable(false);
        jtfPDEC.setEditable(false);
        projectSignDate.setEditable(false);
        projectBudget.setEditable(false);
        projectFinalCost.setEditable(false);
        jtfPVP.setEditable(false);
        projectNotes.setEditable(false);
        cancelEditProject.setVisible(false);
        submitEditProject.setVisible(false);
        //TODO - Refresh from DB
    }//GEN-LAST:event_cancelEditProjectActionPerformed

    private void editProjectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editProjectActionPerformed
        projectName.setEditable(true);
        projectStartDate.setEditable(true);
        projectEta.setEditable(true);
        jtfPDF.setEditable(true);
        jtfPDEC.setEditable(true);
        projectSignDate.setEditable(true);
        projectBudget.setEditable(true);
        projectFinalCost.setEditable(true);
        jtfPVP.setEditable(true);
        projectNotes.setEditable(true);
        cancelEditProject.setVisible(true);
        submitEditProject.setVisible(true);
    }//GEN-LAST:event_editProjectActionPerformed

    private void addFamilyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addFamilyActionPerformed
        AdicionarFamilia af = new AdicionarFamilia(this, true);
        try {
            af.setActivities();
            af.setVisible(true);
        } catch (DataException ex) {
            JOptionPane.showMessageDialog(this, "Ocorreu um erro ao obter os dados");
        }
                
    }//GEN-LAST:event_addFamilyActionPerformed

    private void addMemberActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addMemberActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_addMemberActionPerformed

    private void addApplicationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addApplicationActionPerformed
        (new AdicionarCandidatura(this, true, 1)).setVisible(true);
    }//GEN-LAST:event_addApplicationActionPerformed

    private void repNationalityActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_repNationalityActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_repNationalityActionPerformed

    private void cancelEditFamilyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelEditFamilyActionPerformed
        familyName.setEditable(false);
        familyAddress.setEditable(false);
        familyIncome.setEditable(false);
        familyNotes.setEditable(false);
        familyRep.setEditable(false);
        repBirthDate.setEditable(false);
        repEducation.setEditable(false);
        mainWindowRepProf.setEditable(false);
        repMaritalStatus.setEnabled(false);
        repNif.setEditable(false);
        repNib.setEditable(false);
        repContacts.setEnabled(false);
        cancelEditFamily.setVisible(false);
        submitEditFamily.setVisible(false);
        addRepContact.setVisible(false);
        deleteRepContact.setVisible(false);
        repNationality.setEnabled(false);
        repBirthPlace.setEditable(false);
    }//GEN-LAST:event_cancelEditFamilyActionPerformed

    private void addRepContactActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addRepContactActionPerformed
        ((DefaultTableModel)repContacts.getModel()).addRow(new Object[]{"", "", ""});
    }//GEN-LAST:event_addRepContactActionPerformed

    private void deleteRepContactActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteRepContactActionPerformed
        int rowID;
        if(( rowID = repContacts.getSelectedRow()) >= 0)
        ((DefaultTableModel)repContacts.getModel()).removeRow(rowID);
    }//GEN-LAST:event_deleteRepContactActionPerformed

    private void repBirthDateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_repBirthDateActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_repBirthDateActionPerformed

    private void editFamilyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editFamilyActionPerformed
        try {
            Collection<Activity> items = ControllerFactory.getActivityController().all();
            for( Activity a: items){
                mainWindowRepProf.addItem(a);
        }
        } catch (DataException ex) {
            JOptionPane.showMessageDialog(this, "Ocorreu um erro ao obter os dados");
        }
        
        
        familyName.setEditable(true);
        familyAddress.setEditable(true);
        familyIncome.setEditable(true);
        familyNotes.setEditable(true);
        familyRep.setEditable(true);
        repBirthDate.setEditable(true);
        repEducation.setEditable(true);
        mainWindowRepProf.setEditable(true);
        repMaritalStatus.setEnabled(true);
        repNif.setEditable(true);
        repNib.setEditable(true);
        repNationality.setEnabled(true);
        repBirthPlace.setEditable(true);
        repContacts.setEnabled(true);
        cancelEditFamily.setVisible(true);
        submitEditFamily.setVisible(true);
        addRepContact.setVisible(true);
        deleteRepContact.setVisible(true);
    }//GEN-LAST:event_editFamilyActionPerformed

    private void deleteFamilyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteFamilyActionPerformed
        try {
            String password = JOptionPane.showInputDialog(this, "Erro a ler dados");
            ControllerFactory.getFamiliesController().delete(currentFamily);
        } catch (DataException ex) {
            JOptionPane.showMessageDialog(this, "Erro a ler dados");
        } catch (NullPointerException e) {
            JOptionPane.showMessageDialog(this, "Por favor seleccione uma família.");
        }
    }//GEN-LAST:event_deleteFamilyActionPerformed

    private void submitEditFamilyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submitEditFamilyActionPerformed
        if(currentFamily == null) {
            JOptionPane.showMessageDialog(this, "Por favor seleccione uma família.");
            return;
        }
        
        RepresentativesController rc = ControllerFactory.getRepresentativesController();
        MembersController mc = ControllerFactory.getMembersController();
        FamiliesController fc = ControllerFactory.getFamiliesController();
        ContactsController cc = ControllerFactory.getContactsController();
        
        try {
            currentFamily.setName(familyName.getText());
            currentFamily.setAddress(familyAddress.getText());
            currentFamily.setObservations(familyNotes.getText());
            currentFamily.setIncome(Float.parseFloat(familyIncome.getText()));
            
            fc.save(currentFamily);
            
            currentRepresentative.setName(familyRep.getText());
            currentRepresentative.setBirthDate(Util.strToDate( repBirthDate.getText() ));
            currentRepresentative.setMaritalStatus(repMaritalStatus.getSelectedItem().toString());
            currentRepresentative.setEducation(repEducation.getText());
            Activity a = new Activity("Test");
            a.setId(1);
            currentRepresentative.setActivity(a);
            currentRepresentative.setNif(repNif.getText());
            currentRepresentative.setNib(repNib.getText());
            
            ControllerFactory.getContactsController().updateAll(representativeContacts);
            ControllerFactory.getMembersController().updateAll(currentMembers);
        } catch (DataException e) {
            JOptionPane.showMessageDialog(this, "Erro a guardar dados");
        }
        
    }//GEN-LAST:event_submitEditFamilyActionPerformed

    private void editQuestionnaireActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editQuestionnaireActionPerformed
        currentApplication.setApplicationDate(Util.strToDate(applicationDate.getText()));
        currentApplication.setPriority(applicationPriority.getSelectedIndex());
        currentApplication.setNotes(applicationNotes.getText());
        currentApplication.setLocation(applicationLocation.getText());
        currentApplication.setStatus(applicationApproved.isSelected());
        currentApplication.setApprovalDate(applicationApprovalDate.getText());
        
        try {
            ControllerFactory.getApplicationsController().save(currentApplication);
        } catch (DataException e) {
            JOptionPane.showMessageDialog(this, "Erro a gravar dados");
        }
    }//GEN-LAST:event_editQuestionnaireActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addApplication;
    private javax.swing.JButton addDonationButton;
    private javax.swing.JTable addDonationTable;
    private javax.swing.JButton addDonorButton;
    private javax.swing.JButton addDonorContact;
    private javax.swing.JButton addEventButton;
    private javax.swing.JButton addFamily;
    private javax.swing.JButton addMember;
    private javax.swing.JButton addPayment;
    private javax.swing.JButton addProject;
    private javax.swing.JButton addRepContact;
    private javax.swing.JButton addTask;
    private javax.swing.JButton addVolunteer;
    private javax.swing.JButton addVolunteerTeam;
    private javax.swing.JFormattedTextField applicationApprovalDate;
    private javax.swing.JCheckBox applicationApproved;
    private javax.swing.JFormattedTextField applicationDate;
    private javax.swing.JTextField applicationId;
    private javax.swing.JTextField applicationLocation;
    private javax.swing.JTextArea applicationNotes;
    private javax.swing.JComboBox applicationPriority;
    private javax.swing.JTable applicationQuestionnaire;
    private javax.swing.JButton cancelDonorEdit;
    private javax.swing.JButton cancelEditEventButton;
    private javax.swing.JButton cancelEditFamily;
    private javax.swing.JButton cancelEditProject;
    private javax.swing.JButton cancelEditVolunteer;
    private javax.swing.JButton cancelRemoveDonationButton;
    private javax.swing.JButton confirmRemoveDonationButton;
    private javax.swing.JButton deleteApplication;
    private javax.swing.JButton deleteDonorContact;
    private javax.swing.JButton deleteFamily;
    private javax.swing.JButton deletePayment;
    private javax.swing.JButton deleteProject;
    private javax.swing.JButton deleteRepContact;
    private javax.swing.JButton deleteVolunteer;
    private javax.swing.JTextField donorActivity;
    private javax.swing.JTextField donorAddress;
    private javax.swing.JTable donorContacts;
    private javax.swing.JTextField donorNIF;
    private javax.swing.JTextField donorName;
    private javax.swing.JTextArea donorObservation;
    private javax.swing.JTextField donorType;
    private javax.swing.JButton editApplication;
    private javax.swing.JButton editDonorButton;
    private javax.swing.JButton editEventButton;
    private javax.swing.JButton editFamily;
    private javax.swing.JButton editMember;
    private javax.swing.JButton editParticipantsButton;
    private javax.swing.JButton editPaymentPlan;
    private javax.swing.JButton editProject;
    private javax.swing.JButton editQuestionnaire;
    private javax.swing.JButton editVolunteer;
    private javax.swing.JTextField eventAddress;
    private javax.swing.JFormattedTextField eventDate;
    private javax.swing.JTextArea eventObservation;
    private javax.swing.JSpinner eventParticipantNmb;
    private javax.swing.JFormattedTextField eventRaisedValue;
    private javax.swing.JTextField eventSearch;
    private javax.swing.JTable eventTable;
    private javax.swing.JTextField familyAddress;
    private javax.swing.JCheckBox familyApproved;
    private javax.swing.JFormattedTextField familyIncome;
    private javax.swing.JTable familyList;
    private javax.swing.JTextField familyName;
    private javax.swing.JTextArea familyNotes;
    private javax.swing.JTextField familyRep;
    private javax.swing.JTextField familySearch;
    private javax.swing.JTabbedPane familySubTabbedPane;
    private javax.swing.JButton jButton40;
    private javax.swing.JDialog jDialog1;
    private javax.swing.JFormattedTextField jFormattedTextField1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel110;
    private javax.swing.JLabel jLabel111;
    private javax.swing.JLabel jLabel112;
    private javax.swing.JLabel jLabel114;
    private javax.swing.JLabel jLabel115;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel121;
    private javax.swing.JLabel jLabel122;
    private javax.swing.JLabel jLabel123;
    private javax.swing.JLabel jLabel124;
    private javax.swing.JLabel jLabel125;
    private javax.swing.JLabel jLabel126;
    private javax.swing.JLabel jLabel127;
    private javax.swing.JLabel jLabel128;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel130;
    private javax.swing.JLabel jLabel131;
    private javax.swing.JLabel jLabel133;
    private javax.swing.JLabel jLabel139;
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
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel88;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabel90;
    private javax.swing.JLabel jLabel91;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel25;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel33;
    private javax.swing.JPanel jPanel34;
    private javax.swing.JPanel jPanel35;
    private javax.swing.JPanel jPanel36;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel43;
    private javax.swing.JPanel jPanel44;
    private javax.swing.JPanel jPanel45;
    private javax.swing.JPanel jPanel46;
    private javax.swing.JPanel jPanel47;
    private javax.swing.JPanel jPanel48;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane13;
    private javax.swing.JScrollPane jScrollPane14;
    private javax.swing.JScrollPane jScrollPane15;
    private javax.swing.JScrollPane jScrollPane16;
    private javax.swing.JScrollPane jScrollPane17;
    private javax.swing.JScrollPane jScrollPane18;
    private javax.swing.JScrollPane jScrollPane19;
    private javax.swing.JScrollPane jScrollPane20;
    private javax.swing.JScrollPane jScrollPane21;
    private javax.swing.JScrollPane jScrollPane22;
    private javax.swing.JScrollPane jScrollPane23;
    private javax.swing.JScrollPane jScrollPane24;
    private javax.swing.JScrollPane jScrollPane25;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane32;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JSeparator jSeparator15;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane12;
    private javax.swing.JTabbedPane jTabbedPane14;
    private javax.swing.JTabbedPane jTabbedPane15;
    private javax.swing.JTabbedPane jTabbedPane16;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable3;
    private javax.swing.JTable jTable8;
    private javax.swing.JTextField jTextField10;
    private javax.swing.JFormattedTextField jtfPDEC;
    private javax.swing.JFormattedTextField jtfPDF;
    private javax.swing.JFormattedTextField jtfPVP;
    private javax.swing.JComboBox mainWindowRepProf;
    private javax.swing.JTable memberList;
    private javax.swing.JButton nextApplication;
    private javax.swing.JTable participantsTable;
    private javax.swing.JTable paymentPlan;
    private javax.swing.JTextArea paymentPlanNotes;
    private javax.swing.JButton previousApplication;
    private javax.swing.JFormattedTextField projectBudget;
    private javax.swing.JFormattedTextField projectEta;
    private javax.swing.JFormattedTextField projectFinalCost;
    private javax.swing.JTable projectList;
    private javax.swing.JTextField projectName;
    private javax.swing.JTextArea projectNotes;
    private javax.swing.JFormattedTextField projectSignDate;
    private javax.swing.JFormattedTextField projectStartDate;
    private javax.swing.JButton removeDonationButton;
    private javax.swing.JButton removeDonorButton;
    private javax.swing.JButton removeEventButton;
    private javax.swing.JButton removeMember;
    private javax.swing.JButton removeTask;
    private javax.swing.JFormattedTextField repBirthDate;
    private javax.swing.JTextField repBirthPlace;
    private javax.swing.JTable repContacts;
    private javax.swing.JTextField repEducation;
    private javax.swing.JComboBox repMaritalStatus;
    private javax.swing.JComboBox repNationality;
    private javax.swing.JTextField repNib;
    private javax.swing.JTextField repNif;
    private javax.swing.JButton searchDonorsButton;
    private javax.swing.JButton searchEventsButton;
    private javax.swing.JButton searchFamiliesButton;
    private javax.swing.JButton searchProjectsButton;
    private javax.swing.JTextField searchProjectsField;
    private javax.swing.JButton searchVolunteersButton;
    private javax.swing.JTextField searchVolunteersField;
    private javax.swing.JButton submitDonorEdit;
    private javax.swing.JButton submitEditEventButton;
    private javax.swing.JButton submitEditFamily;
    private javax.swing.JButton submitEditProject;
    private javax.swing.JButton submitEditVolunteer;
    private javax.swing.JTable taskList;
    private javax.swing.JButton taskViewDetails;
    private javax.swing.JTextField volunteerAddress;
    private javax.swing.JFormattedTextField volunteerBirthDate;
    private javax.swing.JTextField volunteerBirthPlace;
    private javax.swing.JTextField volunteerEducation;
    private javax.swing.JTable volunteerEventList;
    private javax.swing.JComboBox volunteerMaritalStatus;
    private javax.swing.JTextField volunteerName;
    private javax.swing.JComboBox volunteerNationality;
    private javax.swing.JTextField volunteerNib;
    private javax.swing.JTextField volunteerNif;
    private javax.swing.JTextArea volunteerNotes;
    private javax.swing.JComboBox volunteerTeam;
    private javax.swing.JButton volunteerViewEventDetails;
    private javax.swing.JButton volunteerViewProjectDetails;
    // End of variables declaration//GEN-END:variables
    private final HashMap<String, JComponent> familyBackup = new HashMap<>();

}

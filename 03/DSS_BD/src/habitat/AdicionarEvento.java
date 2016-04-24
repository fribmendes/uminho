/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package habitat;

import controllers.Controller;
import controllers.ControllerFactory;
import data.DataException;
import java.awt.event.WindowEvent;
import java.util.HashMap;
import java.util.HashSet;
import javax.swing.table.DefaultTableModel;
import models.Event;

/**
 *
 * @author paulo
 */
public class AdicionarEvento extends javax.swing.JDialog {

    HashSet<Integer> addedVol;
    
    /**
     * Creates new form AdicionarEvento
     */
    public AdicionarEvento(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        addedVol = new HashSet<Integer>();
        initComponents();
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
        jLabel5 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        addEventObservation = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();
        addEventDate = new javax.swing.JFormattedTextField();
        jLabel3 = new javax.swing.JLabel();
        addEventAddress = new javax.swing.JTextField();
        addEventCancelButton = new javax.swing.JButton();
        addEventcleanButton = new javax.swing.JButton();
        addEventSubmitButton = new javax.swing.JButton();
        jScrollPane10 = new javax.swing.JScrollPane();
        jtVoluntariosTarefa = new javax.swing.JTable();
        jLabel64 = new javax.swing.JLabel();
        jbAddVoluntario = new javax.swing.JButton();
        jbRemoveVoluntário = new javax.swing.JButton();
        jScrollPane11 = new javax.swing.JScrollPane();
        jtPesquisa = new javax.swing.JTable();
        volunteerEventFilter = new javax.swing.JCheckBox();
        volunteerProjectFilter = new javax.swing.JCheckBox();
        jLabel45 = new javax.swing.JLabel();
        jLabel91 = new javax.swing.JLabel();
        searchVolunteersField = new javax.swing.JTextField();
        searchVolunteersButton = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel1 = new javax.swing.JLabel();
        addEventRaisedValue = new javax.swing.JFormattedTextField();
        jLabel4 = new javax.swing.JLabel();
        addEventParticipantNmr = new javax.swing.JFormattedTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Adicionar Evento");
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel5.setText("Observações:");

        addEventObservation.setColumns(20);
        addEventObservation.setRows(5);
        addEventObservation.setLineWrap(true);

        addEventObservation.setToolTipText("");

        addEventObservation.setWrapStyleWord(true);

        addEventObservation.setDocument(new JTextAreaLimit(500));
        jScrollPane3.setViewportView(addEventObservation);

        jLabel2.setText("Data:");

        addEventDate.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(new java.text.SimpleDateFormat("dd/MM/yyyy"))));
        addEventDate.setToolTipText("dd/MM/aaaa");
        addEventDate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addEventDateActionPerformed(evt);
            }
        });

        jLabel3.setText("Local:");

        addEventCancelButton.setText("Cancelar");
        addEventCancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addEventCancelButtonActionPerformed(evt);
            }
        });

        addEventcleanButton.setText("Limpar");
        addEventcleanButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addEventcleanButtonActionPerformed(evt);
            }
        });

        addEventSubmitButton.setText("Submeter");
        addEventSubmitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addEventSubmitButtonActionPerformed(evt);
            }
        });

        jtVoluntariosTarefa.setAutoCreateRowSorter(true);
        jtVoluntariosTarefa.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "id", "Nome", "NrHoras"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class
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
        jtVoluntariosTarefa.getTableHeader().setReorderingAllowed(false);
        jScrollPane10.setViewportView(jtVoluntariosTarefa);

        jLabel64.setFont(new java.awt.Font("Calibri Light", 0, 24)); // NOI18N
        jLabel64.setText("Participantes");

        jbAddVoluntario.setText("<");
        jbAddVoluntario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbAddVoluntarioActionPerformed(evt);
            }
        });

        jbRemoveVoluntário.setText(">");
        jbRemoveVoluntário.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbRemoveVoluntárioActionPerformed(evt);
            }
        });

        jtPesquisa.setAutoCreateRowSorter(true);
        jtPesquisa.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "id", "Nome", "NrHoras"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                true, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtPesquisa.getTableHeader().setReorderingAllowed(false);
        jScrollPane11.setViewportView(jtPesquisa);

        volunteerEventFilter.setBackground(new java.awt.Color(255, 255, 255));
        volunteerEventFilter.setText("Participou em pelo menos um evento");

        volunteerProjectFilter.setBackground(new java.awt.Color(255, 255, 255));
        volunteerProjectFilter.setText("Trabalhou em pelo menos um projeto");
        volunteerProjectFilter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                volunteerProjectFilterActionPerformed(evt);
            }
        });

        jLabel45.setText("Filtros");

        jLabel91.setText("Pesquisa:");

        searchVolunteersButton.setText("Pesquisar");

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jLabel1.setText("Valor Angariado:");

        addEventRaisedValue.setEditable(true);
        addEventRaisedValue.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00; #0.00"))));

        jLabel4.setText("Nmr de participantes:");

        addEventParticipantNmr.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#"))));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel64)
                        .addGap(423, 423, 423))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(addEventCancelButton)
                                .addGap(173, 173, 173)
                                .addComponent(addEventcleanButton))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel4))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 412, Short.MAX_VALUE)
                                    .addComponent(addEventAddress)
                                    .addComponent(addEventDate, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(addEventParticipantNmr, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE)
                                        .addComponent(addEventRaisedValue, javax.swing.GroupLayout.Alignment.LEADING)))))
                        .addGap(38, 38, 38))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(addEventSubmitButton)
                            .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 468, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(27, 27, 27)
                                .addComponent(jbRemoveVoluntário))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jbAddVoluntario)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 339, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(369, 369, 369)
                .addComponent(searchVolunteersButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(634, 634, 634)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel45)
                        .addComponent(volunteerProjectFilter)
                        .addComponent(volunteerEventFilter)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel91)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(searchVolunteersField, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGap(165, 466, Short.MAX_VALUE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jSeparator1)
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(addEventDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(addEventAddress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(searchVolunteersButton))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(addEventRaisedValue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(5, 5, 5)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(addEventParticipantNmr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel64)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(69, 69, 69)
                                .addComponent(jbAddVoluntario)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jbRemoveVoluntário))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(addEventCancelButton)
                                        .addComponent(addEventcleanButton))
                                    .addComponent(addEventSubmitButton))))
                        .addGap(29, 29, 29))))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(42, 42, 42)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel91)
                        .addComponent(searchVolunteersField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jLabel45)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(volunteerProjectFilter)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(volunteerEventFilter)
                    .addContainerGap(431, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1006, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void addEventDateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addEventDateActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_addEventDateActionPerformed

    private void addEventCancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addEventCancelButtonActionPerformed
        this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
    }//GEN-LAST:event_addEventCancelButtonActionPerformed

    private void addEventcleanButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addEventcleanButtonActionPerformed
        addEventAddress.setText("");
        addEventDate.setText("");
        addEventObservation.setText("");
    }//GEN-LAST:event_addEventcleanButtonActionPerformed

    private void jbAddVoluntarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbAddVoluntarioActionPerformed
        Integer rowID;
        if(( rowID = jtPesquisa.getSelectedRow()) >= 0){
            if(!this.addedVol.contains((String) jtPesquisa.getValueAt( jtPesquisa.getSelectedRow(), 0 ) )){
                ((DefaultTableModel)jtVoluntariosTarefa.getModel()).addRow(new Object[]{jtPesquisa.getValueAt( jtPesquisa.getSelectedRow(), 0 ), jtPesquisa.getValueAt( jtPesquisa.getSelectedRow(),1 ), jtPesquisa.getValueAt( jtPesquisa.getSelectedRow(),2 )});
                addedVol.add((Integer) jtPesquisa.getValueAt( jtPesquisa.getSelectedRow(), 0 ));
            }
        }
    }//GEN-LAST:event_jbAddVoluntarioActionPerformed

    private void jbRemoveVoluntárioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbRemoveVoluntárioActionPerformed
        Integer rowID;
        if(( rowID = jtVoluntariosTarefa.getSelectedRow()) >= 0){
            addedVol.remove((Integer) jtVoluntariosTarefa.getValueAt( jtVoluntariosTarefa.getSelectedRow(), 0 ) );
            ((DefaultTableModel)jtVoluntariosTarefa.getModel()).removeRow(rowID);
        }
    }//GEN-LAST:event_jbRemoveVoluntárioActionPerformed

    private void volunteerProjectFilterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_volunteerProjectFilterActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_volunteerProjectFilterActionPerformed

    private void addEventSubmitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addEventSubmitButtonActionPerformed
        Controller<Event> ec = ControllerFactory.getEventsController();
        HashMap<String, Integer> volTar = new HashMap<>();
        
        try {           
            final Event e = ec.save(new HashMap<String, Object>() {{
                put("date", Util.strToDate( addEventDate.getText() ));
                put("location", addEventAddress.getText() );
                put("observations", addEventObservation.getText());
                put("amountRaised", Float.parseFloat(addEventRaisedValue.getText()));
                put("participantsNr", Integer.valueOf(addEventParticipantNmr.getText()));
            }});
 

            /*
        VolunteersEventController vec = ControllerFactory.getVolunteersEventController();
        vec.saveAll(e.getId(), addedVol);
        */
        //falta os voluntarios
            
        } catch (DataException e) {
            // SHOW ERROR MESSAGE
        }
    }//GEN-LAST:event_addEventSubmitButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField addEventAddress;
    private javax.swing.JButton addEventCancelButton;
    private javax.swing.JFormattedTextField addEventDate;
    private javax.swing.JTextArea addEventObservation;
    private javax.swing.JFormattedTextField addEventParticipantNmr;
    private javax.swing.JFormattedTextField addEventRaisedValue;
    private javax.swing.JButton addEventSubmitButton;
    private javax.swing.JButton addEventcleanButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel91;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JButton jbAddVoluntario;
    private javax.swing.JButton jbRemoveVoluntário;
    private javax.swing.JTable jtPesquisa;
    private javax.swing.JTable jtVoluntariosTarefa;
    private javax.swing.JButton searchVolunteersButton;
    private javax.swing.JTextField searchVolunteersField;
    private javax.swing.JCheckBox volunteerEventFilter;
    private javax.swing.JCheckBox volunteerProjectFilter;
    // End of variables declaration//GEN-END:variables
}

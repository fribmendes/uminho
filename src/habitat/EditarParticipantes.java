/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package habitatinterface;

import java.awt.event.WindowEvent;
import java.util.HashSet;
import java.util.Set;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author paulo
 */
public class EditarParticipantes extends javax.swing.JDialog {

    Set<String> addedVol;
            
    /**
     * Creates new form AdicionarNovosParticipantes
     */
    public EditarParticipantes(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        addedVol = new HashSet<String>();
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
        jLabel1 = new javax.swing.JLabel();
        jScrollPane16 = new javax.swing.JScrollPane();
        editParticipantsVolunteersTable = new javax.swing.JTable();
        addParticipantButton = new javax.swing.JButton();
        removeParticipantButton = new javax.swing.JButton();
        jScrollPane15 = new javax.swing.JScrollPane();
        editParticipantsParticipantsTable = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        addEventSubmitButton = new javax.swing.JButton();
        addEventCancelButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("Voluntários");

        editParticipantsVolunteersTable.setAutoCreateRowSorter(true);
        editParticipantsVolunteersTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"Paulo"},
                {"Tiago"},
                {"António Nestor"}
            },
            new String [] {
                "Nome"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane16.setViewportView(editParticipantsVolunteersTable);

        addParticipantButton.setText(">");
        addParticipantButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addParticipantButtonActionPerformed(evt);
            }
        });

        removeParticipantButton.setText("<");
        removeParticipantButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeParticipantButtonActionPerformed(evt);
            }
        });

        editParticipantsParticipantsTable.setAutoCreateRowSorter(true);
        editParticipantsParticipantsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"Cenas"},
                {"Mais cenas"}
            },
            new String [] {
                "Nome"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane15.setViewportView(editParticipantsParticipantsTable);

        jLabel4.setText("Participantes");

        addEventSubmitButton.setText("Submeter");

        addEventCancelButton.setText("Cancelar");
        addEventCancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addEventCancelButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(102, 102, 102)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 232, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addGap(94, 94, 94))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(addEventCancelButton)
                        .addGap(18, 18, 18)
                        .addComponent(addEventSubmitButton))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addComponent(jScrollPane16, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(removeParticipantButton, javax.swing.GroupLayout.DEFAULT_SIZE, 67, Short.MAX_VALUE)
                            .addComponent(addParticipantButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane15, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                .addGap(38, 38, 38))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel4)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane15, javax.swing.GroupLayout.DEFAULT_SIZE, 233, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(58, 58, 58)
                        .addComponent(addParticipantButton)
                        .addGap(50, 50, 50)
                        .addComponent(removeParticipantButton))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane16, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addEventCancelButton)
                    .addComponent(addEventSubmitButton))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void addParticipantButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addParticipantButtonActionPerformed
        Integer rowID;
        if(( rowID = editParticipantsVolunteersTable.getSelectedRow()) >= 0){
            if(!this.addedVol.contains((String) editParticipantsVolunteersTable.getValueAt( editParticipantsVolunteersTable.getSelectedRow(), 0 ) )){
                ((DefaultTableModel) editParticipantsParticipantsTable.getModel()).addRow(new Object[]{editParticipantsVolunteersTable.getValueAt( editParticipantsVolunteersTable.getSelectedRow(), 0 ), "", ""});
                addedVol.add((String) editParticipantsVolunteersTable.getValueAt( editParticipantsVolunteersTable.getSelectedRow(), 0 ) );
            }
        }
    }//GEN-LAST:event_addParticipantButtonActionPerformed

    private void removeParticipantButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeParticipantButtonActionPerformed
        Integer rowID;
        if(( rowID = editParticipantsParticipantsTable.getSelectedRow()) >= 0){
            addedVol.remove((String) editParticipantsParticipantsTable.getValueAt( editParticipantsParticipantsTable.getSelectedRow(), 0 ) );
            ((DefaultTableModel)editParticipantsParticipantsTable.getModel()).removeRow(rowID);
        }
    }//GEN-LAST:event_removeParticipantButtonActionPerformed

    private void addEventCancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addEventCancelButtonActionPerformed
        this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
    }//GEN-LAST:event_addEventCancelButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addEventCancelButton;
    private javax.swing.JButton addEventSubmitButton;
    private javax.swing.JButton addParticipantButton;
    private javax.swing.JTable editParticipantsParticipantsTable;
    private javax.swing.JTable editParticipantsVolunteersTable;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane15;
    private javax.swing.JScrollPane jScrollPane16;
    private javax.swing.JButton removeParticipantButton;
    // End of variables declaration//GEN-END:variables
}

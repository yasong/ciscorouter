package ciscoroutertool.gui;

import ciscoroutertool.config.ConfigurationManager;
import ciscoroutertool.scanner.FullReport;
import ciscoroutertool.scanner.ScanManager;
import ciscoroutertool.settings.SettingsManager;
import ciscoroutertool.utils.Host;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import java.io.File;
import java.util.ArrayList;
import java.util.Vector;

/**
 * Runs the Main GUI and acts as a portal to the rest of the application
 * @version 0.01ALPHA
 * @author Andrew H. Johnston
 */
public class MainGUI  extends javax.swing.JFrame implements ScanLauncherParent {
    /**
     * Default Serial ID
     */
    private static final long serialVersionUID = 1L;

    /**
     * Filter that will only show configuration (XML) files in the open dialog.
     */
    private final FileNameExtensionFilter filter 
            = new FileNameExtensionFilter("Configuration File", "xml");
    
    /**
     * The file dialog used to open/save files
     */
    private final  JFileChooser fc = new JFileChooser();
    
    /**
     * The object that allows the app to access and store settings
     */
    public final static SettingsManager settingsManager = new SettingsManager();
    
    /**
     * The "Please Wait" dialog that displays while scanning
     */
    private final ScanningDialog scanning = new ScanningDialog();
    
    /**
     * The list of hosts to be scanned
     */
    private static final ArrayList<Host> hosts = new ArrayList<>();
    
    /**
     * The current row of the table we are filling
     */
    private int currentRow = 0;
    
    /**
     * Creates new form MainGUI
     */
    public MainGUI() {
        initComponents();
        fc.setFileFilter(filter);
        if (settingsManager.requiresAuth()) {
            AuthDialog auth = new AuthDialog(this);
            this.setEnabled(false);
            auth.setVisible(true);
            auth.setAlwaysOnTop(true);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        currentConfTable = new javax.swing.JTable();
        lblConfTable = new javax.swing.JLabel();
        btnAddDevice = new javax.swing.JButton();
        btnRunScan = new javax.swing.JToggleButton();
        menuBar = new javax.swing.JMenuBar();
        fileMenu = new javax.swing.JMenu();
        menuOpenConfig = new javax.swing.JMenuItem();
        menuSaveConfig = new javax.swing.JMenuItem();
        menuRunScan = new javax.swing.JMenuItem();
        menuClose = new javax.swing.JMenuItem();
        editMenu = new javax.swing.JMenu();
        menuAddDevice = new javax.swing.JMenuItem();
        menuSecurityChange = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Crest Security Cisco Router Testing Tool");

        currentConfTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Scan", "IP Address", "Scan Type"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        currentConfTable.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(currentConfTable);
        if (currentConfTable.getColumnModel().getColumnCount() > 0) {
            currentConfTable.getColumnModel().getColumn(0).setMinWidth(80);
            currentConfTable.getColumnModel().getColumn(0).setPreferredWidth(80);
            currentConfTable.getColumnModel().getColumn(0).setMaxWidth(80);
        }

        lblConfTable.setText("Current Configuration: ");

        btnAddDevice.setText("Add New Device...");
        btnAddDevice.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddDeviceActionPerformed(evt);
            }
        });

        btnRunScan.setText("Run Scan...");
        btnRunScan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRunScanActionPerformed(evt);
            }
        });

        fileMenu.setText("File");

        menuOpenConfig.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_MASK));
        menuOpenConfig.setText("Open...");
        menuOpenConfig.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuOpenConfigActionPerformed(evt);
            }
        });
        fileMenu.add(menuOpenConfig);

        menuSaveConfig.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        menuSaveConfig.setText("Save...");
        menuSaveConfig.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuSaveConfigActionPerformed(evt);
            }
        });
        fileMenu.add(menuSaveConfig);

        menuRunScan.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_R, java.awt.event.InputEvent.CTRL_MASK));
        menuRunScan.setText("Run");
        fileMenu.add(menuRunScan);

        menuClose.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_W, java.awt.event.InputEvent.CTRL_MASK));
        menuClose.setText("Close...");
        menuClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuCloseActionPerformed(evt);
            }
        });
        fileMenu.add(menuClose);

        menuBar.add(fileMenu);

        editMenu.setText("Edit");

        menuAddDevice.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_MASK));
        menuAddDevice.setText("Add a Device..");
        menuAddDevice.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuAddDeviceActionPerformed(evt);
            }
        });
        editMenu.add(menuAddDevice);

        menuSecurityChange.setText("Enable/Disable Security");
        menuSecurityChange.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuSecurityChangeActionPerformed(evt);
            }
        });
        editMenu.add(menuSecurityChange);

        menuBar.add(editMenu);

        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 649, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblConfTable, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnAddDevice)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnRunScan)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblConfTable, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAddDevice)
                    .addComponent(btnRunScan))
                .addContainerGap(143, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Handles the opening of previously saved scanning configurations 
     * @param evt The ActionEvent object with relevant data
     */
    private void menuOpenConfigActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuOpenConfigActionPerformed
        int returnVal = fc.showOpenDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File f = fc.getSelectedFile();
            ConfigurationManager manager = new ConfigurationManager(f);
            ArrayList<Host> configHosts = manager.getAllHosts();
            hosts.addAll(configHosts);
            for(Host h : hosts) {
                this.addHostToTable(h);
            }
        }
    }//GEN-LAST:event_menuOpenConfigActionPerformed

    /**
     * Show the box that allows for the setting of a password
     * @param evt The ActionEvent object with relevant data
     */
    private void menuSecurityChangeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuSecurityChangeActionPerformed
        if (settingsManager.requiresAuth()) {
            SecurityDialog securityDialog =
                    new SecurityDialog(settingsManager.getUsername(), settingsManager.getPassword());
            securityDialog.setVisible(true);
        } else {
            SecurityDialog secDialog = new SecurityDialog();
            secDialog.setVisible(true);
        }
    }//GEN-LAST:event_menuSecurityChangeActionPerformed

    /**
     * Allows the user to close the application
     * @param evt The ActionEvent object with relevant data
     */
    private void menuCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuCloseActionPerformed
        int confirm = JOptionPane.showConfirmDialog(this, 
                "Are you sure you'd like to quit?",
                "Quit the Application",
                JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
        //Otherwise do nothing
    }//GEN-LAST:event_menuCloseActionPerformed

    /**
     * Opens the window to allow a new device to be added to the current scan 
     * configuration.
     * @param evt The ActionEvent object with relevant data
     */
    private void menuAddDeviceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuAddDeviceActionPerformed
        this.openNewDeviceDialog();
    }//GEN-LAST:event_menuAddDeviceActionPerformed

    /**
     * Opens the window to allow a new device to be added to the current scan 
     * configuration.
     * @param evt The ActionEvent object with relevant data
     */
    private void btnAddDeviceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddDeviceActionPerformed
        this.openNewDeviceDialog();
    }//GEN-LAST:event_btnAddDeviceActionPerformed

    /**
     * Opens the new device dialog with all relevant parameters
     */
    private void openNewDeviceDialog() {
        NewDeviceDialog device = new NewDeviceDialog(this);
        device.setVisible(true);
    }
    
    /**
     * Runs a scan on the selected hosts.
     * @param evt The ActionEvent object with relevant data
     */
    private void btnRunScanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRunScanActionPerformed
        if (hosts.size() < 1) {
            JOptionPane.showMessageDialog(this, "Please enter at least one host.");
        } else {
            ScanManager manager = new ScanManager(this.getHostsToScan());
            ScanLauncher launcher = new ScanLauncher(this, manager);
            launcher.execute();
            btnRunScan.setSelected(false);
        }
        
    }//GEN-LAST:event_btnRunScanActionPerformed

    /**
     * Allows the user to save the current configuration from the file
     * @param evt The ActionEvent object with relevant data
     */
    private void menuSaveConfigActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuSaveConfigActionPerformed
        int returnCode = fc.showSaveDialog(this);
        if (returnCode == JFileChooser.APPROVE_OPTION) {
            File file = fc.getSelectedFile();
            String filename = file.getName();
            String path     = file.getPath();
            //Make sure the filename ends in .xml
            if (!filename.matches("(.*)\\.xml$")) {
                filename = filename + ".xml";
                file = new File(path + filename);
            }
            ConfigurationManager.saveConfiguration(file, hosts);
            JOptionPane.showMessageDialog(this, 
                    "File Saved Successfully!", 
                    "Save Successful",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_menuSaveConfigActionPerformed
    
    /**
     * Shows the Output window (called after the scan is completed)
     * @param report The Full Report on all hosts
     */
    public void displayReport(FullReport report) {
        OutputReview output = new OutputReview(report);
        output.setVisible(true);
    }
    
    /**
     * Starts the application
     * @param args the command line arguments
     */
    @SuppressWarnings("TryWithIdenticalCatches")
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
            java.util.logging.Logger.getLogger(MainGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainGUI().setVisible(true);
            }
        });
    }
    
    /**
     * Adds a host to be scanned into the JTable and to the ArrayList. Note that
     * the JTable is merely for presentation, the ArrayList holds the actual 
     * data.
     * @param h The host to add to the table
     */
    public void updateTable(Host h) {
        hosts.add(h);
        this.addHostToTable(h);
    }

    /**
     * Adds a host to the JTable
     * @param h The host to add to the table
     */
    public void addHostToTable(Host h) {
        final int ROWS_BY_DEFAULT = 4;
        DefaultTableModel model = (DefaultTableModel)
                currentConfTable.getModel();

        String hosts = h.toString();
        //We have four rows by default
        if (currentRow >= ROWS_BY_DEFAULT) {
            Vector v = new Vector();
            model.addRow(v);
        }
        int rowToFill = currentRow;
        currentConfTable.setValueAt(true, rowToFill, 0);
        currentConfTable.setValueAt(hosts, rowToFill, 1);
        currentConfTable.setValueAt("Full Scan", rowToFill, 2);
        currentRow++;
    }

    /**
     * Returns a "cleaned" version of the hostname
     * @return Just the IP address of a hostname
     */
    private String getCleanHostname(Host h) {
        String hosts = h.getAddress().toString();
        int c = hosts.indexOf("/");
        hosts = hosts.substring((c + 1));
        return hosts;
    }
    /**
     * Shows the "Please Wait" Dialog while the scan runs.
     */
    @Override
    public void showPleaseWaitDialog() {
       scanning.setVisible(true);
    }

    /**
     * Removes the "Please Wait" Dialog once the scan is finished.
     */
    @Override
    public void disposePleaseWaitDialog() {
       scanning.dispose();
    }
    
    /**
     * Returns a list of hosts to be scanned (factoring in the checkboxes)
     * @return A list of hosts that are "checked" and should be scanned
     */
    public ArrayList<Host> getHostsToScan() {
        ArrayList<Host> toScan = new ArrayList<>();
        DefaultTableModel tableModel = (DefaultTableModel) currentConfTable.getModel();
        //By writing the number of rows as currentRow, we won't deal with empty rows!
        int numRows = currentRow;
        for (int i = 0; i < numRows; i++) {
            //If the host is unchecked
            if (!((boolean)tableModel.getValueAt(i, 0))) {
                continue; //Don't bother processing it
            }
            String guiHostname = (String) tableModel.getValueAt(i,1);
            for (Host h : hosts) {
                String hostname = h.toString();
                if (hostname.compareTo(guiHostname) == 0) {
                    toScan.add(h);
                }
            }
        }
        return toScan;
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddDevice;
    private javax.swing.JToggleButton btnRunScan;
    private static javax.swing.JTable currentConfTable;
    private javax.swing.JMenu editMenu;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblConfTable;
    private javax.swing.JMenuItem menuAddDevice;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenuItem menuClose;
    private javax.swing.JMenuItem menuOpenConfig;
    private javax.swing.JMenuItem menuRunScan;
    private javax.swing.JMenuItem menuSaveConfig;
    private javax.swing.JMenuItem menuSecurityChange;
    // End of variables declaration//GEN-END:variables
}

package form;

import actionListeners.ActionListenerGetClusterTopology;
//import actionListeners.ActionListenerGetDatabaseEntries;
import actionListeners.ActionListenerGetOpReqResult;
import actionListeners.ActionListenerGetServiceTree;
import heartbeat.Host;
//import net.proteanit.sql.DbUtils;

import javax.swing.*;
import javax.swing.table.TableModel;
import javax.swing.tree.DefaultTreeModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Map;
import java.util.UUID;

public class Client extends JFrame implements ActionListener {
    protected JPanel appViewJPanel;
    protected JTextField ipJTextField;
    protected JButton getTopologyJButton;
    protected JButton refreshJButton;
    protected JButton getDBEntriesJButton;
    protected JTextField clusterManagerPortJTextField;
    protected JTree hostsJTree;
    protected Map<UUID, Host> hosts;
    private JTextField inputParamsJTextField;
  //  private JTextArea inputParamsJTextArea;
    private JTextArea responseJTextArea;
    private JButton getResultFromServiceJButton;
    private JTable dbJTable;
    private JTextArea sqlErrorJTextArea;
    private JLabel sqjErrorJLabel;
    private JTextArea inputParametersJTextArea;
    private String selectedServiceName;
    private Host selectedHost;

/*    String dbHost = "jdbc:mysql://localhost:3306/employees";
    String uName = "root";
    String uPass = "root1234";

    public void fetch_DB_Data(){

        try {

            Connection connection = DriverManager.getConnection(dbHost, uName, uPass);
            Statement statement = connection.createStatement();
            String sql  = "select * from workers";
            ResultSet resultSet = statement.executeQuery(sql);
            dbJTable.setModel( DbUtils.resultSetToTableModel(resultSet));

        }   catch (SQLException e){

            e.printStackTrace();

              this.sqlErrorJTextArea.append("\n" + e);
        }

    }*/

    public Client() {
        hostsJTree.setVisible(false);
        add(appViewJPanel);
        setTitle("Cluster Handler/Manager Client App V:0.16.08.2020");
        setSize(new Dimension(1200, 800));

        inputParametersJTextArea.setEditable(false);
        responseJTextArea.setEditable(false);
        sqlErrorJTextArea.setEditable(false);
        getTopologyJButton.addActionListener(new ActionListenerGetClusterTopology(this));
        refreshJButton.addActionListener(new ActionListenerGetClusterTopology(this));
        hostsJTree.addTreeSelectionListener(new ActionListenerGetServiceTree(this));
        getResultFromServiceJButton.addActionListener(new ActionListenerGetOpReqResult(this));
      //  getDBEntriesJButton.addActionListener(new ActionListenerGetDatabaseEntries(this));
    }

    public void updateResultTextArea(String result) {
        this.responseJTextArea.append("\n" + result);
    }
    public void updateErrosAndSqlResultTextArea(String result) {
        this.sqlErrorJTextArea.append("\n" + result);
    }

    public void updateSelectedHost(Host host) {
        this.selectedHost = host;
    }

    public void updateInputParametersTextArea(String parameters) {
        //inputParamsJTextArea.setText(parameters);
        inputParametersJTextArea.setText(parameters);
    }

    public Map<UUID, Host> getHosts() {
        return hosts;
    }

    public Client setHosts(Map<UUID, Host> hosts) {
        this.hosts = hosts;
        return this;
    }

    public Host getSelectedHost() {
        return selectedHost;
    }

    public Client setSelectedHost(Host selectedHost) {
        this.selectedHost = selectedHost;
        return this;
    }


    public void updateTree(DefaultTreeModel treeModel) {
        hostsJTree.setModel(treeModel);
        hostsJTree.setVisible(true);
    }

    public JPanel getAppViewJPanel() {
        return appViewJPanel;
    }

    public Client setAppViewJPanel(JPanel appViewJPanel) {
        this.appViewJPanel = appViewJPanel;
        return this;
    }

    public JTextField getIpJTextField() {
        return ipJTextField;
    }

    public Client setIpJTextField(JTextField ipJTextField) {
        this.ipJTextField = ipJTextField;
        return this;
    }

    public JButton getGetTopologyJButton() {
        return getTopologyJButton;
    }

    public Client setGetTopologyJButton(JButton getTopologyJButton) {
        this.getTopologyJButton = getTopologyJButton;
        return this;
    }

    public JTextField getClusterManagerPortJTextField() {
        return clusterManagerPortJTextField;
    }

    public Client setClusterManagerPortJTextField(JTextField clusterManagerPortJTextField) {
        this.clusterManagerPortJTextField = clusterManagerPortJTextField;
        return this;
    }

    public JTree getHostsJTree() {
        return hostsJTree;
    }

    public Client setHostsJTree(JTree hostsJTree) {
        this.hostsJTree = hostsJTree;
        return this;
    }

    public void updateSelectedServiceName(String serviceName) {
        this.selectedServiceName = serviceName;
    }

    public JTextField getInputParamsJTextField() {
        return inputParamsJTextField;
    }

    public Client setInputParamsJTextField(JTextField inputParamsJTextField) {
        this.inputParamsJTextField = inputParamsJTextField;
        return this;
    }

    public JTextArea getResponseJTextArea() {
        return responseJTextArea;
    }

    public Client setResponseJTextArea(JTextArea responseJTextArea) {
        this.responseJTextArea = responseJTextArea;
        return this;
    }

    public JTextArea getSqlErrorTextArea() {
        return sqlErrorJTextArea;
    }

    public Client setSqlErrorTextArea() {
        this.sqlErrorJTextArea = sqlErrorJTextArea;
        return this;
    }
    public JButton buttonGetDBEntries() {
        return getDBEntriesJButton;
    }
    public Client setResultFromdb(JButton buttonGetDBEntries) {
        this.getDBEntriesJButton = buttonGetDBEntries;
        return this;
    }


    public JButton getGetResultFromServiceJButton() {
        return getResultFromServiceJButton;
    }

    public Client setGetResultFromServiceJButton(JButton getResultFromServiceJButton) {
        this.getResultFromServiceJButton = getResultFromServiceJButton;
        return this;
    }

    public JTable getDbJTable(){return dbJTable;}
    public Client setDbJTable(JTable dbJTable){
        this.dbJTable = dbJTable;
        return this;
    }
    public void updateDBTableModel(JTable defaultTableModel) {
        dbJTable.setModel((TableModel) defaultTableModel);
        dbJTable.setVisible(true);
    }

    public JTextArea getInputParamsJTextArea() {
        return inputParametersJTextArea;
    }

    public Client setInputParamsJTextArea(JTextArea inputParamsJTextArea) {
        this.inputParametersJTextArea = inputParamsJTextArea;
        return this;
    }

    public String getSelectedServiceName() {
        return selectedServiceName;
    }

    public Client setSelectedServiceName(String selectedServiceName) {
        this.selectedServiceName = selectedServiceName;
        return this;
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}

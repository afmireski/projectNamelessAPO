package screens;

import controllers.FuncionarioController;
import helpers.BuildConfirmDialog;
import helpers.BuildMessageDialog;
import helpers.ErrorTools;
import helpers.GenericComponents;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Date;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import tools.CaixaDeFerramentas;
import tools.Tools;
import enums.DialogMessageType;
import daos.DAOFuncionario;
import models.Funcionario;

/**
 *
 * @author afmireski
 */
public class FuncionarioScreen extends JDialog {

//INSTANCIA DOS HELPERS
    GenericComponents components = new GenericComponents();
    BuildMessageDialog messageDialog;
    BuildConfirmDialog confirmDialog;
    ErrorTools errorTools = new ErrorTools();

//INSTANCIA DOS FUNCTIONS
//INSTANCIA DAS TOOLS
    final private CaixaDeFerramentas cf = new CaixaDeFerramentas();
    Tools tools = new Tools();

//INSTANCIA DOS CONTAINERS
    Container container;

//INSTANCIA DOS PANELS
    //PANELS BASE
    JPanel panNorth = new JPanel();
    JPanel panSouth = new JPanel();
    JPanel panEast = new JPanel();
    JPanel panWest = new JPanel();
    JPanel panBody = new JPanel();

//BODY PANELS
    JPanel panL1C1 = new JPanel(); //Painel referente a posição da grade: Linha 1 - Coluna 1
    JPanel panL1C2 = new JPanel(); //Painel referente a posição da grade: Linha 1 - Coluna 2
    JPanel panL2C1 = new JPanel(); //Painel referente a posição da grade: Linha 2 - Coluna 1
    JPanel panL2C2 = new JPanel(); //Painel referente a posição da grade: Linha 2 - Coluna 2
    JPanel panL3C1 = new JPanel(); //Painel referente a posição da grade: Linha 3 - Coluna 1
    JPanel panL3C2 = new JPanel(); //Painel referente a posição da grade: Linha 3 - Coluna 2

//INSTANCIA DOS BUTTONS
    JButton btnCreate = new JButton("Create");
    JButton btnRetrieve = new JButton("Retrieve");
    JButton btnUpdate = new JButton("Update");
    JButton btnAction = new JButton("Add to List");
    JButton btnCancel = new JButton("Cancel");
    JButton btnList = new JButton("List");

//INSTANCIA DOS CONTROLLERS
    String actionController;
    boolean listController = false;
    FuncionarioController funcionarioController = new FuncionarioController();

//INSTANCIA DOS LABELS
    JLabel lblId = new JLabel("ID");
    JLabel lblNome = new JLabel("NOME");
    JLabel lblDepartamento = new JLabel("DEPARTAMENTO");

//INSTANCIA DOS TEXTFIELD
    JTextField txtId = new JTextField(16);
    JTextField txtNome = new JTextField(16);
    JTextField txtDepartamento = new JTextField(16);

//INSTANCIA DAS ENTIDADES
    Funcionario funcionario = new Funcionario();
//INSTANCIA DAS TABLE SCREENS
    FuncionarioTableScreen funcionarioTableScreen;

    public FuncionarioScreen() {
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setTitle("CRUD - FUNCIONARIO");

        //LOAD DATA
        final String path = "Funcionario.csv";
        funcionarioController.loadData(path);

        buttonsInitialConfiguration();
        textFieldInitialConfiguration();

        //CONTAINER CONFIGURATIONS
        container = getContentPane();
        container.setLayout(new BorderLayout());
        container.add(panNorth, BorderLayout.NORTH);
        container.add(panSouth, BorderLayout.SOUTH);
        container.add(panEast, BorderLayout.EAST);
        container.add(panWest, BorderLayout.WEST);
        container.add(panBody, BorderLayout.CENTER);

        //PAN NORTH CONFIGURATIONS
        panNorth.setLayout(new FlowLayout(FlowLayout.LEFT));
        panNorth.add(lblId);
        panNorth.add(txtId);

        panNorth.add(btnRetrieve);
        panNorth.add(btnCreate);
        panNorth.add(btnUpdate);
        panNorth.add(btnList);
        panNorth.add(btnCancel);
        //PAN EAST CONFIGURATIONS
        //PAN WEST CONFIGURATIONS

        //PAN BODY CONFIGURATIONS
        panBody.setBorder(BorderFactory.createLineBorder(Color.black, 5));
        panBody.setLayout(new GridLayout(3, 2));

        //Prenchimento por Linha
        panBody.add(panL1C1);
        panBody.add(panL1C2);
        panBody.add(panL2C1);
        panBody.add(panL2C2);
        panBody.add(panL3C1);
        panBody.add(panL3C2);

        //Prenchimento Linha 1
        panL1C1.add(lblNome);
        panL1C2.add(txtNome);

        //Prenchimento Linha 2
        panL2C1.add(lblDepartamento);
        panL2C2.add(txtDepartamento);

        panL3C2.add(btnAction);
        //BTN RETRIEVE ACTION LISTENER
        btnRetrieve.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (!txtId.getText().trim().isEmpty()) {
                        funcionario = funcionarioController.retrieve(txtId.getText());
                        if (funcionario != null) {
                            btnCreate.setEnabled(false);
                            btnCreate.setVisible(true);
                            btnUpdate.setEnabled(true);
                            btnUpdate.setVisible(true);

                            txtNome.setEditable(false);
                            txtDepartamento.setEditable(false);

                            txtId.setText(String.valueOf(funcionario.getId()));
                            txtNome.setText(String.valueOf(funcionario.getNome()));
                            txtDepartamento.setText(String.valueOf(funcionario.getDepartamento()));
                        } else {
                            btnCreate.setEnabled(true);
                            btnCreate.setVisible(true);
                            btnUpdate.setEnabled(false);

                            txtNome.setEditable(true);
                            txtDepartamento.setEditable(true);
                            txtNome.setText("");
                            txtDepartamento.setText("");
                        }
                    }
                } catch (Exception excep) {
                    errorTools.showExceptionStackTrace(excep);
                    messageDialog = new BuildMessageDialog(
                            DialogMessageType.ERROR,
                            excep.getMessage(),
                            "Data Error",
                            container);
                }
            }
        });

        //BTN CREATE ACTION LISTENER
        btnCreate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnRetrieve.setEnabled(false);
                btnUpdate.setEnabled(false);
                btnCreate.setVisible(false);
                btnCancel.setVisible(true);
                btnAction.setVisible(true);

                txtId.setEditable(false);
                txtNome.requestFocus();

                actionController = "CREATE";

                btnAction.setText("Adicionar à Lista");
            }
        });

        //BTN UPDATE ACTION LISTENER
        btnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnRetrieve.setEnabled(false);
                btnUpdate.setEnabled(false);
                btnCreate.setVisible(false);
                btnCancel.setVisible(true);
                btnAction.setVisible(true);

                txtId.setEditable(false);
                txtNome.setEditable(true);
                txtDepartamento.setEditable(true);
                txtNome.requestFocus();

                actionController = "UPDATE";

                btnAction.setText("Atualizar na Lista");
            }
        });

        //BTN ACTION ACTION LISTENER
        btnAction.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (actionController.equalsIgnoreCase("CREATE")) {
                        funcionario = new Funcionario();
                    }
                    Funcionario funcionarioAntigo = funcionario;
                    if (txtNome.getText().trim().isEmpty() || txtDepartamento.getText().trim().isEmpty()) {
                        throw new Exception("Verifique se os seus campos estão preenchidos!");
                    } else {
                        funcionario.setId(txtId.getText());
                        funcionario.setNome(txtNome.getText());
                        funcionario.setDepartamento(txtDepartamento.getText());
                        funcionario.setDataAtualizacao(new Date());
                        funcionario.setDataExclusao(null);
                    }
                    if (actionController.equalsIgnoreCase("CREATE")) {
                        funcionario.setDataCadastro(new Date());
                        funcionarioController.create(funcionario);
                    } else if (actionController.equalsIgnoreCase("UPDATE")) {
                        funcionario.setDataCadastro(funcionarioAntigo.getDataCadastro());
                        funcionarioController.update(funcionarioAntigo, funcionario);
                    } else {

                        throw new Exception("Falha ao executar a ação na lista");

                    }
                    
                    System.out.println(funcionario.toString());

                    btnAction.setVisible(false);
                    btnRetrieve.setEnabled(true);
                    btnUpdate.setVisible(true);
                    btnUpdate.setEnabled(false);
                    btnCreate.setVisible(true);
                    btnCreate.setEnabled(false);
                    btnCancel.setVisible(false);
                    textFieldInitialConfiguration();

                    txtId.setEditable(true);
                    txtId.requestFocus();

                    clearAllFields();

                } catch (Exception excep) {
                    errorTools.showExceptionStackTrace(excep);
                    messageDialog = new BuildMessageDialog(
                            DialogMessageType.ERROR,
                            excep.getMessage(),
                            "ACTION Error",
                            container);
                }
            }
        });

        //BTN DELETE ACTION LISTENER

        //BTN LIST ACTION LISTENER
        btnList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // A JANELA TABELA SÓ PODE SER ABERTA SE TABLESCREEN NÃO ESTIVER ATIVA.
                if (listController) {
                    //SE TABLE SCREEN ESTIVER ATIVA, A FECHA ANTES DE ABRIR NOVAMENTE.
                    funcionarioTableScreen.dispose();
                    listController = false;
                }
                List<Funcionario> funcionarios = funcionarioController.listar();
                funcionarioTableScreen = new FuncionarioTableScreen(funcionarios);
                listController = true;
            }
        });
        //BTN CANCEL ACTION LISTENER
        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //RETORNA A TELA AO ESTADO INICIAL
                buttonsInitialConfiguration();
                textFieldInitialConfiguration();
                clearAllFields();

                txtId.requestFocus();

            }
        });

//Close Window Action
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                if (listController) {
                    //SE TABLE SCREEN ESTIVER ATIVA, A FECHA ANTES DE ABRIR NOVAMENTE.
                    funcionarioTableScreen.dispose();
                    listController = false;
                }
                funcionarioController.saveData(path);
                dispose();
            }
        });

        pack();
        setModal(true);
        setVisible(true);
    }

    private void buttonsInitialConfiguration() {	//BUTTONS INITIAL CONFIGURATIONS
        btnRetrieve.setEnabled(true);
        btnCancel.setVisible(false);
        btnList.setVisible(true);
        btnCreate.setEnabled(false);
        btnAction.setVisible(false);
        btnUpdate.setEnabled(false);
    }

    private void textFieldInitialConfiguration() {	//TEXTFIELD INITIAL CONFIGURATIONS
        txtId.setEditable(true);
        txtNome.setEditable(false);
        txtDepartamento.setEditable(false);
    }

    private void clearAllFields() {

        txtId.setText("");
        txtNome.setText("");
        txtDepartamento.setText("");
    }

}

package screens;

import controllers.MercadoriaController;
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
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import enums.DialogMessageType;
import java.util.Date;
import models.Mercadoria;

/**
 *
 * @author afmireski
 */
public class MercadoriaScreen extends JDialog {

//INSTANCIA DOS HELPERS
    GenericComponents components = new GenericComponents();
    BuildMessageDialog messageDialog;
    BuildConfirmDialog confirmDialog;
    ErrorTools errorTools = new ErrorTools();

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
    JPanel panL4C1 = new JPanel(); //Painel referente a posição da grade: Linha 4 - Coluna 1
    JPanel panL4C2 = new JPanel(); //Painel referente a posição da grade: Linha 4 - Coluna 2

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
    MercadoriaController mercadoriaController = new MercadoriaController();

//INSTANCIA DOS LABELS
    JLabel lblId = new JLabel("ID");
    JLabel lblIdCriador = new JLabel("IDCRIADOR");
    JLabel lblDescricao = new JLabel("DESCRICAO");
    JLabel lblQuantidadeEmEstoque = new JLabel("QUANTIDADEEMESTOQUE");

//INSTANCIA DOS TEXTFIELD
    JTextField txtId = new JTextField(16);
    JTextField txtIdCriador = new JTextField(16);
    JTextField txtDescricao = new JTextField(20);
    JTextField txtQuantidadeEmEstoque = new JTextField(10);

//INSTANCIA DAS ENTIDADES
    Mercadoria mercadoria = new Mercadoria();
//INSTANCIA DAS TABLE SCREENS
    MercadoriaTableScreen mercadoriaTableScreen;

    public MercadoriaScreen() {
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setTitle("CRUD - MERCADORIA");

        //LOAD DATA
        final String path = "Mercadoria.csv";
        mercadoriaController.loadData(path);

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
        panBody.setLayout(new GridLayout(4, 2));

        //Prenchimento por Linha
        panBody.add(panL1C1);
        panBody.add(panL1C2);
        panBody.add(panL2C1);
        panBody.add(panL2C2);
        panBody.add(panL3C1);
        panBody.add(panL3C2);
        panBody.add(panL4C1);
        panBody.add(panL4C2);

        //Prenchimento Linha 1
        panL1C1.add(lblIdCriador);
        panL1C2.add(txtIdCriador);

        //Prenchimento Linha 2
        panL2C1.add(lblDescricao);
        panL2C2.add(txtDescricao);

        //Prenchimento Linha 3
        panL3C1.add(lblQuantidadeEmEstoque);
        panL3C2.add(txtQuantidadeEmEstoque);

        //Prenchimento Linha 4
        panL4C2.add(btnAction);
        //BTN RETRIEVE ACTION LISTENER
        btnRetrieve.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (!txtId.getText().trim().isEmpty()) {
                        mercadoria = mercadoriaController.retrieve(txtId.getText());
                        if (mercadoria != null) {
                            btnCreate.setEnabled(false);
                            btnCreate.setVisible(true);
                            btnUpdate.setEnabled(true);
                            btnUpdate.setVisible(true);

                            txtIdCriador.setEditable(false);
                            txtDescricao.setEditable(false);
                            txtQuantidadeEmEstoque.setEditable(false);

                            txtId.setText(String.valueOf(mercadoria.getId()));
                            txtIdCriador.setText(String.valueOf(mercadoria.getIdCriador()));
                            txtDescricao.setText(String.valueOf(mercadoria.getDescricao()));
                            txtQuantidadeEmEstoque.setText(String.valueOf(mercadoria.getQuantidadeEmEstoque()));
                        } else {
                            btnCreate.setEnabled(true);
                            btnCreate.setVisible(true);
                            btnUpdate.setEnabled(false);

                            txtIdCriador.setEditable(true);
                            txtDescricao.setEditable(true);
                            txtQuantidadeEmEstoque.setEditable(true);
                            txtIdCriador.setText("");
                            txtDescricao.setText("");
                            txtQuantidadeEmEstoque.setText("");
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
                txtIdCriador.requestFocus();

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
                txtIdCriador.setEditable(true);
                txtDescricao.setEditable(true);
                txtQuantidadeEmEstoque.setEditable(true);
                txtIdCriador.requestFocus();

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
                        mercadoria = new Mercadoria();
                    }
                    Mercadoria mercadoriaAntigo = mercadoria;
                    if (txtIdCriador.getText().trim().isEmpty() || txtDescricao.getText().trim().isEmpty()
                            || txtQuantidadeEmEstoque.getText().trim().isEmpty()) {
                        throw new Exception("Verifique se os seus campos estão preenchidos!");
                    } else {
                        mercadoria.setId(txtId.getText());
                        mercadoria.setIdCriador(txtIdCriador.getText());
                        mercadoria.setDescricao(txtDescricao.getText());
                        mercadoria.setQuantidadeEmEstoque(Integer.valueOf(txtQuantidadeEmEstoque.getText()));
                        mercadoria.setDataAtualizacao(new Date());
                        mercadoria.setDataExclusao(null);
                    }
                    if (actionController.equalsIgnoreCase("CREATE")) {
                        mercadoria.setDataCadastro(new Date());
                        mercadoriaController.create(mercadoria);
                    } else if (actionController.equalsIgnoreCase("UPDATE")) {
                        mercadoriaController.update(mercadoriaAntigo, mercadoria);
                    } else {

                        throw new Exception("Falha ao executar a ação na lista");

                    }

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

        //BTN LIST ACTION LISTENER
        btnList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // A JANELA TABELA SÓ PODE SER ABERTA SE TABLESCREEN NÃO ESTIVER ATIVA.
                if (listController) {
                    //SE TABLE SCREEN ESTIVER ATIVA, A FECHA ANTES DE ABRIR NOVAMENTE.
                    mercadoriaTableScreen.dispose();
                    listController = false;
                }
                List<Mercadoria> mercadorias = mercadoriaController.listar();
                mercadoriaTableScreen = new MercadoriaTableScreen(mercadorias);
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
                    mercadoriaTableScreen.dispose();
                    listController = false;
                }
                mercadoriaController.saveData(path);
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
        txtIdCriador.setEditable(false);
        txtDescricao.setEditable(false);
        txtQuantidadeEmEstoque.setEditable(false);
    }

    private void clearAllFields() {

        txtId.setText("");
        txtIdCriador.setText("");
        txtDescricao.setText("");
        txtQuantidadeEmEstoque.setText("");
    }

}

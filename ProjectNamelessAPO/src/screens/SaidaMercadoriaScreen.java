package screens;

import controllers.SaidaMercadoriaController;
import daos.DAOFuncionario;
import daos.DAOMercadoria;
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
import tools.CaixaDeFerramentas;
import enums.DialogMessageType;
import daos.DAOSaidaMercadoria;
import models.SaidaMercadoria;

/**
 *
 * @author afmireski
 */
public class SaidaMercadoriaScreen extends JDialog {

//INSTANCIA DOS HELPERS
    GenericComponents components = new GenericComponents();
    BuildMessageDialog messageDialog;
    BuildConfirmDialog confirmDialog;
    ErrorTools errorTools = new ErrorTools();

//INSTANCIA DOS FUNCTIONS

//INSTANCIA DAS TOOLS
    final private CaixaDeFerramentas cf = new CaixaDeFerramentas();

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
    JButton btnAction = new JButton("Add to List");
    JButton btnCancel = new JButton("Cancel");
    JButton btnList = new JButton("List");

//INSTANCIA DOS DAOS
    String actionController;
    boolean listController = false;
    SaidaMercadoriaController saidaMercadoriaController = new SaidaMercadoriaController();
    

//INSTANCIA DOS LABELS
    JLabel lblId = new JLabel("ID");
    JLabel lblIdMercadoria = new JLabel("ID MERCADORIA");
    JLabel lblIdCriador = new JLabel("ID CRIADOR");
    JLabel lblQuantidadeSaida = new JLabel("QUANTIDA DE SAIDA");

//INSTANCIA DOS TEXTFIELD
    JTextField txtId = new JTextField(16);
    JTextField txtIdMercadoria = new JTextField(16);
    JTextField txtIdCriador = new JTextField(16);
    JTextField txtQuantidadeSaida = new JTextField(5);

//INSTANCIA DAS ENTIDADES
    SaidaMercadoria saidaMercadoria = new SaidaMercadoria();
//INSTANCIA DAS TABLE SCREENS
    SaidaMercadoriaTableScreen saidaMercadoriaTableScreen;

    public SaidaMercadoriaScreen() {
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setTitle("CRUD - SAIDAMERCADORIA");

        //LOAD DATA
        final String path = "SaidaMercadoria.csv";
        saidaMercadoriaController.loadData(path);

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
        panL1C1.add(lblIdMercadoria);
        panL1C2.add(txtIdMercadoria);

        //Prenchimento Linha 2
        panL2C1.add(lblIdCriador);
        panL2C2.add(txtIdCriador);

        //Prenchimento Linha 3
        panL3C1.add(lblQuantidadeSaida);
        panL3C2.add(txtQuantidadeSaida);

        //Prenchimento Linha 4
        panL4C2.add(btnAction);
        //BTN RETRIEVE ACTION LISTENER
        btnRetrieve.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (!txtId.getText().trim().isEmpty()) {
                        saidaMercadoria = saidaMercadoriaController.retrieve(txtId.getText());
                        if (saidaMercadoria != null) {
                            btnCreate.setEnabled(false);
                            btnCreate.setVisible(true);

                            txtIdMercadoria.setEditable(false);
                            txtIdCriador.setEditable(false);
                            txtQuantidadeSaida.setEditable(false);

                            txtId.setText(String.valueOf(saidaMercadoria.getId()));
                            txtIdMercadoria.setText(String.valueOf(saidaMercadoria.getIdMercadoria()));
                            txtIdCriador.setText(String.valueOf(saidaMercadoria.getIdCriador()));
                            txtQuantidadeSaida.setText(String.valueOf(saidaMercadoria.getQuantidadeSaida()));
                        } else {
                            btnCreate.setEnabled(true);
                            btnCreate.setVisible(true);

                            txtIdMercadoria.setEditable(true);
                            txtIdCriador.setEditable(true);
                            txtQuantidadeSaida.setEditable(true);
                            txtIdMercadoria.setText("");
                            txtIdCriador.setText("");
                            txtQuantidadeSaida.setText("");
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
                btnCreate.setVisible(false);
                btnCancel.setVisible(true);
                btnAction.setVisible(true);

                txtId.setEditable(false);
                txtIdMercadoria.requestFocus();

                actionController = "CREATE";

                btnAction.setText("Adicionar à Lista");
            }
        });

        //BTN ACTION ACTION LISTENER
        btnAction.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    saidaMercadoria = new SaidaMercadoria();
                    if (txtIdMercadoria.getText().trim().isEmpty() || txtIdCriador.getText().trim().isEmpty()
                            || txtQuantidadeSaida.getText().trim().isEmpty()) {
                        throw new Exception("Verifique se os seus campos estão preenchidos!");
                    } else if (Integer.valueOf(txtQuantidadeSaida.getText().trim()) <= 0) {
                        throw new Exception("A quantidade de saída deve ser maior que 0!");
                    } else {
                        saidaMercadoria.setId(txtId.getText());
                        saidaMercadoria.setIdMercadoria(txtIdMercadoria.getText());
                        saidaMercadoria.setIdCriador(txtIdCriador.getText());
                    }

                    saidaMercadoriaController.create(saidaMercadoria);

                    btnAction.setVisible(false);
                    btnRetrieve.setEnabled(true);
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
                    saidaMercadoriaTableScreen.dispose();
                    listController = false;
                }
                List<SaidaMercadoria> saidaMercadorias = saidaMercadoriaController.listar();
                saidaMercadoriaTableScreen = new SaidaMercadoriaTableScreen(saidaMercadorias);
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
                    saidaMercadoriaTableScreen.dispose();
                    listController = false;
                }
                saidaMercadoriaController.saveData(path);
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
    }

    private void textFieldInitialConfiguration() {	//TEXTFIELD INITIAL CONFIGURATIONS
        txtId.setEditable(true);
        txtIdMercadoria.setEditable(false);
        txtIdCriador.setEditable(false);
        txtQuantidadeSaida.setEditable(false);
    }

    private void clearAllFields() {

        txtId.setText("");
        txtIdMercadoria.setText("");
        txtIdCriador.setText("");
        txtQuantidadeSaida.setText("");
    }

}

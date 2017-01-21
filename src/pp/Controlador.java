package pp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;

import java.net.URL;
import java.util.ResourceBundle;

public class Controlador implements Initializable
{
    private RSA rsa;
    @FXML
    private Button aceptar, generar, cifrar, decifrar;
    @FXML
    private TextArea info, out, men;
    @FXML
    private TextField p, q;
    @FXML
    private Label lbmen, lbout;

    @FXML
    private void accept(ActionEvent e)
    {
        info.setDisable(false);
        String s = "";
        if (!q.getText().isEmpty() && !p.getText().isEmpty())
        {

            if (rsa.setPQ(p.getText(), q.getText()))
            {
                s = s + rsa.getN() + "\n" + rsa.getR() + "\n" + rsa.getE() + "\n" + rsa.getD() + "\n";
                men.setDisable(false);
                aceptar.setDefaultButton(false);
                cifrar.setDisable(false);
                cifrar.setDefaultButton(true);
                generar.setCancelButton(false);
                decifrar.setDisable(false);
                decifrar.setCancelButton(true);
                lbmen.setDisable(false);
                men.requestFocus();
            }
            else
                s=s+"Imgrese numeros mayores a 7";

        }
        if (p.getText().isEmpty())
            s = s + "Ingrese numeros en el campo de p\n";
        if (q.getText().isEmpty())
            s = s + "Ingrese numeros en el campo de q\n";
        info.setText(s);
    }

    @FXML
    private void gene(ActionEvent e)
    {
        rsa.generaQP();
        p.setText(rsa.getP());
        q.setText(rsa.getQ());
    }

    @FXML
    private void cif(ActionEvent e)
    {
        out.setDisable(false);
        out.setText(rsa.cifrar(men.getText()));
        out.requestFocus();
        out.selectAll();
        lbout.setDisable(false);
    }

    @FXML
    private void dec(ActionEvent e)
    {
        try
        {
            out.setText(rsa.decifrar(men.getText()));
            men.requestFocus();
            men.selectAll();
        }
        catch (Exception ex)
        {
            out.setText("");
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        rsa = new RSA();
        men.setOnKeyPressed(e ->
        {
            if (e.getCode() == KeyCode.ENTER)
            {
                cifrar.requestFocus();
                cif(new ActionEvent(null, null));
                e.consume();
            }

        });
        p.setOnMouseClicked(e ->
        {
            aceptar.setDefaultButton(true);
            generar.setCancelButton(true);
            cifrar.setDefaultButton(false);
            decifrar.setCancelButton(false);
        });
        q.setOnMouseClicked(e ->
        {
            aceptar.setDefaultButton(true);
            generar.setCancelButton(true);
            cifrar.setDefaultButton(false);
            decifrar.setCancelButton(false);
        });
        men.setOnMouseClicked(e ->
        {
            aceptar.setDefaultButton(false);
            generar.setCancelButton(false);
            cifrar.setDefaultButton(true);
            decifrar.setCancelButton(true);
        });
        out.setOnMouseClicked(e ->
        {
            aceptar.setDefaultButton(false);
            generar.setCancelButton(false);
            cifrar.setDefaultButton(true);
            decifrar.setCancelButton(true);
        });
    }
}

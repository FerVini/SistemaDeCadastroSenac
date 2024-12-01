package controller;
import view.*;
import model.*;
import static java.nio.file.StandardCopyOption.*;
import java.nio.file.*;
import javax.swing.*;
import java.awt.*;

public class TelaDeCadastroController extends TelaDeCadastroView {
    public static void cadastrarController() {
        TelaDeCadastroModel.cadastrarModel(txtNome.getText(), txtEmail.getText(), String.valueOf(txtSenha.getPassword()), nomeArquivoFoto);
    }

    public static void carregarFoto() {
        try {
            JFileChooser chooser = new JFileChooser();

            chooser.setDialogTitle("Selecione o arquivo que deseja copiar");
            chooser.setApproveButtonText("Copiar arquivo");
            int returnVal1 = chooser.showOpenDialog(null);
            String fileFullPath = "";
            String fileName = "";

            if(returnVal1 == JFileChooser.APPROVE_OPTION) {
                fileFullPath = chooser.getSelectedFile().getAbsolutePath();
                fileName = "file-" + Math.random() + "-" + chooser.getSelectedFile().getName();
            } else {
                System.out.println("O usuario não copiou o arquivo selecionado!");
            }

            Path pathOrigin = Paths.get(fileFullPath);
            Path pathDestination = Paths.get(localViewImgFolder + "\\" + fileName);
            if (fileFullPath.length() > 0) {
                Files.copy(pathOrigin, pathDestination, REPLACE_EXISTING);
                System.out.println("Arquivo " + chooser.getSelectedFile().getName() + " copiado/colado com sucesso.");
            } else {
                System.out.println("Ops! Não foi possível copiar o arquivo. Por favor, verifique e tente novamente mais tarde.");
            }

            nomeArquivoFoto = fileName;

            lblFoto.setIcon(new ImageIcon(new ImageIcon(localViewImgFolder + "\\" + nomeArquivoFoto).getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT)));
        } catch (Exception e) {
            System.err.println("Não foi possível copiar o arquivo.");
        }
    }

    public static void removerFoto() {

    }
}

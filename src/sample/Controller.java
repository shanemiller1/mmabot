package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

import javax.swing.*;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class Controller {

  @FXML
  private GridPane background;

  @FXML
  private ImageView imgBanner;

  @FXML
  private TextArea txtAraWebsiteInfo;

  @FXML
  private TableView<String> tblVwDB;

  @FXML
  private TextField txtFldLinkbar;

  private static String latestWinner;


  public void initialize() {
    txtAraWebsiteInfo.setEditable(false);
    txtFldLinkbar.setPromptText("Enter URL");
  }


  @FXML
  void confirm(ActionEvent event) {
    try {
      URL url = new URL(txtFldLinkbar.getText());
      Scanner s = new Scanner(url.openStream(), "UTF-8");
      while (s.hasNextLine()) {
        String line = s.nextLine();
        if (line.contains("Winner: ")) {
          if (!line.equals(latestWinner)) {
            latestWinner = line;
            line = line.replace("<span class=\"scoreboard small\">", "");
            line = line.replace("</span>", "");
          }
          txtAraWebsiteInfo.setText(line);
          System.out.println(line);
          break;
        }
      }
      s.close();

    } catch (MalformedURLException e) {
      JOptionPane.showMessageDialog(null, "no wins found");
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }

    try {
      Thread.sleep(100);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}

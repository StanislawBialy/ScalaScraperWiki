import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage
import scalafx.scene.Scene
import scalafx.scene.control.{Button, Label, TextField}
import scalafx.Includes._
import scalafx.event.ActionEvent

object Panel extends JFXApp {

  stage = new PrimaryStage {
    title = "Wikipedia Scraper"
    scene = new Scene(200, 200) {
      val urlLabel = new Label("Hasło:")
      urlLabel.layoutX = 20
      urlLabel.layoutY = 20

      val urlInput = new TextField
      urlInput.layoutX = 20
      urlInput.layoutY = 50

      val Stepslabel = new Label("Liczba przejsc:")
      Stepslabel.layoutX = 20
      Stepslabel.layoutY = 80

      val stepsInput = new TextField
      stepsInput.layoutX = 20
      stepsInput.layoutY = 110

      val searchButton = new Button("Szukaj")
      searchButton.layoutX = 20
      searchButton.layoutY = 140
      searchButton.onAction = (event: ActionEvent) => {

        var url = WikiScraper.apply().getUrl(stepsInput.getText().toInt, urlInput.getText())
        java.awt.Desktop.getDesktop().browse(java.net.URI.create(url))
      }


      content = List(urlLabel, Stepslabel, urlInput, stepsInput, searchButton)
    }
  }
}


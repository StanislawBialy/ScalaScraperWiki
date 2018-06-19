import net.ruippeixotog.scalascraper.browser.JsoupBrowser
import net.ruippeixotog.scalascraper.dsl.DSL._
import net.ruippeixotog.scalascraper.scraper.ContentExtractors.{attr, element, elementList}

import scala.collection.mutable.ListBuffer

class WikiScraper {
  def getUrl (loopCount: Int, searchValue: String): String = {
    //var searchValue = "piwo" //wartość wyszukiwana
    //var loopCount = 10 //ilosc przejsc
    val browser = JsoupBrowser()
    val search = browser.get("https://pl.wikipedia.org/w/index.php?search=" + searchValue) // pierwsze wyszukiwanie
    var urpPage = search.location
    for(j <- 1 to loopCount) {
      var page = browser.get(urpPage)
      var content = page >?> element("#content")
      var aList = content >> elementList("a")
      var string = aList.toList
      var elem21 = string(0)
      //pobieramy tylko prwdziwe lijnki

      val p1 = new ListBuffer[String]()
      for (i <- 1 to elem21.length - 1) {
        var ifElem = elem21(i) >?> attr("href")
        if (ifElem.isDefined) {
          var link = elem21(i).attr("href")

          if ((link contains ("/wiki/"))
            && (link contains ("http")) == false
            && (link contains ("wikipedia.org")) == false) {
            p1 += link
            //println(link)
          }
        }
      }
      val r = scala.util.Random
      val randNumberLink = r.nextInt(p1.length - 1)
      urpPage = "https://pl.wikipedia.org" + p1(randNumberLink)
      println(urpPage)
    }
    return urpPage  //tutaj mamy ostatni link
  }

}
object WikiScraper {
  val searchText = "scala"
  val loopCount = 7

  def apply() = new WikiScraper()

}